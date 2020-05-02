package edu.miu.pm.onlineshopping.shoppingcart.controller;

import edu.miu.pm.onlineshopping.admin.model.EndUser;
import edu.miu.pm.onlineshopping.product.model.Product;
import edu.miu.pm.onlineshopping.shoppingcart.model.Cart;
import edu.miu.pm.onlineshopping.shoppingcart.model.CartItem;
import edu.miu.pm.onlineshopping.shoppingcart.model.Order;
import edu.miu.pm.onlineshopping.shoppingcart.model.OrderStatus;
import edu.miu.pm.onlineshopping.shoppingcart.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("order/api/v1")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @PostMapping("/addToCart")
    public ResponseEntity<Cart> addToCart(@RequestBody CartItem cartItem){
        EndUser buyer = new EndUser();
        buyer.setFirstName("John");
       Order order = orderService.addItemToCart(buyer, cartItem);

//       Cart cart = getCartFromOrder(order);
        Cart cart = new Cart();
        cart.setCartItems(order.getCartItems());
        cart.setTotalPrice(order.getTotalPrice());

       return new ResponseEntity<>(cart, HttpStatus.OK);
    }
    @PutMapping("/editCart")
    public ResponseEntity<Cart> editCart(@RequestBody Cart cart){
        EndUser buyer = new EndUser();
        buyer.setFirstName("John");
        Order order = null;
        for (int i=0; i<cart.getCartItems().size(); i++){
            order = orderService.addItemToCart(buyer, cart.getCartItems().get(i));
        }
//        Cart responseCart = getCartFromOrder(order);
        Cart responseCart = new Cart();
        cart.setCartItems(order.getCartItems());
        cart.setTotalPrice(order.getTotalPrice());
        return new ResponseEntity<>(responseCart, HttpStatus.OK);

    }

    @DeleteMapping("/removeItem")
    public ResponseEntity<Cart> deleteCartItem(@RequestBody CartItem cartItem){
       EndUser buyer = new EndUser();
       //get the user from loggedInUser...
        Order order = orderService.removeCartItem(buyer, cartItem);

        if (order == null){
            CartItem emptyItem = new CartItem();
            List<CartItem> items = new ArrayList<>();
            items.add(emptyItem);
            emptyItem.setProductName("");
            Cart cart = new Cart();
            cart.setCartItems(items);
            return new ResponseEntity<>(cart, HttpStatus.OK);
        }
//        Cart cart = getCartFromOrder(order);
        Cart cart = new Cart();
        cart.setCartItems(order.getCartItems());
        cart.setTotalPrice(order.getTotalPrice());

        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PostMapping("/checkout")
    public ResponseEntity<Order> checkoutOrder(@RequestBody Cart cart){
        //update order table - call editCart
        EndUser buyer = new EndUser();
        buyer.setFirstName("John");
        Order order = null;
        for (int i=0; i<cart.getCartItems().size(); i++){
            order = orderService.addItemToCart(buyer, cart.getCartItems().get(i));
        }
        //check stock
        order = orderService.checkStock(order);

        //if there is a problem with stock return Order object with isSufficientStockExist = true
        if (!order.getStockErrors().isEmpty()){
            order.setSufficientStockExist(true);
            return new ResponseEntity<>(order, HttpStatus.BAD_REQUEST);
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

        return new ResponseEntity<>(order, HttpStatus.OK);
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
