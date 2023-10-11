<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <%@ include file="navbar.jsp" %>
            <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
            
<!DOCTYPE html>
<html>
<head>
<title>
Movie Detail</title>

<meta charset="ISO-8859-1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css"
 integrity="sha512-SzlrxWUlpfuzQ+pcUCosxcglQRNAq/DZjVsC0lE40xsADsfeQoEypE+enwcOiGjk/bSuGGKHEyjSoQ1zVisanQ==" 
crossorigin="anonymous" referrerpolicy="no-referrer" />
<style>
body{
background-color:#2f4f4f;
.table{
  text-align: center;
  margin-top:30px;
  
  
  }
}
.buyTicket{
  font-family: "Roboto", sans-serif;
  text-transform: uppercase;
  outline: 0;
width: 15%;
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
  margin-left:555px;

}


.buyTicket:hover{

transform: scale(1.2);
color: #000000;
background: linear-gradient(to right, rgb(186, 250, 150), rgb(56, 143, 151));
}

.container{
    height: 720px;
    width: 400px;
    background-color: rgba(255,255,255,0.13);
    position: absolute;
    transform: translate(-50%,-50%);
    top: 72%;
    left: 50%;
    border-radius: 10px;
    backdrop-filter: blur(10px);
    border: 2px solid rgba(255,255,255,0.1);
    box-shadow: 0 0 40px rgba(8,7,16,0.6);
    padding: 50px 35px;
}

.BackBtn{
  font-family: "Roboto", sans-serif;
  text-transform: uppercase;
  outline: 0;
size: 50%;
  /*background-image: linear-gradient(to bottom right,#228b22 ,#008000);*/
  width: 75%;
  border: 0px ;
  padding: 15px;
  color: #FFFFFF;
  font-size: 14px;
  -webkit-transition: all 0.3 ease;
  transition: all 0.3 ease;
  cursor: pointer;

}
i{
margin-top:20px;
margin-left:auto;

}

i:hover{
transform: scale(1.2);

}
.Imovie{
	margin-top:-15px;
}

.Mmovie{
	margin-top:15px;
	margin-left:auto;
}
.Gmovie{
	margin-top:15px;
	margin-left:auto;
}
.Dmovie{
	margin-top:15px;
	margin-left:auto;
}
.SDmovie{
	margin-top:15px;
	margin-left:auto;
}
.STmovie{
	margin-top:15px;
	margin-left:auto;
}
.Demovie{
	margin-top:15px;
	margin-left:auto;
}
.Bmovie{
	margin-top:15px;
	margin-left:-400px;
	
	
}

</style>
</head>
<body>

<form:form action="/CinemaTicketBooking/userseat" method="get" >
<a type="submit" class="BackBtn" href="/CinemaTicketBooking/">
<i class="fa fa-arrow-left fa-3x" aria-hidden="true"></i></a>


<div class="container">
	<div class="Imovie">
	<img alt="Movie" src="/CinemaTicketBooking/images/${movieDetailImage}" width="400" height="400">
</div>
	<div class="Mmovie"><b>Name</b>        :  ${movieName}</div>
	<div class="Gmovie"><b>Genre</b>       :  ${movieGenre}</div>		
	<div class="Dmovie"><b>Duration</b>    :  ${movieDuration}</div>		
	<div class="SDmovie"><b>Start Date</b> :  ${startDate}</div>		
	<div class="STmovie"><b>Start Time</b> :  ${startTime}</div>		
	<div class="Demovie"><b>Description</b>: ${movieDescription} </div>	
	<div class="Bmovie"><button class="buyTicket" > Buy Ticket </button></div>		
</div>	

</form:form>


</body>
</html>