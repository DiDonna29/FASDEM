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
<link href="<%=request.getContextPath()%>/css/cssfasdem.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/css.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/jquery.ui.all.css" rel="stylesheet" />
<link href="<%=request.getContextPath()%>/css/demos.css" rel="stylesheet" />
<link href="<%=request.getContextPath()%>/css/jquery-ui-1.8.6.custom.css" rel="stylesheet" />
<script src="<%=request.getContextPath()%>/js/jquery/jquery-1.4.3.js"></script>

<script src="<%=request.getContextPath()%>/js/jquery/external/jquery.bgiframe-2.1.2.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery/ui/jquery.ui.core.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery/ui/jquery.ui.widget.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery/ui/jquery.ui.mouse.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery/ui/jquery.ui.draggable.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery/ui/jquery.ui.position.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery/ui/jquery.ui.resizable.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery/ui/jquery.ui.dialog.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery/ui/jquery.ui.sortable.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery/ui/jquery.ui.accordion.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery/ui/jquery.ui.tabs.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery/ui/jquery.ui.dialog.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery/jquery.cookies.js"></script>
<script>
	$(function() {

		$( "#dialog:ui-dialog" ).dialog( "destroy" );
		
		$( "#dialog-modal" ).dialog({
			height: 140,
			modal: true
		});
	});
	</script>
</head>
<body style="margin-top: 10px;">
<html:form action="${form_action}">
	<logic:messagesPresent message="true">

		<div class="ui-widget">
		<div class="ui-state-highlight ui-corner-all cgp" style="margin-top: 5px; padding: 0em;"><html:messages id="msg" message="true" property="msjValidaciones" bundle="mensajes">
			<div class="nredp" style="margin-left: 5px;"><strong><img src="<%=request.getContextPath()%>/images/exclamation.png" width="16" border="0" height="16" />&nbsp;</strong> <bean:write filter="false" name="msg" /> <br />

			</div>
		</html:messages></div>
		</div>
		<html:messages id="msg" message="true" property="msjAvisos" bundle="mensajes">
			<div id="dialog-modal" title="Mensajes del Sistema">
			<p><bean:write filter="false" name="msg" /></p>

			</div>
		</html:messages>
	</logic:messagesPresent>

	<input type="hidden" name="idSini" id="idSini" value="<c:out value="${idSini}"/>" />
	<input type="hidden" name="idFact" id="idFact" value="<c:out value="${idFact}"/>" />
	<input type="hidden" name="anioSiniestro" id="anioSiniestro" value="<c:out value="${anioSiniestro}"/>" />
	<div class="s10"></div>
	<div class="textorange" style="padding-bottom: 5px">Factura</div>
	<div class="container" style="width: 500px; background-color: white;">

		<div style="width: 170px" class="etiqueta titulo cgp">Número Factura</div>
		<div style="width: 320px" class="parametro titulo "><c:out value="${factura.numeroFactura}"/> </div>
		<div style="width: 170px" class="etiqueta titulo cgp">Monto Factura</div>
		<div style="width: 320px" class="parametro titulo "><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" groupingUsed="true" value="${factura.montoFactura}"/> Bs.</div>

	</div>
	
		<div class="s10" style="clear: both;"></div>
	
	<div class="textorange" style="padding-bottom: 5px">Detalle de la factura</div>
	<div class="container" style="width: 500px; background-color: white;"><c:if test="${entorno.tipoGasto}">
		<div style="width: 170px" class="etiqueta titulo cgp"><bean:message key="general.tipoGasto" bundle="etiquetas" /></div>
		<div style="width: 320px" class="parametro titulo cgp"><html:select styleClass="cgp" property="tipoGasto">
			<html:option styleClass="seleccione" value="-1">[SELECCIONAR]</html:option>
			<html:options collection="listTipoGasto" property="id" labelProperty="descripcion" />
		</html:select></div>
	</c:if> <c:if test="${entorno.monto}">
		<div style="width: 170px" class="etiqueta titulo cgp">Monto (Bs.)</div>
		<div style="width: 320px" class="parametro titulo "><html:text styleClass="cgp" property="monto"></html:text></div>
	</c:if>







	<div style="clear: both;width:490px" align="right"><input title="aceptar" value="aceptar" type="submit" /> <input title="cerrar" type="button" value="cerrar" onclick="javaScript:window.close()" /></div>
	
	
</html:form>

<div>
<table class="tabla">
	<tr class="tituloCabecera">
		<td>Tipo de Gasto</td>
		<td>Monto</td>
		<td>Eliminar</</td>
	</tr>
	<c:forEach items="${detalleFactura}" var="df">
		<tr class="item" bgcolor="white">
			<td><c:out value="${df.tipoGasto.descripcion}" /></td>
			<td><c:out value="${df.monto}" /></td>
			<td><a class="nredp" href="<%=request.getContextPath()%>/security/aps/liquidacion/eliminarDetalleFactura.do?idDetalleFact=<c:out value="${df.id}" />&idFact=<c:out value="${idFact}" />&idSini=<c:out value="${idSini}" />&anioSiniestro=<c:out value="${anioSiniestro}" />">Eliminar</a></td>

		</tr>



	</c:forEach>
	<tr class="item" bgcolor="white">
		<td><strong>Total</strong></td>
		<td style="font-weight: bold;"><fmt:formatNumber value="${totalFactura}" maxFractionDigits="2" minFractionDigits="2" /></td>
		<td></td>
	</tr>
	<tr class="item" bgcolor="white">
		<td><strong>Total Iva</strong></td>
		<td style="font-weight: bold;"><fmt:formatNumber value="${totalIva}" maxFractionDigits="2" minFractionDigits="2" /></td>
		<td></td>
	</tr>
	
	<tr class="item" bgcolor="white">
		<td><strong>Total Factura</strong></td>
		<td style="font-weight: bold;"><fmt:formatNumber value="${totalFactura +totalIva}" maxFractionDigits="2" minFractionDigits="2" /></td>
		<td></td>
	</tr>
	
</table>

</div>
</body>
</html>
<script>
function cerrar(){
var tra = document.forms[0].tratamiento;
var obj =window.opener.document.globalActionForm.causaIngreso;
var obj2 =window.opener.document.globalActionForm.idCausaIngreso;
obj.value =document.getElementById('ruta').value;
obj.title =document.getElementById('ruta').value;
if(tra.value!='-1'){
	obj2.value =tra.value;	
}

close();
}
function cambioCombo(combo){
	
	var esp = document.forms[0].especialidad;
	var org = document.forms[0].organo;
	var pat = document.forms[0].patologias;
	var tra = document.forms[0].tratamiento;
	
	if (combo=='1'){
		org.selectedIndex = 0;
		pat.selectedIndex = 0;
		tra.selectedIndex = 0;
	}
	if (combo=='2'){
		pat.selectedIndex = 0;
		tra.selectedIndex = 0;
	}
	if (combo=='3'){
		tra.selectedIndex = 0;
	}
	javaScript:document.forms[0].submit();
} 

</script>
