<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<h3><fmt:message key="room.hotel"/> <a href="${pageContext.request.contextPath}/rooms/${hotel.id}">${hotel.name}</a></h3>

<div class="panel panel-default">
  <!-- Default panel contents -->
  <div class="panel-heading">
    <b>
      <c:choose>
        <c:when test="${room.id != null}">
          <fmt:message key="room.edit"/>
        </c:when>
        <c:otherwise>
          <fmt:message key="room.new"/>
        </c:otherwise>
      </c:choose>
      </b>
  </div>

  <form action="#" method="post" role="form">
    <div class="form-group">
      <label for="numberInput"><fmt:message key="room.number"/></label>
      <input type="text" name="number" id="numberInput" value="${room.number}" class="form-control" placeholder="<fmt:message key="room.number.plc"/>"/>
    </div>

    <div class="form-group">
      <label for="priceInput"><fmt:message key="room.price"/></label>
      <input type="text" name="price" id="priceInput" value="${room.price}" class="form-control" placeholder="<fmt:message key="room.price.plc"/>"/>
    </div>

    <div class="form-group">
      <label for="bedsInput"><fmt:message key="room.beds"/></label>
      <input type="text" name="bedsCount" id="bedsInput" value="${room.bedsCount}" class="form-control" placeholder="<fmt:message key="room.beds.plc"/>"/>
    </div>
    <button type="submit" class="btn btn-primary"><fmt:message key="general.submit"/></button>
    <a class="btn btn-default" href="${pageContext.request.contextPath}/rooms/${hotel.id}"><fmt:message key="general.back"/></a>
  </form>
</div>