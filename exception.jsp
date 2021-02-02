<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<meta charset="UTF-8">
<title>Oups... - ${nomDuSite}</title>
</head>
<body>
	<%@include file="bandeauUtilisateur.jsp" %>
	<div class = "bg-secondary py-2">
	<div class="container">
	<c:out value="Oups, ce n'était pas prévu ..." />
	<c:out value="${e}" />
	<br>

	</div>
	</div>
</body>
</html>