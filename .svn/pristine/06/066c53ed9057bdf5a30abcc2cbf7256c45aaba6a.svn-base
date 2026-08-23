<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<tiles:insert page="/jsp/plantilla/plantilla.jsp">
	<tiles:put name="titulo" direct="true">
		<bean:message key="principal.titulo" bundle="etiquetas" />
	</tiles:put>
	<tiles:put name="titulopagina" content="Detalle del Siniestro" direct="true" />
	<tiles:put name="itemsup" content="/jsp/comunes/items.jsp" />
	<tiles:put name="itemsdown" content="" direct="true" />
	<tiles:put name="cuerpo" direct="true">
		<div class="etiqueta titulo cgp"><bean:message key="general.subCodigo" bundle="etiquetas" /></div>
		<div class="parametro titulo "><input type="hidden" name="id" name="id" value="<c:out value="${siniestro.id}"/>" /> <c:out
			value="${siniestro.aniomesCodigo}${siniestro.codigo}${siniestro.subCodigo}" /></div>
		<div class="etiqueta titulo cgp"><bean:message key="general.nombres" bundle="etiquetas" /> y <bean:message key="general.apellidos"
			bundle="etiquetas" /></div>
		<div class="parametro titulo "><c:out value="${siniestro.nombresBeneficiario}" /> <c:out value="${siniestro.apellidosBeneficiario}" /></div>
		<div class="etiqueta titulo cgp"><bean:message key="general.proveedor" bundle="etiquetas" /></div>
		<div class="parametro titulo "><c:out value="${siniestro.proveedor.descripcion}" /></div>
		<div class="etiqueta titulo cgp"><bean:message key="general.tipoproveedor" bundle="etiquetas" /></div>
		<div class="parametro titulo "><c:out value="${siniestro.proveedor.tipoProveedor.descripcion}" /></div>
		<div class="etiqueta titulo cgp"><bean:message key="general.fecha.ocurrencia" bundle="etiquetas" /></div>
		<div class="parametro titulo "><fmt:formatDate pattern="dd/MM/yyyy" value="${siniestro.fechaOcurrencia}" /></div>
		<div class="etiqueta titulo cgp"><bean:message key="general.fechaNotificacion" bundle="etiquetas" /></div>
		<div class="parametro titulo "><fmt:formatDate pattern="dd/MM/yyyy" value="${siniestro.fechaNotificacion}" /></div>
		<div class="etiqueta titulo cgp"><bean:message key="general.tiposiniestro" bundle="etiquetas" /></div>
		<div class="parametro titulo "><c:out value="${siniestro.tipoSiniestro.descripcion}" /></div>
		<div class="etiqueta titulo cgp"><bean:message key="general.tipoenfermedad" bundle="etiquetas" /></div>
		<div class="parametro titulo "><c:out value="${siniestro.tipoEnfermedad.descripcion}" /></div>
		<div class="etiqueta titulo cgp"><bean:message key="general.tratamiento" bundle="etiquetas" /></div>
		<div title="<c:out  value="${siniestro.patologiaOrganoTratamiento.descripcion}"/>" class="parametro titulo "><c:out
			value="${siniestro.patologiaOrganoTratamiento.descripcion}" /></div>
		
		<div style="overflow: visible; clear: both;">
		<table class="tabla" width="500" cellpadding="1" cellspacing="1" border="0" bgcolor="yellow">
			<tr class="tituloCabecera">
				<td>Nro. Factura</td>
				<td>Nro. Control</td>
				<td>Fecha Factura</td>
				<td>Fecha Recepción</td>
				<td>Monto</td>
				<td>Modificar</td>
				<td>Eliminar</td>
			</tr>
			<c:forEach items="${facturas}" var="f">
				<tr class="item" bgcolor="white">
					<td><c:out value="${f.numeroFactura}" /></td>
					<td><c:out value="${f.controlFactura}" /></td>
					<td><fmt:formatDate pattern="dd/MM/yyyy" value="${f.fechaFactura}" /></td>
					<td><fmt:formatDate pattern="dd/MM/yyyy" value="${f.fechaRecepcionFactura}" /></td>
					<td><fmt:formatNumber value="${f.montoFactura}" /></td>
					<td><c:choose>
							<c:when test="${f.preOrden == null || f.preOrden eq '0'}">
								<a class="norange"
									href="<%=request.getContextPath()%>/security/carga/setupEditarFactura.do?idFact=<c:out value="${f.id}" />&idSini=<c:out value="${siniestro.id}" />&anioBusqueda=<c:out value="${siniestro.anioSiniestro}" />">Modificar</a>
							</c:when>
							<c:otherwise>
								<a class="cgp">No disponible</a>
							</c:otherwise>
						</c:choose></td>
					
					<td>
					
																	<c:choose>
								<c:when test="${f.preOrden == null}"><a class="nredp" href="<%=request.getContextPath()%>/security/carga/eliminaFacturas.do?idFact=<c:out value="${f.id}" />&idSini=<c:out value="${siniestro.id}" />&anioSiniestro=<c:out value="${siniestro.anioSiniestro}" />">Eliminar</a></c:when>
								<c:when test="${f.preOrden != null}"><a class="nturq">Asociada a Pre-Orden:<c:out value="${f.preOrden}" /></a></c:when>
								<c:otherwise><a class="nredp" href="<%=request.getContextPath()%>/security/carga/eliminaFacturas.do?idFact=<c:out value="${f.id}" />&idSini=<c:out value="${siniestro.id}" />&anioSiniestro=<c:out value="${siniestro.anioSiniestro}" />">Eliminar</a></c:otherwise>
								</c:choose>
					
				
					
					
					</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="8" bgcolor="white"><input onclick="submit2();" type="button" value="Buscar Siniestro" class="boton" /></td>
			</tr>
		</table>
		</div>
	</tiles:put>
</tiles:insert>
<script language="JavaScript">
	function openWinScroll2(popup_url, name, width, height) {

		var size = ',width=' + width + ',height=' + height;
		var posicion = ",left=" + Math.round(screen.availWidth / 4) + ",top="
				+ Math.round(screen.availHeight / 4);
		var popUp = window
				.open(
						popup_url,
						name,
						'menubar=yes,location=no,toolbar=no,status=no,scrollbars=no,titlebar=no,directories=no,resizable=no'
								+ size + posicion);
		popUp.opener = self;

	}
	function submit2() {

	document.forms[0].action='<%=request.getContextPath()%>/security/carga/buscaSiniestro.do'
		document.forms[0].submit();
	}	
</script>
