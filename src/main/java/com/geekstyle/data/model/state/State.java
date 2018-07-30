package com.geekstyle.data.model.state;

import java.util.Date;

import lombok.Data;

@Data
public class State {
	
	private Integer id;
	private Integer countryId;
	private String countryCode;
	private String name;
	private Date createTime;
	
}
