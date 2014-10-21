<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException"%>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<title>登录</title>
</head>

<body>
 	<div class="probe-page">
        <div class="probe-form">
			<form id="loginForm" action="${ctx}/login" method="post" class="form-horizontal">
				<fieldset>
                    <legend>用户登录</legend>
                    <%
					String error = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
					if(error != null){
					%>
						<div class="alert alert-danger" role="alert">
							<button class="close" data-dismiss="alert">×</button>登录失败，请重试.
						</div>
					<%
					}
					%>
                    <div class="form-group">
                        <div class="controls">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-envelope-o wfa">&nbsp;电邮</i></span>
                                <input name="username" type="text" value="${email}" placeholder="" class="form-control">
                            </div>
                            <p class="help-block"><small></small></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="controls">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-unlock-alt wfa">&nbsp;密码</i></span>
                                <input name="password" type="password" placeholder="" class="form-control">
                            </div>
                            <p class="help-block"><small></small></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="controls">
                            <input name="rememberMe" value="true" type="checkbox" class="js-switch" checked /> <span>记住我</span>
                            <a href="${ctx}//forget" class="btn pull-right btn-warning">忘记密码</a>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="controls text-center">
                            <button type="submit" class="btn btn-success btn-block">登录</button>
                        </div>
                    </div>
                    <legend>其实，也可以</legend>
                    <div class="form-group">
                        <div class="controls">
                            <a href="" class="btn btn-danger btn-block"><i class="fa fa-weibo"></i>&nbsp;直接用微博帐号登录</a>
                            <a href="" class="btn btn-success btn-block"><i class="fa fa-weixin"></i>&nbsp;直接用微信帐号登录</a>
                        </div>
                    </div>
                    <legend>什么？还没注册</legend>
                    <div class="form-group">
                        <div class="controls">
                            <a href="${ctx}/register" class="btn btn-primary btn-block">去注册</a>
                        </div>
                    </div>
                </fieldset>		
			</form>
		</div>
	</div>	
	
	<script>
	var elem = document.querySelector('.js-switch');
    var switchery = new Switchery(elem);
    $(document).ready(function () {
        $('#loginForm').bootstrapValidator({
            message: '此项内容不符合规则',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
            	username: {
                    validators: {
                        notEmpty: {
                            message: 'Email地址不能为空！'
                        },
                        emailAddress: {
                            message: '请填入合法Email地址！'
                        }
                    }
                },
                password: {
                    validators: {
                        notEmpty: {
                            message: '密码不能为空！'
                        },
                        stringLength: {
                            min: 6,
                            message: '密码需要至少6个字符！'
                        }
                    }
                }
            }
        });
    });
	</script>
</body>
</html>
