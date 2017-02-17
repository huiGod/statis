<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%
	String path=request.getContextPath();
%>
<div id="navbar" class="navbar navbar-default">

	<div class="navbar navbar-default" id="navbar">
		<script type="text/javascript">
			try{ace.settings.check('navbar' , 'fixed')}catch(e){}
		</script>

		<div class="navbar-container" id="navbar-container">
			<div class="navbar-header pull-left">
				<a href="#" class="navbar-brand"> <small> <i class="icon-leaf"></i> 优启后台数据管理系统</small></a>
			</div>
			<div class="navbar-header pull-right" role="navigation">
				<ul class="nav ace-nav">
					<!-- <li class="grey"><a data-toggle="dropdown"
						class="dropdown-toggle" href="#"> <i class="icon-tasks"></i> <span
							class="badge badge-grey">4</span>
					</a></li> -->
					<li class="light-blue">
						<a data-toggle="dropdown" href="#" class="dropdown-toggle"> <img class="nav-user-photo" src="<%=path %>/static/assets/avatars/user.jpg" alt="Jason's Photo" /><span	class="user-info"> <small>欢迎光临,</small> <shiro:principal/></span> <i class="icon-caret-down"></i></a>
						<ul	class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
							<!-- 
								<li>
									<a href="#"> <i class="icon-cog"></i> 设置</a>
								</li>
								<li>
									<a href="#"> <i class="icon-user"></i> 个人资料</a>
								</li>
								<li class="divider"></li>
							-->
								<li><a href="logout"> <i class="icon-off"></i> 退出</a></li>
						</ul>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>