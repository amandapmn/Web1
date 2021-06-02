<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
          <fmt:bundle basename="formsInicio">
          <title><fmt:message key="userauth"/></title>
          </fmt:bundle>
    </head>
    <body>
        <%@include file="/menu.jsp"%>
        <fmt:bundle basename="formsInicio">
        <h1><fmt:message key="userauth"/></h1>
        <c:if test="${mensagens.existeErros}">
            <div id="erro">
                <ul>
                    <c:forEach var="erro" items="${mensagens.erros}">
                      <fmt:bundle basename="formerrors">
                      <li><fmt:message key="${erro}"/></li>
                      </fmt:bundle>
                        </c:forEach>
                </ul>
            </div>
        </c:if>
        <form method="post" action="/SistemaConsultas/publico/login">
            <table>
                <tr>
                    <th>Login: </th>
                    <td><input type="text" name="email"
                               value="${param.email}"/></td>
                </tr>
                <tr>
                    <th><fmt:message key="password"/> </th>
                    <td><input type="password" name="senha" /></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" name="bOK" value='<fmt:message key="entrar"/>'/>
                    </td>
                </tr>
            </table>
        </form>
    </body>
  </fmt:bundle>
</html>
