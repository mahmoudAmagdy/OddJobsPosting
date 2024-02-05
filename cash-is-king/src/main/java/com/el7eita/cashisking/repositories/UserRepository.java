package com.el7eita.cashisking.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.el7eita.cashisking.entities.User;


public interface UserRepository extends JpaRepository<User, Integer>{
	Optional<User> findByUserName(String userName);
	Optional<User> findByUserNatId(String userNatId);
    List<Optional<User>> findByUserLastName(String userLastName);
	@Query("SELECT j FROM User j WHERE UPPER(j.userFirstName) LIKE ?1%") // case insensitive
    List<User> findByUserFirstNameStartingWithIgnoreCase(String userFirstName, Sort sort);


}
