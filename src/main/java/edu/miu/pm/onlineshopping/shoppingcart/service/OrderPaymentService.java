package edu.miu.pm.onlineshopping.shoppingcart.service;

import edu.miu.pm.onlineshopping.shoppingcart.model.Payment;

public interface OrderPaymentService {

    Payment savePayment(Payment payment);
}
