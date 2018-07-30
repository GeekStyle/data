package com.geekstyle.data.service.country.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geekstyle.data.dao.country.CountryDao;
import com.geekstyle.data.model.country.Country;
import com.geekstyle.data.service.country.CountryService;

@Service
public class CountryServiceImpl implements CountryService {

	@Autowired
	CountryDao countryDao;
	
	@Override
	public void insert(Country country) {
		countryDao.insert(country);
	}

	@Override
	public List<Country> queryAll() {
		return countryDao.queryAll();
	}

	@Override
	public void updateCountry(Country country) {
		countryDao.updateCountry(country);
	}
	
}
