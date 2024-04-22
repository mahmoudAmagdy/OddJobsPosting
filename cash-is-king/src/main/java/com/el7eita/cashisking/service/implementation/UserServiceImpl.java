package com.el7eita.cashisking.service.implementation;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import com.el7eita.cashisking.entities.Job;
import com.el7eita.cashisking.entities.User;
import com.el7eita.cashisking.repositories.JobRepository;
import com.el7eita.cashisking.repositories.UserRepository;
import com.el7eita.cashisking.service.UserService;
import com.el7eita.cashisking.service.exception.JobIssuerAlreadyExistsException;
import com.el7eita.cashisking.service.exception.JobSlotsFullException;
import com.el7eita.cashisking.service.exception.NoJobExistsException;
import com.el7eita.cashisking.service.exception.NoJobIssuerExistsException;
import com.el7eita.cashisking.service.exception.NoJobTakerExistsException;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class UserServiceImpl implements UserService{
	 private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private UserRepository userRepository;
    
	@Override
	@Transactional
	public User save(@NotNull @Valid final User jobIss) {
    	LOGGER.debug("Creating {}", jobIss);
    	if(!userRepository.findByUserName(jobIss.getUserName()).isEmpty()) {
    		throw new JobIssuerAlreadyExistsException(
    				String.format("There is already a user by the name of [%s]", jobIss.getUserFirstName() + " " + jobIss.getUserLastName()));
    	}
    	return userRepository.save(jobIss);
	}
	
	@Override
	@Transactional
	public User findOne(@NotNull @Valid final Integer userId) {
    	LOGGER.debug("Finding a job issuer with job id {}", userId);
		if(userRepository.findById(userId).get() == null) {
			throw new NoJobIssuerExistsException(
					String.format("There is no job issuer with job id=%d", userId));
		}
		return userRepository.findById(userId).get();
	}

	@Override
	@Transactional
	public List<User> findAll() {
    	LOGGER.debug("Finding all job issuers currently registered");

		return (List<User>) userRepository.findAll();
	}

    @Override
    @Transactional
    public List<User> findByUserFirstNameStartingWith(String userFirstName) {
        LOGGER.debug("Retrieving the list of all users with firstname start with {}", userFirstName);

        return (List<User>) userRepository.findByUserFirstNameStartingWithIgnoreCase(userFirstName.toUpperCase(), Sort.by(Sort.Direction.ASC,"userFirstName"));
    }
    
	@Override
	@Transactional
	public User findByNatId(@NotNull @Valid final String userNatId) {
		if(userRepository.findByUserNatId(userNatId) == null) {
			throw new NoJobIssuerExistsException(
					String.format("No job issuer exists with national id %d", userNatId));
		}
		return userRepository.findByUserNatId(userNatId).get();
	}
	
	@Override
	@Transactional
	public User update(@NotNull @Valid final User user) {
		User existing = userRepository.findById(user.getUserId()).get();
		if(existing == null) {
			throw new NoJobIssuerExistsException(
					String.format("There does not exist a user with id=%d", user.getUserId()));
		}
		return userRepository.save(user);
	}
	
	@Override
	@Transactional
	public User delete(@NotNull @Valid final Integer userId, @NotNull @Valid final List<Job> jobs) {
		User existing = userRepository.findById(userId).get();
		if(existing == null) {
			throw new NoJobIssuerExistsException(
					String.format("There does not exist a job issuer with id=%d", userId));
		}
		userRepository.deleteById(userId);
		jobRepository.deleteAllInBatch(jobs);
		return existing;
	}
	
    @Override
    @Transactional
    public Job apply(@NotNull @Valid final Job job, @NotNull @Valid final Integer jobId, @NotNull @Valid final Integer userId) {
        LOGGER.debug("Applying {} on JobId={} JobTakerId={}", job, jobId, userId);
        User jTaker =  userRepository.findById(userId).get();
        if(job.getSlotsLeft() <= 0) {
        	throw new JobSlotsFullException(
                    String.format("Job slots are already full!"));
        }
        if (jTaker == null) {
            throw new NoJobTakerExistsException (
                    String.format("No job issuer exists with id=%d", userId));
        }
        if (!jobRepository.existsById(jobId)) {
            throw new NoJobExistsException (
                    String.format("No job exists with id=%d", jobId));
        }
        job.setJobId(jobId);
        List<Job> tempJobList = jTaker.getJobs();
        tempJobList.add(job);
        jTaker.setJobs(tempJobList);
        userRepository.save(jTaker);
        List<User> tempJtList = job.getJobTakers();
        tempJtList.add(jTaker);
        job.setJobTakers(tempJtList);
        job.setSlotsLeft(job.getSlotsLeft()-1);
        return jobRepository.save(job);
    }



}
