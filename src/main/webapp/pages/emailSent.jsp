<%@ page import="fr.eni.java.projet.bo.Utilisateur" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Email envoyé</title>
</head>
<body>
<% Utilisateur user= (Utilisateur)request.getAttribute("user"); %>
	<h1>Bonjour <%= user.getPseudo() %> </h1>
	<h2>Un lien de récupération a été envoyé dans votre boîte mail</h2>
</body>
</html>