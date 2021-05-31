<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table border="1">
	<caption>
   		<c:choose>
   			<c:when test="${cliente != null}">
                               Edição
                           </c:when>
   			<c:otherwise>
                               Cadastro
                           </c:otherwise>
   		</c:choose>
	</caption>
 	<c:if test="${cliente != null}">
   		<input type="hidden" name="id" value="${cliente.id}" />
   	</c:if>
   	<tr>
   		<td><label for="primeiroNome">Primeiro Nome</label></td>
   		<td><input type="text" id="primeiroNome" name="primeiroNome" size="45"
   			required value="${cliente.getUsuario().primeiroNome}" /></td>
   	</tr>
   	<tr>
   		<td><label for="sobrenome">Sobrenome</label></td>
   		<td><input type="text" id="sobrenome" name="sobrenome" size="45" required
   			value="${cliente.getUsuario().sobrenome}" /></td>
   	</tr>
   	<tr>
   		<td><label for="email">Email</label></td>
   		<td><input type="text" id="email" name="email" size="55"
   			required value="${cliente.getUsuario().email}" /></td>
   	</tr>
   	<tr>
   		<td><label for="telefone">Telefone</label></td>
   		<td><input type="text" id="especialidade" name="telefone" size="45" required
   			value="${cliente.telefone}" /></td>
   	</tr>
   	<tr>
   		<td><label for="sexo">Sexo</label></td>
   		<td><input type="text" id="sexo" name="sexo"
   			size="55" required value="${cliente.sexo}" /></td>
   	</tr>
   	<tr>
   		<td><label for="data_nasc">Data de Nascimento</label></td>
   		<td><input type="text" id="data_nasc" name="data_nasc"
   			size="55" required value="${cliente.data_nasc}" /></td>
   	</tr>
   	<tr>
   		<td colspan="2" align="center"><input type="submit" value="Salva" /></td>
   	</tr>
</table>
