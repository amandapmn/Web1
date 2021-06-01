<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Minhas consultas</title>
</head>
<body>
	<%
		String contextPath = request.getContextPath().replace("/", "");
	%>

	<div align="center">
		<table border="1">
			<caption>Minhas consultas</caption>
			<tr>
				<th>Nome profissional</th>
        <th>Especialidade</th>
				<th>Dia/Horário</th>
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
</body>
</html>
