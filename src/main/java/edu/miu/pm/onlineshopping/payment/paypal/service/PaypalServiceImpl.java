package edu.miu.pm.onlineshopping.payment.paypal.service;

import java.util.ArrayList;
import java.util.List;

import edu.miu.pm.onlineshopping.payment.paypal.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Details;
import com.paypal.api.payments.Item;
import com.paypal.api.payments.ItemList;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

@Service
public class PaypalServiceImpl implements PaypalService {
	@Autowired
	private APIContext apiContext;

	/*
	 * redirectURLs has only CancelUrl and ReturnUrl options, the later proceeds to
	 * the actual payment paypal page, and the former abort the transaction
	 * early
	 */

	public Payment createPayment(Order order, String cancelUrl, String reviewUrl)
			throws PayPalRESTException {

		// 1. Creating payer:
		Payer payer = new Payer();
		payer.setPaymentMethod("paypal");
		PayerInfo payerInfo = new PayerInfo();
		payerInfo.setFirstName("Addisu").setLastName("Marilign").setEmail("addisu.marilign@gmail.com");
		payer.setPayerInfo(payerInfo);

		// 2. Set Redirect URL
		RedirectUrls redirectUrls = new RedirectUrls();
		redirectUrls.setCancelUrl(cancelUrl);
		redirectUrls.setReturnUrl(reviewUrl);

		// 3. Set Details and Add PaymentDetails
		// OrderDetail orderDetail = new OrderDetail();
		Details details = new Details();
		details.setSubtotal(String.valueOf(order.getSubtotal()));
		details.setTax(String.valueOf(order.getTax()));
		details.setShipping(String.valueOf(order.getShipping()));

		// 4. Set payment amount
		Amount amount = new Amount();
		amount.setCurrency("USD");
		amount.setTotal(String.valueOf(order.getTotal()));
		amount.setDetails(details);

		// 5. Set Transaction
		Transaction transaction = new Transaction();
		transaction.setDescription(
				"Transaction Handled by OnlineShoppingCart for the item" + order.getProductName());
		transaction.setAmount(amount);

		ItemList itemList = new ItemList();
		List<Item> items = new ArrayList<>();

		Item item = new Item();
		item.setCurrency("USD");
		item.setName(order.getProductName());
		item.setPrice(String.valueOf(order.getSubtotal()));
		item.setTax(String.valueOf(order.getTax()));
		item.setQuantity("1");

		items.add(item);
		itemList.setItems(items);
		transaction.setItemList(itemList);
		List<Transaction> listTransaction = new ArrayList<>();
		listTransaction.add(transaction);

		// 6. Create payment object:, set Intent to "authorize", add payer, set
		// transactions, redirectUrls
		Payment payment = new Payment();
		payment.setIntent("authorize");
		payment.setPayer(payer);
		payment.setTransactions(listTransaction);
		payment.setRedirectUrls(redirectUrls);

		// 7. create payment by passing the apiContext bean which is created in the PaypalConfig file
		return payment.create(apiContext);

	}

	@Override
	public Payment excutePayment(String paymentId, String payerId) throws PayPalRESTException {

		Payment payment = new Payment();

		payment.setId(paymentId);
		PaymentExecution paymentExecution = new PaymentExecution();
		paymentExecution.setPayerId(payerId);
		return payment.execute(apiContext, paymentExecution);

	}

}
