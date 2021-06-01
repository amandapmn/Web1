<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Lista de Clientes</title>
</head>
<body>
	<%
		String contextPath = request.getContextPath().replace("/", "");
	%>
	<div align="center">
		<%@include file="/menu.jsp"%>
		<h3><a href="/SistemaConsultas/admin/clientes_criacao">Adicionar cliente</a></h3>
		<table border="1">
			<caption>Lista de Clientes</caption>
			<tr>
			<th>ID</th>
			<th>Email</th>
			<th>Primeiro Nome</th>
			<th>Sobrenome</th>
			<th>Telefone</th>
			<th>Sexo</th>
			<th>DataNasc</th>
			<th>Excluir</th>
			<th>Editar</th>
			</tr>
			<c:forEach var="cliente" items="${requestScope.listaClientes}">
				<tr>
			<td>${cliente.id}</td>
			<td>${cliente.getUsuario().email}</td>
			<td>${cliente.getUsuario().primeiroNome}</td>
			<td>${cliente.getUsuario().sobrenome}</td>
			<td>${cliente.telefone}</td>
			<td>${cliente.sexo}</td>
			<td>${cliente.dataNasc}</td>
			<td><a href="/SistemaConsultas/admin/clientes_edicao?id=${cliente.getId()}">Editar</td></a>
			<td><a href="/SistemaConsultas/admin/clientes_remocao?id=${cliente.getId()}"
	 		onclick="return confirm('Deseja confirmar a exclusão?');">Excluir</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>
