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
	<link rel="stylesheet" href="css/events.css">
    <title>Events</title>
</head>
<body class="container" style="background-color: #cca8e9;">
    <h1 class="display-3">Welcome, <c:out value="${user.firstName}"/></h1>
	<a href="/registration" class="salida">Logout</a>
	<h3>Eventos Locales</h3>
		<table class="table table-sm">
		<thead>
			<tr>
				<th>Name</th>
				<th>Date</th>
				<th>Location</th>
				<th>State</th>
				<th>Host</th>
				<th>Action / Status</th>								
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${eventIn}" var="in">
        		<tr>
        			<td><a href="/events/${in.id}"><c:out value="${in.name}"/></a></td>
        			<td><c:out value="${in.date}"/></td>
        			<td><c:out value="${in.location}"/></td>
        			<td><c:out value="${in.state}"/></td>
        			<td><c:out value="${in.host.firstName}"/></td>
					<td>
					<c:if test="${user.id == in.host.id}">
			          	<a href="/events/${in.id}/edit">Edit</a> | <a href="/events/${in.id}/delete">Delete</a>
					</c:if>
					<c:if test="${user.id != in.host.id && !out.usersAttending.contains(user)}">
						<a href="/events/${in.id}/join">Join</a>
					</c:if>
					<c:if test="${user.id != out.host.id && out.usersAttending.contains(user)}">
						<p>
							Joining
						</p>
						<a href="/events/${out.id}/cancel">Cancel</a>
					</c:if>
				</td>	 	
					
        		</tr>
        	</c:forEach>
		</tbody>
	</table>
	<hr>
	<h3>Eventos Fuera del Estado</h3>
			<table class="table table-sm">
		<thead>
			<tr>
				<th>Name</th>
				<th>Date</th>
				<th>Location</th>
				<th>State</th>
				<th>Host</th>
				<th>Action</th>								
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${eventOut}" var="out">
        	<tr>
        		<td><a href="/events/${out.id}"><c:out value="${out.name}"/></a></td>
        		<td><c:out value="${out.date}"/></td>
        		<td><c:out value="${out.location}"/></td>
        		<td><c:out value="${out.state}"/></td>
        		<td><c:out value="${out.host.firstName}"/></td>
				<td>
					<c:if test="${user.id == out.host.id}">
			          	<a href="/events/${out.host.id}/edit">Edit</a> | <a href="/events/${out.id}/delete">Delete</a>
					</c:if>
					<c:if test="${user.id != out.host.id && !out.usersAttending.contains(user)}">
						<a href="/events/${out.id}/join">Join</a>
					</c:if>
					<c:if test="${user.id != out.host.id && out.usersAttending.contains(user)}">
						<p>
							Joining
						</p>
						<a href="/events/${out.id}/cancel">Cancel</a>
					</c:if>
				</td>	 
        	</c:forEach>
		</tbody>
	</table>
	<div class="creator" style="background-color: #ffcef3;">
		<h1 class="display-4">Create An Event</h1>
 <form:form action="/events" method="post" modelAttribute="event">
    <p>
        <form:label class="col-sm-3 col-form-label" for="email" path="name">Name</form:label>
        <form:errors path="name"/>
        <form:input class="form-control-sm-6 log log" type="text" path="name"/>
    </p>
        <p>
        <form:label class="col-sm-3 col-form-label" for="email" path="location">Location</form:label>
        <form:errors path="location"/>
        <form:input class="form-control-sm-6 log log" type="text"  path="location"/>
    </p>
    <p>
        <form:label class="col-sm-3 col-form-label" for="email"  path="state">State</form:label>
		<form:errors path="state"/>
		<form:input class="form-control-sm-6 log log" type="text" id="email" path="state"/>
        
	 
		</p>
        <p>
        <form:label class="col-sm-3 col-form-label" for="email" path="date">Date</form:label>
		<form:errors path="date"/>
 		<form:input class="log"  type="date" path="date"/>
    </p> 
    <button  class="boton">Create Event</button>
</form:form>
	</div>
	
</body>
</html>