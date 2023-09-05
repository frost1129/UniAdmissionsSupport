<%-- 
    Document   : f-year-score
    Created on : Sep 5, 2023, 5:16:41 PM
    Author     : prodi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<head>
    <link href="https://cdn.quilljs.com/1.3.6/quill.snow.css" rel="stylesheet">
</head>
<body>
    <c:url value="/admin/faculties/${faculty.id}/scores/add-or-update" var="addOrUpdate"/>

    <form:form 
        cssClass="py-3 container-fluid" 
        modelAttribute="admissionScore" 
        method="post" 
        action="${addOrUpdate}" 
        enctype="multipart/form-data"
    >
        <form:errors path="*" element="div" cssClass="alert alert-danger mt-1"/>

        <form:input type="hidden" path="content" name="content" id="content-field" />
        
        <div class="mb-4 text-primary-emphasis">
            <h4>Điểm tuyển sinh khoa ${faculty.name}</h4>
        </div>
        
        <div class="mb-2">
            <label class="form-label">Năm</label>
            <form:input type="text" class="form-control" path="id" placeholder="Năm tuyển sinh" />
            <form:errors path="id" element="div" cssClass="alert alert-danger mt-1"/>
        </div>
        
        <div class="mb-3">
            <label class="form-label">Thông tin điểm tuyển sinh</label>
            <div id="editor"></div>   
            <form:errors path="content" element="div" cssClass="alert alert-danger mt-1"/>
        </div>
        <button type="submit" class="btn btn-primary">Lưu điểm tuyển sinh</button>
        <c:if test="${not empty admissionScore.id}">
            <a href="#" class="btn btn-danger">Xóa điểm tuyển sinh</a>
        </c:if>
    </form:form>
</body>

<script src="https://cdn.quilljs.com/1.3.6/quill.js"></script>
<script>
    var quill = new Quill('#editor', {
        modules: {
            toolbar: [
              [{ header: [1, 2, 3, 4, 5, false] }],
              ["bold", "italic", "underline", "strike"],
              [{ color: [] }, { background: [] }],
              [{ script: "sub" }, { script: "super" }],
              ["blockquote", "code-block", "image"],
              [{ list: "ordered" }, { list: "bullet" }],
              [{ indent: "-1" }, { indent: "+1" }, { align: [] }]
            ]
        },
        placeholder: 'Nhập nội dung bài đăng...',
        theme: 'snow'
    });
    
    var contentField = document.getElementById('content-field');
    
    var contentValue = contentField.value;
    if (contentValue) {
        quill.root.innerHTML = contentValue;
    }

    quill.on('text-change', function() {
        contentField.value = quill.root.innerHTML;
    });
</script>