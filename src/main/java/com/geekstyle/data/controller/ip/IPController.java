package com.geekstyle.data.controller.ip;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.geekstyle.data.service.ip.IPService;
import com.geekstyle.data.util.IPUtil;

@RestController
@RequestMapping("/ip")
public class IPController {
	
	@Autowired
	IPService ipService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<?> getIP(HttpServletRequest request) {
		String ip = IPUtil.getClientIP(request);
		String countryCode = ipService.getCountryCode(IPUtil.ipToLong(ip));
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("ip", ip);
		result.put("country", countryCode);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	@GetMapping("/{ip}")
	public ResponseEntity<?> getCountryCode(@PathVariable String ip) {
		Long decimalIP = IPUtil.ipToLong(ip);
		String countryCode = ipService.getCountryCode(decimalIP);
		return ResponseEntity.status(HttpStatus.OK).body(countryCode);
	}
	
}
