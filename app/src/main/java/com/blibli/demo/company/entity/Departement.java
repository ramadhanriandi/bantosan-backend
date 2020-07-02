package com.blibli.demo.company.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.blibli.demo.base.MongoBaseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = Departement.COLLECTION_NAME)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Departement extends MongoBaseEntity {

	public static final String COLLECTION_NAME = "department";

	public static final String FIELD_DEPT_NO = "deptNo";
	public static final String FIELD_DEPT_NAME = "deptName";
	public static final String FIELD_LOC = "loc";

	@Field(value = Departement.FIELD_DEPT_NO)
	private Integer deptNo;

	@Field(value = Departement.FIELD_DEPT_NAME)
	private Integer deptName;

	@Field(value = Departement.FIELD_LOC)
	private String loc;

}
