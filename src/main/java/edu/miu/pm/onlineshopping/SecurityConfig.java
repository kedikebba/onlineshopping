package edu.miu.pm.onlineshopping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /* 1st. Authentication for users.
    For the sake of simplicity, we can use an in-built Memory and assign user and password for every roles we have. */
    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
                 auth.inMemoryAuthentication()
                         .withUser("admin")
                         .password("admin")
                         .roles("ADMIN")
                 .and()
                         .withUser("vendor")
                         .password("vendor")
                         .roles("VENDOR")
                 .and()
                         .withUser("client")
                          .password("client")
                          .roles("CLIENT")
                 .and()
                        .withUser("enduser")
                        .password("enduser")
                        .roles("ENDUSER");
        //Roles:ADMIN, CLIENT, VENDOR, ENDUSER
    }

    /* 2nd: Encode password.
     Instead of placing plain password to the database (in this case the built in memory),
     we should encode the password. Create password encoding bean, the BCryptPasswordEncoder */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

/*3rd: Authorization, using HttpSecurity.
 HttpSecurity object helps to configure the path and the access restrictions to paths.*/
/* The most restrictive antMatcher should come first, followed by the next restrictive.*/
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/vendor/**").hasAnyRole("VENDOR","ADMIN")
                .antMatchers("user/**").hasAnyRole("ENDUSER","ADMIN")
                .antMatchers("client/**").hasAnyRole("CLIENT","ADMIN")
                .antMatchers("/**").permitAll().anyRequest().authenticated()
                .and().csrf().disable().formLogin()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/error/403");
    }

//This is exception from asking authorization for static resources
    @Override
    public void configure(WebSecurity web) {
        web
                .ignoring()
                .antMatchers("/resources/**","/uploads/**", "/templates/**", "/static/**", "/css/**", "/js/**", "/static/images/**");
    }
}