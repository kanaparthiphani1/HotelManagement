package com.hms.demo.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hms.demo.model.Hotel;
import com.hms.demo.model.Room;

public interface RoomRepository extends MongoRepository<Room, Long> {
	
	@Transactional
	void deleteByRoomNo(String number);

	//Collection<Room> findAllRooms();
	
	//@GetMapping("/hotels")
	//public List<Hotel> findAll();
}

