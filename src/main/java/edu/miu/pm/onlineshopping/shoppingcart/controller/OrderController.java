package edu.miu.pm.onlineshopping.shoppingcart.controller;

import edu.miu.pm.onlineshopping.admin.model.Address;
import edu.miu.pm.onlineshopping.admin.model.EndUser;
import edu.miu.pm.onlineshopping.admin.service.AddressService;
import edu.miu.pm.onlineshopping.admin.service.EndUserService;
import edu.miu.pm.onlineshopping.email.MailService;
import edu.miu.pm.onlineshopping.shoppingcart.model.*;
import edu.miu.pm.onlineshopping.shoppingcart.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.List;

////////////////     Author:               ///////
////---              Getaneh Yilma Letike, Id: 610112       ---------//

@RestController
@RequestMapping
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private EndUserService endUserService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private MailService mailService;


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

        }
        mav.setViewName("checkout");
        return mav;
    }

    @PostMapping("/checkout/execute")
    public ModelAndView checkoutOrder(@ModelAttribute("order") Order orderWithAddress) {
        orderService.saveOrder(orderWithAddress);
        //update order table - call editCart
        EndUser buyer = endUserService.getEndUserbyId(1);
        Order order = orderService.getPendingOrder(buyer);

        //check stock
        order = orderService.checkStock(order);

        //if there is a problem with stock return Order object with isSufficientStockExist = true
        if (!order.getStockErrors().isEmpty()) {
            order.setSufficientStockExist(false);
            ModelAndView mav = new ModelAndView();
            mav.addObject("order", order);
            mav.setViewName("order_incomplete");
            return mav;
        }
        //if stock is ok - send payment module order object
        order.setSufficientStockExist(true);
        ModelAndView mav = new ModelAndView();
        mav.addObject("order", order);
        order.setTotalPrice(order.getTotalPrice()+order.getShippingPrice());
        mav.setViewName("payments");
        return mav;
    }

    @PostMapping("/checkout/complete")
    public ModelAndView completeCheckout(){

        //if there is a problem with payment - return the order with the error in it
        EndUser buyer = endUserService.getEndUserbyId(1);
        Order order = orderService.getPendingOrder(buyer);
        order.setTotalPrice(order.getTotalPrice()+order.getShippingPrice());

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
        //send mail to buyer
        String[] recipients = {"getaneh.letike@gmail.com"};
        String message = "Congratulations! Your order successfully completed /n" +
                "Order Number:" + order.getOrderNumber() + " /n" +
                "Total price: " + order.getTotalPrice() + " /n" +
                "Date: " + order.getOrderCompletedDate() + " /n" +
                "Ordered by: " + order.getBuyer().getFirstName() + " /n" +
                "Payment Method: " + order.getMethod() + "/n" +
                "Your order will be delivered on: " + order.getDeliveryDate() + " /n";

//        String message =  " <h1>Congratulations! Your order successfully completed.</h1>" +
//        "<div>" +
//               "Order Number: <h3>" + order.getOrderNumber() +"</h3>" +
//                "Total price: <h3>" + order.getTotalPrice() + "</h3>" +
//               "Date: <h3>" + order.getOrderCompletedDate() +  "</h3>" +
//                "Ordered by: <h3>" + order.getBuyer().getFirstName() + "</h3>" +
//                "Payment Method: <h3>" + order.getMethod() + "</h3>" +
//                "Your order will be delivered on: <h3>" + order.getDeliveryDate() +  "</h3>" +
//        "<div>";

        String[] attachments = {};
        mailService.sendMailText(recipients, "Order completed", message, attachments );

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
