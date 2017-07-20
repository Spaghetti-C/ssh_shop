package cn.shop.user.service;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.shop.user.dao.UserDao;
import cn.shop.user.vo.User;
import cn.shop.utils.MailUtils;
import cn.shop.utils.PageBean;
import cn.shop.utils.UUIDUtils;

/**
 * �û�ģ��ҵ������
 * @author Administrator
 *
 */
@Transactional
public class UserService {
	//ע��userdao
	private UserDao userDao;
	
	public void setUserDao (UserDao userDao) {
		this.userDao = userDao;
	}
	
	//���û�����ѯ
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	//ҵ�������û�ע��
	public void save(User user) {
		//�����û������ݿ�
		user.setState(0);	//0��ʾδ����
		String code = UUIDUtils.getUUID() + UUIDUtils.getUUID();
		user.setCode(code);
		userDao.save(user);
		
		//���ͼ����ʼ�
		MailUtils.sendMail(user.getEmail(), code);
	}

	//ҵ�����ݼ������ѯ�û�
	public User findByCode(String code) {
		return userDao.findByCode(code);
	}

	//�޸��û�״̬�ͼ�����
	public void update(User existUser) {
		userDao.update(existUser);
		
	}

	//�û���¼����
	public User login(User user) {
		return userDao.login(user);
	}
	
	// ҵ����û���ѯ����
	public PageBean<User> findByPage(Integer page) {
		PageBean<User> pageBean = new PageBean<User>();
		// ���õ�ǰҳ��:
		pageBean.setPage(page);
		// ����ÿҳ��ʾ��¼��:
		// ��ʾ5��
		int limit = 5;
		pageBean.setLimit(limit);
		// �����ܼ�¼��:
		int totalCount = 0;
		totalCount = userDao.findCount();
		pageBean.setTotalCount(totalCount);
		// ������ҳ��
		pageBean.setTotalPage(totalCount % limit == 0 ? totalCount / limit : totalCount / limit + 1);
		// ����ÿҳ��ʾ���ݼ���:
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
