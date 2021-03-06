<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<div xmlns:c="http://java.sun.com/jsp/jstl/core" xmlns:jsp="http://java.sun.com/JSP/Page">

    <h3><fmt:message key="user.list.title"/></h3>

    <c:choose>
        <c:when test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
            <a href="userCreate"><input type="button" class="btn btn-success" value="<fmt:message key="user.create"/>"name="addUser"/></a>
        </c:when>
    </c:choose>
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><b><fmt:message key="user.list.title"/></b></div>

        <!-- Table -->
        <table class="table table-hover">
            <thead>
            <tr>
                <th><fmt:message key="user.id"/></th>
                <th><fmt:message key="user.firstName"/></th>
                <th><fmt:message key="user.lastName"/></th>
                <th><fmt:message key="user.email"/></th>
                <th><fmt:message key="user.isAdmin"/></th>
                <th><fmt:message key="reservation.list.title"/></th>
                <c:choose>
                    <c:when test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
                        <th><fmt:message key="general.action"/></th>
                    </c:when>
                </c:choose>
            </tr>
            </thead>
            <c:forEach items="${listUsers}" var="user">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.email}</td>
                    <td><tags:yesno value="${user.admin}"/></td>
                    <td><a class="btn btn-default btn-xs" aria-label="Left Align"
                           href="${pageContext.request.contextPath}/userreservations/${user.id}"><fmt:message
                            key="user.reservations"/></a></td>
                    <c:choose>
                        <c:when test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
                            <td>
                                <a class="btn btn-default btn-xs" aria-label="Left Align"
                                   href="${pageContext.request.contextPath}/userEdit/${user.id}"><span
                                        class="glyphicon glyphicon-pencil" style="color: darkgreen;" aria-hidden="true"></span></a>
                                <a onclick="return confirm('<fmt:message key="user.confirm.delete"/>');"
                                   class="btn btn-default btn-xs" aria-label="Left Align"
                                   href="${pageContext.request.contextPath}/userDelete/${user.id}"><span
                                        class="glyphicon glyphicon-remove" style="color: red;" aria-hidden="true"></span></a>
                            </td>
                        </c:when>
                    </c:choose>
                </tr>
            </c:forEach>

        </table>

    </div>


</div>