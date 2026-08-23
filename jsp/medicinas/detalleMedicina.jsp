<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<tiles:insert page="/jsp/plantilla/plantilla.jsp">

	<tiles:put name="titulo" direct="true">
		<bean:message key="principal.titulo" bundle="etiquetas" />
	</tiles:put>

	<tiles:put name="titulopagina" content="Inicio / Medicinas / Consulta de Órdenes Medicas"
		direct="true" />
	<tiles:put name="itemsup" content="" direct="true" />
	<tiles:put name="itemsdown" content="" direct="true" />


	<tiles:put name="cuerpo" direct="true">
		<div class="etiqueta titulo cgp"><bean:message
			key="general.subCodigo" bundle="etiquetas" /></div>
		<div class="nblack"><c:out
			value="${siniestro.aniomesCodigo}-${siniestro.codigo}-${siniestro.subCodigo}" />
		</div>

		<div class="etiqueta titulo cgp"><bean:message
			key="general.nombres" bundle="etiquetas" /> y <bean:message
			key="general.apellidos" bundle="etiquetas" /></div>
		<div class="parametro titulo "><c:out
			value="${siniestro.nombresBeneficiario}" /> <c:out
			value="${siniestro.apellidosBeneficiario}" /></div>
		<div class="etiqueta titulo cgp"><bean:message
			key="general.proveedor" bundle="etiquetas" /></div>
		<div class="parametro titulo "><c:out
			value="${siniestro.proveedor.descripcion}" /></div>
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
			value="${siniestro.observacion}" /></div>

		<c:if test="${factura.numeroFactura!=null}">
			<div class="etiqueta titulo cgp"><bean:message
				key="general.fechaLiquidacion" bundle="etiquetas" /></div>
			<div class="parametro titulo "><fmt:formatDate
				pattern="dd/MM/yyyy" value="${siniestro.fechaLiquidacion}" /></div>
		</c:if>
		<div class="etiqueta titulo cgp"><bean:message
			key="general.tipoTratamiento" bundle="etiquetas" /></div>
		<div class="parametro titulo "><c:out
			value="${siniestro.tipoTratamiento.descripcionTratamiento}" /></div>

		<div class="etiqueta titulo cgp"><bean:message
			key="general.monto" bundle="etiquetas" /></div>
		<div class="parametro titulo "><c:out
			value="${siniestro.montoPresupuestado}" /></div>
		<table class="tabla" width="700" cellpadding="2" cellspacing="1"
			border="0">
			
			<tr class="item" bgcolor="white">
				<td>
				<div class="etiqueta titulo cgp"><bean:message
					key="general.adjuntos" bundle="etiquetas" /></div>
				<a class="red" href="#"
					onclick="javascript:openWinScroll2('<%=request.getContextPath()%>/security/upload/uploadFile.do?idSiniestro=<c:out value='${siniestro.id}'/>&anio=<c:out	value="${siniestro.anioSiniestro}" />','name',500,500);">Ver
				Adjuntos</a>
				</div>

				</td>
			</tr>



			<tr class="item" bgcolor="white">
				<td>
				<div class="etiqueta titulo cgp"><bean:message
					key="general.notatecnicas" bundle="etiquetas" /></div>
				<div class="parametro titulo cgp"><a class="red" href="#"
					onclick="javascript:openWinScroll2('<%=request.getContextPath()%>/security/notatecnica/saveNotaTecnica.do?idSiniestro=<c:out value='${siniestro.id}'/>&anioSiniestro=<c:out	value="${siniestro.anioSiniestro}" />','name',500,500);">Ver
				Nota Técnica</a></div>
				</td>
			</tr>
			<tr class="item" bgcolor="white">
				<td>
				<div class="etiqueta titulo cgp"><bean:message
					key="general.notaActivo" bundle="etiquetas" /></div>
				<img src="<%=request.getContextPath()%>/images/printer.png"
					onclick="javascript:openWinScroll2('<%=request.getContextPath()%>/security/comunes/generareporte.do?id=<c:out
								value="${siniestro.id}" />&anio=<c:out
								value="${siniestro.anioSiniestro}" />&id_reporte=11','NotaTecnicaUsuarioActivo',800,600);">
				</td>
			</tr>
			<tr class="item" bgcolor="white">
				<td>
				<div class="etiqueta titulo cgp"><bean:message
					key="general.notaSiniestro" bundle="etiquetas" /></div>
				<img src="<%=request.getContextPath()%>/images/printer.png"
					onclick="javascript:openWinScroll2('<%=request.getContextPath()%>/security/comunes/generareporte.do?id=<c:out
								value="${siniestro.id}" />&anio=<c:out
								value="${siniestro.anioSiniestro}" />&id_reporte=12','NotaTecnicaSiniestro',800,600);">

				</td>
			</tr>
			<c:if test="${siniestro.tipoTramite.id==6&&siniestro.estatus.id==9}">
				<tr class="item" bgcolor="white">
					<td>
					<div class="etiqueta titulo cgp"><bean:message
						key="general.ordenMedicinas" bundle="etiquetas" /></div>
					<img src="<%=request.getContextPath()%>/images/printer.png"
						onclick="javascript:openWinScroll2('<%=request.getContextPath()%>/security/comunes/generareporte.do?id=<c:out
								value="${siniestro.id}" />&anio=<c:out
								value="${siniestro.anioSiniestro}" />&id_reporte=1','OrdenDeMedicinas',800,600);">
					</td>
				</tr>
			</c:if>
			

		</table>
		<c:if test="${factura.numeroFactura!=null}">
			<table class="tabla" width="600" cellpadding="2" cellspacing="1"
				border="0">
				<tr class="tituloCabecera">
					<td><bean:message key="general.tipoGasto" bundle="etiquetas" /></td>
					<td><bean:message key="general.montoAmparado"
						bundle="etiquetas" /></td>
					<td><bean:message key="general.montoPresupuestado"
						bundle="etiquetas" /></td>
					<td><bean:message key="general.montoNegociado"
						bundle="etiquetas" /></td>

				</tr>
				<c:forEach items="${listDetalle}" var="detalleFacturas">
					<tr class="item" bgcolor="white">

						<td><c:out value="${detalleFacturas.tipoGasto.descripcion}" /></td>
						<td><fmt:formatNumber
							value="${detalleFacturas.montoAmparado}" pattern='###0.00' /></td>
						<td><fmt:formatNumber
							value="${detalleFacturas.montoPresupuestado}" pattern='###0.00' /></td>
						<td><fmt:formatNumber
							value="${detalleFacturas.montoNegociado}" pattern='###0.00' /></td>




					</tr>
				</c:forEach>
			</table>
			<table class="tabla" width="600" cellpadding="2" cellspacing="1"
				border="0">
				<tr class="tituloCabecera">
					<div class="textorange" style="padding-bottom: 5px">Detalles
					de Liquidación</div>
					<td><bean:message key="general.numeroFactura"
						bundle="etiquetas" /></td>
					<td><bean:message key="general.controlFactura"
						bundle="etiquetas" /></td>
					<td><bean:message key="general.fechaRecepcionFactura"
						bundle="etiquetas" /></td>
					<td><bean:message key="general.fechaFactura"
						bundle="etiquetas" /></td>
					<td><bean:message key="general.montoFacturado"
						bundle="etiquetas" /></td>

				</tr>
				<c:forEach items="${listFactura}" var="facturas">
					<tr class="item" bgcolor="white">
						<td><c:out value="${facturas.numeroFactura}" /></td>
						<td><c:out value="${facturas.controlFactura}" /></td>
						<td><fmt:formatDate pattern="dd/MM/yyyy"
							value="${facturas.fechaRecepcionFactura}" /></td>
						<td><fmt:formatDate pattern="dd/MM/yyyy"
							value="${facturas.fechaFactura}" /></td>
						<td><fmt:formatNumber value="${facturas.montoFactura}"
							pattern='###0.00' /></td>

					</tr>
				</c:forEach>
			</table>
		</c:if>


	</tiles:put>
</tiles:insert>



<script language="JavaScript">
	function openWinScroll2(popup_url, name, width, height) {

		var size = ',width=' + width + ',height=' + height;
		var posicion = ",left=" + Math.round(screen.availWidth / 2) + ",top="
				+ Math.round(screen.availHeight / 2);
		var popUp = window
				.open(
						popup_url,
						name,
						'menubar=yes,location=no,toolbar=no,status=no,scrollbars=no,titlebar=no,directories=no,resizable=no'
								+ size + posicion);
		popUp.opener = self;

	}
</script>
