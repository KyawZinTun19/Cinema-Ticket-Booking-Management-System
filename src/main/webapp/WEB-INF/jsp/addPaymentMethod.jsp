<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    <jsp:include page="adminSideBar.jsp"></jsp:include>
<!DOCTYPE html>
<html lang="en" >
<head>
    <title>Admin Update</title>
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
  background-color: #228b22 ;
  /*background-image: linear-gradient(to bottom right,#228b22 ,#008000);*/
 
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


    </style>
</head>
<body>
    <div class="container">
    <h1 class="label">Payment Update</h1>
    <form:form action="/CinemaTicketBooking/addPaymentMethod" method="post" modelAttribute="paymentMethodBean">
     
        <table>
            <tr style="display: none;">
                <td><label>Id</label> </td>
                <td><form:input type="text" name="Id" path="paymentMethodID" readonly="true"></form:input>
                 </td>
            </tr>
            <tr>
                <td><label>Name</label> </td>
                <td><form:input type="text" name="name" path="paymentMethodName"></form:input>
                 </td>
            </tr>
            <tr>
                <td><label>Phone</label> </td>
                <td><form:input type="text" name="phone" path="paymentMethodPhone"></form:input>
                 </td>
            </tr>
            <tr>
                <td><input type="button" value="Cancel" class="CancelButton" onclick="location.href='/CinemaTicketBooking/paymentMethodTable'"></td>
                <td><form:button class="UpdateButton">Add</form:button></td>
            </tr>

        </table>
        <div style="color:red">${error}</div>




    </form:form>
    </div>
</body>