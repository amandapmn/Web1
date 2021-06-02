<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <fmt:bundle basename="formsadmin">
        <title><fmt:message key="menusistema"/></title>
        </fmt:bundle>
    </head>
    <body>
    	<fmt:bundle basename="formsadmin">
        <h1><fmt:message key="adminpage"/></h1>
        <p><fmt:message key="ola"/> ${sessionScope.usuarioLogado.primeiroNome}</p>
        <ul>
            <li>
                <a href="/SistemaConsultas/admin/clientes"><fmt:message key="clientes"/></a>
            </li>
            <li>
                <a href="/SistemaConsultas/admin/profissionais"><fmt:message key="profissionais"/></a>
            </li>
            <li>
                <a href="/SistemaConsultas/publico/logout"><fmt:message key="logout"/></a>
            </li>

        </ul>
        </fmt:bundle>
    </body>
</html>
