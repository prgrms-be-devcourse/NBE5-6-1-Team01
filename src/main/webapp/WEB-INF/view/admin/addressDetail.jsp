<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/include/page.jsp" %>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <%@ include file="/WEB-INF/view/include/static.jsp" %>
</head>
<body>
<div id="popup">
  <div class="row justify-content-center m-4">
    <h2>주문 상세정보</h2>
  </div>
  <div class="card">
    <div class="row-button">
    <h5 class="mt-3" style="margin-left: 15px">${orderInfo.createdAt} (order-id: ${orderInfo.orderId})</h5>
    </div>
    <h6 style="margin-left: 25px">
      <div>${orderInfo.address} (${orderInfo.postcode})</div>
    </h6>
      <ul style="margin-right: 20px">
        <c:forEach var="item" items="${items}">
          <li class="list-group-item mt-1">
              ${item.itemName} 가격: ${item.itemPrice} 수량: ${item.itemCount}
          </li>
        </c:forEach>
      </ul>

    <div class="row-button" style="margin-left: 15px; margin-right: 15px">
      <h5 class="left">전체 금액: <c:out value="${orderInfo.totalPrice}"/>원</h5>
      <h6 class="right"><c:out value="${orderInfo.orderStatus}"/></h6>
    </div>
  </div>
</div>
</body>
</html>