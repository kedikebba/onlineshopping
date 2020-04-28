package edu.miu.pm.onlineshopping;


import edu.miu.pm.onlineshopping.email.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import edu.miu.pm.onlineshopping.payment.paypal.service.PaypalServiceImpl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OnlineshoppingApplication {


    public static void main(String[] args) {
		SpringApplication.run(OnlineshoppingApplication.class, args);
	}

	@Bean
	public PaypalServiceImpl getPaypalServiceImplBean(){

		return new PaypalServiceImpl();
	}


}
