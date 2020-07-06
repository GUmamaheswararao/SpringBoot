/**
 * 
 */
package com.example.talent;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.talent.dao.JobPostingDao;
import com.example.talent.dao.JobSeekerDao;
import com.example.talent.dao.JobStatusDao;
import com.example.talent.model.JobPosting;
import com.example.talent.model.JobSeeker;
import com.example.talent.model.JobStatus;


/**
 * @author g1123
 *
 */
@RestController
@Configuration
@PropertySource(value = {"application.properties"})
@RequestMapping("internal")
public class TalentRestController {
	@Autowired
    private JobSeekerDao jobSeekerDao;
	
	@Autowired
    private JobPostingDao jobPostingDao;
	
	@Autowired
    private JobStatusDao jobStatusDao;
	
	@Autowired
	Environment env;
	
	@GetMapping("/jobseekers/{skillset}")
	public String findBySkillSet(@PathVariable("skillset") String[] skillSet){
		final StringBuilder sb  = new StringBuilder();
		final List<JobSeeker> jobSeekerList =  jobSeekerDao.findBySkillSet(skillSet);
		jobSeekerList.stream().forEach(s -> { 
			sb.append(s.toString());
			sb.append("\n");
        }); 
		return sb.toString();
	}
	
	@GetMapping("/jobpostings")
	public String findBySkills(Model model){
		final StringBuilder sb  = new StringBuilder();
		final List<JobPosting> jobPostingList =  jobPostingDao.findAllJobs();
		for(JobPosting jobPosting : jobPostingList) {
			final String skills = jobPosting.getSkills();
			final String[] skillSet = skills.split(",");
			final List<JobSeeker> jobSeekerList =  jobSeekerDao.findBySkillSet(skillSet);
			//model.addAttribute("jobSeekers", jobSeekerList);
			jobSeekerList.stream().forEach(s -> { 
				sb.append("Job Id : ");
				sb.append(jobPosting.getId());
				sb.append(" Job Name : ");
				sb.append(jobPosting.getName());
				sb.append(" " +s.toString());
				sb.append("\n");
	        }); 
		} 
		//return "jobpostings";
		return sb.toString();
	}
	
	@GetMapping("/schedule")
	public String getDataForSchedule(){
		final StringBuilder sb  = new StringBuilder();
		String configPercent = env.getProperty("job.profile.percentage");
		double minPercent = Double.parseDouble(configPercent);
		final List<JobPosting> jobPostingList =  jobPostingDao.findAllJobs();
		for(JobPosting jobPosting : jobPostingList) {
			final String skills = jobPosting.getSkills();
			final String[] skillSet = skills.split(",");
			final List<JobSeeker> jobSeekerList =  jobSeekerDao.findBySkillSet(skillSet);
			jobSeekerList.stream().forEach(s -> { 
				String status = jobStatusDao.getJobStatusByJobIdAndJobSeekerId(jobPosting.getId(), s.getId());
				if((Double.compare(s.getPercent(), minPercent) > 0) && isValidStatus(status)) {
					sb.append("Job Id : ");
					sb.append(jobPosting.getId());
					sb.append(" Job Name : ");
					sb.append(jobPosting.getName());
					sb.append(" Seeker Id : ");
					sb.append(" " +s.getId());
					sb.append(" Seeker Name : ");
					sb.append(" " +s.getfName()+" " +s.getlName());
					sb.append(" Email : ");
					sb.append(" " +s.getEmail());
					sb.append(" Contact : ");
					sb.append(" " +s.getContactNum());
					sb.append(" Status : ");
					if(status == null) {
						sb.append(" " +JobStatusEnum.NONE.name());
					}else {
						sb.append(" " +status);
					}
					sb.append("\n");
				}
	        }); 
		} 
		return sb.toString();
	}
	
	private boolean isValidStatus(String status) {
		if(status == null) {
			return true;
		}else {
			if((JobStatusEnum.SELECTED.name().equalsIgnoreCase(status)) || (JobStatusEnum.REJECTED.name().equalsIgnoreCase(status))) {
				return false;
			}
		}
		return true;
	}
	
	public enum JobStatusEnum{
		EMAIL_SENT,
		IN_PROGRESS,
		REJECTED,
		SELECTED,
		NONE;
	}
	
	private String getEnumValStatus(String status){
		if(status == null || status.equalsIgnoreCase("null")) {
			return JobStatusEnum.NONE.name();
		}
		return status;
	}
	
	@RequestMapping(value = "/send/{jobId}/{jobSeekerId}/{status}")
	public String send(@PathVariable("jobId") int jobId, @PathVariable("jobSeekerId") int jobSeekerId, @PathVariable("status") String status) throws AddressException, MessagingException, IOException {
		int id = jobStatusDao.getIdByJobIdAndJobSeekerId(jobId, jobSeekerId);
		final JobStatus jobStatus = new JobStatus();
		if(id != 0) {
			jobStatus.setId(id);
		}
		jobStatus.setJobPostingId(jobId);
		jobStatus.setJobSeekerId(jobSeekerId);
		if(JobStatusEnum.NONE.name().equalsIgnoreCase(getEnumValStatus(status))){
			jobStatus.setStatus(JobStatusEnum.EMAIL_SENT.name());
			sendmail();
		}else if(JobStatusEnum.EMAIL_SENT.name().equalsIgnoreCase(status)){
			jobStatus.setStatus(JobStatusEnum.IN_PROGRESS.name());
		}else if(JobStatusEnum.IN_PROGRESS.name().equalsIgnoreCase(status)){
			jobStatus.setStatus(JobStatusEnum.SELECTED.name());
		}
		final JobStatus retJobStatus = jobStatusDao.saveAndFlush(jobStatus);
		return retJobStatus.getStatus();
	}
	
	@RequestMapping(value = "/sendemail")
	public String sendEmail() throws AddressException, MessagingException, IOException {
		String emailId = env.getProperty("company.email.id");
		 if(StringUtils.hasText(emailId)&& StringUtils.isEmpty(emailId)) {
			 sendmail();
			 return "Email sent successfully"; 
		 }else {
			 return "Email configuration not found";
		 }
	} 
	
	private void sendmail() throws AddressException, MessagingException, IOException {
		   String emailId = env.getProperty("company.email.id");
		   String pwd = env.getProperty("company.email.pwd");
		   if(StringUtils.hasText(emailId)&& StringUtils.isEmpty(emailId)) {
			   Properties props = new Properties();
			   props.put("mail.smtp.auth", "true");
			   props.put("mail.smtp.starttls.enable", "true");
			   props.put("mail.smtp.host", "smtp.gmail.com");
			   props.put("mail.smtp.port", "587");
			   Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			      protected PasswordAuthentication getPasswordAuthentication() {
			         return new PasswordAuthentication(emailId, pwd);
			      }
			   });
			   Message msg = new MimeMessage(session);
			   msg.setFrom(new InternetAddress(emailId, false));
	
			   msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailId));
			   msg.setSubject("Talent Intelligence");
			   msg.setContent("The platform should help in automation of talent acquisition ", "text/html");
			   msg.setSentDate(new Date());
	
			   MimeBodyPart messageBodyPart = new MimeBodyPart();
			   messageBodyPart.setContent("The platform should help in automation of talent acquisition and onboarding which aims to\r\n" + 
			   		"be an Artificial Intelligence product for both internal and external use\r\n", "text/html");
	
			   Multipart multipart = new MimeMultipart();
			   multipart.addBodyPart(messageBodyPart);
			   //MimeBodyPart attachPart = new MimeBodyPart();
	
			   //attachPart.attachFile("/var/tmp/image19.png");
			   //multipart.addBodyPart(attachPart);
			   msg.setContent(multipart);
			   Transport.send(msg);   
		   }
		}

}
