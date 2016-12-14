package com.teamup.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

/**
 * Created by user01 on 12/13/16.
 */

public class User {

  public static final String EMAIL = "email";
  public static final String PASSWORD = "password";
  public static final String ROLES = "roles";

  @JsonProperty(EMAIL)
  private String email;

  @JsonProperty(PASSWORD)
  private String password;

  @JsonProperty(ROLES)
  private List<GrantedAuthority> roles;


  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public List<GrantedAuthority> getRoles() {
    return roles;
  }
}
