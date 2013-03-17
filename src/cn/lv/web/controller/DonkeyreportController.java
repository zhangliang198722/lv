package cn.lv.web.controller;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebParam.Mode;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smart.util.RequestUtil;

import cn.lv.bean.Donkey;
import cn.lv.service.JDBCService;

@Controller("donkeyreportController")
@RequestMapping(value = "/donkeyreport")
public class DonkeyreportController {
	
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
		return "donkeyreport/index";
	}
	
	@RequestMapping(value = "/toList")
	public String toList(ModelMap model,HttpServletRequest request,HttpServletResponse response) {
		model.put("donkeyList", null);
		return "donkeyreport/result";
	}
	
	@RequestMapping(value = "/result")
	public String result(ModelMap model,HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> params = RequestUtil.simpleRequestParameterMap(request);
		String sql = "select * from tbl_donkey order by id desc";
		List<Map<String, Object>>  result =jdbcService.resultQuery(sql, params);
		model.put("donkeyList", result);
		return "donkeyreport/result";
	}
	
}
