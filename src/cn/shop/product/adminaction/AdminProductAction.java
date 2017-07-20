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
 * 后台管理商品的action
 * @author Administrator
 *
 */
public class AdminProductAction extends ActionSupport implements ModelDriven<Product> {
	//模型驱动使用的对象
	private Product product = new Product();
	//注入商品的service
	private ProductService productService;
	//接收page
	private Integer page;
	//注入二级分类的service
	private CategorySecondService categorySecondService;
	//文件上传需要的参数：上传的文件，文件名，文件MIME类型
	private File upload;
	private String uploadFileName;
	private String uploadContextType;
	//接收csid
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
	
	//带分页查询商品的方法
	public String findAll() {
		PageBean<Product> pageBean = productService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	
	//跳转到添加商品
	public String addPage() {
		List<CategorySecond> csList = categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("csList", csList);
		return "addPageSuccess";
	}
	
	//保存商品
	public String save() throws IOException {
		product.setPdate(new Date());
		product.setCategorySecond(categorySecondService.findByCsid(csid));
		if (upload != null) {
			//获得文件上传的绝对路径
			String realPath = ServletActionContext.getServletContext().getRealPath("/products");
			//创建文件
			File diskFile = new File(realPath + "//" + uploadFileName);
			System.out.println(realPath);
			//文件上传
			FileUtils.copyFile(upload, diskFile);
			product.setImage("products/" + uploadFileName);
		}
		productService.save(product);
		return "saveSuccess";
	}
	
	//删除商品
	public String delete() {
		product = productService.findById(product.getPid());
		productService.delete(product);
		//删除上传的图片
		String path = product.getImage();
		if (path != null) {
			String realPath = ServletActionContext.getServletContext().getRealPath(path);
			File file = new File(realPath);
			file.delete();
		}
		
		return "deleteSuccess";
	}
	
	//跳转到商品编辑页面
	public String edit() {
		product = productService.findById(product.getPid());
		List<CategorySecond> csList = categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("csList", csList);
		return "editSuccess";
	}
	
	//修改商品
	public String update() throws IOException {
		product.setPdate(new Date());
		product.setCategorySecond(categorySecondService.findByCsid(csid));
		if (upload != null) {
			//删除图片
			String oldPath = product.getImage();
			File file = new File(ServletActionContext.getServletContext().getRealPath("/") + oldPath);
			file.delete();
			
			//获得文件上传的绝对路径
			String realPath = ServletActionContext.getServletContext().getRealPath("/products");
			//创建文件
			File diskFile = new File(realPath + "//" + uploadFileName);
			//文件上传
			FileUtils.copyFile(upload, diskFile);
			product.setImage("products/" + uploadFileName);
		}
		productService.update(product);
		return "updateSuccess";
	}
}
