package cn.shop.interceptor;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

import cn.shop.adminuser.vo.AdminUser;

/**
 * 后台权限校验拦截器
 * 对未登录的用户禁止访问
 * @author Administrator
 *
 */
public class PrivilegeInterceptor extends MethodFilterInterceptor {
	//执行拦截的方法
	@Override
	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		//判断session中是否保存了用户的信息
		AdminUser adminUser = (AdminUser)ServletActionContext.getRequest().getSession().getAttribute("existAdminUser");
		if (adminUser == null) {
			//没有登录
			ActionSupport actionSupport = (ActionSupport)actionInvocation.getAction();
			actionSupport.addActionError("您还没有登录，没有访问权限");
			return "loginFail";
		} else {
			//已经登录，调用下一个拦截器 或 执行下一个Action
			return actionInvocation.invoke();
		}
	}
	
}
