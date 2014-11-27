<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

  <c:if test="${user != null}">
    <h3>${user.firstName} <fmt:message key="reservation.list.title"/> </h3>
  </c:if>
  <c:if test="${room != null}">
    <h3>${room.number} <fmt:message key="reservation.list.title"/> </h3>
  </c:if>

<c:if test="${room != null}">
  <a class="btn btn-success" href="${pageContext.request.contextPath}/newreservation/${room.id}"><fmt:message key="reservation.new"/></a>
</c:if>

<div class="panel panel-default">

  <div class="panel-heading">
    <b><fmt:message key="reservation.list.title"/></b>
  </div>

  <table class="table table-hover">
    <thead>
    <tr>
      <th><fmt:message key="reservation.room.number"/></th>
      <th><fmt:message key="reservation.room.hotel"/></th>
      <th><fmt:message key="reservation.begin"/></th>
      <th><fmt:message key="reservation.end"/></th>
      <th><fmt:message key="general.action"/></th>
    </tr>
    </thead>
    <c:forEach items="${reservations}" var="reservation">
      <tr>
        <td>${reservation.room.number}</td>
        <td>${reservation.room.hotel.name}</td>
        <td>${reservation.beginDate}</td>
        <td>${reservation.endDate}</td>
        <td>
          <a class="btn btn-default btn-xs" aria-label="Left Align" href="${pageContext.request.contextPath}/reservation/${reservation.id}"><span class="glyphicon glyphicon-pencil" style="color: darkgreen;" aria-hidden="true"></span></a>
          <a onclick="return confirm('<fmt:message key="reservation.confirm.delete"/>');" class="btn btn-default btn-xs" aria-label="Left Align" href="${pageContext.request.contextPath}/deletereservation/${reservation.id}"><span class="glyphicon glyphicon-remove" style="color: red;" aria-hidden="true"></span></a>
        </td>
      </tr>
    </c:forEach>
  </table>
</div>