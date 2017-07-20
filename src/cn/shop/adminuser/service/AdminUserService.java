package cn.shop.adminuser.service;

import org.springframework.transaction.annotation.Transactional;

import cn.shop.adminuser.dao.AdminUserDao;
import cn.shop.adminuser.vo.AdminUser;

/**
 * 后台管理业务层的代码
 * @author Administrator
 *
 */
@Transactional
public class AdminUserService {
	//注入后台管理dao
	private AdminUserDao adminUserDao;

	public void setAdminUserDao(AdminUserDao adminUserDao) {
		this.adminUserDao = adminUserDao;
	}

	//业务层后台登录方法
	public AdminUser login(AdminUser adminUser) {
		return adminUserDao.login(adminUser);
	}
	
}
