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
public class Location {
	public static final String FIELD_NAME = "name";
	public static final String FIELD_MAP = "map";

	@Field(value = Location.FIELD_NAME)
	private String name;

	@Field(value = Location.FIELD_MAP)
	private Map map;
}
