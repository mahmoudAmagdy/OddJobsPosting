package com.el7eita.cashisking.rest.domain.requestDTOSs;
import lombok.Data;

@Data
public class JobIssuerRequest {
	private int jiId;
	private String jiNatId;
	private String jiFirstName;
	private String jiLastName;
	private String jiPhoneNumber;
	private Double jiRating;

}
