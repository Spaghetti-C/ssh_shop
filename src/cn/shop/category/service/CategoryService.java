package cn.shop.category.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.shop.category.dao.CategoryDao;
import cn.shop.category.vo.Category;

/**
 * 一级分类的业务层对象
 * @author Administrator
 *
 */
@Transactional
public class CategoryService {
	//注入categorydao、
	private CategoryDao categoryDao;

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	//查询所有一级分类
	public List<Category> findAll() {
		return categoryDao.findAll();
	}

	//业务层保存一级分类到数据库
	public void save(Category category) {
		categoryDao.save(category);
	}

	//业务层根据分类id查询分类方法
	public Category findByCid(Integer cid) {
		return categoryDao.findByCid(cid);
	}

	//业务层删除一级分类
	public void delete(Category category) {
		categoryDao.delete(category);
	}

	//业务层修改一级分类方法
	public void update(Category category) {
		categoryDao.update(category);
	}
	
	
}
