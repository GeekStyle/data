package com.geekstyle.data.service.port;

import java.util.List;

import com.geekstyle.data.model.port.Port;

public interface PortService {
	
	public Port getPortById(Integer id);
	
	public void batchInsert(List<Port> portList);
	
	public List<Port> queryPortByCountryCode(String countryCode);
	
}
