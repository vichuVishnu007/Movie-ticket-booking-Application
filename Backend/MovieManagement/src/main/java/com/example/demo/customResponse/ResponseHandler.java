package com.example.demo.customResponse;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler 
{
	public static ResponseEntity<Object> generateResponse(String message, HttpStatus statusCode, Object responseObj)
	{
		Map<String, Object> mapObj = new HashMap<String, Object>();
		
		mapObj.put("Custom Message", message);
		mapObj.put("Status Code", statusCode.value());
		mapObj.put("Payload", responseObj);
		
		return new ResponseEntity<Object>(mapObj, statusCode);
	}

}
