package com.geekstyle.data.service.port.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geekstyle.data.dao.port.PortDao;
import com.geekstyle.data.model.port.Port;
import com.geekstyle.data.service.port.PortService;

@Service
public class PortServiceImpl implements PortService {
	
	@Autowired
	PortDao portDao;
	
	@Override
	public Port getPortById(Integer id) {
		return null;
	}

	@Override
	public void batchInsert(List<Port> portList) {
		portDao.batchInsert(portList);
		//portDao.insert(portList.get(0));
	}

	@Override
	public List<Port> queryPortByCountryCode(String countryCode) {
		return portDao.queryPortByCountryCode(countryCode);
	}
	

}
