<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css" integrity="sha512-SzlrxWUlpfuzQ+pcUCosxcglQRNAq/DZjVsC0lE40xsADsfeQoEypE+enwcOiGjk/bSuGGKHEyjSoQ1zVisanQ=="
     crossorigin="anonymous" referrerpolicy="no-referrer" />
    <title>Document</title>
    <style>
        body{
            background-color: rgb(57, 55, 58);
        }
       .loading{
        height: 50px;
        width: 50px;
        position: relative;
        top:5px;
        bottom: 45px;
        margin:auto;
        
        border-top: 3px solid rgb(232, 232, 240);
        border-right: 4px solid transparent;
        border-radius: 50%;
        animation: spin 0.5s linear infinite;
       }
       @keyframes spin{
        from{
            transform: rotate(0deg);
        }
        to{
            transform: rotate(360deg);
        }
       }
    p{
        margin-top:25%;
        color: antiquewhite;
        
    }
    .container{
        position: relative;
        width:50%;
        margin:auto;
    }
    .toHome{
  font-family: "Roboto", sans-serif;
  text-transform: uppercase;
  outline: 0;
width:15%;
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
  margin-top:15%;
  margin-left:34%;

}
.toTicket{
font-family: "Roboto", sans-serif;
  text-transform: uppercase;
  outline: 0;
width:15%;
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
  margin-top:15%;
  margin-left:1%;

}
    </style>
</head>
<body>
    <div class="container">
    <p>Please Wait a few minute ! We are checking your payment and Check our response in MyTickets........</p>
    <div class="loading">

    </div>
</div>
<button class="toHome" onclick="location.href='/CinemaTicketBooking/'" >BACK TO HOME</button>
<button class="toTicket" onclick="location.href='/CinemaTicketBooking/myticket'" >MyTickets</button>
</body>
</html>