<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<table style="border:0px;width:100%;height:100%;">
     				<tr>
     					<td width="60%" align="center">
     						<img src="${pageContext.request.contextPath }/imgs/logo.gif"/>欢迎来到叮咚图书网上商店主页!
     					</td>
     					<td align="right" style="padding-right:10px;font-size:10pt;font:bold;">
     						<c:choose>
     							<c:when test="${empty sessionScope.user}">
     								<a target="_content" href="${pageContext.request.contextPath }/jsp/front/user/reg.jsp">注册</a>
     								<a target="_content" href="${pageContext.request.contextPath }/jsp/front/user/login.jsp">登录</a>
     							</c:when>
     							<c:otherwise>
     								欢迎你：
     								<a href="#">${user.name}</a>
     								|
     								<a target="_content" href="${pageContext.request.contextPath }/order/order?action=queryOrders">订单管理</a>
     								|
     								<a target="_content" href="<c:url value='/jsp/front/buy/car.jsp'/>">查看购物车</a>
     							</c:otherwise>
     						</c:choose>
     					</td>
     				</tr>
     			</table>