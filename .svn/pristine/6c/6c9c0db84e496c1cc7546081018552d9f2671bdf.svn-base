<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<tiles:insert page="/jsp/plantilla/plantilla.jsp">

	<tiles:put name="titulo" direct="true">
		<bean:message key="principal.titulo" bundle="etiquetas" />
	</tiles:put>

	<tiles:put name="titulopagina"
		content="Inicio / Emergencia / Consulta de Emergencia" direct="true" />
	<tiles:put name="itemsup" content="" direct="true" />
	<tiles:put name="itemsdown" content="" direct="true" />


	<tiles:put name="cuerpo" direct="true">

		<div class="etiqueta titulo cgp"><bean:message
			key="general.subCodigo" bundle="etiquetas" /></div>
		<div class="nblack "><c:out
			value="${siniestro.aniomesCodigo}-${siniestro.codigo}-${siniestro.subCodigo}" />
		</div>
		<div class="etiqueta titulo cgp"><bean:message
			key="general.usuarioCreador" bundle="etiquetas" /></div>
		<div class="parametro titulo "><c:out
			value="${siniestro.idUsuario}" /></div>
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
			value="${siniestro.estatus.descripcion}" /></div>

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

		<table class="tabla" width="700" cellpadding="2" cellspacing="1"
			border="0">
			<c:if test="${siniestro.estatus.id==9}">
				<tr class="item" bgcolor="white">
					<td>

					<div class="etiqueta titulo cgp"><bean:message
						key="general.notaCobertura" bundle="etiquetas" /></div>
					<img src="<%=request.getContextPath()%>/images/printer.png"
						onclick="javascript:openWinScroll2('<%=request.getContextPath()%>/security/comunes/generareporte.do?id=<c:out
								value="${siniestro.id}" />&anio=<c:out
								value="${siniestro.anioSiniestro}" />&id_reporte=13','NotaCobertura',800,600);">
					</td>
				</tr>
			</c:if>
			<c:if test="${siniestro.estatus.id==4}">
				<tr class="item" bgcolor="white">
					<td>

					<div class="etiqueta titulo cgp"><bean:message
						key="general.notaCobertura" bundle="etiquetas" /></div>
					<img src="<%=request.getContextPath()%>/images/printer.png"
						onclick="javascript:openWinScroll2('<%=request.getContextPath()%>/security/comunes/generareporte.do?id=<c:out
								value="${siniestro.id}" />&anio=<c:out
								value="${siniestro.anioSiniestro}" />&id_reporte=10','NotaCobertura',800,600);">
					</td>
				</tr>
			</c:if>
			<tr class="item" bgcolor="white">
				<td>
				<div class="etiqueta titulo cgp"><bean:message
					key="general.adjuntos" bundle="etiquetas" /></div>
				<div><a class="red" href="#"
					onclick="javascript:openWinScroll2('<%=request.getContextPath()%>/security/upload/uploadFile.do?idSiniestro=<c:out value='${siniestro.id}'/>&anio=<c:out	value="${siniestro.anioSiniestro}" />','name',500,500);">Ver
				<bean:message key="general.adjuntos" bundle="etiquetas" /></a></div>

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


		</table>

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


		<!--<table class="tabla" width="600" cellpadding="2" cellspacing="1"
			border="0">
			<tr class="tituloCabecera">
				<td><bean:message key="general.tipoGasto" bundle="etiquetas" /></td>
				<td><bean:message key="general.montoPresup" bundle="etiquetas" /></td>
				<td><bean:message key="general.montoNego" bundle="etiquetas" /></td>
				<td><bean:message key="general.montoAmp" bundle="etiquetas" /></td>
				<td><bean:message key="general.montoNoAmp" bundle="etiquetas" /></td>


			</tr>
			<c:forEach items="${listDetalle}" var="detalleFacturas">
				<tr class="item" bgcolor="white">

					<td><c:out value="${detalleFacturas.tipoGasto.descripcion}" /></td>
					<td align="right"><fmt:formatNumber
						value="${detalleFacturas.montoPresupuestado}" pattern='###0.00' /></td>
			    <td align="right"><fmt:formatNumber
						value="${detalleFacturas.montoNegociado}" pattern='###0.00' /></td>
			    <td align="right"><fmt:formatNumber value="${detalleFacturas.montoAmparado}"
						pattern='###0.00' /></td>
				  <td align="right"><fmt:formatNumber
						value="${detalleFacturas.montoNoAmparado}" pattern='###0.00' /></td>





				</tr>
			</c:forEach>
			<tr class="item">

				<td>
				<div align="right" class="parametro titulo ">Totales
				</td>
				<td><fmt:formatNumber value="${siniestro.montoPresupuestado}"
					pattern='###0.00' /></td>
				<td><fmt:formatNumber value="${siniestro.montoNegociado}"
					pattern='###0.00' /></td>
				<td><fmt:formatNumber value="${siniestro.montoAmparado}"
					pattern='###0.00' /></td>
				<td><fmt:formatNumber value="${siniestro.montoNoAmparado}"
					pattern='###0.00' /></td>





			</tr>

		</table>
		<c:if test="${facturas.numeroFactura!=null}">
		  <table class="tabla" width="600" cellpadding="2" cellspacing="1"
				border="0">
				<tr class="tituloCabecera">
					<div class="textorange" style="padding-bottom: 5px">Detalles
					de Liquidación</div>
					<td align="right"><bean:message key="general.numeroFactura"
						bundle="etiquetas" /></td>
					<td align="right"><bean:message key="general.controlFactura"
						bundle="etiquetas" /></td>
					<td align="right"><bean:message key="general.fechaRecepcionFactura"
						bundle="etiquetas" /></td>
					<td align="right"><bean:message key="general.fechaFactura"
						bundle="etiquetas" /></td>
					<td align="right"><bean:message key="general.montoFacturado"
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
						<td align="right"><fmt:formatNumber value="${facturas.montoFactura}"
							pattern='###0.00' /></td>
					
					</tr>
				</c:forEach>
			</table>

		</c:if>-->
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

