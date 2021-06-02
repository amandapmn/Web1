<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<table border="1">
	<caption>
		<fmt:bundle basename="formsadmin">
   		<c:choose>
   			<c:when test="${cliente != null}">
                               <fmt:message key="edicao"/>
                           </c:when>
   			<c:otherwise>
                               <fmt:message key="cadastro"/>
                           </c:otherwise>
   		</c:choose>
   		</fmt:bundle>
	</caption>
 	<c:if test="${cliente != null}">
   		<input type="hidden" name="id" value="${cliente.getId()}" />
   	</c:if>
   	<fmt:bundle basename="formsadmin">
   	<tr>
   		<td><label for="primeiroNome"><fmt:message key="firstname"/></label></td>
   		<td><input type="text" id="primeiroNome" name="primeiroNome" size="45"
   			required value="${cliente.getUsuario().getPrimeiroNome()}" /></td>
   	</tr>
   	<tr>
   		<td><label for="sobrenome"><fmt:message key="lastname"/></label></td>
   		<td><input type="text" id="sobrenome" name="sobrenome" size="45" required
   			value="${cliente.getUsuario().getSobrenome()}" /></td>
   	</tr>
   	<tr>
   		<td><label for="email">Email</label></td>
   		<td><input type="text" id="email" name="email" size="55"
   			required value="${cliente.getUsuario().getEmail()}" /></td>
   	</tr>
		<tr>
   		<td><label for="senha"><fmt:message key="password"/></label></td>
   		<td><input type="password" id="senha" name="senha" size="55"
   			required value="${cliente.getUsuario().getSenha()}" /></td>
   	</tr>
		<tr>
   		<td><label for="cpf">CPF</label></td>
   		<td><input type="text" id="cpf" name="cpf" size="55"
   			required value="${cliente.getUsuario().getCPF()}" /></td>
   	</tr>
   	<tr>
   		<td><label for="telefone"><fmt:message key="telephone"/></label></td>
   		<td><input type="text" id="telefone" name="telefone" size="45" required
   			value="${cliente.getTelefone()}" /></td>
   	</tr>
   	<tr>
   		<td><label for="sexo"><fmt:message key="gender"/></label></td>
   		<td><input type="text" id="sexo" name="sexo"
   			size="55" required value="${cliente.getSexo()}" /></td>
   	</tr>
   	<tr>
   		<td><label for="datNasc"><fmt:message key="birthday"/></label></td>
   		<td><input type="text" id="dataNasc" name="dataNasc"
   			size="55" required value="${cliente.getDataNasc()}" /></td>
   	</tr>
   	<tr>
   		<td colspan="2" align="center"><input type="submit" value=<fmt:message key="salvar"/> /></td>
   	</tr>
   	</fmt:bundle>
</table>
