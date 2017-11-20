package com.fencer.modules.gen.controller;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fencer.common.utils.PageUtils;
import com.fencer.common.utils.Query;
import com.fencer.common.utils.R;
import com.fencer.common.xss.XssHttpServletRequestWrapper;
import com.fencer.modules.gen.entity.TableEntity;
import com.fencer.modules.gen.service.SysGeneratorService;
import com.fencer.modules.gen.utils.GenUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * 代码生成器
 * 
 * @author Fencer
 * @email 
 * @date 2017年11月19日 下午9:12:58
 */
@Controller
@RequestMapping("/sys/generator")
public class SysGeneratorController {
	@Autowired
	private SysGeneratorService sysGeneratorService;
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("sys:generator:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<Map<String, Object>> list = sysGeneratorService.queryList(query);
		Configuration config = GenUtils.getConfig();
		for(Map<String, Object> map:list){
			map.put("packageName", config.getString("package"));
		}
		int total = sysGeneratorService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 生成代码
	 */
	@RequestMapping("/code")
	@RequiresPermissions("sys:generator:code")
	public void code(HttpServletRequest request, HttpServletResponse response) throws IOException{
		//获取表名，不进行xss过滤
		HttpServletRequest orgRequest = XssHttpServletRequestWrapper.getOrgRequest(request);
		String tables = orgRequest.getParameter("tables");
		Type type = new TypeToken<List<TableEntity>>() { }.getType(); 
		
		//String[] tableNames = new Gson().fromJson(tables, type);
		List<TableEntity> entityList = new Gson().fromJson(tables, type);
		
		byte[] data = sysGeneratorService.generatorCode(entityList);
		
		response.reset();  
        response.setHeader("Content-Disposition", "attachment; filename=\"fencer.zip\"");  
        response.addHeader("Content-Length", "" + data.length);  
        response.setContentType("application/octet-stream; charset=UTF-8");  
  
        IOUtils.write(data, response.getOutputStream());  
	}
}
