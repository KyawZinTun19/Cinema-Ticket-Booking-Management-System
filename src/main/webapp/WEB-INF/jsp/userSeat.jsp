<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="/CinemaTicketBooking/pages/seat.css">
    <script src="/CinemaTicketBooking/pages/seat.js"></script>
    
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css"
 integrity="sha512-SzlrxWUlpfuzQ+pcUCosxcglQRNAq/DZjVsC0lE40xsADsfeQoEypE+enwcOiGjk/bSuGGKHEyjSoQ1zVisanQ==" 
crossorigin="anonymous" referrerpolicy="no-referrer" />
    <title>Movie Seat Booking</title>
    <style>

i{
   position: relative;
right: 610px;
top:-40px;
color:#fff;

}



i:hover{
transform: scale(1.2);

}
    </style>
  </head>
  <body>

          <a type="submit" class="BackBtn" href="/CinemaTicketBooking/setupUserDetailMovie/${movieID}">
<i class="fa fa-arrow-left fa-3x" aria-hidden="true"></i></a>

<span style="margin-bottom:30px;font-size:24px;">${movieName}</span>

    <ul class="showcasePrice">

    <div class="showcasePrice-group">

      <li>
        <div class="seatA" value="3000"></div>
        <small>${seatAPrice}</small>
      </li>

      <li>
        <div class="seatB" value="4000"></div>
        <small>${seatBPrice}</small>
      </li>

      <li>
        <div class="seatC" value="5000"></div>
        <small>${seatCPrice}</small>
      </li>

      <li>
        <div class="seatD" value="6000"></div>
        <small>${seatDPrice}</small>
      </li>

      <li>
        <div class="seatE" value="6000"></div>
        <small>${seatEPrice}</small>
      </li>

      <li>
        <div class="coupleseatDisplay" value="7000"></div>
        <small>${seatFPrice}</small>
      </li>
    </div>
    </ul>

    <ul class="showcase">
      <div class="showcase-group">
      <li>
        <div class="seat"></div>
        <small>Avaliable</small>
      </li>
      <li>
        <div class="seatB occupied"></div>
      </li>
    </div>
    <div class="showcase-group">
      <li>
        <div class="seat selected"></div>
        <small>Selected</small>
      </li>
      <li>
        <div class="seatC occupied"></div>
        <small>Occupied</small>
      </li>
    </div>
    <div class="showcase-group">
      <li>
        <div class="seatA occupied"></div>
        <small>Occupied</small>

      </li>
      <li>
        <div class="seatD occupied"></div>

      </li>
    </div>
    </ul>

    <div class="purchaseBox">
      <ul class="showcase1" >
      <div class="showcase-group">
      <li>
        <div class="seatD occupied"></div>

      </li>
      </div>
      <li>
        <div class="seatE occupied"></div>
        <small>Occupied</small>
      </li>
          <li>
        <div class="coupleseatDisplay occupied"></div>

      </li>
    </ul>
      </li>
    </ul>
  </div>



    <div class="container">
      <div class="screen"></div>
      <div class="row">
        <div id="A01"  class="seatA">A01</div> 
        <div id="A02" class="seatA">A02</div>
        <div id="A03" class="seatA">A03</div>
        <div id="A04" class="seatA">A04</div>
        <div id="A05" class="seatA">A05</div>
        <div id="A06" class="seatA">A06</div>
        <div id="A07" class="seatA">A07</div>
        <div id="A08" class="seatA">A08</div>
        <div id="A09" class="seatA">A09</div>
        <div id="A10" class="seatA">A10</div>
      </div>
      <div class="row">
        <div id="B01" class="seatB">B01</div>
        <div id="B02" class="seatB">B02</div>
        <div id="B03" class="seatB">B03</div>
        <div id="B04" class="seatB">B04</div>
        <div id="B05" class="seatB">B05</div>
        <div id="B06" class="seatB">B06</div>
        <div id="B07" class="seatB">B07</div>
        <div id="B08" class="seatB">B08</div>
        <div id="B09" class="seatB">B09</div>
        <div id="B10" class="seatB">B10</div>
      </div>

      <div class="row">
        <div id="C01" class="seatC">C01</div>
        <div id="C02" class="seatC">C02</div>
        <div id="C03" class="seatC">C03</div>
        <div id="C04" class="seatC">C04</div>
        <div id="C05" class="seatC">C05</div>
        <div id="C06" class="seatC">C06</div>
        <div id="C07" class="seatC">C07</div>
        <div id="C08" class="seatC">C08</div>
        <div id="C09" class="seatC">C09</div>
        <div id="C10" class="seatC">C10</div>
      </div>

      <div class="row">
        <div id="D01" class="seatD">D01</div>
        <div id="D02" class="seatD">D02</div>
        <div id="D03" class="seatD">D03</div>
        <div id="D04" class="seatD">D04</div>
        <div id="D05" class="seatD">D05</div>
        <div id="D06" class="seatD">D06</div>
        <div id="D07" class="seatD">D07</div>
        <div id="D08" class="seatD">D08</div>
        <div id="D09" class="seatD">D09</div>
        <div id="D10" class="seatD">D10</div>
      </div>

      <div class="row">
        <div id="E01" class="seatE">E01</div>
        <div id="E02" class="seatE">E02</div>
        <div id="E03" class="seatE">E03</div>
        <div id="E04" class="seatE">E04</div>
        <div id="E05" class="seatE">E05</div>
        <div id="E06" class="seatE">E06</div>
        <div id="E07" class="seatE">E07</div>
        <div id="E08" class="seatE">E08</div>
        <div id="E09" class="seatE">E09</div>
        <div id="E10" class="seatE">E10</div>
      </div>

      <div class="row">
        <div id="F01" class="coupleseat">F01</div>
        <div id="F02" class="coupleseat">F02</div>
        <div id="F03" class="coupleseat">F03</div>
        <div id="F04" class="coupleseat">F04</div>
        <div id="F05" class="coupleseat">F05</div>
      </div>
    </div>
 <form action="/CinemaTicketBooking/userPayment"> 
    <ul class="Buyshowcase">
      <button class="purchaseBtn" >Purchase</button>
    </ul>

    <div class="purchaseBox">
    <p class="text" >
      <span id="count">0</span> &nbsp; seats , <span id="total">0</span><span> MMK</span>
    </p>

  </div>
    </form>

  

    <script> 
    const seatsA = document.querySelectorAll('.row .seatA:not(.occupied)');
    const seatsB = document.querySelectorAll('.row .seatB:not(.occupied)');
    const seatsC = document.querySelectorAll('.row .seatC:not(.occupied)');
    const seatsD = document.querySelectorAll('.row .seatD:not(.occupied)');
    const seatsE = document.querySelectorAll('.row .seatE:not(.occupied)');
    const seatsCouple = document.querySelectorAll('.row .coupleseat:not(.occupied)');
    const count = document.getElementById('count');
    const total = document.getElementById('total');
    const ticketPrices = {
      A: 3000,
      B: 4000,
      C: 5000,
      D: 6000,
      E: 7000,
      COUPLE: 10000,
    };

    let selectedSeatsA = [];
    let selectedSeatsB = [];
    let selectedSeatsC = [];
    let selectedSeatsD = [];
    let selectedSeatsE = [];
    let selectedSeatsCouple = [];

    const selectedSeatsAJSON = localStorage.getItem('selectedSeatsA');
    if (selectedSeatsAJSON && selectedSeatsAJSON.trim() !== '') {
      selectedSeatsA = JSON.parse(selectedSeatsAJSON);
    }


    const selectedSeatsBJSON = localStorage.getItem('selectedSeatsB');
    if (selectedSeatsBJSON !== null) {
      selectedSeatsB = JSON.parse(selectedSeatsBJSON);
    }

    const selectedSeatsCJSON = localStorage.getItem('selectedSeatsC');
    if (selectedSeatsCJSON !== null) {
      selectedSeatsC = JSON.parse(selectedSeatsCJSON);
    }

    const selectedSeatsDJSON = localStorage.getItem('selectedSeatsD');
    if (selectedSeatsDJSON !== null) {
      selectedSeatsD = JSON.parse(selectedSeatsDJSON);
    }

    const selectedSeatsEJSON = localStorage.getItem('selectedSeatsE');
    if (selectedSeatsEJSON !== null) {
      selectedSeatsE = JSON.parse(selectedSeatsEJSON);
    }

    const selectedSeatsCoupleJSON = localStorage.getItem('selectedSeatsCouple');
    if (selectedSeatsCoupleJSON !== null) {
      selectedSeatsCouple = JSON.parse(selectedSeatsCoupleJSON);
    }

    updateSelectedSeats();

    [...seatsA, ...seatsB, ...seatsC, ...seatsD, ...seatsE, ...seatsCouple].forEach((seat) => {
      seat.addEventListener('click', () => {
        if (!seat.classList.contains('occupied')) {
          seat.classList.toggle('selected');
          updateSelectedSeats();
        }
      });
    });

    function updateSelectedSeats() {
    	selectedSeatsA = [...document.querySelectorAll('.row .seatA.selected')].map((seatA) => seatA.getAttribute('id'));
    	localStorage.setItem('selectedSeatsA', JSON.stringify(selectedSeatsA));
    	
    	selectedSeatsB = [...document.querySelectorAll('.row .seatB.selected')].map((seatB) => seatB.getAttribute('id'));
    	localStorage.setItem('selectedSeatsB', JSON.stringify(selectedSeatsB));
    	
    	selectedSeatsC = [...document.querySelectorAll('.row .seatC.selected')].map((seatC) => seatC.getAttribute('id'));
    	localStorage.setItem('selectedSeatsC', JSON.stringify(selectedSeatsC));

    	selectedSeatsD = [...document.querySelectorAll('.row .seatD.selected')].map((seatD) => seatD.getAttribute('id'));
    	localStorage.setItem('selectedSeatsD', JSON.stringify(selectedSeatsD));
    	
    	selectedSeatsE = [...document.querySelectorAll('.row .seatE.selected')].map((seatE) => seatE.getAttribute('id'));
    	localStorage.setItem('selectedSeatsE', JSON.stringify(selectedSeatsE));
    	
    	selectedSeatsCouple = [...document.querySelectorAll('.row .coupleseat.selected')].map((coupleseat) => coupleseat.getAttribute('id'));
    	localStorage.setItem('selectedSeatsCouple', JSON.stringify(selectedSeatsCouple));


      let totalA = selectedSeatsA.length * ${seatAPrice};
      let totalB = selectedSeatsB.length * ${seatBPrice};
      let totalC = selectedSeatsC.length * ${seatCPrice};
      let totalD = selectedSeatsD.length * ${seatDPrice};
      let totalE = selectedSeatsE.length * ${seatEPrice};
      let totalCouple = selectedSeatsCouple.length * ${seatFPrice};

      const selectedSeats = [...selectedSeatsA, ...selectedSeatsB, ...selectedSeatsC, ...selectedSeatsD, ...selectedSeatsE, ...selectedSeatsCouple];
      count.innerText = selectedSeats.length;
      total.innerText = totalA + totalB + totalC + totalD + totalE + totalCouple;
      localStorage.setItem("total", totalA + totalB + totalC + totalD + totalE + totalCouple);
      }
    


    var seatNameList = ${seatNameList}; 
    console.log(seatNameList)
    seatNameList.forEach(function(seatName) {

        seatName.classList.add("occupied");

    });
    </script>
    

    


  </body>
</html>