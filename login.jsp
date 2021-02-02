<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<meta charset="UTF-8">
<title>Connexion - ${nomDuSite}</title>
</head>
<body>
	<%@include file="bandeauUtilisateur.jsp" %>
	<div class = "bg-secondary py-2">
	<div class="container">
	<form method="post" action="Login">
		<fieldset>
			<legend>Informations de connection.</legend>
			<span class="requis">${erreurAttribute}</span>
			<br>
			<label for="email"> Adresse email <span class="requis">*</span></label>
			<input type="text" id="email" name=email value="" size="20" maxlength="60" />
			<br>
			<label for="password">Mot de passe <span class="requis">*</span></label>
			<input type="text" id="password" name=password value="" size="20" maxlength="60" />
			<br>
			<input type="submit" class="btn btn-primary" value="OK" class="sansLabel" />
		</fieldset>
	</form>
	</div>
	</div>
</body>
</html>