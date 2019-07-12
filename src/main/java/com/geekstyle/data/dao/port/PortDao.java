package com.geekstyle.data.dao.port;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.geekstyle.data.model.port.Port;

@Mapper
public interface PortDao {
	
	@Insert({
        "<script> insert into port (name,lon,lat,country,country_code,unlocode,create_time) values <foreach item='item' collection='list' separator=','> (#{item.name},#{item.lon},#{item.lat},#{item.country},#{item.countryCode},#{item.unlocode},#{item.createTime}) </foreach> </script>"
	})
	public void batchInsert(@Param("list") List<Port> portList);
	
	@Insert({
        "insert into port (name,lon,lat,country,country_code,unlocode,create_time) values (#{name},#{lon},#{lat},#{country},#{countryCode},#{unlocode},#{createTime} )"
	})
	public void insert(Port port);
	
	@Select({
        "select id,name,lon,lat,country,country_code countryCode,unlocode from port where country_code = #{countryCode} and unlocode is not null order by name"
	})
	public List<Port> queryPortByCountryCode(String countryCode);
	
}
