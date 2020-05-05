package edu.miu.pm.onlineshopping.shoppingcart.repository;

import edu.miu.pm.onlineshopping.shoppingcart.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderPaymentRepository extends JpaRepository<Payment, Long> {
}
