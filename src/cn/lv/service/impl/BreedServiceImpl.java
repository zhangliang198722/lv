package cn.lv.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.lv.bean.Breed;
import cn.lv.dao.BreedDao;
import cn.lv.service.BreedService;

import com.smart.dao.IBaseDao;
import com.smart.page.Page;
import com.smart.page.PageUtil;
import com.smart.service.impl.BaseService;

@Service("BreedService")
public class BreedServiceImpl extends BaseService<Breed, Integer> implements
		BreedService {

	private BreedDao breedDao;
    

	@Autowired
	@Qualifier("BreedDao")
	@Override
	public void setBaseDao(IBaseDao<Breed, Integer> breedDao) {
		this.baseDao = breedDao;
		this.breedDao = (BreedDao) breedDao;
	}

	@Override
	public Page<Breed> query(int pn, int pageSize, Breed command) {
		return PageUtil.getPage(breedDao.countQuery(command), pn,
				breedDao.query(pn, pageSize, command), pageSize);
	}
	
}