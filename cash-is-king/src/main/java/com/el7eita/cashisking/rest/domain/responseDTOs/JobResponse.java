package com.el7eita.cashisking.rest.domain.responseDTOs;

import java.util.List;

import com.el7eita.cashisking.entities.JobIssuer;
import com.el7eita.cashisking.entities.JobTaker;

import lombok.Data;

@Data
public class JobResponse {

	private Integer jobReward;
	private String jobDescription;
	private Integer slotsAvailable;
	private Integer slotsLeft;
	private String jobDate;
	
	private List<JobTaker> jobTakers;
	private JobIssuer jobIssuer;


}
