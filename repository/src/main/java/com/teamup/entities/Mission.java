package com.teamup.entities;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.util.ArrayList;

@Entity("missions")
public class Mission {

  @Id
  private ObjectId _id;
  private String id;
  private ArrayList<Participant> participants = new ArrayList<>();
  private String manager_id;
  private String description;

  public Mission() {
  }

  public Mission(String manager_id, String description) {
    this.manager_id = manager_id;
    this.description = description;
  }

  public ObjectId get_id() {
    return _id;
  }

  public void set_id(ObjectId _id) {
    this._id = _id;
  }

  public ArrayList<Participant> getParticipants() {
    return participants;
  }

  public void setParticipants(ArrayList<Participant> participants) {
    this.participants = participants;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getManager_id() {
    return manager_id;
  }

  public void setManager_id(String manager_id) {
    this.manager_id = manager_id;
  }
}
