package com.hms.demo.model;

public class MailResponse {
	private String message;
	private boolean status;
	private String otp;
	
	
	public MailResponse(String message, boolean status, String otp) {
		this.message = message;
		this.status = status;
		this.otp = otp;
	}
	
	public MailResponse(String message, boolean status) {
		this.message = message;
		this.status = status;
	}
	public MailResponse() {
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}
	
	
	

}