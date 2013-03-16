package cn.lv.dao;

import java.util.List;

import cn.lv.bean.Breed;
import cn.lv.bean.Donkey;
import cn.lv.bean.Heatcheck;
import cn.lv.bean.Pregnancy;
import cn.lv.bean.User;
import cn.lv.bean.UserQueryModel;

import com.smart.dao.IBaseDao;



public interface PregnancyDao extends IBaseDao<Pregnancy, Integer> {
    
    List<Pregnancy> query(int pn, int pageSize, Pregnancy command);
    int countQuery(Pregnancy command);

}
