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
		content="Inicio / Liquidación / Carga de facturas" direct="true" />
	<tiles:put name="itemsup" content="/jsp/comunes/items.jsp" />
	<tiles:put name="itemsdown" content="" direct="true" />
	<tiles:put name="cuerpo" direct="true">
		<html:hidden property="idFactura" name="globalActionForm" />
		<html:hidden property="idSiniestro" name="globalActionForm" />
		<input type="hidden" name="anioSiniestro" value="<c:out	value="${siniestro.anioSiniestro}" />"/>
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
			key="general.usuarioCreador" bundle="etiquetas" /></div>
		<div class="parametro titulo "><c:out
			value="${siniestro.idUsuario}" /></div>
			<c:if test="${siniestro.estatus.id==4}">
			<div class="etiqueta titulo cgp"><bean:message
				key="general.usuarioLiquidacion" bundle="etiquetas" /></div>
			<div class="parametro titulo "><c:out
				value="${motEst.idUsuario}" /></div>
		</c:if>

				
			<div class="etiqueta titulo cgp"><bean:message
				key="general.tipoproveedor" bundle="etiquetas" /></div>
			<div class="parametro titulo "><c:out
				value="${siniestro.proveedor.tipoProveedor.descripcion}" /></div>
					<div class="etiqueta titulo cgp"><bean:message
				key="general.rif" bundle="etiquetas" /></div>
			<div class="parametro titulo "><c:out
				value="${siniestro.proveedor.identificador}" /></div>				
			<div class="etiqueta titulo cgp"><bean:message
				key="general.proveedor" bundle="etiquetas" /></div>
			<div class="parametro titulo "><c:out
				value="${siniestro.proveedor.descripcion}" /></div>



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
				key="general.montoNegociado" bundle="etiquetas" /></div>
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
			<div class="parametro titulo "><a class="red" href="#"
				onclick="javascript:openWinScroll2('<%=request.getContextPath()%>/security/notatecnica/saveNotaTecnica.do?idSiniestro=<c:out value='${siniestro.id}'/>&anioSiniestro=<c:out	value="${siniestro.anioSiniestro}" />','name',500,500);">Ver
			Nota Técnica</a></div>



			<c:if test="${siniestro.tipoTramite.id==1}">
				<div class="etiqueta titulo cgp">&nbsp;</div>
				<div class="parametro titulo "><a class="textorange"
					href="<%=request.getContextPath()%>/security/emergencia/liquidacion/bandejaPorLiquidar.do">Ir
				a la Bandeja de Emergencia "Por Liquidar"</a></div>
			</c:if>
			<c:if test="${siniestro.tipoTramite.id==2}">
				<div class="etiqueta titulo cgp">&nbsp;</div>
				<div class="parametro titulo "><a class="textorange"
					href="<%=request.getContextPath()%>/security/cartaAval/liquidacion/bandejaPorLiquidar.do">Ir
				a la Bandeja de Carta Aval "Por Liquidar"</a></div>
			</c:if>
			<c:if test="${siniestro.tipoTramite.id==3}">
				<div class="etiqueta titulo cgp">&nbsp;</div>
				<div class="parametro titulo "><a class="textorange"
					href="<%=request.getContextPath()%>/security/reembolsos/liquidacion/bandejaPorLiquidar.do">Ir
				a la Bandeja de Reembolsos "Por Liquidar"</a></div>
			</c:if>

			<c:if test="${siniestro.tipoTramite.id==4}">
				<div class="etiqueta titulo cgp">&nbsp;</div>
				<div class="parametro titulo "><a class="textorange"
					href="<%=request.getContextPath()%>/security/aps/liquidacion/bandejaPorLiquidar.do">Ir
				a la Bandeja de A.P.S. "Por Liquidar"</a></div>
			</c:if>

		</c:if>
		<c:if test="${facturas!=null}">
			<div style="overflow: visible; clear: both;">
			<table class="tabla" width="500" cellpadding="1" cellspacing="1"
				border="0" bgcolor="yellow">
				<tr class="tituloCabecera">
					<td>Nro. Factura</td>
					<td>Nro. Control</td>
					<td>Fecha Factura</td>
					<td>Fecha Recepción</td>
					<td>Monto</td>
					<td>Cargar Detalle</td>
					<td>Modificar</td>
					<td>Eliminar</td>
					<c:if test="${validador==1}">
					<c:if test="${siniestro.tipoTramite.id==4}">
					<script language="JavaScript">
	alert("El siniestro posse Facturas Pre-Cargadas");
	</script></c:if></c:if>

				</tr>
				<c:forEach items="${facturas}" var="f">

					<tr class="item" bgcolor="white">
						<td><c:out value="${f.numeroFactura}" /></td>
						<td><c:out value="${f.controlFactura}" /></td>
						<td><fmt:formatDate pattern="dd/MM/yyyy"
							value="${f.fechaFactura}" /></td>
						<td><fmt:formatDate pattern="dd/MM/yyyy"
							value="${f.fechaRecepcionFactura}" /></td>
						<td><fmt:formatNumber value="${f.montoFactura}" /></td>



						<td class="cgp"><c:choose>
							<c:when test="${f.preOrden == null || f.preOrden eq '0'}">
								<a class="ntextblue" href="#"
									onclick="javascript:openWinScroll2('<%=request.getContextPath()%>/security/aps/liquidacion/cargarDetalleFactura.do?idFact=<c:out value="${f.id}" />&idSini=<c:out value="${siniestro.id}" />&anioBusqueda=<c:out value="${siniestro.anioSiniestro}" />','name',500,500);">Cargar
								Detalle</a>
							</c:when>
							<c:otherwise>
								No disponible
							</c:otherwise>
						</c:choose></td>


						<td><c:choose>
							<c:when test="${f.preOrden == null || f.preOrden eq '0'}">
								<a class="norange"
									href="<%=request.getContextPath()%>/security/aps/liquidacion/setupEditarFactura.do?idFact=<c:out value="${f.id}" />&idSini=<c:out value="${siniestro.id}" />&anioBusqueda=<c:out value="${siniestro.anioSiniestro}" />">Modificar</a>
							</c:when>
							<c:otherwise>
								<a class="cgp">No disponible</a>
							</c:otherwise>
						</c:choose></td>

						<td><c:choose>
							<c:when test="${f.preOrden == null || f.preOrden eq '0'}">
								<a class="nredp"
									href="<%=request.getContextPath()%>/security/aps/liquidacion/eliminarFactura.do?idFact=<c:out value="${f.id}" />&idSini=<c:out value="${siniestro.id}" />&anioBusqueda=<c:out value="${siniestro.anioSiniestro}" />">Eliminar</a>
							</c:when>
							<c:otherwise>
								<a class="cgp">No disponible</a>
							</c:otherwise>
						</c:choose></td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="8" bgcolor="white">Observaciones de liquidación <textarea
						id="observacion" name="observacion"><c:choose>
						<c:when test="${siniestro.motivo == null}">
							<c:out value="${siniestro.observacion}" />
						</c:when>
						<c:otherwise>
							<c:out value="${siniestro.motivo}" />
						</c:otherwise>
					</c:choose></textarea></td>
				</tr>
				<tr>
					<td colspan="8" bgcolor="white"><input onclick="submit2();"
						type="button" value="liquidar" class="boton" /></td>
				</tr>
			</table>
			</div>
		</c:if>
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
						'menubar=yes,location=no,toolbar=no,status=no,scrollbars=yes,titlebar=no,directories=no,resizable=yes'
								+ size + posicion);
		popUp.opener = self;

	}
	function submit2() {

	document.forms[0].action='<%=request.getContextPath()%>/security/aps/liquidacion/estatusLiquidado.do'
		document.forms[0].submit();
	}	
	function submit3() {

		alert("YA TIENE FACTURAS CARGADAS");
		}
</script>
