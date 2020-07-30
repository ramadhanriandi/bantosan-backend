package com.blibli.demo.company.entity;

import com.blibli.demo.base.MongoBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = Donation.COLLECTION_NAME)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Donation extends MongoBaseEntity {

  public static final String COLLECTION_NAME = "donations";

  public static final String FIELD_NOMINAL = "nominal";
  public static final String FIELD_PROOF = "proof";
  public static final String FIELD_STATUS = "status";
  public static final String FIELD_DONATUR = "donatur";
  public static final String FIELD_BANK = "bank";
  public static final String FIELD_FUNDRAISING = "fundraising";

  @Field(value = Donation.FIELD_NOMINAL)
  private Double nominal;

  @Field(value = Donation.FIELD_PROOF)
  private String proof;

  @Field(value = Donation.FIELD_STATUS)
  private String status;

  @Field(value = Donation.FIELD_DONATUR)
  @DBRef
  private User donatur;

  @Field(value = Donation.FIELD_BANK)
  private Integer bank;

  @Field(value = Donation.FIELD_FUNDRAISING)
  @DBRef
  private Fundraising fundraising;
}
