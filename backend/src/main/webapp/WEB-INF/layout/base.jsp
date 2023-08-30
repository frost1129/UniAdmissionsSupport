<%-- 
    Document   : base
    Created on : Jul 24, 2023, 8:52:14 AM
    Author     : prodi
--%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <title><tiles:insertAttribute name="title"/></title>
    </head>
    <body>
        <tiles:insertAttribute name="header"/>
        <div class="row">
            <div class="col-xl-2 col-3"></div>
            <div class="col-xl-10 col-9">
                <tiles:insertAttribute name="content"/>
            </div>
        </div>
        
        <%--<tiles:insertAttribute name="footer"/>--%>
    </body>
</html>
