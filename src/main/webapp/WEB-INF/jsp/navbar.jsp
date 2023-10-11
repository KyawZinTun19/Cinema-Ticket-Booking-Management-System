<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
 
 <link rel="stylesheet" href="/CinemaTicketBooking/pages/navbar.css">
  
 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css" 
integrity="sha512-SzlrxWUlpfuzQ+pcUCosxcglQRNAq/DZjVsC0lE40xsADsfeQoEypE+enwcOiGjk/bSuGGKHEyjSoQ1zVisanQ==" 
crossorigin="anonymous" referrerpolicy="no-referrer" /> 
<link rel="icon" type="image/x-icon" href="C:/Users/HP/eclipse-workspace1/CinemaTicketBooking/src/main/webapp/WEB-INF/pages/icon.png"/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
        
  <style>
  /* The Modal (background) */
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
 
<div class="topnav" id="myTopnav">

  <a href="/CinemaTicketBooking/" id="active" class="hover-underline-animation">Home</a>
  <c:if test="${currentUser != null }">
  <a onclick="openNav()" class="hover-underline-animation" id="main">Profile</a>
  <a href="/CinemaTicketBooking/myticket" class="hover-underline-animation">My Tickets</a>
  <a href="/CinemaTicketBooking/contactUs" class="hover-underline-animation" >Contact Us</a>
  <a href="javascript:void(0);" class="icon" onclick="myFunction()">
    <i class="fa fa-bars"></i>
  </a>
  </c:if>
  <c:if test="${currentUser == null }">
  <a  class="hover-underline-animation" id="myBtn">Profile</a>
  <a  class="hover-underline-animation" id="myBtn1">My Tickets</a>
  <a  class="hover-underline-animation" id="myBtn2">Contact Us</a>
  <a href="javascript:void(0);" class="icon" onclick="myFunction()">
    <i class="fa fa-bars"></i>
  </a>
  </c:if>
</div>

<div id="mySidebar" class="sidebar">
 
  <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">×</a>
  
  <i class="fa-solid fa-circle-user fa-3x" id="useric"></i>
   <p id="user">${currentUser}</p>
   
   <table>
     <tr> <td><a href="/CinemaTicketBooking/touserUpdate/${userId }" class="editA"><i class="fa-solid fa-user-pen"></i>&nbsp;&nbsp;&nbsp;&nbsp;Edit Profile</a></td> </tr>
     <tr> <td><a href="/CinemaTicketBooking/userLogout" class="logoutA"><i class="fa-solid fa-power-off"></i>&nbsp;&nbsp;&nbsp;&nbsp;LOGOUT</a></td> </tr>
   </table>
   
</div>
  

<!-- The Modal -->
<div id="myModal" class="modal">

  <!-- Modal content -->
  <div class="modal-content">
    <span class="close">&times;</span>
     <p class="modalmsg">Let's purchase tickets and Enjoy!</p>
     <a href="/CinemaTicketBooking/setupAccountLogin" class="Loginbtn">Login</a>
     <a href="/CinemaTicketBooking/setupUserSignUp" class="Registerbtn">Register</a>
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
</script>

</body>
</html>

 