package cn.lv.dao;

import java.util.List;

import cn.lv.bean.Donkey;
import cn.lv.bean.User;
import cn.lv.bean.UserQueryModel;

import com.smart.dao.IBaseDao;



public interface DonkeyDao extends IBaseDao<Donkey, Integer> {
    
    List<Donkey> query(int pn, int pageSize, Donkey command);
    int countQuery(Donkey command);

}
