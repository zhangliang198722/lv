package com.smart.dao;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

public interface BaseDao {

	final static String ROLE = BaseDao.class.getName();

	List<Map<String, Object>> query(String sql, String where);

	List<?> query(String sql, RowMapper<?> rowMapper, Object... params);

	List<?> query(String sql, RowMapper<?> rowMapper);

	List<?> queryForList(String sql, Map<String, Object> params,
			RowMapper<?> rowMapper);

	List<?> queryForList(String sql, String where, Map<String, Object> params,
			RowMapper<?> rowMapper);

	int update(String sql);

	int update(String sql, Object... params);

	int update(String sql, Map<String, Object> params);

	int queryForInt(String sql);

	int queryForInt(String sql, String where);

	int queryForInt(String sql, Object... params);

	int queryForInt(String sql, Map<String, Object> params);
	long queryForLong(String sql);

	long queryForLong(String sql, Object... params);

	long queryForLong(String sql, Map<String, Object> params);

	Map<String, Object> queryForMap(String sql, Object... params);

	Map<String, Object> queryForMap(String sql);

	Map<String, Object> queryForMap(String sql, Map<String, Object> params);

	List<Map<String, Object>> queryForList(String sql);

	List<Map<String, Object>> queryForList(String sql, Object... param);

	List<Map<String, Object>> queryForList(String sql,
			Map<String, Object> params);

	List<Map<String, Object>> queryForList(String sql, String where,
			Map<String, Object> params);

	Object queryForObject(String sql);

	Object queryForObject(String sql, Map<String, Object> params);

	long incrementNext(String table, String column);

	Object sequencesNext(String sequences);

	List<Map<String, Object>> queryLimit(String sql, String where, int limit,
			int start);

	List<?> queryLimit(String sql, RowMapper<?> rowMapper, int limit,
			int start, Object... params);

	List<?> queryLimit(String sql, RowMapper<?> rowMapper, int limit, int start);

	List<Map<String, Object>> queryLimit(String sql, int limit, int start);

	List<Map<String, Object>> queryLimit(String sql, int limit, int start,
			Object... param);

	List<Map<String, Object>> queryLimit(String sql,
			Map<String, Object> params, int limit, int start);

	List<Map<String, Object>> queryLimit(String sql, String where,
			Map<String, Object> params, int limit, int start);
}
