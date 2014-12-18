<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div xmlns:c="http://java.sun.com/jsp/jstl/core" xmlns:jsp="http://java.sun.com/JSP/Page">


    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading">Hotel List</div>

        <!-- Table -->
        <table class="table">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Address1</th>
            </tr>

            <c:forEach items="${hotels}" var="hotel">
                <tr>
                    <td>${hotel.id}</td>
                    <td>${hotel.getName()}</td>
                    <td>${hotel.getAddress()}</td>
                </tr>
            </c:forEach>

        </table>

        <span>${message}</span>

    </div>


</div>