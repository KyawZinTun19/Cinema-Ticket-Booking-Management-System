<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <%@ include file="navbar.jsp" %>
            <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>



<!DOCTYPE html>
<title>Home</title>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
 <link rel="stylesheet" href="/Cinema_Ticket_Booking/pages/navbar.css">
 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css" 
integrity="sha512-SzlrxWUlpfuzQ+pcUCosxcglQRNAq/DZjVsC0lE40xsADsfeQoEypE+enwcOiGjk/bSuGGKHEyjSoQ1zVisanQ==" 
crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" 
  integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
   crossorigin="anonymous">
   <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.3/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
   <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css" 
integrity="sha512-SzlrxWUlpfuzQ+pcUCosxcglQRNAq/DZjVsC0lE40xsADsfeQoEypE+enwcOiGjk/bSuGGKHEyjSoQ1zVisanQ==" 
crossorigin="anonymous" referrerpolicy="no-referrer" /> 
<link rel="icon" type="image/x-icon" href="C:/Users/HP/eclipse-workspace1/CinemaTicketBooking/src/main/webapp/WEB-INF/pages/icon.png"/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
<style>
@charset "UTF-8";
body {
    height: 2000px;
  margin: 0;
  font-family: Arial, Helvetica, sans-serif;
  color:white;
  background-color: #333;
}




.MovieTitle{
    font-size: 16px;
	color: white;
	margin-top: 5px;
}
.Genre{
	margin-top: 18px;
	text-algin : center;
	color: white;
}

.Duration{
	margin-top: 18px;
	text-algin : center;
	color: white;
}
img{
	margin-top:30px;
	margin-left: 30px;
}
.img-fluid {
    max-width: 80%;
    height: auto;
}

.cinemaBox{
margin-top:10px;
background:#2f4f4f ;
width:260px;
border-radius:10px;
display:flex;
justify-content:center;
align-items:center;
flex-direction:column;
flex-grow:0;
}
.cinemaBox>p{
color:white;
width:100%;
}
.cinemaBox>img{

width:200px;
}
.imageclick{
text-decoration:none;
}
.imageclick:hover{
color:#f3f3f3;
}


.modal {
  display: none; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  left: 0;
  top: 0;
  width: 100%; /* Full width */
  height: 100%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
  background-color: rgb(0,0,0); /* Fallback color */
  background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
}

/* Modal Content/Box */
.modal-content {
  background-color: #fefefe;
  margin: 15% auto; /* 15% from the top and centered */
  padding: 20px;
  border: 1px solid #888;
  width:300px; /* Could be more or less, depending on screen size */
}

/* The Close Button */
.close {
  color: #aaa;
  float: right;
  font-size: 28px;
  font-weight: bold;
}

.close:hover,
.close:focus {
  color: black;
  text-decoration: none;
  cursor: pointer;
}

.modalmsg{
display:block;
color:black;
font-size:20px;
}

.Registerbtn{
margin-right:10%;
color:white;
//float:right;

text-decoration:none;
border:solid 2px ;
padding:5px;
 background-image:linear-gradient(to bottom right,#5B247A,#1BCEDF);
}
.Loginbtn{
margin-right:10%;
color:white;
//float:right;

text-decoration:none;
border:solid 2px ;
padding:5px;
 background-image:linear-gradient(to bottom right,#5B247A,#1BCEDF);
}
a:hover{
text-decoration:none;
color:white;
}
 #user {
margin-top:25px;
}
.editA{
border-left:solid 2px lightblue;
}
.logoutA{
border-left:solid 2px pink;
}
</style>
</head>




<body>
 

<div class="container">
   <div class="row"> 
   <c:if test="${currentUser == null }">
    <c:forEach items="${movielist}" var="movielist">
		<div style="margin-left:10px;" class="cinemaBox pop">
		 <a class="imageclick" id="myBtn">
    <img src="/CinemaTicketBooking/images/${movielist.imageData}" class="img-fluid rounded d-block " alt="${movielist.movieName}"></a>
           <label  class="MovieTitle"style="font-size: 24px">${movielist.movieName}</label>
           <label style="display:none">${movielist.movieID}</label>
      <label class="Genre" >${movielist.movieGenre}</label>
      <label class="Duration"style="font-size: 15px;">${movielist.movieDuration}</label>
      <a class="imageclick"  class="cinemaBox pop">See More...</a>

      </div>

  
      
      </c:forEach>
      </c:if>
      
         <c:if test="${currentUser != null }">
    <c:forEach items="${movielist}" var="movielist">
		<div style="margin-left:10px;" class="cinemaBox">
		 <a href="/CinemaTicketBooking/setupUserDetailMovie/${movielist.movieID }" class="imageclick">
    <img src="/CinemaTicketBooking/images/${movielist.imageData}" class="img-fluid rounded d-block " alt="${movielist.movieName}"></a>
           <label  class="MovieTitle"style="font-size: 24px">${movielist.movieName}</label>
           <label style="display:none">${movielist.movieID}</label>
      <label class="Genre" >${movielist.movieGenre}</label>
      <label class="Duration"style="font-size: 15px;">${movielist.movieDuration}</label>
      <a href="/CinemaTicketBooking/setupUserDetailMovie/${movielist.movieID }" class="imageclick">See More...</a>

      </div>

  
      
      </c:forEach>
      </c:if>
      
      		<c:set var="movie" value="${fn:length(movielist)}"/>
		<c:if test="${movie < 1}">
		<tr><td colspan="4">MovieList is Empty</td></tr>
		</c:if>
         </div> 
         </div>

  



<script>
function myFunction() {
  var x = document.getElementById("myTopnav");
  if (x.className === "topnav") {
    x.className += " responsive";
  } else {
    x.className = "topnav";
  }
}

function openNav() {
	  document.getElementById("mySidebar").style.width = "250px";
	  document.getElementById("main").style.marginLeft = "250px";
	  
	}

	function closeNav() {
	  document.getElementById("mySidebar").style.width = "0";
	  document.getElementById("main").style.marginLeft= "0";
	   
	}
	
	// Get the modal
	var modal = document.getElementById("myModal");
      
	// Get the <span> element that closes the modal
	var span = document.getElementsByClassName("close")[0];

	// Get the button that opens the modal
	var btn = document.getElementById("myBtn");
	btn.onclick = function() {
	  modal.style.display = "block";
	}

	var btn1 = document.getElementById("myBtn1");
	btn1.onclick = function() {
	  modal.style.display = "block";
	}
	
	var btn2 = document.getElementById("myBtn2");
	btn2.onclick = function() {
	  modal.style.display = "block";
	}
	// When the user clicks on <span> (x), close the modal
	span.onclick = function() {
	  modal.style.display = "none";
	}

	// When the user clicks anywhere outside of the modal, close it
	window.onclick = function(event) {
	  if (event.target == modal) {
	    modal.style.display = "none";
	  }
	}
	
	
	const allAnchorTags = document.querySelectorAll('.pop');
	  allAnchorTags.forEach(anchor => {
	      // perform operation on each anchor tag
	      anchor.onclick = function() {
	        modal.style.display = "block";
	      }
	    });
	  // When the user clicks on <span> (x), close the modal
	  span.onclick = function() {
	    modal.style.display = "none";
	  }

	  // When the user clicks anywhere outside of the modal, close it
	  window.onclick = function(event) {
	    if (event.target == modal) {
	      modal.style.display = "none";
	    }
	  }
</script>

</body>
</html>