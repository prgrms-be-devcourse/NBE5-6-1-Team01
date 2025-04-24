<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/include/page.jsp" %>
<html>
<head>
  <title>Grepp</title>
  <%@include file="/WEB-INF/view/include/static.jsp" %>
</head>
<body>
<%@include file="/WEB-INF/view/include/header.jsp" %>
<div class="row justify-content-center m-4">
  <h1 class="text-center">회원가입</h1>
</div>
<main class="container">
  <div class="card security">
    <form:form modelAttribute="signupRequest" action="/user/signup" method="post" id="signupRequest">
      <div class="mb-4">
        <label for="email" class="form-label">이메일</label>
        <form:input path="email" type="email" class="form-control mb-1"
                    id="email" placeholder="Email"/>
        <form:errors path="email" cssClass="helper-text"/>
        <span class="helper-text" id="emailCheckMsg" style="display: none"></span>
      </div>
      <div class="mb-4">
        <label for="password" class="form-label">비밀번호</label>
        <form:password path="password" class="form-control"
                       id="password" placeholder="Password"/>
        <form:errors path="password" cssClass="helper-text"/>
      </div>

      <div class="button-container">
        <button type="submit" name="action" class="btn btn-dark w-50">회원가입</button>
      </div>
    </form:form>
  </div>
</main>
<script>
  const validElement = document.querySelector('#emailCheckMsg');
  document.querySelector('#email').addEventListener('focusout', async ev => {
    const id = ev.target.value;
    if(!id) return;
    const response = await fetch('/api/user/exists/' + id);
    const data = await response.json();
    validElement.style.display = 'block';
    validElement.textContent = data.data ? '사용이 불가능한 아이디 입니다.' : '사용 가능한 아이디 입니다.';
  });

  document.querySelector('#signupRequest').addEventListener('submit', async ev => {
    // form tag 의 기본 이벤트 차단
    ev.preventDefault();

    const id = document.querySelector('#email').value;
    if(!id) return;
    const response = await fetch('/api/user/exists/' + id);
    const data = await response.json();

    if(data.data){
      document.querySelector('#email').focus();
      validElement.textContent = '사용이 불가능한 아이디 입니다.';
      return;
    }

    ev.target.submit();
  });
</script>
</body>
</html>