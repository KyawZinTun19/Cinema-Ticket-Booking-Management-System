<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <%@ include file="navbar.jsp" %>
            <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ticket</title>
<style>
body{
background-color:#333;
}

.container {

          margin: 0 auto; 
        width: 80%;
 /* max-width: 80% 80%; */

  overflow-x: auto;
}

.showticket{
  font-family: "Roboto", sans-serif;
  text-transform: uppercase;
  outline: 0;
width: 60%;
height:50px;
color: #000000;
background: linear-gradient(to right, rgb(182, 244, 146), rgb(51, 139, 147));
  transition: all 0.3 ease;
  cursor: pointer;
    border-top-left-radius: 10px;
  border-top-right-radius: 10px;
      border-bottom-left-radius: 10px;
  border-bottom-right-radius: 10px;
    border: 0px;

  font-size: 14px;


}


.showticket:hover{

transform: scale(1.2);
color: #000000;
background: linear-gradient(to right, rgb(186, 250, 150), rgb(56, 143, 151));
}

.table {
margin-top:30px;
  width: 100%;
  overflow: hidden;
  box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
  border: 2px solid #15191d;
}

.table th,.table td {
  padding: 1rem;
  background-color: #313b4b;
  color: #fff;
  text-align: center;
}

.table th {
  background-color: #074f75;
}



.table tbody td {
  position: relative;
  padding :25px;
  font-size: 20px;
}
.table tbody td:hover {
  background-color: #313743;
}



.table tbody tr:nth-child(even) {
  background-color: #212529;
  color: #fff;
}

.countCoulmn{
width:10%;
}

</style>
</head>
<body>

<div class="container">
<h2 style="color:#fff;">Confirmed Ticket</h2>
<table class="table">
<tr>
<th class="countCoulmn">Count</th>
<th>Movie</th>
<th>Action</th>
</tr>
<% int count = 1; %>
<c:forEach items="${confirmedTicketList}" var="confirmedTicketList">
<tr>
<td><%= count %></td>
<td>${confirmedTicketList.movieName}</td>
<td><button class="showticket" onclick="location.href='/CinemaTicketBooking//ticketDetail/${userId}/${confirmedTicketList.payment_id}/'">Show Ticket</button>
</td>
<% count++; %>
</tr></c:forEach>
		<c:set var="ticket" value="${fn:length(confirmedTicketList)}"/>
		<c:if test="${ticket < 1}">
		<tr><td colspan="4">You Haven't Bought Any Ticket Yet</td></tr>
		</c:if>


<table class="table">

<h2 style="color:#fff;">Pending Ticket</h2>
<tr>
<th class="countCoulmn">Count</th>
<th>Movie</th>
<th>Action</th>
</tr>
<% int pendingcount = 1; %>
<c:forEach items="${pendingticketlist}" var="pendingticketlist">
<tr>
<td><%= pendingcount %></td>
<td>${pendingticketlist.movieName}</td>
<td> <%@ include file="loading.html" %></button>
</td>
<% pendingcount++; %>
</tr></c:forEach>

		<c:set var="pemdingticket" value="${fn:length(pendingticketlist)}"/>
		<c:if test="${pemdingticket < 1}">
		<tr><td colspan="4">You Currently Have No Pending Ticket</td></tr>
		</c:if>





</table>
<!-- rejected list -->
<table class="table">

<h2 style="color:#fff;">Rejected Ticket</h2>
<tr>
<th class="countCoulmn">Count</th>
<th>Movie</th>
<th>Info</th>
</tr>
<% int rejectcount = 1; %>
<c:forEach items="${rejectedticketlist}" var="rejectticketlist">
<tr>
<td><%= rejectcount %></td>
<td>${rejectticketlist.movieName}</td>
<td> Rejected
</td>
<% rejectcount++; %>
</tr></c:forEach>

		<c:set var="rticket" value="${fn:length(rejectedticketlist)}"/>
		<c:if test="${rticket < 1}">
		<tr><td colspan="4">You Currently Have No Rejected Ticket</td></tr>
		</c:if>





</table>
</div>

</body>
</html>