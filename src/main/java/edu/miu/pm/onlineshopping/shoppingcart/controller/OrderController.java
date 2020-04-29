package edu.miu.pm.onlineshopping.shoppingcart.controller;

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
    public List<CartItem> addToCart(@RequestBody CartItem cartItem){
        //call order service and give this item to be saved in the Order object;
      String buyerName = "John";
       Order order = orderService.addItemToCart(buyerName, cartItem);

       List<Product> products = productService.getProducts(order.getCartItems().keySet());
       List<CartItem> cartItems = new ArrayList<>();
       for(Product product: products){
           CartItem item = new CartItem();
           item.setProductName(product.getProductName());
           item.setQuantity(order.getCartItems().get(product.getId()));
           cartItems.add(item);
       }
       return cartItems;
    }
}
