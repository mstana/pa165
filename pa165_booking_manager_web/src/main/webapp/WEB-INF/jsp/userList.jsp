<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<div xmlns:c="http://java.sun.com/jsp/jstl/core" xmlns:jsp="http://java.sun.com/JSP/Page">


    <div class="panel panel-default">
  <!-- Default panel contents -->
  <div class="panel-heading"><fmt:message key="user.list"/></div>
  <a href="userCreate"><input type="button" class="btn btn-default" value="<fmt:message key="user.create"/>" name="addUser"/></a>

  <!-- Table -->
  <table class="table">
      <tr>
          <th><fmt:message key="user.id"/></th>
          <th><fmt:message key="user.firstName"/></th>
          <th><fmt:message key="user.lastName"/></th>
          <th><fmt:message key="user.email"/></th>
          <th><fmt:message key="user.isAdmin"/></th>
      </tr>

      <c:forEach items="${listUsers}" var="user">
       <tr>            

          <td>${user.id}</td>
          <td>${user.firstName}</td>
          <td>${user.lastName}</td>
          <td>${user.email}</td>
          <td><tags:yesno value="${user.isAdmin}"/></td>
          <td>
               <a class="btn btn-default btn-xs" aria-label="Left Align" href="${pageContext.request.contextPath}/userEdit/${user.id}"><span class="glyphicon glyphicon-pencil" style="color: darkgreen;" aria-hidden="true"></span></a>
               <a onclick="return confirm('<fmt:message key="room.confirm.delete"/>');" class="btn btn-default btn-xs" aria-label="Left Align" href="${pageContext.request.contextPath}/userDelete/${user.id}"><span class="glyphicon glyphicon-remove" style="color: red;" aria-hidden="true"></span></a>
          </td>
        </tr>
    </c:forEach>

  </table>

</div>



</div>