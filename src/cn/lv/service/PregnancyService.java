package cn.lv.service;


import cn.lv.bean.Pregnancy;

import com.smart.page.Page;
import com.smart.service.IBaseService;

public interface PregnancyService extends IBaseService<Pregnancy, Integer> {

	Page<Pregnancy> query(int pn, int pageSize, Pregnancy command);
}
