package cn.lv.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.lv.bean.Breed;
import cn.lv.bean.Pregnancy;
import cn.lv.dao.BreedDao;
import cn.lv.dao.PregnancyDao;
import cn.lv.service.BreedService;
import cn.lv.service.PregnancyService;

import com.smart.dao.IBaseDao;
import com.smart.page.Page;
import com.smart.page.PageUtil;
import com.smart.service.impl.BaseService;

@Service("PregnancyService")
public class PregnancyServiceImpl extends BaseService<Pregnancy, Integer> implements
	PregnancyService {

	private PregnancyDao pregnancyDao;
    

	@Autowired
	@Qualifier("PregnancyDao")
	@Override
	public void setBaseDao(IBaseDao<Pregnancy, Integer> pregnancyDao) {
		this.baseDao = pregnancyDao;
		this.pregnancyDao = (PregnancyDao) pregnancyDao;
	}

	@Override
	public Page<Pregnancy> query(int pn, int pageSize, Pregnancy command) {
		return PageUtil.getPage(pregnancyDao.countQuery(command), pn,
				pregnancyDao.query(pn, pageSize, command), pageSize);
	}
	
}