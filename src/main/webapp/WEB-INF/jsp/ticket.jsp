<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <%@ include file="navbar.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css"
 integrity="sha512-SzlrxWUlpfuzQ+pcUCosxcglQRNAq/DZjVsC0lE40xsADsfeQoEypE+enwcOiGjk/bSuGGKHEyjSoQ1zVisanQ==" 
crossorigin="anonymous" referrerpolicy="no-referrer" />
<title>My Ticket</title>
<style>
        body{
    background-color: rgb(19, 47, 72);
    }
    .ticket{
        background-color: rgb(17, 37, 55);
        color: rgb(230, 241, 244);
        margin: auto;
    border: 3px solid rgb(14, 14, 24);
    width: 30%;
    padding: 50px;
    height: 50%;
    border-radius: 20px;
    box-shadow: 30px rgb(22, 22, 147);
    
    }
    table{
        font-size: 20px;
        text-align: left;
       
    }
    th,tr,td{
        padding: 20px 30px;
    }
    h1{
        text-align: center;
    }
    .t1{
        font-size: 50px;
    }
    .value{
text-align:left;
margin-left:30px;
}
    
    </style>
</head>
<body>
    <div class="ticket">
        <table>
           <h1 class="t1">Your Ticket </h1>
           <tr>
		<td>Movie Name</td>
		<td>:</td>
		<td class="value">${detailTicketMovieName}</td>
	</tr>
	<tr>
		<td>Show Time</td>
		<td>:</td>
		<td class="value">${ticketDetailShowTime}</td>
	</tr>
	<tr>
		<td>Show Date</td>
		<td>:</td>
		<td class="value">${ticketDetailShowDate}</td>
	</tr>
	<tr>
		<td>Seat NO:</td>
		<td>:</td>
		<td class="value">${TicketDetailTotalSeat}</td>
	</tr>
	<tr>
		<td>Seat Total</td>
		<td>:</td>
		<td class="value">${ticketDetailSeatSize}</td>
	</tr>
	<tr>
		<td>Total Amount </td>
		<td>:</td>
		<td class="value">${ticketDetailAmount}</td>
	</tr>


            
  
            </table>
            <h1>Thanks For Joined Us <i class="fa-solid fa-check"></i></h1>
       
    </div>
</body>
</html>