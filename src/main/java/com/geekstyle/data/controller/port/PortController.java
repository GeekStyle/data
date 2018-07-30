package com.geekstyle.data.controller.port;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.geekstyle.data.model.port.Port;
import com.geekstyle.data.model.port.temp.PortListVO;
import com.geekstyle.data.model.port.temp.PortVO;
import com.geekstyle.data.service.port.PortService;
import com.geekstyle.data.util.JSONUtil;

@RestController
@RequestMapping("/port")
public class PortController {
	
	@Autowired
	PortService portService;
	
	public static String inputFilePath = "C:/!data/port_by_country.json";
	
	@GetMapping("/initData")
	public ResponseEntity<?> insertSurvey() {
		Integer size = 0;
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(inputFilePath)));
			
			String lineString = null;
			while( (lineString = bufferedReader.readLine()) !=  null) {
				System.out.println(lineString);
				PortListVO portListVO = JSONUtil.toObject(lineString, PortListVO.class);
				size = portListVO.getData().size();
				
				List<Port> portList = new ArrayList<Port>();
				for(PortVO portVO : portListVO.getData()) {
					Port port = new Port();
					port.setCountry(portVO.getCOUNTRY());
					port.setCountryCode(portVO.getCOUNTRY_CODE());
					port.setLon(portVO.getCENTERX());
					port.setLat(portVO.getCENTERY());
					port.setName(portVO.getPORT_NAME());
					port.setUnlocode(portVO.getUNLOCODE());
					port.setCreateTime(new Date());
					portList.add(port);
				}
				portService.batchInsert(portList);
			}
			bufferedReader.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(size);
	}
	
	@GetMapping("/countryCode/{countryCode}")
	public ResponseEntity<?> queryPortByCountryCode(@PathVariable String countryCode) {
		List<Port> portList = portService.queryPortByCountryCode(countryCode);
		return ResponseEntity.ok(portList);
	}
	
}
