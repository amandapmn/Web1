<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<fmt:bundle basename="profissional">
<title><fmt:message key="minhas_consultas"/></title>
</fmt:bundle>
</head>
<body>
	<%
		String contextPath = request.getContextPath().replace("/", "");
	%>
	<fmt:bundle basename="profissional">
	<div align="center">
		<table border="1">
			<caption><fmt:message key="minhas_consultas"/></caption>
			<tr>
				<th><fmt:message key="nome"/></th>
        <th><fmt:message key="cpf"/></th>
        <th><fmt:message key="email"/></th>
				<th><fmt:message key="dia_horario"/></th>
			</tr>
			<c:forEach var="consulta" items="${requestScope.listaConsultas}">
				<tr>
          <td>${consulta.getCliente().getUsuario().getPrimeiroNome()} ${consulta.getCliente().getUsuario().getSobrenome()}</td>
          <td>${consulta.getUsuario().getCPF()}</td>
          <td>${consulta.getUsuario().getEmail()}</td>
          <td>${consulta.getDataHorario()}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	</fmt:bundle>
</body>
</html>
