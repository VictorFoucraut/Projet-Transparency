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
		            <th class="header" colspan="1">Parlementaires</th>
		            <th class="header" colspan="1">Poste</th>
		            <th class="header" colspan="1">Statut</th>
		        </tr>
		    </thead>
		    <tbody class="table" id="listeParlementaires">
			     <c:forEach items="${ listDeclaration }" var="declaration">
					<tr class="table clickable-row" data-href="AnalyseParlementaire?uuid=${declaration.uuid}">
						<td><c:out value="${declaration.nom} ${declaration.prenom}" /></td>
						<td><c:out value="${declaration.mandat}" /></td>
				   		<c:choose>
						    <c:when test="${declaration.etat == 'à analyser'}">
						    	<td><c:out value="${declaration.etat}" /></td>
						    </c:when>
						    <c:when test="${declaration.etat == 'à réviser'}">
						    	<td class = "bg-warning"><c:out value="${declaration.etat}" /></td>
						    </c:when>
						    <c:otherwise>
						    	<td class = "bg-success"><c:out value="${declaration.etat}" /></td>
						    </c:otherwise>
						</c:choose>						
					</tr>
				 </c:forEach>
		    </tbody>
		</table>
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
         $("#listeParlementaires .clickable-row").filter(function() {
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
