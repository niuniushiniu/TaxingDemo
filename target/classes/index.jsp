<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="welcome.html" method ="get">

<input type="submit" value="Start">
  
</form>

<% 

String strPathFile = application.getRealPath(request.getRequestURI());
out.println("文件的绝对路径:" + strPathFile+ "<br/>");

%>
Funk you all!
</body>
</html>