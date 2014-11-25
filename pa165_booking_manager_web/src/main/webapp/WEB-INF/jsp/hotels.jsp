<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>



    <div class="panel panel-default">
  <!-- Default panel contents -->
  <div class="panel-heading">Hotel List</div>

  <!-- Table -->
  <table class="table">
      <tr>
          <th>ID</th>
          <th>Name</th>
          <th>Address1</th>
      </tr>

      <c:forEach items="${hotels}" var="hotel">
       <tr>
          <td>${hotel.id}</td>
          <td>${hotel.name}</td>
          <td>${hotel.address}</td>
        </tr>
    </c:forEach>

  </table>

        <span>${message}</span>

</div>



