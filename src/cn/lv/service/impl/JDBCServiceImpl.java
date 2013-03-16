package cn.lv.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.smart.util.JsonUtil;

import cn.lv.dao.JDBCDao;
import cn.lv.service.JDBCService;
@Service("jdbcService")
public class JDBCServiceImpl implements JDBCService {
	
	@Autowired
	@Qualifier("jdbcDao")
	private JDBCDao jdbcDao;
	
	public String limitQuery(String sql,Map<String, Object> params){
		int start = Integer.parseInt((String) params.get("start"));
		int limit = Integer.parseInt((String) params.get("limit"));
		String where = (String) params.get("where");
		List<Map<String,Object>> result = jdbcDao.queryLimit(sql, where, limit, start);
		String s = JsonUtil.list2json(result);
		int num = jdbcDao.queryLimit(sql, where, 100000, 0).size();
		return "{\"totalCount\":"+num+",\"data\":"+s.toLowerCase()+"}";
	}
	public int update(String sql){
		return jdbcDao.update(sql);
	}
}