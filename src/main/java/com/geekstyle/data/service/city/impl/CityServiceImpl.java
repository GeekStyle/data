package com.geekstyle.data.service.city.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geekstyle.data.dao.city.CityDao;
import com.geekstyle.data.model.city.City;
import com.geekstyle.data.service.city.CityService;

@Service
public class CityServiceImpl implements CityService {
	
	@Autowired
	CityDao cityDao;

	@Override
	public List<City> queryByCountryCode(String countryCode) {
		return cityDao.queryByCountryCode(countryCode);
	}

	@Override
	public List<City> queryByCountryId(String countryId) {
		return cityDao.queryByCountryId(countryId);
	}

	@Override
	public List<City> queryByStateId(String stateId) {
		return cityDao.queryByStateId(stateId);
	}

}
