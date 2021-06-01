<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu do Sistema</title>
    </head>
    <body>
        <h1>Página do Administrador</h1>
        <p>Olá ${sessionScope.usuarioLogado.primeiroNome}</p>
        <ul>
            <li>
                <a href="/SistemaConsultas/admin/clientes">Clientes</a>
            </li>
            <li>
                <a href="/SistemaConsultas/admin/profissionais">Profissionais</a>
            </li>
            <li>
                <a href="/SistemaConsultas/publico/logout">Sair</a>
            </li>

        </ul>
    </body>
</html>
