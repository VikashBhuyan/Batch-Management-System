package com.masai.dao;

import java.util.List;

import com.masai.entity.Batch;
import com.masai.entity.Faculty;
import com.masai.entity.LoggedInUserId;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomeThingWentWrongException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;

public class FacultyDAOImp implements FacultyDAO{

	@Override
	public void login(String username, String password) throws SomeThingWentWrongException {
		// TODO Auto-generated method stub
		EntityManager em = EMUtils.getEntityManager();;
		try {
			
			
			Query query = em.createQuery("SELECT f.id FROM Faculty f WHERE username = :username AND pass = :password");
			query.setParameter("username", username);
			query.setParameter("password", password);
			List<Integer> listInt = (List<Integer>)query.getResultList();
			if(listInt.size() == 0) {
				//you are here means company with given name exists so throw exceptions
				throw new SomeThingWentWrongException("The username or password is incorrect");
			}
			LoggedInUserId.loggedInUserId = listInt.get(0);
//			System.out.println(LoggedInUserId.loggedInUserId);
		}catch(PersistenceException ex) {
			throw new SomeThingWentWrongException("Unable to process request, try again later");
		}finally{
			em.close();
		}
	}

	@Override
	public List<Batch> getBatchDetails() throws SomeThingWentWrongException {
		// TODO Auto-generated method stub
		List<Batch> li = null;
		EntityManager em = EMUtils.getEntityManager();
		try {
			
			//find the details of logged in customer
			em.getTransaction().begin();
			Faculty fac = em.find(Faculty.class, LoggedInUserId.loggedInUserId);
//			System.out.println();
			fac.getBatchList().size();
			li = fac.getBatchList();
//			System.out.println(li);
			if(li==null)
			{
				throw new SomeThingWentWrongException("No batch has been assigned yet");
			}
			em.getTransaction().commit();
		}catch(PersistenceException ex) {
			ex.printStackTrace();
			throw new SomeThingWentWrongException("Unable to process request, try again later");
		}finally{
			
			em.close();
		}
		return li;
	}
	
	public void deleteAccount() throws SomeThingWentWrongException{
		EntityManager em = null;
		try {
			em = EMUtils.getEntityManager();
			Faculty f = em.find(Faculty.class, LoggedInUserId.loggedInUserId);
			EntityTransaction et = em.getTransaction();
			et.begin();
			em.remove(f);
			et.commit();
		}catch(PersistenceException ex) {
			throw new SomeThingWentWrongException("Unable to process request, try again later");
		}finally{
			em.close();
		}
	}

	@Override
	public List<Batch> getBatchByFaculty(int id) throws SomeThingWentWrongException{
		// TODO Auto-generated method stub
		List<Batch> li = null;
		EntityManager em = EMUtils.getEntityManager();
		try {
			
			//find the details of logged in customer
			Faculty fac = em.find(Faculty.class, id);
			fac.getBatchList().size();
			li = fac.getBatchList();
			
			if(li==null)
			{
				throw new SomeThingWentWrongException("No batch has been assigned yet");
			}
		}catch(PersistenceException ex) {
			ex.printStackTrace();
			throw new SomeThingWentWrongException("Unable to process request, try again later");
		}finally{
			em.close();
		}
		return li;
		
	}

	@Override
	public void addFaculty(Faculty fac) throws SomeThingWentWrongException {
		// TODO Auto-generated method stub
		EntityManager em = null;
		try {
			em = EMUtils.getEntityManager();
			
			//check if company with same username exists
			Query query = em.createQuery("SELECT count(c) FROM Faculty c WHERE username = :username");
			query.setParameter("username", fac.getUsername());
			if((Long)query.getSingleResult() > 0) {
				//you are here means company with given name exists so throw exceptions
				throw new SomeThingWentWrongException("The username" + fac.getUsername() + " is already occupied");
			}
			
			//you are here means no company with given name
			EntityTransaction et = em.getTransaction();
			et.begin();
			em.persist(fac);
			et.commit();
		}catch(PersistenceException ex) {
			throw new SomeThingWentWrongException("Unable to process request, try again later");
		}finally{
			em.close();
		}
	}

	@Override
	public Faculty getFacultyById(int id) throws NoRecordFoundException,SomeThingWentWrongException{
		// TODO Auto-generated method stub
		EntityManager em = null;
		Faculty f = null;
		try {
			em = EMUtils.getEntityManager();
			f = em.find(Faculty.class, id);
			if(f == null)
				throw new NoRecordFoundException("No Faculty found with the given id " + id);
		}catch(PersistenceException ex) {
			throw new SomeThingWentWrongException("Unable to process request, try again later");
		}finally{
			em.close();
		}
//		return b;
		return f;
	}
}
