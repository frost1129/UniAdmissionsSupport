<%-- 
    Document   : livestreams
    Created on : Sep 1, 2023, 3:42:29 PM
    Author     : prodi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="py-2 container-fluid">
    <h4 class="">
        Banner
    </h4>

    <table class="table table-hover">
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
            <c:forEach items="${liveposts}" var="p">
                <tr>
                    <td>
                        <img src="${p.image}" alt="${p.title}" width="120px"/>
                    </td>
                    <td>${p.title}</td>
                    <td>${p.admissionType.name}</td>
                    <td>${p.userId.lastName} ${p.userId.firstName}</td>
                    <td>
                        <c:url value="/create-post/${p.id}" var="api" />
                        <a href="${api}" class="btn btn-success">Cập nhật</a>
                        <button class="btn btn-danger" onclick="deleteProduct('${api}')">Xóa</button>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
