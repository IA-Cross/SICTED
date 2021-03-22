package com.integrador.sicdet.config;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
public class Utils{

	//true
	public static <T> ResponseEntity<ResponseBody<T>> response(HttpStatus status, String msg, T data) { 

		ResponseBody<T> body = new ResponseBody<>(data,msg);
		return new ResponseEntity<>(body, status);
	}
	//false
	public static <T> ResponseEntity<ResponseBody<T>> response(HttpStatus status, boolean success, String msg, T data) { 

		ResponseBody<T> body = new ResponseBody<T>(success, data, msg);
		return new ResponseEntity<>(body, status);
	}

}
