/**
 * 
 */
package com.example.external.dao;

import java.util.List;

import com.example.external.model.JobSeeker;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * @author g1123
 *
 */
@Repository
public interface JobSeekerDao extends JpaRepository<JobSeeker, Long>{
	List<JobSeeker> findBySkillSet(String[] skillSet);
}
