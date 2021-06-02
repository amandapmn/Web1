<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
<fmt:bundle basename="formsadmin">
<title><fmt:message key="listaclientes"/></title>
</head>
</fmt:bundle>
<body>
	<fmt:bundle basename="formsadmin">
	<%
		String contextPath = request.getContextPath().replace("/", "");
	%>

	<div align="center">
		<%@include file="/menu.jsp"%>
		<h3><a href="/SistemaConsultas/admin/clientes_criacao"><fmt:message key="addcostumer"/></a></h3>
		<table border="1">

			<caption><fmt:message key="listaclientes"/></caption>
			<tr>
			<th>ID</th>
			<th>Email</th>
			<th><fmt:message key="firstname"/></th>
			<th><fmt:message key="lastname"/></th>
			<th><fmt:message key="telephone"/></th>
			<th><fmt:message key="gender"/></th>
			<th><fmt:message key="birthday"/></th>
			<th><fmt:message key="editar"/></th>
			<th><fmt:message key="excluir"/></th>
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
			<td><a href="/SistemaConsultas/admin/clientes_edicao?id=${cliente.getId()}"><fmt:message key="editar"/></td></a>
			<td><a href="/SistemaConsultas/admin/clientes_remocao?id=${cliente.getId()}"
	 		onclick="return confirm('Deseja confirmar a exclusão?');"><fmt:message key="excluir"/></a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	</fmt:bundle>
</body>
</html>
