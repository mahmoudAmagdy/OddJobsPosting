package com.el7eita.cashisking.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.el7eita.cashisking.service.UserService;
import com.el7eita.cashisking.service.JobService;
import com.el7eita.cashisking.entities.*;
import com.el7eita.cashisking.rest.domain.requestDTOSs.UserRequest;
import com.el7eita.cashisking.rest.domain.requestDTOSs.JobRequest;
import com.el7eita.cashisking.rest.domain.responseDTOs.UserResponse;
import com.el7eita.cashisking.rest.domain.responseDTOs.JobResponse;


@RestController
public class JobIssuerController {
	 protected static final Logger LOGGER = LoggerFactory.getLogger(JobIssuerController.class);
	    
	 	@Autowired
	    private UserService jiService;
	    @Autowired
	    private JobService jobService;
	    @Autowired
	    protected ModelMapper pojoMapper;

	    
	    //POST http://localhost:8080/issuers
	    //Content-type : app/json
	    //payload: { "jiNatId":"30004108762097", "jiFirstName":"Ahmed", "jiLastName":"Sabra", "jiPhoneNumber":"23294233", "jiRating":8.3}
	    @PostMapping(value="/issuers")
	    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest jIss) {
	        LOGGER.debug("Received request to create the {}", jIss);
	    	User ji = jiService.save(pojoMapper.map(jIss, User.class));
	        return ResponseEntity.ok(pojoMapper.map(ji,UserResponse.class));
	    }
	    
	    //GET http://localhost:8080/issuers
	    @GetMapping(value="/issuers")
	    public ResponseEntity<List<UserResponse>> getUsers() {
	        LOGGER.debug("Received request to list all users");
	        List<User> jIssers = new ArrayList<User>();
	        jIssers = (List<User>) jiService.findAll();
	        // convert User to UserResponse
	        List<UserResponse> jiResponse = new ArrayList<UserResponse>();
	        for (User jIss : jIssers) {
	            jiResponse.add(pojoMapper.map(jIss, UserResponse.class));
	        }
	        return ResponseEntity.ok(jiResponse);
	    }
	    
	    //GET http://localhost:8080/issuers/1
	    @GetMapping(value="/issuers/{jiId}")
	    public ResponseEntity<UserResponse> getUser(@PathVariable Integer jiId) {
	        LOGGER.debug("Received request to list the job issuer with id {}", jiId);
	    	return ResponseEntity.ok(pojoMapper.map(jiService.findOne(jiId), UserResponse.class));
	    }
	    
	    //GET http://localhost:8080/apis/issuers/search
	    //Content-type: app/json
	    //payload : {"jtFirstName":"Cache"}
	    @GetMapping(value="/issuers/")
	    public ResponseEntity<List<UserResponse>> findUserByFirstName(@RequestParam String jiFirstName) {
	    	List<User> jIssers = jiService.findByUserFirstNameStartingWith(jiFirstName);
	        List<UserResponse> jiResponse = new ArrayList<UserResponse>();
	        for (User jIss : jIssers) {
	            jiResponse.add(pojoMapper.map(jIss, UserResponse.class));
	        }

	    	return ResponseEntity.ok(jiResponse);    	
	    }
	    
	    //PUT http://localhost:8080/apis/issuers/1
	    //Content-type : app/json
	    @PutMapping(value="/issuers/{jiId}")
	    public ResponseEntity<UserResponse> updateUser(@PathVariable Integer jiId, @RequestBody UserRequest jIss) {
	    	User jiOriginal = pojoMapper.map(jIss, User.class);
	    	jiOriginal.setUserId(jiId);
	    	return ResponseEntity.ok(pojoMapper.map(jiService.update(jiOriginal), UserResponse.class));
	    }
	    
	    //POST http://localhost:8080/Users/1/jobs
	    //Content-type : app/json

	    @PostMapping(value="/issuers/{jiId}/jobs")
	    public ResponseEntity<JobResponse> createJob(@PathVariable Integer jiId, @RequestBody JobRequest job) {
	    	Job jobOriginal = pojoMapper.map(job, Job.class);
	        return ResponseEntity.ok().body(pojoMapper.map(jobService.save(jobOriginal, jiId), JobResponse.class));
	    }
	    
	    //GET http://localhost:8080/Users/1/jobs
	    @GetMapping(value="/issuers/{jiId}/jobs")
	    public ResponseEntity<List<JobResponse>> getJobs(@PathVariable Integer jiId) {
	    	List<Job> jobs = jobService.findByUserId(jiId);
	    	List<JobResponse> jobResponse = new ArrayList<JobResponse>();
	        for (Job job : jobs) {
	            jobResponse.add(pojoMapper.map(job, JobResponse.class));
	        }

	        return ResponseEntity.ok(jobResponse);
	    }

	    //PUT http://localhost:8080/Users/1/jobs
	    //Content-type : app/json

	    @PutMapping(value="/issuers/{jiId}/jobs/{jobId}")
	    public ResponseEntity<JobResponse> updateJob(@PathVariable Integer jiId, @PathVariable Integer jobId, @RequestBody JobRequest job) {
	    	Job jobOriginal = pojoMapper.map(job, Job.class);
	        return ResponseEntity.ok(pojoMapper.map(jobService.update(jobOriginal, jobId, jiId), JobResponse.class));
	    }

	    //DELETE http://localhost:8080/Users/1/jobs/1
	    @DeleteMapping(value="/issuers/{jiId}/jobs/{jobId}")
	    public String deleteJob(@PathVariable Integer jiId, @PathVariable Integer jobId) {
	    	jobService.delete(jobId, jiId);
	    	return jobId + "is deleted";
	    }


	    
}
