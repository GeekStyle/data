package com.geekstyle.data.dao.currency;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.geekstyle.data.model.currency.CountryCurrency;

@Mapper
public interface CountryCurrencyDao {
	
	@Select({"select country_name countryName,default_currency defaultCurrency from country_currency where country_code = #{countryCode}"})
	public CountryCurrency findByCountryCode(String countryCode);
	
}
