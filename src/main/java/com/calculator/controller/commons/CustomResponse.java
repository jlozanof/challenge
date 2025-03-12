package com.calculator.controller.commons;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(value = Include.NON_NULL)
public class CustomResponse {

	// Metadata
	private String message;
	private String httpCode;
	private String uri;
	private String dataTime;

	// Data
	private Object data;

}
