package edu.miu.pm.onlineshopping.payment.paypal.service;

import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

import edu.miu.pm.onlineshopping.payment.paypal.model.Order;


public interface PaypalService {
	public Payment createPayment(Order order, String cancelUrl, String successUrl) throws PayPalRESTException;

	public Payment excutePayment(String paymentId, String payerId) throws PayPalRESTException;
}
