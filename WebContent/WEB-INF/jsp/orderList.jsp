<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0043)http://localhost:8080/mango/cart/list.jhtml -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

<title>我的订单页面</title>
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/cart.css" rel="stylesheet" type="text/css"/>

</head>
<body>

<div class="container header">
	<div class="span5">
		<div class="logo">
			<a href="${ pageContext.request.contextPath }/index.action">
				<img src="${pageContext.request.contextPath}/image/r___________renleipic_01/logo.gif" alt="天猫超市"/>
			</a>
		</div>
	</div>
	<div class="span9">
<div class="headerAd">
	<img src="${pageContext.request.contextPath}/image/header.jpg" width="320" height="50" alt="正品保障" title="正品保障"/>
</div>	
</div>
	
	<%@ include file="menu.jsp" %>
	
</div>	

<div class="container cart">

		<div class="span24">
		
			<div class="step step1">
				<ul>
					
					<li  class="current"></li>
					<li  >我的订单的显示</li>
				</ul>
			</div>
	
		
				<table>
					<tbody>
					<s:iterator value="pageBean.list" var="order">
						<tr>
							<th colspan="3">订单编号：<s:property value="#order.oid" /></th>
							<th colspan="2">订单状态：
							<s:if test="#order.state == 1">
								<a href="${ pageContext.request.contextPath }/order_findByOid.action?oid=<s:property value="#order.oid" />"><font color="red">付款</font></a>
							</s:if>
							<s:elseif test="#order.state == 2">
								已经付款
							</s:elseif>
							<s:elseif test="#order.state == 3">
								<a href="${ pageContext.request.contextPath }/order_updateState.action?oid=<s:property value="#order.oid" />"><font color="red">确认收货</font></a>
							</s:elseif>
							<s:elseif test="#order.state == 4">
								交易完成
							</s:elseif>
							</th>
						</tr>
						
						<tr>
							<th>图片</th>
							<th>商品</th>
							<th>价格</th>
							<th>数量</th>
							<th>小计</th>
						</tr>
						<s:iterator value="#order.orderItems" var="orderItem">
							<tr>
								<td width="60">
									<input type="hidden" name="id" value="22"/>
									<img src="${ pageContext.request.contextPath }/<s:property value="#orderItem.product.image" />"/>
								</td>
								<td>
									<a target="_blank"><s:property value="#orderItem.product.pname" /></a>
								</td>
								<td>
									<s:property value="#orderItem.product.shop_price" />
								</td>
								<td class="quantity" width="60">
									<s:property value="#orderItem.count" />
								</td>
								<td width="140">
									<span class="subtotal">￥<s:property value="#orderItem.subtotal" /></span>
								</td>
							</tr>
						</s:iterator>
						<tr>
							<th colspan="5">&nbsp;</th>
						</tr>
					</s:iterator>
					<tr>
						<th colspan="5">
							<div class="pagination">
								<span></>第 <s:property value="pageBean.page" />/<s:property value="pageBean.totalPage" /> 页</span>
									<a class="firstPage" href="${ pageContext.request.contextPath }/order_findByUid.action?page=1">&nbsp;</a>
									<s:if test="pageBean.page != 1" >
										<a class="previousPage" href="${ pageContext.request.contextPath }/order_findByUid.action?page=<s:property value="pageBean.page-1" />">&nbsp;</a>
									</s:if>
									
									<s:iterator var="i" begin="1" end="pageBean.totalPage">
										<s:if test="pageBean.page == #i">
											<span class="currentPage"><s:property value="#i" /></span> 
										</s:if>
										<s:else>
											<a href="${ pageContext.request.contextPath }/order_findByUid.action?page=<s:property value="#i" />"><s:property value="#i" /></a> 
										</s:else>
									</s:iterator>
									
									<s:if test="pageBean.page != pageBean.totalPage" >
										<a class="nextPage" href="${ pageContext.request.contextPath }/order_findByUid.action?page=<s:property value="pageBean.page+1" />">&nbsp;</a> 
									</s:if>
									<a class="lastPage" href="${ pageContext.request.contextPath }/order_findByUid.action?page=<s:property value="pageBean.totalPage" />">&nbsp;</a>
							</div>
						</th>
					</tr>
				</tbody>
			</table>
			<dl id="giftItems" class="hidden" style="display: none;">
			</dl>
		</div>
		
	</div>
<div class="container footer">
	<div class="span24">
		<div class="footerAd">
					<img src="image\r___________renleipic_01/footer.jpg" alt="我们的优势" title="我们的优势" height="52" width="950">
</div>
</div>
	<div class="span24">
		<ul class="bottomNav">
			<li><a>关于我们</a> |</li>
			<li><a>联系我们</a> |</li>
			<li><a>招贤纳士</a> |</li>
			<li><a>法律声明</a> |</li>
			<li><a>友情链接</a> |</li>
			<li><a target="_blank">支付方式</a> |</li>
			<li><a target="_blank">配送方式</a> |</li>
			<li><a>服务声明</a> |</li>
			<li><a>广告声明</a></li>
		</ul>
	</div>
	<div class="span24">
		<div class="copyright">Copyright © 2005-2017 天喵超市 版权所有</div>
	</div>
</div>
</body>
</html>