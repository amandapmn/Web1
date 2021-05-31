<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table border="1">
	<caption>
   		<c:choose>
   			<c:when test="${profissional != null}">
                               Edição
                           </c:when>
   			<c:otherwise>
                               Cadastro
                           </c:otherwise>
   		</c:choose>
	</caption>
 	<c:if test="${profissional != null}">
   		<input type="hidden" name="id" value="${profissional.getId()}" />
   	</c:if>
   	<tr>
   		<td><label for="primeiroNome">Primeiro Nome</label></td>
   		<td><input type="text" id="primeiroNome" name="primeiroNome" size="45"
   			required value="${profissional.getUsuario().getPrimeiroNome()}" /></td>
   	</tr>
   	<tr>
   		<td><label for="sobrenome">Sobrenome</label></td>
   		<td><input type="text" id="sobrenome" name="sobrenome" size="45" required
   			value="${profissional.getUsuario().getSobrenome()}" /></td>
   	</tr>
   	<tr>
   		<td><label for="email">Email</label></td>
   		<td><input type="text" id="email" name="email" size="55"
   			required value="${profissional.getUsuario().getEmail()}" /></td>
   	</tr>
		<tr>
   		<td><label for="senha">Senha</label></td>
   		<td><input type="password" id="senha" name="senha" size="55"
   			required value="${profissional.getUsuario().getSenha()}" /></td>
   	</tr>
		<tr>
   		<td><label for="cpf">CPF</label></td>
   		<td><input type="text" id="cpf" name="cpf" size="55"
   			required value="${profissional.getUsuario().getCPF()}" /></td>
   	</tr>
   	<tr>
   		<td><label for="especialidade">Especialidade</label></td>
   		<td><input type="text" id="especialidade" name="especialidade" size="45" required
   			value="${profissional.getEspecialidade()}" /></td>
   	</tr>
   	<tr>
   		<td><label for="qualificacoes">Qualificações</label></td>
   		<td><input type="text" id="qualificacoes" name="qualificacoes"
   			size="55" required value="${profissional.getQualificacoes()}" /></td>
   	</tr>
   	<tr>
   		<td colspan="2" align="center"><input type="submit" value="Salva" /></td>
   	</tr>
</table>
