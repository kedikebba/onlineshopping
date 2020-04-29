package edu.miu.pm.onlineshopping.shoppingcart.service;

import edu.miu.pm.onlineshopping.shoppingcart.model.CartItem;
import edu.miu.pm.onlineshopping.shoppingcart.model.Order;

public interface OrderService {


    //retrurns number of items in the cart
    Order addItemToCart(String user, CartItem cartItem);

    Order removeCartItem(String user, CartItem cartItem);
}
