package com.fencer.modules.generator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.fencer.modules.generator.dao.Temp1Dao;
import com.fencer.modules.generator.entity.Temp1Entity;
import com.fencer.modules.generator.service.Temp1Service;



@Service("temp1Service")
public class Temp1ServiceImpl implements Temp1Service {
	@Autowired
	private Temp1Dao temp1Dao;
	
	@Override
	public Temp1Entity queryObject(Integer code){
		return temp1Dao.queryObject(code);
	}
	
	@Override
	public List<Temp1Entity> queryList(Map<String, Object> map){
		return temp1Dao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return temp1Dao.queryTotal(map);
	}
	
	@Override
	public void save(Temp1Entity temp1){
		temp1Dao.save(temp1);
	}
	
	@Override
	public void update(Temp1Entity temp1){
		temp1Dao.update(temp1);
	}
	
	@Override
	public void delete(Integer code){
		temp1Dao.delete(code);
	}
	
	@Override
	public void deleteBatch(Integer[] codes){
		temp1Dao.deleteBatch(codes);
	}
	
}
