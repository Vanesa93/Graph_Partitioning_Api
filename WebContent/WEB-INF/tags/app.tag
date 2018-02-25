<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<html>
<head>
	<meta http-equiv="Content-Style-Type" content="text/css">
	<link rel='stylesheet' href='webjars/bootstrap/3.2.0/css/bootstrap.min.css'>
	<link rel="stylesheet" href="resources/css/main.css" type="text/css">
	<link rel="stylesheet" href="resources/css/navigation.css" type="text/css">
<title>Welcome</title>
</head>
  <body>
  	<script type="text/javascript" src="webjars/jquery/2.1.1/jquery.min.js"></script>
	<script type="text/javascript" src="webjars/bootstrap/3.2.0/js/bootstrap.min.js"></script>
	<script src="resources/js/jquery-3.2.1.min.js"></script>
	<script src="resources/js/sigma.min.js"></script>
	<script src="resources/js/sigma.parsers.json.min.js"></script>
	<script src="resources/js/sigma.renderers.snapshot.js"></script>
	
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.17.0/jquery.validate.js"></script>
	
    <div id="pageheader">
    	<%@ include file="/WEB-INF/jsp/header.jsp" %>	
    </div>
    <div id="body">
      <jsp:doBody/>
    </div>
  </body>
</html>