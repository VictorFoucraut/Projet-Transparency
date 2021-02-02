<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav class="col navbar navbar-expand navbar-light">
   <a class="navbar-brand" href="Accueil">
   		<img src="logo_transparency.png" width="120" height="90" alt="Site logo">
   </a>
   <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarContent">
      <span class="navbar-toggler-icon"></span>
   </button>
   <div id="navbarContent" class="collapse navbar-collapse">
      <ul class="navbar-nav">
         <li class="nav-item">
            <a class="nav-link" href="ListeParlementaires">Module 1</a>
         </li>
      </ul>
      <ul class="navbar-nav">
         <li class="nav-item">
            <a class="nav-link" href="Accueil">Module 2</a>
         </li>
      </ul> 
      <ul class="navbar-nav">
         <li class="nav-item">
            <a class="nav-link" href="Dashboard">Dashboard</a>
         </li>
      </ul>  
	    <c:if test="${utilisateurConnecte.droit == 3}">
	    	<ul class="navbar-nav">
		         <li class="nav-item">
		            <a class="nav-link" href="EspaceAdmin">Espace Admin</a>
		         </li>
	     		</ul>  
	    </c:if>      
      <ul class="navbar-nav ml-auto">
         <li class="nav-item">
            <a class="nav-link" href="Deconnexion">Se déconnecter</a>
         </li>
      </ul>   
   </div>
</nav>