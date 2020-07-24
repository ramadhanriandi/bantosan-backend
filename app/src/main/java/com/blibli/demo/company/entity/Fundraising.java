package com.blibli.demo.company.entity;

import com.blibli.demo.base.MongoBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = Fundraising.COLLECTION_NAME)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Fundraising extends MongoBaseEntity {

  public static final String COLLECTION_NAME = "fundraisings";

  public static final String FIELD_TITLE = "title";
  public static final String FIELD_DESCRIPTION = "description";
  public static final String FIELD_DAY = "day";
  public static final String FIELD_TARGET = "target";
  public static final String FIELD_STATUS = "status";
  public static final String FIELD_START_DATE = "startDate";
  public static final String FIELD_END_DATE = "endDate";
  public static final String FIELD_ORGANIZER = "organizer";
  public static final String FIELD_BANKS = "banks";

  @Field(value = Fundraising.FIELD_TITLE)
  private String title;

  @Field(value = Fundraising.FIELD_DESCRIPTION)
  private String description;

  @Field(value = Fundraising.FIELD_DAY)
  private Integer day;

  @Field(value = Fundraising.FIELD_TARGET)
  private Double target;

  @Field(value = Fundraising.FIELD_STATUS)
  private String status;

  @Field(value = Fundraising.FIELD_START_DATE)
  private Date startDate;

  @Field(value = Fundraising.FIELD_END_DATE)
  private Date endDate;

  @Field(value = Fundraising.FIELD_ORGANIZER)
  @DBRef
  private User organizer;

  @Field(value = Fundraising.FIELD_BANKS)
  private Bank[] banks;
}
