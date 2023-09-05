<%-- 
    Document   : questions
    Created on : Sep 2, 2023, 9:45:31 AM
    Author     : prodi
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="py-2 container-fluid">
    <h3 class="">
        Quản lý câu hỏi người dùng
    </h3>
    
    <div class="row">
        <div>
            <p class="bold">
                Thời gian cho phép gửi câu hỏi: 
                <b>${times.fromTime} - ${times.toTime}</b>
            </p>
            <button type="button" id="openModal" class="btn btn-outline-success">Sửa</button>
        </div>
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
<!--        <tbody>
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
                        <c:url value="/create-post/${p.id}" var="update" />
                        <a href="${update}" class="btn btn-success rounded-pill p-0 px-2">Cập nhật</a>
                        <button class="btn btn-danger rounded-pill p-0 px-2">Xóa</button>
                    </td>
                </tr>
            </c:forEach>
        </tbody>-->
    </table>
</div>
            
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
                
<script>
    $(document).ready(function () {
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
    });
</script>
