package cn.shop.categorysecond.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import cn.shop.categorysecond.vo.CategorySecond;
import cn.shop.utils.PageHibernateCallback;

/**
 * ��������ĳ־ò����
 * @author Administrator
 *
 */
public class CategorySecondDao extends HibernateDaoSupport {

	//�־ò�ͳ�ƶ�����������ķ���
	public int findCount() {
		String hql = "select count(*) from CategorySecond";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	//�־ò��ҳ��ѯ��������ķ���
	public List<CategorySecond> findByPage(int begin, int limit) {
		String hql = "from CategorySecond order by csid desc";
		List<CategorySecond> list = this.getHibernateTemplate().execute(new PageHibernateCallback<>(hql, null, begin, limit));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	//�־ò㱣���������ķ���
	public void save(CategorySecond categorySecond) {
		this.getHibernateTemplate().save(categorySecond);
	}

	//�־ò����csid��ѯ��������
	public CategorySecond findByCsid(Integer csid) {
		return this.getHibernateTemplate().get(CategorySecond.class, csid);
	}

	//�־ò�ɾ����������
	public void delete(CategorySecond categorySecond) {
		this.getHibernateTemplate().delete(categorySecond);
	}

	//�־ò��޸Ķ�������
	public void update(CategorySecond categorySecond) {
		this.getHibernateTemplate().update(categorySecond);
	}

	//�־ò��ѯ���ж�������
	public List<CategorySecond> findAll() {
		String hql = "from CategorySecond";
		return this.getHibernateTemplate().find(hql);
	}

}
