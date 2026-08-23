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
	<tiles:put name="itemsup" content=" " direct="true" />
	<tiles:put name="itemsdown" content=" " direct="true" />
	<tiles:put name="cuerpo" direct="true">
<table class="tabla" width="600" cellpadding="2" cellspacing="1"
				border="0">
				<tr class="tituloCabecera">
						<div class="textorange" style="padding-bottom: 5px">Cobertura de Atención Médica Primaria</div>
					<td><bean:message key="general.cobertura" bundle="etiquetas" />
					</td>
					<td ><fmt:formatNumber value="${cobert}"
							pattern='#,##0.00' /></td>
				</tr></table>
		<div class="s10"></div>
		<div class="textorange" style="padding-bottom: 5px"><bean:message
			key="general.titular" bundle="etiquetas" /></div>
		<div style="overflow: visible">
		<table class="tabla" width="600" cellpadding="2" cellspacing="1"
			border="0">
			<tr class="tituloCabecera">
				<td><bean:message key="general.cedula" bundle="etiquetas" /></td>
				<td><bean:message key="general.nombres" bundle="etiquetas" /></td>
				<td><bean:message key="general.apellidos" bundle="etiquetas" /></td>


			</tr>
			<tr class="item" bgcolor="white"
				onclick="javaScript:submit2('<c:out value="${s}" />', '1')">
				<td><c:out value="${s.cedula}" /></td>
				<td><c:out value="${s.nombres}" /></td>
				<td><c:out value="${s.apellidos}" /></td>

			</tr>


		</table>
		<div class="s10"></div>
		<div class="textorange" style="padding-bottom: 5px"><bean:message
			key="general.beneficiario" bundle="etiquetas" /></div>
		<div style="overflow: visible">
		<table class="tabla" width="600" cellpadding="2" cellspacing="1"
			border="0">
			<tr class="tituloCabecera">
				<td><bean:message key="general.cedula" bundle="etiquetas" /></td>
				<td><bean:message key="general.nombres" bundle="etiquetas" /></td>
				<td><bean:message key="general.apellidos" bundle="etiquetas" /></td>
				<td><bean:message key="general.parentesco" bundle="etiquetas" /></td>


			</tr>
			<tr class="item" bgcolor="white"
				onclick="javaScript:submit2('<c:out value="${s}" />', '1')">
				<td><c:out value="${s.cedulaBeneficiario}" /></td>
				<td><c:out value="${s.nombresBeneficiario}" /></td>
				<td><c:out value="${s.apellidosBeneficiario}" /></td>
				<td><c:out value="${s.parentesco}" /></td>

			</tr>

		</table>



		</div>
		<div class="s10"></div>



		<table class="tabla" width="600" cellpadding="2" cellspacing="1"
			border="0">
			<div class="textorange" style="padding-bottom: 5px">Datos de
			Órden de Medicinas</div>
			<div style="overflow: visible">
			<tr class="item"
				>
				<td><bean:message key="general.codigoSiniestro"
					bundle="etiquetas" /></td>
				<td><bean:message key="general.fecha.notificacion"
					bundle="etiquetas" /></td>
				<td><bean:message key="general.tipoenfermedad"
					bundle="etiquetas" /></td>
				<td><bean:message key="general.causaingreso" bundle="etiquetas" /></td>
				<td><bean:message key="general.observacion" bundle="etiquetas" /></td>

			</tr>
			<tr class="item" bgcolor="white">
				<td class="nblack"><c:out value="${s.aniomesCodigo}" /><c:out value="${s.codigo}" /><c:out
					value="${s.subCodigo}" /></td>
				<td><fmt:formatDate value="${s.fechaNotificacion}" type="date"
					pattern="dd/MM/yyyy" /></td>
				<td><c:out value="${s.tipoEnfermedad.descripcion}" /></td>
				<td><c:out value="${s.patologiaOrganoTratamiento.descripcion}" /></td>
				<td><c:out value="${s.observacionMedicinas}" /></td>

			</tr>
			</div>
		</table>
		<table class="tabla" width="600" cellpadding="2" cellspacing="1"
			border="0">
			<tr class="item" align="center" bgcolor="white">
				<td align="right">
				<img src="<%=request.getContextPath()%>/images/printer.png"
					onclick="javascript:openWinScroll2('<%=request.getContextPath()%>/security/comunes/generareporte.do?id=<c:out
								value="${s.id}" />&anio=<c:out
								value="${s.anioSiniestro}" />&id_reporte=1','OrdenDeMedicinas',800,600);">

				</td>
			</tr>
		</table>
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
						'menubar=yes,location=yes,toolbar=no,status=yes,scrollbars=yes,titlebar=no,directories=no,'
								+ size + posicion);
		popUp.opener = self;
	}
</script>