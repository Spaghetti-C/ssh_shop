package cn.shop.product.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.shop.product.vo.Product;
import cn.shop.utils.PageHibernateCallback;

/**
 * ��Ʒ�ĳ־ò����
 * @author Administrator
 *
 */
public class ProductDao extends HibernateDaoSupport {

	//��ҳ��������Ʒ�Ĳ�ѯ
	public List<Product> findHot() {
		//ʹ�����߲�ѯ
		DetachedCriteria  detachedCriteria = DetachedCriteria.forClass(Product.class);
		//������Ʒ��is_hot����1
		detachedCriteria.add(Restrictions.eq("is_hot", 1));
		//��������
		detachedCriteria.addOrder(Order.desc("pdate"));
		//ִ�в�ѯ
		List<Product> hList = this.getHibernateTemplate().findByCriteria(detachedCriteria, 0, 10);
		return hList;
	}

	//��ѯ������Ʒ
	public List<Product> findNew() {
		//ʹ�����߲�ѯ
		DetachedCriteria  detachedCriteria = DetachedCriteria.forClass(Product.class);
		//��������
		detachedCriteria.addOrder(Order.desc("pdate"));
		//ִ�в�ѯ
		List<Product> nList = this.getHibernateTemplate().findByCriteria(detachedCriteria, 0, 10);
		return nList;
	}

	//������Ʒid��ѯ��Ʒ
	public Product findById(Integer pid) {
		return this.getHibernateTemplate().get(Product.class, pid);
	}

	//���ݷ���id��ѯ��Ʒ����
	public int findCountCid(Integer cid) {
		String hql = "select count(*) from Product p where p.categorySecond.category.cid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql, cid);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	//���ݷ���id��ѯ��Ʒ����
	public List<Product> findByPageCid(Integer cid, int begin, int limit) {
		//select p.* from category c, categorySecond cs, product p where c.cid = cs.cid and cs.csid = p.csid and c.cid = 1;
		String hql = "select p from Product p join p.categorySecond cs join cs.category c where c.cid = ?";
		List<Product> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, new Object[]{cid}, begin, limit));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	//���ݶ�������id��ѯ�����������
	public int findCountCsid(Integer csid) {
		String hql = "select count(*) from Product p where p.categorySecond.csid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql, csid);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	//���ݶ�������id��ҳ��ѯ��������
	public List<Product> findByPageCsid(Integer csid, int begin, int limit) {
		String hql = "select p from Product p join p.categorySecond cs where cs.csid = ?";
		List<Product> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, new Object[]{csid}, begin, limit));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	//��ѯ���ж����������
	public int findCount() {
		String hql = "select count(*) from Product";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	//��ҳ��ѯ��������
	public List<Product> findByPage(int begin, int limit) {
		String hql = "from Product order by pid desc";
		List<Product> list = this.getHibernateTemplate().execute(new PageHibernateCallback<>(hql, null, begin, limit));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	//ҵ��㱣����Ʒ�ķ���
	public void save(Product product) {
		this.getHibernateTemplate().save(product);
	}

	//ɾ����Ʒ
	public void delete(Product product) {
		this.getHibernateTemplate().delete(product);
	}

	//�޸���Ʒ
	public void update(Product product) {
		this.getHibernateTemplate().update(product);
	}

}
