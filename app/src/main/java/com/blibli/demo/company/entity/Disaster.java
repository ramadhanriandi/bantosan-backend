package com.blibli.demo.company.entity;

import com.blibli.demo.base.MongoBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = Disaster.COLLECTION_NAME)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Disaster extends MongoBaseEntity {

  public static final String COLLECTION_NAME = "disasters";

  public static final String FIELD_NAME = "name";
  public static final String FIELD_CATEGORY = "category";
  public static final String FIELD_STATUS = "status";
  public static final String FIELD_DESCRIPTION = "description";
  public static final String FIELD_LOCATION = "location";
  public static final String FIELD_EVIDENCE = "evidence";
  public static final String FIELD_DISPLAY = "display";
  public static final String FIELD_REPORTER = "reporter";

  @Field(value = Disaster.FIELD_NAME)
  private String name;

  @Field(value = Disaster.FIELD_CATEGORY)
  private String category;

  @Field(value = Disaster.FIELD_STATUS)
  private String status;

  @Field(value = Disaster.FIELD_DESCRIPTION)
  private String description;

  @Field(value = Disaster.FIELD_LOCATION)
  private Location location;

  @Field(value = Disaster.FIELD_EVIDENCE)
  private String evidence;

  @Field(value = Disaster.FIELD_DISPLAY)
  private String display;

  @Field(value = Disaster.FIELD_REPORTER)
  @DBRef
  private User reporter;
}
