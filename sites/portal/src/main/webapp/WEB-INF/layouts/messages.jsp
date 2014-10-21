<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${not empty flash_success}">
<div class="alert alert-success alert-dismissible" role="alert">
  <button type="button" class="close" data-dismiss="alert">
  	<span aria-hidden="true">&times;</span>
  	<span class="sr-only">Close</span>
  </button>
  <strong>成功！</strong> ${flash_success}
</div>
</c:if>
<c:if test="${not empty flash_info}">
<div class="alert alert-info alert-dismissible" role="alert">
  <button type="button" class="close" data-dismiss="alert">
  	<span aria-hidden="true">&times;</span>
  	<span class="sr-only">Close</span>
  </button>
  <strong>信息！</strong> ${flash_info}
</div>
</c:if>
<c:if test="${not empty flash_warning}">
<div class="alert alert-warning alert-dismissible" role="alert">
  <button type="button" class="close" data-dismiss="alert">
  	<span aria-hidden="true">&times;</span>
  	<span class="sr-only">Close</span>
  </button>
  <strong>警告！</strong> ${flash_warning}
</div>
</c:if>
<c:if test="${not empty flash_danger}">
<div class="alert alert-danger alert-dismissible" role="alert">
  <button type="button" class="close" data-dismiss="alert">
  	<span aria-hidden="true">&times;</span>
  	<span class="sr-only">Close</span>
  </button>
  <strong>危险！</strong> ${flash_danger}
</div>
</c:if>