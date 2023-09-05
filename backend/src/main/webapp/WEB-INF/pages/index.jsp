<%-- 
    Document   : index
    Created on : Aug 15, 2023, 8:52:56 PM
    Author     : prodi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!--CONTAINER CHỨA LIST BANNERS-->
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
                    <form action="${del}" id="delBannerForm" method="POST">
                        <input type="hidden" name="id" value="${b.id}">
                        <button class="btn btn-danger openBannerModal" type="button">Xóa</button>
                    </form>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<!--MODAL XÁC NHẬN XÓA BANNER-->
<div class="modal fade" id="delBannerModal" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header align-items-center">
                <h5 class="modal-title" id="exampleModalLabel">Xác nhận xóa</h5>
            </div>
            <div class="modal-body">
                Bạn muốn xóa banner này?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" id="closeBannerModal">Hủy</button>
                <button type="button" class="btn btn-danger" id="confDeleteBanner">Xác nhận xóa</button>
            </div>
        </div>
    </div>
</div>

<!--MODAL THÊM BANNER-->
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
                    id="addBannerForm"
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
                
<!--CONTAINER CHỨA LIST CƠ SỞ-->
<div class="py-2 container-fluid">
    <h4 class="">
        Địa chỉ
    </h4>
    <div class="row d-flex flex-row flex-nowrap py-2" style="overflow-x: auto;">
        <c:url value="/admin/branches/branch-detail" var="addOrUpdate" />
        <div class="p-2 me-2 card d-flex justify-content-center align-items-center" style="width: 11rem;" >
            <a href="${addOrUpdate}" class="btn bg-transparent" style="width: 100%;">+</a>
        </div>
        
        <c:forEach items="${branches}" var="b">
            <div class="pt-2 px-2 me-2 card" style="height: 16rem; width: 13rem;">
                <div class="card-body">
                    <h5 class="card-title">Cơ sở ${b.id}</h5>
                    <p class="card-text">
                        ${b.address}
                    </p>
                    <a href="${b.link}" target="_blank" class="btn btn-outline-primary mb-2">Xem trên bản đồ</a>
                    
                    <c:url value="/admin/branches/${b.id}" var="del" />
                    <form action="${del}" id="delBannerForm" method="POST">
                        <input type="hidden" name="id" value="${b.id}">
                        <a href="${del}" class="btn btn-primary">Sửa</a>
                        <button class="btn btn-danger openBranchModal" type="button">Xóa</button>
                    </form>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<!--MODAL XÁC NHẬN XÓA CƠ SỞ-->
<div class="modal fade" id="branchModal" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header align-items-center">
                <h5 class="modal-title">Xác nhận xóa</h5>
            </div>
            <div class="modal-body">
                Bạn có chắc chắn về việc xóa chi nhánh với ID này <br>
                <i>Lưu ý: Việc này đồng nghĩa rằng sẽ không tồn tại chi nhanh với ID này.</i>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" id="closeBranchModal">Hủy</button>
                <button type="button" class="btn btn-danger" id="confDeleteBranch">Xác nhận xóa</button>
            </div>
        </div>
    </div>
</div>

<!--CONTAINER CHỨA LIST TOPICS-->
<div class="py-2 container-fluid">
    <h4 class="">
        Chủ đề nổi bật
    </h4>
    <c:url value="/admin/topics/topic-detail" var="addTopic" />
    <div class="navbar justify-content-between">
        <a href="${addTopic}" class="btn btn-outline-primary">Thêm chủ đề</a>
    </div>
    
    <c:choose>
        <c:when test="${empty topics}">
            <h5><i>Hiện tại chưa có chủ đề nào</i></h5>
        </c:when>
        <c:otherwise>
        <table class="table table-striped table-hover">
            <thead class="text-center">
                <tr>
                    <th class="col-3">Tên chủ đề</th>
                    <th class="w-25"></th>
                </tr>
            </thead>
            <tbody class="text-center">
                <c:forEach items="${topics}" var="t" >
                <tr>
                    <td class="text-start">${t.title}</td>
                    <td>
                        <c:url value="/admin/topics/${t.id}" var="delTopic" />
                        <form action="${delTopic}" id="delTopicForm" method="POST">
                            <input type="hidden" name="id" value="${t.id}">
                            <button type="button" class="btn btn-danger rounded-pill p-0 px-2 openTopicModal">Xóa</button>
                        </form>
                    </td>
                </tr>
                </c:forEach>
            </tbody>
        </table>
        </c:otherwise>
    </c:choose>
</div>
         
<!--MODAL XÁC NHẬN XÓA TOPIC-->
<div class="modal fade" id="topicModal" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header align-items-center">
                <h5 class="modal-title">Xác nhận xóa</h5>
            </div>
            <div class="modal-body">
                Bạn có chắc chắn về việc xóa chủ đề này? <br>
                <i>Lưu ý: Bài đăng của liên quan sẽ không bị xóa khi xóa chủ đề này.</i>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" id="closeTopicModal">Hủy</button>
                <button type="button" class="btn btn-danger" id="confDeleteTopic">Xác nhận xóa</button>
            </div>
        </div>
    </div>
</div>

<script>
    $(document).ready(function () {
        
//        XỬ LÝ MODAL THÊM BANNER 
        var modal = $('#addBannerModal');
        
        $('#openModalBtn').click(function () {
            modal.modal('show');
        });
        
        $('#closeModal').click(function () {
            modal.modal('hide'); // Đóng modal
        });
        
        $("#addBannerForm").submit(function (event) {
            var fileInput = $("#file"); // Trường input file
            var file = fileInput[0].files[0]; // Lấy file đã được chọn

            // Kiểm tra xem file đã được chọn hay chưa
            if (!file) {
                alert("Vui lòng chọn một file trước khi lưu.");
                event.preventDefault(); // chặn việc submit form
            }
        });
        
//        XỬ LÝ SỰ KIỆN XÓA BANNER
        var delBannerModal = $('#delBannerModal');
        
        $(".openBannerModal").click(function () {
            var form = $(this).closest("form"); // Tìm form gần nhất
            var deleteUrl = form.attr("action"); // Lấy URL xóa từ thuộc tính action của form

            // Thiết lập action cho nút xác nhận xóa
            $("#confDeleteBanner").attr("banner-delete-url", deleteUrl);

            // Mở modal xác nhận
            delBannerModal.modal("show");
        });
        
        $('#closeBannerModal').click(function () {
            delBannerModal.modal('hide');
        });

        $("#confDeleteBanner").click(function () {
            var deleteUrl = $(this).attr("banner-delete-url"); // Lấy URL xóa từ thuộc tính data-delete-url

            $.ajax({
                type: "POST",
                url: deleteUrl,
                success: function () {
                    delBannerModal.modal("hide");
                    location.reload();
                },
                error: function () {
                    console.log(error);
                }
            });
        });
        
//        XỬ LÝ SỰ KIỆN XÓA TOPIC
        var topicModal = $('#topicModal');
        
        $('.openTopicModal').click(function () {
            var form = $(this).closest("form"); // Tìm form gần nhất
            var deleteUrl = form.attr("action"); // Lấy URL xóa từ thuộc tính action của form

            $("#confDeleteTopic").attr("topic-delete-url", deleteUrl);

            topicModal.modal('show');
        });
        
        $('#closeTopicModal').click(function () {
            topicModal.modal('hide');
        });
        
        $("#confDeleteTopic").click(function () {
            var deleteUrl = $(this).attr("topic-delete-url"); // Lấy URL xóa từ thuộc tính data-delete-url

            $.ajax({
                type: "POST",
                url: deleteUrl,
                success: function () {
                    topicModal.modal("hide");
                    location.reload();
                },
                error: function () {
                    console.log(error);
                }
            });
        });
        
//        XỬ LÝ SỰ KIỆN XÓA BRANCH        
        var branchModal = $('#branchModal');
        
        $('.openBranchModal').click(function () {
            var form = $(this).closest("form"); // Tìm form gần nhất
            var deleteUrl = form.attr("action"); // Lấy URL xóa từ thuộc tính action của form

            $("#confDeleteBranch").attr("branch-delete-url", deleteUrl);

            branchModal.modal('show');
        });
        
        $('#closeBranchModal').click(function () {
            branchModal.modal('hide');
        });
        
        $("#confDeleteBranch").click(function () {
            var deleteUrl = $(this).attr("branch-delete-url"); // Lấy URL xóa từ thuộc tính data-delete-url

            $.ajax({
                type: "POST",
                url: deleteUrl,
                success: function () {
                    branchModal.modal("hide");
                    location.reload();
                },
                error: function () {
                    console.log(error);
                }
            });
        });
    });
</script>
