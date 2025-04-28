<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/view/include/page.jsp" %>
<html>
<head>
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
    <div style="padding: 20px">
        <div class="row-button">
            <h3><c:out value="${user.email}"/>님</h3>

            <div class="text-end mt-3 mb-3">
                <form action="/user/update" method="get">
                    <button type="submit" class="btn btn-outline-dark">비밀번호 수정</button>
                </form>
            </div>
        </div>
    </div>
    <h4 class="text-center"><b>주문 목록</b></h4>
    <c:if test="${empty orderList}">
        <p>주문 내역이 없습니다.</p>
    </c:if>

    <div class="col-md-12 mt-4 d-flex align-items-start p-3 pt-0">
        <ul class="list-group products">
            <c:forEach items="${orderList.keySet()}" var="key">

                <!-- 주문상태 -->
                <c:if test="${key == 'ORDER'}">
                    <h5>배송 준비</h5>
                </c:if>
                <c:if test="${key == 'DELIVER'}">
                    <h5 style="margin-top: 30px">배송중</h5>
                </c:if>
                <c:if test="${key == 'CANCEL'}">
                    <h5 style="margin-top: 30px">배송 취소</h5>
                </c:if>

                <c:if test="${empty orderList[key]}">
                    <p>내역이 없습니다.</p>
                </c:if>
                <c:forEach items="${orderList[key]}" var="order">
                    <li class="list-group-item" style="padding-left: 30px; padding-right: 30px;">
                        <!-- 주문 내역 상단 주문일자, 취소 버튼 -->
                        <div>
                            <div class="row-button">
                                <h5><c:out value="${order.createdAt}"/></h5>
                                <c:if test="${order.orderStatus eq 'ORDER'}">
                                    <div class="text-end mt-3 mb-3">
                                        <form action="/user/mypage" method="post" onsubmit="return confirm('정말로 주문을 취소하시겠습니까?');">
                                            <input type="hidden" name="postcode" value="${order.postcode}">
                                            <button type="submit" class="btn btn-outline-dark">취소</button>
                                        </form>
                                    </div>
                                </c:if>
                            </div>
                            <h6>주소: <c:out value="${order.address}"/></h6>
                        </div>

                        <!-- 주문 상세 내역 -->
                        <div class="w-100">
                            <c:forEach items="${orderItemList[order.orderId]}" var="item">
                                <div class="row w-100 border p-2 mb-2 align-items-center">
                                    <div class="col-6 text-start">상품명: <c:out value="${item.itemName}"/></div>
                                    <div class="col-2 text-start">가격: <c:out value="${item.itemPrice}"/>원</div>
                                    <div class="col-2 text-start">수량: <c:out value="${item.itemCount}"/>개</div>

                                    <c:set var="totalPrice" value="${item.itemPrice * item.itemCount}" />
                                    <div class="col-2 text-start">총액: <c:out value="${totalPrice}"/>원</div>
                                </div>
                            </c:forEach>
                        </div>

                        <!-- 하단 전체 금액, 주문 상태 -->
                        <div class="row-button" style="margin-top: 30px">
                            <h5 class="left">전체 금액: <c:out value="${order.totalPrice}"/>원</h5>
                            <h5 class="right"><c:out value="${order.orderStatus}"/></h5>
                        </div>
                    </li>
                </c:forEach>
            </c:forEach>
        </ul>
    </div>
    <form class="right" action="/user/remove" method="post" onsubmit="return confirm('회원 탈퇴를 하시겠습니까?');">
        <input type="hidden" name="email" value="${user.userId}" />
        <button type="submit" class="btn btn-outline-dark">회원 탈퇴</button>
    </form>
</div>
</body>
</html>

<script>

</script>