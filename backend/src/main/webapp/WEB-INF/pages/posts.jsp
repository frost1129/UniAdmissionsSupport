<%-- 
    Document   : posts
    Created on : Sep 1, 2023, 3:42:17 PM
    Author     : prodi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="py-2 container-fluid">
    <h4 class="">
        Quản lý bài đăng tuyển sinh
    </h4>
    
    <c:url value="/admin/create-post" var="add" />
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
                        <c:url value="/admin/create-post/${p.id}" var="update" />
                        <c:if test="${userInfo != null}">
                            <c:if test="${userInfo.id == p.userId.id}">
                                <a href="${update}" class="btn btn-success rounded-pill p-0 px-2">Cập nhật</a>
                            </c:if>
                            <c:url value="/admin/delete/${p.id}" var="del" />
                            <form action="${del}" id="delForm" method="POST">
                                <input type="hidden" name="id" value="${f.id}">
                                <button type="button" class="btn btn-danger rounded-pill p-0 px-2 openModal">Xóa</button>
                            </form>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
        
<!--MODAL XÁC NHẬN XÓA-->
<div class="modal fade" id="delModal" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header align-items-center">
                <h5 class="modal-title" id="exampleModalLabel">Xác nhận xóa</h5>
            </div>
            <div class="modal-body">
                Bạn muốn xóa bài đăng này?
                <p class="text-danger">
                    <i>Lưu ý: Việc xóa bài đăng này sẽ xóa tất cả comment của bài đăng.</i>
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