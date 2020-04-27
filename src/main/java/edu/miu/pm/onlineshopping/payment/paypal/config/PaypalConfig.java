package edu.miu.pm.onlineshopping.payment.paypal.config;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;
@Configuration
public class PaypalConfig {
        @Value("${paypal.client.id}")
        private String clientId;
        @Value("${paypal.client.secret}")
        private String clientSecrete;
        @Value("${paypal.mode}")
        private String mode;

        @Bean
        public Map<String, String> paypalSdkConfig(){
            //creating the object of the map
            Map<String, String> configMap= new HashMap<>();
            //set the mode we mentioned in the properties file
            configMap.put("mode", mode);
            return configMap;
        }

        @Bean
        public OAuthTokenCredential oAuthTokenCredential() {
            return new OAuthTokenCredential(clientId,clientSecrete,paypalSdkConfig());
        }

        @Bean
        public APIContext apiContext() throws PayPalRESTException {
            APIContext context= new APIContext(oAuthTokenCredential().getAccessToken());
            context.setConfigurationMap(paypalSdkConfig());
            return context;
        }
    }