package com.teamup.service;

import com.teamup.database.MongoReal;
import com.teamup.entities.Participant;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user01 on 11/30/16.
 */

@Service
public class ServiceReal extends AbstractService implements UserDetailsService {

  public ServiceReal(MongoReal db) {
    super(db);
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Participant participant =  this.read(email);
    if (participant != null) {
      List<GrantedAuthority> authorityList = new ArrayList<>();
      authorityList.add(new SimpleGrantedAuthority("ROLE_PARTICIPANT"));

      return new User(participant.getEmail(), participant.getPassword(), authorityList);
    }
    throw new UsernameNotFoundException("No account registered by " + email + ".");
  }
}
