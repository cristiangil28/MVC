<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src='<c:url value="/res/jQuery.js"></c:url>'></script>

<script type="text/javascript">

	jQuery(document).ready(function(){
		jQuery(".comfirm").on("click", function(){
			return confirm("si eliminas este elemento no se podrá recuperar. ¿continuar?");
		});
	});


</script>
</head>
<body>
	<h1>Admin.jsp</h1>
	<sf:form action="${pageContext.request.contextPath}/admin/save" method="post" commandName="admin">
		<table>
		
		<!-- la siguiente línea indica que si el valor del id no es igual a 0 obtenga los valores de 
		los campos ocultos -->
		<c:if test="${admin.id ne 0 }">
			<sf:input path="id" type="hidden"/>
			<sf:input path="fechaCreacion" type="hidden"/>
		
		</c:if>
		
			<tr>
				<td>Nombre</td>
				<td><sf:input path="nombre" type="text"/></td>
				
			</tr>
			<tr>
				<td>Cargo:</td>
				<td><sf:input path="cargo" type="text"/></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Registrar"></td>
			</tr>
		
		</table>
		
	</sf:form>
	<c:out value="${resultado}"></c:out><br/><br/>
	
	<c:forEach items="${admins }" var="admin">
		<c:out value="${admin }"></c:out>
		<a href='<c:url value="/admin/${admin.id}/update"></c:url>'>Actualizar</a>
		<a class="confirm" href='<c:url value="/admin/${admin.id}/delete"></c:url>'>Eliminar</a>
		<br/>
	</c:forEach>
</body>
</html>