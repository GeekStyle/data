package com.geekstyle.data.service.state.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geekstyle.data.dao.state.StateDao;
import com.geekstyle.data.model.state.State;
import com.geekstyle.data.service.state.StateService;

@Service
public class StateServiceImpl implements StateService{
	
	@Autowired
	StateDao stateDao;
	
	public List<State> queryByCountryCode(String countryCode) {
		return stateDao.queryByCountryCode(countryCode);
	}

	@Override
	public List<State> queryByCountryId(Integer countryId) {
		return stateDao.queryByCountryId(countryId);
	}
	
}
