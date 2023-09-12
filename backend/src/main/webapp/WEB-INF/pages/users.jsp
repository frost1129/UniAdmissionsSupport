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
    
    <c:url value="/admin/users/create-user" var="add" />
    <div class="navbar justify-content-between">
        <a href="${add}" class="btn btn-outline-primary">Thêm người dùng</a>
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
                        <button class="rounded-pill p-0 px-2 btn btn-secondary" disabled>
                        <c:choose>
                            <c:when test="${u.userAdmissionType != null}">
                                ${u.userAdmissionType.name}
                            </c:when>
                            <c:otherwise>
                                Không có
                            </c:otherwise>
                        </c:choose>
                        </button>
                    </td>
                    <td>
                        <c:url value="/admin/users/${u.id}" var="update" />
                        <a href="${update}" class="btn btn-success rounded-pill p-0 px-2">Cập nhật</a>
                        
                        <c:url value="/admin/users/${u.id}/delete" var="del" />
                        <form action="${del}" id="delForm" method="POST">
                            <input type="hidden" name="id" value="${f.id}">
                            <button type="button" class="btn btn-danger rounded-pill p-0 px-2 openModal">Xóa</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    
    <!--PHÂN TRANG-->
    <c:if test="${counter > 1}">
        <ul class="pagination justify-content-center">
            <li class="page-item">
                <c:url value="/admin/users" var="prevPageUrl">
                    <c:param name="page" value="${currentPage - 1}"></c:param>
                </c:url>
                <c:if test="${currentPage > 1}">
                    <a class="page-link" href="${prevPageUrl}" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </c:if>
            </li>
            
            <c:forEach begin="1" end="${counter}" var="i">
                <c:url value="/admin/users" var="pageUrl">
                    <c:param name="page" value="${i}"></c:param>
                </c:url>
                <li class="page-item ${i == currentPage ? 'active' : ''}">
                    <a class="page-link" href="${pageUrl}">${i}</a>
                </li>
            </c:forEach>
            
            <li class="page-item">
                <c:url value="/admin/users" var="nextPageUrl">
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
        
<div class="modal fade" id="delModal" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header align-items-center">
                <h5 class="modal-title" id="exampleModalLabel">Xác nhận xóa</h5>
            </div>
            <div class="modal-body">
                Bạn muốn xóa bài đăng này?
                <p class="text-danger">
                    <i>Lưu ý: Xóa người dùng này đồng nghĩa với việc xóa toàn bộ bài đăng, livestream, ... và các nội dung có liên quan tới người dùng này.</i>
                </p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" id="closeModal">Hủy</button>
                <button type="button" class="btn btn-danger" id="confDelete">Xác nhận xóa</button>
            </div>
        </div>
    </div>
</div>
                
<script>
    $(document).ready(function () {
    //        XỬ LÝ SỰ KIỆN XÓA
        var delModal = $('#delModal');
        
        $(".openModal").click(function () {
            var form = $(this).closest("form"); // Tìm form gần nhất
            var deleteUrl = form.attr("action"); // Lấy URL xóa từ thuộc tính action của form

            // Thiết lập action cho nút xác nhận xóa
            $("#confDelete").attr("delete-url", deleteUrl);

            // Mở modal xác nhận
            delModal.modal("show");
        });
        
        $('#closeModal').click(function () {
            delModal.modal('hide');
        });

        $("#confDelete").click(function () {
            var deleteUrl = $(this).attr("delete-url"); // Lấy URL xóa từ thuộc tính data-delete-url

            $.ajax({
                type: "POST",
                url: deleteUrl,
                success: function () {
                    delModal.modal("hide");
                    location.reload();
                },
                error: function () {
                    console.log(error);
                }
            });
        });
    });
</script>

