package edu.miu.pm.onlineshopping.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter{
	
	@Autowired
	private TokenStore tokenStore;
	
	@Autowired
    private AuthenticationManager authenticationManager;
	
	@Autowired
	private ApprovalStore approvalStore;
	
	@Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		// Add client information
		// Use memory to store OAuth client information
        clients.inMemory()
                // client_id
                .withClient("client")
                // client_secret
                .secret("secret")
                // The authorization types allowed by the client are different, and the way of obtaining tokens is different.
                .authorizedGrantTypes("authorization_code","implicit","refresh_token")
                .resourceIds("resourceId")
                //Callback uri, used to receive the server's return information during authorization_code and implicit authorization
                .redirectUris("http://localhost:8090/")
                // Allowed scope of authorization
                .scopes("app","test");
    }
	
	@Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore).approvalStore(approvalStore)
                .authenticationManager(authenticationManager);
    }
	
	@Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.realm("OAuth2-Sample")
        	.allowFormAuthenticationForClients()
        	.tokenKeyAccess("permitAll()")
        	.checkTokenAccess("isAuthenticated()");
    }
	
	@Bean
	public TokenStore tokenStore() {
		// The token is stored in memory (it can also be stored in the database or Redis).
		// If stored in middleware (database, Redis), the resource server and the authentication server may not be in the same project.
		// Note: If access_token is not saved, user information cannot be obtained through access_token
		return new InMemoryTokenStore();
	}
	
	@Bean
	public ApprovalStore approvalStore() throws Exception {
		TokenApprovalStore store = new TokenApprovalStore();
		store.setTokenStore(tokenStore);
		return store;
	}
}
