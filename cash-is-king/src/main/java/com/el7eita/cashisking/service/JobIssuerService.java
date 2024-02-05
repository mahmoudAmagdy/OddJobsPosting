package com.el7eita.cashisking.service;

import java.util.List;


import com.el7eita.cashisking.entities.Job;
import com.el7eita.cashisking.entities.JobIssuer;

public interface JobIssuerService {
	JobIssuer save(JobIssuer jobIss);
	JobIssuer findOne(Integer jiId);
	List<JobIssuer> findAll();
//	JobIssuer findByJobId(Integer jobId);
	List<JobIssuer> findByJiFirstNameStartingWith(String jiFirstName);
	JobIssuer findByNatId(String jiNatId);
	JobIssuer update(JobIssuer jobIss);
	JobIssuer delete(Integer jiId, List<Job> jobs);
//	List<JobIssuer> findByJobTakerId(Integer jtId);

}
