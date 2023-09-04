<%-- 
    Document   : topic-detail
    Created on : Sep 4, 2023, 1:53:19 PM
    Author     : prodi
--%>

<%--<%@page contentType="text/html" pageEncoding="UTF-8"%>--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<c:url value="/admin/topics/topic-detail" var="addTopic"/>
<form:form 
    cssClass="py-3 container-fluid" 
    modelAttribute="topic" 
    method="post" 
    action="${action}" 
>
    <form:input type="hidden" path="postId" id="selectedId"/>

    <div class="mb-4 text-primary-emphasis">
        <h4>Chi tiết chủ đề</h4>
    </div>
    <div class="mb-3">
        <label class="form-label">Tên chủ đề</label>
        <form:input type="text" class="form-control" path="title" placeholder="Nhập tên chủ đề ở đây" />
        <form:errors path="title" element="div" cssClass="alert alert-danger mt-1"/>
    </div>
    <div class="mb-3">
        <label class="form-label">Vai trò</label>
        <select class="form-select" id="postSelect">
            <c:choose>
                <c:when test="${empty posts}">
                    <option value="" disabled="true">Chưa có bài viết nào trong hệ thống</option>
                </c:when>
                <c:otherwise>
                    <option value="" disabled="true" selected="true">Chọn bài viết gắn với chủ đề</option>
                    <c:forEach items="${posts}" var="p">
                        <option value="${p.id}">${p.title} - ${p.updatedDate}</option>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </select>
        <form:errors path="postId" element="div" cssClass="alert alert-danger mt-1"/>
        <i>
            Bấm vào 
            <a href="/notbackend/admin/create-post"> đây </a> 
            để tạo bài đăng mới.
        </i>
    </div>
        
    <button type="submit" class="btn btn-success">Tạo chủ đề</button>
</form:form>

<script>
    $(document).ready(function () {
        var selectedId = $("#selectedId");

        $("#postSelect").change(function () {
            var selectedValue = $(this).val();
            selectedId.val(selectedValue);
        });
    });
</script>