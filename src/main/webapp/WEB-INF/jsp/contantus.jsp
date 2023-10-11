<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        
      <%@ include file="navbar.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css" integrity="sha512-SzlrxWUlpfuzQ+pcUCosxcglQRNAq/DZjVsC0lE40xsADsfeQoEypE+enwcOiGjk/bSuGGKHEyjSoQ1zVisanQ=="
     crossorigin="anonymous" referrerpolicy="no-referrer" />
<title>Insert title here</title>
<style>
body{
    background-color:#333;

}

.hcontant{
    font-size:30px;
    text-align: center;
    position:relative;
    top:-30px;
    color: rgb(0, 174, 255);
}



.contant{
    margin-left: 5%;
    margin-top: 8%;
    color: #fff;
    font-size:20px;
    position:relative;
    top:0px;

}


.cucontainer{
    height: 400px;
    width: 400px;
    background-color: rgba(255,255,255,0.13);
    position: absolute;
    transform: translate(-50%,-50%);
    top: 50%;
    left: 50%;
    border-radius: 10px;
    backdrop-filter: blur(10px);
    border: 2px solid rgba(255,255,255,0.1);
    box-shadow: 0 0 40px rgba(8,7,16,0.6);
    padding: 50px 35px;


}
TExtarea{
width: 365px;
    height: 175px;
}
	
</style>
</head>

<body>
    <div class="cucontainer">
    <div class="hcontant">    <h3 class="Chead">Contant Us</h3>
    </div>

    <div class="contant"><i class="fa-solid fa-envelope"></i> Email :   aungminkhant.aln01@gmail.com</div>

    <div class="contant"><i class="fa-solid fa-phone"></i> Phone   :   +959972257054</div>

    <div class="contant"><i class="fa-brands fa-facebook"></i> Facebook   :   4inOne Cinema</div>

    <div class="contant"><i class="fa-solid fa-map-location-dot"></i> Office Adress    :   Ward7,Beteen Pyay Road and Kyun Taw Road,Karmaryut Tsp Near Seik Pyo Yay Bus Stop,Yangon</div>

    

    <!--  <div class="contant"><i class="fa-solid fa-pen"></i> Suggest here    : <TExtarea placeholder="Message"></TExtarea></div>
</div> -->

</body>
</html>