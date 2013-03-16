package cn.lv.service;


import cn.lv.bean.Breed;

import com.smart.page.Page;
import com.smart.service.IBaseService;

public interface BreedService extends IBaseService<Breed, Integer> {

	Page<Breed> query(int pn, int pageSize, Breed command);
}
