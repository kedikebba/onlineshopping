package edu.miu.pm.onlineshopping.shoppingcart.service.Impl;

import edu.miu.pm.onlineshopping.admin.model.Address;
import edu.miu.pm.onlineshopping.admin.model.EndUser;
import edu.miu.pm.onlineshopping.admin.service.AddressService;
import edu.miu.pm.onlineshopping.product.model.Product;
import edu.miu.pm.onlineshopping.product.service.IProductService;
import edu.miu.pm.onlineshopping.shoppingcart.model.CartItem;
import edu.miu.pm.onlineshopping.shoppingcart.model.Order;
import edu.miu.pm.onlineshopping.shoppingcart.model.OrderStatus;
import edu.miu.pm.onlineshopping.shoppingcart.repository.CartItemRepository;
import edu.miu.pm.onlineshopping.shoppingcart.repository.OrderRepository;
import edu.miu.pm.onlineshopping.shoppingcart.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

////////////////     Author:               ///////
////---              Getaneh Yilma Letike, Id: 610112       ---------//

@Service
@EnableTransactionManagement
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private IProductService productService;
    private CartItemRepository cartItemRepository;
    private AddressService addressService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, IProductService productService, CartItemRepository cartItemRepository, AddressService addressService) {
        this.orderRepository = orderRepository;
        this.productService = productService;
        this.cartItemRepository = cartItemRepository;
        this.addressService = addressService;
    }

    @Override
    @Transactional
    public Order addItemToCart(EndUser user, CartItem cartItem) {
        int numberOfItemsInCart = 0;
        double tax = 0.02;
        Order pendingOrder = getPendingOrder(user);
        if (cartItem.getProductId() == 0){
            return pendingOrder;
        }

         if (pendingOrder != null){
             List<CartItem> cartItems = pendingOrder.getCartItems();
             boolean itemExixts = false;
             for (CartItem item: cartItems){
                if (item.getProductId() == cartItem.getProductId()){
                    item.setQuantity(cartItem.getQuantity());
                    saveCartItem(item);
                    itemExixts = true;
                }
             }
             if (!itemExixts){
                 Product product = productService.findById(cartItem.getProductId());
                 cartItem.setProductName(product.getProductName());
                 cartItem.setProductPrice(product.getPrice());
                 cartItem.setQuantityInStock(product.getQuantity());
                 saveCartItem(cartItem);
                 cartItems.add(cartItem);
             }
            double price = 0;
            for (CartItem item: cartItems){
                Product product = productService.findById(item.getProductId());
                price += product.getPrice()*item.getQuantity();
            }
            pendingOrder.setCartItems(cartItems);
            pendingOrder.setTotalPrice(price + price*tax);
             numberOfItemsInCart = pendingOrder.getCartItems().size();
             return saveOrder(pendingOrder);
        } else {
            Order newOrder = new Order();
            newOrder.setBuyer(user);
             Product product = productService.findById(cartItem.getProductId());
             cartItem.setProductName(product.getProductName());
             cartItem.setProductPrice(product.getPrice());
             cartItem.setQuantityInStock(product.getQuantity());
            saveCartItem(cartItem);
             List<CartItem> cartItems = new ArrayList<>();
             cartItems.add(cartItem);
             newOrder.setCartItems(cartItems);
            if (product != null){
                newOrder.setTotalPrice(product.getPrice() * cartItem.getQuantity() +
                                         product.getPrice() * cartItem.getQuantity() * tax);
            }
            numberOfItemsInCart = 1;
             return saveOrder(newOrder);
        }

    }

    @Override
    @Transactional
    public Order removeCartItem(EndUser buyer, CartItem cartItem) {
        Order pendingOrder = getPendingOrder(buyer);
        List<CartItem> cartItems = pendingOrder.getCartItems();
        for (int i=0; i<cartItems.size(); i++){
            if (cartItem.getProductId() == cartItems.get(i).getProductId())
                cartItems.remove(i);
        }
        deleteCartItem(cartItem);
        pendingOrder.setCartItems(cartItems);
        if (!pendingOrder.getCartItems().isEmpty()) {
            double tax = 0.02;
            double price = 0;
            for (CartItem item: cartItems){
                Product product = productService.findById(item.getProductId());
                price += product.getPrice()*item.getQuantity();
            }
            pendingOrder.setTotalPrice(price + price * tax);
            return saveOrder(pendingOrder);
        } else {
            orderRepository.delete(pendingOrder);
            return null;
        }
    }

    @Override
    @Transactional
    public Order getPendingOrder(EndUser buyer){
        return orderRepository.findByBuyer_UserIdAndOrderStatus(buyer.getUserId(), OrderStatus.PENDING);
    }

    @Override
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    @Override
    public List<Product> searchProduct(String search) {
        return productService.searchProduct(search);
    }

    @Override
    public List<Product> getProducts(List<Long> keyList) {
        List<Product> products = new ArrayList<>();
        for(Long id: keyList){
            products.add(productService.findById(id));
        }

        return products;
    }

    @Override
    public Order checkStock(Order order) {
        List<Product> stockErrors = new ArrayList<>();
        for (CartItem item: order.getCartItems()){
            Product product = productService.findById(item.getProductId());
           if (product == null ){
               stockErrors.add(product);
               order.setSufficientStockExist(false);

           }
           else if ((product.getQuantity() - item.getQuantity()) < 0){
               stockErrors.add(product);
               order.setSufficientStockExist(false);
            }
        }
        if (!stockErrors.isEmpty())
            order.setStockErrors(stockErrors);

        return order;
    }

    @Override
    @Transactional
    public void updateStock(Order order) {

        for (CartItem item: order.getCartItems()){
            Product product = productService.findById(item.getProductId());
            if(product != null){
                product.setQuantity(product.getQuantity() - item.getQuantity());
                product.setSoldQuantity(item.getQuantity());
                productService.saveProduct(product);
            }
        }
    }

    @Override
    public Order generateOrderNumber(Order order) {
        String orderNumber = "";
        String part1 = LocalDate.now().toString().substring(5,7) + LocalDate.now().toString().substring(8);
        String part2 = order.getBuyer().getFirstName().substring(0,3);
        String part3 = order.getBuyer().getAddress().getZipCode();
        String part4 = LocalDateTime.now().toString().substring(17,19) + LocalDateTime.now().toString().substring(20, 24);
        orderNumber = part1 + part2 + part3 + part4;
        order.setOrderNumber(orderNumber);
        return order;
    }

    @Override
    @Transactional
    public Order saveOrder(Order order) {
        Address address = null;
        if (order.getBillingAddress() != null) {
            address = addressService.getAddress(order.getBillingAddress().getStreet(), order.getBillingAddress().getState(),
                    order.getBillingAddress().getCity(), order.getBillingAddress().getZipCode());
        }
        if (address != null){
            order.setBillingAddress(address);
        }
        else if (order.getBillingAddress() != null){
            addressService.saveAddress(order.getBillingAddress());
        }

        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    @Transactional
    public CartItem saveCartItem(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    @Transactional
    public void deleteCartItem(CartItem cartItem){
        cartItemRepository.delete(cartItem);
    }

}
