<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
	<meta http-equiv="Cache-Control" content="no-store" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Expires" content="0" />
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no">
    
    <link type="image/x-icon" href="${ctx}/static/favicon.ico" rel="shortcut icon">
    <title>海淘拼单网:<sitemesh:title/></title>

    <!-- Bootstrap -->
    <link href="http://cdn.bootcss.com/twitter-bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet">
    <link href="http://cdn.bootcss.com/bootstrap/3.2.0/css/bootstrap-theme.min.css" rel="stylesheet">
    <link href="http://cdn.bootcss.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="http://cdn.bootcss.com/pace/0.5.7/themes/blue/pace-theme-flash.css" rel="stylesheet">
    <link href="http://cdn.bootcss.com/jquery.bootstrapvalidator/0.5.1/css/bootstrapValidator.min.css" rel="stylesheet">
    
    <link rel="stylesheet" href="${ctx}/static/stylesheets/switchery.min.css">
    <link rel="stylesheet" href="${ctx}/static/stylesheets/style.css">
    
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="http://cdn.bootcss.com/twitter-bootstrap/3.2.0/js/bootstrap.min.js"></script>
    <script src="http://cdn.bootcss.com/pace/0.5.7/pace.min.js"></script>
    <script src="http://cdn.bootcss.com/jquery.bootstrapvalidator/0.5.1/js/bootstrapValidator.min.js"></script>
    <script src='${ctx}/static/javascripts/switchery.min.js'></script>
    
    <script src='${ctx}/static/javascripts/main.js'></script>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/r29/html5.jquery.parallax.min.js"></script>
       <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.jquery.parallax.min.js"></script>
    <![endif]-->
  <sitemesh:head/>  
  </head>
  <body>
     <div class="container">
     	<div class="messages">
			<%@ include file="/WEB-INF/layouts/messages.jsp"%>
		</div>	
		<div id="content">
			<sitemesh:body/>
		</div>
		<%@ include file="/WEB-INF/layouts/footer.jsp"%>
	</div>
  </body>
</html>