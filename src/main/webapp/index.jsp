<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Página inicial</title>
</head>
<body>

<form action="ServletLogin" method="post">
	<table>
		<tr>
			<td><label>Login</label></td>
			<td><input name="login" type="text" required></td>
		</tr>	
		<tr>
			<td><label>Senha</label></td>
			<td><input name="senha" type="password" required></td>
		</tr>
		<tr>
			<td></td>	
			<td><input type="submit"></td>
		</tr>
	</table>	
</form>
<h4>${mensagemErroLogin}</h4>

</body>
</html>