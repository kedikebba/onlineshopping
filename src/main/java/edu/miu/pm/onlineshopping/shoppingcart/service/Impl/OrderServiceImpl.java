package edu.miu.pm.onlineshopping.shoppingcart.service.Impl;

import edu.miu.pm.onlineshopping.admin.model.EndUser;
import edu.miu.pm.onlineshopping.product.model.Product;
import edu.miu.pm.onlineshopping.product.repository.IProductRepository;
import edu.miu.pm.onlineshopping.shoppingcart.model.CartItem;
import edu.miu.pm.onlineshopping.shoppingcart.model.Order;
import edu.miu.pm.onlineshopping.shoppingcart.model.OrderStatus;
import edu.miu.pm.onlineshopping.shoppingcart.repository.OrderRepository;
import edu.miu.pm.onlineshopping.shoppingcart.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private IProductRepository productRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, IProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Order addItemToCart(EndUser user, CartItem cartItem) {
        int numberOfItemsInCart = 0;
        double tax = 0.02;
        Order pendingOrder = getPendingOrder(user.getFirstName());

         if (pendingOrder != null){
            Map<Long, Integer> cartMaps = pendingOrder.getCartItems();
            cartMaps.put(cartItem.getProductId(), cartItem.getQuantity());
            pendingOrder.setCartItems(cartMaps);
//            pendingOrder.setCartItems(cartItem);
            double price = 0;
            for(Long id: pendingOrder.getCartItems().keySet()){
                Optional<Product> product = productRepository.findById(id);
                price += (product.get().getPrice() * pendingOrder.getCartItems().get(id));
            }
            pendingOrder.setTotalPrice(price + price*tax);
            numberOfItemsInCart = pendingOrder.getCartItems().keySet().size();
          return orderRepository.save(pendingOrder);


        } else {
            Order newOrder = new Order();
            newOrder.setBuyer(user);
            Map<Long, Integer> cartMap = newOrder.getCartItems();
            cartMap.put(cartItem.getProductId(), cartItem.getQuantity());
            newOrder.setCartItems(cartMap);
//            newOrder.setCartItems(cartItem);
            Optional<Product> product = productRepository.findById(cartItem.getProductId());
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
                Optional<Product> product = productRepository.findById(id);
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

    //Product Related implementation
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> searchProduct(String search) {
//        return productRespository.findAllByProductNameOrCategory_CategoryNameOrVendor_FirstName(search, search, search);
        return productRepository.findAllByProductNameContainsOrCategory_CategoryNameContainsOrVendor_FirstNameContains(search, search, search);
    }

    @Override
    public List<Product> getProducts(Set<Long> keySet) {
        List<Product> products = new ArrayList<>();
        for(Long id: keySet){
            products.add(productRepository.findById(id).get());
        }

        return products;
    }

    @Override
    public Order checkStock(Order order) {
        List<Product> stockErrors = new ArrayList<>();
        for (Long id: order.getCartItems().keySet()){
            Optional<Product> product = productRepository.findById(id);
           if (!product.isPresent()){
               stockErrors.add(product.get());
           }
           else if ((product.get().getQuantity() - order.getCartItems().get(id) < 0){
               stockErrors.add(product.get());
            }
        }
        order.setStockErrors(stockErrors);
        return order;
    }

}
