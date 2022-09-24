<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Dados de usuário</title>
</head>
<body>

<% 
String nome = request.getParameter("nome");
String idade = request.getParameter("idade");

out.println("Nome: " + nome + "<br/>");
out.println("Idade: " + idade);
%>
</body>
</html>