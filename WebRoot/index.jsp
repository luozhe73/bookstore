<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
    <title>跳转到首页</title>  
  </head>
  
  <body>
  <c:redirect url="/index">
  	<c:param name="action" value="showIndex"/>
  </c:redirect>
  </body>
</html>
