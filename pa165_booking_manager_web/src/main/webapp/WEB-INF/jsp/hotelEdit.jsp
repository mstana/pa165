<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<a href="${pageContext.request.contextPath}/hotels" class="btn btn-default"><fmt:message key="general.back"/></a>

<div class="panel panel-default">

    <div class="panel-heading">
        <b>
            <c:choose>
                <c:when test="${hotel.id != null}">
                    <fmt:message key="hotel.edit"/>
                </c:when>
                <c:otherwise>
                    <fmt:message key="hotel.create"/>
                </c:otherwise>
            </c:choose>
        </b>
    </div>

    <form:form action="#" method="POST" accept-charset="UTF-8" role="form" commandName="hotelTo">
        <input type="hidden" name="id" value="${hotel.id}"/>

        <%@include file="hotelForm.jsp" %>

        <button type="submit" class="btn btn-primary"><fmt:message key="general.submit"/></button>
    </form:form>
</div>