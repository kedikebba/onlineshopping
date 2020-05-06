package edu.miu.pm.onlineshopping;

import edu.miu.pm.onlineshopping.admin.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
   /*@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/**").permitAll().anyRequest().authenticated().and().csrf().disable();
    } */

    @Autowired
    DataSource loginDataSource;


    //Authentication
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(loginDataSource)
                .usersByUsernameQuery("select user_name,password,account_id from account where user_name = ?")

                .authoritiesByUsernameQuery("select role, first_name ,last_name from client where account_account_id= ?");
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    //Authorization
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //admin can access any API
        //vendor can crate product API
        //Other API  permitALL

        http.authorizeRequests()
                .antMatchers("/registerVendor","/getVendors","/updateVendor"
                        ,"/deleteVendor","/rejectVendor","/getInactiveVendors","/getActiveVendors").hasAnyRole(Role.VENDOR.toString(),Role.ADMIN.toString())
                .antMatchers("/registerVendor").hasRole(Role.VENDOR.toString())
                .antMatchers("/getEndusers","/getEnduser","/updateEnduser","/deleteEndUser"
                        ,"/activeEndUser","/deactiveEndUser","/getInactiveEndUsers","/getActiveEndUsers").hasRole(Role.ADMIN.toString())
                .antMatchers("/registerClient","/getEndusers","/updateClient","/deleteClient").hasRole(Role.ADMIN.toString())
                .antMatchers("/categories","/category","/update_category","/categories"
                        ,"/products","/vendor","/productDetails","/getProductStockQuantity","/upload").hasAnyRole(Role.ADMIN.toString(),Role.VENDOR.toString())
                .antMatchers("/home","/pay","/registerEndUser","/login"
                        ,"/products","/images","/getProducts","/productReport","/list","/addToCart","/cart","/editCart","/checkout","/orders").permitAll()
                .antMatchers("/").permitAll()
                .and().formLogin();

//        http.authorizeRequests()
//                .antMatchers("/registerVendor").hasAnyRole(Role.VENDOR.toString(),Role.ADMIN.toString())
//                .antMatchers("/login").permitAll().and().formLogin();
    }

}
