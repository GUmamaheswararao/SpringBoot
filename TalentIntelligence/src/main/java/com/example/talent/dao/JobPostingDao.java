/**
 * 
 */
package com.example.talent.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.talent.model.JobPosting;

/**
 * @author g1123
 *
 */
@Repository
public interface JobPostingDao extends JpaRepository<JobPosting, Long>{
	List<JobPosting> findAllJobs();
}
