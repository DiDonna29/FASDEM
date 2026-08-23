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
			value="${s.aniomesCodigo}-${s.codigo}-${s.subCodigo}" /></div>

		<div class="etiqueta titulo cgp"><bean:message
			key="general.nombres" bundle="etiquetas" /> y <bean:message
			key="general.apellidos" bundle="etiquetas" /></div>
		<div class="parametro titulo "><c:out
			value="${s.nombresBeneficiario}" /> <c:out
			value="${s.apellidosBeneficiario}" /></div>
		<div class="etiqueta titulo cgp"><bean:message
			key="general.proveedor" bundle="etiquetas" /></div>
		<div class="parametro titulo "><c:out
			value="${s.proveedor.descripcion}" /></div>
		<div class="etiqueta titulo cgp"><bean:message
			key="general.fecha.ocurrencia" bundle="etiquetas" /></div>
		<div class="parametro titulo "><fmt:formatDate
			pattern="dd/MM/yyyy" value="${s.fechaOcurrencia}" /></div>
		<div class="etiqueta titulo cgp"><bean:message
			key="general.fechaNotificacion" bundle="etiquetas" /></div>
		<div class="parametro titulo "><fmt:formatDate
			pattern="dd/MM/yyyy" value="${s.fechaNotificacion}" /></div>


		<div class="etiqueta titulo cgp"><bean:message
			key="general.tiposiniestro" bundle="etiquetas" /></div>
		<div class="parametro titulo "><c:out
			value="${s.tipoSiniestro.descripcion}" /></div>

		<div class="etiqueta titulo cgp"><bean:message
			key="general.tipoenfermedad" bundle="etiquetas" /></div>
		<div class="parametro titulo "><c:out
			value="${s.tipoEnfermedad.descripcion}" /></div>
		<div class="etiqueta titulo cgp"><bean:message
			key="general.estatus" bundle="etiquetas" /></div>
		<div class="parametro titulo "><c:out
			value="${s.estatus.descripcion}" /></div>

		<div class="etiqueta titulo cgp"><bean:message
			key="general.tratamiento" bundle="etiquetas" /></div>
		<div
			title="<c:out  value="${s.patologiaOrganoTratamiento.descripcion}"/>"
			class="parametro titulo "><c:out
			value="${s.patologiaOrganoTratamiento.descripcion}" /></div>

		<div class="etiqueta titulo cgp"><bean:message
			key="general.observacion" bundle="etiquetas" /></div>
		<div class="parametro titulo "><c:out value="${s.observacion}" />
		</div>

		<div class="etiqueta titulo cgp"><bean:message
			key="general.tipoTratamiento" bundle="etiquetas" /></div>
		<div class="parametro titulo "><c:out
			value="${s.tipoTratamiento.descripcionTratamiento}" /></div>

		<div class="etiqueta titulo cgp"><bean:message
			key="general.monto" bundle="etiquetas" /></div>
		<div class="parametro titulo "><c:out
			value="${s.montoPresupuestado}" /></div>
		<table class="tabla" width="700" cellpadding="2" cellspacing="1"
			border="0">

			<tr class="item" bgcolor="white">
				<td>
				<div class="etiqueta titulo cgp"><bean:message
					key="general.adjuntos" bundle="etiquetas" /></div>
				<a class="red" href="#"
					onclick="javascript:openWinScroll2('<%=request.getContextPath()%>/security/upload/uploadFile.do?idSiniestro=<c:out value='${s.id}'/>&anio=<c:out	value="${s.anioSiniestro}" />','name',500,500);">Ver
				Adjuntos</a>
				</div>

				</td>
			</tr>



			<tr class="item" bgcolor="white">
				<td>
				<div class="etiqueta titulo cgp"><bean:message
					key="general.notatecnicas" bundle="etiquetas" /></div>
				<div class="parametro titulo cgp"><a class="red" href="#"
					onclick="javascript:openWinScroll2('<%=request.getContextPath()%>/security/notatecnica/saveNotaTecnica.do?idSiniestro=<c:out value='${s.id}'/>&anioSiniestro=<c:out	value="${s.anioSiniestro}" />','name',500,500);">Ver
				Nota Técnica</a></div>
				</td>
			</tr>
			<tr class="item" bgcolor="white">
				<td>
				<div class="etiqueta titulo cgp"><bean:message
					key="general.notaActivo" bundle="etiquetas" /></div>
				<img src="<%=request.getContextPath()%>/images/printer.png"
					onclick="javascript:openWinScroll2('<%=request.getContextPath()%>/security/comunes/generareporte.do?id=<c:out
								value="${s.id}" />&anio=<c:out
								value="${s.anioSiniestro}" />&id_reporte=11','NotaTecnicaUsuarioActivo',800,600);">
				</td>
			</tr>
			<tr class="item" bgcolor="white">
				<td>
				<div class="etiqueta titulo cgp"><bean:message
					key="general.notaSiniestro" bundle="etiquetas" /></div>
				<img src="<%=request.getContextPath()%>/images/printer.png"
					onclick="javascript:openWinScroll2('<%=request.getContextPath()%>/security/comunes/generareporte.do?id=<c:out
								value="${s.id}" />&anio=<c:out
								value="${s.anioSiniestro}" />&id_reporte=12','NotaTecnicaSiniestro',800,600);">

				</td>
			</tr>
			<c:if test="${s.tipoTramite.id==6&&s.estatus.id==9}">
				<tr class="item" bgcolor="white">
					<td>
					<div class="etiqueta titulo cgp"><bean:message
						key="general.ordenMedicinas" bundle="etiquetas" /></div>
					<img src="<%=request.getContextPath()%>/images/printer.png"
						onclick="javascript:openWinScroll2('<%=request.getContextPath()%>/security/comunes/generareporte.do?id=<c:out
								value="${s.id}" />&anio=<c:out
								value="${s.anioSiniestro}" />&id_reporte=1','OrdenDeMedicinas',800,600);">
					</td>
				</tr>
			</c:if>


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
						'menubar=yes,location=no,toolbar=no,status=no,scrollbars=no,titlebar=no,directories=no,resizable=no'
								+ size + posicion);
		popUp.opener = self;

	}
</script>
