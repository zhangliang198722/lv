package cn.lv.dao;

import java.util.List;
import java.util.Map;

public interface JDBCDao {
	public List<Map<String, Object>> queryForList(String sql) ;
	public List<Map<String, Object>> queryLimit(String sql, String where,int limit, int start);
	public int update(String sql);
}
