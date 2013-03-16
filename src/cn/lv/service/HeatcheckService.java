package cn.lv.service;


import cn.lv.bean.Heatcheck;

import com.smart.page.Page;
import com.smart.service.IBaseService;

public interface HeatcheckService extends IBaseService<Heatcheck, Integer> {

	Page<Heatcheck> query(int pn, int pageSize, Heatcheck command);
}
