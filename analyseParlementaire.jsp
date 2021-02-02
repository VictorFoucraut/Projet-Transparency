<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<meta charset="UTF-8">
<title>Analyse parlementaire - ${nomDuSite}</title>
</head>
<body>
	<%@include file="bandeauUtilisateur.jsp" %>
	<div class = "bg-secondary py-2">
	<div class="container">  
	<a class="btn btn-primary" href="<c:url value="http://www2.assemblee-nationale.fr/deputes/liste/alphabetique" />"target="_blank">Accéder aux données de l'Assemblé Nationale</a> 
	<a class="btn btn-primary" href="<c:url value="http://www.senat.fr/pubagas/liste_senateurs_collaborateurs.pdf" />"target="_blank">Accéder aux données du Sénat</a><br><br>
	<form method="post" action="AnalyseParlementaire">
		<span class="text-danger">${erreurAttribute}</span>
		<input type="hidden" name="uuid" value="${declaration.uuid}" />

	   <p>
		   	<h1 class = "text-center"><c:out value="${declaration.civilite} ${declaration.nom} ${declaration.prenom}" /></h1>
		   <br>
		   	<c:out value="${declaration.mandat}"/>
		   <br>
		   Date de dépot de la déclaration : 
		   <c:out value="${declaration.dateDepot}"/> 

	   
	   
		<%-- tableau activité collaborateurs --%>   
		<table class="table table-primary table-striped table-bordered">
		    <thead class="table-dark">
		        <tr>
			        <th colspan = "6" class = "text-center">Activités des collaborateurs</th>
		        </tr>		    
		        <tr>
		            <th >Nom</th>
		            <th >Employeur</th>
		            <th >Description activité</th>
		            <th >Commentaire du parlementaire</th>
		            <th >Etat</th>
		        </tr>

		    </thead>
		    <tbody>
			    <c:forEach items="${ listeCollaborateurs }" var="collab">
			    	<tr>
			    		<td ><c:out value="${collab.nom}"/></td>
			    		<td ><c:out value="${collab.employeur}"/></td>
			    		<td ><c:out value="${collab.descriptionActivite}"/></td>
			    		<td ><c:out value="${collab.commentaire}"/></td>
			    		<c:choose>
						    <c:when test="${declaration.etat == 'à analyser'}">
						    	<td ><SELECT name="selection${collab.id}"> <OPTION> - <OPTION> à jour <OPTION> erreur1 <OPTION> erreur2 </SELECT></td>
						    </c:when>
						    <c:otherwise>
						    	<td ><c:out value="${collab.etat}"/></td>
						    </c:otherwise>
						</c:choose>
			    	</tr>
			    </c:forEach>
		    </tbody>
		</table>	
	<br>
	   <div class="row">
	      <div class="col">
	         <label for="nbCollabErreur"> Nombre de collaborateurs dont les noms sont mentionnés dans les listes
			de l’AN/du Sénat mais n’apparaissent pas dans la liste ci-dessous (0 si pas de différence) : </label>
			<label for="commentaire">Commentaire :</label>
	      </div>
	      <div class="col">
	   		<c:choose>
			    <c:when test="${declaration.etat == 'à analyser'}">
			    	<input type="text" id="nbCollabErreur" name=nbCollabErreur value="" size="5" maxlength="60" />
			    </c:when>
			    <c:otherwise>
			    	<input type="text" id="nbCollabErreur" name=nbCollabErreur value="${ declaration.nbCollabErreur }" size="5" maxlength="60" readonly="readonly"/>
			    </c:otherwise>
			</c:choose>
			<br><br><br>
			<c:choose>
			    <c:when test="${declaration.etat == 'à analyser'}">
			    	<textarea id="commentaire" name="commentaire" rows="5" cols="33"></textarea>
			    </c:when>
			    <c:otherwise>
			    	<textarea id="commentaire" name="commentaire" rows="5" cols="33" readonly="readonly"><c:out value="${declaration.commentaire}"/></textarea>
			    </c:otherwise>
			</c:choose>
		      </div>
	   </div>	
		
		<br>
		<c:choose>
		    <c:when test="${declaration.etat == 'à analyser'}">
		    	<input type="submit" class="btn btn-primary" name="Enregistrer" value="Enregistrer" class="sansLabel" />
		    </c:when>
		    <c:when test="${declaration.etat == 'à réviser' && sessionScope.utilisateurConnecte.droit >= 2}">
		    	<input type="submit" class="btn btn-primary" name="Valider" value="Valider" class="sansLabel" />
		    	<input type="submit" class="btn btn-danger" name="Signaler" value="Signaler une erreur" class="sansLabel" />
		    </c:when>
		    <c:otherwise>
		    	
		    </c:otherwise>
		</c:choose>
		

	<br>	   
	</form>
	</div>
	</div>
</body>
</html>