<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <link rel="stylesheet" href="/css/message.css">
    <title>Event</title>
</head>
<body style="background-color: #aa96da;">
    <div class="container">
        <p>
            <div class="alert" role="alert">
                <h2 class="display-4"><span>Host:</span> <c:out value="${event.host.firstName}" /></h2>
			<h2>Date: <fmt:formatDate pattern="MMMM, dd, yyyy" value="${event.date}"/></h2>
			<h2>Location: <c:out value="${event.location}"/></h2>
            <h4>People who are attending this event: <c:out value="${event.usersAttending.size()}"/></h4>
			
            </div>
        </p>
    <table class="table table-sm">
		<thead>
			<tr>
				<th>Name</th>
				<th>Location</th>							
			</tr>
		</thead>
        <tbody>
            <c:forEach items="${event.usersAttending}" var="asistente">
        	    <tr>
        		    <td><c:out value="${asistente.firstName}"/></td>
        		    <td><c:out value="${asistente.location}"/></td>
                </tr> 
            </c:forEach>
        </tbody>   
    </table>    
    <div class="alinear">
    <h1 class="mensaje">Message Wall</h1>
        <p id="js">
            <c:forEach items = "${showMessages}" var="show">
                <c:out value= "${show.user.firstName}"/>
                <c:out value= "${show.user.lastName}"/> :
                <c:out value= "${show.description}"/>
                <br>
            </c:forEach>
    </div>         
       <div class="alinear">
    <form:form action="/events/${event.id}/a" method="post" modelAttribute="message">
        <label class="col-sm-3 col-form-label">Add Message</label>
       
 		<form:input class="form-control-sm-2 log"  type="text" path="description"/>
           <input type="submit" value="submit"/>
   
  
    </form:form>
       </div>
        </p>
        
    </div>
    <script src="/js/scroll.js"></script>

</body>
</html>