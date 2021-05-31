<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table border="1">
	<caption>
   		<c:choose>
   			<c:when test="${usuario != null}">
                               Edição
                           </c:when>
   			<c:otherwise>
                               Cadastro
                           </c:otherwise>
   		</c:choose>
	</caption>
 	<c:if test="${usuario != null}">
   		<input type="hidden" name="id" value="${usuario.id}" />
   	</c:if>
   	<tr>
   		<td><label for="primeiroNome">Primeiro Nome</label></td>
   		<td><input type="text" id="primeiroNome" name="primeiroNome" size="45"
   			required value="${usuario.primeiroNome}" /></td>
   	</tr>
   	<tr>
   		<td><label for="sobrenome">Sobrenome</label></td>
   		<td><input type="text" id="sobrenome" name="sobrenome" size="45" required
   			value="${usuario.sobrenome}" /></td>
   	</tr>
   	<tr>
   		<td><label for="email">Email</label></td>
   		<td><input type="text" id="email" name="email" size="55"
   			required value="${usuario.email}" /></td>
   	</tr>
   	<tr>
   		<td><label for="senha">Senha</label></td>
   		<td><input type="text" id="senha" name="senha" size="45" required
   			value="${usuario.senha}" /></td>
   	</tr>
   	<tr>
   		<td><label for="cpf">CPF</label></td>
   		<td><input type="text" id="cpf" name="cpf"
   			size="55" required value="${usuario.cpf}" /></td>
   	</tr>
   	<tr>
   		<td><label for="papel">Papel</label></td>
   		<td><input type="text" id="papel" name="papel"
   			size="55" required value="${usuario.papel}" /></td>
   	</tr>
   	<tr>
   		<td colspan="2" align="center"><input type="submit" value="Salva" /></td>
   	</tr>
</table>
