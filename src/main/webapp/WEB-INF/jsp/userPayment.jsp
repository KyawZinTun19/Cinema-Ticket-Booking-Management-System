<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
         <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <jsp:include page="navbar.jsp"></jsp:include>
<!DOCTYPE html>
<html>
<head>
  <!-- Design by foolishdeveloper.com -->
    <title>User Payment</title>
 
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;500;600&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css"
 integrity="sha512-SzlrxWUlpfuzQ+pcUCosxcglQRNAq/DZjVsC0lE40xsADsfeQoEypE+enwcOiGjk/bSuGGKHEyjSoQ1zVisanQ==" 
crossorigin="anonymous" referrerpolicy="no-referrer" />
    <!--Stylesheet-->
    <style media="screen">
    

      *,
*:before,
*:after{
    padding: 0;
    margin: 0;
    box-sizing: border-box;
}
body{
    background-color: #080710;
}
.background{
    width: 430px;
    height: 520px;
    position: absolute;
    transform: translate(-50%,-50%);
    bottom: 30%;
    left: 50%;
    top: 50%;
}
    i{
	position: relative;
	right: -30px;
	top: 30px;
	color:#fff;
	width:50%;
}




.background .shape{
    height: 200px;
    width: 200px;
    position: absolute;
    border-radius: 50%;
}
.shape:first-child{
    background: linear-gradient(
        #1845ad,
        #23a2f6
    );
    left: -80px;
    top: 100px;
}
.shape:last-child{
    background: linear-gradient(
        to right,
        #ff512f,
        #f09819
    );
    right: -30px;
    bottom: -80px;
}
form{
    height: 640px;
    width: 400px;
    background-color: rgba(255,255,255,0.13);
    position: absolute;
    transform: translate(-50%,-50%);
    margin-top:50px;
    top: 52%;
    left: 50%;
    border-radius: 10px;
    backdrop-filter: blur(10px);
    border: 2px solid rgba(255,255,255,0.1);
    box-shadow: 0 0 40px rgba(8,7,16,0.6);
    padding: 50px 35px;
}
form *{
    font-family: 'Poppins',sans-serif;
    color: #ffffff;
    letter-spacing: 0.5px;
    outline: none;
    border: none;
}
form h3{
    font-size: 30px;
    font-weight: 500;
    line-height: 42px;
    text-align: center;
}

form h1{
	margin-top:none;
}
label{
    display: block;
    margin-top: 5px;
    font-size: 16px;
    font-weight: 500;
}
input{
    display: block;
    height: 50px;
    width: 100%;
    background-color: rgba(255,255,255,0.07);
    border-radius: 3px;
    padding: 0 10px;
    font-size: 14px;
    font-weight: 300;
}
::placeholder{
    color: #e5e5e5;
}
button{
    margin-top: 40px;
    width: 100%;
    background-color: #ffffff;
    color: #080710;
    padding: 15px 0;
    font-size: 18px;
    font-weight: 600;
    border-radius: 5px;
    cursor: pointer;
}

h5{
	font-size: 15px;
	margin-top: 15px;
	text-align: center;
}
.container{

	
}
.payment{
background-color:rgba(100,100,100,0.1);
}

.payments{
background-color:rgba(100,100,100,0.1);
color:black;
}
.error{
text-align:center;
color:red;
}


    </style>
</head>

<body>


          <a type="submit" class="BackBtn" href="/CinemaTicketBooking/setupUserDetailMovie/${movieID}">
<i class="fa fa-arrow-left fa-3x" aria-hidden="true"></i></a>

<div class="container">
    <div class="background">
        <div class="shape"></div>
        <div class="shape"></div>
        </div>
    
    
    <form:form  action="/CinemaTicketBooking/continuePayment" enctype="multipart/form-data" method="post" modelAttribute="paymentbean">
        <table>
            <tr>
                <td> 
        <h1> Ticket Payment Method </h1>
                </td>
            </tr> 
<tr><td><p id="seat-container"></p><p style="color:red" id="seaterror-container"></p></td></tr>

                           
        <tr><td><p class="message" style="color:red">${error}</p></td></tr>
        <div class="second-row">
            <div class="number">
<form:input path="amount" value="0" id="totalInput" style="display:none;"/>
<form:input path="seatANames"  id="seatAInput" value="" style="display:none;"/>
<form:input path="seatBNames"  id="seatBInput" value="" style="display:none;"/>
<form:input path="seatCNames"  id="seatCInput" value="" style="display:none;"/>
<form:input path="seatDNames"  id="seatDInput" value="" style="display:none;"/>
<form:input path="seatENames"  id="seatEInput" value="" style="display:none;"/>
<form:input path="seatFNames"  id="seatFInput" value="" style="display:none;"/>

<tr>
    <td>
        <form:label path="phone">Phone Number:</form:label>
    </td>
</tr>
<tr>
    <td></td>
</tr>
<tr>
    <td>
        <form:input path="phone" class="input-field" type="tel" placeholder="Phone Number"/><br>
    </td>
</tr>

        </div>
        <div class="third-row">
            <div class="code">
                <tr>
                    <td> 
                    <form:label path="transactionImage">Transation Image:</form:label>
                </td>
                </tr>
                <tr>
                    <td>
            <form:input path="transactionImage" class="input-field" type="file" accept="image/*" placeholder="Enter Your Tran-code"/><br>
        </td>
        </tr>
            </div>
        </div>
        <div class="first-row">
            <div class="Method">
                <tr>
                    <td> 
                    <form:label path="pay"> PayMent Method:</form:label>
                                </td>
                </tr>
                <tr>
                    <td>
                    <form:select path="pay" class="payment">
                    <!--<form:option value="NONE" label="select"></form:option>-->
                    <form:options items="${paymentlist}"  class="payments"/>
                    </form:select><br>
                          
                           </td>
                    </tr>
             </div>
        </div>
    </table>
    
   <br> <button class="con">Continute

    </button>
    </form:form>
</div>
<%  
session.removeAttribute("paymenterror");
 
%>
<script>

var valuesA = localStorage.getItem("selectedSeatsA");
var valuesB = localStorage.getItem("selectedSeatsB");
var valuesC = localStorage.getItem("selectedSeatsC");
var valuesD = localStorage.getItem("selectedSeatsD");
var valuesE = localStorage.getItem("selectedSeatsE");
var valuesF = localStorage.getItem("selectedSeatsCouple");
const myInput = document.getElementById('totalInput');
const seatAInput = document.getElementById('seatAInput');
const seatBInput = document.getElementById('seatBInput');
const seatCInput = document.getElementById('seatCInput');
const seatDInput = document.getElementById('seatDInput');
const seatEInput = document.getElementById('seatEInput');
const seatFInput = document.getElementById('seatFInput');
var total = localStorage.getItem("total");
myInput.value = total;

var selectedSeatsAs = JSON.parse(valuesA);
var selectedSeatsBs = JSON.parse(valuesB);
var selectedSeatsCs = JSON.parse(valuesC);
var selectedSeatsDs = JSON.parse(valuesD);
var selectedSeatsEs = JSON.parse(valuesE);
var selectedSeatsFs = JSON.parse(valuesF);

// Create a comma-separated string of seat IDs
var seatAIds = selectedSeatsAs.join(", ");
var seatBIds = selectedSeatsBs.join(", ");
var seatCIds = selectedSeatsCs.join(", ");
var seatDIds = selectedSeatsDs.join(", ");
var seatEIds = selectedSeatsEs.join(", ");
var seatFIds = selectedSeatsFs.join(", ");

seatAInput.value = selectedSeatsAs;
seatBInput.value = selectedSeatsBs;
seatCInput.value = selectedSeatsCs;
seatDInput.value = selectedSeatsDs;
seatEInput.value = selectedSeatsEs;
seatFInput.value = selectedSeatsFs;
console.log(seatAIds);

// Select the container element where we'll display the selected seats
var seatContainer = document.querySelector('#seat-container');
var seatContainerError = document.querySelector('#seaterror-container');

// Create a <p> element and set its text content to the seatIds string
var seatElement = document.createElement('p');
let seats = [seatAIds, seatBIds, seatCIds, seatDIds, seatEIds, seatFIds];
let selectedSeats = seats.filter(s => s !== null && s !== "").join(", ");

if (selectedSeats !== "") {
  seatElement.textContent = "You have purchased seats " + selectedSeats + " for the price of "+total+".";
} else {
	seatContainerError.textContent = "You have not purchased any seats.";
}





// Add the <p> element to the seatContainer element
seatContainer.appendChild(seatElement);




</script>
</body>
</html>