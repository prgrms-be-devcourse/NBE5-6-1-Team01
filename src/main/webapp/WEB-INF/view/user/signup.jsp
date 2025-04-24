<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/include/page.jsp" %>
<html>
<head>
  <title>Grepp</title>
  <%@include file="/WEB-INF/view/include/static.jsp" %>
</head>
<body>
<%@include file="/WEB-INF/view/include/header.jsp" %>
<main class="container">
  <div class="card security">
    <form:form modelAttribute="signupForm" action="/user/signup" method="post" id="signupForm">
      <div class="mb-4">
        <label for="email" class="form-label">이메일</label>
        <input type="email" class="form-control mb-1" id="email">
      </div>
      <div class="mb-4">
        <label for="password" class="form-label">비밀번호</label>
        <input type="text" class="form-control" id="password">
      </div>

      <div class="button-container">
        <button type="submit" name="action" class="btn btn-dark w-50">회원가입</button>
      </div>
    </form:form>
  </div>
</main>

</body>
</html>