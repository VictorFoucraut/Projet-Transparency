<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Choisissez un module - ${nomDuSite}</title>
<link type="text/css" rel="stylesheet" href="Style1.css" />
</head>
<body>
	
	<%@include file="bandeauUtilisateur.jsp" %>
	<c:out value="Choix du module..." />
	<table>
	    <thead>
	        <tr>
	            <th colspan="1">Projet en cours</th>
	            <th colspan="1">Statut</th>
	            <th colspan="1">...</th>
	        </tr>
	    </thead>
	    <tbody>
	        <tr>
	            <td>Collaborateurs parlementaires</td>
	            <td>Ouvert aux contributions</td>
	            <td><a href="<c:url value="ListeParlementaires" />">Cliquez ici pour ouvrir</a></td>
	        </tr>
	       	<tr>
	            <td>Module 2</td>
	            <td>Non ouvert aux contributions</td>
	            <td>Cliquez pour ne pas ouvrir</td>
	        </tr>
	    </tbody>
	</table>
</body>
</html>