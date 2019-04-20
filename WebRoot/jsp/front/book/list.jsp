<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
  <head>
  	<style type="text/css">
  		.div{
  			border:1px dotted #E0E0E0;
  			font-size:10pt;
  			margin:3px;
  			float:left;
  			text-align:center;
  			width:120px;
  		}
  		.img{
  			width:100px;
  			height:120px;
  			border:0px;
  		}
  		.divin{
  			border:1px solid gray;
  			font-size:10pt;
  			margin:3px;
  			float:left;
  			text-align:center;
  			width:120px;
  		}
  	</style>
  </head>
  <body>
  	<form id="queryForm" action="${pageContext.request.contextPath }/index?action=queryBooks" method="post">
  		<input type="hidden" id="currentPage" name="currentPage" value="${pageBean.currentPage }"/>
  		<input type="hidden" id="pageSizeId" name="pageSize" value="${pageBean.pageSize }"/>
  		<input type="hidden" name="typeId" value="${param['typeId'] }"/>
  	</form>
  
  	<c:forEach items="${pageBean.data}" var="book" varStatus="idx">
  		<div class="div" onmousemove="divin(this);" onmouseout="divout(this);">
  			<a href="${pageContext.request.contextPath }/index?action=queryBook&id=${book.id}">
  				<img class="img" src="${pageContext.request.contextPath }/imgs/${book.img}"></img>
  			</a>
  			<br/>
  			<c:choose>
  				<c:when test="${fn:length(book.name)>7}">
  					${fn:substring(book.name,0,7)}...
  				</c:when>
  				<c:otherwise>
  					${book.name}
  				</c:otherwise>
  			</c:choose>
  			<br/>
  			<c:if test="${book.price!=book.currentPrice}" var="boo">
  				<font style="text-decoration:line-through;">${book.price}元</font>
  			</c:if>
  			<font style="color:red;">${book.currentPrice}元</font>
  		</div>
  		<c:if test="${idx.count%4==0}">
  			<br style="clear:left;"/>
  		</c:if>
  	</c:forEach>
  	<table width="800px">
  		<tr>
  		<td>
  	<%--分页显示 --%>
  	<%--
    	 	需求： 
    	 		1） 如果当前页是首页，那么不能点击“首页”和“上一页”，否则可以点击
    	 		2) 如果当前页是末页，那么不能点击“下一页”和“末页”，否则可以点击
    	 	 --%>
    	 	 <c:choose>
    	 	 	<c:when test="${pageBean.currentPage==pageBean.firstPage}">
    	 	 		首页&nbsp;
    	 	 		上一页
    	 	 	</c:when>
    	 	 	<c:otherwise>
    	 	 		<a href="javascript:void(0)" onclick="toPage(${pageBean.firstPage})">首页</a>&nbsp;
    	 			<a href="javascript:void(0)" onclick="toPage(${pageBean.prePage})">上一页</a>&nbsp;
    	 	 	</c:otherwise>
    	 	 </c:choose>
    	 	 
    	 	 <c:choose>
    	 	 	<c:when test="${pageBean.currentPage==pageBean.totalPage}">
    	 	 		下一页&nbsp;
    	 	 		末页
    	 	 	</c:when>
    	 	 	<c:otherwise>
    	 	 		<a href="javascript:void(0)" onclick="toPage(${pageBean.nextPage})">下一页</a>&nbsp;
    	 			<a href="javascript:void(0)" onclick="toPage(${pageBean.totalPage})">末页</a>&nbsp;
    	 	 	</c:otherwise>
    	 	 </c:choose>
    	 		当前为第${pageBean.currentPage }页/共${pageBean.totalPage }页&nbsp;
    	 		共${pageBean.totalCount }条数据&nbsp;
    	 		每页显示 <input type="text" size="2" id="pageSize" value="${pageBean.pageSize }" onblur="changePageSize()"/> 条&nbsp;
    	 		跳转到第<input type="text" id="curPage" size="2" onblur="changePage()"/>页
    	</td></tr>
    	</table>
  </body>
  <script type="text/javascript">
  	function divin(obj){
  		obj.className="divin";
  	}
	function divout(obj){
		obj.className="div";
  	}
  	
  	//提交表单,进行翻页
  	function toPage(pageNo){
  		//提交之前，把当前页设置跳转到页码
  		document.getElementById("currentPage").value = pageNo;
  		//提交表单
  		var queryForm = document.getElementById("queryForm");
  		queryForm.submit();//提交表单
  	}
  	
  	/*改变每页显示记录数*/
  	function changePageSize(){
  		//1.得到用户输入
  		var pageSize = document.getElementById("pageSize").value;
  		//判断规则：只能输入1-2个的数字
  		var reg = /^[1-9][0-9]?$/;
  		if(!reg.test(pageSize)){
  			alert("只能输入1-2位的数字");
  			return;
  		}
  		//每次改变,都需要把当前页设置为第1页
  		document.getElementById("currentPage").value = "1";
  		//2.
  		//提交表单,同时发送参数(pageSize)
  		document.getElementById("pageSizeId").value=pageSize;
  		var queryForm = document.getElementById("queryForm");
  		queryForm.submit();//提交表单
  	}
  </script>
</html>
