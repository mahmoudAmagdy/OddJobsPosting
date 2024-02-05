package com.el7eita.cashisking.service;

import java.util.List;

import com.el7eita.cashisking.entities.Job;


public interface JobService {
	Job save(Job job, Integer jiId);
	List<Job> findByUserId(Integer userId);
	Job update(Job job, Integer jobId, Integer jiId);
	Job delete(Integer jobId, Integer jiId);
//	Job apply(Job job, Integer jobId, Integer jtId);
	List<Job> findAll();
	Job findOne(Integer jobId);
}
