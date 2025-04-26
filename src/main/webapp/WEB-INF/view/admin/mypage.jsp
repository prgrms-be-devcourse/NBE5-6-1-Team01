<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/view/include/page.jsp" %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%@ include file="/WEB-INF/view/include/static.jsp" %>
</head>
<body>
<%@ include file="/WEB-INF/view/include/admin-header.jsp"%>
<div class="row justify-content-center m-4">
    <h1 class="text-center">Admin Page</h1>
</div>
<div class="card admin-card">
    <div class="row scrollable-content">
        <c:if test="${not empty orders}">

            <c:set var="currentEmail" value="" scope="page" />

            <ul>
                <c:forEach items="${orders}" var="order">
                    <c:if test="${order.email ne currentEmail}">
                        <c:set var="currentEmail" value="${order.email}" scope="page" />
                        <h5 class="mt-3">${order.email}</h5>
                    </c:if>
                    <li class="list-group-item mt-1">
                        <div class="row-button admin-order-info">
                            <h5>${order.createdAt} (${order.orderId})</h5>
                            <c:if test="${order.orderStatus eq 'ORDER'}">
                                <div class="text-end mt-3 mb-3">
                                    <form action="/admin/mypage" method="post" onsubmit="return confirm('정말로 주문을 취소하시겠습니까?');">
                                        <input type="hidden" name="orderid" value="${order.orderId}">
                                        <button type="submit" class="btn btn-outline-dark">취소</button>
                                    </form>
                                </div>
                            </c:if>
                        </div>
                        <h6>주소: <c:out value="${order.address}"/></h6>
                        <h6>
                            item:
                            <c:forEach var="item" items="${itemMap[order.orderId]}" varStatus="itemStatus">
                                ${item}<c:if test="${!itemStatus.last}">, </c:if>
                            </c:forEach>
                        </h6>
                        <div class="row-button">
                            <h6 class="right"><c:out value="${order.orderStatus}"/></h6>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </c:if>
        <c:if test="${empty orders}">
            <h3>현재 주문 내역이 없습니다</h3>
        </c:if>
    </div>
</div>
</body>
</html>