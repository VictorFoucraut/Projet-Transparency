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
		<div class="row mb-3">
		   <div class="col">
		      <input class="form-control" id="searchInput" type="text" placeholder="Recherche...">
		   </div>
		</div>
		
		<table class="table table-bordered table-striped table-primary">
		    <thead class="table table-dark">
		        <tr>
		            <th class="header" colspan="1">Utilisateur</th>
		            <th class="header" colspan="1">E-mail</th>
		            <th class="header" colspan="1">Droit</th>
		            <th class="header" colspan="1">Bannissement</th>
		        </tr>
		    </thead>
		    <tbody class="table" id="listeUtilisateurs">
			     <c:forEach items="${ listUtilisateur }" var="utilisateur">
					<tr>
						<td><c:out value="${utilisateur.nom} ${utilisateur.prenom}" /></td>
						<td><c:out value="${utilisateur.email}" /></td>
				   		<c:choose>
						    <c:when test="${utilisateur.droit == 1}">
						    	<td>Simple utilisateur</td>
						    </c:when>
						    <c:when test="${utilisateur.droit == 2}">
						    	<td>Utilisateur avancé</td>
						    </c:when>
						    <c:when test="${utilisateur.droit == 3}">
						    	<td>Admin</td>
						    </c:when>
						</c:choose>	
						<c:if test="${utilisateur.droit != 3}">
							<td><a href="<c:url value="Bannissement?id=${utilisateur.id}" />" style="color:#FF0000;">Bannir</a></td>		
						</c:if>	
						<c:if test="${utilisateur.droit == 3}">
							<td>-</td>		
						</c:if>		
					</tr>
				 </c:forEach>
		    </tbody>
		</table>
		<form method="post" action="AjoutUtilisateur">
			<fieldset>
				<h3>Ajouter un utilisateur</h3><br>
				<span class="text-danger">${erreurAttribute}</span>
				<br>
				<label for="nom"> Nom utilisateur <span class="requis">*</span></label>
				<input type="text" id="nom" name=nom value="" size="20" maxlength="60" />
				<br>
				<label for="prenom">Prenom utilisateur<span class="requis">*</span></label>
				<input type="text" id=prenom name=prenom value="" size="20" maxlength="60" />
				<br>
				<label for="email">Adresse mail<span class="requis">*</span></label>
				<input type="text" id=email name=email value="" size="20" maxlength="60" />
				<br>
				<label for="mdp">Mot de passe<span class="requis">*</span></label>
				<input type="text" id=mdp name=mdp value="" size="20" maxlength="60" />
				<br>
				<label for="droit">Droit<span class="requis">*</span></label>
				<SELECT name="droit"> <OPTION> Simple utilisateur <OPTION> Utilisateur avance </SELECT>
				<br>
				<input type="submit" class="btn btn-primary" value="OK" class="sansLabel" />
			</fieldset>
		</form>
		<br><br>
		<h3>Mise à jour base de donnée</h3><br>
			Pour mettre à jour la base de donnée, déposer le nouveau fichier nommé "declarations.xml" à la racine du projet (au même endroit que le logo transparency) et cliquez sur le bouton. La mise à jour peut prendre plusieurs minutes.
			<a class="btn btn-danger" href="<c:url value="UpdateDatabase" />"target="_blank">MàJ</a>
	</div>
	</div>
</body>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

<script>
   $(document).ready(function(){
      $("#searchInput").on("keyup", function() {
         var value = $(this).val().toLowerCase();
         $("#listeUtilisateurs .clickable-row").filter(function() {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
         });
      });
   });
</script>
<script>
$(function(){
    $('.table tr[data-href]').each(function(){
        $(this).css('cursor','pointer').hover(
            function(){ 
                $(this).addClass('active'); 
            },  
            function(){ 
                $(this).removeClass('active'); 
            }).click( function(){ 
                document.location = $(this).attr('data-href'); 
            }
        );
    });
});
</script>
</html>