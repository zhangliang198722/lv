package cn.lv.service;


import cn.lv.bean.Sire;

import com.smart.page.Page;
import com.smart.service.IBaseService;

public interface SireService extends IBaseService<Sire, Integer> {

	Page<Sire> query(int pn, int pageSize, Sire command);
}
