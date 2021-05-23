<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Lista de Usuarios</title>
</head>
<body>
	<%
		String contextPath = request.getContextPath().replace("/", "");
	%>

	<div align="center">
		<table border="1">
			<caption>Lista de Usuarios</caption>
			<tr>
				<th>ID</th>
				<th>Email</th>
				<th>Primeiro Nome</th>
				<th>Sobrenome</th>
				<th>Papel</th>
			</tr>
			<c:forEach var="usuario" items="${requestScope.listaUsuarios}">
				<tr>
					<td>${usuario.id}</td>
          <td>${usuario.email}</td>
          <td>${usuario.primeiroNome}</td>
          <td>${usuario.sobrenome}</td>
          <td>${usuario.papel}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>
