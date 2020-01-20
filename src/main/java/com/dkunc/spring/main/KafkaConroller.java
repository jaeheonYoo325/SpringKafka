package com.dkunc.spring.main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dkunc.spring.common.kafka.KafkaSender;

@Controller
public class KafkaConroller {

	private Logger logger = LoggerFactory.getLogger(KafkaConroller.class);
	
	@RequestMapping("/main/kafka")
	public String viewMainPage() {
		logger.debug( "#ex1 - debug 한글 log" );
		logger.info( "#ex1 - info 한글 log" );
		logger.warn( "#ex1 - warn 한글 log" );
		logger.error( "#ex1 - error 한글 log" );
		
		String filePath = "E:/kafkaLog/kafka.log";
		
		FileInputStream fileStream = null;
		String message = null;
		
		try {
			fileStream = new FileInputStream(filePath);
			try {
				byte[] readBuffer = new byte[fileStream.available()];
				while(fileStream.read(readBuffer) != -1) {}
				message = new String(readBuffer);
				fileStream.close();
				
				KafkaSender kafkaSender = new KafkaSender();
				kafkaSender.send(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return "main/kafka";
	}
}
