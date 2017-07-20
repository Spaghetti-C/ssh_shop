package cn.shop.interceptor;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

import cn.shop.adminuser.vo.AdminUser;

/**
 * ��̨Ȩ��У��������
 * ��δ��¼���û���ֹ����
 * @author Administrator
 *
 */
public class PrivilegeInterceptor extends MethodFilterInterceptor {
	//ִ�����صķ���
	@Override
	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		//�ж�session���Ƿ񱣴����û�����Ϣ
		AdminUser adminUser = (AdminUser)ServletActionContext.getRequest().getSession().getAttribute("existAdminUser");
		if (adminUser == null) {
			//û�е�¼
			ActionSupport actionSupport = (ActionSupport)actionInvocation.getAction();
			actionSupport.addActionError("����û�е�¼��û�з���Ȩ��");
			return "loginFail";
		} else {
			//�Ѿ���¼��������һ�������� �� ִ����һ��Action
			return actionInvocation.invoke();
		}
	}
	
}
