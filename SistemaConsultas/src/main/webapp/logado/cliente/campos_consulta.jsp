<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<table border="1">
 	<c:if test="${profissional != null}">
   		<input type="hidden" name="id" value="${profissional.getId()}" />
   	</c:if>
   	<fmt:bundle basename="formsConsulta">
   	<tr>
   		<td><label for="data"><fmt:message key="dataescolhida"/></label></td>
   		<td><input type="date" id="data" name="data" size="55"
   			required value="" /></td>
   	</tr>
		<tr>
   		<td><label for="horario"><fmt:message key="marcarhorario"/></label></td>
   		<td>
				<select name="horario">
					<option value="09:00:00" selected>09:00</option>
					<option value="10:00:00">10:00</option>
					<option value="11:00:00">11:00</option>
					<option value="12:00:00">12:00</option>
					<option value="13:00:00">13:00</option>
					<option value="14:00:00">14:00</option>
					<option value="15:00:00">15:00</option>
					<option value="16:00:00">16:00</option>
					<option value="17:00:00">17:00</option>
					<option value="18:00:00">18:00</option>
				</select>
		</td>
   	</tr>
		<tr>
   		<td><label for="data"><fmt:message key="videoconferencia"/></label></td>
   		<td><input type="text" id="videoconferencia" name="videoconferencia" required value="" /></td>
   	</tr>
   	<tr>
   		<td colspan="2" align="center"><input type="submit" value="Salva" /></td>
   	</tr>
   	</fmt:bundle>
</table>
