<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<div class="panel panel-default">
  <!-- Default panel contents -->
  <div class="panel-heading">Room edit</div>


  <form action="#" method="post" role="form">
    <input type="hidden" value="${room.id}" />

    <div class="form-group">
      <label for="numberInput">Number</label>
      <input type="text" name="number" id="numberInput" value="${room.number}" class="form-control" placeholder="Enter room number"/>
    </div>

    <div class="form-group">
      <label for="priceInput">Price</label>
      <input type="text" name="price" id="priceInput" value="${room.price}" class="form-control" placeholder="Enter room price"/>
    </div>

    <div class="form-group">
      <label for="bedsInput">Number of beds</label>
      <input type="text" name="bedsCount" id="bedsInput" value="${room.bedsCount}" class="form-control" placeholder="Enter the number of beds in the room"/>
    </div>
    <button type="submit" class="btn btn-default">Submit</button>
  </form>
</div>