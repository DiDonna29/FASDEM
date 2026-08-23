<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<tiles:insert page="/jsp/plantilla/plantilla.jsp">

	<tiles:put name="titulo" direct="true">
		<bean:message key="principal.titulo" bundle="etiquetas" />
	</tiles:put>
	<tiles:put name="imgtitulo" content="/images/user.png" direct="true" />
	<tiles:put name="view" content=" " direct="true" />
	<tiles:put name="items" content="" />
	<tiles:put name="cuerpo" direct="true">
		<table class="blackg" width="100%" cellpadding="1" cellspacing="0"
			border="0">
			<tr>
				<td colspan="2">
				<div class="line" />
				</td>
			</tr>
<td align="center" class=textblue colspan="2"><input
					type="hidden" name="nodo" value="1"></td>
	<tr>
				<td width="14%">Numero de Orden </td>
				<td width="86%"><input name="siniestro" type="text">
			    <input type="button" value="Buscar" onclick="javaScript:document.forms.action=''"  /></td>
						
			</tr>
            	<tr>
				<td width="14%">Numero de Siniestro </td>
				<td width="86%"><input name="siniestro" type="text">
			    <input type="button" value="Buscar" onclick="javaScript:document.forms.action='<%=request.getContextPath()%>/security/pago/consulta_pago.do?fromlist=true';document.forms.submit();"  /></td>
						
			</tr>
            <tr>
            
				<td width="14%">Clinica </td>
				<td width="86%">
<c:if test="${entorno.cedula}"></c:if>

<c:if test="${entorno.nombres}">
		<div class="etiqueta titulo cgp"><bean:message key="general.nombres" bundle="etiquetas" /></div>
		<div class="parametro titulo cgp"></div>
</c:if>
<c:if test="${entorno.apellidos}">		
		<div class="etiqueta titulo cgp"><bean:message key="general.apellidos" bundle="etiquetas" /></div>
		<div class="parametro titulo cgp">
		  <input name="siniestro2" type="text" />
		  Desde
			<input  class="cgp texto" type="text" name="nombres"/>
			Hasta
			<input  class="cgp texto" type="text" name="apellidos"/>
		    <span class="etiqueta titulo cgp">
            <input class="boton" value="aceptar" type="submit" />
            </span></div>
</c:if>

<c:if test="${entorno.fechaInicio}">
		<div class="etiqueta titulo cgp"><bean:message key="general.fecha.inicio" bundle="etiquetas" /></div>
</c:if>

<c:if test="${entorno.fechaFin}">
		<div class="etiqueta titulo cgp" ><bean:message key="general.fecha.fin" bundle="etiquetas" />
		</div>
</c:if>

<c:if test="${entorno.boton}">
  <div class="sp5"></div>
		<div align="right"></div>
</c:if>





</td>
						
			</tr>
            
		</table>
	</tiles:put>
</tiles:insert>
