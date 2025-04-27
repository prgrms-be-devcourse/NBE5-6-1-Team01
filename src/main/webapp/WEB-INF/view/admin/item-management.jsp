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
<header>
        <div class="header">
            <a href="/admin/mypage">Admin Page</a>
            <a href="/user/logout">Logout</a>
        </div>
</header>
<div class="row justify-content-center m-4">
    <h1 class="text-center">Grids & Circle</h1>
</div>

<div class="card">
    <div class="row">
        <div class="col-md-8 mt-4 d-flex flex-column align-items-start p-3 pt-0">
            <h5 class="flex-grow-0"><b>상품 목록</b></h5>
            <ul class="list-group products" id="item-list">
                <!-- 아이템 리스트 동적으로 추가 -->
            </ul>
        </div>

        <div class="col-md-4 summary p-4">
            <div>
                <h5 class="m-0 p-0"><b id="form-title">상품 등록</b></h5>
                <hr>
                <form id="itemForm">
                    <input type="hidden" id="itemId" name="itemId" value=""> <!-- 수정용 itemId -->
                    <div class="mb-3">
                        <label for="itemType" class="form-label">상품 타입</label>
                        <input type="text" class="form-control mb-1" id="itemType" name="itemType" placeholder="상품 타입 입력">
                    </div>
                    <div class="mb-3">
                        <label for="itemName" class="form-label">상품명</label>
                        <input type="text" class="form-control mb-1" id="itemName" name="itemName" placeholder="상품명 입력">
                    </div>
                    <div class="mb-3">
                        <label for="itemPrice" class="form-label">상품가격</label>
                        <input type="number" class="form-control" id="itemPrice" name="itemPrice" placeholder="상품가격 입력">
                    </div>
                    <hr>
                    <button type="submit" class="btn btn-dark col-12" id="submitBtn">등록</button>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
  document.addEventListener("DOMContentLoaded", async () => {
    await loadItems();
  });

  async function loadItems() {
    try {
      const response = await fetch(location.origin + "/admin/item/list");
      const items = await response.json();
      const container = document.getElementById("item-list");
      container.innerHTML = "";

      if (items.length === 0) {
        container.innerHTML = "<li class='list-group-item'>상품이 없습니다.</li>";
        return;
      }

      items.forEach(item => {
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
            <div class="col text-center price">\${(item.itemPrice || item.itemPrice === 0) ? item.itemPrice + '원' : '가격 정보 없음'}</div>
            <div class="col text-end action">
              <button class="btn btn-small btn-outline-dark btn-update" data-item='\${JSON.stringify(item)}'>수정</button>
            </div>
            <div class="col text-end action">
              <button class="btn btn-small btn-outline-dark btn-delete" data-item-id="\${item.itemId}">삭제</button>
            </div>
          </div>
        `;

        container.appendChild(li);
      });

      // 삭제
      document.querySelectorAll(".btn-delete").forEach(button => {
        button.addEventListener("click", async (event) => {
          const itemId = event.target.getAttribute("data-item-id");
          await deleteItem(itemId);
        });
      });

      // 수정
      document.querySelectorAll(".btn-update").forEach(button => {
        button.addEventListener("click", (event) => {
          const item = JSON.parse(event.target.getAttribute("data-item"));
          fillFormForUpdate(item);
        });
      });

    } catch (error) {
      console.error("상품 목록 불러오기 실패:", error);
      document.getElementById("item-list").innerHTML =
          "<li class='list-group-item'>데이터를 불러오는 데 실패했습니다.</li>";
    }
  }

  // 상품 삭제
  async function deleteItem(itemId) {
    if (!confirm('정말 삭제하시겠습니까?')) return;

    try {
      const response = await fetch(`/admin/item/\${itemId}/remove`, {
        method: 'DELETE',
      });

      if (response.ok) {
        alert('상품이 삭제되었습니다.');
        await loadItems();
      } else {
        throw new Error('상품 삭제 실패');
      }
    } catch (error) {
      console.error('삭제 실패:', error);
      alert('상품 삭제에 실패했습니다.');
    }
  }

  // 폼을 수정 모드로
  function fillFormForUpdate(item) {
    document.getElementById('form-title').innerText = '상품 수정';
    document.getElementById('submitBtn').innerText = '수정 완료';

    document.getElementById('itemId').value = item.itemId;
    document.getElementById('itemType').value = item.itemType;
    document.getElementById('itemName').value = item.itemName;
    document.getElementById('itemPrice').value = item.itemPrice;
  }

  // 폼 제출 (등록 or 수정)
  const form = document.getElementById('itemForm');
  form.addEventListener('submit', async (event) => {
    event.preventDefault();

    const itemId = document.getElementById('itemId').value;
    const formData = new URLSearchParams(new FormData(form));

    let url = '/admin/item/add';
    let method = 'POST';

    if (itemId) {
      url = `/admin/item/\${itemId}/update`;
      method = 'PUT';
    }

    const response = await fetch(url, {
      method,
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
      },
      body: formData
    });

    if (response.ok) {
      alert(itemId ? '상품 수정 완료' : '상품 등록 완료');
      form.reset();
      document.getElementById('form-title').innerText = '상품 등록';
      document.getElementById('submitBtn').innerText = '등록';
      await loadItems();
    } else {
      alert(itemId ? '상품 수정 실패' : '상품 등록 실패');
    }
  });
</script>

</body>
</html>
