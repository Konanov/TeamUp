package com.teamup.service;

import com.teamup.database.AbstractMongo;
import com.teamup.entities.Mission;
import com.teamup.entities.Participant;
import com.teamup.entities.Task;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

/**
 * Created by user01 on 11/30/16.
 */

@Service
public abstract class AbstractService {

  private final AbstractMongo db;

  AbstractService(AbstractMongo db) {
    this.db = db;
  }

  /**
   * Participant
   */

  public void save(Participant participant) {
    this.db.save(participant);
  }

  public Participant create(String name, String surname, String email, String password) {
    return new Participant(name, surname, email, password);
  }

  public Participant read(String name) {
    return db.read(name);
  }

  /**
   * Task
   */

  public void save(Task task) {
    this.db.save(task);
  }

  public void assignTask(String description, ObjectId managerId, ObjectId executorId, String locationCoordinates) {
    Task task = new Task(description, managerId, executorId, locationCoordinates);
    Participant executor = db.readById(executorId);
    executor.getTasks().add(task);
    db.save(task);
    db.addTask(executor, task);
  }

  /**
   * Mission
   */

  public void save(Mission mission) {
    this.db.save(mission);
  }



  public void delete(String name) {
    db.delete(name);
  }


  public void dropDatabase(String name) {
    db.dropDatabase(name);
  }
}
