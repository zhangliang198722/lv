package cn.lv.web.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smart.util.BeanUtil;
import com.smart.util.JsonUtil;
import com.smart.util.RequestUtil;

import cn.lv.bean.Breed;
import cn.lv.service.BreedService;
import cn.lv.service.JDBCService;

@Controller("breedController")
@RequestMapping(value = "/breed")
public class BreedController {
	
	@Autowired
	@Qualifier("BreedService")
	private BreedService breedService;
	
	@Autowired
	@Qualifier("jdbcService")
	private JDBCService jdbcService;
	
	private String outstr;
	public void setOutstr(String outstr) {
		this.outstr = outstr;
	}
	public String getOutstr() {
		return outstr;
	}

	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request) {
		return "breed/index";
	}
	@RequestMapping(value = "/add")
	public String add(ModelMap model, HttpServletRequest request,HttpServletResponse response) {
		Breed breed  = (Breed) BeanUtil.request2bean(Breed.class, request);
		if("modify".equals(request.getParameter("operator_action"))){
			int id = Integer.parseInt(request.getParameter("id"));
			breed.setId(id);
			breedService.update(breed);
		}else{
			breedService.save(breed);
		}
		outstr = "{success:" + true + "}";
		model.put("outstr", outstr);
		return "data";
	}
	@RequestMapping(value = "/toAdd")
	public String toAdd(HttpServletRequest request) {
		return "breed/add";
	}
	@RequestMapping(value = "/list")
	public String list(ModelMap model,HttpServletRequest request,HttpServletResponse response){
		
		Map<String, Object> params = RequestUtil.simpleRequestParameterMap(request);
		String sql = "select * from tbl_breed order by id desc";
		outstr =jdbcService.limitQuery(sql,params);
		model.put("outstr", outstr);
		return "data";
	}
	@RequestMapping(value = "/load")
	public String load(ModelMap model,HttpServletRequest request,HttpServletResponse response){
		Breed breed = breedService.get(Integer.parseInt(request.getParameter("id")));
		String breedJson = JsonUtil.bean2json(breed);
		outstr = "{\"success\":true,\"data\":"+breedJson+"}";
		model.put("outstr",outstr);
		return "data";
	}
	@RequestMapping(value="/delete")
	public String delete(ModelMap model,HttpServletRequest request,HttpServletResponse response){
		String ids = request.getParameter("ids");
		String sql = "delete from tbl_breed where id in ("+ids+")";
		jdbcService.update(sql);
		return "data";
	}
}
