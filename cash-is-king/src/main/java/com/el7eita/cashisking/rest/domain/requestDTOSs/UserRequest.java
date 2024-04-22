package com.el7eita.cashisking.rest.domain.requestDTOSs;

import lombok.Data;

@Data
public class UserRequest {
	private Integer userId;
	private String userName;
	private String password;
	private String userNatId;
	private String userFirstName;
	private String userLastName;
	private String userPhoneNumber;
	private Double userRating;
	private Boolean active;
	private String roles;

}
