package com.el7eita.cashisking.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class UserType {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer typeId;
	private String typeName;
	@OneToMany
	private List<User> user;
}
