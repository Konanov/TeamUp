package com.teamup.service;

import com.mongodb.gridfs.GridFSDBFile;
import com.teamup.database.AbstractMongo;
import com.teamup.entities.Mission;
import com.teamup.entities.Participant;
import com.teamup.entities.Task;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

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
   * Getters & Setters
   */

  /**
   * Participants
   */

  public Participant save(Participant participant) {
    Participant newParticipant = this.db.save(participant);
    this.db.update("id", newParticipant.get_id().toHexString(), newParticipant.getEmail());
    return this.db.read(participant.getEmail());
  }

  public Participant create(String name, String surname, String email, String password) {
    return new Participant(name, surname, email, password);
  }

  public boolean update(String field, String value, String email) {
    return this.db.update(field, value, email) != null;
  }

  public Participant read(String email) {
    return db.read(email);
  }

  public Participant read(String email, String password) {
    return db.read(email, password);
  }

  public Participant readById(ObjectId id) {
    return db.readById(id);
  }

  public void delete(String participantName) {
    db.delete(participantName);
  }

  public List<Participant> getAllParticipants() {
    return db.getAllParticipants();
  }

  public void saveUserAvatar(File file, Participant participant) throws IOException {
    db.saveUserAvatar(file, participant);
  }

  public GridFSDBFile getUserAvatar(Participant participant) throws IOException {
    return db.getUserAvatar(participant);
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

  public void deleteTask(ObjectId taskId) {
    db.deleteTask(taskId);
  }

  /**
   * Mission
   */

  public void save(Mission mission) {
    this.db.save(mission);
  }

  public Mission read(Mission mission) {
    return this.db.read(mission);
  }

  public void dropDatabase(String name) {
    db.dropDatabase(name);
  }

  public void addParticipant(Participant participant, Mission mission) {
    mission.getParticipants().add(participant);
    db.save(mission);
  }

  public List<Participant> getCurrentParty(String mission_id) {
    return db.getCurrentParty(mission_id);
  }
}
