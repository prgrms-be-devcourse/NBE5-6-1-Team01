<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/view/include/page.jsp" %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%@ include file="/WEB-INF/view/include/static.jsp" %>
    <style>
      .header button {
        text-decoration: none;
        color: black;
        font-weight: bold;
      }
      .logout-link {
        background: none;
        border: none;
        padding: 0;
        margin: 0;
        cursor: pointer; /* 마우스 포인터를 손가락으로 */
        font: inherit; /* 폰트 스타일 상속 */
      }
    </style>
</head>
<body class="container-fluid">
<header>
    <div class="header">
        <a href="/admin/itemManagement">Product Management</a>
        <form:form action="/user/logout" method="POST">
            <button type="submit" class="logout-link">Logout</button>
        </form:form>
    </div>
</header>
<div class="row justify-content-center m-4">
    <h1 class="text-center">Admin Page</h1>
</div>
<div class="card admin-card">
    <h5 style="margin-left: 20px">전체 유저 주문목록</h5>
    <div class="row scrollable-content">
        <c:if test="${not empty orderMap}">
            <c:forEach var="emailEntry" items="${orderMap}">
                <h5 class="mt-3">${emailEntry.key}</h5>
                <ul>
                    <c:forEach var="orderEntry" items="${emailEntry.value}">
                        <h6 style="margin-top: 7px">주소: ${orderEntry.key}(${orderEntry.value[0].postcode})</h6>
                        <c:forEach var="orderInfo" items="${orderEntry.value}">
                            <li class="list-group-item mt-1">
                                <div class="row-button">
                                    <h5>
                                            ${orderInfo.createdAt} (order-id: ${orderInfo.orderId})
                                        <form:form action="/admin/addressDetail" method="post" onsubmit="return openPopupAndSubmit(this);" style="display: inline;">
                                            <input type="hidden" name="orderid" value="${orderInfo.orderId}">
                                            <input type="submit" value="상세" class="btn btn-outline-dark" style="display: inline; margin-left: 10px; padding:2px 4px 2px 4px;">
                                        </form:form>
                                    </h5>
                                    <c:if test="${orderInfo.orderStatus eq 'ORDER'}">
                                        <div class="text-end">
                                            <form:form action="/admin/mypage" method="post" onsubmit="return confirm('정말로 주문을 취소하시겠습니까?');">
                                                <input type="hidden" name="orderid" value="${orderInfo.orderId}">
                                                <button type="submit" class="btn btn-outline-dark">취소</button>
                                            </form:form>
                                        </div>
                                    </c:if>
                                </div>
                                <h6>
                                    item:
                                    <c:forEach var="item" items="${orderInfo.itemNames}" varStatus="itemStatus">
                                        ${item}<c:if test="${!itemStatus.last}">, </c:if>
                                    </c:forEach>
                                </h6>
                                <div class="row-button">
                                    <h5 class="left">전체 금액: <c:out value="${orderInfo.totalPrice}"/>원</h5>
                                    <h6 class="right"><c:out value="${orderInfo.orderStatus}"/></h6>
                                </div>
                            </li>
                        </c:forEach>
                    </c:forEach>
                </ul>
            </c:forEach>
        </c:if>
        <c:if test="${empty orderMap}">
            <h3>현재 주문 내역이 없습니다</h3>
        </c:if>
    </div>
    <div class="button-container text-center mt-4">
        <h5 class="order-status">
            <span>전체 주문 건:
                <span class="badge" style="background-color: black">${statuses[0]}</span>
            </span>
            <span style="margin-left: 15px">
                취소된 주문 건: <span class="badge" style="background-color: #616161;">${statuses[1]}</span>
            </span>
            <span style="margin-left: 15px">
                배송중: <span class="badge" style="background-color: #e0e0e0; color: black">${statuses[2]}</span>
            </span>
        </h5>
    </div>
</div>
<script>
  function openPopupAndSubmit(form) {
    const popup = window.open('', 'popupWindow', 'width=800,height=600,left=850,top=400');
    form.target = 'popupWindow';
    return true;
  }
</script>
</body>
</html>