package cn.shop.categorysecond.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import cn.shop.categorysecond.vo.CategorySecond;
import cn.shop.utils.PageHibernateCallback;

/**
 * 二级分类的持久层代码
 * @author Administrator
 *
 */
public class CategorySecondDao extends HibernateDaoSupport {

	//持久层统计二级分类个数的方法
	public int findCount() {
		String hql = "select count(*) from CategorySecond";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	//持久层分页查询二级分类的方法
	public List<CategorySecond> findByPage(int begin, int limit) {
		String hql = "from CategorySecond order by csid desc";
		List<CategorySecond> list = this.getHibernateTemplate().execute(new PageHibernateCallback<>(hql, null, begin, limit));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	//持久层保存二级分类的方法
	public void save(CategorySecond categorySecond) {
		this.getHibernateTemplate().save(categorySecond);
	}

	//持久层根据csid查询二级分类
	public CategorySecond findByCsid(Integer csid) {
		return this.getHibernateTemplate().get(CategorySecond.class, csid);
	}

	//持久层删除二级分类
	public void delete(CategorySecond categorySecond) {
		this.getHibernateTemplate().delete(categorySecond);
	}

	//持久层修改二级分类
	public void update(CategorySecond categorySecond) {
		this.getHibernateTemplate().update(categorySecond);
	}

	//持久层查询所有二级分类
	public List<CategorySecond> findAll() {
		String hql = "from CategorySecond";
		return this.getHibernateTemplate().find(hql);
	}

}
