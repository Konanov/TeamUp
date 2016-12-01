package com.teamup.database;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.WriteResult;
import com.teamup.entities.Mission;
import com.teamup.entities.Participant;
import com.teamup.entities.Task;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

/**
 * Created by user01 on 11/30/16.
 */
public abstract class AbstractMongo {

  Morphia morphia = new Morphia();
  Datastore ds;
  MongoClient mongo;

  AbstractMongo() {
    ServerAddress serverAddress = new ServerAddress("localhost", 27017);
    this.mongo = new MongoClient(serverAddress);
    this.morphia.map(Participant.class);
  }

  public void save(Participant participant) {
    this.ds.save(participant);
  }

  public Participant read(String name) {
    return ds.find(Participant.class).field("name").equal(name).get();
  }

  public Participant readById(ObjectId id) {
    return ds.find(Participant.class).field("id").equal(id).get();
  }

  public void addTask(Participant participant, Task task) {
    ds.update(participant, ds.createUpdateOperations(Participant.class).addToSet("tasks", task));
  }

  public WriteResult delete(String name) {
    Query<Participant> query = ds.createQuery(Participant.class);
    query.field("name").equal(name);
    return ds.delete(query);
  }

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
}
