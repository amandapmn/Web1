<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:choose>
<c:when test='${usuarioLogado != null && usuarioLogado.getPapel() == "CLIENTE"}'>
  <h3><a href="/SistemaConsultas/cliente/index">Voltar para home</a></h3>
</c:when>
<c:when test='${usuarioLogado != null && usuarioLogado.getPapel() == "PROFISSIONAL"}'>
  <h3><a href="/SistemaConsultas/profissional/index">Voltar para home</a></h3>
</c:when>
<c:when test='${usuarioLogado != null && usuarioLogado.getPapel() == "ADMIN"}'>
  <h3><a href="/SistemaConsultas/admin/index">Voltar para home</a></h3>
</c:when>
<c:otherwise>
  <h3><a href="/SistemaConsultas/">Voltar para home</a></h3>
</c:otherwise>
</c:choose>
