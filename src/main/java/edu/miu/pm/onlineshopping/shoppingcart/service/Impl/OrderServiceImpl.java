package edu.miu.pm.onlineshopping.shoppingcart.service.Impl;

import edu.miu.pm.onlineshopping.admin.model.EndUser;
import edu.miu.pm.onlineshopping.product.model.Product;
import edu.miu.pm.onlineshopping.product.service.IProductService;
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
    private IProductService productService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, IProductService productService) {
        this.orderRepository = orderRepository;
        this.productService = productService;
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
                Product product = productService.findById(id);
                price += (product.getPrice() * pendingOrder.getCartItems().get(id));
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
            Product product = productService.findById(cartItem.getProductId());
            if (product != null){
                newOrder.setTotalPrice(product.getPrice() * cartItem.getQuantity() +
                                         product.getPrice() * cartItem.getQuantity() * tax);
            }

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
                Product product = productService.findById(id);
                price += (product.getPrice() * pendingOrder.getCartItems().get(id));
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
        return productService.findAll();
    }

    @Override
    public List<Product> searchProduct(String search) {
//        return productRespository.findAllByProductNameOrCategory_CategoryNameOrVendor_FirstName(search, search, search);
//        return productRepository.findAllByProductNameContainsOrCategory_CategoryNameContainsOrVendor_FirstNameContains(search, search, search);
        return productService.searchProduct(search);
    }

    @Override
    public List<Product> getProducts(Set<Long> keySet) {
        List<Product> products = new ArrayList<>();
        for(Long id: keySet){
            products.add(productService.findById(id));
        }

        return products;
    }

    @Override
    public Order checkStock(Order order) {
        List<Product> stockErrors = new ArrayList<>();
        for (Long id: order.getCartItems().keySet()){
            Product product = productService.findById(id);
           if (product == null ){
               stockErrors.add(product);
               order.setSufficientStockExist(false);

           }
           else if ((product.getQuantity() - order.getCartItems().get(id) < 0)){
               stockErrors.add(product);
               order.setSufficientStockExist(false);
            }
        }
        order.setStockErrors(stockErrors);

        return order;
    }

    @Override
    public void updateStock(Order order) {

        for (Long id: order.getCartItems().keySet()){
            Product product = productService.findById(id);
            if(product != null){
                product.setQuantity(product.getQuantity() - order.getCartItems().get(id));
                productService.saveProduct(product);
            }
        }
    }

    @Override
    public Order generateOrderNumber(Order order) {


        return null;
    }

}
