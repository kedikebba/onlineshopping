package edu.miu.pm.onlineshopping.shoppingcart.controller;

import edu.miu.pm.onlineshopping.admin.model.EndUser;
import edu.miu.pm.onlineshopping.admin.service.EndUserService;
import edu.miu.pm.onlineshopping.product.model.Product;
import edu.miu.pm.onlineshopping.shoppingcart.model.*;
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
            mav.addObject("payment", new Payment());

        }
        mav.setViewName("checkout");
        return mav;
    }

    @PostMapping("/checkout/complete")
    public ModelAndView checkoutOrder(@ModelAttribute Payment payment){
//        System.out.println("PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP");
        System.out.println(payment.getMethod());
                //update order table - call editCart
        EndUser buyer = endUserService.getEndUserbyId(1);
        Order order = orderService.getPendingOrder(buyer);
        order.setPayment(payment);
        //check stock
        order = orderService.checkStock(order);

        //if there is a problem with stock return Order object with isSufficientStockExist = true
        if (!order.getStockErrors().isEmpty()){
            order.setSufficientStockExist(true);
            ModelAndView mav = new ModelAndView();
            mav.addObject("order", order);
            mav.setViewName("order_complete");
            return mav;
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

        ModelAndView mav = new ModelAndView();
        mav.addObject("order", order);
        mav.setViewName("order_complete");

        return mav;
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

}
