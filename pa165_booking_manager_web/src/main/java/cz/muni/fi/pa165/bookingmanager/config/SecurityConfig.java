package cz.muni.fi.pa165.bookingmanager.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.sql.DataSource;


/*
@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(getUsers())
                .authoritiesByUsernameQuery(getAuthorities());
    }

    private String getUsers() {
        return "SELECT username, password, true FROM BOOKING_USER WHERE username = ?";
    }

    private String getAuthorities() {
        return "SELECT username, CASE WHEN(admin = TRUE) THEN 'ROLE_ADMIN' ELSE 'ROLE_USER' END as authority FROM BOOKING_USER WHERE username = ?";
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/", "/home", "/cs", "/en", "/signup").permitAll()
            .antMatchers("/img/**", "/css/**", "/js/**", "/fonts/**").permitAll()
            .anyRequest().authenticated();

        http.authorizeRequests().anyRequest().authenticated()
            .and()
            .formLogin().loginPage("/login").permitAll()
            .and()
            .logout().logoutSuccessUrl("/").permitAll();
    }

    /*@Configuration
    public static class TT extends WebSecurityConfigurerAdapter {
        

    }

    @Configuration
    @Order(1)
    public static class RESTWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/rest/**")
                .authorizeRequests()
                .and()
                .httpBasic();
        }
    }
}*/
