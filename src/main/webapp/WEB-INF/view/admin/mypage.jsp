<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/view/include/page.jsp" %>
<html>
<head>
<%--    <title>Hello, World!</title>--%>
<%--    <!-- Required meta tags -->--%>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%@include file="/WEB-INF/view/include/static.jsp" %>
</head>
<body class="container-fluid">
<%@include file="/WEB-INF/view/include/admin-header.jsp"%>
<div class="row justify-content-center m-4">
    <h1 class="text-center">Admin Page</h1>
</div>
<div class="card">
    <div class="row">
        <h2>${admin.email}</h2>
        <!-- TODO: 주문내역 목록 불러오기 (DB 데이터 조회 확인을 임시로 비번으로 해둠) -->
        <h3>주문내역</h3>
        <p>${admin.password}</p>
        <div class="text-end mt-3 mb-3">
            <form action="/user/update" method="get">
                <button type="submit" class="btn btn-outline-dark">비밀번호 수정</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
