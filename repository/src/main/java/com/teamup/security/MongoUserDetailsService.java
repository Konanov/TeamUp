package com.teamup.security;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.teamup.database.AbstractMongo;
import com.teamup.database.MongoTest;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by user01 on 12/22/16.
 */

@Service
public class MongoUserDetailsService implements UserDetailsService {

  private AbstractMongo mongo;

  @Autowired
  public MongoUserDetailsService(MongoTest mongo) {
    this.mongo = mongo;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    MongoDatabase database = mongo.getDatabase("teamUp");
    MongoCollection<Document> collection = database.getCollection("users");

    Document document = collection.find(Filters.eq("email", email)).first();

    if (document != null) {
      String name = document.getString("name");
      String surname = document.getString("surname");
      String password = document.getString("password");
      List<GrantedAuthority> authorities = (List<GrantedAuthority>) document.get("authorities");
      MongoUserDetails mongoUserDetails = new MongoUserDetails(email, password, authorities);
      return mongoUserDetails;
    }
    return null;
  }
}
