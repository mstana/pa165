<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<a href="${pageContext.request.contextPath}/roomreservations/${room.id}" class="btn btn-default"><fmt:message
        key="general.back"/></a>

<div class="panel panel-default">

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

            <div class="input-append date">
                <input class="datepicker-input" data-provide="datepicker" name="beginDate" id="beginDate"
                       value="<fmt:formatDate value="${reservation.beginDate}" pattern="MM/dd/yyyy" />" readonly>
                <span class="add-on"><i class="glyphicon glyphicon-calendar"></i></span>
            </div>
        </div>

        <div class="form-group">
            <label for="beginDate"><fmt:message key="reservation.end"/></label>

            <div class="input-append date">
                <input class="datepicker-input" data-provide="datepicker" name="endDate" id="endDate"
                       value="<fmt:formatDate value="${reservation.endDate}" pattern="MM/dd/yyyy" />" readonly>
                <span class="add-on"><i class="glyphicon glyphicon-calendar"></i></span>
            </div>
        </div>

        <div class="form-group">
            <label for="user"><fmt:message key="reservation.user"/></label>






            <select class="form-control" name="user" id="user">

                <c:choose>
                    <c:when test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
                        <c:forEach items="${users}" var="user">
                            <option
                                <c:choose>
                                    <c:when test="${reservation.user.id == user.id}">selected="selected"</c:when>
                                </c:choose>
                                value="${user.id}">${user.firstName} ${user.lastName}
                            </option>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${users}" var="user">
                                <c:choose>

                                    <c:when test="${pageContext.request.remoteUser == user.username}">
                                        <option selected="selected" value="${user.id}">
                                            ${user.firstName} ${user.lastName}
                                        </option>
                                </c:when>
                                </c:choose>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>



            </select>
        </div>

        <input type="hidden"
               name="${_csrf.parameterName}"
               value="${_csrf.token}"/>

        <button type="submit" class="btn btn-primary"><fmt:message key="general.submit"/></button>
    </form>
</div>