package com.geekstyle.data.service.country;

import java.util.List;

import com.geekstyle.data.model.country.Country;

public interface CountryService {
	
	public void insert(Country country);
	
	public List<Country> queryAll();
	
	public void updateCountry(Country country);
	
}
