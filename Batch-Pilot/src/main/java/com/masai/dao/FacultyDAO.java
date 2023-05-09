package com.masai.dao;

import java.util.List;

import com.masai.entity.Batch;
import com.masai.entity.Faculty;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomeThingWentWrongException;

public interface FacultyDAO {
	public void addFaculty(Faculty fac) throws SomeThingWentWrongException;
	public void login(String username, String password) throws SomeThingWentWrongException;
	public List<Batch> getBatchDetails() throws SomeThingWentWrongException;
	public void deleteAccount() throws SomeThingWentWrongException;
	public Faculty getFacultyById(int id) throws NoRecordFoundException,SomeThingWentWrongException;
	public List<Batch> getBatchByFaculty(int id) throws SomeThingWentWrongException;
}
