package com.geekstyle.data.service.state;

import java.util.List;

import com.geekstyle.data.model.state.State;

public interface StateService {
	
	public List<State> queryByCountryCode(String countryCode);
	
	public List<State> queryByCountryId(Integer countryId);
	
}
