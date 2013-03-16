package cn.lv.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.lv.bean.Breed;
import cn.lv.bean.Sire;
import cn.lv.dao.BreedDao;
import cn.lv.dao.SireDao;
import cn.lv.service.BreedService;
import cn.lv.service.SireService;

import com.smart.dao.IBaseDao;
import com.smart.page.Page;
import com.smart.page.PageUtil;
import com.smart.service.impl.BaseService;

@Service("SireService")
public class SireServiceImpl extends BaseService<Sire, Integer> implements
	SireService {

	private SireDao sireDao;
    

	@Autowired
	@Qualifier("SireDao")
	@Override
	public void setBaseDao(IBaseDao<Sire, Integer> sireDao) {
		this.baseDao = sireDao;
		this.sireDao = (SireDao) sireDao;
	}

	@Override
	public Page<Sire> query(int pn, int pageSize, Sire command) {
		return PageUtil.getPage(sireDao.countQuery(command), pn,
				sireDao.query(pn, pageSize, command), pageSize);
	}
	
}