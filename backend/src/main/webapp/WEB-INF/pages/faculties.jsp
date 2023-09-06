<%-- 
    Document   : faculties
    Created on : Sep 5, 2023, 4:01:19 PM
    Author     : prodi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="py-2 container-fluid">
    <h4 class="">
        Quản lý danh sách khoa
    </h4>
    
    <c:url value="/admin/faculties/detail" var="add" />
    <div class="navbar justify-content-between">
        <a href="${add}" class="btn btn-outline-primary">Thêm khoa</a>
    </div>

    <table class="table table-striped table-hover">
        <thead>
            <tr>
                <th>Tên khoa</th>
                <th>Website</th>
                <th>Giới thiệu</th>
                <th>Điểm tuyển sinh</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${faculties}" var="f">
                <tr>
                    <td>${f.name}</td>
                    <td>${f.website}</td>
                    <td>
                        <a 
                            href="<c:url value="/admin/faculties/${f.id}/overview"/>" 
                            class="btn btn-secondary rounded-pill p-0 px-2">
                            Xem giới thiệu khoa
                        </a>
                    </td>
                    <td>
                        <a 
                            href="<c:url value="/admin/faculties/${f.id}/scores"/>" 
                            class="btn btn-secondary rounded-pill p-0 px-2">
                            Xem điểm
                        </a>
                    </td>
                    <td>
                        <a 
                            href="<c:url value="/admin/faculties/${f.id}"/>" 
                            class="btn btn-success rounded-pill p-0 px-2">
                            Chi tiết
                        </a>
                        <c:url value="/admin/faculties/${f.id}" var="del" />
                        <form action="${del}" id="delTopicForm" method="POST">
                            <input type="hidden" name="id" value="${f.id}">
                            <button type="button" class="btn btn-danger rounded-pill p-0 px-2 openModal">Xóa</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
    
<!--MODAL XÁC NHẬN XÓA BANNER-->
<div class="modal fade" id="delFacultyModal" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header align-items-center">
                <h5 class="modal-title" id="exampleModalLabel">Xác nhận xóa</h5>
            </div>
            <div class="modal-body">
                Bạn muốn xóa khoa này?
                <p class="text-danger">
                    <i>Lưu ý: Việc xóa khoa này sẽ xóa tất cả thông tin và điểm tuyển sinh của khoa.</i>
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
//        XỬ LÝ SỰ KIỆN XÓA BANNER
        var delFacultyModal = $('#delFacultyModal');
        
        $(".openModal").click(function () {
            var form = $(this).closest("form"); // Tìm form gần nhất
            var deleteUrl = form.attr("action"); // Lấy URL xóa từ thuộc tính action của form

            // Thiết lập action cho nút xác nhận xóa
            $("#confDelete").attr("delete-url", deleteUrl);

            // Mở modal xác nhận
            delFacultyModal.modal("show");
        });
        
        $('#closeModal').click(function () {
            delFacultyModal.modal('hide');
        });

        $("#confDelete").click(function () {
            var deleteUrl = $(this).attr("delete-url"); // Lấy URL xóa từ thuộc tính data-delete-url

            $.ajax({
                type: "POST",
                url: deleteUrl,
                success: function () {
                    delFacultyModal.modal("hide");
                    location.reload();
                },
                error: function () {
                    console.log(error);
                }
            });
        });
    });
</script>