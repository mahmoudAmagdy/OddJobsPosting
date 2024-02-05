package com.el7eita.cashisking.entities;

import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@NotNull
	private Integer userId;
	
	@NotNull
	@NotBlank(message = "username must not be empty!")
	private String userName;
	
	@NotNull
	@NotBlank(message = "password must not be empty!")
	private String password;
		
	private Boolean active;
	
	private String roles;
	
	@NotNull
	@NotBlank(message = "the national id is empty!")
	private String userNatId;
	
	@NotNull
	@NotBlank(message = "the first name is empty!")
	private String userFirstName;
	
	@NotNull
	@NotBlank(message = "the last name is empty!")
	private String userLastName;
	
	@NotNull
	@NotBlank(message = "the phone number is empty!")
	private String userPhoneNumber;
	
	private Double userRating;
	
	@ManyToOne
	private UserType userType;
	
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
	private List<Job> jobs;

}
