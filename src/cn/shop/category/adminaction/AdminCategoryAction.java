package cn.shop.category.adminaction;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.shop.category.service.CategoryService;
import cn.shop.category.vo.Category;

/**
 * 后台一级分类action
 * @author Administrator
 *
 */
public class AdminCategoryAction extends ActionSupport implements ModelDriven<Category> {
	//模型驱动使用的类
	private Category category = new Category();
	//注入一级分类的service
	private CategoryService categoryService;
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@Override
	public Category getModel() {
		return category;
	}
	
	//后台执行查询所有一级分类的方法
	public String findAll() {
		List<Category> cList = categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "findAll";
	}
	
	//后台执行添加一级分类的方法
	public String save() {
		categoryService.save(category);
		return "saveSuccess";
	}
	
	//后台执行删除一级分类的方法
	public String delete() {
		//用模型驱动接收cid，删除一级分类同时删除二级分类需要先根据id查询，再删除
		category = categoryService.findByCid(category.getCid());
		categoryService.delete(category);
		return "deleteSuccess";
	}
	
	//后台执行编辑一级分类的方法
	public String edit() {
		category = categoryService.findByCid(category.getCid());
		return "editSuccess";
	}
	
	//后台修改一级分类的方法
	public String update() {
		categoryService.update(category);
		return "updateSuccess";
	}
}
