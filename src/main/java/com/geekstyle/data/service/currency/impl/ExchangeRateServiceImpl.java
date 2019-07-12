package com.geekstyle.data.service.currency.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geekstyle.data.dao.currency.ExchangeRateDao;
import com.geekstyle.data.model.currency.ExchangeRate;
import com.geekstyle.data.service.currency.ExchangeRateService;
import com.geekstyle.data.service.httpclient.RestHttpClient;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService{

	@Autowired
	private ExchangeRateDao exchangeRateDao;
	
	@Autowired
	RestHttpClient restHttpClient;
	
	@Override
	public ExchangeRate getExchangeRate(ExchangeRate exchangeRate) {
		return exchangeRateDao.getExchangeRate(exchangeRate);
	}
	
	@Override
	public List<ExchangeRate> getAllExchangeRate() {
		return exchangeRateDao.getAllExchangeRate();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public HashMap refreshExchangeRate() {
		
		HashMap data = restHttpClient.simpleGetData(EXCHANGE_RATE_URL);
		HashMap<String,Object> rates = (HashMap<String,Object>)data.get("rates");
		List<ExchangeRate> exchangeRateList = new ArrayList<ExchangeRate>();
		for(String key : rates.keySet()) {
			ExchangeRate exchangeRate = new ExchangeRate();
			exchangeRate.setBase("USD");
			exchangeRate.setTarget(key);
			exchangeRate.setRate(BigDecimal.valueOf(Double.parseDouble(rates.get(key).toString())));
			exchangeRate.setUpdateTime(new Date());
			exchangeRateList.add(exchangeRate);
		}
		exchangeRateDao.truncateExchangeRate();
		exchangeRateDao.batchInsertExchangeRate(exchangeRateList);
		return data;
	}
	
}
