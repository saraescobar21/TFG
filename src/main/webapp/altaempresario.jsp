<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page
	language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> Alta de Empresario </title>
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
		<div id="formEmpresario" class="formulariogeneral">
			<form name="frmEmpresario" method="get"
				action="${pageContext.request.contextPath}/controller">
				<fieldset id="datosEmpresario">
					<legend><img src="resources/img/azarquiel.gif">&nbsp;Nuevo Empresario</legend>
					<div class="etiquetas">
						<label for="nombre">Nombre:</label>
						<br>
						<br>
						<br>
						<label for="telefono">Telefono:</label>
						<br>
						<br>
						<br>
						<label for="correo">Correo:</label>
					</div>
					<div class="campos">
						<input type="text" id="nombre" name="nombre"> 
						<br>
						<br>
						<br>
						<input type="text" id="telefono" name="telefono">
						<br>
						<br>
						<br> 
						<input type="text" id="correo" name="correo"> 
						
						 
						<input name="operacion" type="hidden" id="operacion"
							value="insertaempresario">
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