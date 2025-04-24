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
  <h1 class="text-center">비밀번호 변경</h1>
</div>
<main class="container">
  <div class="card security">
    <form:form modelAttribute="updateForm" action="/user/update" method="post" id="updateForm">
      <div class="mb-4">
        <label for="password" class="form-label">비밀번호</label>
        <form:password path="password" class="form-control"
                    id="password" placeholder="password"/>
        <form:errors path="password" cssClass="helper-text"/>
      </div>
      <div class="mb-4">
        <label for="newPassword" class="form-label">새 비밀번호</label>
        <form:password path="newPassword" class="form-control"
                       id="newPassword" placeholder="newPassword"/>
        <form:errors path="newPassword" cssClass="helper-text"/>
      </div>

      <div class="button-container">
        <button type="submit" name="action" class="btn btn-dark w-50">비밀번호 변경</button>
      </div>
    </form:form>
  </div>
</main>

</body>
</html>