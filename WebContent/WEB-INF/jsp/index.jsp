<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path=request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
  	<meta HTTP-EQUIV="pragma" CONTENT="no-cache">
	<meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate" />
  	<title> 优启数据统计后台 </title>
  	<jsp:include page="./include/include.jsp"></jsp:include>
</head>
	<body class="skin-1">
	    <jsp:include page="./include/header.jsp"></jsp:include>
	    <div class="main-container" id="main-container">
	    	<jsp:include page="./include/left.jsp"></jsp:include>
	     	<div class="main-content">
	     		<div class="page-content">
	     			<div class="alert alert-info">
						<button type="button" class="close" data-dismiss="alert">
							<i class="icon-remove"></i>
						</button>
						欢迎访问数据管理系统~
						<br>
					</div>
		 		</div>
			</div>
	    </div>
 </body>
</html>