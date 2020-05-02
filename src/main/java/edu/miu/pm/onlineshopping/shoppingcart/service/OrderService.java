package edu.miu.pm.onlineshopping.shoppingcart.service;

import edu.miu.pm.onlineshopping.admin.model.EndUser;
import edu.miu.pm.onlineshopping.product.model.Product;
import edu.miu.pm.onlineshopping.shoppingcart.model.CartItem;
import edu.miu.pm.onlineshopping.shoppingcart.model.Order;

import java.util.List;
import java.util.Set;

public interface OrderService {


    //retrurns number of items in the cart
    Order addItemToCart(EndUser user, CartItem cartItem);

    Order removeCartItem(String user, CartItem cartItem);
    
    //Related to Saearch-product
    List<Product> getAllProducts();

    List<Product> searchProduct(String search);

    List<Product> getProducts(Set<Long> keySet);

    Order checkStock(Order order);

    void updateStock(Order order);

    Order generateOrderNumber(Order order);
}
