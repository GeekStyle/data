package com.geekstyle.data.controller.tool;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/time")
public class TimeController {
	
	@RequestMapping(value="/seconds2time/{seconds}",method=RequestMethod.GET)
	public ResponseEntity<?> seconds2time(Long seconds) {
		return ResponseEntity.status(HttpStatus.OK).body(new Date(seconds * 1000));
	}
	
	@RequestMapping(value="/milliseconds2time/{milliseconds}",method=RequestMethod.GET)
	public ResponseEntity<?> milliseconds2time(Long milliseconds) {
		return ResponseEntity.status(HttpStatus.OK).body(new Date(1000));
	}
}
