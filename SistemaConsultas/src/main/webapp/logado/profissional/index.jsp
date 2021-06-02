<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <fmt:bundle basename="profissional">
        <title><fmt:message key="menusistema"/></title>
        </fmt:bundle>
    </head>
    <body>
      <fmt:bundle basename="profissional">
        <h1><fmt:message key="profissional_pag"/></h1>
        <p><fmt:message key="ola"/> ${sessionScope.usuarioLogado.primeiroNome}</p>
        <ul>
            <li>
                <a href="/SistemaConsultas/profissional/listaConsultas"><fmt:message key="minhas_consultas"/></a>
            </li>
            <li>
                <a href="/SistemaConsultas/publico/logout"><fmt:message key="sair"/></a>
            </li>

        </ul>
    </fmt:bundle>
    </body>
</html>
