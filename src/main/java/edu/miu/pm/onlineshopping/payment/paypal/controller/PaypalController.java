package edu.miu.pm.onlineshopping.payment.paypal.controller;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import edu.miu.pm.onlineshopping.payment.paypal.model.Order;
import edu.miu.pm.onlineshopping.payment.paypal.service.PaypalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PaypalController {
//    @Autowired
//    PaypalServiceImpl paypalServiceImpl;
//    @GetMapping("/")
//    public String home() {
//        return "home";
//    }
//    public static final String SUCCESS_URL="pay/success";
//    public static final String CANCEL_URL="pay/cancel";
//
//    @PostMapping("/pay")
//    public String payment(@ModelAttribute("order") Order order )  {
//        try {
//            Payment payment=paypalServiceImpl.
//                    createPayment(order.getPrice(),
//                            order.getCurrency(),
//                            order.getMethod(),
//                            order.getIntent(),
//                            order.getDescription(),
//                            "http://localhost:9090/"+CANCEL_URL,
//                            "http://localhost:9090/"+SUCCESS_URL);
//            for(Links link:payment.getLinks()) {
//                if(link.getRel().equals("approval_url"))
//                {
//                    return "redirect:"+link.getHref();
//                }
//            }
//
//        } catch (PayPalRESTException e) {
//            e.printStackTrace();
//        }
//        return "redirect:/";
//    }
//
//    @GetMapping(value=CANCEL_URL)
//    public String cancelPay() {
//        return "cancel";
//    }
//
//    @GetMapping(value=SUCCESS_URL)
//    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerId") String payerId) {
//        try {
//            Payment payment=paypalServiceImpl.excutePayment(paymentId, payerId);
//            System.out.println(payment.toJSON());
//            if(payment.getState().equals("approved")) {
//                return "success";
//            }
//        }
//        catch (PayPalRESTException e){
//            System.out.println(e.getMessage());
//
//        }
//        return "redirect:/";
//    }
}