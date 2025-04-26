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
<div class="card mypage">
    <div class="row-button">
        <h3><c:out value="${user.email}"/></h3>

        <div class="text-end mt-3 mb-3">
            <form action="/user/update" method="get">
                <button type="submit" class="btn btn-outline-dark">비밀번호 수정</button>
            </form>
        </div>
    </div>
    <h5 class="flex-grow-0 left"><b>주문 목록</b></h5>
    <c:if test="${empty orderList}">
        <p>주문 내역이 없습니다.</p>
    </c:if>
    <div class="col-md-12 mt-4 d-flex align-items-start p-3 pt-0">
        <ul class="list-group products">
            <!-- 아이템 리스트 -->
            <c:forEach items="${orderList}" var="order">
                <li class="list-group-item mt-1">
                    <div class="row-button">
                        <h5><c:out value="${order.createdAt}"/></h5>

                        <c:if test="${order.orderStatus eq 'ORDER'}">
                            <div class="text-end mt-3 mb-3">
                                <form action="/user/mypage" method="post" onsubmit="return confirm('정말로 주문을 취소하시겠습니까?');">
                                    <input type="hidden" name="orderid" value="${order.orderId}">
                                    <button type="submit" class="btn btn-outline-dark">취소</button>
                                </form>
                            </div>
                        </c:if>
                    </div>
                    <h6>주소: <c:out value="${order.address}"/></h6>

                    <div class="row-button">
                        <h5 class="left">전체 금액: <c:out value="${order.totalPrice}"/>원</h5>
                        <h6 class="right"><c:out value="${order.orderStatus}"/></h6>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </div>


</div>
</body>
</html>
