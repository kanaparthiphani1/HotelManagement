package com.hms.demo.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.hms.demo.model.Staff;



@Repository
public interface StaffRepository extends MongoRepository<Staff, Long>{

	

}
