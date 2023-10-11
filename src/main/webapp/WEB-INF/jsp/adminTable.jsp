<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en" >
<head>
  <jsp:include page="adminSideBar.jsp"></jsp:include>
  <meta charset="UTF-8">
  <title>Admin Table</title>
  <link rel="stylesheet" href="./style.css">
	<style>
	html,
body {
  height: 100%;
}

body {
  height: auto;
  margin: 0;
  background: linear-gradient(to bottom right,#212529,#15191d);
  font-family: sans-serif;
  font-weight: 100;
}

.container {
   margin: 0 auto; 
    width: 80%;
 /* max-width: 80% 80%; */

  overflow-x: auto;
}
.label{
  color: #fff;
    font-family: sans-serif;
  font-weight: 100;
  font-size: 40px;
  text-align: center;

}

.table {
  width: 100%;
  border-collapse: collapse;
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

.table tbody tr:hover {
  background-color: #fff;
}

.table tbody td {
  position: relative;
}

.table tbody td:hover:before {
  content: "";
  position: absolute;
  left: 0;
  right: 0;
  top: -9999px;
  bottom: -9999px;
  background-color: rgba(255, 255, 255, 0.2);
  z-index: -1;
}

.table tbody tr:nth-child(even) {
  background-color: #212529;
  color: #fff;
}

.UpdateButton {
  font-family: "Roboto", sans-serif;
  text-transform: uppercase;
  outline: 0;
  background-color: #009900;
  color: #FFFFFF;
  /*background-color: #3d5c71 ;
  
  */
  width: 25%;
  border: 1px solid #3e607b;
  padding: 15px;
  
  font-size: 14px;
  -webkit-transition: all 0.3 ease;
  transition: all 0.3 ease;
  cursor: pointer;
}

.DeleteButton {
  font-family: "Roboto", sans-serif;
  text-transform: uppercase;
  outline: 0;
  background-color: #e8000d ;
  color: #FFFFFF;
  /*background-color: #3d5c71 ;
  
  */
  width: 25%;
  border: 1px solid #3e607b;
  padding: 15px;
  
  font-size: 14px;
  -webkit-transition: all 0.3 ease;
  transition: all 0.3 ease;
  cursor: pointer;
}

.UpdateButton:hover {
  background-color: #32cd32 ;
}

.DeleteButton:hover {
  background-color: #ff0000 ;
}


.SearchButton {
  width: 10%;
  padding: 6px;
  margin-top: 8px;
  font-size: 17px
}

.SearchButton:hover {
  background-color: #40667d; /* Green */
  color: white;
}
.AddButton{
  width: 10%;
  padding: 6px;
  margin-top: 8px;
  font-size: 17px;
}
.AddButton:hover {
  background-color: #40667d; /* Green */
  color: white;
}

.ResetButton{
  width: 10%;
  padding: 6px;
  margin-top: 8px;
  font-size: 17px;
}
.ResetButton:hover {
  background-color: #40667d;
  color: white; /* Green */
}

@media only screen and (max-width: 768px) {
  table {
    font-size: 0.8rem;
  }
}

@media only screen and (max-width: 600px) {
  table {
    font-size: 0.7rem;
  }
}

@media only screen and (max-width: 480px) {
  table {
    font-size: 0.6rem;
  }

  .table thead {
    display: none;
  }

  .table, .table tbody, .table tr, .table td {
    display: block;
    width: 100%;
  }

  .table tr {
    margin-bottom: 15px;
  }

  .table td {
    text-align: right;
    padding-left: 50%;
    text-align: right;
    position: relative;
  }

  .table td::before {
    content: attr(data-label);
    position: absolute;
    left: 0;
    width: 50%;
    padding-left: 15px;
    font-size: 15px;
    font-weight: bold;
    text-align: left;
  }
}

input[type=text] {
  padding: 6px;
  margin-top: 8px;
  font-size: 17px;}
  
 .top{
 padding:2px;
 margin-bottom:10px;
 }
 
  .labelContainer{
 margin-top: 10px;
 }
  
	
	</style>
</head>
<body>

<!-- partial:index.partial.html -->
<div class="container">
	<div class="labelContainer"><label class="label">Admin Table</label></div>
	<div class="top"><input type="text" name="name" placeholder="Id"> <input type="text" name="name" placeholder="Name"> 
	<input type="text" name="name" placeholder="Email">
		<button class="SearchButton">Search</button>
		<button class="AddButton" onclick="location.href='toaddAdmin'">Add</button>
		<button class="ResetButton">Reset</button></div>

	<table class="table">
		<thead>
			<tr>
				<th>Id</th>
				<th>Name</th>
				<th>Email</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${adminlist}" var="adminlist">
			<tr>
				<td>${adminlist.accountID}</td>
				<td>${adminlist.accountName}</td>
				<td>${adminlist.accountEmail}</td>
				 
				<td>
				<c:if test="${currentAdmin == adminlist.accountEmail}">
				<button onclick="location.href='/CinemaTicketBooking/setupUpdateAdmin/${adminlist.accountID}'" class="UpdateButton" style="width:50%">Update</button> 
				</c:if>
				<c:if test="${currentAdmin != adminlist.accountEmail}">
				<button onclick="location.href='/CinemaTicketBooking/setupUpdateAdmin/${adminlist.accountID}'" class="UpdateButton" >Details</button>
				<button onclick="location.href='/CinemaTicketBooking/deleteAdmin/${adminlist.accountID}'"  class="DeleteButton" >Delete</button> 
				</c:if>
				</td>
			
			</tr>
			</c:forEach>


		</tbody>
	</table>
</div>
<!-- partial -->
  
</body>
</html>
 