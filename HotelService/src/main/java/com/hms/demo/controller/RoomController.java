package com.hms.demo.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.demo.dao.RoomRepository;
import com.hms.demo.model.Hotel;
import com.hms.demo.model.Room;
import com.hms.demo.service.SequenceGeneratorService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v3")
public class RoomController {
	
	@Autowired
	private RoomRepository service;
	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;

	
	@GetMapping("/room")
	public List<Room> findAll(){
		
		return service.findAll();
	}
	
	@PostMapping("/room")
	public void createRoom(@RequestBody Room room) {
		room.setId(sequenceGeneratorService.generateSequence(Room.SEQUENCE_NAME));
		service.save(room);
	}
	
	@DeleteMapping("/room/{roomId}")
	public void deleteRoom(@PathVariable Long roomId) {
		
		service.deleteById(roomId);
	}
	
	@PutMapping("/room/{roomId}")
	public void updateRoom(@RequestBody Room room) {
		
		service.save(room);
	}
	
	
	@GetMapping("/room/{roomId}")
	public Optional<Room> findById(@PathVariable Long roomId) {
		
		return service.findById(roomId);
	}
	
	/*@GetMapping("/rooms/hotelName/{hotelId}")
	public List<Room> findRoomsByHotelName(@PathVariable Long hotelId) {
		
		Hotel hotel = service.findAll().stream().filter(h -> h.getHotelId()==hotelId).findAny().get();
		return hotel.getRooms().stream().filter(l -> l.isAvailability()==true).collect(Collectors.toList());
	}
	
	@GetMapping("/rooms/hotelName/{hotelId}/roomType/{type}")
	public List<Room> findRoomsByType(@PathVariable Long hotelId, @PathVariable String type){
		
		Hotel hotel = service.findAll().stream().filter(h -> h.getHotelId()==hotelId).findAny().get();
		return hotel.getRooms().stream().filter(l -> l.getRoomType().equals(type) && l.isAvailability()==true).collect(Collectors.toList());
	}
	
	@GetMapping("/rooms-admin/hotelName/{hotelId}")
	public List<Room> findRoomsByHotel(@PathVariable Long hotelId) {
		
		Hotel hotel = service.findAll().stream().filter(h -> h.getHotelId()==hotelId).findAny().get();
		return hotel.getRooms();
	}
	
	@GetMapping("/rooms-admin/hotelName/{hotelId}/roomType/{type}")
	public List<Room> findRooms(@PathVariable Long hotelId, @PathVariable String type){
		
		Hotel hotel = service.findAll().stream().filter(h -> h.getHotelId()==hotelId).findAny().get();
		return hotel.getRooms().stream().filter(l -> l.getRoomType().equals(type)).collect(Collectors.toList());
	}
	*/
}
