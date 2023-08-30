<%-- 
    Document   : header
    Created on : Aug 29, 2023, 9:04:56 PM
    Author     : prodi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="bg-dark text-white fixed-bottom col-xl-2 col-3" style="height: 100vh; top: 0px; left: 0px; padding: 2rem 1rem;">
    <a class="text-decoration-none d-flex align-items-center" href="<c:url value="/"/>">
        <img src="https://res.cloudinary.com/dbh8vdpi7/image/upload/v1693374553/ou_logo_long_white_eojuky.png" alt="OU logo" class="py-5 img-fluid">
    </a>
    <div class="flex-column nav nav-pills">
        <small class="fw-bold text-uppercase mt-3">Thông tin chung</small>
        <li class="nav-item mb-2">
            <a class="nav-link text-white" href="<c:url value="/" />">Trang chủ</a>
        </li>
        <li class="nav-item mb-2">
            <a class="nav-link text-white" href="#">Quản lý người dùng</a>
        </li>
        <li class="nav-item mb-2">
            <a class="nav-link text-white" href="#">Quản lý khoa</a>
        </li>
        <li class="nav-item mb-2">
            <a class="nav-link text-white" href="#">Quản lý câu hỏi</a>
        </li>
        
        <small class="fw-bold text-uppercase mt-3">
            Thông tin tuyển sinh
        </small>
        <li class="nav-item mb-2">
            <a class="nav-link text-white" href="#">Bài đăng tuyển sinh</a>
        </li>
        <li class="nav-item mb-2">
            <a class="nav-link text-white" href="#">Livestream tuyển sinh</a>
        </li>
    </div>
</div>

<script>
    $(document).ready(function() {
  // Lấy đường dẫn hiện tại
  var currentPath = window.location.pathname;

  // Duyệt qua các liên kết và kiểm tra nếu đường dẫn trùng khớp
  $('.nav-pills li.nav-item a.nav-link').each(function() {
      var linkPath = $(this).attr('href');
      
      if (linkPath === currentPath) {
        $(this).addClass('active');
      } else {
        $(this).removeClass('active');
      }
    });
});
</script>
