package com.teamup.security;

import com.teamup.database.MongoReal;
import com.teamup.service.ServiceReal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by user01 on 12/13/16.
 */

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
class SecurityConfig extends WebSecurityConfigurerAdapter {

  private MongoReal mongo;

  @Autowired
  public SecurityConfig(MongoReal mongoReal) {
    this.mongo = mongoReal;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .formLogin()
      .and()
      .authorizeRequests()
      .antMatchers("/someurl").permitAll();
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(new ServiceReal(mongo));
  }
}
