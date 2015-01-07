<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url value="/j_spring_security_check" var="loginUrl"/>
<form class="form-signin" action="${loginUrl}" method="post">
  <h2 class="form-signin-heading"><fmt:message key="login.please"/></h2>
  <c:if test="${param.error != null}">
    <p>
      <fmt:message key="login.invalidLogin"/>
    </p>
  </c:if>
  <c:if test="${param.logout != null}">
    <p>
      <fmt:message key="login.logout"/>
    </p>
  </c:if>
    <label for="username" class="sr-only" ><fmt:message key="login.username"/></label>
    <input type="text" id="username" class="form-control" name="username" required autofocus placeholder="email@example.com"/>
    <label for="password" class="sr-only"><fmt:message key="login.password"/></label>
    <input type="password" id="password" class="form-control" name="password" placeholder="******"/>
  <input type="hidden"
         name="${_csrf.parameterName}"
         value="${_csrf.token}"/>
  <button type="submit" class="btn btn-lg btn-primary btn-block"><fmt:message key="login.loginButton"/></button>
</form>
