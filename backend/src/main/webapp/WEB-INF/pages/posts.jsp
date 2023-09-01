<%-- 
    Document   : posts
    Created on : Sep 1, 2023, 3:42:17 PM
    Author     : prodi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="py-2 container-fluid">
    <h4 class="">
        Banner
    </h4>
    
    <c:url value="/create-post" var="add" />
    <div class="navbar justify-content-between">
        <a href="${add}" class="btn btn-outline-primary">Thêm bài đăng</a>
        <form class="form-inline d-flex flex-row">
            <input class="form-control mr-sm-2 mx-1" type="search" name="kw" placeholder="Nhập tiêu đề bài đăng">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Tìm</button>
        </form>
    </div>

    <table class="table table-striped table-hover">
        <thead>
            <tr>
                <th>Hình</th>
                <th>Tiêu đề bài viết</th>
                <th>Hệ tuyển sinh</th>
                <th>Người đăng</th>
                <td></td>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${posts}" var="p">
                <tr>
                    <td>
                        <c:if test="${p.image != null}">
                            <img src="${p.image}" alt="${p.title}" width="120px"/>
                        </c:if>
                    </td>
                    <td>${p.title}</td>
                    <td>${p.admissionType.name}</td>
                    <td>${p.userId.lastName} ${p.userId.firstName}</td>
                    <td>
                        <c:url value="/create-post/${p.id}" var="update" />
                        <a href="${update}" class="btn btn-success rounded-pill p-0 px-2">Cập nhật</a>
                        <button class="btn btn-danger rounded-pill p-0 px-2">Xóa</button>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>