package edu.miu.pm.onlineshopping.shoppingcart.controller;

import edu.miu.pm.onlineshopping.admin.model.EndUser;
import edu.miu.pm.onlineshopping.admin.service.EndUserService;
import edu.miu.pm.onlineshopping.product.model.Product;
import edu.miu.pm.onlineshopping.shoppingcart.model.Cart;
import edu.miu.pm.onlineshopping.shoppingcart.model.CartItem;
import edu.miu.pm.onlineshopping.shoppingcart.model.Order;
import edu.miu.pm.onlineshopping.shoppingcart.model.OrderStatus;
import edu.miu.pm.onlineshopping.shoppingcart.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private EndUserService endUserService;


//    @PostMapping("/addToCart")
//    public ResponseEntity<Cart> addToCart(@RequestBody CartItem cartItem){
//        EndUser buyer = new EndUser();
//        buyer.setFirstName("John");
//       Order order = orderService.addItemToCart(buyer, cartItem);
//
////       Cart cart = getCartFromOrder(order);
//        Cart cart = new Cart();
//        cart.setCartItems(order.getCartItems());
//        cart.setTotalPrice(order.getTotalPrice());
//
//       return new ResponseEntity<>(cart, HttpStatus.OK);
//    }
    @PostMapping("/addToCart")
    public long addProductToCart(@RequestBody CartItem cartItem){
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        EndUser buyer = endUserService.getEndUserbyId(1);
        Order order = orderService.addItemToCart(buyer, cartItem);
        if (order == null)
            return 0;
        Cart cart = new Cart();
        cart.setCartItems(order.getCartItems());
        cart.setTotalPrice(order.getTotalPrice());
        Long count = order.getCartItems().stream().map(item -> item.getProductId()).distinct().count();

        return count;
    }
    @PutMapping("/cart/addToCart")
    public long updateCart(@RequestBody CartItem cartItem){
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        EndUser buyer = endUserService.getEndUserbyId(1);
        Order order = orderService.addItemToCart(buyer, cartItem);
        if (order == null)
            return 0;
        Cart cart = new Cart();
        cart.setCartItems(order.getCartItems());
        cart.setTotalPrice(order.getTotalPrice());
        Long count = order.getCartItems().stream().map(item -> item.getProductId()).distinct().count();

        return count;
    }
    @GetMapping("/cart")
    public ModelAndView getCart(){
        EndUser buyer = endUserService.getEndUserbyId(1);
        Order order = orderService.getPendingOrder(buyer);
        ModelAndView mav = new ModelAndView();
        if (order != null){
            List<CartItem> cartItems = order.getCartItems();
            mav.addObject("cartItems", cartItems);
            mav.addObject("productItems", order.getCartItems());
            mav.addObject("cartPage", true);

        }
        mav.setViewName("cart");
        return mav;
    }

    @PutMapping("/editCart")
    public ModelAndView editCart(@RequestBody CartItem cartItem){
        EndUser buyer = endUserService.getEndUserbyId(1);
        Order order = orderService.addItemToCart(buyer, cartItem);

        ModelAndView mav = new ModelAndView();
        if (order != null){
            List<CartItem> cartItems = order.getCartItems();
            mav.addObject("cartItems", cartItems);
            mav.addObject("productItems", order.getCartItems());
            mav.addObject("cartPage", true);

        }
        mav.setViewName("cart");
        return mav;

    }

    @DeleteMapping("/cart/removeItem")
    public long deleteCartItem(@RequestBody CartItem cartItem){
        System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
        EndUser buyer = endUserService.getEndUserbyId(1);
        Order order = orderService.removeCartItem(buyer, cartItem);

        ModelAndView mav = new ModelAndView();
        if (order != null){
            List<CartItem> cartItems = order.getCartItems();
            mav.addObject("cartItems", cartItems);
            mav.addObject("productItems", order.getCartItems());
            mav.addObject("cartPage", true);

        }
        mav.setViewName("cart");
        return order.getCartItems().size();
    }

    @GetMapping("/checkout")
    public ModelAndView getCheckoutPage(){
        EndUser buyer = endUserService.getEndUserbyId(1);
        Order order = orderService.getPendingOrder(buyer);

        ModelAndView mav = new ModelAndView();
        if (order != null){
            List<CartItem> cartItems = order.getCartItems();
            mav.addObject("cartItems", cartItems);
            mav.addObject("productItems", order.getCartItems());
            mav.addObject("order", order);

        }
        mav.setViewName("checkout");
        return mav;
    }

    @GetMapping("/checkout/complete")
    public Order checkoutOrder(){
        //update order table - call editCart
        EndUser buyer = endUserService.getEndUserbyId(1);
        Order order = orderService.getPendingOrder(buyer);
//        for (int i=0; i<cart.getCartItems().size(); i++){
//            order = orderService.addItemToCart(buyer, cart.getCartItems().get(i));
//        }
        //check stock
        order = orderService.checkStock(order);

        //if there is a problem with stock return Order object with isSufficientStockExist = true
        if (!order.getStockErrors().isEmpty()){
            order.setSufficientStockExist(true);
            return order;
        }
        //if stock is ok - send payment module order object
        //if there is a problem with payment - return the order with the error in it

        //if payment is ok - reduce quantity of product
        orderService.updateStock(order);

        //generate order number
        order = orderService.generateOrderNumber(order);

        //generate orderComplete date
        order.setOrderCompletedDate(LocalDate.now());
        //change the order status to COMPLETED,
        order.setOrderStatus(OrderStatus.COMPLETED);
        //generate delivery date - in 3 days
        order.setDeliveryDate(LocalDate.now().plusDays(3));// 3 days delivery
        //save order to database
        order = orderService.saveOrder(order);
        //return the order

        return order;
    }

    @GetMapping("/orders")
    public ModelAndView getOrders(){
        //get the logged in user

        ModelAndView mav = new ModelAndView();
//        mav.addObject("orders", orderService.getOrders(user));
        mav.addObject("orders", orderService.getAllOrders());
        mav.setViewName("orders");
        return mav;
    }

//    public Cart getCartFromOrder(Order order){
////        List<Product> products = orderService.getProducts(order.getCartItems().keySet());
//        List<Long> keyList = order.getCartItems().stream()
//                                                .map(item -> item.getProductId())
//                                                .collect(Collectors.toList());
//        List<Product> products = orderService.getProducts(keyList);
//        List<CartItem> cartItems = new ArrayList<>();
//        for(Product product: products){
//            CartItem item = new CartItem();
//            item.setProductName(product.getProductName());
//            item.setQuantity(order.getCartItems().get(product.getProductId()));
//            cartItems.add(item);
//        }
//        Cart cart = new Cart();
//        cart.setCartItems(cartItems);
//        cart.setTotalPrice(order.getTotalPrice());
//
//        return cart;
//    }

}
