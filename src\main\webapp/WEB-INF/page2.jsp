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


<form action="" method ="get">
<b>citizenship:&nbsp&nbsp</b> <input type="text" name = "citizenship" value="USA">
	<br><br>
	<b>residence:&nbsp&nbsp</b> <input type="text" name = "residence" value="USA">
	<br><br>
	
	<b>greencard Yes</b>&nbsp&nbsp&nbsp<input type="radio" name="greencard" value="1">
	<b>&nbsp&nbsp&nbsp No</b>&nbsp&nbsp<input type="radio" name="greencard" value="2">
	<br><br>
	<b>US citizen Yes</b>&nbsp&nbsp&nbsp<input type="radio" name="US_citizen" value="1">
	<b>&nbsp&nbsp&nbsp No</b>&nbsp&nbsp<input type="radio" name="US_citizen" value="2">
	<br><br>
	<b>greencard holder Yes</b>&nbsp&nbsp&nbsp<input type="radio" name="greencard_holder" value="1">
	<b>&nbsp&nbsp&nbsp No</b>&nbsp&nbsp<input type="radio" name="greencard_holder" value="2">
	<br><br>
	
	<b>[E]If you had a visa on the last day of the tax year, enter your visa type. If you did not have a visa, enter your U.S. immigration status on the last day of the tax year.
	:&nbsp&nbsp</b> <input type="text" name = "e" value="USA">
	<br><br>
	
	<b>[F]
Have you ever changed your visa type (nonimmigrant status) or U.S. immigration status? Yes</b>&nbsp&nbsp&nbsp<input type="radio" name="f" value="1">
	<b>&nbsp&nbsp&nbsp No</b>&nbsp&nbsp<input type="radio" name="f" value="2">
	<br><br>
	
	<b>If you answered “Yes,” indicate the date and nature of the change.:&nbsp&nbsp</b> <input type="text" name = "f_yes" value="USA">
	<br><br>
	
<b>Date entered United States mm/dd/yy:&nbsp&nbsp</b> <input type="text" name = "enterUSA" value="USA">
	<br><br>
	<b>Date departed United States mm/dd/yy:&nbsp&nbsp</b> <input type="text" name = "departedUSA" value="USA">
	<br><br>
	<b>[H] Give number of days (including vacation, nonworkdays, and partial days) you were present in the United States during
	:&nbsp&nbsp</b> <input type="text" name = "h" value="30">
	<br><br>
	
	<b>[I] Did you file a U.S. income tax return for any prior year? Yes</b>&nbsp&nbsp&nbsp<input type="radio" name="I" value="1">
	<b>&nbsp&nbsp&nbsp No</b>&nbsp&nbsp<input type="radio" name="I" value="2">
	<br><br>
	
	<b>If “Yes,” give the latest year and form number you filed:&nbsp&nbsp</b> <input type="text" name = "I2" value="USA">
	<br><br>
	
<b>J
Income Exempt from Tax—If you are claiming exemption from income tax under a U.S. income tax treaty with a foreign country, complete (1) and (2) below. See Pub. 901 for more information on tax treaties.
1.
Enter the name of the country, the applicable tax treaty article, the number of months in prior years you claimed the treaty benefit, and the amount of exempt income in the columns below. Attach Form 8833 if required (see instructions).
<br>country:&nbsp&nbsp</b> <input type="text" name = "country" value="USA">
	<br><br>
	<b>article 20:&nbsp&nbsp</b> <input type="text" name = "article_20" value="USA">
	<br><br>
	<b>prior tax year month:&nbsp&nbsp</b> <input type="text" name = "prior_tax_year_month" value="USA">
	<br><br>
	<b>amount of exemption 5000:&nbsp&nbsp</b> <input type="text" name = "amount_of_exemption_5000" value="USA">
	<br><br>
	<b>treaty total:&nbsp&nbsp</b> <input type="text" name = "treaty_total" value="1000">
	<br><br>
	
	<b>Were you subject to tax in a foreign country on any of the income shown in 1(d) above? 
	Yes</b>&nbsp&nbsp&nbsp<input type="radio" name="J" value="1">
	<b>&nbsp&nbsp&nbsp No</b>&nbsp&nbsp<input type="radio" name="J" value="2">
	<br><br>
	
	
	<input type="submit" class="btn btn-primary" value="sumbit page 2">
  
</form>



</body>
</html>