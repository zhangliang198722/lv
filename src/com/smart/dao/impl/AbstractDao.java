package com.smart.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.smart.dao.BaseDao;


public abstract class AbstractDao implements BaseDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	// 取得 order 位置的表达式
	private static final Pattern ORDER_PATTERN = Pattern.compile(
			"\\s(order)(\\s)+by\\s", Pattern.CASE_INSENSITIVE);
	// 取得 group 位置的表达式
	private static final Pattern GROUP_PATTERN = Pattern.compile(
			"\\s(group)(\\s)+by\\s", Pattern.CASE_INSENSITIVE);

	public List<Map<String, Object>> query(String sql, String where) {
		return jdbcTemplate.queryForList(appendCondition(sql, where));
	}

	public List<?> query(String sql, RowMapper<?> rowMapper, Object... params) {
		return jdbcTemplate.query(sql, params, rowMapper);
	}

	public List<?> query(String sql, RowMapper<?> rowMapper) {
		return jdbcTemplate.query(sql, rowMapper);
	}

	public List<?> queryForList(String sql, Map<String, Object> params,
			RowMapper<?> rowMapper) {
		return null;
	}

	public List<?> queryForList(String sql, String where,
			Map<String, Object> params, RowMapper<?> rowMapper) {
		return null;
	}

	public int update(String sql, Map<String, Object> params) {
		return namedParameterJdbcTemplate.update(sql, params);
	}

	public int update(String sql, Object... args) {
		return jdbcTemplate.update(sql, args);
	}

	public int update(String sql) {
		return update(sql, new HashMap<String, Object>());
	}

	public int queryForInt(String sql, Map<String, Object> params) {
		return namedParameterJdbcTemplate.queryForInt(sql, params);
	}

	public int queryForInt(String sql, Object... params) {
		return jdbcTemplate.queryForInt(sql, params);
	}

	public int queryForInt(String sql) {
		return jdbcTemplate.queryForInt(sql);
	}
	
	public int queryForInt(String sql,String where) {
		return jdbcTemplate.queryForInt(appendCondition(sql, where));
	}
	public long queryForLong(String sql, Map<String, Object> params) {
		return namedParameterJdbcTemplate.queryForLong(sql, params);
	}

	public long queryForLong(String sql, Object... params) {
		return jdbcTemplate.queryForLong(sql, params);
	}

	public long queryForLong(String sql) {
		return jdbcTemplate.queryForLong(sql);
	}

	public Map<String, Object> queryForMap(String sql, Object... params) {
		return jdbcTemplate.queryForMap(sql, params);
	}

	public Map<String, Object> queryForMap(String sql,
			Map<String, Object> params) {
		return namedParameterJdbcTemplate.queryForMap(sql, params);
	}

	public Map<String, Object> queryForMap(String sql) {
		return jdbcTemplate.queryForMap(sql);
	}

	public List<Map<String, Object>> queryForList(String sql, Object... param) {
		return jdbcTemplate.queryForList(sql, param);
	}

	public List<Map<String, Object>> queryForList(String sql,
			Map<String, Object> params) {
		return namedParameterJdbcTemplate.queryForList(sql, params);
	}

	public List<Map<String, Object>> queryForList(String sql, String where,
			Map<String, Object> params) {
		return namedParameterJdbcTemplate.queryForList(
				appendCondition(sql, where), params);
	}

	public List<Map<String, Object>> queryForList(String sql) {
		return jdbcTemplate.queryForList(sql);
	}

	public Object queryForObject(String sql, Map<String, Object> params) {
		return namedParameterJdbcTemplate.queryForObject(sql, params,
				Object.class);
	}

	public Object queryForObject(String sql) {
		return jdbcTemplate.queryForObject(sql, Object.class);
	}

	public long incrementNext(String table, String column) {
		return this.queryForLong("SELECT max(" + column + ")+1 FROM " + table);
	}

	public Object sequencesNext(String sequences) {
		return this.queryForMap(
				"select " + sequences + ".nextval as sequences from dual").get(
				"sequences");
	}

	public List<Map<String, Object>> queryLimit(String sql, String where,
			int limit, int start) {
		return jdbcTemplate.queryForList(getLimitString(
				appendCondition(sql, where), limit, start));
	}

	public List<?> queryLimit(String sql, RowMapper<?> rowMapper, int limit,
			int start, Object... params) {
		return jdbcTemplate.query(getLimitString(sql, limit, start), rowMapper,
				params);
	}

	public List<?> queryLimit(String sql, RowMapper<?> rowMapper, int limit,
			int start) {
		return jdbcTemplate.query(getLimitString(sql, limit, start), rowMapper);
	}

	public List<Map<String, Object>> queryLimit(String sql, int limit,
			int start) {
		return jdbcTemplate.queryForList(getLimitString(sql, limit, start));
	}

	public List<Map<String, Object>> queryLimit(String sql, int limit,
			int start, Object... param) {
		return jdbcTemplate.queryForList(getLimitString(sql, limit, start),
				param);
	}

	public List<Map<String, Object>> queryLimit(String sql,
			Map<String, Object> params, int limit, int start) {
		return namedParameterJdbcTemplate.queryForList(
				getLimitString(sql, limit, start), params);
	}

	public List<Map<String, Object>> queryLimit(String sql,
			String where, Map<String, Object> params, int limit, int start) {
		return namedParameterJdbcTemplate.queryForList(
				getLimitString(appendCondition(sql, where), limit, start),
				params);
	}

	private String appendCondition(final String sql, final String condition) {
		if (condition == null || condition.length() == 0) {
			return sql;
		}
		// 取得order by 的位置
		Matcher oMatcher = ORDER_PATTERN.matcher(sql);
		Matcher gMatcher = GROUP_PATTERN.matcher(sql);
		int oLoc = gMatcher.find() ? gMatcher.start(1)
				: (oMatcher.find() ? oMatcher.start(1) : sql.length());
		StringBuilder str = new StringBuilder(sql);
		String cond = null;
		if (str.lastIndexOf("where") == -1) {
			cond = "where (" + condition + ") ";
		} else {
			cond = "and (" + condition + ") ";
		}
		if (oLoc == sql.length()) {
			cond = " " + cond;
		}
		str.insert(oLoc, cond);
		return str.toString();
	}

	private String getLimitString(String sql, int limit, int start) {
		StringBuffer pagingSelect = new StringBuffer(sql.length() + 100);
		pagingSelect
				.append("SELECT * FROM ( SELECT row_.*, rownum rownum_ FROM ( ");
		pagingSelect.append(sql);
		pagingSelect.append(" ) row_ ) WHERE rownum_ <= " + (limit + start)
				+ " AND rownum_ > " + start);
		return pagingSelect.toString();
	}
}
