<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/view/include/page.jsp" %>
<html>
<head>
    <title>Hello, World!</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%@include file="/WEB-INF/view/include/static.jsp" %>
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
            <a href="/admin/mypage">Admin Page</a>
            <form:form action="/user/logout" method="POST">
                <button type="submit" class="logout-link">Logout</button>
            </form:form>
        </div>
</header>
<div class="row justify-content-center m-4">
    <h1 class="text-center">상품 관리</h1>
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
                <form:form action="${context}/admin/item/add" id="itemForm"
                           modelAttribute="itemRegistForm" enctype="multipart/form-data"
                           method="POST">
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <form:input type="hidden" path="itemId" id="itemId"/><!-- 수정용 itemId -->
                    <form:input type="hidden" id="itemImgId"  path="itemImgId"/>
                    <div class="mb-3">
                        <label for="itemImg" class="form-label">상품 이미지</label>
                        <input type="file" class="form-control" id="itemImg" name="img"/>
                        <span id="itemImgName" class="mt-2 text-muted"></span>
                    </div>
                    <div class="mb-3">
                        <label for="itemType" class="form-label">상품 타입</label>
                        <form:input type="text" class="form-control mb-1" path="itemType"
                                    id="itemType" placeholder="상품 타입 입력"/>
                    </div>
                    <div class="mb-3">
                        <label for="itemName" class="form-label">상품명</label>
                        <form:input type="text" class="form-control mb-1" path="itemName"
                                    id="itemName" placeholder="상품명 입력"/>
                    </div>
                    <div class="mb-3">
                        <label for="itemPrice" class="form-label">상품가격</label>
                        <form:input type="number" class="form-control" path="itemPrice"
                                    id="itemPrice" placeholder="상품가격 입력"/>
                    </div>
                    <hr>
                        <button type="submit" class="btn btn-dark col-12 mb-2" id="submitBtn">등록</button>
                    <button type="reset" class="btn btn-secondary col-12" id="cancelBtn">취소</button>
                </form:form>
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
      const response = await fetch(location.origin + "/api/item/list");
      const data = await response.json();

      const items = data.data.itemInfos || {};
      const container = document.getElementById("item-list");
      container.innerHTML = "";

      if (items.length === 0) {
        container.innerHTML = "<li class='list-group-item'>상품이 없습니다.</li>";
        return;
      }

      items.forEach(item => {
        const li = document.createElement("li");
        li.classList.add("list-group-item", "d-flex", "mt-3");

        const imageUrl = item.img ? item.img.replace('/download/', '/upload/') : '/default-image.jpg';

        li.innerHTML = `
          <div class="row w-100">
            <div class="col-2">
              <img class="img-fluid" src="\${imageUrl}" alt="사진 오류"/>
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
    const csrfToken = document.querySelector('input[name="_csrf"]').value;

    try {

      const response = await fetch(`/admin/item/\${itemId}/remove`, {
        method: 'DELETE',
        headers: {
          'X-CSRF-TOKEN': csrfToken
        }
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

    console.log("선택한 상품 정보: ", item);
    document.getElementById('form-title').innerText = '상품 수정';
    document.getElementById('submitBtn').innerText = '수정 완료';

    document.getElementById('itemId').value = item.itemId;
    document.getElementById('itemImgId').value = item.itemImgId;
    document.getElementById('itemType').value = item.itemType;
    document.getElementById('itemName').value = item.itemName;
    document.getElementById('itemPrice').value = item.itemPrice;
    document.getElementById('itemImgName').textContent = item.originFileName ? item.originFileName : '이미지 없음';

    const form = document.getElementById('itemForm');
    form.action = `/admin/item/\${itemId}/update`;

  }

  // 폼 제출 (등록 or 수정)
  const form = document.getElementById('itemForm');
  form.addEventListener('submit', async (event) => {
    event.preventDefault();

    const itemId = document.getElementById('itemId').value;
    const itemImgId = document.getElementById('itemImgId').value;
    const formData = new FormData();
    const csrfToken = document.querySelector('input[name="_csrf"]').value;
    formData.append('_csrf', csrfToken);

    const itemType = document.getElementById('itemType').value;
    const itemName = document.getElementById('itemName').value;
    const itemPrice = document.getElementById('itemPrice').value;

    const img = document.getElementById('itemImg').files[0];
    if (img) {
      formData.append('img', img);
    }

    formData.append('itemImgId', itemImgId);
    formData.append('itemType', itemType);
    formData.append('itemName', itemName);
    formData.append('itemPrice', itemPrice);

    let url = '/admin/item/add';
    let method = 'POST';

    if (itemId) {
      url = `/admin/item/\${itemId}/update`;
      method = 'POST';
      formData.append('itemId', itemId);
    }

    const response = await fetch(url, {
      method,
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

  // 등록 or 수정 취소
  form.addEventListener('reset', async (event) => {
    event.preventDefault();
    form.reset();

    document.getElementById('form-title').innerText = '상품 등록';
    document.getElementById('submitBtn').innerText = '등록';

    document.getElementById('itemId').value = '';
    document.getElementById('itemImg').value = '';
    document.getElementById('itemType').value = '';
    document.getElementById('itemName').value = '';
    document.getElementById('itemPrice').value = '';
    document.getElementById('itemImgName').textContent = '';
  });
</script>

</body>
</html>
