package com.el7eita.cashisking.service;

import java.util.List;

import com.el7eita.cashisking.entities.Job;
import com.el7eita.cashisking.entities.User;


public interface UserService {
	User save(User jobIss);
	User findOne(Integer jiId);
	List<User> findAll();
	List<User> findByUserFirstNameStartingWith(String userFirstName);
	User findByNatId(String jiNatId);
	User update(User jobIss);
	User delete(Integer jiId, List<Job> jobs);
	Job apply(Job job, Integer jobId, Integer userId);

}
