<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic-el.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ page import="java.util.Date"%>
<%@page contentType="text/html; charset=ISO-8859-1"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<link href="<%=request.getContextPath()%>/css/cssfasdem.css"
	rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/css.css" rel="stylesheet"
	type="text/css" />
<link href="<%=request.getContextPath()%>/css/jquery.ui.all.css"
	rel="stylesheet" />
<link href="<%=request.getContextPath()%>/css/demos.css"
	rel="stylesheet" />
<link
	href="<%=request.getContextPath()%>/css/jquery-ui-1.8.6.custom.css"
	rel="stylesheet" />
<script src="<%=request.getContextPath()%>/js/jquery/jquery-1.4.3.js"></script>
<script
	src="<%=request.getContextPath()%>/js/jquery/ui/jquery.ui.core.js"></script>
<script
	src="<%=request.getContextPath()%>/js/jquery/ui/jquery.ui.widget.js"></script>
<script
	src="<%=request.getContextPath()%>/js/jquery/ui/jquery.ui.mouse.js"></script>
<script
	src="<%=request.getContextPath()%>/js/jquery/ui/jquery.ui.sortable.js"></script>
<script
	src="<%=request.getContextPath()%>/js/jquery/ui/jquery.ui.accordion.js"></script>
<script
	src="<%=request.getContextPath()%>/js/jquery/ui/jquery.ui.datepicker.js"></script>


<script src="<%=request.getContextPath()%>/js/popcalendar.js"></script>

<style>
.padeo {
	width: 90px;
	padding-right: 5px;
	padding-left: 5px;
}

.parampadeo {
	width: 200px;
}
</style>
</head>

<body style="margin-top: 10px;">
<html:form action="${form_action}">
	<logic:messagesPresent message="true">

		<html:messages id="msg" message="true" bundle="mensajes">
			<div class="nredp" style="margin-left: 5px;">- <bean:write
				name="msg" /><br />
			</div>
		</html:messages>
		<div class="delineadoBotton"></div>
	</logic:messagesPresent>
	<div class="container" style="width: 450px; background-color: white;">

	<div class="etiqueta padeo"><bean:message key="general.rif"
		bundle="etiquetas" /></div>
	<div class="parametro parampadeo"><html:hidden
		property="tipoProveedor" /> <html:hidden
		property="listTipoTramite" /> <html:text style="width: 200px;"
		styleClass="cgp" property="nombres"></html:text>
	</div>
	
	<div align="right"><input  class="boton" title="aceptar" value="aceptar"
		type="submit" /></div>

	<div class="s10"></div>
	<div class="textorange" style="padding-bottom: 5px">Listado de
	Proveedores</div>
	<div>
	<table class="tabla" width="100%" cellpadding="2" cellspacing="1"
		border="0">

		<tr>
			<td width="80%">Descripción</td>
			<td width="20%">R.I.F</td>
		</tr>
		<c:forEach items="${listProveedor}" var="p">
			<tr bgcolor="white" class="item" onclick="javaScript:cerrar('<c:out value="${p.id}" />', '<c:out value="${p.descripcion}" />');">
				<td><c:out value="${p.descripcion}" /></td>
				<td><c:out value="${p.identificador}" /></td>
			</tr>
		</c:forEach>
	</table>
	</div>
</html:form>
</body>
</html>

<script>
	function cerrar(argId, argDescripcion){
		var id =window.opener.document.globalActionForm.idProveedor;
		var des =window.opener.document.globalActionForm.proveedor;
		id.value =argId;
		des.value =argDescripcion;
		close();
	}
</script>
