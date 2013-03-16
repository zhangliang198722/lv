package cn.lv.dao;

import java.util.List;

import cn.lv.bean.Donkey;
import cn.lv.bean.Heatcheck;
import cn.lv.bean.User;
import cn.lv.bean.UserQueryModel;

import com.smart.dao.IBaseDao;



public interface HeatcheckDao extends IBaseDao<Heatcheck, Integer> {
    
    List<Heatcheck> query(int pn, int pageSize, Heatcheck command);
    int countQuery(Heatcheck command);

}
