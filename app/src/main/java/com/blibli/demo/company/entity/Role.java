package com.blibli.demo.company.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = Role.COLLECTION_NAME)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role {
  public static final String COLLECTION_NAME = "roles";

  public static final String FIELD_NAME = "name";

  @Id
  private String id;

  @Field(value = Role.FIELD_NAME)
  private ERole name;
}