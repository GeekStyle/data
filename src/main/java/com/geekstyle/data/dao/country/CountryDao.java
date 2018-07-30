package com.geekstyle.data.dao.country;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.geekstyle.data.model.country.Country;

@Mapper
public interface CountryDao {
	
	@Insert({
        "insert into country (name,country_code,country_code_3d,create_time) values (#{name},#{countryCode},#{countryCode3d},#{createTime} )"
	})
	public void insert(Country country);
	
	@Select({
        "select id,name,country_code countryCode,country_code_3d countryCode3d,phone_code phoneCode from country order by name"
	})
	public List<Country> queryAll();
	
	@Update({
        "update country set name = #{name},country_code = #{countryCode},country_code_3d = #{countryCode3d},phone_code = #{phoneCode} where id = #{id}"
	})
	public void updateCountry(Country country);
	
}
