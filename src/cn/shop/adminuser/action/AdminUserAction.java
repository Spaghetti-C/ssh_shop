package cn.shop.adminuser.action;

import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.shop.adminuser.service.AdminUserService;
import cn.shop.adminuser.vo.AdminUser;

/**
 * 后台登录的持久层代码
 * @author Administrator
 *
 */
public class AdminUserAction extends ActionSupport implements ModelDriven<AdminUser> {
	//模型驱动使用的对象
	private AdminUser adminUser = new AdminUser();
	//注入后台管理service
	private AdminUserService adminUserService;
	
	public void setAdminUserService(AdminUserService adminUserService) {
		this.adminUserService = adminUserService;
	}

	@Override
	public AdminUser getModel() {
		return adminUser;
	}
	
	//后台管理登录方法
	public String login() {
		AdminUser existAdminUser = adminUserService.login(adminUser);
		if (existAdminUser != null) {
			ServletActionContext.getRequest().getSession().setAttribute("existAdminUser", existAdminUser);
			System.out.println(existAdminUser.getUsername());
			return "loginSuccess";
		} else {
			this.addActionError("您的用户名或者密码错误！");
			return "loginFail";
		}
	}
}
