package edu.miu.pm.onlineshopping.shoppingcart.repository;

import edu.miu.pm.onlineshopping.shoppingcart.model.Order;
import edu.miu.pm.onlineshopping.shoppingcart.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

////////////////     Author:               ///////
////---              Getaneh Yilma Letike, Id: 610112       ---------//

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findByBuyer_UserIdAndOrderStatus(int userId, Enum Orderstatus);
}
