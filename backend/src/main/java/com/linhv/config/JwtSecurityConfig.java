/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.linhv.config;

import com.linhv.filters.CustomAccessDeniedHandler;
import com.linhv.filters.JwtAuthenticationTokenFilter;
import com.linhv.filters.RestAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
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
    "com.linhv.service",
    "com.linhv.components"
})
@Order(1)
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() throws Exception {
        JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter = new JwtAuthenticationTokenFilter();
        jwtAuthenticationTokenFilter.setAuthenticationManager(authenticationManager());
        return jwtAuthenticationTokenFilter;
    }

    @Bean
    public RestAuthenticationEntryPoint restServicesEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    @Bean
    public CustomAccessDeniedHandler customAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/api/**");
        http.authorizeRequests().antMatchers("/api/info/").permitAll();
        http.authorizeRequests().antMatchers("/api/branches/").permitAll();
        http.authorizeRequests().antMatchers("/api/banners/").permitAll();
        http.authorizeRequests().antMatchers("/api/admissions/**").permitAll();
        http.authorizeRequests().antMatchers("/api/faculties/**").permitAll();
        http.authorizeRequests().antMatchers("/api/topics/").permitAll();
        http.authorizeRequests().antMatchers("/api/faqs/**").permitAll();
        http.authorizeRequests().antMatchers("/api/posts/**").permitAll();

        http.authorizeRequests().antMatchers("/api/login/").permitAll();
        http.authorizeRequests().antMatchers("/api/users/").permitAll();
        
        http.authorizeRequests().antMatchers("/api/questions/setting/").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/**/comments/").permitAll();
        http.antMatcher("/api/**").httpBasic().authenticationEntryPoint(restServicesEntryPoint()).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/current-user/").authenticated()
                .antMatchers(HttpMethod.PUT, "/api/current-user/").authenticated()
                .antMatchers(HttpMethod.GET, "/api/user-questions/**").authenticated()
                .antMatchers(HttpMethod.GET, "/api/user-questions/unanswer/").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_ADVISOR')")
                .antMatchers(HttpMethod.GET, "/api/user-questions/advisor/").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_ADVISOR')")
                .antMatchers(HttpMethod.GET, "/api/user-questions/by-email").authenticated()
                .antMatchers(HttpMethod.POST, "/api/user-questions/add/").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/user-questions/update/").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_ADVISOR')")
                .and()
                .addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());
    }
}
