package com.practice.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecuirtyConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private CustomSuccessHandler customSuccessHandler;

  @Autowired
  public void globalSecurityConfig(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication().withUser("bill").password("abc123").roles("USER");
    auth.inMemoryAuthentication().withUser("admin").password("root123").roles("ADMIN");
    auth.inMemoryAuthentication().withUser("dba").password("root123").roles("ADMIN", "DBA");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests().antMatchers("/", "/home").access("hasRole('USER')").antMatchers("/admin/**")
    .access("hasRole('ADMIN')").antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')").and()
    .formLogin().loginPage("/login").successHandler(customSuccessHandler).usernameParameter("ssoId")
        .passwordParameter("password").and().csrf().and().exceptionHandling().accessDeniedPage("/accessDenied");
  }

}
