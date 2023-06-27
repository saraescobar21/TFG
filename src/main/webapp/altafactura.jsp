<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page
	language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> Alta de Factura </title>
<jsp:directive.include file="includes/includefile.jspf" />
</head>
<body>
	<div id="container">
		<div id="header"></div>
		<div id="menu">
			<jsp:directive.include file="WEB-INF/menu.jspf" />
		</div>
		<c:if test="${error != null}">
			<div id="diverror">
				<p>
					<strong><c:out value="Error" /></strong> <br>
					<c:out value="${error}" />
				</p>
			</div>
		</c:if>
		<c:if test="${confirmaroperacion != null}">
			<div id="divconfirmacion">
				<p>
					<strong><c:out value="Mensaje" /></strong> <br>
					<c:out value="${confirmaroperacion}" />
				</p>
			</div>
		</c:if>
		<div id="formFactura" class="formulariogeneral">
			<form name="frmFactura" method="get"
				action="${pageContext.request.contextPath}/controller">
				<fieldset id="datosFactura">
					<legend><img src="resources/img/azarquiel.gif">&nbsp;Nueva Factura</legend>
					<div class="etiquetas">
						<label for="cliente">Cliente:</label>
						<br>
						<br>
						<br>
						<label for="fecha">Fecha:</label>
						<br>
						<br>
						<br>
						<label for="importe">Importe:</label>
					</div>
					<div class="campos">
						<input type="text" id="cliente" name="cliente"> 
						<br>
						<br>
						<br>
						<input type="text" id="fecha" name="fecha">
						<br>
						<br>
						<br> 
						<input type="text" id="importe" name="importe"> 
						
						 
						<input name="operacion" type="hidden" id="operacion"
							value="insertafactura">
					</div>
					<div class="cb"></div>
					<div class="cb"></div>
					<div class="botones">	
							<input type="submit" name="Submit" value="Guardar">
					</div>
				</fieldset>
			</form>
		</div>
		<div id="separacion">
			<br>
		</div>
	</div>
</body>
</html>