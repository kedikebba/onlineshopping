package edu.miu.pm.onlineshopping;

import edu.miu.pm.onlineshopping.email.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OnlineshoppingApplication implements CommandLineRunner {

    @Autowired
    private MailService mailService;

    private static final String[] RECIPIENTS = {"zhangziyiemail@gmail.com"};
    private static final String SUBJECT = "Shopping Detail";


    public static void main(String[] args) {
		SpringApplication.run(OnlineshoppingApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        sendNormalTextMessage();
    }


    private void sendNormalTextMessage() {
        try {
            mailService.sendMailText(RECIPIENTS, SUBJECT, "Hello this from Online Shopping", null);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

}
