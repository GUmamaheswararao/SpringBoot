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
@Table(name = "Job_Posting")
public class JobPosting {
	@Id
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "skills")
	private String skills;
	/**
	 * @return the id
	 */
	@GeneratedValue(strategy = GenerationType.AUTO)
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the skills
	 */
	public String getSkills() {
		return skills;
	}
	/**
	 * @param skills the skills to set
	 */
	public void setSkills(String skills) {
		this.skills = skills;
	}
	@Override
	public String toString() {
		return "JobPosting [id=" + id + ", name=" + name + ", skills=" + skills + "]";
	}
	
}
