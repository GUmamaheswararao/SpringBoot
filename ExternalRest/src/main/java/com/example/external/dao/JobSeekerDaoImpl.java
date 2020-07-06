/**
 * 
 */
package com.example.external.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.external.model.JobSeeker;

/**
 * @author g1123
 *
 */
public class JobSeekerDaoImpl implements JobSeekerDao{
	
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<JobSeeker> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<JobSeeker> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<JobSeeker> findAllById(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends JobSeeker> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends JobSeeker> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteInBatch(Iterable<JobSeeker> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public JobSeeker getOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends JobSeeker> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends JobSeeker> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<JobSeeker> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends JobSeeker> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<JobSeeker> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(JobSeeker entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends JobSeeker> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends JobSeeker> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends JobSeeker> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends JobSeeker> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends JobSeeker> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public List<JobSeeker> findBySkillSet(String[] skillSet) {
		try {
		    List<JobSeeker> jobSeekerList = new ArrayList<JobSeeker>();
		    if (skillSet == null) {
		      this.findAll().forEach(jobSeekerList::add);
		    }else {
		    	Query query = entityManager.createNativeQuery("SELECT js.* FROM jobseeker as js " +
		                "WHERE "+getParam(skillSet), JobSeeker.class);
		    	int cnt = 1;
				for(String skill : skillSet) {
					query.setParameter(cnt, "%" + skill + "%");
					cnt++;
				}
		        jobSeekerList.addAll(query.getResultList());
		        return jobSeekerList;
		    }
		  } catch (Exception e) {
		    //return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		  }
		return Collections.emptyList();
	}
	
	private String getParam(String[] skillSet) {
		StringBuilder sb  = new StringBuilder();
		int skillSetLen = skillSet.length;
		if(skillSetLen == 1) {
			sb.append("js.skillset ILIKE ?");
			return sb.toString();
		}else {
			int cnt = 1;
			for(int i=0 ; i<skillSetLen; i++) {
				if(skillSetLen == cnt) {
					sb.append("js.skillset ILIKE ?");
				}else {
					sb.append("js.skillset ILIKE ? ");
					sb.append("OR ");
				}
				cnt++;
			}
		}
		return sb.toString();
	}

}
