package cn.lv.web.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.lv.bean.User;
import cn.lv.service.UserService;

@Controller("indexController")
public class IndexController {
	
	@Autowired
	@Qualifier("UserService")
	private UserService userService;

	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request) {
//		User user = new User();
//		user.setPassword("123445");
//		user.setUsername("khiker");
//		user.setEmail("khiker@qq.com");
//		user.setRegisterDate(new Date());
//		userService.save(user);
		return "index";
	}

}
