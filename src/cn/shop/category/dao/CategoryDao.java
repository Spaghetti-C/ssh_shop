package cn.shop.category.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import cn.shop.category.vo.Category;

/**
 * 一级分类的持久层对象
 * @author Administrator
 *
 */
public class CategoryDao extends HibernateDaoSupport {
	//查询所有一级分类
	public List<Category> findAll() {
		String hql = "from Category";
		List<Category> clist = this.getHibernateTemplate().find(hql);
		
		return clist;
	}

	//持久层保存一级分类方法
	public void save(Category category) {
		this.getHibernateTemplate().save(category);
	}

	//持久层根据id查询分类
	public Category findByCid(Integer cid) {
		return this.getHibernateTemplate().get(Category.class, cid);
	}

	//持久层删除一级分类方法
	public void delete(Category category) {
		this.getHibernateTemplate().delete(category);
	}

	//持久层修改一级分类的方法
	public void update(Category category) {
		this.getHibernateTemplate().update(category);
	}
}
