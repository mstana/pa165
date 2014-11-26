<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<div xmlns:c="http://java.sun.com/jsp/jstl/core" xmlns:jsp="http://java.sun.com/JSP/Page">

    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><fmt:message key="hotel.list"/></div>

        <!-- Table -->
        <table class="table">
            <tr>
                <th><fmt:message key="hotel.id"/></th>
                <th><fmt:message key="hotel.name"/></th>
                <th><fmt:message key="hotel.address"/></th>
                <th><fmt:message key="hotel.rooms"/></th>
            </tr>

            <c:forEach items="${hotels}" var="hotel">
                <tr>
                    <td>${hotel.id}</td>
                    <td><a href="hotel/${hotel.id}">${hotel.name}</a></td>
                    <td>${hotel.address}</td>
                    <td><a href="rooms/${hotel.id}"><fmt:message key="hotel.show.all.rooms"/></a></td>
                </tr>
            </c:forEach>

        </table>
    </div>

    <div class="panel panel-default">

        <div class="panel-heading"><fmt:message key="hotel.create"/></div>

        <form action="${pageContext.request.contextPath}/newhotel" method="post" role="form">

            <%@include file="hotelForm.jsp"%>

            <button type="submit" class="btn btn-default"><fmt:message key="submit"/></button>
        </form>
    </div>

</div>



