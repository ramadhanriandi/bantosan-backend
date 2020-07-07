package com.blibli.demo.company.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = Role.COLLECTION_NAME)
public class Role {
  public static final String COLLECTION_NAME = "roles";

  @Id
  private String id;

  private ERole name;

  public Role() {

  }

  public Role(ERole name) {
    this.name = name;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public ERole getName() {
    return name;
  }

  public void setName(ERole name) {
    this.name = name;
  }
}