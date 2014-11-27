<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>



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


  <form action="#" method="post" role="form" accept-charset="UTF-8">
    <div class="form-group">
      <label for="firstNameInput"><fmt:message key="user.firstName"/></label>
      <input type="text" name="firstName" id="firstNameInput" value="${user.firstName}" class="form-control" placeholder="<fmt:message key="user.firstName.enter"/>"/>
    </div>

    <div class="form-group">
      <label for="lastNameInput"><fmt:message key="user.lastName"/></label>
      <input type="text" name="lastName" id="lastNameInput" value="${user.lastName}" class="form-control" placeholder="<fmt:message key="user.lastName.enter"/>"/>
    </div>

    <div class="form-group">
      <label for="emailInput"><fmt:message key="user.email"/></label>
      <input type="text" name="email" id="emailInput" value="${user.email}" class="form-control" placeholder="<fmt:message key="user.email.enter"/>"/>
    </div>
    
    
    
    
    
    <div class="form-group">
      <label for="isAdmin"><fmt:message key="user.isAdmin"/></label>
      <input type="checkbox" name="isAdmin" id="isAdmin" value="True" ${user.isAdmin == 'true' ? 'checked':''}>
    </div>
    <button type="submit" class="btn btn-primary"><fmt:message key="general.submit"/></button>
    <a class="btn btn-default" href="${pageContext.request.contextPath}/userList"><fmt:message key="general.back"/></a>

  </form>
</div>