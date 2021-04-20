package com.hms.demo.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.demo.exception.ResourceNotFoundException;
import com.hms.demo.model.Staff;
import com.hms.demo.repository.StaffRepository;
import com.hms.demo.service.SequenceGeneratorService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
@Api(value="Hotel Management System", description="Operations pertaining to employee in Hotel Management System")
public class StaffController {
	@Autowired
	private StaffRepository staffRepository;
	
	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;

	@ApiOperation(value = "View a list of available Staff", response = List.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			
			})
	@GetMapping("/staff")
	public List<Staff> getAllStaff() {
		return staffRepository.findAll();
	}
	
	@GetMapping("/staff/count")
	public int getAllStaffCount() {
		List<Staff> staffList = staffRepository.findAll();
		int count =0;
		for(Staff staff:staffList) {
			count++;
		}
		return count;
	}

	@ApiOperation(value = "Get an employee by Id")
	@GetMapping("/staff/{id}")
	public ResponseEntity<Staff> getStaffById(@ApiParam(value = "Employee id from which employee object will retrieve", required = true) @PathVariable(value = "id") Long staffId)
			throws ResourceNotFoundException {
		Staff staff = staffRepository.findById(staffId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + staffId));
		return ResponseEntity.ok().body(staff);
	}

	@ApiOperation(value = "Add an employee")
	@PostMapping("/staff")
	public Staff createEmployee(@ApiParam(value = "Employee object store in database table", required = true)
	@Valid @RequestBody Staff staff) {
		staff.setId(sequenceGeneratorService.generateSequence(Staff.SEQUENCE_NAME));
		return staffRepository.save(staff);
	}

	@ApiOperation(value = "Update an employee")
	@PutMapping("/staff/{id}")
	public ResponseEntity<Staff> updateEmployee(@ApiParam(value = "Employee Id to update employee object", required = true) @PathVariable(value = "id") Long staffId,
			@ApiParam(value = "Update employee object", required = true) @Valid @RequestBody Staff staffDetails) throws ResourceNotFoundException {
		Staff staff = staffRepository.findById(staffId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + staffId));

		staff.setEmailId(staffDetails.getEmailId());
		staff.setLastName(staffDetails.getLastName());
		staff.setFirstName(staffDetails.getFirstName());
		final Staff updatedStaff = staffRepository.save(staff);
		return ResponseEntity.ok(updatedStaff);
	}

	@ApiOperation(value = "Delete an employee")
	@DeleteMapping("/staff/{id}")
	public Map<String, Boolean> deleteStaff(@ApiParam(value = "Employee Id from which employee object will delete from database table", required = true) @PathVariable(value = "id") Long staffId)
			throws ResourceNotFoundException {
		Staff staff = staffRepository.findById(staffId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + staffId));

		staffRepository.delete(staff);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
