package cn.lv.service;

import java.util.Map;

public interface JDBCService {
	public String limitQuery(String sql,Map<String, Object> params);
	public int update(String sql);

}
