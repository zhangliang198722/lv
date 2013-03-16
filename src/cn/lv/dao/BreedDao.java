package cn.lv.dao;

import java.util.List;

import cn.lv.bean.Breed;
import cn.lv.bean.Donkey;
import cn.lv.bean.Heatcheck;
import cn.lv.bean.User;
import cn.lv.bean.UserQueryModel;

import com.smart.dao.IBaseDao;



public interface BreedDao extends IBaseDao<Breed, Integer> {
    
    List<Breed> query(int pn, int pageSize, Breed command);
    int countQuery(Breed command);

}
