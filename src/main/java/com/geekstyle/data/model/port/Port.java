package com.geekstyle.data.model.port;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Port {
	
	private Integer id;
	private String name;
	private String lon;
	private String lat;
	private String country;
	private String countryCode;
	private String unlocode;
	private Date createTime;
	
}
