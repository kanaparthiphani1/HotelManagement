package com.hms.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonBackReference;
@Document(collection="room")
public class Room {
	@Transient
    public static final String SEQUENCE_NAME = "users_sequence1";
	
	@Id
	
	private long id;

	private String roomNo;

	private String roomType;

	private int price;

	private boolean availability;
	

	private Hotel hotel;

	/**
	 * 
	 */
	public Room() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param roomNo
	 * @param roomType
	 * @param price
	 * @param availability
	 * @param hotel
	 */
	public Room( String roomNo, String roomType, int price, boolean availability) {
		
		this.roomNo = roomNo;
		this.roomType = roomType;
		this.price = price;
		this.availability = availability;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public boolean isAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}
	//@DBRef
	@JsonBackReference
	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	
	
}
