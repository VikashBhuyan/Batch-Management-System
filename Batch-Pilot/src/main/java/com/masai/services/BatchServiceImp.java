package com.masai.services;

import java.util.List;

import com.masai.dao.BatchDAO;
import com.masai.dao.BatchDAOImp;
import com.masai.entity.Batch;
import com.masai.entity.Faculty;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomeThingWentWrongException;

public class BatchServiceImp implements BatchService {

	@Override
	public void addBatch(Batch b) throws SomeThingWentWrongException {
		// TODO Auto-generated method stub
		BatchDAO bdao = new BatchDAOImp();
		bdao.addBatch(b);
	}

	@Override
	public void updateBatch(int id, String name, int seat, double duration)
			throws SomeThingWentWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub
		BatchDAO bdao = new BatchDAOImp();
		bdao.updateBatch(id, name, seat, duration);
	}

	@Override
	public Batch findBatchById(int id) throws NoRecordFoundException, SomeThingWentWrongException {
		// TODO Auto-generated method stub
		BatchDAO bdao = new BatchDAOImp();
		return bdao.findBatchById(id);
	}

	@Override
	public List<Batch> getBatchList() throws SomeThingWentWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub
		BatchDAO bdao = new BatchDAOImp();
		return bdao.getBatchList();
	}

	@Override
	public void deleteBatch(int id) throws NoRecordFoundException, SomeThingWentWrongException {
		// TODO Auto-generated method stub
		BatchDAO bdao = new BatchDAOImp();
		 bdao.deleteBatch(id);
	}

	@Override
	public void assignBatchToFec(Batch b, Faculty f) {
		// TODO Auto-generated method stub
		BatchDAO bdao = new BatchDAOImp();
		bdao.assignBatchToFec(b,f);
	}

}
