package cn.shop.category.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import cn.shop.category.vo.Category;

/**
 * һ������ĳ־ò����
 * @author Administrator
 *
 */
public class CategoryDao extends HibernateDaoSupport {
	//��ѯ����һ������
	public List<Category> findAll() {
		String hql = "from Category";
		List<Category> clist = this.getHibernateTemplate().find(hql);
		
		return clist;
	}

	//�־ò㱣��һ�����෽��
	public void save(Category category) {
		this.getHibernateTemplate().save(category);
	}

	//�־ò����id��ѯ����
	public Category findByCid(Integer cid) {
		return this.getHibernateTemplate().get(Category.class, cid);
	}

	//�־ò�ɾ��һ�����෽��
	public void delete(Category category) {
		this.getHibernateTemplate().delete(category);
	}

	//�־ò��޸�һ������ķ���
	public void update(Category category) {
		this.getHibernateTemplate().update(category);
	}
}
