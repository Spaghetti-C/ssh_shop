package cn.shop.adminuser.service;

import org.springframework.transaction.annotation.Transactional;

import cn.shop.adminuser.dao.AdminUserDao;
import cn.shop.adminuser.vo.AdminUser;

/**
 * ��̨����ҵ���Ĵ���
 * @author Administrator
 *
 */
@Transactional
public class AdminUserService {
	//ע���̨����dao
	private AdminUserDao adminUserDao;

	public void setAdminUserDao(AdminUserDao adminUserDao) {
		this.adminUserDao = adminUserDao;
	}

	//ҵ����̨��¼����
	public AdminUser login(AdminUser adminUser) {
		return adminUserDao.login(adminUser);
	}
	
}
