<%-- 
    Document   : index
    Created on : Aug 15, 2023, 8:52:56 PM
    Author     : prodi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="py-2 container-fluid">
    <h4 class="">
        Banner
    </h4>
    <div class="row d-flex flex-row flex-nowrap py-2" style="overflow-x: auto;">
        <div class="p-2 me-2 card" style="width: 12rem;">
            <a class="text-decoration-none" href="/admin">
                <div class="d-flex justify-content-center align-items-center rounded fw-bolder" style="height: 11rem;">
                    +
                </div>
            </a>
        </div>
        <c:forEach items="${banners}" var="b">
            <div class="pt-2 px-2 me-2 card" style="width: 12rem;">
                <img class="card-img" alt="Banner" src="${b.image}" />
                <div class="text-center card-body">
                    <button type="button" class="btn btn-danger">
                        Xóa
                    </button>
                </div>
            </div>
        </c:forEach>
        
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

