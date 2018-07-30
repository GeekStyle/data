package com.geekstyle.data.service.city;

import java.util.List;

import com.geekstyle.data.model.city.City;

public interface CityService {
	
	public List<City> queryByCountryCode(String countryCode);
	
	public List<City> queryByCountryId(String countryId);
	
	public List<City> queryByStateId(String stateId);
	
}
