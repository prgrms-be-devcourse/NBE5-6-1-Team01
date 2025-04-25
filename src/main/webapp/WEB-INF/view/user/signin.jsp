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
  <h1 class="text-center">로그인</h1>
</div>
<main class="container">

  <div class="card security">
    <c:if test="${not empty param.error}">
      <div id="alert-wrong-login">아이디나 비밀번호를 확인하세요</div>
    </c:if>
    <form:form modelAttribute="signinRequest" action="/user/signin" method="post" id="signinRequest">
      <div class="mb-4">
        <label for="email" class="form-label">이메일</label>
        <form:input path="email" type="email" class="form-control mb-1"
                    id="email" placeholder="Email"/>
        <form:errors path="email" cssClass="helper-text"/>
      </div>
      <div class="mb-4">
        <label for="password" class="form-label">비밀번호</label>
        <form:password path="password" class="form-control"
                       id="password" placeholder="Password"/>
        <form:errors path="password" cssClass="helper-text"/>
      </div>
      <div class="row">
        <p>
          <label>
            <input type="checkbox" name="remember-me" />
            <span>remember-me</span>
          </label>
        </p>
      </div>
      <div class="button-container">
        <button type="submit" name="action" class="btn btn-dark w-50">로그인</button>
      </div>
    </form:form>
  </div>
</main>

</body>
</html>