package com.el7eita.cashisking.rest.domain.requestDTOSs;

import lombok.Data;

@Data
public class UserRequest {
	private int userId;
	private String userNatId;
	private String userFirstName;
	private String userLastName;
	private String userPhoneNumber;
	private Double userRating;

}
