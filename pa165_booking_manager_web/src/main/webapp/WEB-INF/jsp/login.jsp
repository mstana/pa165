<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url value="/j_spring_security_check" var="loginUrl"/>
<form action="${loginUrl}" method="post">
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
  <p>
    <label for="username"><fmt:message key="login.username"/></label>
    <input type="text" id="username" name="username"/>
  </p>
  <p>
    <label for="password"><fmt:message key="login.password"/></label>
    <input type="password" id="password" name="password"/>
  </p>
  <input type="hidden"
         name="${_csrf.parameterName}"
         value="${_csrf.token}"/>
  <button type="submit" class="btn btn-primary"><fmt:message key="login.loginButton"/></button>
</form>