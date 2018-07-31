package com.geekstyle.data.model.port.temp;

import java.util.List;

public class PortListVO {
	
	private List<PortVO> data;
	private Integer totalCount;
	public List<PortVO> getData() {
		return data;
	}
	public void setData(List<PortVO> data) {
		this.data = data;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	
}
