package com.teamup.service;

import com.teamup.database.MongoTest;
import org.springframework.stereotype.Service;

/**
 * Created by user01 on 11/30/16.
 */

@Service
public class ServiceTest extends AbstractService {
  public ServiceTest(MongoTest db) {
    super(db);
  }
}
