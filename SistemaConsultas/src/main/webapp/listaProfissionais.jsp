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
		<form action="/SistemaConsultas/publico/listaClientes" method="get">
			<input id="todas" type="radio" name="opcao" value="0" checked='checked'>Todas as especialidades<br>
			<input id="pesquisar" type="radio" name="opcao" value="1">Pesquisar <br>
			<div id="especialidade">
	       	<b>Escreva a especialidade</b> <br><input type="text" name="especialidade"><br><br>
			</div>
			<input type="submit" value="Filtrar">
		</form>
		<script>
		document.getElementById("especialidade").style.display = "none";

		document.getElementById("pesquisar").onclick = function () {
      document.getElementById("especialidade").style.display = "block";
    };

		document.getElementById("todas").onclick = function () {
			document.getElementById("especialidade").style.display = "none";
    };
		</script>

	<div align="center">
		<table border="1">
			<caption>Lista de Profissionais</caption>
			<tr>
				<th>ID</th>
				<th>Email</th>
				<th>Primeiro Nome</th>
				<th>Sobrenome</th>
				<th>Especialidade</th>
        <th>Qualificações</th>
				<c:if test='${usuarioLogado != null && usuarioLogado.getPapel() == "CLIENTE"}'>
				<th>Marcar Consulta</th>
				</c:if>
			</tr>
			<c:forEach var="profissional" items="${requestScope.listaProfissionais}">
				<tr>
				<td>${profissional.getId()}</td>
		        <td>${profissional.getUsuario().email}</td>
		        <td>${profissional.getUsuario().primeiroNome}</td>
		        <td>${profissional.getUsuario().sobrenome}</td>
		        <td>${profissional.especialidade}</td>
		        <td>${profissional.qualificacoes}</td>
						<c:if test='${usuarioLogado != null && usuarioLogado.getPapel() == "CLIENTE"}'>
						<td>
							 <a href="/SistemaConsultas/cliente/marcarConsulta?id=${profissional.getId()}"
							 onclick="return confirm('Deseja marcar uma consulta?');">Marcar Consulta</a>
					 	</td>
					  </c:if>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>
