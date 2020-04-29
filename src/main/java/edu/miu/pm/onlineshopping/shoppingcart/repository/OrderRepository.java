package edu.miu.pm.onlineshopping.shoppingcart.repository;

import edu.miu.pm.onlineshopping.shoppingcart.model.Order;
import edu.miu.pm.onlineshopping.shoppingcart.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

//    List<Order> findAllByBuyer_FirstNameAndOrderStatus(String UserFirstName, Enum Orderstatus);
//    List<Order> findAllByBuyerNameAndOrderStatus(String UserFirstName, Enum Orderstatus);
    Order findByBuyerNameAndOrderStatus(String UserFirstName, Enum Orderstatus);
}
