package com.blibli.demo.company.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Map {
	public static final String FIELD_TYPE = "type";
	public static final String FIELD_COORDINATES = "coordinates";

	@Field(value = Map.FIELD_TYPE)
	private String type;

	@Field(value = Map.FIELD_COORDINATES)
	private Double[] coordinates;
}
