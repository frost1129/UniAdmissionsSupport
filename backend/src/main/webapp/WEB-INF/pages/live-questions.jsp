<%-- 
    Document   : live-questions
    Created on : Sep 12, 2023, 4:51:14 PM
    Author     : prodi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="py-2 container-fluid">
    <h3 class="">
        Danh sách câu hỏi bài đăng livestream
    </h3>
    
    <p>
        Tên bài đăng: ${livePost.title} <br>
        Thời gian chỉnh sửa: ${livePost.updatedDate}
    </p>

    <c:choose>
        <c:when test="${not empty questions}">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>Người hỏi</th>
                        <th>Nội dung câu hỏi</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${questions}" var="q">
                        <tr>
                            <td>
                                ${q.userId.lastName} ${q.userId.firstName}
                            </td>
                            <td>${q.content}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <p><i>Hiện tại chưa có câu hỏi nào</i></p>
        </c:otherwise>
    </c:choose>
</div>
