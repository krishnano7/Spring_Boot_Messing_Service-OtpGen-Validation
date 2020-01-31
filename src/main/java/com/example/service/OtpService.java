package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.delegate.OtpDelegate;

@RestController
public class OtpService {

	@Autowired
	private OtpDelegate delegate;

	@RequestMapping(value = "/generateOtp/{mobile}", method = RequestMethod.POST)
	public String generateOtp(@PathVariable("mobile") String mobile) {

		String result = delegate.generateOtp(mobile);
		
		return result;
	}
	
	@RequestMapping(value = "/validateOtp/{mobile}/{otp}")
	public String validateOtp(@PathVariable("mobile") String mobile, @PathVariable("otp") String otp) {
		
		System.out.println("validateotp :: otpeservice");
		
		String returnSuccOrFail = delegate.validateOtp(mobile, otp);
		
		return returnSuccOrFail;
		
	}

}
