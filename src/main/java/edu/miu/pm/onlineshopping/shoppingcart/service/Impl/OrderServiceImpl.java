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

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Map;
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
            Map<Long, Integer> cartMaps = pendingOrder.getCartItems();
            cartMaps.put(cartItem.getProductId(), cartItem.getQuantity());
            pendingOrder.setCartItems(cartMaps);
//            pendingOrder.setCartItems(cartItem);
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
            Map<Long, Integer> cartMap = newOrder.getCartItems();
            cartMap.put(cartItem.getProductId(), cartItem.getQuantity());
            newOrder.setCartItems(cartMap);
//            newOrder.setCartItems(cartItem);
            Optional<Product> product = productRespository.findById(cartItem.getProductId());
            product.ifPresent(item -> newOrder.setTotalPrice(item.getPrice() * cartItem.getQuantity() +
                                        item.getPrice() * cartItem.getQuantity() * tax)
                            );

            numberOfItemsInCart = 1;
          return orderRepository.save(newOrder);
        }

    }

    @Override
    public Order removeCartItem(String user, CartItem cartItem) {
        Order pendingOrder = getPendingOrder(user);
        Map<Long, Integer> cartItems = pendingOrder.getCartItems();
        cartItems.remove(cartItem.getProductId());
        pendingOrder.setCartItems(cartItems);

        if (!pendingOrder.getCartItems().isEmpty()) {
            double tax = 0.02;
            double price = 0;
            for (Long id : pendingOrder.getCartItems().keySet()) {
                Optional<Product> product = productRespository.findById(id);
                price += (product.get().getPrice() * pendingOrder.getCartItems().get(id));
            }
            pendingOrder.setTotalPrice(price + price * tax);

            return orderRepository.save(pendingOrder);
        } else {
            orderRepository.delete(pendingOrder);
            return null;
        }
    }

    public Order getPendingOrder(String userFirstName){
            return orderRepository.findByBuyerNameAndOrderStatus(userFirstName, OrderStatus.PENDING);
    }

}
