package cn.shop.product.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.shop.product.vo.Product;
import cn.shop.utils.PageHibernateCallback;

/**
 * 商品的持久层对象
 * @author Administrator
 *
 */
public class ProductDao extends HibernateDaoSupport {

	//首页上热门商品的查询
	public List<Product> findHot() {
		//使用离线查询
		DetachedCriteria  detachedCriteria = DetachedCriteria.forClass(Product.class);
		//热门商品，is_hot等于1
		detachedCriteria.add(Restrictions.eq("is_hot", 1));
		//倒序排序
		detachedCriteria.addOrder(Order.desc("pdate"));
		//执行查询
		List<Product> hList = this.getHibernateTemplate().findByCriteria(detachedCriteria, 0, 10);
		return hList;
	}

	//查询最新商品
	public List<Product> findNew() {
		//使用离线查询
		DetachedCriteria  detachedCriteria = DetachedCriteria.forClass(Product.class);
		//倒序排序
		detachedCriteria.addOrder(Order.desc("pdate"));
		//执行查询
		List<Product> nList = this.getHibernateTemplate().findByCriteria(detachedCriteria, 0, 10);
		return nList;
	}

	//根据商品id查询商品
	public Product findById(Integer pid) {
		return this.getHibernateTemplate().get(Product.class, pid);
	}

	//根据分类id查询商品个数
	public int findCountCid(Integer cid) {
		String hql = "select count(*) from Product p where p.categorySecond.category.cid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql, cid);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	//根据分类id查询商品集合
	public List<Product> findByPageCid(Integer cid, int begin, int limit) {
		//select p.* from category c, categorySecond cs, product p where c.cid = cs.cid and cs.csid = p.csid and c.cid = 1;
		String hql = "select p from Product p join p.categorySecond cs join cs.category c where c.cid = ?";
		List<Product> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, new Object[]{cid}, begin, limit));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	//根据二级分类id查询二级分类个数
	public int findCountCsid(Integer csid) {
		String hql = "select count(*) from Product p where p.categorySecond.csid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql, csid);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	//根据二级分类id分页查询二级分类
	public List<Product> findByPageCsid(Integer csid, int begin, int limit) {
		String hql = "select p from Product p join p.categorySecond cs where cs.csid = ?";
		List<Product> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, new Object[]{csid}, begin, limit));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	//查询所有二级分类个数
	public int findCount() {
		String hql = "select count(*) from Product";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	//分页查询二级分类
	public List<Product> findByPage(int begin, int limit) {
		String hql = "from Product order by pid desc";
		List<Product> list = this.getHibernateTemplate().execute(new PageHibernateCallback<>(hql, null, begin, limit));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	//业务层保存商品的方法
	public void save(Product product) {
		this.getHibernateTemplate().save(product);
	}

	//删除商品
	public void delete(Product product) {
		this.getHibernateTemplate().delete(product);
	}

	//修改商品
	public void update(Product product) {
		this.getHibernateTemplate().update(product);
	}

}
