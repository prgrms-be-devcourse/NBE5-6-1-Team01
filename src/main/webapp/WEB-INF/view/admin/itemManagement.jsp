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

<%@include file="/WEB-INF/view/include/admin-header.jsp" %>
<div class="row justify-content-center m-4">
    <h1 class="text-center">상품 관리</h1>
</div>
<div class="card">
    <div class="row">
        <div class="col-md-8 mt-4 d-flex flex-column align-items-start p-3 pt-0">
            <h5 class="flex-grow-0"><b>상품 목록</b></h5>
            <ul class="list-group products" id="item-list">
                <!-- 아이템 리스트 동적으로 추가-->
            </ul>
        </div>
        <div class="col-md-4 summary p-4">
            <div>
                <h5 class="m-0 p-0"><b>상품 등록/수정</b></h5>
            </div>
            <hr>
        </div>
    </div>
</div>

<script>
  document.addEventListener("DOMContentLoaded", async () => {
    try {
      const response = await fetch(location.origin + "/admin/item/list");
      const items = await response.json();

      const container = document.getElementById("item-list");
      // container.innerHTML = "";

      if (items.length === 0) {
        container.innerHTML = "<li class='list-group-item'>상품이 없습니다.</li>";
        return;
      }

      items.forEach(item => {
        console.log(item);
        console.log("typeof item.itemName:", typeof item.itemName);
        console.log(item.itemName);
        const li = document.createElement("li");
        li.classList.add("list-group-item", "d-flex", "mt-3");

        li.innerHTML = `
  <div class="row w-100">
<div class="col-2">
      <img class="img-fluid" src="\${item.img}" alt="사진 오류"/>
    </div>
    <div class="col">
      <div class="row text-muted">\${item.itemType || '정보 없음'}</div>
      <div class="row">\${item.itemName || '상품명 없음'}</div>
    </div>
    <div class="col text-center price">\${(item.itemPrice || item.itemPrice == 0) ? item.itemPrice + '원' : '가격 정보 없음'}</div>
    <div class="col text-end action">
      <a class="btn btn-small btn-outline-dark" href="#" data-item-id="\${item.itemId}">삭제</a>
    </div>
  </div>
`;
      });

    } catch (error) {
      console.error("상품 목록 불러오기 실패:", error);
      document.getElementById("item-list").innerHTML =
          "<li class='list-group-item'>데이터를 불러오는 데 실패했습니다.</li>";
    }
  });

</script>

</body>
</html>
