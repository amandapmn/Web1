<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html lang="pt-BR">
<head>
<title>Consulta.me</title>
</head>

<body>
	<fmt:bundle basename="formsConsulta">
	<div align="center">

		<h1><fmt:message key="titulo"/></h1>
	</div>
	</fmt:bundle>
	<div align="center">
				<form action="/SistemaConsultas/cliente/marcarConsulta" method="get">
					<%@include file="campos_consulta.jsp"%>
				</form>
	</div>

	<c:if test="${!empty requestScope.mensagens}">
		<ul class="erro">
			<c:forEach items="${requestScope.mensagens}" var="mensagem">
				<fmt:bundle basename="formerrors">
				<li><fmt:message key="${mensagem}"/></li>
				</fmt:bundle>
			</c:forEach>
		</ul>
	</c:if>
</body>
</html>
