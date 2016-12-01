package com.teamup.entities;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * Created by user01 on 11/30/16.
 */
@Entity("tasks")
public class Task {

  @Id
  private ObjectId _id;
  private String description;
  private String status;
  private ObjectId managerId;
  private ObjectId executorId;
  private boolean isCompleted = false;
  private String locationCoordinates;

  public Task(String description, ObjectId managerId, ObjectId executorId, String locationCoordinates) {
    this.managerId = managerId;
    this.executorId = executorId;
    this.description = description;
    this.locationCoordinates = locationCoordinates;
  }

  public Task() {
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public boolean isCompleted() {
    return isCompleted;
  }

  public void setCompleted(boolean completed) {
    isCompleted = completed;
  }

  public String reportStatus() {
    return null;
  }

  public boolean reportCompleted() {
    setCompleted(true);
    return isCompleted();
  }

  public void setLocation(String coordinates) {
    this.locationCoordinates = coordinates;
  }

  public String getLocationCoordinates() {
    return locationCoordinates;
  }

  public ObjectId getManagerId() {
    return managerId;
  }

  public void setManagerId(ObjectId managerId) {
    this.managerId = managerId;
  }

  public ObjectId getExecutorId() {
    return executorId;
  }

  public void setExecutorId(ObjectId executorId) {
    this.executorId = executorId;
  }

  public void setLocationCoordinates(String locationCoordinates) {
    this.locationCoordinates = locationCoordinates;
  }
}
