<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Lista de Profissionais</title>
</head>
<body>
	<%
		String contextPath = request.getContextPath().replace("/", "");
	%>

	<div align="center">
		<table border="1">
			<caption>Lista de Profissionais</caption>
			<tr>
				<th>Nome completo</th>
				<th>Especialidade</th>
			</tr>
			<c:forEach var="profissional" items="${requestScope.listaProfissionais}">
				<tr>
          <td>${profissional.getUsuario().primeiro_nome + " " + profissional.getUsuario().sobrenome }</td>
          <td>${profissional.especialidade}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>
