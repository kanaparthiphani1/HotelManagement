package com.hms.demo.controller;

import java.util.List;
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

import com.hms.demo.dao.HotelRepository;
import com.hms.demo.model.Hotel;
import com.hms.demo.model.Room;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v4")
public class HotelController {

	@Autowired
	private HotelRepository service;
	
	
	@GetMapping("/hotel")
	public List<Hotel> findAll() {
		
		return service.findAll();
	}

	@PostMapping("/hotel")
	public void createHotel(@RequestBody Hotel hotel) {
		
		service.save(hotel);
	}

	@DeleteMapping("hotel/delete/{hotelId}")
	public void deleteHotel(@PathVariable Long hotelId) {
		
		service.deleteById(hotelId);
	}

	@PutMapping("hotel/update")
	public void updateHotel(@RequestBody Hotel hotel) {
		
		service.save(hotel);
	}
	
	//update
	
	
	
	@GetMapping("/hotel/by/{hotelId}")
	public Hotel findById(@PathVariable Long hotelId) {
		
		return service.findAll().stream().filter(id -> id.getHotelId()==hotelId).findAny().get();
	}
	
	@GetMapping("/hotel/name/{hotelName}")
	public List<Hotel> findByName(@PathVariable String hotelName){
		
		return service.findAll().stream().filter(name -> name.getHotelName().equals(hotelName)).collect(Collectors.toList());
	}
	
	@GetMapping("/hotel/city/{hotelCity}")
	public List<Hotel> findByCity(@PathVariable String hotelCity){
		
		return service.findAll().stream().filter(name -> name.getCity().equals(hotelCity)).collect(Collectors.toList());
	}
	
	@GetMapping("/hotel/rating/{hotelRating}")
	public List<Hotel> findByRating(@PathVariable int hotelRating){
		
		return service.findAll().stream().filter(name -> name.getRating()==hotelRating).collect(Collectors.toList());
	}

}
