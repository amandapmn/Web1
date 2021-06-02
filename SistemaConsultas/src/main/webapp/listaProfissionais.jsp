<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
	<fmt:bundle basename="formsInicio">
	<title><fmt:message key="listaprof"/></title>
	</fmt:bundle>
<body>
	<fmt:bundle basename="formsInicio">
	<%
		String contextPath = request.getContextPath().replace("/", "");
	%>

	<div align="center">

		<%@include file="/menu.jsp"%>

		<form action="/SistemaConsultas/publico/listaClientes" method="get">
			<input id="todas" type="radio" name="opcao" value="0" checked='checked'><th><fmt:message key="todas_especialidades"/></th><br>
			<input id="pesquisar" type="radio" name="opcao" value="1"><th><fmt:message key="pesquisar"/></th><br>
			<div id="especialidade">
	       	<b><th><fmt:message key="digite_especialidade"/></th></b> <br><input type="text" name="especialidade"><br><br>
			</div>
			<input type="submit" value='<fmt:message key="filtrar"/>'>
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

		<table border="1">
			<caption><fmt:message key="listaprof"/></caption>
			<tr>
				<th><fmt:message key="id"/></th>
				<th><fmt:message key="email"/></th>
				<th><fmt:message key="primeiro_nome"/></th>
				<th><fmt:message key="sobrenonome"/></th>
				<th><fmt:message key="especialidade"/></th>
				<th><fmt:message key="qualificacoes"/></th>

				<c:if test='${usuarioLogado != null && usuarioLogado.getPapel() == "CLIENTE"}'>
					<th><fmt:message key="marcar_consulta"/></th>
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
							 <a href="/SistemaConsultas/cliente/marcarConsulta?id=${profissional.getId()}">
								 <th><fmt:message key="marcar_consulta"/></th></a>
					 	</td>
					  </c:if>
				</tr>
			</c:forEach>
		</table>
	</div>
	</fmt:bundle>
</body>
</html>
