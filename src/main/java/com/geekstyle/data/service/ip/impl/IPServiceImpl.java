package com.geekstyle.data.service.ip.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geekstyle.data.dao.ip.IPCountryDao;
import com.geekstyle.data.service.ip.IPService;

@Service
public class IPServiceImpl implements IPService {

	@Autowired
	IPCountryDao ipCountryDao;
	
	@Override
	public String getCountryCode(Long ip) {
		return ipCountryDao.getCountryCode(ip);
	}

}
