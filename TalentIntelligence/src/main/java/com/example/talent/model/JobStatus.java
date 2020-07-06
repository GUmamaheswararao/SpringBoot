/**
 * 
 */
package com.example.talent.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author g1123
 *
 */
@Entity
@Table(name = "Job_Status")
public class JobStatus {
	
	@Id
	@Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	
	@Column(name = "job_posting_id")
	private int jobPostingId;
	
	@Column(name = "job_seeker_id")
	private int jobSeekerId;
	
	@Column(name = "status")
	private String status;
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the jobPostingId
	 */
	public int getJobPostingId() {
		return jobPostingId;
	}
	/**
	 * @param jobPostingId the jobPostingId to set
	 */
	public void setJobPostingId(int jobPostingId) {
		this.jobPostingId = jobPostingId;
	}
	/**
	 * @return the jobSeekerId
	 */
	public int getJobSeekerId() {
		return jobSeekerId;
	}
	/**
	 * @param jobSeekerId the jobSeekerId to set
	 */
	public void setJobSeekerId(int jobSeekerId) {
		this.jobSeekerId = jobSeekerId;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
}
