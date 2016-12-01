package com.teamup.entities;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.*;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity("participants")
@Repository
public class Participant implements Serializable {

  @Id
  private ObjectId _id;
  private String name;
  private String surname;
  private String email;
  private String password;
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
    this.email = email;
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
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

    if (!name.equals(that.name)) return false;
    if (!surname.equals(that.surname)) return false;
    if (!email.equals(that.email)) return false;
    return password.equals(that.password);

  }

  @Override
  public int hashCode() {
    int result = name.hashCode();
    result = 31 * result + surname.hashCode();
    result = 31 * result + email.hashCode();
    result = 31 * result + password.hashCode();
    return result;
  }
}
