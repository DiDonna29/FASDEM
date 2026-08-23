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
	width: 18%;
	padding-right: 5px;
	padding-left: 5px;
}

.parampadeo {
	width: 79% px;
}
</style>
</head>

<body style="margin: 5px;">

<html:form action="/security/upload/uploadFile" method="post"
	enctype="multipart/form-data">
	<div class="titblue"
		style="background-color: #EEEEEE; padding-left: 5px; padding-bottom: 2px">Anexar
	Archivos</div>
	<div class="s10"></div>
	<div style="width: 490px; border-top: 0px;"><logic:messagesPresent
		message="true">

		<html:messages id="msg" message="true" bundle="mensajes">
			<div class="nredp" style="margin-left: 5px;">- <bean:write
				name="msg" /><br />
			</div>
		</html:messages>
		<div class="delineadoBotton"></div>
	</logic:messagesPresent> 
	<input type="hidden" name="idSiniestro" id="idSiniestro"
		value="<c:out value='${idSiniestro}'/>" /> 
		<input type="hidden"
		name="idUpload" id="idUpload" value="" />
		
		<input type="hidden" name="anio" property="anio" value="<bean:write name="FileUpload" property="anioSiniestro"/>"/>
	<div class="etiqueta padeo">Archivo</div>
	<div class="parametro parampadeo"><html:file property="theFile" />
	</div>

	<div class="etiqueta padeo">Descripción</div>
	<div class="parametro parampadeo"><html:textarea
		style="width:100%" property="descripcion" /></div>


	<div align="right" style="clear: both; width: 100%;"><html:submit
		styleClass="boton">Aceptar</html:submit></div>

	<div class="s10"></div>
	<div style="float: left; clear: both; width: 100%" align="right">
	<table class="tabla" width="100%" cellpadding="2" cellspacing="1"
		border="0">
		<tr class="tituloCabecera">
			<td align="center" bgcolor="white" class="textorange" colspan="4">Listado
			de Anexos</td>
		</tr>
		<tr class="tituloCabecera">
			<td width="35%">Nombre del Archivo</td>
			<td width="35%">Descripción</td>
			<td width="35%">Usuario</td>			
			<td width="15%" align="center" class="ntextblue">Descargar</td>
			<td width="15%" align="center" class="nredp">Eliminar</td>
		</tr>
		<c:forEach items="${adjuntos}" var="a">
			<tr class="item" bgcolor="white" style="cursor: default;">
				<td><c:out value="${a.fileName}" /></td>
				<td><c:out value="${a.descripcion}" /></td>
				<td><c:out value="${a.desUsuario}" /></td>
				<td align="center"><a class="textblue" target="_blank"
					href="<%=request.getContextPath()%>/security/upload/downloadFile.do?idUpload=<c:out value="${a.id}"/>&anio=<bean:write name="FileUpload" property="anioSiniestro"/>"><img
					height="13" width="14" border="0"
					src="<%=request.getContextPath()%>/images/download.gif"></img></a></td>
			
					
					<td class="grayplink" align="center"> 
	                   <img onclick="javascript:eliminar2('<c:out value="${a.id}"/>','<bean:write name="FileUpload" property="anioSiniestro"/>');"  style="cursor:hand" src="<%=request.getContextPath()%>/images/cross.png" width="14" height="13" title="Detalle"></img>
	                </td>
			
			</tr>
		</c:forEach>
	</table>
	</div>

	<div class="s10"></div>
	
	<input type="hidden" name="idA" id="idA" /> 

	</div>
</html:form>

<script language="JavaScript">




	function eliminar1(Id,a){
		
		
		alert(Id);
		alert (a);
		document.getElementById('idA').value=Id;
		document.location.target='_blank';
		if(confirm('¿Esta seguro que desea realizar esta operación?')){  
			document.location.href = '<%=request.getContextPath()%>/security/upload/deleteFile.do?idUpload='
	    }
		
	
    }

	
	function eliminar2(Id,a){
		
		if(confirm('¿Esta seguro que desea realizar esta operación?')){ 
		
			
			document.forms[0].action='<%=request.getContextPath()%>/security/upload/deleteFile.do?idUpload='+Id+'&anio='+a
			document.forms[0].submit();
		 }
		
	}
	

</script>


</body>

</html>




