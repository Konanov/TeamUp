package com.teamup.service;

import com.mongodb.gridfs.GridFSDBFile;
import com.teamup.database.AbstractMongo;
import com.teamup.dto.ParticipantDTO;
import com.teamup.entities.Mission;
import com.teamup.entities.Participant;
import com.teamup.entities.Task;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

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
   * MongoUserDetails
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

  public Participant readById(ObjectId id) {
    return db.readById(id);
  }

  public void delete(String participantName) {
    db.delete(participantName);
  }

  public ParticipantDTO convertParticipant(Participant participant) {
    return new ParticipantDTO(participant.get_id().toHexString(), participant.getName(), participant.getSurname());
  }

  public List<ParticipantDTO> getAllParticipants() {
    return db.getAllParticipants().stream().map(this::convertParticipant).collect(Collectors.toList());
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


  public void dropDatabase(String name) {
    db.dropDatabase(name);
  }
}
