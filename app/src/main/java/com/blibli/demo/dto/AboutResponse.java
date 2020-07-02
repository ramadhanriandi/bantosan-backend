package com.blibli.demo.dto;

import com.blibli.demo.company.entity.Author;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AboutResponse implements Serializable {

	private Integer aboutId;
	private String content;
	private Author author;
}
