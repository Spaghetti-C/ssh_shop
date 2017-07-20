package cn.shop.adminuser.action;

import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.shop.adminuser.service.AdminUserService;
import cn.shop.adminuser.vo.AdminUser;

/**
 * ��̨��¼�ĳ־ò����
 * @author Administrator
 *
 */
public class AdminUserAction extends ActionSupport implements ModelDriven<AdminUser> {
	//ģ������ʹ�õĶ���
	private AdminUser adminUser = new AdminUser();
	//ע���̨����service
	private AdminUserService adminUserService;
	
	public void setAdminUserService(AdminUserService adminUserService) {
		this.adminUserService = adminUserService;
	}

	@Override
	public AdminUser getModel() {
		return adminUser;
	}
	
	//��̨�����¼����
	public String login() {
		AdminUser existAdminUser = adminUserService.login(adminUser);
		if (existAdminUser != null) {
			ServletActionContext.getRequest().getSession().setAttribute("existAdminUser", existAdminUser);
			System.out.println(existAdminUser.getUsername());
			return "loginSuccess";
		} else {
			this.addActionError("�����û��������������");
			return "loginFail";
		}
	}
}
