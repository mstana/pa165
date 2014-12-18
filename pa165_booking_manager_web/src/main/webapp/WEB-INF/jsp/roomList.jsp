<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:choose>
    <c:when test="${hotel != null}">
        <h3><fmt:message key="room.hotel"/> ${hotel.name}</h3>
    </c:when>
    <c:otherwise>
        <h3><fmt:message key="room.all"/></h3>
    </c:otherwise>
</c:choose>

<c:if test="${hotel != null}">
    <a class="btn btn-success" href="${pageContext.request.contextPath}/newroom/${hotel.id}"><fmt:message
            key="room.new"/></a>
</c:if>
<div class="panel panel-default">

    <div class="panel-heading">
        <b><fmt:message key="room.list.title"/></b>
    </div>

    <table class="table table-hover">
        <thead>
        <tr>
            <th><fmt:message key="room.number"/></th>
            <th><fmt:message key="room.price"/></th>
            <th><fmt:message key="room.beds"/></th>
            <th><fmt:message key="room.hotel"/></th>
            <th><fmt:message key="reservation.list.title"/></th>
            <th><fmt:message key="general.action"/></th>
        </tr>
        </thead>
        <c:forEach items="${rooms}" var="room">
            <tr>
                <td><a href="${pageContext.request.contextPath}/room/${room.hotel.id}/${room.id}">${room.number}</a>
                </td>
                <td>${room.price}</td>
                <td>${room.bedsCount}</td>
                <td>${room.hotel.name}</td>
                <td>
                    <a class="btn btn-default btn-xs" aria-label="Left Align"
                       href="${pageContext.request.contextPath}/roomreservations/${room.id}"><fmt:message
                            key="room.reservations"/></a>
                    <a class="btn btn-default btn-xs" aria-label="Left Align"
                       href="${pageContext.request.contextPath}/newreservation/${room.id}"><fmt:message
                            key="room.new.reservation"/></a>
                </td>
                <td>
                    <a class="btn btn-default btn-xs" aria-label="Left Align"
                       href="${pageContext.request.contextPath}/room/${room.hotel.id}/${room.id}"><span
                            class="glyphicon glyphicon-pencil" style="color: darkgreen;" aria-hidden="true"></span></a>
                    <a onclick="return confirm('<fmt:message key="room.confirm.delete"/>');"
                       class="btn btn-default btn-xs" aria-label="Left Align"
                       href="${pageContext.request.contextPath}/deleteroom/${room.hotel.id}/${room.id}"><span
                            class="glyphicon glyphicon-remove" style="color: red;" aria-hidden="true"></span></a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>