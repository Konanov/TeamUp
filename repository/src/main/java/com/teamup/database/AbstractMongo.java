package com.teamup.database;

import com.mongodb.*;
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

import java.io.File;
import java.io.IOException;
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
    return this.read(participant.getEmail());
  }

  public Participant read(String email) {
    return ds.find(Participant.class).field("email").equal(email).get();
  }

  public Participant read(String email, String password) {
    return ds.find(Participant.class).field("email").equal(email).field("password").equal(password).get();
  }

  public WriteResult update(String field, String value, String email) {
    DBCollection collection = this.mongo.getDB("teamUp").getCollection("participants");
    BasicDBObject newDocument = new BasicDBObject();
    newDocument.append("$set", new BasicDBObject().append(field, value));
    BasicDBObject searchQuery = new BasicDBObject().append("email", email);
    return collection.update(searchQuery, newDocument);
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

  public void dropDatabase(String name) {
    mongo.dropDatabase(name);
  }

  public WriteResult deleteTask(ObjectId taskId) {
    Query<Task> query = ds.createQuery(Task.class);
    query.field("_id").equal(taskId);
    return ds.delete(query);
  }

  /**
   * Mission
   */

  public void save(Mission mission) {
    this.morphia.map(Mission.class);
    this.ds.save(mission);
  }

  public WriteResult updateMission(String field, String value, String mission_id) {
    DBCollection collection = this.mongo.getDB("teamUp").getCollection("missions");
    BasicDBObject newDocument = new BasicDBObject();
    newDocument.append("$set", new BasicDBObject().append(field, value));
    BasicDBObject searchQuery = new BasicDBObject().append("description", mission_id);
    return collection.update(searchQuery, newDocument);
  }

  public Mission read(Mission mission) {
    return ds.find(Mission.class).field("_id").equal(mission.get_id()).get();
  }

  public List<Participant> getCurrentParty(String mission_id) {
    return this.ds.find(Mission.class).field("_id").equal(new ObjectId(mission_id)).get().getParticipants();
  }

  public List<Mission> getUsersMissions(String manager_id) {
    List<Mission> missions = this.ds.find(Mission.class).filter("manager_id", manager_id).asList();
    return missions;
  }
}
