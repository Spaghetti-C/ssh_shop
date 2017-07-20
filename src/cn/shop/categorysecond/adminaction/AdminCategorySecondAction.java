package cn.shop.categorysecond.adminaction;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.shop.category.service.CategoryService;
import cn.shop.category.vo.Category;
import cn.shop.categorysecond.service.CategorySecondService;
import cn.shop.categorysecond.vo.CategorySecond;
import cn.shop.utils.PageBean;

/**
 * 后台管理二级分类的实体类
 * @author Administrator
 *
 */
public class AdminCategorySecondAction extends ActionSupport implements ModelDriven<CategorySecond> {
	//模型驱动使用的对象
	private CategorySecond categorySecond = new CategorySecond();
	//注入二级分类的service
	private CategorySecondService categorySecondService;
	//接收page
	private Integer page;
	//注入一级分类的service
	private CategoryService categoryService;
	//接收cid
	private Integer cid;
	
	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public void setPage(Integer page) {
		this.page = page;
	}
	
	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}

	@Override
	public CategorySecond getModel() {
		return categorySecond;
	}
	
	//查询二级分类的方法
	public String findAll() {
		PageBean<CategorySecond> pageBean = categorySecondService.findByPage(page);
		//将数据保存到值栈中
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	
	//跳转到添加的页面
	public String addPage() {
		//查询所有一级分类
		List<Category> cList = categoryService.findAll();
		//显示数据到页面的下拉列表
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "addPageSuccess";
	}
	
	//保存二级分类的方法
	public String save() {
		categorySecond.setCategory(categoryService.findByCid(cid));
		categorySecondService.save(categorySecond);
		return "saveSuccess";
	}
	
	//删除二级分类的方法
	public String delete() {
		categorySecond = categorySecondService.findByCsid(categorySecond.getCsid());
		categorySecondService.delete(categorySecond);
		return "deleteSuccess";
	}
	
	//编辑二级分类的方法
	public String edit() {
		categorySecond = categorySecondService.findByCsid(categorySecond.getCsid());
		List<Category> cList = categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "editSuccess";
	}
	
	//修改二级分类的方法
	public String update() {
		categorySecond.setCategory(categoryService.findByCid(cid));
		categorySecondService.update(categorySecond);
		return "updateSuccess";
	}
}
