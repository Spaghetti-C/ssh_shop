package cn.shop.user.dao;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


import java.util.List;
import cn.shop.user.vo.User;
import cn.shop.utils.PageHibernateCallback;

/**
 * 用户模块持久层代码
 * @author Administrator
 *
 */
public class UserDao extends HibernateDaoSupport {
	//按名称查询是否有该用户
	public User findByUsername(String username) {
		String hql = "from User where username = ?";
		List<User> list = this.getHibernateTemplate().find(hql, username);

		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	//注册用户存入数据库
	public void save(User user) {
		this.getHibernateTemplate().save(user);
		
	}

	//根据激活码查询用户
	public User findByCode(String code) {
		String hql = "from User where code = ?";
		List<User> list = this.getHibernateTemplate().find(hql, code);
		
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	//修改用户状态和激活码
	public void update(User existUser) {
		this.getHibernateTemplate().update(existUser);
	}

	//用户登录方法
	public User login(User user) {
		String hql = "from User where username = ? and password = ? and state = ?";
		List<User> list = this.getHibernateTemplate().find(hql, user.getUsername(), user.getPassword(), 1);
		
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	//查询所有用户个数
	public int findCount() {
		String hql = "select count(*) from User";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	//查询用户
	public List<User> findByPage(int begin, int limit) {
		String hql = "from User";
		List<User> list = this.getHibernateTemplate().execute(new PageHibernateCallback<User>(hql, null, begin, limit));
		return list;
	}

	//根据用户id查询用户
	public User findByUid(Integer uid) {
		return this.getHibernateTemplate().get(User.class, uid);
	}

	//删除用户
	public void delete(User existUser) {
		this.getHibernateTemplate().delete(existUser);
	}
}
