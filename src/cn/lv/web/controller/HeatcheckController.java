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

import cn.lv.bean.Donkey;
import cn.lv.bean.Heatcheck;
import cn.lv.service.DonkeyService;
import cn.lv.service.HeatcheckService;
import cn.lv.service.JDBCService;

@Controller("heatcheckController")
@RequestMapping(value = "/heatcheck")
public class HeatcheckController {
	
	@Autowired
	@Qualifier("HeatcheckService")
	private HeatcheckService heatcheckService;
	
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
		return "heatcheck/index";
	}
	@RequestMapping(value = "/add")
	public String add(ModelMap model, HttpServletRequest request,HttpServletResponse response) {
		Heatcheck heatcheck  = (Heatcheck) BeanUtil.request2bean(Heatcheck.class, request);
		if("modify".equals(request.getParameter("operator_action"))){
			int id = Integer.parseInt(request.getParameter("id"));
			heatcheck.setId(id);
			heatcheckService.update(heatcheck);
		}else{
			heatcheckService.save(heatcheck);
		}
		outstr = "{success:" + true + "}";
		model.put("outstr", outstr);
		return "data";
	}
	@RequestMapping(value = "/toAdd")
	public String toAdd(HttpServletRequest request) {
		return "heatcheck/add";
	}
	@RequestMapping(value = "/list")
	public String list(ModelMap model,HttpServletRequest request,HttpServletResponse response){
		
		Map<String, Object> params = RequestUtil.simpleRequestParameterMap(request);
		String sql = "select * from tbl_heatcheck order by id desc";
		outstr =jdbcService.limitQuery(sql,params);
		model.put("outstr", outstr);
		return "data";
	}
	@RequestMapping(value = "/load")
	public String load(ModelMap model,HttpServletRequest request,HttpServletResponse response){
		Heatcheck heatcheck = heatcheckService.get(Integer.parseInt(request.getParameter("id")));
		String heatcheckJson = JsonUtil.bean2json(heatcheck);
		outstr = "{\"success\":true,\"data\":"+heatcheckJson+"}";
		System.out.println(outstr);
		model.put("outstr",outstr);
		return "data";
	}
	@RequestMapping(value="/delete")
	public String delete(ModelMap model,HttpServletRequest request,HttpServletResponse response){
		String ids = request.getParameter("ids");
		String sql = "delete from tbl_heatcheck where id in ("+ids+")";
		jdbcService.update(sql);
		return "data";
	}
}
