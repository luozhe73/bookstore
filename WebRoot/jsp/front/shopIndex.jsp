<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
  	<title>欢迎来到叮咚图书网上商店!</title>
  	<script type="text/javascript">
function reinitIframe(){
var iframe = document.getElementById("_content");
try{
var bHeight = iframe.contentWindow.document.body.scrollHeight;
var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
var height = Math.max(bHeight, dHeight);
iframe.height = height;
}catch (ex){}
}
window.setInterval("reinitIframe()", 200);
</script>
  	<style type="text/css">
  		#content td{
  			border:1px solid gray;
  			border-bottom:0px;
  		}
  		.type{
  			margin-top:10px;
  			font-size:10pt;
  		}
  		.table{
  			border:1px solid gray;
  			border-bottom:0px;
  			width:1020px;
  			height:200%;
  			border-collapse:collapse;
  		}
  		.menu{
  			background:#CECEFF;
  			margin:5px;
  			width:100%;
  			height:20px;
  			vertical-align:middle;
  			text-align: center;
  		}
  		.on{
  			background:#AAAAFF;
  			margin:5px;
  			width:100%;
  			height:20px;
  			vertical-align:middle;
  			text-align: center;
  		}
  		.menu a{
  			text-decoration:none;
  		}
  		.btn{
  			border:0px;
  			background:transparent;
  			width:100px;
  		}
  		.txt{
  			border:1px solid blue;
  		}
  		td a{
  			font-size:10pt;
  		}
  	</style>
  </head>
  <body style="text-align:center;margin-bottom:0px;margin-top:0px;">
     <table class="table">
     	<tr style="height:60px;background:#ffffff">
     		<td colspan="2">
     			<%--引入通用的头部页面 --%>
     			<%@include file="/jsp/front/common/header.jsp" %>
     		</td>
     	</tr>
     	<tr id="content">
     		<td style="width:120px;vertical-align:top;padding:10px;">
     			<div class="menu" onmousemove="chg(this);" onmouseout="_chg(this);">
     					<a target="_content" href="<c:url value='/index?action=queryBooks'/>" class="type">
     						全部
     					</a>
     			</div>
     			<c:forEach items="${requestScope.types}" var="type">
     				<div class="menu" onmousemove="chg(this);" onmouseout="_chg(this);">
     					<a target="_content" href="<c:url value='/index?action=queryBooks&typeId=${type.id}'/>" class="type">
     						${type.name}
     					</a>
     				</div>
     			</c:forEach>
     		</td>
     		<td>
     			<table style="width:100%;">
     			<tr>
     				<td style="text-align:center;background:#E0E0E0;height:30px;" colspan="2">
     					<select class="txt">
     						<option value="name">书名</option>
     						<option value="auth">作者</option>
     					</select>：
     			　		<input class="txt" type="text"/><button class="btn">查询</button>
     				</td>
     			</tr>
     			</table>
     			<c:choose>
     				<%--设置是否显示选择的当前图书--%>
     				<c:when test="${!empty sessionScope.currentBook}">
						<c:set var="url" value="${pageContext.request.contextPath}/index?action=queryBook&id=${sessionScope.currentBook}"></c:set>
						<c:remove var="currentBook" scope="session"/>
     				</c:when>
     				<c:otherwise>
     					<c:set var="url" value="${pageContext.request.contextPath}/index?action=queryBooks"></c:set>
     				</c:otherwise>
     			</c:choose>
     			<iframe id="_content" scrolling="no" name="_content" src="${url}" frameborder="0" width="800px" height="100%">
     			</iframe>
     		</td>
     	</tr>
     </table>
  </body>
  <script type="text/javascript">
  	function chg(obj){
  		obj.className="on";
  	}
  	function _chg(obj){
  		obj.className="menu";
  	}
  </script>
</html>
     					