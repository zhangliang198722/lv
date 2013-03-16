package cn.lv.service;

import cn.lv.bean.User;
import cn.lv.bean.UserQueryModel;

import com.smart.page.Page;
import com.smart.service.IBaseService;

public interface UserService extends IBaseService<User, Integer> {

	Page<User> query(int pn, int pageSize, UserQueryModel command);
}
