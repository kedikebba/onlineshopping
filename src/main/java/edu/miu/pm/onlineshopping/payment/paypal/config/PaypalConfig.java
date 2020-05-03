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

    /**
 * OAuthTokenCredential is used for generation of OAuth Token used by PayPal
 * REST API service. ClientID and ClientSecret are required by the class to
 * generate OAuth Token, the resulting token is of the form "Bearer xxxxxx". The
 * class has two constructors, one of it taking an additional configuration map
 * used for dynamic configuration. When using the constructor with out
 * configuration map the endpoint is fetched from the configuration that is used
 * during initialization. See {@link PayPalResource} for configuring the system.
 * When using a configuration map the class expects an entry by the name
 * "oauth.EndPoint" or "service.EndPoint" to retrieve the value of the endpoint
 * for the OAuth Service. If either are not present the configuration should
 * have a entry by the name "mode" with values sandbox or live wherein the
 * corresponding endpoints are default to PayPal endpoints.
 *
 * @author kjayakumar
 *
 */

    /**
     * Configuration Constructor for dynamic configuration
     *
     * @param clientID         Client ID for the OAuth
     * @param clientSecret     Client Secret for OAuth
     * @param configurationMap Dynamic configuration map which should have an entry
     *                         for 'oauth.EndPoint' or 'service.EndPoint'. If either
     *                         are not present then there should be entry for 'mode'
     *                         with values sandbox/live, wherein PayPals endpoints
     *                         are used.
     */

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