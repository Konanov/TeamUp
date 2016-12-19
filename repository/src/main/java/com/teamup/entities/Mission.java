package com.teamup.entities;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.util.concurrent.ArrayBlockingQueue;

@Entity("missions")
public class Mission {

  @Id
  private ObjectId _id;
  private ArrayBlockingQueue<Participant> participants = new ArrayBlockingQueue<>(5);
  private Participant manager;
  private String description;

  public Mission() {
  }

  public Mission(Participant manager, String description) {
    this.manager = manager;
    this.description = description;
  }

  public ObjectId get_id() {
    return _id;
  }

  public void set_id(ObjectId _id) {
    this._id = _id;
  }

  public ArrayBlockingQueue<Participant> getParticipants() {
    return participants;
  }

  public void setParticipants(ArrayBlockingQueue<Participant> participants) {
    this.participants = participants;
  }

  public Participant getManager() {
    return manager;
  }

  public void setManager(Participant manager) {
    this.manager = manager;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Mission mission = (Mission) o;

    if (!participants.equals(mission.participants)) return false;
    if (!manager.equals(mission.manager)) return false;
    return description.equals(mission.description);

  }

  @Override
  public int hashCode() {
    int result = participants.hashCode();
    result = 31 * result + manager.hashCode();
    result = 31 * result + description.hashCode();
    return result;
  }
}
