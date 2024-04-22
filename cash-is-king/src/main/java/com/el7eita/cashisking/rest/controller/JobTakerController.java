package com.el7eita.cashisking.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.el7eita.cashisking.entities.Job;
import com.el7eita.cashisking.entities.User;
import com.el7eita.cashisking.rest.domain.requestDTOSs.JobRequest;
import com.el7eita.cashisking.rest.domain.requestDTOSs.UserRequest;
import com.el7eita.cashisking.rest.domain.responseDTOs.JobResponse;
import com.el7eita.cashisking.rest.domain.responseDTOs.UserResponse;
import com.el7eita.cashisking.service.JobService;
import com.el7eita.cashisking.service.UserService;

@RestController
public class JobTakerController {
	 protected static final Logger LOGGER = LoggerFactory.getLogger(JobTakerController.class);

    @Autowired
    private JobService jobService;
    @Autowired
    protected UserService userService;
    @Autowired
    protected ModelMapper pojoMapper;

    //POST http://localhost:8080/Users
    //Content-type : app/json
    //payload:
    @PostMapping(value="/takers")
    public ResponseEntity<UserResponse> createUserJt(@RequestBody UserRequest useraker) {
        LOGGER.debug("Received request to create the {}", useraker);
        User user = userService.save(pojoMapper.map(useraker, User.class));
        return ResponseEntity.ok().body(pojoMapper.map(user, UserResponse.class));
    }

    //GET http://localhost:8080/Users/findAll
    @GetMapping(value="/takers")
    public ResponseEntity<List<UserResponse>> getUsers(){
        LOGGER.debug("Received request to retrieve all job takers");
        List<User> userakers = new ArrayList<User>();
        userakers = (List<User>) userService.findAll();
        // convert User to UserResponse
        List<UserResponse> userResponse = new ArrayList<UserResponse>();
        for (User userakr : userakers) {
            userResponse.add(pojoMapper.map(userakr, UserResponse.class));
        }
        return ResponseEntity.ok().body(userResponse);

    }

    //GET http://localhost:8080/Users/1
    @GetMapping(value="/takers/{userId}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Integer userId) {
    	return ResponseEntity.ok(pojoMapper.map(userService.findOne(userId), UserResponse.class));
    }

//    //GET http://localhost:8080/Users/search
//    @GetMapping(value="/searchByNatId")
//    public User findUserByNatId(@RequestParam String userNatId) {
//        return userService.findByNatId(userNatId);
//    	
//    }

    //GET http://localhost:8080/Users/search
    //Content-type: app/json
    //payload : {"userFirstName":"Johnny"}
    @GetMapping(value="/takers/findbyname")
    public ResponseEntity<List<UserResponse>> findUserByFirstName(@RequestParam String userFirstName) {
    	List<User> userakers = userService.findByUserFirstNameStartingWith(userFirstName);
    	List<UserResponse> userResponse = new ArrayList<UserResponse>();
    	for(User user : userakers) {
    		userResponse.add(pojoMapper.map(user, UserResponse.class));
    	}
        return ResponseEntity.ok(userResponse);
    }

    //PUT http://localhost:8080/Users/1
    //Content-type : app/json
    @PutMapping(value="/takers/{userId}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Integer userId, @RequestBody UserRequest userakr) {
    	User userOriginal = pojoMapper.map(userakr, User.class);
    	userOriginal.setUserId(userId);
    	return ResponseEntity.ok(pojoMapper.map(userService.update(userOriginal), UserResponse.class));
    }

    //POST http://localhost:8080/Users/1/jobs
    //Content-type : app/json

    @PutMapping(value="/takers/{userId}/jobs")
    public ResponseEntity<JobResponse> applyForJob(@PathVariable Integer userId, @RequestBody JobRequest job) {

    	Job jobOriginal = jobService.findOne(job.getJobId());
    	return ResponseEntity.ok().body(pojoMapper.map(userService.apply(jobOriginal, job.getJobId(), userId), JobResponse.class));
    }

    //GET http://localhost:8080/Users/1/jobsTaken
    @GetMapping(value="/takers/{userId}/jobsTaken")
    public ResponseEntity<List<JobResponse>> getJobs(@PathVariable Integer userId) {
    	List<Job> jobs = jobService.findByUserId(userId);
    	List<JobResponse> jobResponse = new ArrayList<JobResponse>();
    	for(Job job : jobs) {
    		jobResponse.add(pojoMapper.map(job, JobResponse.class));
    	}
        return ResponseEntity.ok(jobResponse);
    }

    //GET http://localhost:8080/Users/1/jobs/lookForWork
    @GetMapping(value="/{userId}/jobs/lookForWork")
    public ResponseEntity<List<JobResponse>> getJobs(){
        LOGGER.debug("Received request to retrieve all job takers");
        List<Job> jobs = new ArrayList<Job>();
        jobs = (List<Job>) jobService.findAll();
        // convert User to UserResponse
        List<JobResponse> jobResponse = new ArrayList<JobResponse>();
        for (Job job : jobs) {
        	if(job.getSlotsLeft() > 0)
        		jobResponse.add(pojoMapper.map(job, JobResponse.class));
        }
        return ResponseEntity.ok(jobResponse);

    }
}