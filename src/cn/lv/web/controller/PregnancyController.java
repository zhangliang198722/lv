package cn.lv.web.controller;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smart.util.BeanUtil;
import com.smart.util.JsonUtil;
import com.smart.util.RequestUtil;

import cn.lv.bean.Pregnancy;
import cn.lv.service.JDBCService;
import cn.lv.service.PregnancyService;

@Controller("pregnancyController")
@RequestMapping(value = "/pregnancy")
public class PregnancyController {
	
	@Autowired
	@Qualifier("PregnancyService")
	private PregnancyService pregnancyService;
	
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
		return "pregnancy/index";
	}
	@RequestMapping(value = "/add")
	public String add(ModelMap model, HttpServletRequest request,HttpServletResponse response) {
		Pregnancy pregnancy  = (Pregnancy) BeanUtil.request2bean(Pregnancy.class, request);
		if("modify".equals(request.getParameter("operator_action"))){
			int id = Integer.parseInt(request.getParameter("id"));
			pregnancy.setId(id);
			pregnancyService.update(pregnancy);
		}else{
			pregnancyService.save(pregnancy);
		}
		outstr = "{success:" + true + "}";
		model.put("outstr", outstr);
		return "data";
	}
	@RequestMapping(value = "/toAdd")
	public String toAdd(HttpServletRequest request) {
		return "pregnancy/add";
	}
	@RequestMapping(value = "/list")
	public String list(ModelMap model,HttpServletRequest request,HttpServletResponse response){
		
		Map<String, Object> params = RequestUtil.simpleRequestParameterMap(request);
		String sql = "select * from tbl_pregnancy order by id desc";
		outstr =jdbcService.limitQuery(sql,params);
		model.put("outstr", outstr);
		return "data";
	}
	@RequestMapping(value = "/load")
	public String load(ModelMap model,HttpServletRequest request,HttpServletResponse response){
		Pregnancy pregnancy = pregnancyService.get(Integer.parseInt(request.getParameter("id")));
		String pregnancyJson = JsonUtil.bean2json(pregnancy);
		outstr = "{\"success\":true,\"data\":"+pregnancyJson+"}";
		model.put("outstr",outstr);
		return "data";
	}
	@RequestMapping(value="/delete")
	public String delete(ModelMap model,HttpServletRequest request,HttpServletResponse response){
		String ids = request.getParameter("ids");
		String sql = "delete from tbl_pregnancy where id in ("+ids+")";
		jdbcService.update(sql);
		return "data";
	}
}
