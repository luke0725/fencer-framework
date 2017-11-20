package com.fencer.modules.sys.dao;


import com.fencer.modules.sys.entity.SysConfigEntity;
import org.apache.ibatis.annotations.Param;

/**
 * 系统配置信息
 * 
 * @author Fencer
 * @email 
 * @date 2017年11月4日 下午6:46:16
 */
public interface SysConfigDao extends BaseDao<SysConfigEntity> {

	/**
	 * 根据key，查询value
	 */
	SysConfigEntity queryByKey(String paramKey);

	/**
	 * 根据key，更新value
	 */
	int updateValueByKey(@Param("key") String key, @Param("value") String value);
	
}
