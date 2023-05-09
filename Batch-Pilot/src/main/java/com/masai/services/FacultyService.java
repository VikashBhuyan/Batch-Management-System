package com.masai.services;

import com.masai.entity.Batch;
import com.masai.exception.SomeThingWentWrongException;

public interface FacultyService {
	void addBatch(Batch b) throws SomeThingWentWrongException; 
}
