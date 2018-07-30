package com.geekstyle.data.controller.city;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.geekstyle.data.dao.city.CityDao;
import com.geekstyle.data.dao.state.StateDao;
import com.geekstyle.data.dao.temp.TempDao;
import com.geekstyle.data.model.city.City;
import com.geekstyle.data.model.country.Country;
import com.geekstyle.data.model.state.State;
import com.geekstyle.data.model.temp.TCity;
import com.geekstyle.data.model.temp.TCountry;
import com.geekstyle.data.model.temp.TState;
import com.geekstyle.data.service.city.CityService;
import com.geekstyle.data.service.country.CountryService;
import com.geekstyle.data.service.state.StateService;

@RestController
@RequestMapping("/city")
public class CityController {
	
	@Autowired
	CountryService countryService;
	
	@Autowired
	TempDao tempDao;
	
	@Autowired
	StateDao stateDao;
	
	@Autowired
	CityDao cityDao;
	
	@Autowired
	StateService stateService;
	
	@Autowired
	CityService cityService;
	
	public static Map<Integer,TCountry> tCountryMap = new HashMap<Integer,TCountry>();
	public static Map<String,Country> countryMap = new HashMap<String,Country>();
	public static Map<Integer,TState> tStateMap = new HashMap<Integer,TState>();
	public static Map<String,State> stateMap = new HashMap<String,State>();
	public static Map<Integer,List<TCity>> tCityMap = new HashMap<Integer,List<TCity>>();
	
	@GetMapping("/initData")
	public ResponseEntity<?> insertData() {
		List<TCountry> countryList = tempDao.getAllCountry();
		for(TCountry tCountry : countryList) {
			tCountryMap.put(tCountry.getId(), tCountry);
		}
		List<Country> countries = countryService.queryAll();
		for(Country country : countries) {
			countryMap.put(country.getCountryCode(),country);
		}
		List<TState> tStateList = tempDao.getAllTState();
		for(TState tState : tStateList) {
			tStateMap.put(tState.getId(), tState);
		}
		List<State> stateList = stateDao.getAllState();
		for(State state : stateList) {
			stateMap.put(state.getName(),state);
		}
		
		List<TState> tTtateList = tempDao.getAllTState();
		List<TCity> tCityList = tempDao.getAllCity();
		for(TCity tCity : tCityList) {
			if(!tCityMap.containsKey(tCity.getStateId())) {
				List<TCity> tempList = new ArrayList<TCity>();
				tempList.add(tCity);
				tCityMap.put(tCity.getStateId(), tempList);
			}else {
				tCityMap.get(tCity.getStateId()).add(tCity);
			}
		}
		int count = 0;
		for(TState tState : tTtateList) {
			TCountry tCountry = tCountryMap.get(tState.getCountryId());
			Country country = countryMap.get(tCountry.getSortname());
			if(tCountry!= null && country != null) {
				State state = new State();
				state.setCountryId(country.getId());
				state.setName(tState.getName());
				state.setCountryCode(tCountry.getSortname());
				state.setCreateTime(new Date());
				stateDao.insert(state);
				List<TCity> tempTCityList = tCityMap.get(tState.getId());
				List<City> tempCityList = new ArrayList<City>();
				if(tempTCityList != null && tempTCityList.size() > 0) {
					for(TCity tCity : tempTCityList) {
						City city = new City();
						city.setStateId(state.getId());
						city.setCountryId(country.getId());
						city.setCountryCode(country.getCountryCode());
						city.setName(tCity.getName());
						city.setCreateTime(new Date());
						tempCityList.add(city);
					}
					cityDao.batchInsert(tempCityList);
					count ++;
					System.out.println("count: " + count);
				}
			}
		}
		return ResponseEntity.ok(null);
	}
	
	@GetMapping("/countryCode/{countryCode}")
	public ResponseEntity<?> queryByCountryCode(@PathVariable String countryCode) {
		List<City> cityList = cityService.queryByCountryCode(countryCode);
		return ResponseEntity.ok(cityList);
	}
	
	@GetMapping("/countryId/{countryId}")
	public ResponseEntity<?> queryByCountryId(String countryId) {
		List<City> cityList = cityService.queryByCountryId(countryId);
		return ResponseEntity.ok(cityList);
	}
	
	@GetMapping("/stateId/{stateId}")
	public ResponseEntity<?> queryByStateId(String stateId) {
		List<City> cityList = cityService.queryByStateId(stateId);
		return ResponseEntity.ok(cityList);
	}
	
}
