<%-- 
    Document   : create-post
    Created on : Aug 30, 2023, 4:04:56 PM
    Author     : prodi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<head>
    <link href="https://cdn.quilljs.com/1.3.6/quill.snow.css" rel="stylesheet">
</head>
<body>
    <c:url value="/admin/create-post" var="add"/>
    <c:url value="/admin/update-post" var="update"/>
    <c:url value="/admin/add-or-update" var="action" />

    <form:form 
        cssClass="py-3 container-fluid" 
        modelAttribute="post" 
        method="post" 
        action="${action}" 
        enctype="multipart/form-data"
    >
        <form:errors path="*" element="div" cssClass="alert alert-danger mt-1"/>
        
        <form:input type="hidden" path="id"/>
        <form:input type="hidden" path="image"/>
        <form:input type="hidden" path="content" name="content" id="content-field" />
        <form:input type="hidden" path="allowComment" />
        <form:input type="hidden" path="allowQuestion" />
        
        <div class="mb-4 text-primary-emphasis">
            <h4>Chi tiết bài đăng</h4>
        </div>
        <div class="mb-2">
            <label class="form-label">Tiêu đề bài đăng</label>
            <form:input type="text" class="form-control" path="title" placeholder="Nhập tiêu đề bài viết ở đây" />
            <form:errors path="title" element="div" cssClass="alert alert-danger mt-1"/>
        </div>
        <div class="mb-3">
            <label for="file" class="form-label">Chọn ảnh cho bài đăng</label>
            <br>
            <c:if test="${not empty post.image}">
                <img class="mb-2" src="${post.image}" alt="${post.title}" width="120px"/>
            </c:if>
            <form:input cssClass="form-control" type="file" id="file" path="file" accept="image/png, image/gif, image/jpeg" />
            <form:errors path="file" element="div" cssClass="alert alert-danger mt-1"/>
        </div>
        <div class="mb-3 row">
            <div class="col">
                <label class="form-label">Loại bài đăng</label>
                <form:select class="form-select" path="postType">
                    <c:choose>
                        <c:when test="${post.postType == 'post'}">
                            <option value="post">Tin tuyển sinh</option>
                        </c:when>
                        <c:when test="${post.postType == 'livestream'}">
                            <option value="livestream">Livestream tuyển sinh</option>
                        </c:when>
                        <c:otherwise>
                            <option value="post">Tin tuyển sinh</option>
                            <option value="livestream">Livestream tuyển sinh</option>
                        </c:otherwise>
                    </c:choose>  
                </form:select>
            </div>
            <div class="col">
                <label class="form-label">Hệ tuyển sinh</label>
                <form:select class="form-select" path="admissionType">
                    <c:forEach items="${admissionTypes}" var="a">
                        <c:choose>
                            <c:when test="${a.id == post.admissionType.id}">
                                <option value="${a.id}" selected>${a.name}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${a.id}">${a.name}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </form:select>
            </div>
        </div>
        <div class="mb-3">
            <label class="form-label">Nội dung bài đăng</label>
            <div id="editor"></div>   
            <form:errors path="content" element="div" cssClass="alert alert-danger mt-1"/>
        </div>
            <c:choose>
                <c:when test="${post.id != null}">
                    <button type="submit" class="btn btn-primary">Lưu bài đăng</button>
                </c:when>
                <c:otherwise>
                    <button type="submit" class="btn btn-success">Đăng bài</button>
                </c:otherwise>
            </c:choose>
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