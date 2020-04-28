package edu.miu.pm.onlineshopping;

import edu.miu.pm.onlineshopping.email.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OnlineshoppingApplication {


    public static void main(String[] args) {
		SpringApplication.run(OnlineshoppingApplication.class, args);
	}


}
