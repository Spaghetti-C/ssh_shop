package cn.shop.adminuser.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.shop.adminuser.vo.AdminUser;

/**
 * 后台管理的持久层代码
 * @author Administrator
 *
 */
public class AdminUserDao extends HibernateDaoSupport {

	//持久层后台管理的方法
	public AdminUser login(AdminUser adminUser) {
		String hql = "from AdminUser where username = ? and password = ?";
		List<AdminUser> list = this.getHibernateTemplate().find(hql, adminUser.getUsername(), adminUser.getPassword());
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

}
