<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<meta charset="UTF-8">
<title>Liste des parlementaires - ${nomDuSite}</title>
</head>
<body>
	<%@include file="bandeauUtilisateur.jsp" %>
	<div class = "bg-secondary py-2">
		<div class="container">

			<h6>Etat d'avancement du projet</h6><br>
			Déclarations à analyser : ${nbAAnalyser} sur ${nbDeclaration} soit ${nbAAnalyser/nbDeclaration*100}% <br>
			Déclarations à réviser  : ${nbAReviser} sur ${nbDeclaration} soit ${nbAReviser/nbDeclaration*100}%<br>
			Déclarations validée    : ${nbValidee} sur ${nbDeclaration} soit ${nbValidee/nbDeclaration*100}% <br>
			<br>
			<h6>Etat d'actualisation des déclarations validées</h6><br>
			Nombre de déclarations à jour : ${nbDeclarationAJour} sur ${nbValidee} soit ${nbDeclarationAJour/nbValidee*100}%<br>
			Nombre de déclarations erronée : ${nbValidee-nbDeclarationAJour} sur ${nbValidee} soit ${(nbValidee-nbDeclarationAJour)/nbValidee*100}%<br>
			<br>
			<c:if test="${utilisateurConnecte.droit == 3}">
			<h6>Activité des utilisateurs</h6><br>
				<table class="table table-bordered table-striped table-primary">
				    <thead class="table table-dark">
				        <tr>
				            <th class="header" colspan="1">Utilisateur</th>
				            <th class="header" colspan="1">Déclarations analysées</th>
				        </tr>
				    </thead>
				    <tbody class="table" id="listeUtilisateurs">
					     <c:forEach items="${ utilisateurAnalyse }" var="utilisateurMap">
							<tr>
								<td><c:out value="${utilisateurMap.key.nom} ${utilisateurMap.key.prenom}" /></td>
								<td><c:out value="${utilisateurMap.value}" /></td>	
							</tr>
						 </c:forEach>
				    </tbody>
				</table>
				<br>
				<table class="table table-bordered table-striped table-primary">
				    <thead class="table table-dark">
				        <tr>
				            <th class="header" colspan="1">Utilisateur</th>
				            <th class="header" colspan="1">Déclarations révisée</th>
				        </tr>
				    </thead>
				    <tbody class="table" id="listeUtilisateurs">
					     <c:forEach items="${ utilisateurRevision }" var="utilisateurMap">
							<tr>
								<td><c:out value="${utilisateurMap.key.nom} ${utilisateurMap.key.prenom}" /></td>
								<td><c:out value="${utilisateurMap.value}" /></td>	
							</tr>
						 </c:forEach>
				    </tbody>
				</table>
			</c:if> 			
		</div>
	</div>
</body>
</html>
