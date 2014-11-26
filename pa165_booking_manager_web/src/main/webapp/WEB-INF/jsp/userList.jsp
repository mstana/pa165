<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<div xmlns:c="http://java.sun.com/jsp/jstl/core" xmlns:jsp="http://java.sun.com/JSP/Page">


    <div class="panel panel-default">
  <!-- Default panel contents -->
  <div class="panel-heading">User List</div>
  <a href="userCreate"><input type="button" value="Add User" name="addUser"/></a>

  <!-- Table -->
  <table class="table">
      <tr>
          <th>ID</th>
          <th>First Name</th>
          <th>Last Name</th>
          <th>Email</th>
      </tr>

      <c:forEach items="${listUsers}" var="user">
       <tr>
          <td>${user.id}</td>
          <td>${user.firstName}</td>
          <td>${user.lastName}</td>
          <td>${user.email}</td>
        </tr>
    </c:forEach>

  </table>

</div>



</div>