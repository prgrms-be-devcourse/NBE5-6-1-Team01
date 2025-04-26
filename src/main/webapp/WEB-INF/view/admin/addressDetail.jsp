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
    <h2>주소 상세정보</h2>
    <p><strong>주소:</strong> ${order.address}</p>
    <c:if test="${not empty postcode}">
      <p><strong>우편번호:</strong> ${order.postcode}</p>
    </c:if>
    <%-- 추가 데이터가 필요하면 여기에 계속 작성 가능 --%>
  </div>
</body>
</html>