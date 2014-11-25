<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<div class="panel panel-default">
    <!-- Default panel contents -->
    <div class="panel-heading">Room List</div>

    <!-- Table -->
    <table class="table">
      <tr>
          <th>Action</th>
          <th>Number</th>
          <th>Price</th>
          <th>Number of beds</th>
      </tr>

      <c:forEach items="${rooms}" var="room">
        <tr>
            <td><a href="/room/${room.hotel.id}/${room.id}">Edit</a></td>
            <td>${room.number}</td>
            <td>${room.price}</td>
            <td>${room.bedsCount}</td>
        </tr>
      </c:forEach>

    </table>

    <span>${message}</span>
  </div>