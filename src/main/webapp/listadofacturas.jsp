<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:directive.include file="includes/includefile.jspf" />
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">  

<title>Listado de facturas</title>
</head>
<body>
	<div id="container">
		<div id="header"></div>
		<div id="menu">
			<jsp:directive.include file="WEB-INF/menu.jspf" />
		</div>
	<c:if test="${not empty listadofacturas}">
		<div id="tabla">
			<table class="table tablaconborde tablacebra">
				<caption>Listado de Facturas</caption>
				<tr>
					<th scope="col">CODIGO FACTURA</th>
					<th scope="col">CLIENTE</th>
					<th scope="col">FECHA</th>
					<th scope="col">IMPORTE</th>
					
				</tr>
				<c:forEach items="${listadofacturas}" var="factura">
						<tr>
						<td class="txtderecha">${factura.id}</td>
						<td>${factura.cliente}</td>
						<td>${factura.fecha}</td>
						<td>${factura.importe}</td>
						
					</tr>
				</c:forEach>
			</table>
		</div>
	</c:if>		
   </div>
</body>
</html>