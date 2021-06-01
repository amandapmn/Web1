<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
	<fmt:bundle basename="formsConsulta">
<title><fmt:message key="tituloMinhasConsultas"/></title>
</head>
<body>
	<%
		String contextPath = request.getContextPath().replace("/", "");
	%>
	<div align="center">
		<%@include file="/menu.jsp"%>
		<table border="1">
			<caption><fmt:message key="titulotabela"/></caption>
			<tr>
				<th><fmt:message key="nomeprofissional"/></th>
        <th><fmt:message key="especialidade"/></th>
				<th><fmt:message key="diahorario"/></th>
			</tr>
			<c:forEach var="consulta" items="${requestScope.listaConsultas}">
				<tr>
          <td>${consulta.getProfissional().getUsuario().getPrimeiroNome()} ${consulta.getProfissional().getUsuario().getSobrenome()}</td>
          <td>${consulta.getProfissional().getEspecialidade()}</td>
          <td>${consulta.getDataHorario()}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	</fmt:bundle>
</body>
</html>
