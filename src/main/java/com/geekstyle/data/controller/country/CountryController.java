package com.geekstyle.data.controller.country;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.geekstyle.data.model.country.Country;
import com.geekstyle.data.service.country.CountryService;

@RestController
@RequestMapping("/country")
public class CountryController {
	
	@Autowired
	CountryService countryService;
	
	public static String inputFilePath = "C:/!data/country/country.txt";
	
	@GetMapping("/initData")
	public ResponseEntity<?> insertSurvey() {
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(inputFilePath)));
			String lineString = null;
			while( (lineString = bufferedReader.readLine()) !=  null) {
				System.out.println(lineString);
				String[] strArray = lineString.split("	");
				if(strArray.length != 4) {
					System.out.println(strArray);
					throw new RuntimeException("this data sucks");
				}
				
				Country country = new Country();
				country.setName(strArray[0]);
				country.setCountryCode(strArray[1]);
				country.setCountryCode3d(strArray[2]);
				country.setCreateTime(new Date());
				countryService.insert(country);
			}
			bufferedReader.close();
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
		return null;
	}
	
	@GetMapping()
	public ResponseEntity<?> getAllCountry() {
		List<Country> countryList = countryService.queryAll();
		return ResponseEntity.ok(countryList);
	}
	
}
