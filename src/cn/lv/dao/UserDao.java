package cn.lv.dao;

import java.util.List;

import cn.lv.bean.User;
import cn.lv.bean.UserQueryModel;

import com.smart.dao.IBaseDao;


///////
public interface UserDao extends IBaseDao<User, Integer> {
     
    List<User> query(int pn, int pageSize, UserQueryModel command);
    int countQuery(UserQueryModel command);

}
