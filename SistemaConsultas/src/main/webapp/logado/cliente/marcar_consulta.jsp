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
		<h1>Marcar consulta</h1>
	</div>
	<div align="center">
		<c:choose>
			<c:when test="${consulta != null}">
				<form action="/SistemaConsultas/admin/horarios_consultas" method="get">
					<%@include file="campos_consulta.jsp"%>
				</form>
			</c:when>
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
