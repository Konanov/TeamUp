package com.teamup.service;

import com.teamup.database.MongoReal;
import org.springframework.stereotype.Service;

/**
 * Created by user01 on 11/30/16.
 */

@Service
public class ServiceReal extends AbstractService {
  ServiceReal(MongoReal db) {
    super(db);
  }
}
