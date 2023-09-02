<%-- 
    Document   : index
    Created on : Aug 15, 2023, 8:52:56 PM
    Author     : prodi
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="py-2 container-fluid">
    <h4 class="">
        Banner
    </h4>
    <div class="row d-flex flex-row flex-nowrap py-2" style="overflow-x: auto;">
        <div class="p-2 me-2 card d-flex justify-content-center align-items-center" style="width: 11rem;" >
            <button type="button" class="btn bg-transparent" style="width: 100%; height: 100%" id="openModalBtn">+</button>
        </div>
        <c:forEach items="${banners}" var="b">
            <div class="pt-2 px-2 me-2 card" style="height: 13rem; width: 13rem;">
                <img class="card-img" style="width: 12rem; max-height: 8rem" alt="Banner" src="${b.image}" />
                <div class="text-center card-body">
                    <c:url value="/admin/banners/${b.id}" var="del" />
                    <form action="${del}" method="POST">
                        <input type="hidden" name="id" value="${b.id}">
                        <button class="btn btn-danger" type="submit">Xóa</button>
                    </form>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<div class="modal fade" id="addBannerModal" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Thêm banner</h5>
                <button type="button" id="closeModal" class="btn bg-transparent" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <c:url value="/admin/add-banner" var="add" />
                <form:form 
                    modelAttribute="banner" 
                    method="post" 
                    action="${add}" 
                    enctype="multipart/form-data">
                    <div class="mb-3">
                        <label for="file" class="form-label">Chọn banner</label>
                        <form:input cssClass="form-control" type="file" id="file" path="file" accept="image/png, image/gif, image/jpeg" />
                    </div>
                    <button type="submit" class="btn btn-primary">Lưu banner</button>
                </form:form>
            </div>
        </div>
    </div>
</div>

<div class="py-2 container-fluid">
    <h4 class="">
        Bài đăng nổi bật
    </h4>
    <table class="table table-striped table-hover">
        <thead class="text-center">
            <tr>
                <th></th>
                <th class="col-3">Tiêu đề</th>
                <th>Người đăng</th>
                <th>Thời gian</th>
                <th>Bình luận</th>
                <th></th>
            </tr>
        </thead>
        <tbody class="text-center">
            <tr>
                <td>1</td>
                <td class="text-start">1</td>
                <td>1</td>
                <td>1</td>
                <td>
                    <button type="button" class="rounded-pill p-0 px-2 btn btn-danger">
                        Not Allow
                    </button>
                </td>
                <td>
                    
                </td>
            </tr>
        </tbody>
    </table>
</div>

<script>
    $(document).ready(function () {
        var modal = $('#addBannerModal');

        $('#openModalBtn').click(function () {
            modal.modal('show');
        });
        
        $('#closeModal').click(function () {
            modal.modal('hide'); // Đóng modal
        });
    });
</script>
