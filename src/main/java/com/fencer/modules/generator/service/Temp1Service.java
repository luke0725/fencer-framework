package com.fencer.modules.generator.service;

import com.fencer.modules.generator.entity.Temp1Entity;

import java.util.List;
import java.util.Map;

/**
 * 测试表
 * 
 * @author Fencer
 * @email 
 * @date 2017-11-20 17:35:28
 */
public interface Temp1Service {
	
	Temp1Entity queryObject(Integer code);
	
	List<Temp1Entity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(Temp1Entity temp1);
	
	void update(Temp1Entity temp1);
	
	void delete(Integer code);
	
	void deleteBatch(Integer[] codes);
}
