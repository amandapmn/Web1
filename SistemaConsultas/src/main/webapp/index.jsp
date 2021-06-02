<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<body>
  <!DOCTYPE html>
  <html>
      <head>
          <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
          <fmt:bundle basename="formsInicio">
          <title><fmt:message key="menusistema"/></title>
          </fmt:bundle>
      </head>
      <body>
      	<fmt:bundle basename="formsInicio">
        <h1>Menu</h1>
        <ul>
            <li>
                <a href="/SistemaConsultas/publico/listaProfissionais"><fmt:message key="listaprof"/></a>
            </li>
            <li>
                <a href="/SistemaConsultas/publico/login">Login</a>
            </li>
        </ul>
        </fmt:bundle>
      </body>
  </html>

</body>
