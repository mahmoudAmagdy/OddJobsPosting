package com.el7eita.cashisking.rest.domain.responseDTOs;

import java.util.List;

import com.el7eita.cashisking.entities.Job;

import lombok.Data;

@Data
public class JobTakerResponse {
	private String jtFirstName;
	private String jtLastName;
	private String jtPhoneNumber;
	private Double jtRating;
	private List<Job> job;

}
