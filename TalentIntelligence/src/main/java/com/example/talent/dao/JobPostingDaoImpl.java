/**
 * 
 */
package com.example.talent.dao;

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

import com.example.talent.model.JobPosting;

/**
 * @author g1123
 *
 */
public class JobPostingDaoImpl implements JobPostingDao{
	
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<JobPosting> findAllJobs() {
		List<JobPosting> jobPostingList = new ArrayList<JobPosting>();
		try {
		    	Query query = entityManager.createNativeQuery("SELECT js.* FROM job_posting as js ", JobPosting.class);
		    	jobPostingList.addAll(query.getResultList());
		   
		  } catch (Exception e) {
		    //return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		  }
		return jobPostingList;
	}

	@Override
	public List<JobPosting> findAll() {
		 List<JobPosting> jobPostingList = new ArrayList<JobPosting>();
		try {
		    this.findAll().forEach(jobPostingList::add);
		  } catch (Exception e) {
		    //return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		  }
		System.out.println(jobPostingList);
		return jobPostingList;
	}

	@Override
	public List<JobPosting> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<JobPosting> findAllById(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends JobPosting> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends JobPosting> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteInBatch(Iterable<JobPosting> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public JobPosting getOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends JobPosting> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends JobPosting> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<JobPosting> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends JobPosting> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<JobPosting> findById(Long id) {
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
	public void delete(JobPosting entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends JobPosting> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends JobPosting> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends JobPosting> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends JobPosting> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends JobPosting> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
