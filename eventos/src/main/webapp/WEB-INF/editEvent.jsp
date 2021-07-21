<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <!-- Etiquetas meta de JavaScript Opcionales-->
    <!-- Primero jQuery, luego Popper.js, y por último Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="/css/editEvent.css">
    <title>Edit Event</title>
</head>
<body class="container" style="background-color: #fcbad3;">
    <h2 class="display-2">Edit Event</h2>
        
		<div class="contenedor" style="background-color: #a8d8ea;">
 <form:form action="/events/${e.id}/edit" method="post" modelAttribute="e">
    <input type="hidden" name="_method" value="PUT">
	        <p>
	            <form:label class="col-sm-4 col-form-label" path="name">Name</form:label>
	            <form:input class="form-control col-sm-6 " type="text" path="name"/>
	        </p>
	        <p>
	            <form:label class="col-sm-3 col-form-label"  path="date">Date</form:label>
		        <form:errors path="date"/>
 		        <form:input class="form-control col-sm-6 log"  type="date" path="date"/>
	        </p>
	       
	        <p>
	            <form:label class="col-sm-4 col-form-label" path="location">Location</form:label>
	            <form:input class="form-control col-sm-6" type="text" path="location"/>
	        </p>
			 <p>
	            <form:label class="col-sm-4 col-form-label" path="state">State</form:label>
	            <form:input class="form-control col-sm-6" type="text" path="state"/>
	        </p>
	       
	         <button  class="boton">Edit Event</button>
	        
       
		</div>
    </form:form>
</body>
</html>