<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<tiles:insert page="/jsp/plantilla/plantilla.jsp">

	<tiles:put name="titulo" direct="true">
		<bean:message key="principal.titulo" bundle="etiquetas" />
	</tiles:put>

	<tiles:put name="titulopagina" content="Notas Médicas"
		direct="true" />
	<tiles:put name="itemsup" content="" direct="true" />
	<tiles:put name="itemsdown" content="/jsp/comunes/items.jsp"  />


	<tiles:put name="cuerpo" direct="true">

		<div class="etiqueta titulo cgp"><bean:message
			key="general.subCodigo" bundle="etiquetas" /></div>
		<div class="nblack "><c:out
			value="${siniestro.aniomesCodigo}-${siniestro.codigo}-${siniestro.subCodigo}" />
		</div>

<div class="etiqueta titulo cgp"><bean:message
			key="general.cobertura" bundle="etiquetas" /></div>
		<div class="parametro titulo "><c:out
			value="${siniestro.cobertura.tipoCobertura.descripcion}" /> </div>
		<div class="etiqueta titulo cgp"><bean:message
			key="general.nombres" bundle="etiquetas" /> y <bean:message
			key="general.apellidos" bundle="etiquetas" /></div>
		<div class="parametro titulo "><c:out
			value="${siniestro.nombresBeneficiario}" /> <c:out
			value="${siniestro.apellidosBeneficiario}" /></div>
			<div class="etiqueta titulo cgp"><bean:message
			key="general.cedula" bundle="etiquetas" /></div>
		<div class="parametro titulo "><c:out
			value="${siniestro.cedulaBeneficiario}" />
		</div>
		<div class="etiqueta titulo cgp"><bean:message
			key="general.parentesco" bundle="etiquetas" /></div>
		<div class="parametro titulo "><c:out
			value="${siniestro.parentesco}" />
		</div>
		<div class="etiqueta titulo cgp"><bean:message
			key="general.tipoproveedor" bundle="etiquetas" /></div>
		<div class="parametro titulo "><c:out 
			 value="${siniestro.proveedor.tipoProveedor.descripcion}" /></div>
		<div class="etiqueta titulo cgp"><bean:message
			key="general.proveedor" bundle="etiquetas" /></div>
		<div class="parametro titulo "><c:out 
			 value="${siniestro.proveedor.descripcion}" /></div>

		<div class="etiqueta titulo cgp"><bean:message
			key="general.fecha.ocurrencia" bundle="etiquetas" /></div>
		<div class="parametro titulo "><fmt:formatDate
			pattern="dd/MM/yyyy" value="${siniestro.fechaOcurrencia}" /></div>
		<div class="etiqueta titulo cgp"><bean:message
			key="general.fechaIngreso" bundle="etiquetas" /></div>
		<div class="parametro titulo "><fmt:formatDate
			pattern="dd/MM/yyyy" value="${siniestro.fechaIngreso}" /></div>
		<div class="etiqueta titulo cgp"><bean:message
			key="general.fechaEgreso" bundle="etiquetas" /></div>
		<div class="parametro titulo "><fmt:formatDate
			pattern="dd/MM/yyyy" value="${siniestro.fechaEgreso}" /></div>

		<div class="etiqueta titulo cgp"><bean:message
			key="general.fechaNotificacion" bundle="etiquetas" /></div>
		<div class="parametro titulo "><fmt:formatDate
			pattern="dd/MM/yyyy" value="${siniestro.fechaNotificacion}" /></div>
		
		<c:if test="${siniestro.fechaLiquidacion!=null}">
			<div class="etiqueta titulo cgp"><bean:message
				key="general.fechaLiquidacion" bundle="etiquetas" /></div>
			<div class="parametro titulo "><fmt:formatDate
				pattern="dd/MM/yyyy" value="${siniestro.fechaLiquidacion}" /></div>
		</c:if>
		<div class="etiqueta titulo cgp"><bean:message
			key="general.citaPostOperatorio" bundle="etiquetas" /></div>
		<div class="parametro titulo "><c:if
			test="${siniestro.citaPostOperatorio!=false}">
			Requiere de Cita PostOperatorio</c:if><c:if
			test="${siniestro.citaPostOperatorio==false}">
			No Requiere de Cita PostOperatorio</c:if></div>

		<div class="etiqueta titulo cgp"><bean:message
			key="general.tiposiniestro" bundle="etiquetas" /></div>
		<div class="parametro titulo "><c:out
			value="${siniestro.tipoSiniestro.descripcion}" /></div>
			
			
			
		<div class="etiqueta titulo cgp"><bean:message
			key="general.estatus" bundle="etiquetas" /></div>
		<div class="parametro titulo "><c:out
			value="${siniestro.estatus.descripcion}" />  <c:if test="${tipoImpresion!=null}"> <img src="<%=request.getContextPath()%>/images/printer.png"
					onclick="javascript:openWinScroll2('<%=request.getContextPath()%>/security/comunes/generareporte.do?id=<c:out value="${siniestro.id}" />&id_reporte=<c:out  value="${tipoImpresion}"/>&anio=<c:out	value="${siniestro.anioSiniestro}" />','CartaAval',800,600);"></c:if></div>
		
		
		<c:if test="${siniestro.estatus.id==34}">
		<div class="etiqueta titulo cgp"><bean:message
			key="general.justificacion" bundle="etiquetas" /></div>
		<div class="parametro titulo "><c:out
			value="${motEst.descripcion}" /></div>
			</c:if>
			<c:if test="${siniestro.estatus.id==35}">
		<div class="etiqueta titulo cgp"><bean:message
			key="general.justificacion" bundle="etiquetas" /></div>
		<div class="parametro titulo "><c:out
			value="${motEst.descripcion}" /></div>
			</c:if>
			<c:if test="${siniestro.estatus.id==2}">
		<div class="etiqueta titulo cgp"><bean:message
			key="general.justificacion" bundle="etiquetas" /></div>
		<div class="parametro titulo "><c:out
			value="${motEst.descripcion}" /></div>
			</c:if>
			<c:if test="${siniestro.estatus.id==38}">
		<div class="etiqueta titulo cgp"><bean:message
			key="general.justificacion" bundle="etiquetas" /></div>
		<div class="parametro titulo "><c:out
			value="${motEst.descripcion}" /></div>
			</c:if>
			
			
			
		<div class="etiqueta titulo cgp"><bean:message
			key="general.tipoenfermedad" bundle="etiquetas" /></div>
		<div class="parametro titulo "><c:out
			value="${siniestro.tipoEnfermedad.descripcion}" /></div>
		<div class="etiqueta titulo cgp"><bean:message
			key="general.listTipoTramite" bundle="etiquetas" /></div>
		<div class="parametro titulo "><c:out
			value="${siniestro.tipoTramite.descripcion}" /></div>

		<div class="etiqueta titulo cgp"><bean:message
			key="general.tratamiento" bundle="etiquetas" /></div>
		<div
			title="<c:out  value="${siniestro.patologiaOrganoTratamiento.descripcion}"/>"
			class="parametro titulo "><c:out
			value="${siniestro.patologiaOrganoTratamiento.descripcion}" /></div>

		<div class="etiqueta titulo cgp"><bean:message
			key="general.observacion" bundle="etiquetas" /></div>
		<div class="parametro titulo "><c:out
			value="${siniestro.observacion}" /></div>





		
		
		
		
		
		
		
		
		
		
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
</script>

