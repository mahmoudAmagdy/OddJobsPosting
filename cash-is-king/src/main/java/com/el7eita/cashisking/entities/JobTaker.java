package com.el7eita.cashisking.entities;

import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name="jobTaker")
@Data
public class JobTaker {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(columnDefinition = "serial")
	private Integer jtId;
	
	@NotNull
	@NotBlank(message = "the national id is empty!")
	private String jtNatId;
	
	@NotNull
	@NotBlank(message = "the national id is empty!")
	private String jtFirstName;
	
	@NotNull
	@NotBlank(message = "the national id is empty!")
	private String jtLastName;
	
	@NotNull
	@NotBlank(message = "the national id is empty!")
	private String jtPhoneNumber;
	
	private Double jtRating;
	
	@ManyToMany
	@JsonIgnore
	@JoinColumn(name="job_taker_jobs")
    @OnDelete(action = OnDeleteAction.CASCADE)
	private List<Job> jobs;
	
	protected JobTaker() {
	}


}
