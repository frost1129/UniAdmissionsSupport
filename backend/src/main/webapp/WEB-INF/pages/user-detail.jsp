<%-- 
    Document   : user-detail
    Created on : Sep 3, 2023, 3:58:23 PM
    Author     : prodi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url value="/admin/users/add" var="add"/>
<c:url value="/admin/users/${user.id}" var="update"/>

<c:if test="${not empty bindingResult}">
    <div class="alert alert-danger">
        <p>Đã xảy ra lỗi:</p>
        <ul>
            <c:forEach items="${bindingResult.allErrors}" var="error">
                <li><c:out value="${error.defaultMessage}" /></li>
            </c:forEach>
        </ul>
    </div>
</c:if>

<form:form 
    cssClass="py-3 container-fluid" 
    modelAttribute="user" 
    method="post" 
    action="${empty user.id ? add : update}" 
    enctype="multipart/form-data"
>
    <form:errors path="*" element="div" cssClass="alert alert-danger mt-1"/>

    <div class="mb-4 text-primary-emphasis">
        <h4>Chi tiết thông tin người dùng</h4>
    </div>
    
    <div class="mb-3">
        <label class="form-label">Địa chỉ Email</label>
        <form:input type="text" class="form-control" path="email" placeholder="Địa chỉ email người dùng" readonly="${empty user.id ? false : true}" />
        <form:errors path="email" element="div" cssClass="alert alert-danger mt-1"/>
    </div>
    <div class="mb-3 row">
        <div class="col">
            <label class="form-label">Họ và tên đệm</label>
            <form:input type="text" class="form-control" path="lastName" placeholder="Ví dụ: Nguyễn Văn" />
            <form:errors path="lastName" element="div" cssClass="alert alert-danger mt-1"/>
        </div>
        <div class="col">
            <label class="form-label">Tên</label>
            <form:input type="text" class="form-control" path="firstName" placeholder="Ví dụ: A" />
            <form:errors path="firstName" element="div" cssClass="alert alert-danger mt-1"/>
        </div>
    </div>
    
    <c:choose>
        <c:when test="${user.id != null}">
            <div class="mb-3">
                <label class="form-label">Đổi mật khẩu người dùng</label>
                <form:input type="password" class="form-control" path="password" placeholder="Mật khẩu người dùng" />
                <form:errors path="password" element="div" cssClass="alert alert-danger mt-1"/>

                <form:input id="fileInput" type="file" path="file"/>
            </div>
        </c:when>
        <c:otherwise>
            <div class="mb-3">
                <label for="file" class="form-label">Chọn ảnh đại diện cho người dùng</label>
                <c:if test="${user.image != null}">
                    <img class="mb-3" src="${user.image}" alt="${user.firstName}" width="120px"/>
                </c:if>
                <form:input cssClass="form-control" type="file" id="file" path="file" accept="image/png, image/gif, image/jpeg" />
                <form:errors path="file" element="div" cssClass="alert alert-danger mt-1"/>
            </div>
            <div class="mb-3 row">
                <div class="col">
                    <label class="form-label">Mật khẩu</label>
                    <form:input type="password" class="form-control" path="password" placeholder="Mật khẩu người dùng" />
                    <form:errors path="password" element="div" cssClass="alert alert-danger mt-1"/>
                </div>
                <div class="col">
                    <label class="form-label">Xác nhận mật khẩu</label>
                    <form:input type="password" class="form-control" path="confPassword" placeholder="Nhập lại mật khẩu để xác nhận" />
                    <form:errors path="confPassword" element="div" cssClass="alert alert-danger mt-1"/>
                </div>
            </div>
        </c:otherwise>
    </c:choose>
    
    <div class="mb-3 row">
        <div class="col">
            <label class="form-label">Vai trò</label>
            <form:select id="userRole" class="form-select" path="userRole">
                <option value="" disabled="true">Chọn vai trò của người dùng trong hệ thống</option>
                <c:choose>
                    <c:when test="${user.userRole == 'ROLE_ADMIN'}">
                        <option value="ROLE_ADMIN" selected>Quản trị viên</option>
                        <option value="ROLE_ADVISOR">Tư vấn viên</option>
                        <option value="ROLE_USER">Người dùng</option>
                    </c:when>
                    <c:when test="${user.userRole == 'ROLE_ADVISOR'}">
                        <option value="ROLE_ADMIN">Quản trị viên</option>
                        <option value="ROLE_ADVISOR" selected>Tư vấn viên</option>
                        <option value="ROLE_USER">Người dùng</option>
                    </c:when>
                    <c:when test="${user.userRole == 'ROLE_USER'}">
                        <option value="ROLE_ADMIN">Quản trị viên</option>
                        <option value="ROLE_ADVISOR">Tư vấn viên</option>
                        <option value="ROLE_USER" selected>Người dùng</option>
                    </c:when>
                    <c:otherwise>
                        <option value="ROLE_ADMIN">Quản trị viên</option>
                        <option value="ROLE_ADVISOR">Tư vấn viên</option>
                        <option value="ROLE_USER">Người dùng</option>
                    </c:otherwise>
                </c:choose>
            </form:select>
            <form:errors path="userRole" element="div" cssClass="alert alert-danger"/>
        </div>

        <div class="col">
            <label class="form-label">Hệ tuyển sinh (nếu có)</label>
            <form:select id="userAdmissionType" class="form-select" path="userAdmissionType">
                <option value="">Không phải thuộc hệ tuyển sinh nào</option>
                <c:forEach items="${admissionTypes}" var="a">
                    <c:choose>
                        <c:when test="${a.id == user.userAdmissionType.id}">
                            <option value="${a.id}" selected>${a.name}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${a.id}">${a.name}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </form:select>
        </div>
    </div>

    <c:choose>
        <c:when test="${user.id != null}">
            <button type="submit" class="btn btn-primary">Lưu thông tin người dùng</button>
        </c:when>
        <c:otherwise>
            <button type="submit" class="btn btn-success">Tạo tài khoản người dùng mới</button>
        </c:otherwise>
    </c:choose>
</form:form>

<script>
    $(document).ready(function () {
        $("#fileInput").hide();
        
        $("#userRole").change(function () {
            var selectedValue = $(this).val();
            var admissionTypeSelect = $("#userAdmissionType");
            
            if (selectedValue === "ROLE_ADVISOR") {
                admissionTypeSelect.prop("disabled", false);
                admissionTypeSelect.val("1");
            } else {
                admissionTypeSelect.val("");
                admissionTypeSelect.prop("disabled", true);
            }
        });
        
        var initialUserRoleValue = $("#userRole").val();
        if (initialUserRoleValue !== "ROLE_ADVISOR") {
            $("#userAdmissionType").prop("disabled", true);
        }
    });
</script>
