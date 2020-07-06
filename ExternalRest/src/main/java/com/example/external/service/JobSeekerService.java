/**
 * 
 */
package com.example.external.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.external.model.JobSeeker;

/**
 * @author g1123
 *
 */
@Service
public interface JobSeekerService {
	List<JobSeeker> findAll();
	List<JobSeeker> findJobSeekersBySkill(String skillSet);
}
