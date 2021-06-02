<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
<title>Consulta.me</title>
</head>

<body>
	<fmt:bundle basename="formsadmin">
	<div align="center">
		<h1><fmt:message key="gerenciarprof"/></h1>
		<h2>
			<a href="profissionais"><fmt:message key="listaprofissionais"/></a>
		</h2>
	</div>
	<div align="center">
		<c:choose>
			<c:when test="${profissional != null}">
				<form action="/SistemaConsultas/admin/profissionais_edicao" method="get">
					<%@include file="campos_profissional.jsp"%>
				</form>
			</c:when>
			<c:otherwise>
				<form action="/SistemaConsultas/admin/profissionais_criacao" method="get">
					<%@include file="campos_profissional.jsp"%>
				</form>
			</c:otherwise>
		</c:choose>
	</div>
	<c:if test="${!empty requestScope.mensagens}">
		<ul class="erro">
			<c:forEach items="${requestScope.mensagens}" var="mensagem">
			<fmt:bundle basename="formserrors">
				<li><fmt:message key="${mensagem}"/></li>
			</fmt:bundle>
			</c:forEach>
		</ul>
	</c:if>
	</fmt:bundle>
</body>
</html>
