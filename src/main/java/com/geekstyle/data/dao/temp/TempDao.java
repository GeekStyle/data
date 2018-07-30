package com.geekstyle.data.dao.temp;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.geekstyle.data.model.temp.TCity;
import com.geekstyle.data.model.temp.TCountry;
import com.geekstyle.data.model.temp.TState;

@Mapper
public interface TempDao {
	
	@Select({
        "select id,name,country_id countryId from states"
	})
	public List<TState> getAllTState();
	
	@Select({
        "select id,sortname,name,phonecode from countries"
	})
	public List<TCountry> getAllCountry();
	
	@Select({
        "select id,name,state_id stateId from cities"
	})
	public List<TCity> getAllCity();
	
}
