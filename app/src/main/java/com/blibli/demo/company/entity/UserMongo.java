package com.blibli.demo.company.entity;

import com.blibli.demo.base.MongoBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = UserMongo.COLLECTION_NAME)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserMongo extends MongoBaseEntity {

  public static final String COLLECTION_NAME = "users";

  public static final String FIELD_USERNAME = "username";
  public static final String FIELD_EMAIL = "email";
  public static final String FIELD_PASSWORD = "password";
  public static final String FIELD_FULLNAME = "fullname";
  public static final String FIELD_PHONE = "phone";
  public static final String FIELD_STATUS = "status";
  public static final String FIELD_ROLE = "role";
  public static final String FIELD_AVATAR = "avatar";
  public static final String FIELD_IDENTITY = "identity";

  @Field(value = UserMongo.FIELD_USERNAME)
  private String username;

  @Field(value = UserMongo.FIELD_EMAIL)
  private String email;

  @Field(value = UserMongo.FIELD_PASSWORD)
  private String password;

  @Field(value = UserMongo.FIELD_FULLNAME)
  private String fullname;

  @Field(value = UserMongo.FIELD_PHONE)
  private String phone;

  @Field(value = UserMongo.FIELD_STATUS)
  private String status;

  @Field(value = UserMongo.FIELD_ROLE)
  private String role;

  @Field(value = UserMongo.FIELD_AVATAR)
  private String avatar;

  @Field(value = UserMongo.FIELD_IDENTITY)
  private String identity;
}
