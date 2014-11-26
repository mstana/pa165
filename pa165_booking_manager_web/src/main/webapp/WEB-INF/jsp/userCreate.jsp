<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<div class="panel panel-default">
  <!-- Default panel contents -->
  <div class="panel-heading">User edit</div>


  <form action="#" method="post" role="form">
    <input type="hidden" value="${user.id}" />

    <div class="form-group">
      <label for="numberInput">First Name</label>
      <input type="text" name="firstName" id="numberInput" value="${user.firstName}" class="form-control" placeholder="Enter first name"/>
    </div>

    <div class="form-group">
      <label for="priceInput">Last Name</label>
      <input type="text" name="lastName" id="priceInput" value="${user.lastName}" class="form-control" placeholder="Enter last name"/>
    </div>

    <div class="form-group">
      <label for="bedsInput">Email</label>
      <input type="text" name="email" id="bedsInput" value="${user.email}" class="form-control" placeholder="Enter email"/>
    </div>
    <button type="submit" class="btn btn-default">Submit</button>
  </form>
</div>