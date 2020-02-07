<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Vector" %>
<%@page import="java.util.Iterator" %>

<%@page import="MediChecker.Database" %>
<%@page import="MediChecker.Query" %>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>MediChecker</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="robots" content="all,follow">
    <!-- Bootstrap CSS-->
    <link rel="stylesheet" href="vendor/bootstrap/css/bootstrap.min.css">
    <!-- Google fonts-->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat:700,800&amp;display=swap">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Poppins:300,400&amp;display=swap">
    <!-- theme stylesheet-->
    <link rel="stylesheet" href="css/style.default.css" id="theme-stylesheet">
    <!-- Custom stylesheet - for your changes-->
    <link rel="stylesheet" href="css/custom.css">
    <!-- Favicon-->
    <link rel="shortcut icon" href="img/favicon.ico">
    <!-- Tweaks for older IEs--><!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script><![endif]-->
        <style>
        table{
          overflow-y:scroll;
          height:600px;
          display:block;
          scroll-behavior: smooth;
       }
       </style>
  </head>
  <body onload="insertTable()">
    <!-- navbar-->
    <header class="header" id="header">
    <div>
      <!-- Primary Navbar-->
      <nav class="navbar navbar-expand-lg navbar-light py-4 border-custom bg-dark-custom">
          <div class = "col-lg-10"> 
              <div class="row">
                <h1>MediChecker</h1>
                <i class="far fa-check-circle" style="font-size: 3rem; float: left;"></i>
            </div>
        </div>
        <div class = "col-lg-2" style = "float: right;">
            <button class="btn btn-primary" type="submit" onclick="location.href='index.html'" id="btn-back" style="border-radius: 5px;">Back to Search</button>
        </div>
      </nav>
    </div>
    </header>
    
    <script>
      var Defi = [];
      var Provi = [];
      var AvCost = [];
      var locations = [];
      var zipCode = [];
      var distance;
      var userLoc;
       //var locations = ["Kansas", "Alabama", "Toronto", "New York", "New Jersey", "Kentucky"];
      var prevInfoBox;
      var prevMarker;
      var arMarker = [];
      var infoBoxes = [];
      var userLoc;
      var userLatLong
    </script>
    
    <!-- Hero Banner-->
    <section class="hero-home bg-cover bg-center" style="background-color: #0cb8b6; height: 250px; width: 100%;">
      <div class="container index-forward py-5 mb-5">
        <div class="row text-white">
          <div class="col-lg-12">
            <p class="h4 text-shadow" style="text-align: center;">Here to help you find the best price and location for all your medical needs. </p>
          </div>
        </div>
      </div>
    </section>

    <!-- Form Section-->
    <section id="form-section">
        <div class="col-lg-12">
          <div class="row">
            <div class="col-lg-6">
                <table class="table table-hover" id="resultsTable">
                    <thead>
                      <tr>
                        <th>Results</th>
                      </tr>
                    
                <tr>
				<td>Definition</td>
				<td>Provider Name</td>
				<td>Distance (mi)</td>
				<td>Average Total Payments ($)</td>
      </tr>
    </thead>
    <tbody>

	<%
	
	/*
	* This function needs to be optimized and refactored
	* User input will be integrated from the form on the front end
	*/
	
	String id = request.getParameter("gridRadios");		// User selects this with radio buttons on the front-end and is either "code" or "procedure"
  String input = request.getParameter("inputSearchCriteria");	// This input will either be a DRG code or procedure/condition input from the user form 
  String inputZip = request.getParameter("inputZip");
	
    String inputMinPrice = request.getParameter("inputMinPrice"); // Gets minimum price from the user input
    	if (inputMinPrice.isEmpty()) { // If no min price is selected the default value is set
    		inputMinPrice = "0";
        }
            	 	
    String inputMaxPrice = request.getParameter("inputMaxPrice"); // Gets maximum price from the user input
        if (inputMaxPrice.isEmpty()) { // If no max price is selected the default value is set
        	inputMaxPrice = "99999999";
        }
    String inputMaxDist = request.getParameter("inputMaxDist"); 
      int maxDist = 0;
      if (inputMaxDist.isEmpty()) { // If no max price is selected the default value is set
        maxDist = 100;
      }
      else
      {
        maxDist = Integer.parseInt(inputMaxDist);
      }
    
    
    String sort = request.getParameter("SortBy");
    String sortSql = null;
    
    if (sort.equals("price")) {
    	sortSql = "ORDER BY AverageTotalPayments ASC";
    }
    
    if (sort.equals("distance")) {
    	sortSql = " ";
    }
    
    if (sort.equals("ranking")) {
    	sortSql = " ";
    }
 
	
    List<Query> output = null;
	Database db = new Database();
	
	if (id.equals("code")) { // If the radio button selected is search by code
		
		output = db.executeDBQuery("SELECT * FROM medichecker WHERE Definition LIKE '" + input + "%' AND AverageTotalPayments > " + inputMinPrice + " AND AverageTotalPayments < " + inputMaxPrice + " " + sortSql + ";");
	}	
	else if (id.equals("procedure")) { // If the radio button selected is search by procedure/condition
		
		output = db.executeDBQuery("SELECT * FROM medichecker WHERE Definition LIKE '%" + input + "%' AND AverageTotalPayments > " + inputMinPrice + " AND AverageTotalPayments < " + inputMaxPrice + " " + sortSql + ";");
	}
	else if (id.equals("description")) {
		
		Vector splitInput = new Vector();
		String[] splitArray = input.split(" ");
		
		for (int i=0; i<splitArray.length; i++)
		{
			if (splitArray[i].length() > 2) {
				splitInput.add(splitArray[i]);
			}
		}
		
		String query = "SELECT * FROM medichecker WHERE ";
		
		for(int i=0; i<splitInput.size(); i++) {
			System.out.println(splitInput.get(i));
			System.out.println(splitInput.lastElement());
			System.out.println(query);
			if (splitInput.get(i) != splitInput.lastElement()) {
				query += "Definition LIKE '%" + splitInput.get(i) + "%' OR ";
			}
			else {
				query += "Definition LIKE '%" + splitInput.get(i) + "%' AND AverageTotalPayments > " + inputMinPrice + " AND AverageTotalPayments < " + inputMaxPrice + " " + sortSql + ";";
			}
		}
		
		//System.out.println(query);
		output = db.executeDBQuery(query);
		
	}
	
	int i = 0;
	double mileageRate = 0.58;
	
  	for (Query obj : output)
  		{
        if(i < 10){
          String def = obj.getDefinition();
          String prov = obj.getProviderName();
          String provid = obj.getProviderID();         
          float avCost = obj.getAvgTotalPayments();
          String Addr = obj.getProviderAddress() + ", " + obj.getProviderZip();
          //System.out.println(Addr);
          String Zip = obj.getProviderZip();
          double dist = db.getDistanceValue(inputZip, maxDist, provid);
          int distanceInt =(int)(dist);
          
          double ranking = avCost + (mileageRate * distanceInt * 2);

          if (dist != 0.0)
          {
        	obj.setDistance(distanceInt);
            System.out.println(distanceInt);
            i++;
   		 %>
    		<tr>
    			<td><%=obj.getDefinition()%></td>
    			<td><%=obj.getProviderName()%></td>
    			<td><%=obj.getDistance()%></td>
    			<td><%=obj.getAvgTotalPayments()%></td>
        </tr>
        <script>
          userLoc = "<%= inputZip%>";
          Defi.push("<%= def%>");
          Provi.push("<%= prov%>");
          AvCost.push("<%= avCost%>");
          locations.push("<%= Addr%>");
          zipCode.push("<%= Zip%>");
        </script>
      <%        
      }
    }
      else{
        break;
      
      }
    }
    %>
    <script>
      //237,238,239
      //hue 140, sat 14, lum 224
      //154,159,165
      //140,14,150
      var table = document.getElementById("resultsTable");
      var currSelected;
      function addRowHandlers() {
        //var table = document.getElementById("resultsTable");
        var rows = table.getElementsByTagName("tr");
        for (i = 2; i < rows.length; i++) {
          var currentRow = table.rows[i];
          var createClickHandler = 
            function(row) 
              {
              return function() {

                if (currSelected){
                  currSelected.style.backgroundColor = "rgb(237, 238, 239)";
                }
                var cell = row.getElementsByTagName("td")[1];
                row.style.backgroundColor = "rgb(154, 159, 165)";
                currSelected = row;
                var id = cell.innerHTML;
                for (var j = 0; j<arMarker.length; j++){
                  if (arMarker[j].getTitle() == id){
                    var infowindow = infoBoxes[j];
                    if (prevInfoBox){
                          prevInfoBox.close();
                          prevMarker.setAnimation(null);

                        }
                        prevInfoBox = infowindow;
                        prevMarker = arMarker[j];
                        infowindow.open(map, arMarker[j]);
                        arMarker[j].setAnimation(google.maps.Animation.BOUNCE);
                      }
                    }
                  }
                }
        currentRow.onclick = createClickHandler(currentRow);
            }
          };
        
        window.onload = addRowHandlers();
        
      </script>

                    </tbody>
                  </table>
            </div>
            <div class="col-lg-6">
               	<style>
                    /* Always set the map height explicitly to define the size of the div
                     * element that contains the map. */
                     #map {
                      height: 600px;  /* The height is 400 pixels */
                      width: 100%;  /* The width is the width of the web page */
                     }
               	</style>
                <div id="map"></div>
                <script>
                    function initMap() {
                      var map = new google.maps.Map(document.getElementById('map'), {
                        zoom: 4,
                        center: {lat: 35, lng: -96}
                      });

                      var homeCoder = new google.maps.Geocoder();
                      homeCoder.geocode({'address': userLoc}, function(results, status) {
                        if (status === 'OK') {
                          var icons = {
                            url: "https://cdn1.iconfinder.com/data/icons/real-estate-83/64/x-24-512.png", // url
                            scaledSize: new google.maps.Size(35, 35), // scaled size
                          };
                          var home = new google.maps.Marker({
                            map: map,
                            animation: google.maps.Animation.DROP,
                            icon: icons,
                            position: results[0].geometry.location,
                            title: "Your Location" 
                          });
                          
                        } else {
                          alert('Geocode was not successful for the following reason: ' + status);
                        }
                      });

                      for (var i = 0; i< locations.length; i++){
                        geocodeAddress(map, 10, userLoc, locations[i], i)
                          }
                      /*infoWindow = new google.maps.InfoWindow;
              
                      // Try HTML5 geolocation.
                      if (navigator.geolocation) {
                        navigator.geolocation.getCurrentPosition(function(position) {
                          var pos = {
                            lat: position.coords.latitude,
                            lng: position.coords.longitude
                          };
                          userLoc = pos;
              
                          infoWindow.setPosition(pos);
                          infoWindow.setContent('You are Here');
                          infoWindow.open(map);
                          map.setCenter(pos);
                        }, function() {
                          handleLocationError(true, infoWindow, map.getCenter());
                        });
                      } else {
                        // Browser doesn't support Geolocation
                        handleLocationError(false, infoWindow, map.getCenter());
                      }*/
                    }

                  function handleLocationError(browserHasGeolocation, infoWindow, pos) {
                      infoWindow.setPosition(pos);
                      infoWindow.setContent(browserHasGeolocation ?
                                            'Error: The Geolocation service failed.' :
                                            'Error: Your browser doesn\'t support geolocation.');
                      infoWindow.open(map);
                    }

                    function geocodeAddress(resultsMap, distance, origin, address, num) {
                      var geocoder = new google.maps.Geocoder();
                      geocoder.geocode({'address': address}, function(results, status) {
                        if (status === 'OK') {
                          //resultsMap.setCenter(results[0].geometry.location);
                          addMarker(resultsMap, origin, distance, address, results, num);
                        } else {
                          alert('Geocode was not successful for the following reason: ' + status);
                        }
                      });
                    }

                    function addMarker(resultsMap, origin, dist,address, results, num){
                      var hospitalName = Provi[num];
                      var cost = AvCost[num];
                      var contentString = '<div id="content">'+
                      '<div id="siteNotice">'+
                      '</div>'+
                      '<h1 id="firstHeading" class="firstHeading">' +hospitalName+ '</h1>'+
                      '<div id="bodyContent">'+
                      '<p><b>' + hospitalName  + '</b>, is '+ dist +' away from '+ origin+'. The Hospitals address is '+ address +'  The average cost of this treatment at this hospital is $'+ cost +' </p>'+
                      '</div>'+
                      '</div>';

                      var icons = {
                        url: "https://cdn0.iconfinder.com/data/icons/healthcare-medicine/512/hospital_location-512.png", // url
                        scaledSize: new google.maps.Size(35, 35), // scaled size
                      };
                      var marker = new google.maps.Marker({
                            map: resultsMap,
                            animation: google.maps.Animation.DROP,
                            icon: icons,
                            position: results[0].geometry.location,
                            title: hospitalName 
                          });

                      var infowindow = new google.maps.InfoWindow({
                          content: contentString
                      });
                      marker.addListener('click', function() {
                        if (prevInfoBox){
                          prevInfoBox.close();
                          prevMarker.setAnimation(null);

                        }
                        prevInfoBox = infowindow;
                        prevMarker = marker
                        infowindow.open(map, marker);
                        marker.setAnimation(google.maps.Animation.BOUNCE);
                        var rows = table.getElementsByTagName("tr");
                        var currentRow;
                        var id;
                        var cell;
                        if (currSelected){
                            currSelected.style.backgroundColor = "rgb(237, 238, 239)";
                          }
                        for (i = 2; i < rows.length; i++) {
                          currentRow = table.rows[i];
                          cell = currentRow.getElementsByTagName("td")[1];
                          id = cell.innerHTML;  
                          if (id == marker.getTitle()){
                            currentRow.style.backgroundColor = "rgb(154, 159, 165)";
                            currSelected = currentRow;
                            rows[i].scrollIntoView({
                              behavior: 'smooth',
                              block: 'top'
                          });                          
                          }
                        }

                              });
                              infoBoxes.push(infowindow);
                              arMarker.push(marker);
                            }
                  </script>
                  <script async defer
                    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDYNH-VAjyeKCXX0w90AWoknL2qzTL_5Nk&callback=initMap">
                  </script>
            </div>
          </div>
        </div>

    </section>
    <!-- <script>alert("thisworks!");</script> -->
    
	<footer>
      <div class="copyrights">       
        <div class="container text-center py-4">
          <p class="mb-0 text-muted">&copy; 2019, UoD - AC31007 Agile Software Engineering Team 9 Project. Template designed by <a href="https://bootstraptemple.com">Bootstrap Temple</a>.</p>
        </div>
      </div>
    </footer>
    
    <!-- JavaScript files-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="js/front.js"></script>
    <script src="js/custom.js"></script>
    <!-- FontAwesome CSS - loading as last, so it doesn't block rendering-->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.1/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
  </body>
</html>