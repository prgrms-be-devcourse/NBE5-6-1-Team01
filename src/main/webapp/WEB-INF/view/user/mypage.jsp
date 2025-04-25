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
<div class="header">
    <a href="/">Home</a>
    <%@include file="/WEB-INF/view/include/header.jsp"%>
</div>
<div class="row justify-content-center m-4">
    <h1 class="text-center">MyPage</h1>
</div>
<div class="card">
    <div class="row">
        <h2>${user.email}</h2>
        <!-- TODO: 주문내역 목록 불러오기 (DB 데이터 조회 확인을 임시로 비번으로 해둠) -->
        <h3>주문내역</h3>
        <p>${user.password}</p>
        <div class="text-end mt-3 mb-3">
            <form action="/user/update" method="get">
                <button type="submit" class="btn btn-outline-dark">비밀번호 수정</button>
            </form>
        </div>
    </div>
                    <div class="row-button">
                        <h5><c:out value="${order.createdAt}"/></h5>

                        <div class="text-end mt-3 mb-3">
                            <form action="/user/mypage" method="post">
                                <input type="hidden" name="orderid" value="${order.orderId}">
                                <button type="submit" class="btn btn-outline-dark">취소</button>
                            </form>
                        </div>
                    </div>
</div>
</body>
</html>
