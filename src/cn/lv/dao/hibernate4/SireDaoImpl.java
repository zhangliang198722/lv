package cn.lv.dao.hibernate4;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.lv.bean.Breed;
import cn.lv.bean.Sire;
import cn.lv.dao.BreedDao;
import cn.lv.dao.SireDao;

import com.smart.dao.impl.BaseHibernateDao;

@Repository("SireDao")
public class SireDaoImpl extends BaseHibernateDao<Sire, Integer> implements
	SireDao {

	private static final String HQL_LIST = "from UserModel ";
	private static final String HQL_COUNT = "select count(*) from UserModel ";

	private static final String HQL_LIST_QUERY_CONDITION = " where username like ?";
	private static final String HQL_LIST_QUERY_ALL = HQL_LIST
			+ HQL_LIST_QUERY_CONDITION + "order by id desc";
	private static final String HQL_COUNT_QUERY_ALL = HQL_COUNT
			+ HQL_LIST_QUERY_CONDITION;

	@Override
	public List<Sire> query(int pn, int pageSize, Sire command) {
		return list(HQL_LIST_QUERY_ALL, pn, pageSize, getQueryParam(command));
	}

	@Override
	public int countQuery(Sire command) {
		return this.<Number> aggregate(HQL_COUNT_QUERY_ALL,
				getQueryParam(command)).intValue();
	}

	private Object[] getQueryParam(Sire command) {
		// TODO 改成全文索引
		String usernameLikeStr = "%" + command.getBreedId() + "%";
		return new Object[] { usernameLikeStr };
	}

}
