/**
 * 
 */
package com.example.external.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.external.dao.JobSeekerDao;
import com.example.external.model.JobSeeker;

/**
 * @author g1123
 *
 */
public class JobSeekerServiceImpl implements JobSeekerService{
	
	@Autowired
	private JobSeekerDao jobSeekerDao;
	
	@Override
	public List<JobSeeker> findJobSeekersBySkill(String skillSet) {
		return null;
	}

	@Override
	public List<JobSeeker> findAll() {
		return jobSeekerDao.findAll();
	}

	/**
	 * @return the jobSeekerDao
	 */
	public JobSeekerDao getJobSeekerDao() {
		return jobSeekerDao;
	}

	/**
	 * @param jobSeekerDao the jobSeekerDao to set
	 */
	public void setJobSeekerDao(JobSeekerDao jobSeekerDao) {
		this.jobSeekerDao = jobSeekerDao;
	}

}
