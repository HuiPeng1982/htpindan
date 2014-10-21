<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException"%>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<title>忘记密码</title>
</head>

<body>
 	<div class="probe-page">
        <div class="probe-form">
			<form id="forgetForm" action="${ctx}/forget" method="post" role="form" class="form-horizontal">
            <fieldset>
                <legend>密码找回</legend>
                <div class="form-group">
                    <div class="controls">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-envelope-o wfa">&nbsp;电邮</i></span>
                            <input name="email" type="text" placeholder="" class="form-control">
                        </div>
                        <p class="help-block"><small></small></p>
                    </div>
                </div>
                <div class="form-group">
                    <div class="controls text-center">
                        <button type="submit" class="btn btn-success btn-block">把重置密码邮件发我吧</button>
                    </div>
                </div>
            </fieldset>
        </form>
		</div>
	</div>	
	
	<script>
	$(document).ready(function () {
        $('#forgetForm').bootstrapValidator({
            message: '此项内容不符合规则',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                email: {
                    validators: {
                        notEmpty: {
                            message: 'Email地址不能为空！'
                        },
                        emailAddress: {
                            message: '请填入合法Email地址！'
                        }
                    }
                }
            }
        });
    });
	</script>
</body>
</html>
