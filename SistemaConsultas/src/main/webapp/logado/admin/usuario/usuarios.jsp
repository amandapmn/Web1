<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Lista de Usuários</title>
</head>
<body>
	<%
		String contextPath = request.getContextPath().replace("/", "");
	%>

	<div align="center">
		<table border="1">
			<caption>Lista de Usuários</caption>
			<tr>
				<th>ID</th>
				<th>Email</th>
				<th>Primeiro Nome</th>
				<th>Sobrenome</th>
				<th>Papel</th>
				<th>Excluir</th>
        <th>Editar</th>
			</tr>
			<c:forEach var="usuario" items="${requestScope.listaUsuarios}">
				<tr>
					<td>${usuario.id}</td>
          <td>${usuario.email}</td>
          <td>${usuario.primeiroNome}</td>
          <td>${usuario.sobrenome}</td>
          <td>${usuario.papel}</td>
			<td><a href="/<%= contextPath%>/usuario/edicao?id=${usuario.id}">
			</a> &nbsp;&nbsp;&nbsp;&nbsp; <a
				href="/<%= contextPath%>/usuario/remocao?id=${usuario.id}"
			onclick="return confirm('<fmt:message key="confirm.link" />');">
			</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>
