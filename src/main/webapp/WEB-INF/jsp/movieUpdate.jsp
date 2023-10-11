<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ include file="adminSideBar.jsp" %>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en" >
<head>
    <title>Movie Update</title>
      <link rel="icon" type="image/x-icon" href="/CinemaTicketBooking/images/movie.jpg">
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

table {
  width: 100%;
  border-collapse: collapse;
  overflow: hidden;
  box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
  border: 2px solid #383838;
}


th, td {
  padding: 1rem;
  background-color: #313b4b;
  color: #fff;
  text-align: center;
}

th {
  background-color: #074f75;
}



.UpdateButton, .CancelButton {
  font-family: "Roboto", sans-serif;
  text-transform: uppercase;
  outline: 0;
  background-color: #1f75fe ;
  /*background-image: linear-gradient(to bottom right,#228b22 ,#008000);*/
  width: 35%;
  border: 1px solid #1f75fe;
  padding: 15px;
  color: #FFFFFF;
  font-size: 14px;
  -webkit-transition: all 0.3 ease;
  transition: all 0.3 ease;
  cursor: pointer;
}

.UpdateButton:hover {
  background-color: #318ce7;
}


.CancelButton {
  width: 50%;
text-decoration: none;
  background-color: #3d5c71 ; 
  border: 1px solid #3e607b;
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

textarea {
  padding: 6px;
  margin-top: 8px;
  font-size: 17px;
  width:56%;
  resize: none;
}


    </style>
</head>
<body>
    <div class="container">
    <h1 class="label">Movie Update</h1>
    <form:form action="/CinemaTicketBooking/updatemovie" method="post" enctype="multipart/form-data"  modelAttribute="movieAndTimetableBean">
    <div>

    </div>
          <table>

                        <tr >
            <td colspan="2" style="color:red">${error}</td></tr>
            <tr style="display:none;">
                <td><label >Id</label> </td>
                <td><form:input type="text" name="Id" path="movieID" readonly="true"></form:input>
                 </td>
            </tr>
            <tr>
                <td><label>Name</label> </td>
                <td><form:input type="text" name="name" path="movieName" placeholder="Movie Name" readonly="true"></form:input>
                 </td>
            </tr>
            <tr>
                <td><label>Genre</label> </td>
                <td><form:input type="text" name="genre" path="movieGenre" placeholder="Movie Genre"></form:input>
                 </td>
            </tr>
            <tr>
                <td><label>Duration <br>(Format- _Hr _min)</label> </td>
                <td><form:input type="text" name="duration" placeholder="Movie Duration" path="movieDuration" pattern="\d+Hr [0-5]?\dmin" oninput="validateTimeInput()" title="Error!! Please use the proper format"></form:input>
                <span id="time-input-error" style="color:red; display:none;">Please enter a valid time in the format '%Hr %min' with minutes less than 60.</span> 
                </td>
            </tr>
            
            
                 <tr>
                <td><label>Description</label> </td>
                <td><form:textarea  name="description" path="movieDescription" rows="4" placeholder="Description" ></form:textarea>
                 </td>
            </tr>

            
                        <tr style="display:none">
            
                <td><label>Id</label> </td>
                <td><form:input type="text"  path="timetableMovieId" readonly="true"></form:input>
                 </td>

            </tr>

   
  <tr>
                <td><label>Show Date</label> </td>
                <td><form:input type="date"  path="startDate"></form:input>
                 </td>
            </tr>

            <tr>
                <td><label>Start Time</label> <br>
                Format: __:__(24Hour)</td>
          
                <td>
			<form:input type="text" id="timeInput" path="startTime" pattern="(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]" title="Error!! Please use the proper format"  />

                </td>
            </tr>           
                         <tr>
                 <td><label for="poster">Movie Poster</label></td>
                 <td><form:input class="imageSelecter" type="file" path="moviePoster" accept="image/*"/></td>
            </tr>
            
                        <tr> 
                <td><a type="submit" href="/CinemaTicketBooking/movieTable" class="CancelButton" style="text-decoration:none">Cancel</a></td>
                <td><form:button class="UpdateButton">Update</form:button></td>
            </tr>

        </table>
    </form:form>
    </div>
</body>