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
<%@ include file="/WEB-INF/view/include/admin-header.jsp"%>
<div class="row justify-content-center m-4">
    <h1 class="text-center">Admin Page</h1>
</div>
<div class="card">
    <div class="row scrollable-content">
        <c:if test="${not empty orders}">
            <c:set var="currentEmail" value="" scope="page" />
            <c:set var="currentOrderId" value="" scope="page" />

            <c:forEach var="order" items="${orders}">
                <c:if test="${order.email ne currentEmail}">
                    <c:set var="currentEmail" value="${order.email}" scope="page" />
                    <h5 class="mt-3">${order.email}</h5>
                </c:if>

                <c:if test="${order.orderId ne currentOrderId}">
                    <c:set var="currentOrderId" value="${order.orderId}" scope="page" />
                    <div class="ms-3 mb-2">
                        <small>
                                ${order.orderId} | ${order.createdAt}, ${order.address}, item:
                            <c:forEach var="item" items="${itemMap[order.orderId]}" varStatus="itemStatus">
                                ${item}<c:if test="${!itemStatus.last}">, </c:if>
                            </c:forEach>
                            (${order.orderStatus})
                        </small>
                    </div>
                </c:if>
            </c:forEach>
        </c:if>
        <c:if test="${empty orders}">
            <h3>현재 주문 내역이 없습니다</h3>
        </c:if>
    </div>
    <div class="row text-end mt-3 mb-3">
        <form action="/admin/remove" method="get">
            <button type="submit" class="btn btn-outline-dark">주문 삭제</button>
        </form>
    </div>
</body>
</html>