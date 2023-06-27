<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:directive.include file="includes/includefile.jspf" />
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">  

<title>Listado de Servicios</title>
</head>
<body>
	<div id="container">
		<div id="header"></div>
		<div id="menu">
			<jsp:directive.include file="WEB-INF/menu.jspf" />
		</div>
	<c:if test="${not empty listadoservicios}">
		<div id="tabla">
			<table class="table tablaconborde tablacebra">
				<caption>Listado de Servicios</caption>
				<tr>
					<th scope="col">CODIGO SERVICIOS</th>
					<th scope="col">DESCRIPCION </th>
					<th scope="col">COSTO</th>
					<th scope="col">CODIGO FACTURA</th>
					<th scope="col">CODIGO EMPRESARIO</th>
					
				</tr>
				<c:forEach items="${listadoservicios}" var="servicio">
						<tr>
						<td class="txtderecha">${servicio.id}</td>
						<td>${servicio.descripcion}</td>
						<td>${servicio.costo}</td>
						<td>${servicio.factura_id}</td>
						<td>${servicio.empresario_id}</td>
						
						
					</tr>
				</c:forEach>
			</table>
		</div>
	</c:if>		
   </div>
</body>
</html>