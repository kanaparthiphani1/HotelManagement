package com.hms.demo.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

import com.hms.demo.model.Hotel;

public interface HotelRepository extends MongoRepository<Hotel, Long>{
	
	@Transactional
	void deleteByHotelName(String name);
}

