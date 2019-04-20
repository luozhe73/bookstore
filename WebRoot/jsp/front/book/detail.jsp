<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
  <head>
  	<script type="text/javascript" src="<c:url value='/js/show.js'/>"></script>
  	<style type="text/css">
  		table{
  			width:100%;
  			border:0px solid red;
  			font-size:10pt;
  		}
  		td{
  			border-bottom:1px dotted #0000C6;
  		}
  		.img{
  			width:100px;
  			height:120px;
  			border:0px;
  		}
  	</style>
  </head>
  <body style="font-size:10pt;">
  	<p>以下是《${book.name}》的信息</p>
    <table>
    	<tr>
    		<td style="width:120px;">
    			<img class="img" src="${pageContext.request.contextPath }/imgs/${book.img}"></img>
    		</td>
    		<td align="left" valign="top">
    			书名：${book.name}<br/>
    			<c:if test="${book.rebate!=1.0}" var="boo">
    				原价：<font style="text-decoration:line-through;">${book.price}元</font><br/>
    				现价：${book.currentPrice}元<br/>
    				折扣：<fmt:formatNumber pattern="##" value="${book.rebate*100}"/>折<br/>
    			</c:if>
    			<c:if test="${!boo}">
    				价格：${book.currentPrice}元<br/>
    			</c:if>
    			作者：${book.auth}<br/>
    			出版：${book.publisher}<br/>
    			版次：第${book.versions}版，第${book.printtimes}次印刷<br/>
    			出版时间：${book.publishdate}<br/>
    			开本：${book.size}开，共${book.pages}页<br/>
    			<a href="javascript:_go('${book.id}','${book.name}','${book.currentPrice}','${book.img}');">
    				<img style="border:0px;" src="${pageContext.request.contextPath }/css/imgs/btn_buy.gif"></img>
    			</a>
    		</td>
    	</tr>
    	<tr>
    		<td colspan="2">
    			<p>以下是书的说明信息</p>
    			<p>简介：</p>
    			${book.brief}
    			<hr style="border:1px dotted blue;"/>
    			<p>目录</p>
    			${book.content}
    		</td>
    	</tr>
    </table>
  </body>
</html>
