package cn.shop.category.adminaction;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.shop.category.service.CategoryService;
import cn.shop.category.vo.Category;

/**
 * ��̨һ������action
 * @author Administrator
 *
 */
public class AdminCategoryAction extends ActionSupport implements ModelDriven<Category> {
	//ģ������ʹ�õ���
	private Category category = new Category();
	//ע��һ�������service
	private CategoryService categoryService;
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@Override
	public Category getModel() {
		return category;
	}
	
	//��ִ̨�в�ѯ����һ������ķ���
	public String findAll() {
		List<Category> cList = categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "findAll";
	}
	
	//��ִ̨�����һ������ķ���
	public String save() {
		categoryService.save(category);
		return "saveSuccess";
	}
	
	//��ִ̨��ɾ��һ������ķ���
	public String delete() {
		//��ģ����������cid��ɾ��һ������ͬʱɾ������������Ҫ�ȸ���id��ѯ����ɾ��
		category = categoryService.findByCid(category.getCid());
		categoryService.delete(category);
		return "deleteSuccess";
	}
	
	//��ִ̨�б༭һ������ķ���
	public String edit() {
		category = categoryService.findByCid(category.getCid());
		return "editSuccess";
	}
	
	//��̨�޸�һ������ķ���
	public String update() {
		categoryService.update(category);
		return "updateSuccess";
	}
}
