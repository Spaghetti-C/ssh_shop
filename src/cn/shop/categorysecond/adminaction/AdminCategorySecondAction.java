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
 * ��̨������������ʵ����
 * @author Administrator
 *
 */
public class AdminCategorySecondAction extends ActionSupport implements ModelDriven<CategorySecond> {
	//ģ������ʹ�õĶ���
	private CategorySecond categorySecond = new CategorySecond();
	//ע����������service
	private CategorySecondService categorySecondService;
	//����page
	private Integer page;
	//ע��һ�������service
	private CategoryService categoryService;
	//����cid
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
	
	//��ѯ��������ķ���
	public String findAll() {
		PageBean<CategorySecond> pageBean = categorySecondService.findByPage(page);
		//�����ݱ��浽ֵջ��
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	
	//��ת����ӵ�ҳ��
	public String addPage() {
		//��ѯ����һ������
		List<Category> cList = categoryService.findAll();
		//��ʾ���ݵ�ҳ��������б�
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "addPageSuccess";
	}
	
	//�����������ķ���
	public String save() {
		categorySecond.setCategory(categoryService.findByCid(cid));
		categorySecondService.save(categorySecond);
		return "saveSuccess";
	}
	
	//ɾ����������ķ���
	public String delete() {
		categorySecond = categorySecondService.findByCsid(categorySecond.getCsid());
		categorySecondService.delete(categorySecond);
		return "deleteSuccess";
	}
	
	//�༭��������ķ���
	public String edit() {
		categorySecond = categorySecondService.findByCsid(categorySecond.getCsid());
		List<Category> cList = categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "editSuccess";
	}
	
	//�޸Ķ�������ķ���
	public String update() {
		categorySecond.setCategory(categoryService.findByCid(cid));
		categorySecondService.update(categorySecond);
		return "updateSuccess";
	}
}
