package com.teamup.database;

import org.springframework.stereotype.Component;

/**
 * Created by user01 on 11/30/16.
 */
@Component
public class MongoReal extends AbstractMongo {

  MongoReal() {
    super();
    this.ds = this.morphia.createDatastore(mongo, "TeamUp_database");
  }
}
