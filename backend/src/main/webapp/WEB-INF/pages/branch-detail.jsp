<%-- 
    Document   : branch-detail
    Created on : Sep 4, 2023, 8:16:16 PM
    Author     : prodi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url value="/admin/branches/add-or-update" var="action"/>
    
<form:form 
    cssClass="py-3 container-fluid" 
    modelAttribute="branch" 
    method="post" 
    action="${action}" 
    enctype="multipart/form-data"
>
    <form:input type="hidden" path="id"/>
    
    <div class="mb-4 text-primary-emphasis">
        <h4>Chi tiết chi nhánh</h4>
    </div>
    <div class="mb-2">
        <label class="form-label">Địa chỉ</label>
        <form:input type="text" class="form-control" path="address" placeholder="Nhập địa chỉ của chi nhánh" />
        <form:errors element="div" cssClass="alert alert-danger mt-1" path="address" />
    </div>
    <div class="mb-2">
        <label class="form-label">Link</label>
        <form:input type="text" class="form-control" path="link" placeholder="Đường link google map của chi nhánh" />
        <form:errors element="div" cssClass="alert alert-danger mt-1" path="link" />
    </div>
    <c:choose>
        <c:when test="${branch.id != null}">
            <button type="submit" class="btn btn-primary">Lưu thông tin</button>
        </c:when>
        <c:otherwise>
            <button type="submit" class="btn btn-success">Tạo chi nhánh</button>
        </c:otherwise>
    </c:choose>
    
</form:form>