package edu.miu.pm.onlineshopping.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
    	//User information is kept in memory
		//When identifying role roler, ROLLER_ prefix will be added by default
        auth.inMemoryAuthentication().withUser("user").password("user").roles("USER").and()
        	.withUser("test").password("test").roles("TEST");
    }
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		http.formLogin() //Registration interface, the default is permit All
		.and()
		.authorizeRequests().antMatchers("/","/home").permitAll() //Can be accessed without identity authentication
		.and()
		.authorizeRequests().anyRequest().authenticated() //Other requests must have identity authentication
        .and()
        .csrf()
        .requireCsrfProtectionMatcher(new AntPathRequestMatcher("/oauth/authorize")).disable();
    }
	
	@Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
