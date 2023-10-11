<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    <jsp:include page="adminSideBar.jsp"></jsp:include>
<!DOCTYPE html>
<html>
<head>
    <title>Add TimeTable</title>
    <style type="text/css">
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
  border: 2px solid #383838;
}


.table td {
  padding: 1rem;
  background-color: #313b4b;
  color: #fff;
  text-align: center;
}



.UpdateButton, .CancelButton {
  font-family: "Roboto", sans-serif;
  text-transform: uppercase;
  outline: 0;
  background-color: #228b22 ;
  /*background-image: linear-gradient(to bottom right,#228b22 ,#008000);*/
  width: 27%;
  border: 1px solid #006400;
  padding: 15px;
  color: #FFFFFF;
  font-size: 14px;
  -webkit-transition: all 0.3 ease;
  transition: all 0.3 ease;
  cursor: pointer;
}

.UpdateButton:hover {
  background-color: #009900;
}


.CancelButton {
  background-color: #3d5c71 ; 
  border: 1px solid #3e607b;
  text-decoration:none;
}

.CancelButton:hover{
  background-color: #40667d   ;
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
  font-size: 17px;
}
input[type=date] {
  padding: 6px;
  margin-top: 8px;
  font-size: 17px;
}
input[type=time] {
  padding: 6px;
  margin-top: 8px;
  font-size: 17px;
  width:136px;
}
.movieName {
font-size:30px;
font-family:lucida console;
}
#movie{
background-image:linear-gradient(to right,#8360c3,#2ebf91);
}
    </style>
</head>
<body>
    <div class="container">
    <h1 class="label">Add Movie Show Time</h1>
    <form:form action="/CinemaTicketBooking/addMovieStartTime" method="post" modelAttribute="timeTableBean">
    <div>
     
    </div>
        <table class="table">
            <tr style="display:none">
                <td><label>Id</label> </td>
                <td><form:input type="text"  path="movieId" readonly="true"></form:input>
                 </td>
            </tr>
            <tr><td colspan="2" id="movie"><span class="movieName">${movieName}</span></td></tr>
            <tr>
                <td><label>Start Date</label> </td>
                <td><form:input type="date"  path="startDate"></form:input>
                 </td>
            </tr>
            <tr>
                <td><label>End Date</label> </td>
                <td><form:input type="date"  path="endDate"></form:input>
                 </td>
            </tr>
            <tr>
                <td><label>Start Time</label> </td>
                <td><form:input type="time" path="startTime"></form:input> 
                </td>
            </tr>
             
            <tr>
                <td><a href="/" class="CancelButton" >Cancel</a></td>
                <td><form:button class="UpdateButton">Add</form:button></td>
            </tr>

        </table>
        <div style="color:red">${error}</div>




    </form:form>
    </div>
</body>