<%-- 
    Document   : user-questions
    Created on : Sep 7, 2023, 10:07:25 PM
    Author     : prodi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="py-2 container-fluid">
    <h4>
        Danh sách câu hỏi người dùng
    </h4>
    
    <c:choose>
        <c:when test="${empty questions}">
            <h5><i>Hiện tại chưa có câu hỏi nào từ người dùng!</i></h5>
        </c:when>
        
        <c:otherwise>
        <table class="table table-striped table-hover">
            <thead>
                <tr>
                    <th>Câu hỏi</th>
                    <th>Hệ đào tạo</th>
                    <th>Người gửi</th>
                    <th>Ngày gửi</th>
                    <th>Trạng thái</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${questions}" var="q">
                    <tr>
                        <td>${q.content}</td>
                        <td>${q.admissionType.name}</td>
                        <td>${q.askUserEmail}</td>
                        <td>${q.submitTime}</td>
                        <td>
                            <c:choose>
                                <c:when test="${empty q.answer}">
                                    <button disabled="true" class="btn btn-secondary rounded-pill p-0 px-2">Chưa trả lời</button>
                                </c:when>
                                <c:otherwise>
                                    <button disabled="true" class="btn btn-success rounded-pill p-0 px-2">Đã trả lời</button>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        </c:otherwise>
    </c:choose>
    
    <c:if test="${counter > 1}">
        <ul class="pagination justify-content-center">
            <li class="page-item">
                <c:url value="/admin/questions/user-questions" var="prevPageUrl">
                    <c:param name="page" value="${currentPage - 1}"></c:param>
                </c:url>
                <c:if test="${currentPage > 1}">
                    <a class="page-link" href="${prevPageUrl}" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </c:if>
            </li>
            
            <c:forEach begin="1" end="${counter}" var="i">
                <c:url value="/admin/questions/user-questions" var="pageUrl">
                    <c:param name="page" value="${i}"></c:param>
                </c:url>
                <li class="page-item ${i == currentPage ? 'active' : ''}">
                    <a class="page-link" href="${pageUrl}">${i}</a>
                </li>
            </c:forEach>
            
            <li class="page-item">
                <c:url value="/admin/questions/user-questions" var="nextPageUrl">
                    <c:param name="page" value="${currentPage + 1}"></c:param>
                </c:url>
                <c:if test="${currentPage < counter}">
                    <a class="page-link" href="${nextPageUrl}" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </c:if>
            </li>
        </ul>
    </c:if>
</div>