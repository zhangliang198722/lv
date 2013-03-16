package cn.lv.dao;

import java.util.List;

import cn.lv.bean.Breed;
import cn.lv.bean.Donkey;
import cn.lv.bean.Heatcheck;
import cn.lv.bean.Sire;
import cn.lv.bean.User;
import cn.lv.bean.UserQueryModel;

import com.smart.dao.IBaseDao;



public interface SireDao extends IBaseDao<Sire, Integer> {
    
    List<Sire> query(int pn, int pageSize, Sire command);
    int countQuery(Sire command);

}
