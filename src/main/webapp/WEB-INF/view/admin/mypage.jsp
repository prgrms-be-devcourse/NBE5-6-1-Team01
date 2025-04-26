<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/view/include/page.jsp" %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%@ include file="/WEB-INF/view/include/static.jsp" %>
</head>
<body class="container-fluid">
<header>
    <div class="header">
            <a href="/admin/itemManagement">Product Management</a>
            <a href="/user/logout">Logout</a>
    </div>
</header>
<div class="row justify-content-center m-4">
    <h1 class="text-center">Admin Page</h1>
</div>
<div class="card admin-card">
    <div class="row scrollable-content">
<%--TODO: 상세 버튼을 누르면 해당 주문 상세 정보 추가--%>
        <c:if test="${not empty orderMap}">
            <h5>전체 유저 주문목록</h5>
            <c:forEach var="emailEntry" items="${orderMap}">
                <h5 class="mt-3">${emailEntry.key}</h5>
                <ul>
                    <c:forEach var="orderEntry" items="${emailEntry.value}">
                        <h6 style="margin-top: 7px">주소: ${orderEntry.key}(${orderEntry.value.postcode})</h6>
                        <li class="list-group-item mt-1">
                            <div class="row-button admin-order-info">
                                <h5>${orderEntry.value.createdAt} (order-id: ${orderEntry.value.orderId})</h5>
                                <c:if test="${orderEntry.value.orderStatus eq 'ORDER'}">
                                    <div class="text-end">
                                        <form action="/admin/mypage" method="post" onsubmit="return confirm('정말로 주문을 취소하시겠습니까?');">
                                            <input type="hidden" name="orderid" value="${orderEntry.value.orderId}">
                                            <button type="submit" class="btn btn-outline-dark">취소</button>
                                        </form>
                                    </div>
                                </c:if>
                            </div>
                            <h6>
                                item:
                                <c:forEach var="item" items="${orderEntry.value.itemNames}" varStatus="itemStatus">
                                    ${item}<c:if test="${!itemStatus.last}">, </c:if>
                                </c:forEach>
                            </h6>
                            <div class="row-button">
                                <h5 class="left">전체 금액: <c:out value="${orderEntry.value.totalPrice}"/>원</h5>
                                <h6 class="right"><c:out value="${orderEntry.value.orderStatus}"/></h6>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </c:forEach>
        </c:if>
        <c:if test="${empty orderMap}">
            <h3>현재 주문 내역이 없습니다</h3>
        </c:if>
    </div>
</div>
</body>
</html>