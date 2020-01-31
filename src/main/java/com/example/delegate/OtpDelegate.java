package com.example.delegate;

import java.util.Date;
import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.OtpRepositor;
import com.example.pojo.OneTimePassword;
import com.example.service.SmsService;

@Service
public class OtpDelegate {

	@Autowired
	private SmsDelegate SenSms;

	@Autowired
	private OtpRepositor dao;

	public String generateOtp(String mobile) {

		System.out.println("otpService:: otp Delgate");

		Supplier<String> s = () -> {
			String otp = "";
			for (int i = 0; i < 6; i++) {

				otp = otp + (int) (Math.random() * 10);
			}

			return otp;

		};
		String otp = s.get();
		System.out.println(otp);

		String message = "your otp is:" + otp + " Please Don't share with any one";
		String hubReturns = SenSms.sendSms(mobile, message);

		OneTimePassword o = new OneTimePassword();
		o.setMobile(mobile);
		o.setOtp(otp);
		o.setTime(new Date());

		dao.save(o);

		return hubReturns;

	}

	public String validateOtp(String mobile, String otp) {
		
		System.out.println("validateotp :: otpedelegate");
		
		Optional<OneTimePassword> getOtpObject = dao.findById(mobile);
		OneTimePassword objectFromDb  = getOtpObject.orElseGet(() -> new OneTimePassword());
		
		String otpFromDb = objectFromDb.getOtp();
		if(otpFromDb != null && otp.equals(otpFromDb)) {
			return "true";
		}else {
			
			return "false";
		}
		
		
	}

}
