package com.geekstyle.data.model.country;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Country {
	
	private Integer id;
	private String name;
	private String countryCode;
	private String countryCode3d;
	private String phoneCode;
	private Date createTime;
	
}
