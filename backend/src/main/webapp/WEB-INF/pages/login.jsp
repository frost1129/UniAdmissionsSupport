<%-- 
    Document   : login
    Created on : Sep 2, 2023, 4:41:02 PM
    Author     : prodi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="col-xl-8 col-6 container p-3 m-5 ">
    <form class="col-7 mx-auto p-3 bg-white rounded shadow">
        <div class="mb-4">
            <h3 class="fw-bold">ĐĂNG NHẬP</h3>
        </div>
        <div class="mb-3 text-primary-emphasis input-group">
            <input type="text" class="form-control form-control-lg bg-light fs-6" placeholder="Địa chỉ Email" name="email">
        </div>
        <div class="mb-1 input-group">
            <input type="password" class="form-control form-control-lg bg-light fs-6" placeholder="Mật khẩu" name="password">
        </div>
        <div class="mb-5 d-flex justify-content-between input-group">
            <div class="form-check form-check">
                <input type="checkbox" id="formCheck" class="form-check-input">
                <label for="formCheck" class="text-secondary form-check-label">
                    <small>Ghi nhớ đăng nhập</small>
                </label>
            </div>
        </div>
        <div class="mb-3 input-group">
            <button type="button" class="btn-lg btn-primary w-100 fs-6 btn btn-primary">Đăng nhập</button>
        </div>
    </form>
</div>