package com.hms.demo.dao;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.hms.demo.model.Booking;
//@Repository
public interface BookingRepository extends MongoRepository<Booking, Integer> {

	List<Booking> findByUserId(String userId);
	
	Booking findById(long id);
}
