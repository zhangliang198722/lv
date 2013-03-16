package cn.lv.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.lv.bean.User;
import cn.lv.bean.UserQueryModel;
import cn.lv.dao.UserDao;
import cn.lv.service.UserService;

import com.smart.dao.IBaseDao;
import com.smart.page.Page;
import com.smart.page.PageUtil;
import com.smart.service.impl.BaseService;

@Service("UserService")
public class UserServiceImpl extends BaseService<User, Integer> implements
		UserService {

	private UserDao userDao;

	@Autowired
	@Qualifier("UserDao")
	@Override
	public void setBaseDao(IBaseDao<User, Integer> userDao) {
		this.baseDao = userDao;
		this.userDao = (UserDao) userDao;
	}

	@Override
	public Page<User> query(int pn, int pageSize, UserQueryModel command) {
		return PageUtil.getPage(userDao.countQuery(command), pn,
				userDao.query(pn, pageSize, command), pageSize);
	}

}