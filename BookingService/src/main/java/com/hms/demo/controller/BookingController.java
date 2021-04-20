package com.hms.demo.controller;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.demo.model.Booking;
import com.hms.demo.model.Feedback;
import com.hms.demo.service.SequenceGeneratorService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import com.hms.demo.dao.BookingRepository;
import com.hms.demo.dao.FeedBackRepo;
@RestController
@CrossOrigin("*")
@RequestMapping("/api/v2")
public class BookingController {

	@Autowired
	private BookingRepository repository;
	
	@Autowired
	private FeedBackRepo feedrepo;
	
	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;
	@ApiOperation(value = "View a list of available Rooms", response = List.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			
			})

	
	@GetMapping("/booking")
	public List<Booking> findAll() {
		System.out.println("Getall");
		return repository.findAll();
		
	}
	
	@GetMapping("/booking/count")
	public int findAllCount() {
		
		List<Booking> findAll = repository.findAll();
		int count =0;
		for(Booking book: findAll) {
			count++;
		}
		return count;
	}
	
	@ApiOperation(value = "add booking")
	@PostMapping("/booking")
	public void addBooking(@RequestBody Booking book) {
		System.out.println("HYYYYY");
		if(book.getId() == 0) {
		book.setBookingId(sequenceGeneratorService.generateSequence(book.SEQUENCE_NAME));}
		
		repository.save(book);
	}

//	@GetMapping("/booking/date/{startDate}/to/{endDate}")
//	public List<Booking> showBookingsByDate(
//			@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
//			@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
//		if ((startDate.compareTo(endDate)) > 0) {
//			return null;
//		} else {
//			return repository.findAll().stream().filter(
//					(bookings) -> bookings.getCheckin().isAfter(startDate.minusDays(1)) && bookings.getCheckout().isBefore(endDate.plusDays(1)))
//					.collect(Collectors.toList());
//		}
//	}
	
	@ApiOperation(value = "update booking")
	//update
	@PutMapping("/booking")
	public void updateBooking(@RequestBody Booking book) {
		System.out.println("HIIIII");
		System.out.println(book.toString());
		repository.save(book);
	}
	
	
	
		//view
		@GetMapping("/booking/hotel/{hotelId}")
		public List<Booking> findBookingsByHotel(@PathVariable Long hotelId) {
			return repository.findAll().stream().filter(x -> x.getHotelId()==hotelId).collect(Collectors.toList());
		}
		@ApiOperation(value = "Get booking details by customer id")
		@GetMapping("/booking/customer/{userId}")
		public List<Booking> findBookingsByCustomer(@PathVariable String userId){
			return repository.findByUserId(userId);
		}


		
		@GetMapping("/feed")
		public List<Feedback> getAllFeeds(){
			return feedrepo.findAll();
		}
		
		@GetMapping("/feed/bookingId/{bookingId}")
		public List<Feedback> getFeedsById(@PathVariable long bookingId){
			return feedrepo.findByBookingId(bookingId);
		}
		
		@PostMapping("/feed")
		public void postFeed(@RequestBody Feedback feed) {
			feed.setId(sequenceGeneratorService.generateSequence(feed.SEQUENCE_NAME));
			feedrepo.save(feed);
		}
		
		@GetMapping("/feed/userName/{userName}")
		public List<Feedback> getFeedsById(@PathVariable String userName){
			return feedrepo.findByUserName(userName);
		}
		
		@GetMapping("/feed/ratings")
		private float getAllFeedsforRating() {
			List<Feedback> feeds =  feedrepo.findAll();
			int total = 0;
			float grtthree = 0;
			float ratingPercent = 0;
			
			Iterator<Feedback> iter = feeds.iterator();
			while(iter.hasNext()) {
				Feedback feed = iter.next();
				total = total+1;
				if(feed.getRating()>3) {
					grtthree = grtthree+1;
				}
			}
			
			System.out.println(grtthree);
			System.out.println(total);
			
			ratingPercent = (grtthree/total)*100;
			
			return ratingPercent;
			
		}
		
		
	
}
