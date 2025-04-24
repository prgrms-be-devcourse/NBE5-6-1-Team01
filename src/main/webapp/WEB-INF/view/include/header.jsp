<%@ page language="java" %>
<header>
    <sec:authorize access="isAnonymous()">
        <div class="header">
            <a href="/user/signin">Sign In</a>
            <a href="/user/signup">Sign Up</a>
        </div>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <div class="header">
            <a href="/user/mypage">MyPage</a>
            <a href="/user/signin">Logout</a>
        </div>
    </sec:authorize>
</header>