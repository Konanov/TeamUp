/*package com.teamup.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;



public class MongoUserDetails implements UserDetails {
  private String userName;
  private String password;
  private List<GrantedAuthority> grantedAuthorities;

  public MongoUserDetails(String userName, String password, List<GrantedAuthority> grantedAuthorities) {
    this.userName = userName;
    this.password = password;
    this.grantedAuthorities = grantedAuthorities;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public String getPassword() {
    return null;
  }

  @Override
  public String getUsername() {
    return null;
  }

  @Override
  public boolean isAccountNonExpired() {
    return false;
  }

  @Override
  public boolean isAccountNonLocked() {
    return false;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return false;
  }

  @Override
  public boolean isEnabled() {
    return false;
  }
}*/
