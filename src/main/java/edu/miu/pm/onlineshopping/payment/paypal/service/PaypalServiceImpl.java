package edu.miu.pm.onlineshopping.payment.paypal.service;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;


public class PaypalServiceImpl implements  PaypalService {

    @Autowired
    private APIContext apiContext;

    @Override
    public Payment createPayment(Double total, String currency, String method, String intent, String description,
                                 String cancelUrl, String successUrl) throws PayPalRESTException {

        Amount amount= new Amount();
        total= new BigDecimal(total).setScale(2, RoundingMode.HALF_UP).doubleValue();
        amount.setCurrency(currency);

        amount.setTotal(String.format("%.2f", total));

        Transaction transaction= new Transaction();

        transaction.setDescription(description);
        transaction.setAmount(amount);
        List<Transaction> transactions= new ArrayList<>();
        transactions.add(transaction);

        Payer payer= new Payer();
        payer.setPaymentMethod(method).toString();

        Payment payment = new Payment();
        payment.setIntent(intent.toString());
        payment.setPayer(payer);
        payment.setTransactions(transactions);

        RedirectUrls redirectUrls= new RedirectUrls();
        redirectUrls.setCancelUrl(cancelUrl);
        redirectUrls.setReturnUrl(successUrl);
        payment.setRedirectUrls(redirectUrls);
        return payment.create(apiContext);
    }

    @Override
    public Payment excutePayment(String paymentId, String payerId) throws PayPalRESTException {
        // TODO Auto-generated method stub
        Payment payment= new Payment();
        payment.setId(paymentId);
        PaymentExecution  paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);
        return payment.execute(apiContext, paymentExecution);

    }
}
