package com.el7eita.cashisking.entities;
import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name="job")
@Data
public class Job {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(columnDefinition = "serial")
	private Integer jobId;
	
	@NotNull
	@Min(value = 100, message = "at least 100")
	private Integer jobReward;
	
	@NotNull
	@NotBlank(message = "what are they going to do?")
	private String jobDescription;
	
	@NotNull
	@Min(value = 1, message = "how many people do you want?")
	private Integer slotsAvailable;
	
	private Integer slotsLeft;
	
	@NotNull
	@NotBlank(message = "when do you want folks to come around?")
	private String jobDate;
	
	@ManyToMany
	@JsonIgnore
	private List<User> jobTakers;
	
	@ManyToOne
	@JoinColumn(name="job_issuer_id")
	@JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
	private User jobIssuer;
	
	protected Job() {
		
	}
	
}
