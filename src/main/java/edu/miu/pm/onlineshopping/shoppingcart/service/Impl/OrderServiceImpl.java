package edu.miu.pm.onlineshopping.shoppingcart.service.Impl;

import edu.miu.pm.onlineshopping.shoppingcart.model.CartItem;
import edu.miu.pm.onlineshopping.shoppingcart.model.Order;
import edu.miu.pm.onlineshopping.shoppingcart.model.OrderStatus;
import edu.miu.pm.onlineshopping.shoppingcart.model.Product;
import edu.miu.pm.onlineshopping.shoppingcart.repository.OrderRepository;
import edu.miu.pm.onlineshopping.shoppingcart.repository.ProductRespository;
import edu.miu.pm.onlineshopping.shoppingcart.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRespository productRespository;

    @Override
    public Order addItemToCart(String user, CartItem cartItem) {
        int numberOfItemsInCart = 0;
        double tax = 0.02;
        Order pendingOrder = getPendingOrder(user);

        if (pendingOrder != null){
            pendingOrder.setCartItems(cartItem);
            double price = 0;
            for(Long id: pendingOrder.getCartItems().keySet()){
                Optional<Product> product = productRespository.findById(id);
                price += (product.get().getPrice() * pendingOrder.getCartItems().get(id));
            }
            pendingOrder.setTotalPrice(price + price*tax);
            numberOfItemsInCart = pendingOrder.getCartItems().keySet().size();
          return orderRepository.save(pendingOrder);


        } else {
            Order newOrder = new Order();
            newOrder.setBuyerName(user);
            newOrder.setCartItems(cartItem);
            Optional<Product> product = productRespository.findById(cartItem.getProductId());
            product.ifPresent(item -> newOrder.setTotalPrice(item.getPrice() * cartItem.getQuantity() +
                                        item.getPrice() * cartItem.getQuantity() * tax)
                            );

            numberOfItemsInCart = 1;
          return orderRepository.save(newOrder);
        }



//        return numberOfItemsInCart;
    }

    public Order getPendingOrder(String userFirstName){
            return orderRepository.findByBuyerNameAndOrderStatus(userFirstName, OrderStatus.PENDING);
    }
}
