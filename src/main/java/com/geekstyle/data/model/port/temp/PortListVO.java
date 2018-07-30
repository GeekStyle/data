package com.geekstyle.data.model.port.temp;

import java.util.List;

import lombok.Data;

@Data
public class PortListVO {
	
	private List<PortVO> data;
	private Integer totalCount;
	
}
