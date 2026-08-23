<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<tiles:insert page="/jsp/plantilla/plantilla.jsp">
	<tiles:put name="titulo" direct="true">
		<bean:message key="principal.titulo" bundle="etiquetas" />
	</tiles:put>
	<tiles:put name="titulopagina"
		content="Inicio / Liquidaciòn / Modificaciòn de factura" direct="true" />
	<tiles:put name="itemsup" content="/jsp/comunes/items.jsp" />
	<tiles:put name="itemsdown" content="" direct="true" />
	<tiles:put name="cuerpo" direct="true">
		<html:hidden property="idFactura" name="globalActionForm" />
		<html:hidden property="idSiniestro" name="globalActionForm" />
		<c:if test="${siniestro!=null}">
			<div class="etiqueta titulo cgp"><bean:message
				key="general.cobertura" bundle="etiquetas" /></div>
			<div class="parametro titulo "><c:out
				value="${siniestro.cobertura.tipoCobertura.descripcion}:" /> <fmt:formatNumber
				groupingUsed="true" value="${siniestro.cobertura.monto}" /> Bs.</div>
			<div class="etiqueta titulo cgp"><bean:message
				key="general.subCodigo" bundle="etiquetas" /></div>
			<div class="parametro titulo "><input type="hidden" name="id"
				name="id" value="<c:out value="${siniestro.id}"/>" /> <c:out
				value="${siniestro.aniomesCodigo}${siniestro.codigo}${siniestro.subCodigo}" /></div>
			<div class="etiqueta titulo cgp"><bean:message
				key="general.nombres" bundle="etiquetas" /> y <bean:message
				key="general.apellidos" bundle="etiquetas" /></div>
			<div class="parametro titulo "><c:out
				value="${siniestro.nombresBeneficiario}" /> <c:out
				value="${siniestro.apellidosBeneficiario}" /></div>
			<div class="etiqueta titulo cgp"><bean:message
				key="general.fecha.ocurrencia" bundle="etiquetas" /></div>
			<div class="parametro titulo "><fmt:formatDate
				pattern="dd/MM/yyyy" value="${siniestro.fechaOcurrencia}" /></div>
			<div class="etiqueta titulo cgp"><bean:message
				key="general.fechaNotificacion" bundle="etiquetas" /></div>
			<div class="parametro titulo "><fmt:formatDate
				pattern="dd/MM/yyyy" value="${siniestro.fechaNotificacion}" /></div>
			<div class="etiqueta titulo cgp"><bean:message
				key="general.tiposiniestro" bundle="etiquetas" /></div>
			<div class="parametro titulo "><c:out
				value="${siniestro.tipoSiniestro.descripcion}" /></div>
			<div class="etiqueta titulo cgp"><bean:message
				key="general.tipoenfermedad" bundle="etiquetas" /></div>
			<div class="parametro titulo "><c:out
				value="${siniestro.tipoEnfermedad.descripcion}" /></div>
			<div class="etiqueta titulo cgp"><bean:message
				key="general.tratamiento" bundle="etiquetas" /></div>
			<div
				title="<c:out  value="${siniestro.patologiaOrganoTratamiento.descripcion}"/>"
				class="parametro titulo "><c:out
				value="${siniestro.patologiaOrganoTratamiento.descripcion}" /></div>
			<div class="etiqueta titulo cgp"><bean:message
				key="general.observacion" bundle="etiquetas" /></div>
			<div class="parametro titulo "><c:out
				value="${siniestro.observacion}" escapeXml="false" /></div>
			<div class="etiqueta titulo cgp"><bean:message
				key="general.montoPresupuestado" bundle="etiquetas" /></div>
			<div class="parametro titulo "><fmt:formatNumber
				value="${siniestro.montoPresupuestado}" pattern='###0.00' /> Bs.</div>
			<div class="etiqueta titulo cgp"><bean:message
				key="general.negociadoo" bundle="etiquetas" /></div>
			<div class="parametro titulo "><fmt:formatNumber
				value="${siniestro.montoNegociado}" pattern='###0.00' /> Bs.</div>
			<div class="etiqueta titulo cgp"><bean:message
				key="general.amparado" bundle="etiquetas" /></div>
			<div class="parametro titulo "><fmt:formatNumber
				value="${siniestro.montoAmparado}" pattern='###0.00' /> Bs.</div>
			<div class="etiqueta titulo cgp"><bean:message
				key="general.noAmparado" bundle="etiquetas" /></div>
			<div class="parametro titulo "><fmt:formatNumber
				value="${siniestro.montoNoAmparado}" pattern='###0.00' /> Bs.</div>
			<div class="etiqueta titulo cgp"><bean:message
				key="general.notaTecnica" bundle="etiquetas" /></div>
			<div class="parametro titulo "><c:out
				value="${siniestro.notaTecnica.observacion}"
				default="No posee nota técnica asociada" /></div>

			<!--<div class="etiqueta titulo cgp">&nbsp;</div>
			<div class="parametro titulo "><a class="textorange"
				href="<%=request.getContextPath()%>/security/aps/liquidacion/bandejaPorLiquidar.do">Ir
			a la Bandeja de A.P.S "Por Liquidar"</a></div>
		--></c:if>
	</tiles:put>
</tiles:insert>
