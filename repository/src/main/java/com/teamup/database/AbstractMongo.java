package com.teamup.database;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.WriteResult;
import com.mongodb.client.MongoDatabase;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import com.teamup.entities.Mission;
import com.teamup.entities.Participant;
import com.teamup.entities.Task;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

import java.io.*;
import java.util.List;

/**
 * Created by user01 on 11/30/16.
 */
public abstract class AbstractMongo {

  Morphia morphia = new Morphia();
  Datastore ds;
  MongoClient mongo;

  public Datastore getDs() {
    return ds;
  }

  AbstractMongo() {
    ServerAddress serverAddress = new ServerAddress("localhost", 27017);
    this.mongo = new MongoClient(serverAddress);
    this.morphia.map(Participant.class);
  }

  public MongoDatabase getDatabase(String name) {
    return this.mongo.getDatabase(name);
  }

  /**
   * Participants
   */

  public Participant save(Participant participant) {
    this.ds.save(participant);
    return this.read(participant.getName());
  }

  public Participant read(String email) {
    return ds.find(Participant.class).field("email").equal(email).get();
  }

  public Participant read(String email, String password) {
    return ds.find(Participant.class).field("email").equal(email).field("password").equal(password).get();
  }

  public Participant readById(ObjectId id) {
    return ds.find(Participant.class).field("_id").equal(id).get();
  }

  public void addTask(Participant participant, Task task) {
    ds.update(participant, ds.createUpdateOperations(Participant.class).addToSet("tasks", task));
  }

  public WriteResult delete(String participantName) {
    Query<Participant> query = ds.createQuery(Participant.class);
    query.field("name").equal(participantName);
    return ds.delete(query);
  }

  public List<Participant> getAllParticipants() {
    return ds.find(Participant.class).asList();
  }

  public void saveUserAvatar(File file, Participant participant) throws IOException {
    GridFS gridFS = new GridFS(this.mongo.getDB("teamUp"));
    GridFSInputFile photo = gridFS.createFile(file);
    photo.setFilename(participant.get_id().toString() + "_user_avatar");
    photo.save();
  }

  public GridFSDBFile getUserAvatar(Participant participant) throws IOException {
    GridFS gridFS = new GridFS(this.mongo.getDB("teamUp"));
    return gridFS.findOne(participant.get_id() + "_user_avatar");
  }

  /**
   * Task
   */

  public void save(Task task) {
    this.morphia.map(Task.class);
    this.ds.save(task);
  }

  public void save(Mission mission) {
    this.morphia.map(Mission.class);
    this.ds.save(mission);
  }

  public void dropDatabase(String name) {
    mongo.dropDatabase(name);
  }

  public WriteResult deleteTask(ObjectId taskId) {
    Query<Task> query = ds.createQuery(Task.class);
    query.field("_id").equal(taskId);
    return ds.delete(query);
  }
}
