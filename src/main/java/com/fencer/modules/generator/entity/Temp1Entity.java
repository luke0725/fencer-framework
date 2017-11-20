package com.fencer.modules.generator.entity;

import java.io.Serializable;
import java.util.Date;
import java.sql.Blob;


import org.hibernate.validator.constraints.NotBlank;
import com.fencer.common.validator.group.AddGroup;
import com.fencer.common.validator.group.UpdateGroup;


/**
 * 测试表
 * 
 * @author Fencer
 * @email 
 * @date 2017-11-20 17:35:28
 */
public class Temp1Entity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编码
	private Integer code;
	//名称
	@NotBlank(message="名称不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String name;
	//大对象
	private byte[] jobData;

	/**
	 * 设置：编码
	 */
	public void setCode(Integer code) {
		this.code = code;
	}
	/**
	 * 获取：编码
	 */
	public Integer getCode() {
		return code;
	}
	/**
	 * 设置：名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：大对象
	 */
	public void setJobData(byte[] jobData) {
		this.jobData = jobData;
	}
	/**
	 * 获取：大对象
	 */
	public byte[] getJobData() {
		return jobData;
	}
}
