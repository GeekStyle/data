package com.geekstyle.data.dao.ip;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface IPCountryDao {
	
	@Select({"select country_code countryCode from ip_country where #{ip} >= start and #{ip} <= end"})
	public String getCountryCode(Long ip);
	
}
