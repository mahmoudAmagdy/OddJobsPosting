package com.el7eita.cashisking.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jdbc.repository.query.Query;

//import java.util.List;

//import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.el7eita.cashisking.entities.JobIssuer;

public interface JobIssuerRepository extends JpaRepository<JobIssuer, Integer> {
	
	
	JobIssuer findByJiNatId(String jiNatId);
    List<JobIssuer> findByJiLastName(String jiLastName);
    
	@Query("SELECT j FROM JobIssuer j WHERE UPPER(j.jiFirstName) LIKE ?1%") // case insensitive
    List<JobIssuer> findByJiFirstNameStartingWithIgnoreCase(String jiFirstName, Sort sort);


//    @Query("SELECT i FROM JobIssuer i WHERE i.job.jobId = ?1")
//    JobIssuer findByJobs(Integer jobId);
    
//    @Query("SELECT i FROM JobIssuer i WHERE i.job.jobTakers.jobTakersJtId = ?1") //DOUBLE CHECK
//    List<JobIssuer> findByJobTakersJtId(Integer jobTakersJtId);

}
