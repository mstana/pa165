<%-- 
    Document   : yesno
    Created on : Nov 27, 2014, 1:39:54 AM
    Author     : mstana
--%>

<%@tag description="is admin tag" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%-- any content can be specified here e.g.: --%>
<%@ attribute name="value" type="java.lang.Boolean" required="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:choose><c:when test="${value}"><fmt:message key="general.yes"/></c:when><c:otherwise><fmt:message key="general.no"/></c:otherwise></c:choose>