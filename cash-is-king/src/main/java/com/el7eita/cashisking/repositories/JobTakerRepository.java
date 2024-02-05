package com.el7eita.cashisking.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.el7eita.cashisking.entities.JobTaker;

public interface JobTakerRepository extends JpaRepository<JobTaker, Integer>{
	
	JobTaker findByJtNatId(String jtNatId);

//    @Query("SELECT i FROM JobTaker i WHERE i.job.jobIssuer.jiId = ?1") //DOUBLE CHECK
//    List<JobTaker> findByJobIssuerId(Integer jiId);
    
//    @Query("SELECT i FROM JobTaker i WHERE i.job = ?1")
//    List<JobTaker> findByJobJobId(Integer jobId);
    
	@Query("SELECT j FROM JobTaker j WHERE UPPER(j.jtFirstName) LIKE ?1%") // case insensitive
    List<JobTaker> findByJtFirstNameStartingWithIgnoreCase(String jtFirstName, Sort sort);



}
