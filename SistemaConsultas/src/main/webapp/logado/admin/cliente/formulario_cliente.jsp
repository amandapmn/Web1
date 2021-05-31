<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Consulta.me</title>
</head>

<body>
	<div align="center">
		<h1>Gerenciamento de clientes</h1>
		<h2>
			<a href="clientes">Lista de clientes</a>
		</h2>
	</div>
	<div align="center">
		<c:choose>
			<c:when test="${cliente != null}">
				<form action="/SistemaConsultas/admin/clientes_edicao" method="get">
					<%@include file="campos_cliente.jsp"%>
				</form>
			</c:when>
			<c:otherwise>
				<form action="/SistemaConsultas/admin/clientes_criacao" method="get">
					<%@include file="campos_cliente.jsp"%>
				</form>
			</c:otherwise>
		</c:choose>
	</div>
	<c:if test="${!empty requestScope.mensagens}">
		<ul class="erro">
			<c:forEach items="${requestScope.mensagens}" var="mensagem">
				<li>${mensagem}</li>
			</c:forEach>
		</ul>
	</c:if>
</body>
</html>
