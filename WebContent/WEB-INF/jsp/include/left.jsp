<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="main-container-inner">
		<a class="menu-toggler" id="menu-toggler" href="#">
			<span class="menu-text"></span>
		</a>

		<div class="sidebar" id="sidebar">
			<script type="text/javascript">
				try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
			</script>

			<div class="sidebar-shortcuts" id="sidebar-shortcuts">
				<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
					<button class="btn btn-success">
						<i class="icon-signal"></i>
					</button>

					<button class="btn btn-info">
						<i class="icon-pencil"></i>
					</button>

					<button class="btn btn-warning">
						<i class="icon-group"></i>
					</button>

					<button class="btn btn-danger">
						<i class="icon-cogs"></i>
					</button>
				</div>
			<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
					<span class="btn btn-success"></span>

					<span class="btn btn-info"></span>

					<span class="btn btn-warning"></span>

					<span class="btn btn-danger"></span>
				</div>
			</div><!-- #sidebar-shortcuts -->
			<ul class="nav nav-list">
				<c:forEach items="${menus }" var="menu">
					<li id="${menu.url }">
						<a href="javascript:void(0);"> 
							<i class="icon-sitemap"></i>
							<span class="menu-text"> ${menu.name}</span>
							<!-- <b class="arrow icon-angle-down"></b> -->
							
						</a>
						
						<!-- <ul class="submenu" style="display: none;">
								<li id="index">
									<a href="javascript:void(0);">
										<i class="icon-double-angle-right"></i>
										组件
									</a>
								</li>
						</ul> -->
					</li>		
				</c:forEach>
			</ul>
			<div class="sidebar-collapse" id="sidebar-collapse">
				<i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
			</div>
		</div>
		<script type="text/javascript">
			var url=location.href;
			$(".sidebar li").click(function(){
				var idattr=$(this).attr("id");
				if(idattr=="" || idattr==null){//展开子菜单
					$(this).find("ul").eq(0).css("display","block");
				}else{
					location.href=idattr;
				}
			});
			$(".sidebar li").each(function(){
				var idattr=$(this).attr("id");
				if(idattr!="" && idattr!=null && url.indexOf(idattr)!=-1){
					$(this).addClass("active");
					if($(this).parent().hasClass("submenu")){//如果是子菜单，展开父菜单
						$(this).parent().css("display","block");
					}
				}
			});
		</script>
</div>

