<%-- 
    Document   : f-scores
    Created on : Sep 5, 2023, 5:16:25 PM
    Author     : prodi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
    <link href="https://cdn.quilljs.com/1.3.6/quill.snow.css" rel="stylesheet">
</head>
<body>
    <div class="py-3 container-fluid">
        <div class="mb-4 text-primary-emphasis">
            <h4>Điểm tuyển sinh khoa ${faculty.name}</h4>
        </div>
        
        <div class="mb-3">
            <label class="form-label">Năm tuyển sinh</label>
            <select class="form-select" id="yearSelect">
                <c:choose>
                    <c:when test="${empty scores}">
                        <option value="" disabled="true" selected="true">Chưa có thông tin điểm tuyển sinh</option>
                    </c:when>
                    <c:otherwise>
                        <!--<option value="" disabled="true" selected="true">Chọn năm muốn xem</option>-->
                        <c:forEach items="${scores}" var="s" >
                            <option value="${s.content}">${s.year}</option>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>  
            </select>
        </div>
        
        <div class="mb-3">
            <a 
                href="<c:url value="/admin/faculties/${faculty.id}/scores/new" />" 
                class="btn btn-primary">
                Thêm điểm tuyển sinh
            </a>
        </div>
             
        <c:if test="${not empty scores}">
            <input type="hidden" id="facultyId" value="${faculty.id}" />
            <a 
                id="edtLink"
                href="#" 
                class="btn btn-outline-success mb-3">
                Chỉnh sửa thông tin điểm 
            </a>
            
            <div class="mb-3">
                <label class="form-label">Thông tin điểm tuyển sinh</label>
                <div id="editor"></div>   
                <form:errors path="content" element="div" cssClass="alert alert-danger mt-1"/>
            </div>
        </c:if>
        

    </div>
</body>

<script src="https://cdn.quilljs.com/1.3.6/quill.js"></script>
<script>
    $(document).ready(function () {
        var quill = new Quill('#editor', {
            theme: 'snow'
        });
        
        var yearSelect = $('#yearSelect');
        var facultyId = $('#facultyId').val();
        var editLink = $('#edtLink');
        
        quill.root.innerHTML = yearSelect.val();
        editLink.attr('href', '/notbackend/admin/faculties/' +facultyId+ '/scores/' + yearSelect.find('option:selected').text());
        quill.enable(false);
        
        yearSelect.on('change', function () {
            console.log(yearSelect.val());
            quill.root.innerHTML = yearSelect.val();
            editLink.attr('href', '/notbackend/admin/faculties/' +facultyId+ '/scores/' + yearSelect.find('option:selected').text());
        });
    });
</script>
