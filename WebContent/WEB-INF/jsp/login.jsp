<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path=request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>登录</title>
<link rel="icon" href="<%=path %>/static/images/favicon.ico" type="image/x-icon" />
<link href="<%=path %>/static/css/login.css" rel="stylesheet" type="text/css" media="all" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
<script>var __links = document.querySelectorAll('a');function __linkClick(e) { parent.window.postMessage(this.href, '*');} ;for (var i = 0, l = __links.length; i < l; i++) {if ( __links[i].getAttribute('data-t') == '_blank' ) { __links[i].addEventListener('click', __linkClick, false);}}</script>
<script src="<%=path %>/static/js/jquery-1.11.1.min.js"></script>
<script>
/*$(document).ready(function() {
		 $('.alert-close').on('click', function(c){
			$('.message').fadeOut('slow', function(c){
		  		$('.message').remove();
			});
		}); 
	});*/
	$(document).ready(function(){
		$("#rememberMe").change(function(){
			if(this.checked){
				$(this).val(true);
			}else{
				$(this).val(false);
			}
		});
	});
	function myFunction(){
		$("form").submit();
	}
	
</script>
</head>
<body>
<!-- contact-form -->	
<div class="message warning">
<div class="inset">
	<div class="login-head tx-center">
		<h1><img class="bottom_icon"  alt="logo" src="<%=path %>/static/images/icon_right.png">优启数据管理系统</h1>
		<!-- <div class="alert-close"> </div>  -->			
	</div>
		<form action="" method="post">
			<li>
				<input type="text" class="text" value="${username }" name="username"/><a href="#" class=" icon user"></a>
			</li>
				<div class="clear"> </div>
			<li>
				<input type="password" value="" name="password"/> <a href="#" class="icon lock"></a>
			</li>
			<div class="clear"> </div>
			<div class="txt-lt">
			<input type="checkbox" name="rememberMe" id="rememberMe" value="false"/>remember me?
			</div>
			<div class="clear"> </div>
			<h1 style="color: red;" id="error">${error }</h1>
			<div class="submit txt-center">
				<input type="submit" onclick="myFunction()" value="登录" >
				<div class="clear">  </div>	
			</div>
			<%-- <img class="bottom_icon"  alt="logo" src="<%=path %>/static/images/icon_right.png"> --%>				
		</form>
		</div>					
	</div>
	<div class="clear"> </div>
<!--- footer --->
<div class="footer">
	<p>深圳优启科技有限公司</p>
</div>
</body>
</html>