package com.el7eita.cashisking.rest.domain.requestDTOSs;


import lombok.Data;

@Data
public class JobRequest {

	private Integer jobId;
	private Integer jobReward;
	private String jobDescription;
	private Integer slotsAvailable;
	private Integer slotsLeft;
	private String jobDate;
	


}
