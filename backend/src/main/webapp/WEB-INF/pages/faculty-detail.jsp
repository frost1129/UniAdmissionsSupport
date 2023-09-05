<%-- 
    Document   : faculty-detail
    Created on : Sep 5, 2023, 4:01:27 PM
    Author     : prodi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url value="/admin/faculties/add-or-update" var="action"/>
<form:form 
    cssClass="py-3 container-fluid" 
    modelAttribute="faculty" 
    method="post" 
    action="${action}" 
    enctype="multipart/form-data"
>
    <form:input type="hidden" path="id"/>
    <form:input type="hidden" path="nameEng"/>
    
    <div class="mb-4 text-primary-emphasis">
        <h4>Chi tiết khoa</h4>
    </div>
    <div class="mb-2">
        <label class="form-label">Tên Khoa</label>
        <form:input type="text" class="form-control" path="name" placeholder="Nhập tên gọi của khoa" />
        <form:errors element="div" cssClass="alert alert-danger mt-1" path="name" />
    </div>
    <div class="mb-2">
        <label class="form-label">Website Khoa</label>
        <form:input type="text" class="form-control" path="website" placeholder="Website khoa" />
        <form:errors element="div" cssClass="alert alert-danger mt-1" path="website" />
    </div>
    <div class="mb-2">
        <label class="form-label">Email Khoa</label>
        <form:input type="text" class="form-control" path="email" placeholder="Email khoa" />
        <form:errors element="div" cssClass="alert alert-danger mt-1" path="email" />
    </div>
    <c:choose>
        <c:when test="${faculty.id != null}">
            <button type="submit" class="btn btn-primary">Lưu thông tin</button>
        </c:when>
        <c:otherwise>
            <button type="submit" class="btn btn-success">Tạo khoa</button>
        </c:otherwise>
    </c:choose>
    
</form:form>
