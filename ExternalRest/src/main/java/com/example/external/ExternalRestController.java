/**
 * 
 */
package com.example.external;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.external.dao.JobSeekerDao;
import com.example.external.model.JobSeeker;

/**
 * @author g1123
 *
 */
@RestController
@RequestMapping("external")
public class ExternalRestController {
	
	@Autowired
    private JobSeekerDao jobSeekerDao;
	
	
	
	@GetMapping("/jobseekers")
    public String getAllJobSeekers() {
		final StringBuilder sb  = new StringBuilder();
		final List<JobSeeker> jobSeekerList =  jobSeekerDao.findAll();
		jobSeekerList.stream().forEach(s -> { 
			sb.append(s.toString());
			sb.append("\n");
        }); 
		return sb.toString();
    }
	
	@GetMapping("/jobseekers/{skillset}")
	public String findBySkillSet(@PathVariable("skillset") String[] skillSet){
		final StringBuilder sb  = new StringBuilder();
		final List<JobSeeker> jobSeekerList =  jobSeekerDao.findBySkillSet(skillSet);
		jobSeekerList.stream().forEach(s -> { 
			sb.append(s.toString());
			sb.append("Percentage : ");
			sb.append(calculatePercentage(skillSet, s));
			sb.append("\n");
        }); 
		return sb.toString();
	}
	
	private int calculatePercentage(String[] skillSet, JobSeeker jobSeeker) {
		int percentage = 0;
		String jobSeekerSkills = jobSeeker.getSkillSet();
		String[] jobSeekerSkillSet = jobSeekerSkills.split(",");
		int skillSetCnt = skillSet.length;
		int jobSeekerSkillSetCnt = jobSeekerSkillSet.length;
		int matchCnt = 0;
		for (int i = 0; i < jobSeekerSkillSetCnt; i++) {
			for (int j = 0; j < skillSetCnt; j++) {
				if (skillSet[j].trim().equalsIgnoreCase(jobSeekerSkillSet[i].trim())) {
					matchCnt++;
				}
			}
		}
		if(matchCnt == skillSetCnt) {
			percentage =  100;
		}else {
			percentage = (matchCnt * 100 / skillSetCnt);
		}
		return percentage;
	}
	
}
