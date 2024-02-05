package com.el7eita.cashisking.rest.domain.responseDTOs;

import java.util.List;

import com.el7eita.cashisking.entities.User;

import lombok.Data;

@Data
public class JobResponse {

	private Integer jobReward;
	private String jobDescription;
	private Integer slotsAvailable;
	private Integer slotsLeft;
	private String jobDate;
	
	private List<User> jobTakers;
	private User jobIssuer;


}
