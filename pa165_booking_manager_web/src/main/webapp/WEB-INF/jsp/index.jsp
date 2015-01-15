<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div xmlns:c="http://java.sun.com/jsp/jstl/core" xmlns:jsp="http://java.sun.com/JSP/Page">

    <!-- Page Content -->
    <div class="container">

        <div class="row">
            <div class="col-lg-12 text-center">
                <c:choose>
                    <c:when test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
                        <h1><fmt:message key="general.welcome.welcome.admin"/></h1>
                        <p class="lead"><fmt:message key="general.welcome.admin"/></p>
                    </c:when>
                    <c:otherwise>
                        <h1><fmt:message key="general.welcome.welcome.user"/></h1>
                        <p class="lead"><fmt:message key="general.welcome.user"/></p>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <!-- /.row -->

    </div>
</div>