package com.el7eita.cashisking.service.implementation;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import com.el7eita.cashisking.entities.Job;
import com.el7eita.cashisking.entities.JobTaker;
import com.el7eita.cashisking.repositories.JobRepository;
import com.el7eita.cashisking.repositories.JobTakerRepository;
import com.el7eita.cashisking.service.JobTakerService;
import com.el7eita.cashisking.service.exception.JobSlotsFullException;
import com.el7eita.cashisking.service.exception.JobTakerAlreadyExistsException;
import com.el7eita.cashisking.service.exception.NoJobExistsException;
import com.el7eita.cashisking.service.exception.NoJobIssuerExistsException;
import com.el7eita.cashisking.service.exception.NoJobTakerExistsException;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class JobTakerServiceImpl implements JobTakerService {
	 private static final Logger LOGGER = LoggerFactory.getLogger(JobTakerServiceImpl.class);

	    @Autowired
	    private JobTakerRepository jtRepository;
	    
	    @Autowired JobRepository jobRepository;
		@Override
		@Transactional
		public JobTaker save(@NotNull @Valid final JobTaker jTaker) {
	    	LOGGER.debug("Creating {}", jTaker);
	    	if(jtRepository.existsById(jTaker.getJtId())) {
	    		throw new JobTakerAlreadyExistsException(
	    				String.format("There is already a job taker by the name of %d", jTaker.getJtFirstName() + " " + jTaker.getJtLastName()));
	    	}
	    	return jtRepository.save(jTaker);
		}
		
		@Override
		@Transactional
		public JobTaker findOne(@NotNull @Valid final Integer jtId) {
	    	LOGGER.debug("Finding a job issuer with job id {}", jtId);
			if(jtRepository.findById(jtId).get() == null) {
				throw new NoJobTakerExistsException(
						String.format("There is no job taker with job id=%d", jtId));
			}
			return jtRepository.findById(jtId).get();
		}

		@Override
		@Transactional
		public List<JobTaker> findAll() {
	    	LOGGER.debug("Finding all job takers currently registered");

			return (List<JobTaker>) jtRepository.findAll();
		}

	    @Override
	    public List<JobTaker> findByFirstName(String jtFirstName) {
	        LOGGER.debug("Retrieving the list of all users with firstname start with {}", jtFirstName);
	        return (List<JobTaker>) jtRepository.findByJtFirstNameStartingWithIgnoreCase(jtFirstName.toUpperCase(), Sort.by(Sort.Direction.ASC,"jtFirstName"));
	    }

//		@Override
//		@Transactional
//		public List<JobTaker> findByJobId(@NotNull @Valid final Integer jobId) {
//	    	LOGGER.debug("Retrieving the job takers who are enlisted to do the job with id={}", jobId);
//	    	Job job = jobRepository.getReferenceById(jobId);
//			if(job == null) {
//				throw new NoJobExistsException(
//						String.format("No job exists with id=%d", jobId));
//			}
//			return jtRepository.findByJobJobId(jobId);
//		}

		@Override
		@Transactional
		public JobTaker findByNatId(@NotNull @Valid final String jtNatId) {
			if(jtRepository.findByJtNatId(jtNatId) == null) {
				throw new NoJobTakerExistsException(
						String.format("No job taker exists with national id %d", jtNatId));
			}
			return jtRepository.findByJtNatId(jtNatId);
		}
		
		@Override
		@Transactional
		public JobTaker update(@NotNull @Valid final JobTaker jTaker) {
			JobTaker existing = jtRepository.findById(jTaker.getJtId()).get();
			if(existing == null) {
				throw new NoJobIssuerExistsException(
						String.format("There does not exist a job taker with id=%d", jTaker.getJtId()));
			}
			return jtRepository.save(jTaker);
		}
		
		@Override
		@Transactional
		public JobTaker delete(@NotNull @Valid final Integer jtId) {
			JobTaker existing = jtRepository.findById(jtId).get();
			if(existing == null) {
				throw new NoJobTakerExistsException(
						String.format("There does not exist a job taker with id=%d", jtId));
			}
			jtRepository.deleteById(jtId);
			return existing;
		}
	    @Override
	    @Transactional
	    public Job apply(@NotNull @Valid final Job job, @NotNull @Valid final Integer jobId, @NotNull @Valid final Integer jtId) {
	        LOGGER.debug("Applying {} on JobId={} JobTakerId={}", job, jobId, jtId);
	        JobTaker jTaker =  jtRepository.findById(jtId).get();
	        if(job.getSlotsLeft() <= 0) {
	        	throw new JobSlotsFullException(
	                    String.format("Job slots are already full!"));
	        }
	        if (jTaker == null) {
	            throw new NoJobTakerExistsException (
	                    String.format("No job issuer exists with id=%d", jtId));
	        }
	        if (!jobRepository.existsById(jobId)) {
	            throw new NoJobExistsException (
	                    String.format("No job exists with id=%d", jobId));
	        }
	        job.setJobId(jobId);
	        List<Job> tempJobList = jTaker.getJobs();
	        tempJobList.add(job);
	        jTaker.setJobs(tempJobList);
	        jtRepository.save(jTaker);
//	        List<JobTaker> tempJtList = job.getJobTakers();
//	        tempJtList.add(jTaker);
//	        job.setJobTakers(tempJtList);
	        job.setSlotsLeft(job.getSlotsLeft()-1);
	        return jobRepository.save(job);

	    }

		
}
