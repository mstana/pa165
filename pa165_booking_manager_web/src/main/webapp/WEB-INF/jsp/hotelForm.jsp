<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="form-group">
    <label for="nameInput"><fmt:message key="hotel.name"/></label>
    <input type="text" name="name" id="nameInput" value="${hotel.name}" class="form-control"
           placeholder="<fmt:message key="hotel.name.enter"/>"/>
    <font color="red"><form:errors path="name" cssClass="error"/></font>

</div>

<div class="form-group">
    <label for="addressInput"><fmt:message key="hotel.address"/></label>
    <input type="text" name="address" id="addressInput" value="${hotel.address}" class="form-control"
           placeholder="<fmt:message key="hotel.address.enter"/>"/>
    <font color="red"><form:errors path="address" cssClass="error"/></font>
</div>

<input type="hidden"
       name="${_csrf.parameterName}"
       value="${_csrf.token}"/>