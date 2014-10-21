<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<title>注册</title>
</head>

<body>
<div class="probe-page">
        <div class="probe-form">
			<form id="registerForm" action="${ctx}/register" method="post" class="form-horizontal">
				<fieldset>
                    <legend>用户注册</legend>
                    <%
					String error = (String) request.getAttribute("error");
					if(error != null){
					%>
						<div class="alert alert-danger" role="alert">
							<button class="close" data-dismiss="alert">×</button>名字或电邮已注册，请重试.
						</div>
					<%
					}
					%>
                    <div class="form-group">
                        <div class="controls">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-user wfa">&nbsp;名字</i></span>
                                <input name="name" type="text" placeholder="" class="form-control">
                            </div>
                            <p class="help-block"><small></small></p>
                        </div>
                    </div>
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
                        <div class="controls">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-unlock-alt wfa">&nbsp;密码</i></span>
                                <input name="plainPassword" type="password" placeholder="" class="form-control">
                            </div>
                            <p class="help-block"><small></small></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="controls text-center">
                            <button type="submit" class="btn btn-success btn-block">注册</button>
                        </div>
                    </div>
                    <legend>慢着，我有帐号</legend>
                    <div class="form-group">
                        <div class="controls">
                            <a href="${ctx}/login" class="btn btn-primary btn-block">去登录</a>
                        </div>
                    </div>
                </fieldset>
			</form>
		</div>
	</div>
	
	<script>
	$(document).ready(function () {
        $('#registerForm').bootstrapValidator({
            message: '此项内容不符合规则',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                name: {
                    validators: {
                        notEmpty: {
                            message: '名字不能为空！'
                        },
                        remote: {
                            message: '这个名字已被注册！',
                            url: '${ctx}/register/checkName'
                        }
                    }
                },
                email: {
                    validators: {
                        notEmpty: {
                            message: 'Email地址不能为空！'
                        },
                        emailAddress: {
                            message: '请填入合法Email地址！'
                        },
                        remote: {
                            message: '这个Email地址已被注册！',
                            url: '${ctx}/register/checkEmail'
                        }
                    }
                },
                plainPassword: {
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
