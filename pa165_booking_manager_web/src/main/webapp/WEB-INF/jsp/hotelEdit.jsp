<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<div class="panel panel-default">

    <div class="panel-heading"><fmt:message key="hotel.edit"/></div>

    <form action="${pageContext.request.contextPath}/hotel/${hotel.id}" method="post" role="form">
        <input type="hidden" name="id" value="${hotel.id}" />

        <%@include file="hotelForm.jsp"%>

        <button type="submit" class="btn btn-default"><fmt:message key="submit"/></button>
    </form>
</div>