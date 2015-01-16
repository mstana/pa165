<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<div xmlns:c="http://java.sun.com/jsp/jstl/core" xmlns:jsp="http://java.sun.com/JSP/Page">

    <h3><fmt:message key="hotel.all"/></h3>
    <c:choose>
        <c:when test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
            <a href="newhotel" class="btn btn-success"><fmt:message key="hotel.create"/></a>
        </c:when>
    </c:choose>
    <div class="panel panel-default">

        <div class="panel-heading"><b><fmt:message key="hotel.list"/></b></div>

        <table class="table">
            <tr>
                <th><fmt:message key="hotel.id"/></th>
                <th><fmt:message key="hotel.name"/></th>
                <th><fmt:message key="hotel.address"/></th>
                <th><fmt:message key="hotel.rooms"/></th>
                <c:choose>
                    <c:when test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
                        <th><fmt:message key="general.action"/></th>
                    </c:when>
                </c:choose>
            </tr>

            <c:forEach items="${hotels}" var="hotel">
                <tr>
                    <td>${hotel.id}</td>
                    <td>
                        <c:choose>
                            <c:when test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
                                <a href="hotel/${hotel.id}">${hotel.name}</a>
                            </c:when>
                            <c:otherwise>
                                ${hotel.name}
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>${hotel.address}</td>
                    <td><a class="btn btn-default btn-xs" href="rooms/${hotel.id}"><fmt:message
                            key="hotel.show.all.rooms"/></a></td>
                    <c:choose>
                        <c:when test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
                            <td>
                                <a class="btn btn-default btn-xs" aria-label="Left Align"
                                   href="${pageContext.request.contextPath}/hotel/${hotel.id}"><span
                                        class="glyphicon glyphicon-pencil" style="color: darkgreen;" aria-hidden="true"></span></a>
                                <a onclick="return confirm('<fmt:message key="hotel.confirm.delete"/>');"
                                   class="btn btn-default btn-xs" aria-label="Left Align"
                                   href="${pageContext.request.contextPath}/deletehotel/${hotel.id}"><span
                                        class="glyphicon glyphicon-remove" style="color: red;" aria-hidden="true"></span></a>
                            </td>
                        </c:when>
                    </c:choose>
                </tr>
            </c:forEach>

        </table>
    </div>

    <%-- <div class="panel panel-default">

        <div class="panel-heading"><fmt:message key="hotel.create"/></div>

        <form action="${pageContext.request.contextPath}/newhotel" method="post" role="form">

            <%@include file="hotelForm.jsp"%>

            <button type="submit" class="btn btn-default"><fmt:message key="general.submit"/></button>
        </form>
    </div>--%>

</div>



