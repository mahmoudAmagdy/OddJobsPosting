package com.el7eita.cashisking.rest.domain.responseDTOs;

import java.util.List;

import com.el7eita.cashisking.entities.Job;

import lombok.Data;

@Data
public class UserResponse {
	private String userFirstName;
	private String userName;
	private String userLastName;
	private String userPhoneNumber;
	private Double userRating;
	private List<Job> jobs;

}
