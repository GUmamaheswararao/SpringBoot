/**
 * 
 */
package com.example.talent.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.talent.model.JobStatus;

/**
 * @author g1123
 *
 */

@Repository
public class JobStatusDaoImpl implements JobStatusDaoCustom{
	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public String getJobStatusByJobIdAndJobSeekerId(int jobId, int jobSeekerId) {
		try {
		    	Query query = entityManager.createNativeQuery("SELECT js.* FROM job_status as js "+
		    			"WHERE js.job_Posting_Id=? AND js.job_Seeker_Id=? ", JobStatus.class);
		    	query.setParameter(1, jobId);
		    	query.setParameter(2, jobSeekerId);
		    	JobStatus result = (JobStatus) query.getSingleResult();
		    	if(result != null) {
		    		return result.getStatus();
		    	}
		  } catch (Exception e) {
		    
		  }
		return null;
	}
	
	@Override
	public int getIdByJobIdAndJobSeekerId(int jobId, int jobSeekerId) {
		try {
		    	Query query = entityManager.createNativeQuery("SELECT js.* FROM job_status as js "+
		    			"WHERE js.job_Posting_Id=? AND js.job_Seeker_Id=? ", JobStatus.class);
		    	query.setParameter(1, jobId);
		    	query.setParameter(2, jobSeekerId);
		    	JobStatus result = (JobStatus) query.getSingleResult();
		    	if(result != null) {
		    		return result.getId();
		    	}
		  } catch (Exception e) {
		    
		  }
		return 0;
	}
}
