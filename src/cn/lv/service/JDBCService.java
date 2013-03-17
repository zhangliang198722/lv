package cn.lv.service;

import java.util.List;
import java.util.Map;

public interface JDBCService {
	public String limitQuery(String sql,Map<String, Object> params);
	public int update(String sql);
	public  List<Map<String, Object>> resultQuery(String sql,Map<String,Object> params);

}
