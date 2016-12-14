package com.teamup.entities;

import com.teamup.security.User;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Transient;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity("participants")
@Repository
public class Participant extends User implements Serializable {

  @Id
  private ObjectId _id;
  private String name;
  private String surname;
  @Transient
  private Task currentTask;
  @Embedded
  private List<Task> tasks = new ArrayList<>();
  private byte[] avatar;
  private String coordinates;

  protected Participant() {
  }

  public Participant(String name, String surname, String email, String password) {
    this.name = name;
    this.surname = surname;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public Task getCurrentTask() {
    return currentTask;
  }

  public void setCurrentTask(Task currentTask) {
    this.currentTask = currentTask;
  }

  public List<Task> getTasks() {
    return tasks;
  }

  public void setTasks(List<Task> tasks) {
    this.tasks = tasks;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public byte[] getAvatar() {
    return avatar;
  }

  public void setAvatar(byte[] avatar) {
    this.avatar = avatar;
  }

  public String getCoordinates() {
    return coordinates;
  }

  public void setCoordinates(String coordinates) {
    this.coordinates = coordinates;
  }

  public ObjectId get_id() {
    return _id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Participant that = (Participant) o;

    if (_id != null ? !_id.equals(that._id) : that._id != null) return false;
    if (name != null ? !name.equals(that.name) : that.name != null) return false;
    return surname != null ? surname.equals(that.surname) : that.surname == null;

  }

  @Override
  public int hashCode() {
    int result = _id != null ? _id.hashCode() : 0;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (surname != null ? surname.hashCode() : 0);
    return result;
  }
}
