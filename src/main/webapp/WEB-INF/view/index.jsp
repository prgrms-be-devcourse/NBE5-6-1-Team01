<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/view/include/page.jsp" %>
<html>
<head>
    <title>Hello, World!</title>

    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <%@include file="/WEB-INF/view/include/static.jsp" %>
</head>
<body class="container-fluid">

<div class="header">
    <a href="/user/mypage">Mypage</a>
    <%@include file="/WEB-INF/view/include/header.jsp"%>
</div>
<div class="row justify-content-center m-4">
    <h1 class="text-center">Grids & Circle</h1>
</div>
<div class="card">
    <div class="row">
        <div class="col-md-8 mt-4 d-flex flex-column align-items-start p-3 pt-0">
            <h5 class="flex-grow-0"><b>상품 목록</b></h5>
            <ul class="list-group products">
                <!-- 아이템 리스트 -->
                <c:forEach items="${items}" var="item">
                    <li class="list-group-item d-flex mt-3"
                        data-item-id="${item.itemId}"
                        data-stock="${item.stock}"
                        data-count="0">
                        <div class="col-2"><img class="img-fluid" src="${item.img}" alt="사진 오류"/></div>
                        <div class="col">
                            <div class="row text-muted"><c:out value="${item.itemType}"/> </div>
                            <div class="row"><c:out value="${item.itemName}"/> </div>
                        </div>
                        <div class="col text-center price"><c:out value="${item.itemPrice}"/>원</div>
                        <div class="row small text-muted">재고: <c:out value="${item.stock}"/>개</div>

                        <div class="col text-end action d-flex flex-column">
                            <button type="button" class="btn btn-small btn-outline-black mb-1" onclick="addItem(this)">추가</button>
                            <button type="button" class="btn btn-small btn-outline-black" onclick="removeItem(this)">제거</button>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <div class="col-md-4 summary p-4">
            <div>
                <h5 class="m-0 p-0"><b>Summary</b></h5>
            </div>
            <hr>
            <!-- Order 리스트 -->
            <div id="summaryList">
                <c:forEach items="${items}" var="item">
                    <div class="row" id="summary-item-${item.itemId}">
                        <div class="col">
                            <c:out value="${item.itemName}"/>
                        </div>
                        <div class="col text-end">
                            <span class="badge bg-dark" id="summary-count-${item.itemId}">0개</span>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <!-- TODO: 결제버튼 클릭 시 action 추가, Form 클래스 추가 필요 -->
            <form:form action="${xxxx}/xxxx" method="post" enctype="" modelAttribute="">
                <div class="mb-3">
                    <label for="email" class="form-label">이메일</label>
                    <input type="email" class="form-control mb-1" id="email">
                </div>
                <div class="mb-3">
                    <label for="address" class="form-label">주소</label>
                    <input type="text" class="form-control mb-1" id="address">
                </div>
                <div class="mb-3">
                    <label for="postcode" class="form-label">우편번호</label>
                    <input type="text" class="form-control" id="postcode">
                </div>
                <div>당일 오후 2시 이후의 주문은 다음날 배송을 시작합니다.</div>
                <div class="row pt-2 pb-2 border-top">
                    <h5 class="col">총금액</h5>
                    <!-- TODO: totalPrice 가져오는 기능 필요 -->
                    <h5 class="col text-end"><c:out value="${order.totalPrice}"/></h5>
                </div>
                <button type="submit" name="action" class="btn btn-dark col-12">결제하기</button>
            </form:form>
        </div>
    </div>
</div>

<script>
  function addItem(button) {
    const item = button.closest('li');
    console.log('item: ', item);
    console.log('item.dataset.itemId: ', item.dataset.itemId);
    let count = parseInt(item.dataset.count, 10);
    const stock = parseInt(item.dataset.stock, 10);

    if (count < stock) {
      count++;
      item.dataset.count = count;
      updateItem(item);
    } else {
      alert('재고를 초과할 수 없습니다.');
    }
  }

  function removeItem(button) {
    const item = button.closest('li');
    console.log('item: ', item);
    console.log('item.dataset.itemId: ', item.dataset.itemId);
    let count = parseInt(item.dataset.count, 10);

    if (count > 0) {
      count--;
      item.dataset.count = count;
      updateItem(item);
    } else {
      alert('0개 이하로는 제거할 수 없습니다.');
    }
  }

  function updateItem(item) {
    console.log(' updateItem 호출됨');
    const itemId = item.dataset.itemId;
    const count = parseInt(item.dataset.count, 10);
    const stock = parseInt(item.dataset.stock, 10);

    console.log(count);
    console.log(itemId);
    // Summary에 수량 갱신
    const countBadge = document.getElementById(`summary-count-`+itemId);
    console.log(' 찾은 countBadge: ', countBadge);
    if (countBadge) {
      countBadge.textContent = count+`개`;
      console.log(' countBadge.textContent 바뀜:', countBadge.textContent);
    }

    // 버튼 활성화/비활성화
    const buttons = item.querySelectorAll('button');
    const addButton = buttons[0];
    const removeButton = buttons[1];

    addButton.disabled = (count >= stock);
    removeButton.disabled = (count <= 0);
  }
</script>
</body>
</html>
