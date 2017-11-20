package com.fencer.modules.job.dao;

import com.fencer.modules.sys.dao.BaseDao;
import com.fencer.modules.job.entity.ScheduleJobEntity;

import java.util.Map;

/**
 * 定时任务
 * 
 * @author Fencer
 * @email 
 * @date 2017年11月1日 下午10:29:57
 */
public interface ScheduleJobDao extends BaseDao<ScheduleJobEntity> {
	
	/**
	 * 批量更新状态
	 */
	int updateBatch(Map<String, Object> map);
}
