package com.el7eita.cashisking.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class UserType {
	private Integer typeId;
	private String typeName;
	@OneToMany
	private List<User> user;
}
