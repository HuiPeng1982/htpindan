<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<title>数据录入</title>
</head>

<body>
<div class="probe-page">
        <div class="probe-form">
			<form id="orderForm" action="${ctx}/order" method="post" class="form-horizontal">
				<fieldset>
                    <legend>数据录入</legend>
                    <div class="form-group">
                        <div class="controls">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-user wfa">&nbsp;Json</i></span>
                                <textarea name="order" rows="30" class="form-control" style="resize: none;"></textarea>
                            </div>
                            <p class="help-block"><small></small></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="controls text-center">
                            <button type="submit" class="btn btn-success btn-block">注册</button>
                        </div>
                    </div>
                </fieldset>
			</form>
		</div>
	</div>
	
	<script>
	$(document).ready(function () {
        $('#orderForm').bootstrapValidator({
            message: '此项内容不符合规则',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                order: {
                    validators: {
                        notEmpty: {
                            message: '名字不能为空！'
                        }
                    }
                }
            }
        });
    });
	</script>
</body>
</html>
