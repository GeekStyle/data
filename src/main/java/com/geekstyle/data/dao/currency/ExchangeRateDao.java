package com.geekstyle.data.dao.currency;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.geekstyle.data.model.currency.ExchangeRate;

@Mapper
public interface ExchangeRateDao {
	
	@Select({"select id,base,target,rate from exchange_rate where base=#{base} and target = #{target}"})
	public ExchangeRate getExchangeRate(ExchangeRate exchangeRate);
	
	@Select({"select id,base,target,rate,update_time updateTime from exchange_rate"})
	public List<ExchangeRate> getAllExchangeRate();
	
	@Update("truncate table exchange_rate")
	public void truncateExchangeRate();
	
	@Insert({"insert into exchange_rate (base,target,rate,update_time) values (#{base},#{target},#{updateTime})"})
	public void insertExchangeRate(ExchangeRate exchangeRate);
	
	@Insert({
        "<script>",
        "insert into exchange_rate (base,target,rate,update_time)",
        "values ",
        "<foreach  collection='list' item='item' separator=','>",
        	"(#{item.base},#{item.target},#{item.rate},#{item.updateTime})",
        "</foreach>",
        "</script>"
	})
	public void batchInsertExchangeRate(@Param("list") List<ExchangeRate> exchangeRateList);
	
}
