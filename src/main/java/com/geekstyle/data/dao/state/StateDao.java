package com.geekstyle.data.dao.state;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.geekstyle.data.model.state.State;

@Mapper
public interface StateDao {
	
	@Insert({
        "insert into state (country_id,country_code,name,create_time) values (#{countryId},#{countryCode},#{name},#{createTime} )"
	})
	@Options(useGeneratedKeys=true)
	public void insert(State state);
	
	@Insert({
        "<script> insert into state (country_id,country_code,name,create_time) values <foreach item='item' collection='list' separator=','> (#{item.countryId},#{item.countryCode},#{item.name},#{item.createTime}) </foreach> </script>"
	})
	public void batchInsert(@Param("list") List<State> stateList);
	
	@Select({
        "select id,country_id,country_code,name,create_time createTime from state where country_code = #{countryCode}"
	})
	public List<State> queryByCountryCode(String countryCode);
	
	@Select({
        "select id,country_id,country_code,name,create_time createTime from state where country_id = #{countryId}"
	})
	public List<State> queryByCountryId(Integer countryId);
	
	@Select({
        "select id,name,country_id countryId from states"
	})
	public List<State> getAllState();
	
}
