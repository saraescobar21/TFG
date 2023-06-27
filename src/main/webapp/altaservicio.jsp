<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page
	language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
    <title> Alta de Servicio </title>
    <jsp:directive.include file="includes/includefile.jspf" />
</head>
<body>
  <div id="container">
	<div id="header"></div>
	<div id="menu">
	  <jsp:directive.include file="WEB-INF/menu.jspf" />
	</div>
	<c:choose>
  	  <c:when test="${confirmaroperacion != null}">
        <div id="divconfirmacion">
		  <p>
		    <strong><c:out value="Mensaje" /></strong> <br>
			<c:out value="${confirmaroperacion}" />
		  </p>
		</div>
	  </c:when>
	  <c:otherwise>		
		<div id="formCita" class="formulariogeneral">
		  <form name="frmservicio" method="get" action="${pageContext.request.contextPath}/controller">
		    <fieldset>
			  <legend><img src="resources/img/azarquiel.gif">&nbsp;Alta de servicio </legend>
			  <c:if test="${not empty listadofacturas}">
			    <div>
				  Selecciona el cliente de la factura
				  <select name="factura_id">
				    <c:forEach items="${listadofacturas}" var="factura">
					  <option value="${factura.id}">${factura.cliente}	</option>
			 	    </c:forEach>
	 			  </select>
	 			</div>
			  </c:if>
			  <c:if test="${not empty listadoempresarios}">
				<div>
				  Selecciona el empresario
				  <select name="empresario_id">
					<c:forEach items="${listadoempresarios}" var="empresario">
					  <option value="${empresario.id}">${empresario.nombre}	</option>
					</c:forEach>
	 			  </select>
	 			</div>
			  </c:if>
			  
	 			<div class="etiquetas">
						<label for="descripcion">Descripcion:</label>
						<br>
						<br>
						<br>
						<label for="costo">Costo:</label>
						<br>
						<br>
						<br>
						
						
	
					</div>
				<div class="campos">
						<input type="text" id="descripcion" name="descripcion"> 
						<br>
						<br>
						<br>
						<input type="text" id="costo" name="costo">
						<br>
						<br>
						<br> 
					
                

						<input name="operacion" type="hidden" id="operacion"
							value="insertaservicio">
					</div>
	 			<div class="botones">	
				  <input type="submit" name="Submit" value="Enviar">
				</div>
<%-- 			  </c:if> --%>
			  <input name="operacion" type="hidden" id="operacion" value="insertaservicio">
			  <div class="cb"></div>
			  <div class="cb"></div>
			</fieldset>
		  </form>
		</div>
		<div id="separacion">
		  <br>
		</div>
	  </c:otherwise>
	</c:choose>
  </div>
</body>
</html>