package com.geekstyle.data.controller.currency;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.geekstyle.data.model.currency.CountryCurrency;
import com.geekstyle.data.model.currency.ExchangeRate;
import com.geekstyle.data.service.currency.CountryCurrencyService;
import com.geekstyle.data.service.currency.ExchangeRateService;
import com.geekstyle.data.util.StringUtil;

@RestController
@RequestMapping("/exchangerate")
public class ExchangeRateController {
	
	@Autowired
	ExchangeRateService exchangeRateService;
	@Autowired
	CountryCurrencyService countryCurrencyService; 
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<?> getExchangeRate(@RequestBody ExchangeRate exchangeRate) {
		ExchangeRate rate = exchangeRateService.getExchangeRate(exchangeRate);
		if(rate == null) {
			return ResponseEntity.status(400).body("no such data");
		}
		return ResponseEntity.ok(rate.getRate());
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<?> getAllExchangeRate() {
		List<ExchangeRate> exchangeRateList = exchangeRateService.getAllExchangeRate();
		return ResponseEntity.ok(exchangeRateList);
	}
	
	@RequestMapping(value="/{countryCode}",method=RequestMethod.GET)
	public ResponseEntity<?> getDefaultCurrency(@PathVariable("countryCode") String countryCode) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		CountryCurrency countryCurrency = countryCurrencyService.getCountryCurrency(countryCode);
		if(countryCurrency == null) {
			return ResponseEntity.status(400).body("no such data");
		}
		resultMap.put("countryCode", countryCode);
		resultMap.put("countryName", countryCurrency.getCountryName());
		resultMap.put("defaultCurrency", countryCurrency.getDefaultCurrency());
		if(StringUtil.isNotEmpty(countryCurrency.getDefaultCurrency())) {
			ExchangeRate exchangeRate = new ExchangeRate();
			exchangeRate.setBase("USD");
			exchangeRate.setTarget(countryCurrency.getDefaultCurrency());
			resultMap.put("rate", exchangeRateService.getExchangeRate(exchangeRate));
		}
		return ResponseEntity.ok(resultMap);
	}
	
	@RequestMapping(value="/refresh",method=RequestMethod.GET)
	public ResponseEntity<?> refreshExchangeRate() {
		exchangeRateService.refreshExchangeRate();
		return ResponseEntity.ok("success");
	}
	
}
