/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.linhv.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import java.text.SimpleDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author prodi
 */
@Configuration
@EnableWebSecurity
@EnableTransactionManagement
@ComponentScan(basePackages = {
    "com.linhv.controllers",
    "com.linhv.repository",
    "com.linhv.service"
})
@Order(2)
@PropertySource("classpath:configs.properties")
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    private Environment env;
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/")
                .usernameParameter("email")
                .passwordParameter("password");
        http.formLogin()
                .defaultSuccessUrl("/admin/")
                .failureUrl("/?error");
        http.logout().logoutSuccessUrl("/?logout");
        http.exceptionHandling()
                .accessDeniedPage("/?accessDenied");

//        http.authorizeRequests()
////                .antMatchers("/api/caigido").hasRole("ADMIN")
//                .antMatchers("/api/**").permitAll()
//                .antMatchers("/admin/**").hasRole("ADMIN");
        http.csrf().disable();
    }
    
    @Bean
    public Cloudinary cloudinary() {
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", env.getProperty("cloudinary.cloud_name"),
                "api_key", env.getProperty("cloudinary.api_id"),
                "api_secret", env.getProperty("cloudinary.api_secret"),
                "secure", "true"
        ));
        return cloudinary;
    }
    
    @Bean
    public SimpleDateFormat simpleDateFormat() {
        return new SimpleDateFormat("yyyy-MM-dd");
    }
}
