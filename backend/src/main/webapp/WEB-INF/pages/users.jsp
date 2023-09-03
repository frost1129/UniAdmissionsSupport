<%-- 
    Document   : users
    Created on : Sep 2, 2023, 10:22:14 PM
    Author     : prodi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="py-2 container-fluid">
    <h4 class="">
        Quản lý người dùng
    </h4>
    
    <div class="navbar justify-content-between">
        <a type="button" id="openModal" class="btn btn-outline-primary">Thêm người dùng</a>
    </div>

    <table class="table table-striped table-hover mx-4">
        <thead>
            <tr>
                <th>Avatar</th>
                <th>Email</th>
                <th>Họ và tên</th>
                <th>Vai trò</th>
                <th>Hệ tuyển sinh</th>
                <td></td>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${users}" var="u">
                <tr class="align-middle">
                    <td>
                        <img 
                            src="${u.image}" 
                            alt="${u.firstName} avatar" 
                            class="rounded-circle" 
                            style="object-fit: cover; width: 50px; height: 50px"
                        />
                    </td>
                    <th>${u.email}</th>
                    <td>${u.lastName} ${u.firstName}</td>
                    <td>${u.userRole}</td>
                    <td>
                        <c:choose>
                            <c:when test="${u.userAdmissionType != null}">
                                ${u.userAdmissionType}
                            </c:when>
                            <c:otherwise>
                                <button class="rounded-pill p-0 px-2 btn btn-secondary" disabled>
                                    Không tồn tại
                                </button>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <c:url value="/admin/users/${u.id}" var="update" />
                        <a href="${update}" class="btn btn-success rounded-pill p-0 px-2">Cập nhật</a>
                        <button class="btn btn-danger rounded-pill p-0 px-2">Xóa</button>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
        
<div class="modal fade" id="newUserModal" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Thêm người dùng</h5>
                <button type="button" id="closeModal" class="btn bg-transparent" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
<!--            <div class="modal-body">
                <c:url value="/admin/questions/update-time" var="update" />
                <form:form 
                    modelAttribute="times" 
                    method="post" 
                    action="${update}" 
                    >
                    <form:input type="hidden" path="id" /> 
                    <form:input type="hidden" path="fromTime" id="set1" /> 
                    <form:input type="hidden" path="toTime" id="set2" /> 

                    <div class="form-group mb-3">
                        <label>Thời gian bắt đầu:</label>
                        <input type="time" id="time1" step="1" min="00:00:00" max="24:00:00" class="form-control my-2"/>
                    </div>
                    
                    <div class="form-group mb-3">
                        <label>Thời gian kết thúc:</label>
                        <input type="time" id="time2" step="1" min="00:00:00" max="24:00:00" class="form-control my-2"/>
                    </div>

                    <button type="submit" class="btn btn-primary">Lưu thời gian</button>
                </form:form>
            </div>-->
        </div>
    </div>
</div>
                
<script>
    $(document).ready(function () {
        var modal = $('#newUserModal');

        $('#openModal').click(function () {
            modal.modal('show');
        });
        
        $('#closeModal').click(function () {
            modal.modal('hide'); // Đóng modal
        });
    });
</script>

