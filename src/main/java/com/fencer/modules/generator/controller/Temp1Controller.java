package com.fencer.modules.generator.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fencer.modules.generator.entity.Temp1Entity;
import com.fencer.modules.generator.service.Temp1Service;
import com.fencer.common.utils.PageUtils;
import com.fencer.common.utils.Query;
import com.fencer.common.utils.R;
import com.fencer.common.validator.ValidatorUtils;
import com.fencer.common.validator.group.AddGroup;
import com.fencer.common.validator.group.UpdateGroup;


/**
 * 测试表
 * 
 * @author Fencer
 * @email 
 * @date 2017-11-20 17:35:28
 */
@RestController
@RequestMapping("temp1")
public class Temp1Controller {
	@Autowired
	private Temp1Service temp1Service;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("temp1:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<Temp1Entity> temp1List = temp1Service.queryList(query);
		int total = temp1Service.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(temp1List, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{code}")
	@RequiresPermissions("temp1:info")
	public R info(@PathVariable("code") Integer code){
		Temp1Entity temp1 = temp1Service.queryObject(code);
		
		return R.ok().put("temp1", temp1);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("temp1:save")
	public R save(@RequestBody Temp1Entity temp1){
	    ValidatorUtils.validateEntity(temp1, AddGroup.class);
	    
		temp1Service.save(temp1);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("temp1:update")
	public R update(@RequestBody Temp1Entity temp1){
		ValidatorUtils.validateEntity(temp1, UpdateGroup.class);
	
		temp1Service.update(temp1);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("temp1:delete")
	public R delete(@RequestBody Integer[] codes){
		temp1Service.deleteBatch(codes);
		
		return R.ok();
	}
	
}
