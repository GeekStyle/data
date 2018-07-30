package com.geekstyle.data.controller.state;

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

import com.geekstyle.data.dao.state.StateDao;
import com.geekstyle.data.dao.temp.TempDao;
import com.geekstyle.data.model.country.Country;
import com.geekstyle.data.model.state.State;
import com.geekstyle.data.model.temp.TCountry;
import com.geekstyle.data.model.temp.TState;
import com.geekstyle.data.service.country.CountryService;
import com.geekstyle.data.service.state.StateService;

@RestController
@RequestMapping("/state")
public class StateController {
		
	@Autowired
	CountryService countryService;
	
	@Autowired
	TempDao tempDao;
	
	@Autowired
	StateDao stateDao;
	
	@Autowired
	StateService stateService;
	
	public static Map<Integer,TCountry> tCountryMap = new HashMap<Integer,TCountry>();
	public static Map<String,Country> countryMap = new HashMap<String,Country>();
	
	@GetMapping("/initData")
	public ResponseEntity<?> insertSurvey() {
		List<TCountry> countryList = tempDao.getAllCountry();
		for(TCountry tCountry : countryList) {
			tCountryMap.put(tCountry.getId(), tCountry);
		}
		List<Country> countries = countryService.queryAll();
		for(Country country : countries) {
			countryMap.put(country.getCountryCode(),country);
		}
		//update country_phone_code
//		for(Country country : countries) {
//			for(TCountry tCountry : countryList) {
//				if(country.getCountryCode().equalsIgnoreCase(tCountry.getSortname())) {
//					System.out.println(tCountry.getSortname());
//					country.setPhoneCode(tCountry.getPhonecode());
//					countryService.updateCountry(country);
//				}
//			}
//		}
		
		List<TState> tTtateList = tempDao.getAllTState();
		List<State> stateList = new ArrayList<State>();
		for(TState tState : tTtateList) {
			TCountry tCountry = tCountryMap.get(tState.getCountryId());
			Country country = countryMap.get(tCountry.getSortname());
			if(tCountry!= null && country != null) {
				State state = new State();
				state.setCountryId(country.getId());
				state.setName(tState.getName());
				state.setCountryCode(tCountry.getSortname());
				state.setCreateTime(new Date());
				stateList.add(state);
			}
		}
		stateDao.batchInsert(stateList);
		return ResponseEntity.ok(null);
	}
	
	@GetMapping("/countryCode/{countryCode}")
	public ResponseEntity<?> queryByCountryCode(@PathVariable String countryCode) {
		return ResponseEntity.ok(stateService.queryByCountryCode(countryCode));
	}
	
	@GetMapping("/countryId/{countryId}")
	public ResponseEntity<?> queryByCountryId(@PathVariable Integer countryId) {
		return ResponseEntity.ok(stateService.queryByCountryId(countryId));
	}
	
	
}
