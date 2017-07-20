package cn.shop.user.service;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.shop.user.dao.UserDao;
import cn.shop.user.vo.User;
import cn.shop.utils.MailUtils;
import cn.shop.utils.PageBean;
import cn.shop.utils.UUIDUtils;

/**
 * 用户模块业务层代码
 * @author Administrator
 *
 */
@Transactional
public class UserService {
	//注入userdao
	private UserDao userDao;
	
	public void setUserDao (UserDao userDao) {
		this.userDao = userDao;
	}
	
	//按用户名查询
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	//业务层完成用户注册
	public void save(User user) {
		//保存用户到数据库
		user.setState(0);	//0表示未激活
		String code = UUIDUtils.getUUID() + UUIDUtils.getUUID();
		user.setCode(code);
		userDao.save(user);
		
		//发送激活邮件
		MailUtils.sendMail(user.getEmail(), code);
	}

	//业务层根据激活码查询用户
	public User findByCode(String code) {
		return userDao.findByCode(code);
	}

	//修改用户状态和激活码
	public void update(User existUser) {
		userDao.update(existUser);
		
	}

	//用户登录方法
	public User login(User user) {
		return userDao.login(user);
	}
	
	// 业务层用户查询所有
	public PageBean<User> findByPage(Integer page) {
		PageBean<User> pageBean = new PageBean<User>();
		// 设置当前页数:
		pageBean.setPage(page);
		// 设置每页显示记录数:
		// 显示5个
		int limit = 5;
		pageBean.setLimit(limit);
		// 设置总记录数:
		int totalCount = 0;
		totalCount = userDao.findCount();
		pageBean.setTotalCount(totalCount);
		// 设置总页数
		pageBean.setTotalPage(totalCount % limit == 0 ? totalCount / limit : totalCount / limit + 1);
		// 设置每页显示数据集合:
		int begin = (page - 1)*limit;
		List<User> list = userDao.findByPage(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}


	public User findByUid(Integer uid) {
		return userDao.findByUid(uid);
	}


	public void delete(User existUser) {
		userDao.delete(existUser);
	}
}
