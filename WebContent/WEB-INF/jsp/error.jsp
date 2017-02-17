<%@ page language="java" import="java.util.*" isErrorPage="true" pageEncoding="UTF-8"%>
<%
	String path=request.getContextPath();
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<link href="<%= path%>/static/css/error.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<div id="main">
		<!-- header -->
		<div id="header">
			<h1>No pages here as you see!<span>404 error - not found.</span></h1>
		</div>
		<!-- content -->
		<div id="content">
         <p>Unless creepy emptiness was what you were looking for, this place has nothing that you want.<br /> 
         So please either go to our <a href="/index">index</a> or contact us</a>.</p>
		</div>
		<!-- footer -->
	</div>
</body>
</html>