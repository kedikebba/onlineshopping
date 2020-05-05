package edu.miu.pm.onlineshopping.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableResourceServer
public class ResServerConfig extends ResourceServerConfigurerAdapter{
	
	@Autowired
	private TokenStore tokenStore;

	@Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources
                .tokenStore(tokenStore)
                .resourceId("resourceId");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
    	/*
    	 requestMatchers()：
    	 Invoking requestMatchers() will not override previous invocations of ::
    	 mvcMatcher(String)}, requestMatchers(), antMatcher(String), regexMatcher(String), and requestMatcher(RequestMatcher).
    	 */
        
        http
        	// Since we want the protected resources to be accessible in the UI as well we need 
			// session creation to be allowed (it's disabled by default in 2.0.6)
        	.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
			.and()
       		.requestMatchers()
           .antMatchers("/user","/res/**")
           .and()
           .authorizeRequests()
           .antMatchers("/user","/res/**")
           .authenticated();
    }
}
