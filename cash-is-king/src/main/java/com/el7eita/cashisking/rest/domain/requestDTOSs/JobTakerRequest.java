package com.el7eita.cashisking.rest.domain.requestDTOSs;


import lombok.Data;

@Data
public class JobTakerRequest {
	private String jtFirstName;
	private String jtLastName;
	private String jtPhoneNumber;
	private Double jtRating;

}