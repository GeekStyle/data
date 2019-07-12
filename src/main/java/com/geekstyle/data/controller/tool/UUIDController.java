package com.geekstyle.data.controller.tool;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.geekstyle.data.util.UUIDUtil;

@RestController
@RequestMapping("/uuid")
public class UUIDController {
	
	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody String getUUID() {
		return UUIDUtil.getUUID();
	}
	
}
