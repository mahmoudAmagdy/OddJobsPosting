package com.el7eita.cashisking.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.el7eita.cashisking.entities.Job;
import com.el7eita.cashisking.entities.JobTaker;

@Service
public interface JobTakerService{
	JobTaker save(JobTaker jTaker);
	JobTaker findOne(Integer jtId);
	List<JobTaker> findAll();
	List<JobTaker> findByFirstName(String jtFirstName);
//	List<JobTaker> findByJobId(Integer jobId);
	JobTaker findByNatId(String jtNatId);
	JobTaker update(JobTaker jTaker);
	JobTaker delete(Integer jtId);
	Job apply(Job job, Integer jobId, Integer jtId);

//	List<JobTaker> findByJobIssuerId(Integer jiId);

}
