/**
 * 
 */
package com.example.talent.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.talent.model.JobStatus;

/**
 * @author g1123
 *
 */
@Repository
public interface JobStatusDao extends JpaRepository<JobStatus, Long>,JobStatusDaoCustom{
	
}
