package com.geekstyle.data.service.currency.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geekstyle.data.dao.currency.CountryCurrencyDao;
import com.geekstyle.data.model.currency.CountryCurrency;
import com.geekstyle.data.service.currency.CountryCurrencyService;

@Service
public class CountryCurrencyServiceImpl implements CountryCurrencyService{
	
	@Autowired
	CountryCurrencyDao countryCurrencyDao; 
	
	@Override
	public CountryCurrency getCountryCurrency(String countryCode) {
		return countryCurrencyDao.findByCountryCode(countryCode);
	}
	
}
