package com.integrador.sicdet.config;
public class ResponseBody<T> {
	private boolean success;
	private  T data;
	private String message;
	public ResponseBody() {
		this.success = true;
	}
	public ResponseBody(T data) {
		this.success = true;
		this.data = data;
	}
	public ResponseBody(T data, String message) {
		this.success = true;
		this.data = data;
		this.message = message;
 	}
//false
	public ResponseBody(boolean success) {
		this.success = success;
	}
	public ResponseBody(boolean success, T data) {
		this.success = success;
		this.data = data;
	}
	public ResponseBody(boolean success, T data, String message) {
		this.success = success;
		this.data = data;
		this.message = message;
	}
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
