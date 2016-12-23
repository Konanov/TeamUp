package com.teamup.dto;

/**
 * Created by user01 on 12/14/16.
 */
public class ParticipantDTO {
  private String id;
  private String name;
  private String surname;
  private String email;
  private String password;

  public ParticipantDTO() {
  }

  public ParticipantDTO(String id, String name, String surname) {
    this.id = id;
    this.name = name;
    this.surname = surname;
  }

  public ParticipantDTO(String email, String password) {
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

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }
}
