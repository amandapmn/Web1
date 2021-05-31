<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table border="1">
 	<c:if test="${consulta != null}">
   		<input type="hidden" name="id" value="${consulta.getId()}" />
   	</c:if>
   	<tr>
   		<td><label for="primeiroNome">Nome do Cliente</label></td>
   		<td><input type="text" id="primeiroNome" name="primeiroNome" size="45"
   			required value="${cliente.getUsuario().getPrimeiroNome()}" /></td>
   	</tr>
   	<tr>
   		<td><label for="sobrenome">Sobrenome do Cliente</label></td>
   		<td><input type="text" id="sobrenome" name="sobrenome" size="45" required
   			value="${cliente.getUsuario().getSobrenome()}" /></td>
   	</tr>
   	<tr>
   		<td><label for="primeiroNome_prof">Nome do Profissional</label></td>
   		<td><input type="text" id="primeiroNome_prof" name="primeiroNome_prof" size="55"
   			required value="${profissional.getUsuario().getNome()}" /></td>
   	</tr>
		<tr>
   		<td><label for="sobrenome_prof">Sobrenome do Profissional</label></td>
   		<td><input type="text" id="sobrenome_prof" name="sobrenome_prof" size="55"
   			required value="${profissional.getUsuario().getSobrenome()}" /></td>
   	</tr>
   	<tr>
   		<td><label for="data">Data escolhida</label></td>
   		<td><input type="date" id="data" name="data" size="55"
   			required value="${consulta.getData()}" /></td>
   	</tr>
		<tr>
   		<td><label for="horario">Horário escolhido</label></td>
   		<td><input type="radio" id="horario" name="horario" size="55"
   			required value="${consulta.getHorario()}" /></td>
   	</tr>
   	<tr>
   		<td colspan="2" align="center"><input type="submit" value="Salva" /></td>
   	</tr>
</table>
