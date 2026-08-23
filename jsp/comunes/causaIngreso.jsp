<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic-el.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@	taglib uri="/WEB-INF/tlds/pager-taglib.tld" prefix="pg"%>
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

<style>
.padeo {
	width: 20%;
	padding-right: 5px;
	padding-left: 5px;
}

.parampadeo {
	width: 75%;
}
</style>
</head>

<body style="margin-top: 10px;">
<html:form action="${form_action}">
	<div class="container" style="width: 100%; background-color: white;"><c:if test="${entorno.especialidad}">
		<div class="etiqueta padeo"><bean:message key="general.especialidad" bundle="etiquetas" /></div>
		<div class="parametro parampadeo"><html:select style="width:100%" styleClass="cgp" property="especialidad" onchange="cambioCombo('1')">
			<html:option styleClass="seleccione" value="-1">[SELECCIONAR]</html:option>
			<html:options collection="listEspecialidad" property="id" labelProperty="descripcion" />
		</html:select></div>
	</c:if> <c:if test="${entorno.organo}">
		<div class="etiqueta padeo"><bean:message key="general.organo" bundle="etiquetas" /></div>
		<div class="parametro parampadeo"><html:select style="width:100%" styleClass="cgp" property="organo" onchange="cambioCombo('2')">
			<html:option styleClass="seleccione" value="-1">[SELECCIONAR]</html:option>
			<html:options collection="listOrgano" property="id" labelProperty="descripcion" />
		</html:select></div>
	</c:if> <c:if test="${entorno.patologias}">
		<div class="etiqueta padeo"><bean:message key="general.patologia" bundle="etiquetas" /></div>
		<div class="parametro parampadeo"><html:select style="width:100%" styleClass="cgp" property="patologias" onchange="cambioCombo('3')">
			<html:option styleClass="seleccione" value="-1">[SELECCIONAR]</html:option>
			<html:options collection="listPatologias" property="id" labelProperty="descripcion" />
		</html:select></div>
	</c:if> <c:if test="${entorno.tratamiento}">
		<div class="etiqueta padeo"><bean:message key="general.tratamiento" bundle="etiquetas" /></div>
		<div class="parametro parampadeo"><html:select onchange="cambioCombo('4')" style="width:100%" styleClass="cgp" property="tratamiento">
			<html:option styleClass="seleccione" value="-1">[SELECCIONAR]</html:option>
			<html:options collection="listTratamiento" property="id" labelProperty="descripcion" />
		</html:select></div>
	</c:if> <!-- c:if test="${entorno.descripcion}"-->
	<div style="clear: both;" align="right"><input title="aceptar" value="aceptar" type="button" onclick="javaScript:cerrar();" /></div>
	<div style="clear: both;" align="right">&nbsp;</div>
	<div class="etiqueta padeo"><bean:message key="general.descripcion" bundle="etiquetas" /></div>
	<div class="parametro parampadeo"><html:text style="width:72%" property="descripcion" onfocus="limpiarCombo();"></html:text><input title="aceptar" value="buscar" type="button" onclick="submit();" /></div>
	<!-- /c:if-->



	<div class="sp5"><textarea onfocus="blur()" class="cgp" id="ruta" name="ruta" style="border-width: 0px; width: 100%;" 2" cols="4"></textarea></div>
</html:form>

<c:if test="${listTratamiento!=null }">
	<div>
	<table class="tabla">
		<tr class="tituloCabecera">
			<td>Especialidad</td>
			<td>Organo</td>
			<td>Patología</td>
			<td>Tratamiento</td>
		</tr>

		<pg:pager maxPageItems="<%=20%>" maxIndexPages="10" url="busquedaCausaIngreso.do" isOffset="false" export="offset,currentPageNumber=pageNumber" scope="request">
			<pg:param name="descripcion" />
			<c:forEach items="${listTratamiento}" var="t">
				<pg:item>
					<tr class="item" bgcolor="white" onclick="javaScript:seleccion('<c:out value="${t.id}" />','<c:out value="${t.descripcion}" />')">
						<td><c:out value="${t.desEspecialidad}" /></td>
						<td><c:out value="${t.desOrgano}" /></td>
						<td><c:out value="${t.desPatologia}" /></td>
						<td><c:out value="${t.desTratamiento}" /></td>
					</tr>
				</pg:item>
			</c:forEach>
			<pg:index>
				<tr bgcolor="#FFFFFF">
					<td colspan="9" width="100%" align="right" class="textblue"><pg:prev export="pageUrl">
						<img height="11" src="<%=request.getContextPath()%>/images/left1.gif" width="10" align="middle" />&nbsp; <a style="cursor: hand; cursor: pointer;" class="textorange" href="<c:out value='${pageUrl}'/>">Anterior</a>
					</pg:prev>&nbsp; | <pg:pages>
						<c:choose>
							<c:when test="${pageNumber==currentPageNumber}">
								<c:out value="${pageNumber}" />
							</c:when>
							<c:otherwise>
								<a style="cursor: hand; cursor: pointer;" class="textorange" href="<c:out value='${pageUrl}'/>"> <c:out value="${pageNumber}" /> </a>
							</c:otherwise>
						</c:choose>
					</pg:pages>| <pg:next export="pageUrl">
						<a style="cursor: hand; cursor: pointer;" class="textorange" href="<c:out value="${pageUrl}"/>"> Siguiente</a>&nbsp;<img height="11" src="<%=request.getContextPath()%>/images/right1.gif" width="10" align="middle" />
					</pg:next>&nbsp;</td>
				</tr>
			</pg:index>
		</pg:pager>
	</table>
	</div>
</c:if>



</body>
</html>
<script>
function cerrar(){
	var tra = document.forms[0].tratamiento;
	var obj =window.opener.document.globalActionForm.causaIngreso;
	var obj2 =window.opener.document.globalActionForm.idCausaIngreso;
	if(tra.value!='-1'){
	obj.value =document.getElementById('ruta').value;
	obj.title =document.getElementById('ruta').value;

		obj2.value =tra.value;	
	}
	close();
}
function cambioCombo(combo){
	
	var esp = document.forms[0].especialidad;
	var org = document.forms[0].organo;
	var pat = document.forms[0].patologias;
	var tra = document.forms[0].tratamiento;
	document.forms[0].descripcion.value = null;
	
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
function limpiarCombo(combo){
		esp.selectedIndex = 0;
		org.selectedIndex = 0;
		pat.selectedIndex = 0;
		tra.selectedIndex = 0;
} 

function seleccion(argId, argDescrip){
	document.forms[0].tratamiento.value = argId;
	document.getElementById('ruta').value = argDescrip;
	cerrar();
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