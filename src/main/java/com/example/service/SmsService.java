package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.delegate.SmsDelegate;



@RestController 
public class SmsService {
	
	
	@Autowired(required = true)
	private SmsDelegate delegate;
	
	@RequestMapping(value = "/sendSms/{mobile}/{text}" , method = RequestMethod.GET)
	public String sendSms(@PathVariable("mobile") String mobile, @PathVariable("text") String text) {
		
		System.out.println("entered into SmsService");
		
		String result = delegate.sendSms(mobile, text);
		//String result = "abc";
		return result;
		
	}

}
