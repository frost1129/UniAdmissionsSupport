<%-- 
    Document   : questions
    Created on : Sep 2, 2023, 9:45:31 AM
    Author     : prodi
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<head>
    <link rel="stylesheet" href="//cdn.quilljs.com/1.3.6/quill.bubble.css">
</head>

<div class="py-2 container-fluid">
    <h3 class="">
        Quản lý câu hỏi người dùng
    </h3>
    
    <div class="row mb-3">
        <div>
            <p class="bold">
                Thời gian cho phép gửi câu hỏi: 
                <b>${times.fromTime} - ${times.toTime}</b>
            </p>
            <button type="button" id="openModal" class="btn btn-outline-success">Sửa</button>
            <a href="<c:url value="/admin/questions/user-questions" />" class="btn btn-outline-info">
                Danh sách câu hỏi người dùng
            </a>
        </div>
    </div>
            
    <h4 class="">
        Danh sách câu hỏi thường gặp
    </h4>
            
    <button type="button" id="openF" class="btn btn-primary mb-3">Thêm FAQs</button>

    <c:choose>
        <c:when test="${empty faqs}">
            <h5><i>Hiện chưa có câu hỏi thường gặp.</i></h5>
        </c:when>
        <c:otherwise>
            <table class="table table-striped table-hover">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Câu hỏi</th>
                        <th>Câu trả lời</th>
                        <th>Ngày cập nhật</th>
                        <td></td>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${faqs}" var="f">
                        <tr>
                            <td>${f.id}</td>
                            <td>${f.title}</td>
                            <td>${f.content}</td>
                            <td>${f.updatedDate}</td>
                            <td>
                                <c:url value="/admin/questions/faq/${f.id}" var="del" />
                                <form action="${del}" id="delFaqForm" method="POST">
                                    <input type="hidden" name="id" value="${f.id}">
                                    <button type="button" class="btn btn-danger rounded-pill p-0 px-2 openDelModal">Xóa</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            
            <c:if test="${counter > 1}">
                <ul class="pagination justify-content-center">
                    <li class="page-item">
                        <c:url value="/admin/questions" var="prevPageUrl">
                            <c:param name="page" value="${currentPage - 1}"></c:param>
                        </c:url>
                        <c:if test="${currentPage > 1}">
                            <a class="page-link" href="${prevPageUrl}" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </c:if>
                    </li>

                    <c:forEach begin="1" end="${counter}" var="i">
                        <c:url value="/admin/questions" var="pageUrl">
                            <c:param name="page" value="${i}"></c:param>
                        </c:url>
                        <li class="page-item ${i == currentPage ? 'active' : ''}">
                            <a class="page-link" href="${pageUrl}">${i}</a>
                        </li>
                    </c:forEach>

                    <li class="page-item">
                        <c:url value="/admin/questions" var="nextPageUrl">
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
        </c:otherwise>
    </c:choose>
</div>
            
<!--MODAL XÁC NHẬN XÓA-->
<div class="modal fade" id="delModal" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header align-items-center">
                <h5 class="modal-title">Xác nhận xóa</h5>
            </div>
            <div class="modal-body">
                Xác nhận xóa câu hỏi thường gặp này?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" id="closeModal">Hủy</button>
                <button type="button" class="btn btn-danger" id="confDelete">Xác nhận xóa</button>
            </div>
        </div>
    </div>
</div>
            
<!--MODAL THÊM FAQS-->
<div class="modal fade" id="addFAQsModal" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Thêm câu hỏi thường gặp</h5>
                <button type="button" id="closeF" class="btn bg-transparent" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <c:url value="/admin/questions/add-faq" var="add" />
                <form:form 
                    id="addFAQForm"
                    modelAttribute="faq" 
                    method="post" 
                    action="${add}" 
                    enctype="multipart/form-data">
                    <div class="mb-3">
                        <label class="form-label">Nội dung câu hỏi</label>
                        <form:input type="text" class="form-control" path="title" placeholder="Nội dung câu hỏi" />
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Câu trả lời</label>
                        <div id="editor" class="border border-secondary-subtle rounded"></div>   
                        <form:input type="hidden" id="content" path="content"/>
                    </div>
                    <button type="submit" class="btn btn-primary">Lưu</button>
                </form:form>
            </div>
        </div>
    </div>
</div>

<!--MODAL SỬA THỜI GIAN-->
<div class="modal fade" id="settingModal" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Chỉnh sửa thời gian nhận câu hỏi</h5>
                <button type="button" id="closeModal" class="btn bg-transparent" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <c:url value="/admin/questions/update-time" var="update" />
                <form method="post" action="${update}" >
                    <div class="form-group mb-3">
                        <label>Thời gian bắt đầu:</label>
                        <input type="time" id="time1" name="time1" class="form-control my-2"/>
                    </div>
                    
                    <div class="form-group mb-3">
                        <label>Thời gian kết thúc:</label>
                        <input type="time" id="time1" name="time2" class="form-control my-2"/>
                    </div>

                    <button type="submit" class="btn btn-primary">Lưu thời gian</button>
                </form>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.quilljs.com/1.3.6/quill.js"></script>
<script>
    $(document).ready(function () { 
        var quill = new Quill('#editor', {
            placeholder: 'Nhập nội dung câu trả lời',
            theme: 'bubble'
        });
        
        var contentField = document.getElementById('content');
        quill.on('text-change', function() {
            contentField.value = quill.root.innerHTML;
        });
        
//        XỬ LÝ THAY ĐỔI THỜI GIAN
        var modal = $('#settingModal');

        $('#openModal').click(function () {
            modal.modal('show');
        });
        
        $('#closeModal').click(function () {
            modal.modal('hide'); // Đóng modal
        });
       
        
        $("#time1").on('change', function () {
            var timePickerValue = $("#time1").val();
            $("#set1").val(timePickerValue);
        }); 
        
        $("#time2").on('change', function () {
            var timePickerValue = $("#time2").val();
            $("#set2").val(timePickerValue);
        }); 
    
    //        XỬ LÝ MODAL THÊM FAQ
        var modals = $('#addFAQsModal');

        $('#openF').click(function () {
            modals.modal('show');
        });

        $('#closeF').click(function () {
            modals.modal('hide'); // Đóng modal
        });

        $("#addFAQForm").submit(function (e) {
            var title = $('#title').val();
            var content = $('#content').val();
            
            console.log(quill.root.innerHTML);
            console.log(content);

            // Kiểm tra xem title và content có trống không
            if (title.trim() === '' || content.trim() === '') {
                // Nếu có lỗi, ngăn chặn gửi form và hiển thị thông báo
                e.preventDefault();
                alert('Vui lòng điền đầy đủ thông tin câu hỏi và câu trả lời.');
            }
        });

    //        XỬ LÝ SỰ KIỆN XÓA FAQ
        var delModal = $('#delModal');

        $(".openDelModal").click(function () {
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
