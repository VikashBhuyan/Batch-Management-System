package com.masai.dao;

import java.util.ArrayList;
import java.util.List;

import com.masai.entity.Batch;
import com.masai.entity.Faculty;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomeThingWentWrongException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;

public  class BatchDAOImp implements BatchDAO{

	@Override
	public void addBatch(Batch b) throws SomeThingWentWrongException{
		// TODO Auto-generated method stub
		EntityManager em = EMUtils.getEntityManager();
		try {
			Query query = em.createQuery("SELECT count(c) FROM Batch c WHERE course_name = :course");
			query.setParameter("course", b.getCourse_name());
			if((Long)query.getSingleResult() > 0) {
				throw new SomeThingWentWrongException("Batch already exists with name " + b.getCourse_name());
			}
			
			EntityTransaction et = em.getTransaction();
			et.begin();
			em.persist(b);
			et.commit();
		}catch(PersistenceException ex) {
			throw new SomeThingWentWrongException("Unable to process request, try again later");
		}finally{
			em.close();
		}
	}

	@Override
	public void updateBatch(int id, String name, int seat, double duration) throws SomeThingWentWrongException, NoRecordFoundException{
		// TODO Auto-generated method stub
		EntityManager em = null;
		try {
			em = EMUtils.getEntityManager();
			Batch b = em.find(Batch.class, id);
			if(b == null)
				throw new NoRecordFoundException("No Company found with the given id " + id);
			else {
				EntityTransaction et = em.getTransaction();
				et.begin();
				b.setCourse_name(name);
				b.setNumber_of_seats(seat);
				b.setDuration(duration);
				em.persist(b);
				et.commit();
			}
		}catch(PersistenceException ex) {
			throw new SomeThingWentWrongException("Unable to process request, try again later");
		}finally{
			em.close();
		}
	}

	@Override
	public Batch findBatchById(int id) throws NoRecordFoundException,SomeThingWentWrongException {
		// TODO Auto-generated method stub
		Batch b = null;
		EntityManager em = null;
		try {
			em = EMUtils.getEntityManager();
			b = em.find(Batch.class, id);
			if(b == null)
				throw new NoRecordFoundException("No Company found with the given id " + id);
		}catch(PersistenceException ex) {
			throw new SomeThingWentWrongException("Unable to process request, try again later");
		}finally{
			em.close();
		}
		return b;
	}

	@Override
	public List<Batch> getBatchList()  throws SomeThingWentWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub
		EntityManager em = EMUtils.getEntityManager();
		List<Batch> bl = null;
		try {
			em.getTransaction().begin();
			Query query = em.createQuery("FROM Batch b");
			bl = (List<Batch>)query.getResultList();
			em.getTransaction().commit();
			if(bl.size() ==0) {
				throw new NoRecordFoundException("No Batch Found");
			}
		}catch(IllegalArgumentException ex) {
			throw new SomeThingWentWrongException("Unable to process request, try again later");
		}finally{
			em.close();
		}
		return bl;
		
	}

	@Override
	public void deleteBatch(int id) throws NoRecordFoundException,SomeThingWentWrongException{
		// TODO Auto-generated method stub
		EntityManager em = null;
		try {
			em = EMUtils.getEntityManager();
			Batch b = em.find(Batch.class, id);
			if(b == null)
				throw new NoRecordFoundException("No Company found with the given id " + id);
			else {
				EntityTransaction et = em.getTransaction();
				et.begin();
				em.remove(b);
				et.commit();
			}
		}catch(PersistenceException ex) {
			throw new SomeThingWentWrongException("Unable to process request, try again later");
		}finally{
			em.close();
		}
	}

	@Override
	public void assignBatchToFec(Batch b, Faculty f) {
		// TODO Auto-generated method stub
		EntityManager em = null;
		em = EMUtils.getEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		List<Batch> b1 = new ArrayList<>();
		b1.add(b);
		List<Faculty> f1 = new ArrayList<>();
		f1.add(f);
		f.setBatchList(b1);
		b.setFacultyList(f1);
		em.merge(f);
		em.merge(b);
		et.commit();
		em.close();
	}

}
