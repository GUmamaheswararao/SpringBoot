/**
 * 
 */
package com.example.talent.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.talent.model.JobSeeker;
/**
 * @author g1123
 *
 */
@Repository
public interface JobSeekerDao extends JpaRepository<JobSeeker, Long>{
	List<JobSeeker> findBySkillSet(String[] skillSet);
}
