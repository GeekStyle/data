package com.geekstyle.data.dao.city;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.geekstyle.data.model.city.City;

@Mapper
public interface CityDao {
	
	@Insert({
        "<script> insert into city (state_id,country_id,country_code,name,create_time) values <foreach item='item' collection='list' separator=','> (#{item.stateId},#{item.countryId},#{item.countryCode},#{item.name},#{item.createTime}) </foreach> </script>"
	})
	public void batchInsert(@Param("list") List<City> cityList);
	
	@Select({
        "select id,state_id stateId,country_id countryId,country_code countryCode,name,create_time createTime from city where country_code = #{countryCode}"
	})
	public List<City> queryByCountryCode(String countryCode);
	
	@Select({
        "select id,state_id stateId,country_id countryId,country_code countryCode,name,create_time createTime from city where country_id = #{countryId}"
	})
	public List<City> queryByCountryId(String countryId);
	
	@Select({
        "select id,state_id stateId,country_id countryId,country_code countryCode,name,create_time createTime from city where state_id = #{stateId}"
	})
	public List<City> queryByStateId(String stateId);
	
}
