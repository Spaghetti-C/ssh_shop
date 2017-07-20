package cn.shop.categorysecond.service;

import java.util.List;

import cn.shop.categorysecond.dao.CategorySecondDao;
import cn.shop.categorysecond.vo.CategorySecond;
import cn.shop.utils.PageBean;

/**
 * 二级分类的业务层代码
 * @author Administrator
 *
 */
public class CategorySecondService {
	//注入二级分类的dao
	private CategorySecondDao categorySecondDao;
	
	public void setCategorySecondDao(CategorySecondDao categorySecondDao) {
		this.categorySecondDao = categorySecondDao;
	}
	
	//业务层分页查询二级分类的方法
	public PageBean<CategorySecond> findByPage(Integer page) {
		PageBean<CategorySecond> pageBean = new PageBean<CategorySecond>();
		//设置属性
		pageBean.setPage(page);
		int limit = 10;
		pageBean.setLimit(limit);
		int totalCount = 0;
		totalCount = categorySecondDao.findCount();
		pageBean.setTotalCount(totalCount);
		pageBean.setTotalPage(totalCount % limit == 0 ? totalCount / limit : totalCount / limit + 1);
		int begin = (page - 1) * limit;
		List<CategorySecond> list = categorySecondDao.findByPage(begin, limit);
		pageBean.setList(list);
		
		return pageBean;
	}
	
	//业务层保存二级分类的方法
	public void save(CategorySecond categorySecond) {
		categorySecondDao.save(categorySecond);
	}

	//业务层根据二级分类id查询二级分类
	public CategorySecond findByCsid(Integer csid) {
		return categorySecondDao.findByCsid(csid);
	}

	//业务层删除二级分类
	public void delete(CategorySecond categorySecond) {
		categorySecondDao.delete(categorySecond);
	}

	//业务层修改二级分类
	public void update(CategorySecond categorySecond) {
		categorySecondDao.update(categorySecond);
	}

	//业务层查询所有二级分类
	public List<CategorySecond> findAll() {
		return categorySecondDao.findAll();
	}
	
}
