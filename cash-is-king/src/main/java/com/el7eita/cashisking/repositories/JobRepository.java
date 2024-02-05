package com.el7eita.cashisking.repositories;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.el7eita.cashisking.entities.Job;

public interface JobRepository extends JpaRepository<Job, Integer>{
    @Query("SELECT i FROM Job i WHERE i.userId = ?1")
    List<Job> findByJobIssuerUserId(Integer userId);
    

}
