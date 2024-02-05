package com.el7eita.cashisking.service.implementation;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.el7eita.cashisking.entities.Job;
import com.el7eita.cashisking.entities.User;
import com.el7eita.cashisking.repositories.JobRepository;
import com.el7eita.cashisking.repositories.UserRepository;
import com.el7eita.cashisking.service.JobService;
import com.el7eita.cashisking.service.exception.JobAlreadyExistsException;
import com.el7eita.cashisking.service.exception.NoJobExistsException;
import com.el7eita.cashisking.service.exception.NoJobIssuerExistsException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;


public class JobServiceImpl implements JobService{
	 private static final Logger LOGGER = LoggerFactory.getLogger(JobServiceImpl.class);
	 
	    @Autowired
	    private JobRepository jobRepository;
	    @Autowired
	    private UserRepository userRepository;
	    
	    @Override
	    @Transactional
	    public Job save(@NotNull @Valid final Job job, @NotNull @Valid final Integer userId) {
	    	LOGGER.debug("Creating {} on jobIssuerId={}", job, userId);
	    	User jobIss = userRepository.findById(userId).get();
	        if (jobIss == null) {
	            throw new NoJobIssuerExistsException (
	                    String.format("No user exists with id=%d", userId));
	        }
	    	if(jobRepository.existsById(job.getJobId())) {
	    		throw new JobAlreadyExistsException(
	    				String.format("there already exists a job with ID=%d", job.getJobId()));
	    	}
	    	Job newJob = job;
	    	job.setJobIssuer(jobIss);
	    	List<Job> tempJobList = jobIss.getJobs();
	    	tempJobList.add(job);
	    	jobIss.setJobs(tempJobList);
	    	userRepository.save(jobIss);
	    	return jobRepository.save(newJob);
	    }
	    
	    @Override
	    @Transactional(readOnly = true)
	    public List<Job> findByUserId(@NotNull @Valid final Integer userId) {
	    	LOGGER.debug("Retrieving all jobs issued by job issuer with id={}", userId);
	    	User userBase = userRepository.findById(userId).get();
	    	if(userBase == null) {
	    		throw new NoJobIssuerExistsException(
	    				String.format("No job issuer exists with id=%d", userId));
	        }
	    	return jobRepository.findByJobIssuerUserId(userId);
	    }
	    

	    @Override
	    @Transactional
	    public Job update(@NotNull @Valid final Job job, @NotNull @Valid final Integer jobId, @NotNull @Valid final Integer userId) {
	        LOGGER.debug("Updating {} on JobId={} JobIssuerId={}", job, jobId, userId);
	        User jobIss =  userRepository.findById(userId).get();
	        if (jobIss == null) {
	            throw new NoJobIssuerExistsException (
	                    String.format("No job issuer exists with id=%d", userId));
	        }
	        if (!jobRepository.existsById(jobId)) {
	            throw new NoJobExistsException (
	                    String.format("No job exists with id=%d", jobId));
	        }
	        job.setJobId(jobId);
	        job.setJobIssuer(jobIss);
	        return jobRepository.save(job);
	    }
	    
	    @Override
	    @Transactional
	    public Job delete(@NotNull @Valid final Integer jobId, @NotNull @Valid final Integer userId) {
	        LOGGER.debug("Deleting {} on UserId={}", jobId, userId);
	        if (!userRepository.existsById(userId)) {
	            throw new NoJobIssuerExistsException (
	                    String.format("No user exists with id=%d", userId));
	        }
	        Job job = jobRepository.findById(jobId).get();
	        if (job == null) {
	            throw new NoJobExistsException (
	                    String.format("No item exists with id=%d", jobId));
	        }
	        jobRepository.deleteById(jobId);
	        return job;
	    }
	    

		@Override
		@Transactional
		public List<Job> findAll() {
	        LOGGER.debug("finding all currently available jobs");
	        
	        return jobRepository.findAll();
		}
		
		@Override
		@Transactional
		public Job findOne(@NotNull @Valid final Integer jobId) {
	    	LOGGER.debug("Finding a job with job id {}", jobId);
			if(jobRepository.findById(jobId).get() == null) {
				throw new NoJobIssuerExistsException(
						String.format("There is no job issuer with job id=%d", jobId));
			}
			return jobRepository.findById(jobId).get();
		}


}
