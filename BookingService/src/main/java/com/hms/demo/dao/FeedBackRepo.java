package com.hms.demo.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.hms.demo.model.Feedback;

public interface FeedBackRepo extends MongoRepository<Feedback, Integer> {

	List<Feedback> findByUserName(String username);
	
	List<Feedback> findByBookingId(long bookingId);
	
	
}
