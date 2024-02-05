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
@Table(name="jobIssuer")
@Data
public class JobIssuer{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(columnDefinition = "serial")
	private Integer jiId;
	
	
	
	@NotNull
	@NotBlank(message = "the national id is empty!")
	private String jiNatId;
	
	@NotNull
	@NotBlank(message = "the first name is empty!")
	private String jiFirstName;
	
	@NotNull
	@NotBlank(message = "the last name is empty!")
	private String jiLastName;
	
	@NotNull
	@NotBlank(message = "the phone number is empty!")
	private String jiPhoneNumber;
	
	private Double jiRating;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy = "jobIssuer")
	@JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
	private List<Job> jobs;
	
	protected JobIssuer() {
		
	}
}
