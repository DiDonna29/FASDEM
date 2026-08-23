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
<script src="<%=request.getContextPath()%>/js/jquery/ui/jquery.ui.core.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery/ui/jquery.ui.widget.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery/ui/jquery.ui.mouse.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery/ui/jquery.ui.sortable.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery/ui/jquery.ui.accordion.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery/ui/jquery.ui.datepicker.js"></script>
<script src="<%=request.getContextPath()%>/js/popcalendar.js"></script>

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
<input type="hidden" name="idSini" id="idSini" value="<c:out value="${idSini}"/>"/>
<input type="hidden" name="idFact" id="idFact" value="<c:out value="${idFact}"/>"/>
	<div class="s10"></div>
	<div class="textorange" style="padding-bottom: 5px">Detalle de la factura</div>

	<c:if test="${entorno.numeroFactura}">
	<div style="width:170px" class="etiqueta titulo cgp"><bean:message
		key="general.numeroFactura" bundle="etiquetas" /></div>
	<div class="parametro titulo "><html:text styleClass="cgp"
		property="numeroFactura"></html:text></div>
</c:if>
<c:if test="${entorno.controlFactura}">
	<div style="width:170px" class="etiqueta titulo cgp"><bean:message
		key="general.controlfactura" bundle="etiquetas" /></div>
	<div   class="parametro titulo "><html:text styleClass="cgp"
		property="controlFactura"></html:text></div>
</c:if>
<c:if test="${entorno.fechaFactura}">
	<div style="width:170px" class="etiqueta titulo cgp"><bean:message
		key="general.fechafactura" bundle="etiquetas" /></div>
	<div class="parametro titulo cgp"><html:text styleClass="cgp"
		property="fechaFactura"
		onfocus="javascript:showCalendarSelected2(document.forms[0].fechaFactura, document.forms[0].fechaFactura, 'dd/mm/yyyy','es',1);blur();"/></div>
</c:if>
<c:if test="${entorno.fechaRecepcionFactura}">
	<div style="width:170px" class="etiqueta titulo cgp"><bean:message
		key="general.fechaRecepcionFactura" bundle="etiquetas" /></div>
	<div  class="parametro titulo cgp"><html:text styleClass="cgp"
		property="fechaRecepcionFactura"
		onfocus="javascript:showCalendarSelected2(document.forms[0].fechaRecepcionFactura, document.forms[0].fechaRecepcionFactura, 'dd/mm/yyyy','es',1);blur();"/>
	</div>
</c:if>
	<div class="container" style="width: 500px; background-color: white;"><c:if test="${entorno.tipoGasto}">
		<div style="width:170px" class="etiqueta titulo cgp"><bean:message key="general.tipoGasto" bundle="etiquetas" /></div>
		<div style="width:320px" class="parametro titulo cgp"><html:select styleClass="cgp" property="tipoGasto" >
			<html:option styleClass="seleccione" value="-1">[SELECCIONAR]</html:option>
			<html:options collection="listTipoGasto" property="id" labelProperty="descripcion" />
		</html:select></div>
	</c:if> 
	<c:if test="${entorno.montoAmparado}">
		<div style="width:170px" class="etiqueta titulo cgp"><bean:message key="general.monto.amparado" bundle="etiquetas" /></div>
		<div style="width:320px" class="parametro titulo "><html:text styleClass="cgp" property="montoAmparado"></html:text></div>
	</c:if> 
	<c:if test="${entorno.monto}">
		<div style="width:170px" class="etiqueta titulo cgp">Monto Presupuestado (Bs.)</div>
		<div style="width:320px" class="parametro titulo "><html:text styleClass="cgp" property="monto"></html:text></div>
	</c:if> 
	<c:if test="${entorno.montoFactura}">
		<div style="width:170px" class="etiqueta titulo cgp">Monto Negociado (Bs.)</div>
		<div style="width:320px" class="parametro titulo "><html:text styleClass="cgp" property="montoFactura"></html:text></div>
	</c:if>
	
		<div style="clear: both;" align="right"><input title="aceptar" value="aceptar"
		type="submit" /></div>
		<div style="overflow: visible; clear: both;">
		<table class="tabla" width="500" cellpadding="1" cellspacing="1" border="0" bgcolor="yellow">
			<tr class="tituloCabecera">
				<td>Tipo Gasto</td>
				<td>Monto Presupuestado</td>
				<td>Monto Amparado</td>
				<td>Monto Negociado</td>
				<td>Editar</td>
				<td>Eliminar</td>
			</tr>
			<c:forEach items="${detalles}" var="d">
				<tr class="item" bgcolor="white">
					<td><c:out value="${d.tipoGasto.descripcion}" /></td>
					<td><fmt:formatNumber value="${d.montoPresupuestado}" /></td>
					<td><fmt:formatNumber value="${d.montoAmparado}" /></td>
					<td><fmt:formatNumber value="${d.montoNegociado}" /></td>
					<td><a class="nredp"
						href="<%=request.getContextPath()%>/security/cartaAval/liquidacion/eliminarFactura.do?idFact=<c:out value="${f.id}" />&idSini=<c:out value="${siniestro.id}" />">Editar</a></td>
					<td><a class="nredp"
						href="<%=request.getContextPath()%>/security/cartaAval/liquidacion/eliminarFactura.do?idFact=<c:out value="${f.id}" />&idSini=<c:out value="${siniestro.id}" />">Eliminar</a></td>
				</tr>
			</c:forEach>
			
		</table>
		</div>
</div>
</html:form>
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
<script>
var esp = document.forms[0].especialidad;
var org = document.forms[0].organo;
var pat = document.forms[0].patologias;
var tra = document.forms[0].tratamiento;
var rut = document.forms[0].ruta;

if (esp.value != '-1'){

	rut.value = esp.options[esp.selectedIndex].text
}
if (org.value != '-1'){

	rut.value = rut.value + ' / ' +org.options[org.selectedIndex].text
}
if (pat.value != '-1'){

	rut.value = rut.value + ' / ' +pat.options[pat.selectedIndex].text
}
if (tra.value != '-1'){

	rut.value = rut.value + ' / ' +tra.options[tra.selectedIndex].text
}
</script>