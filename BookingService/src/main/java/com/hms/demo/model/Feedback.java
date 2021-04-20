package com.hms.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "feedback")
public class Feedback {

	@Transient
    public static final String SEQUENCE_NAME = "feeds_sequence";
	
	@Id
	private long id;
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	private long bookingId;
	
	private String userName;
	
	private String feedDate;
	

	public String getFeedDate() {
		return feedDate;
	}

	public void setFeedDate(String feedDate) {
		this.feedDate = feedDate;
	}

	public Feedback(long bookingId, String userName, String feedDate, int rating, String comments) {
		super();
		this.bookingId = bookingId;
		this.userName = userName;
		this.feedDate = feedDate;
		this.rating = rating;
		this.comments = comments;
	}

	private int rating;
	
	private String comments;

	public Feedback(long bookingId, String userName, int rating, String comments) {
		
		this.bookingId = bookingId;
		this.userName = userName;
		this.rating = rating;
		this.comments = comments;
	}

	public long getBookingId() {
		return bookingId;
	}

	public void setBookingId(long bookingId) {
		this.bookingId = bookingId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Feedback() {
		super();
	}
	
	
	
	
	
	
}
