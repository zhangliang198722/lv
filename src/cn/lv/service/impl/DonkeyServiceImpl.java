package cn.lv.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.lv.bean.Donkey;
import cn.lv.dao.DonkeyDao;
import cn.lv.service.DonkeyService;

import com.smart.dao.IBaseDao;
import com.smart.page.Page;
import com.smart.page.PageUtil;
import com.smart.service.impl.BaseService;
import com.smart.util.JsonUtil;

@Service("DonkeyService")
public class DonkeyServiceImpl extends BaseService<Donkey, Integer> implements
		DonkeyService {

	private DonkeyDao donkeyDao;
    

	@Autowired
	@Qualifier("DonkeyDao")
	@Override
	public void setBaseDao(IBaseDao<Donkey, Integer> donkeyDao) {
		this.baseDao = donkeyDao;
		this.donkeyDao = (DonkeyDao) donkeyDao;
	}

	@Override
	public Page<Donkey> query(int pn, int pageSize, Donkey command) {
		return PageUtil.getPage(donkeyDao.countQuery(command), pn,
				donkeyDao.query(pn, pageSize, command), pageSize);
	}
	
}