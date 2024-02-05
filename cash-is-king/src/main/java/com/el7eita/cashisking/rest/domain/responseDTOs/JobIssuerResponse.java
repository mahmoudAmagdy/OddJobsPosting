package com.el7eita.cashisking.rest.domain.responseDTOs;
import java.util.List;
import com.el7eita.cashisking.entities.Job;
import lombok.Data;

@Data
public class JobIssuerResponse {
	private String jiFirstName;
	private String jiLastName;
	private String jiPhoneNumber;
	private Double jiRating;
	private List<Job> jobs;

}
