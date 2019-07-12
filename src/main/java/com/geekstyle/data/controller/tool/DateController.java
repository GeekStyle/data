package com.geekstyle.data.controller.tool;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/date")
public class DateController {
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<?> getDate() {
		String timeInMillis = new Date().getTime() + "\n";
		return ResponseEntity.status(HttpStatus.OK).body(timeInMillis);
	}
	
}
