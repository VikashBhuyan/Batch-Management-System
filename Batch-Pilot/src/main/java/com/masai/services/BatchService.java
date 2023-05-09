package com.masai.services;

import java.util.List;

import com.masai.entity.Batch;
import com.masai.entity.Faculty;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomeThingWentWrongException;

public interface BatchService {
	void addBatch(Batch b) throws SomeThingWentWrongException; 
	void updateBatch(int id,String name,int seat,double duration) throws SomeThingWentWrongException, NoRecordFoundException;
	Batch findBatchById(int id) throws NoRecordFoundException,SomeThingWentWrongException;
	List<Batch> getBatchList() throws SomeThingWentWrongException, NoRecordFoundException;
	void deleteBatch(int id) throws NoRecordFoundException,SomeThingWentWrongException;
	void assignBatchToFec(Batch b, Faculty f); 
}
