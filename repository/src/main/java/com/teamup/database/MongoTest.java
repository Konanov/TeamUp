package com.teamup.database;

import org.springframework.stereotype.Component;

/**
 * Created by user01 on 11/29/16.
 */

@Component
public class MongoTest extends AbstractMongo {

  public MongoTest() {
    super();
    this.ds = this.morphia.createDatastore(mongo, "teamUp");
  }
}
