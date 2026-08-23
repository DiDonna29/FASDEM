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
	width: 400px;
}
</style>
</head>

<body style="margin-top: 10px;">
<html:form action="${form_action}">
<div class="titblue" style="background-color: #EEEEEE; padding-left: 5px; padding-bottom: 2px">Carga de Facturas</div>
	<div class="container" style="width: 500px; background-color: white;">
<input type="hidden"  name="idS" value="<c:out value="${idS}"/>"/>
<input type="hidden"  name="anioS" value="<c:out value="${anioSin}"/>"/>
<input type="hidden"  name="montop" value="<c:out value="${montop}"/>"/>
	<div class="textorange ">Número de Siniestro <c:out value="${nroSin}"/></div>
	<div class="textorange ">Monto Presupuestado <c:out value="${montop}"/></div>
	
<c:if test="${entorno.fechaFactura}">
	<div class="etiqueta titulo cgp"><bean:message
		key="general.fechafactura" bundle="etiquetas" /></div>
	<div class="parametro titulo cgp"><html:text styleClass="cgp"
		property="fechaFactura" 
		onfocus="javascript:showCalendarSelected2(document.forms[0].fechaFactura, document.forms[0].fechaFactura, 'dd/mm/yyyy','es',1);blur();" />
	</div>
</c:if>
<c:if test="${entorno.numeroFactura}">
	<div class="etiqueta titulo cgp"><bean:message
		key="general.numeroFactura" bundle="etiquetas" /></div>
	<div class="parametro titulo "><html:text styleClass="cgp"
		property="numeroFactura" ></html:text></div>
</c:if>

<c:if test="${entorno.controlFactura}">
	<div class="etiqueta titulo cgp"><bean:message
		key="general.controlfactura" bundle="etiquetas" /></div>
	<div class="parametro titulo "><html:text styleClass="cgp"
		property="controlFactura"></html:text></div>
</c:if>
<c:if test="${entorno.montoFactura}">
	<div class="etiqueta titulo cgp"><bean:message
		key="general.monto.pagado" bundle="etiquetas" /></div>
	<div class="parametro titulo "><html:text styleClass="cgp"
		property="montoFactura" ></html:text></div>
</c:if>
<c:if test="${entorno.montoAmparado}">
	<div class="etiqueta titulo cgp"><bean:message
		key="general.monto.amparado" bundle="etiquetas" /></div>
	<div class="parametro titulo "><html:text styleClass="cgp"
		property="montoAmparado" ></html:text></div>
</c:if>
<c:if test="${entorno.montoNoAmparado}">
	<div class="etiqueta titulo cgp"><bean:message
		key="general.monto.noamparado" bundle="etiquetas" /></div>
	<div class="parametro titulo "><html:text styleClass="cgp"
		property="montoNoAmparado" ></html:text></div>
</c:if>

<c:if test="${entorno.tipoGasto}">
	<div class="etiqueta titulo cgp"><bean:message
		key="general.tipoGasto" bundle="etiquetas" /></div>
	<div class="parametro titulo cgp"><html:select styleClass="cgp"
		property="tipoGasto" >
		<html:option styleClass="seleccione" value="-1">[SELECCIONAR]</html:option>
		<html:options collection="listTipoGasto" property="id"
			labelProperty="descripcion" />
	</html:select></div>
	
</c:if>
<br><br><br><br><br><br><br><br><br><br><br><br><br>
<div align="right" style="padding-right: 200px;"><input title="Guardar" value="Guardar"
		type="button" onclick="submit();"  /></div>
	
	<div class="sp5">
	</div>
	
	<c:if test="${listaFactura!=null}">
			<div class="s10"></div>
			
			
			<table class="tabla" width="600" cellpadding="2" cellspacing="1"
				border="0">
				<tr class="tituloCabecera">
					<td><bean:message key="general.fechafactura" bundle="etiquetas" /></td>
					<td><bean:message key="general.numeroFactura" bundle="etiquetas" /></td>
					<td><bean:message key="general.controlFactura" bundle="etiquetas" /></td>
					<td><bean:message key="general.monto.pagado" bundle="etiquetas" /></td>
					<td><bean:message key="general.montoAmparado" bundle="etiquetas" /></td>
					<td><bean:message key="general.tipoGasto" bundle="etiquetas" /></td>
	
				</tr>
				<c:forEach items='${listaFactura}' var="fact">
				<tr class="item" bgcolor="white"
					onclick="javaScript:submit2('<c:out value="${fact.idFactura}" />', '1')">
					<td><fmt:formatDate pattern="dd/MM/yyyy" 
					value="${fact.fechaFactura}" /></td>
					<td><c:out value="${fact.numeroFactura}" /></td>
					<td><c:out value="${fact.controlFactura}" /></td>
					<td><c:out value="${fact.montoFactura}" /><input type="hidden" id="hdMontoFactura" name="hdMontoFactura" value="<c:out value="${fact.montoFactura}" />" /></td>
					<td><c:out value="${fact.montoAmparado}" /><input type="hidden" id="hdMontoAmparado" name="hdMontoAmparado" value="<c:out value="${fact.montoAmparado}" />" /></td>
					<td><c:out value="${fact.tipoGasto.descripcion}" /></td>
					</tr>
					</c:forEach>
				
			</table>
			
</c:if>
<div align="right"><input title="aceptar" value="aceptar"
		type="button" onclick="javaScript:cerrar();" /></div>
</html:form>
</body>
</html>
<script>
function cerrar(){
	var opeMf =window.opener.document.globalActionForm.totalFacturado;
	var opeTal=window.opener.document.globalActionForm.totalAliquidar;
	
	opeMf.value =document.getElementById('hdMontoFactura').value;
	opeTal.value=document.getElementById ('hdMontoAmparado').value;
	close();
}

</script>



