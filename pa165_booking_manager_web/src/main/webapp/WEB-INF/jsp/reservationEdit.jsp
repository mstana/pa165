<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h3>TODO</h3>

<div class="panel panel-default">
  <!-- Default panel contents -->
  <div class="panel-heading">
    <b>
      <c:choose>
        <c:when test="${reservation.id != null}">
          <fmt:message key="reservation.edit"/>
        </c:when>
        <c:otherwise>
          <fmt:message key="reservation.new"/>
        </c:otherwise>
      </c:choose>
    </b>
  </div>

  <form action="#" method="post" role="form">
    <div class="form-group">
      <label for="beginDate"><fmt:message key="reservation.begin"/></label>
      <input data-provide="datepicker" name="beginDate" id="beginDate" value="${reservation.beginDate}" class="form-control" />
    </div>

    <div class="form-group">
      <label for="endDate"><fmt:message key="reservation.end"/></label>
      <input data-provide="datepicker" name="endDate" id="endDate" value="${reservation.endDate}" class="form-control" />
    </div>

    <div class="form-group">
      <label for="user"><fmt:message key="reservation.user"/></label>
      <select class="form-control" name="user" id="user">
        <c:forEach items="${users}" var="user">
          <option value="${user.id}">${user.firstName} ${user.lastName}</option>
        </c:forEach>
      </select>
    </div>

    <button type="submit" class="btn btn-primary"><fmt:message key="general.submit"/></button>
    <a class="btn btn-default" href="${pageContext.request.contextPath}/roomreservations/${room.id}"><fmt:message key="general.back"/></a>
  </form>
</div>