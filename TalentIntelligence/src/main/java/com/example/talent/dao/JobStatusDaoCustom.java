/**
 * 
 */
package com.example.talent.dao;

/**
 * @author g1123
 *
 */
public interface JobStatusDaoCustom {
	String getJobStatusByJobIdAndJobSeekerId(int jobId, int jobSeekerId);
	int getIdByJobIdAndJobSeekerId(int jobId, int jobSeekerId);
}
