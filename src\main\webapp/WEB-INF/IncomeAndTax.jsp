<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
	  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
	  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Welcome to Taxing For You</title>
	<style type="text/css">
		body {
			background-image: url('http://crunchify.com/bg.png');
		}
		table, th, td {
		    border: 1px solid black;
		    border-collapse: collapse;
		}
		th {
		    text-align: mid;
		}
		#wrapper {
		    width: 50%;
		    margin: 0 auto;
		}
	</style>
<title>Insert title here</title>
</head>
<body>



<h1> ${msg} </h1>
<h2> ${pathofjava} </h2>


<form action="sumbitTax" method ="get">
---------->&nbsp<b>wage:&nbsp&nbsp</b> <input type="text" name = "wage" value="50000">
	<br><br>
	---------->&nbsp<b>taxable_refunds:&nbsp&nbsp</b> <input type="text" name = "taxable_refunds" value="15000">
	<br><br>
	------->&nbsp<b>scholarship :&nbsp&nbsp</b> <input type="text" name = "scholarship" value="20000">
	<br><br>
	
	----->&nbsp<b>Home Address:&nbsp&nbsp</b> <input type="text" name = "home_addr" value="123 Broad Way, Apt 456">
	<br><br>
	<b>City/State PostCode:&nbsp&nbsp</b> <input type="text" name = "city_state_zip" value="Jersey City,NJ 07307">
	<br><br>
	-----><b>Foreign Country:&nbsp&nbsp</b> <input type="text" name = "country" value="China">
	<br><br>
	----><b>Foreign Province:&nbsp&nbsp</b> <input type="text" name = "province" value="Hubei">
	<br><br>
	----><b>Foreign ZipCode:&nbsp&nbsp</b> <input type="text" name = "zipcode" value="430074">
	<br><br>
<input type="submit" value="Start">
  
</form>

</body>
</html>