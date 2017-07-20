package cn.shop.product.adminaction;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.shop.categorysecond.service.CategorySecondService;
import cn.shop.categorysecond.vo.CategorySecond;
import cn.shop.product.service.ProductService;
import cn.shop.product.vo.Product;
import cn.shop.utils.PageBean;

/**
 * ��̨������Ʒ��action
 * @author Administrator
 *
 */
public class AdminProductAction extends ActionSupport implements ModelDriven<Product> {
	//ģ������ʹ�õĶ���
	private Product product = new Product();
	//ע����Ʒ��service
	private ProductService productService;
	//����page
	private Integer page;
	//ע����������service
	private CategorySecondService categorySecondService;
	//�ļ��ϴ���Ҫ�Ĳ������ϴ����ļ����ļ������ļ�MIME����
	private File upload;
	private String uploadFileName;
	private String uploadContextType;
	//����csid
	private Integer csid;
	
	public void setCsid(Integer csid) {
		this.csid = csid;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setUploadContextType(String uploadContextType) {
		this.uploadContextType = uploadContextType;
	}

	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@Override
	public Product getModel() {
		return product;
	}
	
	//����ҳ��ѯ��Ʒ�ķ���
	public String findAll() {
		PageBean<Product> pageBean = productService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	
	//��ת�������Ʒ
	public String addPage() {
		List<CategorySecond> csList = categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("csList", csList);
		return "addPageSuccess";
	}
	
	//������Ʒ
	public String save() throws IOException {
		product.setPdate(new Date());
		product.setCategorySecond(categorySecondService.findByCsid(csid));
		if (upload != null) {
			//����ļ��ϴ��ľ���·��
			String realPath = ServletActionContext.getServletContext().getRealPath("/products");
			//�����ļ�
			File diskFile = new File(realPath + "//" + uploadFileName);
			System.out.println(realPath);
			//�ļ��ϴ�
			FileUtils.copyFile(upload, diskFile);
			product.setImage("products/" + uploadFileName);
		}
		productService.save(product);
		return "saveSuccess";
	}
	
	//ɾ����Ʒ
	public String delete() {
		product = productService.findById(product.getPid());
		productService.delete(product);
		//ɾ���ϴ���ͼƬ
		String path = product.getImage();
		if (path != null) {
			String realPath = ServletActionContext.getServletContext().getRealPath(path);
			File file = new File(realPath);
			file.delete();
		}
		
		return "deleteSuccess";
	}
	
	//��ת����Ʒ�༭ҳ��
	public String edit() {
		product = productService.findById(product.getPid());
		List<CategorySecond> csList = categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("csList", csList);
		return "editSuccess";
	}
	
	//�޸���Ʒ
	public String update() throws IOException {
		product.setPdate(new Date());
		product.setCategorySecond(categorySecondService.findByCsid(csid));
		if (upload != null) {
			//ɾ��ͼƬ
			String oldPath = product.getImage();
			File file = new File(ServletActionContext.getServletContext().getRealPath("/") + oldPath);
			file.delete();
			
			//����ļ��ϴ��ľ���·��
			String realPath = ServletActionContext.getServletContext().getRealPath("/products");
			//�����ļ�
			File diskFile = new File(realPath + "//" + uploadFileName);
			//�ļ��ϴ�
			FileUtils.copyFile(upload, diskFile);
			product.setImage("products/" + uploadFileName);
		}
		productService.update(product);
		return "updateSuccess";
	}
}
