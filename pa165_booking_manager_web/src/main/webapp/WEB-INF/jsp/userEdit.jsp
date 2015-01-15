<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<a class="btn btn-default" href="${pageContext.request.contextPath}/userList"><fmt:message key="general.back"/></a>


<div class="panel panel-default">
<!-- Default panel contents -->
<div class="panel-heading">
<c:choose>
    <c:when test="${user.id != null}">
        <b><fmt:message key="user.edit"/></b></div>
    </c:when>
    <c:otherwise>
        <b><fmt:message key="user.create"/></b></div>
    </c:otherwise>
</c:choose>


<form:form action="#" method="POST" role="form" accept-charset="UTF-8" commandName="userTo">
    <div class="form-group">
        <label for="firstNameInput"><fmt:message key="user.firstName"/></label>
        <input type="text" name="firstName" id="firstNameInput" value="${user.firstName}" class="form-control"
               placeholder="<fmt:message key="user.firstName.enter"/>"/>
        <font color="red"><form:errors path="firstName" cssClass="error"/></font>
    </div>

    <div class="form-group">
        <label for="lastNameInput"><fmt:message key="user.lastName"/></label>
        <input type="text" name="lastName" id="lastNameInput" value="${user.lastName}" class="form-control"
               placeholder="<fmt:message key="user.lastName.enter"/>"/>
        <font color="red"><form:errors path="lastName" cssClass="error"/></font>
    </div>

    <div class="form-group">
        <label for="emailInput"><fmt:message key="user.email"/></label>
        <input type="text" name="email" id="emailInput" value="${user.email}" class="form-control"
               placeholder="<fmt:message key="user.email.enter"/>"/>
        <font color="red"><form:errors path="email" cssClass="error"/></font>
    </div>

    <div class="form-group">
        <label for="usernameInput"><fmt:message key="user.username"/></label>
        <input type="text" name="username" id="usernameInput" value="${user.username}" class="form-control"
               placeholder="<fmt:message key="user.username.enter"/>"/>
        <font color="red"><form:errors path="username" cssClass="error"/></font>
    </div>

    <div class="form-group">
        <label for="passwordInput"><fmt:message key="user.password"/></label>
        <input type="password" name="password" id="passwordInput" value="${user.password}" class="form-control"
               placeholder="<fmt:message key="user.password.enter"/>"/>
        <font color="red"><form:errors path="password" cssClass="error"/></font>
    </div>

    <div class="form-group">
        <label for="isAdmin"><fmt:message key="user.isAdmin"/></label>
        <input type="checkbox" name="isAdmin" id="isAdmin" value="True" ${user.admin == 'true' ? 'checked':''}>
    </div>

    <input type="hidden"
           name="${_csrf.parameterName}"
           value="${_csrf.token}"/>

    <button type="submit" class="btn btn-primary"><fmt:message key="general.submit"/></button>

</form:form>
</div>