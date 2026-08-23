<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<tiles:insert page="/jsp/plantilla/plantilla.jsp">

	<tiles:put name="titulo" direct="true">
		<bean:message key="principal.titulo" bundle="etiquetas" />
	</tiles:put>

	<tiles:put name="titulopagina" content="Inicio / Reembolsos / Edición de Siniestros"
		direct="true" />
	<tiles:put name="itemsup" content="/jsp/comunes/items.jsp" />
    <tiles:put name="itemsdown" content=" " direct="true" />
	<tiles:put name="cuerpo" direct="true">
	
<div class="etiqueta titulo cgp"><bean:message
			key="general.notaTecnica" bundle="etiquetas" /></div>
		<div class="parametro titulo cgp"><a class="red" href="#"
			onclick="javascript:openWinScroll2('<%=request.getContextPath()%>/security/notatecnica/saveNotaTecnica.do?idSiniestro=<c:out value='${siniestro.id}'/>&anioSiniestro=<c:out	value="${siniestro.anioSiniestro}" />','name',500,500);">Nueva
		Nota Técnica</a></div>


		<div class="etiqueta titulo cgp"><bean:message
			key="general.archivos" bundle="etiquetas" /></div>
		<div class="parametro titulo cgp"><a class="red" href="#"
			onclick="javascript:openWinScroll2('<%=request.getContextPath()%>/security/upload/uploadFile.do?idSiniestro=<c:out value='${siniestro.id}'/>&anio=<c:out	value="${siniestro.anioSiniestro}" />','name',500,500);">Nuevo
		Archivo Adjunto</a></div>
		
		<div class="etiqueta titulo cgp">
			<bean:message key="general.subCodigo" bundle="etiquetas" />
		</div>
		<div class="parametro titulo ">
			<c:out value="${siniestro.aniomesCodigo}${siniestro.codigo}${siniestro.subCodigo}"/>
		</div>
			<div class="etiqueta titulo cgp">
			<bean:message key="general.nombres" bundle="etiquetas" /> y  
			<bean:message key="general.apellidos" bundle="etiquetas" />
		</div>
		<div class="parametro titulo ">
			<c:out value="${siniestro.nombresBeneficiario}"/> <c:out value="${siniestro.apellidosBeneficiario}"/>
		</div>
		
		<div
			style="border-bottom-color: #EEEEEE; border-bottom-width: thin; border-bottom-style: solid">&nbsp;</div>
		<div class="sp5"></div>
		<div align="right"><input class="boton" value="aceptar"
			type="button" onclick="editar();" /></div>
	</tiles:put>
</tiles:insert>
<script language="JavaScript">
function editar() {
		document.forms[0].accion.value='editar';
		document.forms[0].submit();
	}
</script>


	
	
	
	
	