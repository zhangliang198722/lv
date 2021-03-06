package cn.lv.dao.hibernate4;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.lv.bean.User;
import cn.lv.bean.UserQueryModel;
import cn.lv.dao.UserDao;

import com.smart.dao.impl.BaseHibernateDao;

@Repository("UserDao")
public class UserDaoImpl extends BaseHibernateDao<User, Integer> implements
		UserDao {

	private static final String HQL_LIST = "from UserModel ";
	private static final String HQL_COUNT = "select count(*) from UserModel ";

	private static final String HQL_LIST_QUERY_CONDITION = " where username like ?";
	private static final String HQL_LIST_QUERY_ALL = HQL_LIST
			+ HQL_LIST_QUERY_CONDITION + "order by id desc";
	private static final String HQL_COUNT_QUERY_ALL = HQL_COUNT
			+ HQL_LIST_QUERY_CONDITION;

	@Override
	public List<User> query(int pn, int pageSize, UserQueryModel command) {
		return list(HQL_LIST_QUERY_ALL, pn, pageSize, getQueryParam(command));
	}

	@Override
	public int countQuery(UserQueryModel command) {
		return this.<Number> aggregate(HQL_COUNT_QUERY_ALL,
				getQueryParam(command)).intValue();
	}

	private Object[] getQueryParam(UserQueryModel command) {
		// TODO 改成全文索引
		String usernameLikeStr = "%" + command.getUsername() + "%";
		return new Object[] { usernameLikeStr };
	}

}
