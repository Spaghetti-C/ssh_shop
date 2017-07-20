package cn.shop.category.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.shop.category.dao.CategoryDao;
import cn.shop.category.vo.Category;

/**
 * һ�������ҵ������
 * @author Administrator
 *
 */
@Transactional
public class CategoryService {
	//ע��categorydao��
	private CategoryDao categoryDao;

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	//��ѯ����һ������
	public List<Category> findAll() {
		return categoryDao.findAll();
	}

	//ҵ��㱣��һ�����ൽ���ݿ�
	public void save(Category category) {
		categoryDao.save(category);
	}

	//ҵ�����ݷ���id��ѯ���෽��
	public Category findByCid(Integer cid) {
		return categoryDao.findByCid(cid);
	}

	//ҵ���ɾ��һ������
	public void delete(Category category) {
		categoryDao.delete(category);
	}

	//ҵ����޸�һ�����෽��
	public void update(Category category) {
		categoryDao.update(category);
	}
	
	
}
