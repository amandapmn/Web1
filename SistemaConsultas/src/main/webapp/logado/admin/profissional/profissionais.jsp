<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
<fmt:bundle basename="formsadmin">
<title><fmt:message key="listaprofissionais"/></title>
</fmt:bundle>
</head>
<body>
	<fmt:bundle basename="formsadmin">
	<%
		String contextPath = request.getContextPath().replace("/", "");
	%>

	<div align="center">
		<%@include file="/menu.jsp"%>
		<h3><a href="/SistemaConsultas/admin/profissionais_criacao"><fmt:message key="addprof"/></a></h3>
		<table border="1">
			<caption><fmt:message key="listaprofissionais"/></caption>
			<tr>
				<th>ID</th>
				<th>Email</th>
				<th><fmt:message key="firstname"/></th>
				<th><fmt:message key="lastname"/></th>
				<th><fmt:message key="especialidade"/></th>
        		<th><fmt:message key="qualificacoes"/></th>
        		<th><fmt:message key="editar"/></th>
        		<th><fmt:message key="excluir"/></th>
			</tr>
			<c:forEach var="profissional" items="${requestScope.listaProfissionais}">
				<tr>
				<td>${profissional.getId()}</td>
		        <td>${profissional.getUsuario().email}</td>
		         <td>${profissional.getUsuario().primeiroNome}</td>
		         <td>${profissional.getUsuario().sobrenome}</td>
		         <td>${profissional.especialidade}</td>
		         <td>${profissional.qualificacoes}</td>
		         <td><a href="/SistemaConsultas/admin/profissionais_edicao?id=${profissional.getId()}"><fmt:message key="editar"/></td></a>
						 <td>
					<a href="/SistemaConsultas/admin/profissionais_remocao?id=${profissional.getId()}"
					onclick="return confirm('Deseja confirmar a exclusão?');"><fmt:message key="excluir"/></a></td>
				</tr>
			<h2>
				<a href="/<%=contextPath%>"></a>
				&nbsp;&nbsp;&nbsp; <a href="/<%=contextPath%>/profissional/cadastro">
				</a>
			</h2>
			</c:forEach>

		</table>
	</div>
	</fmt:bundle>
</body>
</html>
