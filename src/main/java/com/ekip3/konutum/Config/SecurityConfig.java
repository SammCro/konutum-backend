package com.ekip3.konutum.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
public class SecurityConfig extends org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter{

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/api/**","/","/login","/register","/register/**","/css/**","/js/**","/img/**","/fonts/**","/vendor/**").permitAll()
                .anyRequest().permitAll()
                .and()
                .csrf().disable();
    }
}
