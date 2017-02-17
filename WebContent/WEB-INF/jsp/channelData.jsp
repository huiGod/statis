<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path=request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
  	<meta HTTP-EQUIV="pragma" CONTENT="no-cache">
	<meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate" />
  	<title>渠道数据管理 </title>
  	<jsp:include page="./include/include.jsp"></jsp:include>
</head>
	<body class="skin-1">
	    <jsp:include page="./include/header.jsp"></jsp:include>
	    <div class="main-container" id="main-container">
	    	<jsp:include page="./include/left.jsp"></jsp:include>
	     	<div class="main-content">
	     		<div class="breadcrumbs" id="breadcrumbs"></div>
	     		<div class="page-content"> 
	     			<div class="row">
						<div class="col-xs-12">
							<div class="table-header no-padding-left">
								<div class="control-group">
									<div class="controls">
										<div class="input-prepend input-group">
											<span class="add-on input-group-addon">
												<i class="glyphicon glyphicon-calendar fa fa-calendar"></i>
											</span>
											<input type="text" style="width: 200px" name="reservation" id="reservation"	class="form-control" />
											<select class="form-control" id="channel" style="width:120px;margin-left:10px;"></select>
											<select class="form-control" id="appId" style="width:150px;margin-left:10px;"></select>
										</div>
									</div>
								</div>
								<input type="hidden" id="startTime" value=""/>
								<input type="hidden" id="endTime" value=""/>
							</div>
							<table id="table_channel" width="100%">
							    <thead>
							        <tr>
							        	<th width="5%;">序号</th>
							        	<th>日期</th>
							        	<th>应用名称</th>
							            <th>排重点击</th>
							            <th>激活数</th>
							            <th>CR(%)</th>
							            <th width="10%;">比例(10为最大)</th>
							        </tr>
							    </thead>
							</table>
						</div>
					</div>
		 		</div>
			</div>
	    </div>
    <script src="<%=path %>/static/js/data/app-channel.js?rand=1.0.0"></script>
    <script type="text/javascript">
  	function visiableCol6(){
  		var column = $('#table_channel').DataTable().column(6);
  		if($("#channel").val()!=2){
        	column.visible(false);
        }else{
        	column.visible(true);
        	<shiro:lacksPermission name="level">
            column.visible(false);
            </shiro:lacksPermission>
        } 
  	}
    </script>
 </body>
</html>