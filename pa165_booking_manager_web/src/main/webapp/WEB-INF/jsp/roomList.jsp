<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:if test="${hotel != null}">
    <a class="btn btn-success" href="${pageContext.request.contextPath}/newroom/${hotel.id}"><fmt:message key="room.new"/></a>
</c:if>
<div class="panel panel-default">

    <div class="panel-heading">
        <c:choose>
            <c:when test="${hotel != null}">
                <b>${hotel.name} hotel</b>
            </c:when>
            <c:otherwise>
                <b>All rooms</b>
            </c:otherwise>
        </c:choose>
    </div>

    <table class="table table-hover">
      <thead>
        <tr>
          <th>Number</th>
          <th>Price</th>
          <th>Number of beds</th>
          <th>Hotel</th>
        </tr>
      </thead>
      <c:forEach items="${rooms}" var="room">
        <tr>
            <td><a href="${pageContext.request.contextPath}/room/${room.hotel.id}/${room.id}">${room.number}</a></td>
            <td>${room.price}</td>
            <td>${room.bedsCount}</td>
            <td>${room.hotel.name}</td>
        </tr>
      </c:forEach>
    </table>
  </div>