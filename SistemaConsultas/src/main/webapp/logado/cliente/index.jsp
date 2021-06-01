<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <fmt:bundle basename="formsConsulta">
        <title><fmt:message key="clienteMenuTitulo"/></title>
    </head>
    <body>
        <h1><fmt:message key="clienteMenuTitulo"/></h1>
        <p><fmt:message key="saudacao"/> ${sessionScope.usuarioLogado.primeiroNome}</p>
        <ul>
            <li>
                <a href="/SistemaConsultas/cliente/minhasConsultas"><fmt:message key="titulotabela"/></a>
            </li>
            <li>
                <a href="/SistemaConsultas/cliente/profissionais"><fmt:message key="listaprofissionais"/></a>
            </li>
            <li>
                <a href="/SistemaConsultas/publico/logout"><fmt:message key="logout"/></a>
            </li>
      </fmt:bundle>
        </ul>
    </body>
</html>
