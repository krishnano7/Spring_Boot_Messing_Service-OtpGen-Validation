package com.example.delegate;

import java.util.ArrayList;

import javax.xml.ws.Response;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.pojo.Account;
import com.example.pojo.Msg;
import com.example.pojo.SmsRequest;
import com.example.pojo.TextMessage;
import com.google.gson.Gson;

@Service
public class SmsDelegate {

	public String sendSms(String mobile, String text) {
		// Requesting smsGateWayhub Services
		StringBuilder url = new StringBuilder("https://www.smsgatewayhub.com/api/mt/SendSMS?");
		SmsRequest smsReq = prepareRequest(mobile, text);
		
		Gson gson = new Gson();
		String smsJson = gson.toJson(smsReq);
		System.out.println("request in json format: " + smsJson);
		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(smsJson, headers);

		ResponseEntity<String> result = rt.exchange(url.toString(), HttpMethod.POST, entity, String.class);

		String responsebody = result.getBody();
		System.out.println("Resaponse from smsgatewayhud: " + result.getBody());

		TextMessage textMessage = gson.fromJson(responsebody, TextMessage.class);
		System.out.println("Message Status:" + textMessage.getErrorMessage());

		//return Response.status(200).entity(textMessage.getErrorMessage()).build();
		
		return textMessage.getErrorMessage();
	}

	public SmsRequest prepareRequest(String mobile, String text) {

		SmsRequest request = new SmsRequest();

		Account acc = new Account();

		acc.setUser("krishnano7");
		acc.setPassword("90306670k@");
		acc.setSenderId("SMSTST");
		acc.setChannel("1");
		acc.setDCS("0");

		ArrayList<Msg> msglist = new ArrayList<Msg>();
		Msg msg = new Msg();
		msg.setNumber(mobile);
		msg.setText(text);

		msglist.add(msg);

		request.setAccount(acc);
		request.setMessages(msglist);

		return request;
	}

}
