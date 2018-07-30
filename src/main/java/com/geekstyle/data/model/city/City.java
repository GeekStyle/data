package com.geekstyle.data.model.city;

import java.util.Date;

import lombok.Data;

@Data
public class City {
	
	private Integer id;
	private Integer stateId;
	private Integer countryId;
	private String countryCode;
	private String name;
	private Date createTime;
	
}
