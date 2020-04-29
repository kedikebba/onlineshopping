package edu.miu.pm.onlineshopping.shoppingcart.controller;

import edu.miu.pm.onlineshopping.shoppingcart.model.Cart;
import edu.miu.pm.onlineshopping.shoppingcart.model.CartItem;
import edu.miu.pm.onlineshopping.shoppingcart.model.Order;
import edu.miu.pm.onlineshopping.shoppingcart.model.Product;
import edu.miu.pm.onlineshopping.shoppingcart.service.OrderService;
import edu.miu.pm.onlineshopping.shoppingcart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("order/api/v1")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;




    @PostMapping("/addToCart")
    public Cart addToCart(@RequestBody CartItem cartItem){
      String buyerName = "John";
       Order order = orderService.addItemToCart(buyerName, cartItem);

       return getCartFromOrder(order);
//       List<Product> products = productService.getProducts(order.getCartItems().keySet());
//       List<CartItem> cartItems = new ArrayList<>();
//       for(Product product: products){
//           CartItem item = new CartItem();
//           item.setProductName(product.getProductName());
//           item.setQuantity(order.getCartItems().get(product.getId()));
//           cartItems.add(item);
//       }
//       Cart cart = new Cart();
//       cart.setCartItems(cartItems);
//       cart.setTotalPrice(order.getTotalPrice());
//
//       return cart;
    }
    @PutMapping("/editCart")
    public Cart editCart(@RequestBody Cart cart){
        String buyerName = "John";
        Order order = null;
        for (int i=0; i<cart.getCartItems().size(); i++){
            order = orderService.addItemToCart(buyerName, cart.getCartItems().get(i));
        }
        return getCartFromOrder(order);

    }

    @DeleteMapping("/removeItem")
    public Cart deleteCartItem(@RequestBody CartItem cartItem){
        String buyerName = "John";
        Order order = orderService.removeCartItem(buyerName, cartItem);

        if (order == null){
            CartItem emptyItem = new CartItem();
            List<CartItem> items = new ArrayList<>();
            items.add(emptyItem);
            emptyItem.setProductName("");
            Cart cart = new Cart();
            cart.setCartItems(items);
            return cart;
        }

        return getCartFromOrder(order);
    }

    public Cart getCartFromOrder(Order order){
        List<Product> products = productService.getProducts(order.getCartItems().keySet());
        List<CartItem> cartItems = new ArrayList<>();
        for(Product product: products){
            CartItem item = new CartItem();
            item.setProductName(product.getProductName());
            item.setQuantity(order.getCartItems().get(product.getId()));
            cartItems.add(item);
        }
        Cart cart = new Cart();
        cart.setCartItems(cartItems);
        cart.setTotalPrice(order.getTotalPrice());

        return cart;
    }
}
