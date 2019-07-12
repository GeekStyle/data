package com.geekstyle.data.controller.ip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.geekstyle.data.service.ip.IPService;
import com.geekstyle.data.util.IPUtil;

@RestController
@RequestMapping("/ip")
public class IPController {
	
	@Autowired
	IPService ipService;
	
	@GetMapping("/{ip}")
	public ResponseEntity<?> getCountryCode(@PathVariable String ip) {
		Long decimalIP = IPUtil.ipToLong(ip);
		String countryCode = ipService.getCountryCode(decimalIP);
		return ResponseEntity.status(HttpStatus.OK).body(countryCode);
	}
	
}
