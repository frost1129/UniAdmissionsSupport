<%-- 
    Document   : index
    Created on : Aug 15, 2023, 8:52:56 PM
    Author     : prodi
--%>

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
        <div class="pt-2 px-2 me-2 card" style="width: 12rem;">
            <img class="card-img" alt="Banner" src="https://dummyimage.com/600x400/aaa/fff">
                <div class="text-center card-body">
                    <button type="button" class="btn btn-danger">
                        Xóa
                    </button>
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
                    <a class="btn p-0 px-1 m-0" href="/admin">
                        <svg aria-hidden="true" focusable="false" data-prefix="fas" data-icon="circle-info" class="svg-inline--fa fa-circle-info " role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512" style="color: rgb(46, 46, 255);">
                        <path fill="currentColor" d="M256 512A256 256 0 1 0 256 0a256 256 0 1 0 0 512zM216 336h24V272H216c-13.3 0-24-10.7-24-24s10.7-24 24-24h48c13.3 0 24 10.7 24 24v88h8c13.3 0 24 10.7 24 24s-10.7 24-24 24H216c-13.3 0-24-10.7-24-24s10.7-24 24-24zm40-208a32 32 0 1 1 0 64 32 32 0 1 1 0-64z">

                        </path>
                        </svg>
                    </a>
                    <a class="btn p-0 px-1 m-0" href="/admin">
                        <svg aria-hidden="true" focusable="false" data-prefix="fas" data-icon="pencil" class="svg-inline--fa fa-pencil " role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512"><path fill="currentColor" d="M410.3 231l11.3-11.3-33.9-33.9-62.1-62.1L291.7 89.8l-11.3 11.3-22.6 22.6L58.6 322.9c-10.4 10.4-18 23.3-22.2 37.4L1 480.7c-2.5 8.4-.2 17.5 6.1 23.7s15.3 8.5 23.7 6.1l120.3-35.4c14.1-4.2 27-11.8 37.4-22.2L387.7 253.7 410.3 231zM160 399.4l-9.1 22.7c-4 3.1-8.5 5.4-13.3 6.9L59.4 452l23-78.1c1.4-4.9 3.8-9.4 6.9-13.3l22.7-9.1v32c0 8.8 7.2 16 16 16h32zM362.7 18.7L348.3 33.2 325.7 55.8 314.3 67.1l33.9 33.9 62.1 62.1 33.9 33.9 11.3-11.3 22.6-22.6 14.5-14.5c25-25 25-65.5 0-90.5L453.3 18.7c-25-25-65.5-25-90.5 0zm-47.4 168l-144 144c-6.2 6.2-16.4 6.2-22.6 0s-6.2-16.4 0-22.6l144-144c6.2-6.2 16.4-6.2 22.6 0s6.2 16.4 0 22.6z">

                        </path>
                        </svg>
                    </a>
                </td>
            </tr>
        </tbody>
    </table>
</div>

