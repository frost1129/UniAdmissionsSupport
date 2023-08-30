<%-- 
    Document   : create-post
    Created on : Aug 30, 2023, 4:04:56 PM
    Author     : prodi
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<head>
    <link href="https://cdn.quilljs.com/1.3.6/quill.snow.css" rel="stylesheet">
</head>
<body>
    <c:url value="/create-post" var="action"/>
    
    <form:form 
        cssClass="py-3 container-fluid" 
        modelAttribute="post" 
        method="post" 
        action="${action}" 
        enctype="multipart/form-data"
    >
        <form:input type="hidden" path="content" name="content" id="content-field" />
        
        <div class="mb-4 text-primary-emphasis">
            <h4>Chi tiết bài đăng</h4>
        </div>
        <div class="mb-2">
            <label class="form-label">Tiêu đề bài đăng</label>
            <form:input type="text" class="form-control" path="content" placeholder="Nhập tiêu đề bài viết ở đây" />
        </div>
        <div class="mb-3 row">
            <div class="col">
                <label class="form-label">Loại bài đăng</label>
                <form:select class="form-select" path="postType">
                    <option value="post">Tin tuyển sinh</option>
                    <option value="livestream">Livestream tuyển sinh</option>
                </form:select>
            </div>
            <div class="col">
                <label class="form-label">Hệ tuyển sinh</label>
                <form:select class="form-select" path="admissionType">
                    
                    <c:forEach items="${admissionTypes}" var="a">
                        <option value="${a.id}">${a.name}</option>
                    </c:forEach>
                        
                </form:select>
            </div>
        </div>
        <div class="mb-3">
            <label class="form-label">Nội dung bài đăng</label>
            <div id="editor"></div>   
        </div>
        <button type="submit" class="btn btn-primary">Lưu bài đăng</button>
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

    quill.on('text-change', function() {
        contentField.value = quill.root.innerHTML;
    });
</script>