package cn.lv.dao.hibernate4;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.lv.bean.Breed;
import cn.lv.dao.BreedDao;

import com.smart.dao.impl.BaseHibernateDao;

@Repository("BreedDao")
public class BreedDaoImpl extends BaseHibernateDao<Breed, Integer> implements
		BreedDao {

	private static final String HQL_LIST = "from UserModel ";
	private static final String HQL_COUNT = "select count(*) from UserModel ";

	private static final String HQL_LIST_QUERY_CONDITION = " where username like ?";
	private static final String HQL_LIST_QUERY_ALL = HQL_LIST
			+ HQL_LIST_QUERY_CONDITION + "order by id desc";
	private static final String HQL_COUNT_QUERY_ALL = HQL_COUNT
			+ HQL_LIST_QUERY_CONDITION;

	@Override
	public List<Breed> query(int pn, int pageSize, Breed command) {
		return list(HQL_LIST_QUERY_ALL, pn, pageSize, getQueryParam(command));
	}

	@Override
	public int countQuery(Breed command) {
		return this.<Number> aggregate(HQL_COUNT_QUERY_ALL,
				getQueryParam(command)).intValue();
	}

	private Object[] getQueryParam(Breed command) {
		// TODO 改成全文索引
		String usernameLikeStr = "%" + command.getBreedDate() + "%";
		return new Object[] { usernameLikeStr };
	}

}
