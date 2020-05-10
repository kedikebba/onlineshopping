package edu.miu.pm.onlineshopping.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;


@Configuration
@EnableWebSecurity
public class ShoppingSecurityConfig  extends WebSecurityConfigurerAdapter {


    @Qualifier("accountDetailService")
    @Autowired
    UserDetailsService userDetailsService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers("/**").permitAll().anyRequest().authenticated().and().csrf().disable();
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/","/home", "/login", "/register", "/search").permitAll()
//                .antMatchers("/admin/**").hasAuthority("ADMIN")
//                .antMatchers("/activate/admin").hasAuthority("ADMIN")
//                .antMatchers("/activate-user/admin").hasAuthority("ADMIN")
//                .antMatchers("/deactivate/admin").hasAuthority("ADMIN")
//                .antMatchers("/deactivate-user/admin").hasAuthority("ADMIN")
//
//                .antMatchers("/vendor").hasAuthority("VENDOR")
//                .antMatchers("/delete/vendor").hasAuthority("VENDOR")
//                .antMatchers("/addProduct/vendor").hasAuthority("VENDOR")
//                .antMatchers("/editProduct/vendor").hasAuthority("VENDOR")
//                .antMatchers("/addProduct").hasAuthority("VENDOR")
//                .antMatchers("/edit/**").hasAuthority("VENDOR")
//
////                .antMatchers("/registerEndUser").hasAuthority("ENDUSER")
//                .antMatchers("/addtocart").hasAuthority("ENDUSER")
//                .antMatchers("/cart").hasAuthority("ENDUSER")
//                .antMatchers("/checkout").hasAuthority("ENDUSER")
//                .antMatchers("/checkout/execute").hasAuthority("ENDUSER")
//                .antMatchers("/checkout/complete").hasAuthority("ENDUSER")
//                .antMatchers("/orders").hasAuthority("ENDUSER")
//
////                .antMatchers("/charge").hasAuthority("ENDUSER")
////                .antMatchers("/orderConfirmation").hasAuthority("ENDUSER")
////                .antMatchers("/orders").hasAnyAuthority("SELLER","ENDUSER")
////                .antMatchers("/orderHistory").hasAuthority("ENDUSER")
////                .antMatchers("/products/user/follow/**").hasAuthority("ENDUSER")
////                .antMatchers("/products/user/unfollow/**").hasAuthority("ENDUSER")
////                .antMatchers("/orders/cancelOrder").hasAnyAuthority("SELLER","ENDUSER")
////                .antMatchers("/products/review/**").hasAuthority("ENDUSER")
////                .antMatchers("/products/review/add").hasAuthority("ENDUSER")
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .failureUrl("/login-error?error=true")
//                .defaultSuccessUrl("/login-success")
//                .usernameParameter("email")
//                .passwordParameter("password")//don't apply CSRF protection to /h2-console
//                .and()
//                .exceptionHandling().accessDeniedPage("/error/access-denied");
//
//        http.csrf().disable();
//        http.headers().frameOptions().disable();
//
//    }


    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }
    //
    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }


}
