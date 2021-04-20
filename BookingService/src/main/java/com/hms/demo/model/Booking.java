package com.hms.demo.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
@Document(collection = "booking")
public class Booking {
	@Transient
    public static final String SEQUENCE_NAME = "users_sequence";
	
	@Id
	private long id;
	private Long hotelId;

	
	private Long roomId;

	
	private String userId;
	
	private String userName;

	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public Booking(Long hotelId, Long roomId, String userId, String userName, LocalDate checkin, LocalDate checkout,
			int numOfGuests, String approvalStatus, double finalPrice) {
		super();
		this.hotelId = hotelId;
		this.roomId = roomId;
		this.userId = userId;
		this.userName = userName;
		this.checkin = checkin;
		this.checkout = checkout;
		this.numOfGuests = numOfGuests;
		this.approvalStatus = approvalStatus;
		this.finalPrice = finalPrice;
	}


	@JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
	
	private LocalDate checkin;

	@JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
	
	private LocalDate checkout;

	
	private int numOfGuests;

	private String approvalStatus;
	
	
	
	public Booking(Long hotelId, Long roomId, String userId, LocalDate checkin, LocalDate checkout,
			int numOfGuests, String approvalStatus, double finalPrice) {
		this.hotelId = hotelId;
		this.roomId = roomId;
		this.userId = userId;
		this.checkin = checkin;
		this.checkout = checkout;
		this.numOfGuests = numOfGuests;
		this.approvalStatus = approvalStatus;
		this.finalPrice = finalPrice;
	}


	public Booking() {
	}


	public String getApprovalStatus() {
		return approvalStatus;
	}


	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}


	private double finalPrice;


	public long getId() {
		return id;
	}


	public void setBookingId(long id) {
		this.id = id;
	}


	public Long getHotelId() {
		return hotelId;
	}


	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}


	public Long getRoomId() {
		return roomId;
	}


	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	@Override
	public String toString() {
		return "Booking [id=" + id + ", hotelId=" + hotelId + ", roomId=" + roomId + ", userId=" + userId
				+ ", userName=" + userName + ", checkin=" + checkin + ", checkout=" + checkout + ", numOfGuests="
				+ numOfGuests + ", approvalStatus=" + approvalStatus + ", finalPrice=" + finalPrice + "]";
	}


	public LocalDate getCheckin() {
		return checkin;
	}


	public void setCheckin(LocalDate checkin) {
		this.checkin = checkin;
	}


	public LocalDate getCheckout() {
		return checkout;
	}


	public void setCheckout(LocalDate checkout) {
		this.checkout = checkout;
	}


	public int getNumOfGuests() {
		return numOfGuests;
	}


	public void setNumOfGuests(int numOfGuests) {
		this.numOfGuests = numOfGuests;
	}


	public double getFinalPrice() {
		return finalPrice;
	}


	public void setFinalPrice(double finalPrice) {
		this.finalPrice = finalPrice;
	}


	public Booking(Long hotelId, Long roomId, String userId, LocalDate checkin, LocalDate checkout,
			int numOfGuests, double finalPrice) {
		this.hotelId = hotelId;
		this.roomId = roomId;
		this.userId = userId;
		this.checkin = checkin;
		this.checkout = checkout;
		this.numOfGuests = numOfGuests;
		this.finalPrice = finalPrice;
	}
	

}
