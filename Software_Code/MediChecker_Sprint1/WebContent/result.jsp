<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>

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

    <script>
      var Defi = [];
      var Provi = [];
      var AvCost = [];
      var locations = [];
      var zipCode = [];
    </script>

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
                    </thead>
                    <tbody>
                    
                <tr>
				<td>Definition</td>
				<td>Provider Name</td>
				<td>Average Total Payments ($)</td>
			</tr>

	<%
	
	/*
	* This function needs to be optimized and refactored
	* User input will be integrated from the form on the front end
	*/
	
	String id = request.getParameter("gridRadios");		// User selects this with radio buttons on the front-end and is either "code" or "procedure"
	String input = request.getParameter("inputSearchCriteria");	// This input will either be a DRG code or procedure/condition input from the user form 
	
    String inputMinPrice = request.getParameter("inputMinPrice"); // Gets minimum price from the user input
    	if (inputMinPrice.isEmpty()) { // If no min price is selected the default value is set
    		inputMinPrice = "0";
        }
            	 	
    String inputMaxPrice = request.getParameter("inputMaxPrice"); // Gets maximum price from the user input
        if (inputMaxPrice.isEmpty()) { // If no max price is selected the default value is set
        	inputMaxPrice = "99999999";
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
	
	if (id.equals("procedure")) { // If the radio button selected is search by procedure/condition
		
		output = db.executeDBQuery("SELECT * FROM medichecker WHERE Definition LIKE '%" + input + "%' AND AverageTotalPayments > " + inputMinPrice + " AND AverageTotalPayments < " + inputMaxPrice + " " + sortSql + ";");
  }
  
	int i = 0;
  	for (Query obj : output)
  		{
        if(i < 10){
          String def = obj.getDefinition();
          String prov = obj.getProviderName();
          float avCost = obj.getAvgTotalPayments();
          String Addr = obj.getProviderAddress();
          String Zip = obj.getProviderZip();
   		 %>
    		<tr>
    			<td><%=obj.getDefinition()%></td>
    			<td><%=obj.getProviderName()%></td>
    			<td><%=obj.getAvgTotalPayments()%></td>
        </tr>
        <script>
          alert("<%= Addr%>");
          alert("<%= def%>");
          Defi.push("<%= def%>");
          Provi.push("<%= prov%>");
          AcCost.push("<%= avCost%>");
          locations.push("<%= Addr%>");
          zipCode.push("<%= Zip%>");
        </script>
      <%        
      }
      i++;

  		}
		%>

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
                    var distance;
                    var userLoc;
                    //var locations = ["Kansas", "Alabama", "Toronto", "New York", "New Jersey", "Kentucky"];
                    var prevInfoBox;
                    var prevMarker;
                    var arMarker;
                    function initMap() {
                      var map = new google.maps.Map(document.getElementById('map'), {
                        zoom: 4,
                        center: {lat: 35, lng: -96}
                      });
                      infoWindow = new google.maps.InfoWindow;
              
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
                      }
                      distanceMatriX('New York', map);
                    }
                    function distanceMatriX(origin, resultsMap) {
                      var service = new google.maps.DistanceMatrixService;
                      service.getDistanceMatrix({
                        origins: [origin],
                        destinations: locations,
                        travelMode: 'DRIVING',
                      }, function(response, status) {
                        if (status !== 'OK') {
                          alert('Error was: ' + status);
                        } else {
                          var origins = response.originAddresses;
                          var destinations = response.destinationAddresses;
                          for (var i = 0; i < origins.length; i++) {
                            var results = response.rows[i].elements;
                            for (var j = 0; j < results.length; j++) {
                              var element = results[j];
                              distance = element.distance.text;
                              var duration = element.duration.text;
                              var from = origins[i];
                              var to = destinations[j];
                              geocodeAddress(resultsMap, 1, distance, origin, locations[j])
                            }
                          }
                          //distance = element.distance.text;
                        }
                      });
                  }

                  function handleLocationError(browserHasGeolocation, infoWindow, pos) {
                      infoWindow.setPosition(pos);
                      infoWindow.setContent(browserHasGeolocation ?
                                            'Error: The Geolocation service failed.' :
                                            'Error: Your browser doesn\'t support geolocation.');
                      infoWindow.open(map);
                    }

                    function geocodeAddress(resultsMap, num, distance, origin, address) {
                      var geocoder = new google.maps.Geocoder();
                      geocoder.geocode({'address': address}, function(results, status) {
                        if (status === 'OK') {
                          //resultsMap.setCenter(results[0].geometry.location);
                          addMarker(resultsMap, origin, distance, address, results);
                        } else {
                          alert('Geocode was not successful for the following reason: ' + status);
                        }
                      });
                    }

                    function addMarker(resultsMap, origin, dist,address, results){
                      var hospitalName = 'UNIVERSITY OF ALABAMA HOSPITAL'
                      var cost = '$273,737.23'
                      var contentString = '<div id="content">'+
                      '<div id="siteNotice">'+
                      '</div>'+
                      '<h1 id="firstHeading" class="firstHeading">' +hospitalName+ '</h1>'+
                      '<div id="bodyContent">'+
                      '<p><b>' + hospitalName  + '</b>, is '+ dist +' away from '+ origin+' it is located at '+ address +'  Average Cost: '+ cost +' </p>'+
                      '</div>'+
                      '</div>';

                      /*var icons = {
                        url: "https://cdn0.iconfinder.com/data/icons/healthcare-medicine/512/hospital_location-512.png", // url
                        scaledSize: new google.maps.Size(35, 35), // scaled size
                        origin: new google.maps.Point(0,0), // origin
                        anchor: new google.maps.Point(0, 0) // anchor
                      };*/
                      var marker = new google.maps.Marker({
                            map: resultsMap,
                            animation: google.maps.Animation.DROP,
                            //icon: icons,
                            position: results[0].geometry.location,
                            title: address 
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
