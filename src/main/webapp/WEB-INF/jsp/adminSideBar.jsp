<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
 <link rel="stylesheet" href="/Cinema_Ticket_Booking/pages/navbar.css">
 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css" 
integrity="sha512-SzlrxWUlpfuzQ+pcUCosxcglQRNAq/DZjVsC0lE40xsADsfeQoEypE+enwcOiGjk/bSuGGKHEyjSoQ1zVisanQ==" 
crossorigin="anonymous" referrerpolicy="no-referrer" />

<style>
	@charset "UTF-8";
body {
  margin: 0;
  font-family: Arial, Helvetica, sans-serif;
  color:white;
  background-color: #333;
}

.topnav {
  overflow: hidden;
  background-image:linear-gradient(to bottom right,#5B247A,#1BCEDF);

}

.topnav a {
  float: left;
  display: block;
  color: #f2f2f2;
  text-align: center;
  padding: 20px 16px;
  text-decoration: none;
  font-size: 17px;
    margin-left: 40px;
}

/*.topnav a:hover {
  background-color: #ddd;
  color: black;
}*/

#active {
  
  color:yellow;
}

.topnav .icon {
  display: none;
}

@media screen and (max-width: 600px) {
  .topnav a:not(:first-child) {display: none;}
  .topnav a.icon {
    float: right;
    display: block;
  }
}

@media screen and (max-width: 600px) {
  .topnav.responsive {position: relative;}
  .topnav.responsive .icon {
    position: absolute;
    right: 0;
    top: 0;
  }
  .topnav.responsive a {
    float: none;
    display: block;
    text-align: left;
  }
}

.w3rcontainer{
   border: 1px solid;
   border-radius: 2px;
} 
.hover-underline-animation {
  display: inline-block;
  position: relative;
 
}

.hover-underline-animation:after {
  content: '';
  position: absolute;
  width: 100%;
  transform: scaleX(0);
  height: 3px;
  bottom: 0;
  left: 0;
  background-color:white;
  transform-origin: bottom right;
  transition: transform 0.25s ease-out;
}

.hover-underline-animation:hover:after {
  transform: scaleX(1);
  transform-origin: bottom left;
}
/*----------------------------------------------------sidebar-----------------------------------------------*/
.sidebar {
  height: 100%;
  width: 0;
  position: fixed;
  z-index: 1;
  top: 0;
  left: 0;
  background-color:#000d1a;
   
  overflow-x: hidden;
  transition: 0.3s;
  padding-top: 60px;
}

.sidebar a {
  padding: 8px 2px 8px 32px;
  text-decoration: none;
  font-size: 14px;
  color:white;
  display: block;
  transition: 0.3s;
}

.sidebar a:hover {
  color: #f1f1f1;
/*  color:#818181;*/
}

.sidebar .closebtn {
  position: absolute;
  top: 0;
  right: 25px;
  font-size: 36px;
  margin-left: 50px;
}

.openbtn {
  font-size: 20px;
  cursor: pointer;
  background-color:white;
  color: white;
  padding: 10px 15px;
  border: none;
  position:absolute;
}

.openbtn:hover {
  background-color: #444;
}

/* On smaller screens, where height is less than 450px, change the style of the sidenav (less padding and a smaller font size) */
@media screen and (max-height: 450px) {
  .sidebar {padding-top: 15px;}
  .sidebar a {font-size: 18px;}
}
/* my custom css*/
#user{
padding: 8px 8px 8px 32px;
  text-decoration: none;
  font-size: 16px;
  display: block;
  transition: 0.3s;
  
}
#useric{
position:absolute;
top:3%;
left:37%;
 
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

  <a onclick="openNav()" class="hover-underline-animation" id="main">Profile</a>
  <a href="/CinemaTicketBooking/income" class="hover-underline-animation">Income</a>
  <a href="/CinemaTicketBooking/adminTable" class="hover-underline-animation">Admin</a>
  <a href="/CinemaTicketBooking/userTable" class="hover-underline-animation">User</a>
  <a href="/CinemaTicketBooking/paymentMethodTable" class="hover-underline-animation">Payment Method</a>
  <a href="/CinemaTicketBooking/movieTable" class="hover-underline-animation">Movie</a>
    <a href="/CinemaTicketBooking/seatTable" class="hover-underline-animation">Seat </a>
    <a href="/CinemaTicketBooking/userPaymentTable" class="hover-underline-animation">Manage Payment </a>
  <a href="javascript:void(0);" class="icon" onclick="myFunction()">
    <i class="fa fa-bars"></i>
  </a>
</div>

<div id="mySidebar" class="sidebar">
  <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">×</a>
  <i class="fa-solid fa-circle-user fa-3x" id="useric"></i>
  <p class="editA" id="user">${currentAdminName}</p>
   <p class="logoutA" id="user">${currentAdmin}</p> 
   <table>
     <tr> <td><a href="/CinemaTicketBooking/setupUpdateAdmin/${adminId }" class="editA"><i class="fa-solid fa-user-pen"></i>&nbsp;&nbsp;&nbsp;&nbsp;Edit Profile</a></td> </tr>
     <tr> <td><a href="/CinemaTicketBooking/adminLogout" class="logoutA"><i class="fa-solid fa-power-off"></i>&nbsp;&nbsp;&nbsp;&nbsp;LOGOUT</a></td> </tr>
   </table>
   
</div>

<div style="padding-left:16px">
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
</script>

</body>
</html>

 