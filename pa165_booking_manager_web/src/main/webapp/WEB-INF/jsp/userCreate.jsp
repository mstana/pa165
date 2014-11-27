<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<div class="panel panel-default">
  <!-- Default panel contents -->
  <div class="panel-heading"><fmt:message key="user.create"/></div>

  <form action="#" method="post" role="form">
    <input type="hidden" value="${user.id}" />

    <div class="form-group">
      <label for="firstName"><fmt:message key="user.firstName"/></label>
      <input type="text" name="firstName" id="numberInput" value="${user.firstName}" class="form-control" placeholder="<fmt:message key="user.firstName.enter"/>"/>
    </div>

    <div class="form-group">
      <label for="lastName"><fmt:message key="user.lastName"/></label>
      <input type="text" name="lastName" id="priceInput" value="${user.lastName}" class="form-control" placeholder="<fmt:message key="user.lastName.enter"/>"/>
    </div>

    <div class="form-group">
      <label for="email"><fmt:message key="user.email"/></label>
      <input type="text" name="email" id="email" value="${user.email}" class="form-control" placeholder="<fmt:message key="user.email.enter"/>"/>
    </div>
    
    <div class="form-group">
      <label for="isAdmin"><fmt:message key="user.isAdmin"/></label>
       <input type="checkbox" name="isAdmin" id="isAdmin" value="True" ${user.isAdmin == 'true' ? 'checked':''}>
    </div>
    
    <button type="submit" class="btn btn-default"><fmt:message key="general.submit"/></button>
    <a href="/pa165/userList"><input type="button" class="btn btn-default" value="<fmt:message key="general.back"/>" name="back"/></a>

  </form>
</div>