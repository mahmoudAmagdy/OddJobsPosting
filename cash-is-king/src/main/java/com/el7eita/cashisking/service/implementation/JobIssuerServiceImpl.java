package com.el7eita.cashisking.service.implementation;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import com.el7eita.cashisking.entities.Job;
import com.el7eita.cashisking.entities.JobIssuer;
import com.el7eita.cashisking.repositories.JobIssuerRepository;
import com.el7eita.cashisking.repositories.JobRepository;
import com.el7eita.cashisking.service.JobIssuerService;
//import com.el7eita.cashisking.entities.JobTaker;
import com.el7eita.cashisking.service.exception.JobIssuerAlreadyExistsException;
//import com.el7eita.cashisking.service.exception.NoJobExistsException;
import com.el7eita.cashisking.service.exception.NoJobIssuerExistsException;
//import com.el7eita.cashisking.service.exception.NoJobTakerExistsException;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class JobIssuerServiceImpl implements JobIssuerService {
	 private static final Logger LOGGER = LoggerFactory.getLogger(JobIssuerServiceImpl.class);

    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private JobIssuerRepository jiRepository;
//    @Autowired
//    private JobTakerRepository jtRepository;
    
	@Override
	@Transactional
	public JobIssuer save(@NotNull @Valid final JobIssuer jobIss) {
    	LOGGER.debug("Creating {}", jobIss);
    	if(jiRepository.findByJiNatId(jobIss.getJiNatId()) != null) {
    		throw new JobIssuerAlreadyExistsException(
    				String.format("There is already a job issuer by the name of [%s]", jobIss.getJiFirstName() + " " + jobIss.getJiLastName()));
    	}
    	return jiRepository.save(jobIss);
	}
	
	@Override
	@Transactional
	public JobIssuer findOne(@NotNull @Valid final Integer jiId) {
    	LOGGER.debug("Finding a job issuer with job id {}", jiId);
		if(jiRepository.findById(jiId).get() == null) {
			throw new NoJobIssuerExistsException(
					String.format("There is no job issuer with job id=%d", jiId));
		}
		return jiRepository.findById(jiId).get();
	}

	@Override
	@Transactional
	public List<JobIssuer> findAll() {
    	LOGGER.debug("Finding all job issuers currently registered");

		return (List<JobIssuer>) jiRepository.findAll();
	}

    @Override
    public List<JobIssuer> findByJiFirstNameStartingWith(String jiFirstName) {
        LOGGER.debug("Retrieving the list of all users with firstname start with {}", jiFirstName);

        return (List<JobIssuer>) jiRepository.findByJiFirstNameStartingWithIgnoreCase(jiFirstName.toUpperCase(), Sort.by(Sort.Direction.ASC,"jiFirstName"));
    }

//	@Override
//	@Transactional
//	public JobIssuer findByJobId(@NotNull @Valid final Integer jobId) {
//    	LOGGER.debug("Retrieving the job issuer who issued job with id={}", jobId);
//    	Job job = jobRepository.getReferenceById(jobId);
//		if(job == null) {
//			throw new NoJobExistsException(
//					String.format("No job exists with id=%d", jobId));
//		}
//		return jiRepository.findByJobs(jobId);
//	}

	@Override
	@Transactional
	public JobIssuer findByNatId(@NotNull @Valid final String jiNatId) {
		if(jiRepository.findByJiNatId(jiNatId) == null) {
			throw new NoJobIssuerExistsException(
					String.format("No job issuer exists with national id %d", jiNatId));
		}
		return jiRepository.findByJiNatId(jiNatId);
	}
	
	@Override
	@Transactional
	public JobIssuer update(@NotNull @Valid final JobIssuer jobIss) {
		JobIssuer existing = jiRepository.findById(jobIss.getJiId()).get();
		if(existing == null) {
			throw new NoJobIssuerExistsException(
					String.format("There does not exist a job issuer with id=%d", jobIss.getJiId()));
		}
		return jiRepository.save(jobIss);
	}
	
	@Override
	@Transactional
	public JobIssuer delete(@NotNull @Valid final Integer jiId, @NotNull @Valid final List<Job> jobs) {
		JobIssuer existing = jiRepository.findById(jiId).get();
		if(existing == null) {
			throw new NoJobIssuerExistsException(
					String.format("There does not exist a job issuer with id=%d", jiId));
		}
		jiRepository.deleteById(jiId);
		jobRepository.deleteAllInBatch(jobs);
		return existing;
	}
	
	

	
//	@Override
//	@Transactional
//	public List<JobIssuer> findByJobTakerId(@NotNull @Valid final Integer jtId) {
//    	LOGGER.debug("Retrieving the job issuers who have the job taker with id={}", jtId);
//    	JobTaker jTaker = jtRepository.getReferenceById(jtId);
//		if(jTaker == null) {
//			throw new NoJobTakerExistsException(
//					String.format("No job taker exists with id=%d", jtId));
//		}
//		return jiRepository.findByJobTakerId(jtId);
//	}

    

}
