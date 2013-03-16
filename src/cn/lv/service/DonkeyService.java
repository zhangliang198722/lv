package cn.lv.service;


import cn.lv.bean.Donkey;

import com.smart.page.Page;
import com.smart.service.IBaseService;

public interface DonkeyService extends IBaseService<Donkey, Integer> {

	Page<Donkey> query(int pn, int pageSize, Donkey command);
}
