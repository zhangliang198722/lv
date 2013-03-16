package cn.lv.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.lv.bean.Heatcheck;
import cn.lv.dao.HeatcheckDao;
import cn.lv.service.HeatcheckService;

import com.smart.dao.IBaseDao;
import com.smart.page.Page;
import com.smart.page.PageUtil;
import com.smart.service.impl.BaseService;
import com.smart.util.JsonUtil;

@Service("HeatcheckService")
public class HeatcheckServiceImpl extends BaseService<Heatcheck, Integer> implements
		HeatcheckService {

	private HeatcheckDao HeatcheckDao;
    

	@Autowired
	@Qualifier("HeatcheckDao")
	@Override
	public void setBaseDao(IBaseDao<Heatcheck, Integer> HeatcheckDao) {
		this.baseDao = HeatcheckDao;
		this.HeatcheckDao = (HeatcheckDao) HeatcheckDao;
	}

	@Override
	public Page<Heatcheck> query(int pn, int pageSize, Heatcheck command) {
		return PageUtil.getPage(HeatcheckDao.countQuery(command), pn,
				HeatcheckDao.query(pn, pageSize, command), pageSize);
	}
	
}