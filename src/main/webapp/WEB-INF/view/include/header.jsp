<%@ page language="java" %>

<header>
    <sec:authorize access="isAnonymous()">
        <div class="header">
            <a href="/user/signin">Sign In</a>
            <a href="/user/signup">Sign Up</a>
        </div>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <form:form action="/user/logout" method="POST">
            <button type="submit" class="logout-link">Logout</button>
        </form:form>
    </sec:authorize>
</header>
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