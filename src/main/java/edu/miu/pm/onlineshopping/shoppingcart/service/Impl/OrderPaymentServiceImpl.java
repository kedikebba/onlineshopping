package edu.miu.pm.onlineshopping.shoppingcart.service.Impl;


import edu.miu.pm.onlineshopping.shoppingcart.model.Payment;
import edu.miu.pm.onlineshopping.shoppingcart.repository.OrderPaymentRepository;
import edu.miu.pm.onlineshopping.shoppingcart.service.OrderPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderPaymentServiceImpl implements OrderPaymentService {
    @Autowired
    private OrderPaymentRepository orderPaymentRepository;

    @Override
    public Payment savePayment(Payment payment) {
        return orderPaymentRepository.save(payment);
    }
}
