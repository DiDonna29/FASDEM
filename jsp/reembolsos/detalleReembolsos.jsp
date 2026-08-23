
<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<tiles:insert page="/jsp/plantilla/plantilla.jsp">

	<tiles:put name="titulo" direct="true">
		<bean:message key="principal.titulo" bundle="etiquetas" />
	</tiles:put>

	<tiles:put name="titulopagina" content="Inicio / Reembolsos / Datos del Siniestro" direct="true" />
	<tiles:put name="itemsup" content=" " direct="true" />
	<tiles:put name="itemsdown" content=" " direct="true" />
	<tiles:put name="cuerpo" direct="true">
		<br>


		<div class="s10"></div>
		<div class="textorange" style="padding-bottom: 5px"><bean:message key="general.titular" bundle="etiquetas" /></div>
		<div style="overflow: visible">
		<table class="tabla" width="600" cellpadding="2" cellspacing="1" border="0">
			<tr class="tituloCabecera">
				<td><bean:message key="general.cedula" bundle="etiquetas" /></td>
				<td><bean:message key="general.nombres" bundle="etiquetas" /></td>
				<td><bean:message key="general.apellidos" bundle="etiquetas" /></td>


			</tr>
			<tr class="item" bgcolor="white" onclick="javaScript:submit2('<c:out value="${s}" />', '1')">
				<td><c:out value="${s.cedula}" /></td>
				<td><c:out value="${s.nombres}" /></td>
				<td><c:out value="${s.apellidos}" /></td>

			</tr>


		</table>
		<div class="s10"></div>
		<div class="textorange" style="padding-bottom: 5px"><bean:message key="general.beneficiario" bundle="etiquetas" /></div>
		<div style="overflow: visible">
		<table class="tabla" width="600" cellpadding="2" cellspacing="1" border="0">
			<tr class="tituloCabecera">
				<td><bean:message key="general.cedula" bundle="etiquetas" /></td>
				<td><bean:message key="general.nombres" bundle="etiquetas" /></td>
				<td><bean:message key="general.apellidos" bundle="etiquetas" /></td>
				<td><bean:message key="general.parentesco" bundle="etiquetas" /></td>


			</tr>
			<tr class="item" bgcolor="white" onclick="javaScript:submit2('<c:out value="${s}" />', '1')">
				<td><c:out value="${s.cedulaBeneficiario}" /></td>
				<td><c:out value="${s.nombresBeneficiario}" /></td>
				<td><c:out value="${s.apellidosBeneficiario}" /></td>
				<td><c:out value="${s.parentesco}" /></td>

			</tr>

		</table>



		</div>
		<div class="s10"></div>



		<table class="tabla" width="600" cellpadding="2" cellspacing="1" border="0">
			<div class="textorange" style="padding-bottom: 5px">Datos del Reembolso</div>
			<div style="overflow: visible">
			<tr class="item">
				<td><bean:message key="general.codigoSiniestro" bundle="etiquetas" /></td>
				<td><bean:message key="general.fecha.notificacion" bundle="etiquetas" /></td>
				<td><bean:message key="general.tipoenfermedad" bundle="etiquetas" /></td>
				<td><bean:message key="general.estatus" bundle="etiquetas" /></td>
				<td><bean:message key="general.causaingreso" bundle="etiquetas" /></td>


			</tr>
			<tr class="item" bgcolor="white">
				<td><c:out value="${s.aniomesCodigo}" /><c:out value="${s.codigo}" /><c:out value="${s.subCodigo}" /></td>
				<td><fmt:formatDate value="${s.fechaNotificacion}" type="date" pattern="dd/MM/yyyy" /></td>
				<td><c:out value="${s.tipoEnfermedad.descripcion}" /></td>
				<c:if test="${siniestro.estatus.id!=34}">
					<td><c:out value="${s.estatus.descripcion}" /> <c:if
						test="${tipoImpresion!=null}">
						<img src="<%=request.getContextPath()%>/images/printer.png"
							onclick="javascript:openWinScroll2('<%=request.getContextPath()%>/security/comunes/generareporte.do?id=<c:out value="${s.id}" />&id_reporte=<c:out  value="${tipoImpresion}"/>&anio=<c:out	value="${s.anioSiniestro}" />','Reembolso',800,600);">
					</c:if></td>
				</c:if>
				<c:if test="${siniestro.estatus.id==34}">
					<td><c:out value="${s.estatus.descripcion}" /></td>
				</c:if>
				<td><c:out value="${s.patologiaOrganoTratamiento.descripcion}" /></td>
			</tr>
			</div>
		</table>
		<c:if test="${s.estatus.id==2||s.estatus.id==1}">
			<table class="tabla" width="700" cellpadding="2" cellspacing="1" border="0">
				<div class="etiqueta titulo cgp"><bean:message key="general.notaTecnica" bundle="etiquetas" /></div>
				<div class="parametro titulo cgp"><a class="red" href="#" onclick="javascript:openWinScroll2('<%=request.getContextPath()%>/security/notatecnica/saveNotaTecnica.do?idSiniestro=<c:out value='${s.id}'/>&anioSiniestro=<c:out	value="${s.anioSiniestro}" />','name',500,500);">Nueva Nota Técnica</a></div>
				<div class="etiqueta titulo cgp"><bean:message key="general.archivos" bundle="etiquetas" /></div>
				<div class="parametro titulo cgp"><a class="red" href="#" onclick="javascript:openWinScroll2('<%=request.getContextPath()%>/security/upload/uploadFile.do?idSiniestro=<c:out value='${s.id}'/>&anio=<c:out	value="${s.anioSiniestro}" />','name',500,500);">Cargar Adjuntos</a></div>
				<tr class="item" bgcolor="white">
					<td>
					<div class="etiqueta titulo cgp"><bean:message key="general.notaSiniestro" bundle="etiquetas" /></div>
					<img src="<%=request.getContextPath()%>/images/printer.png" onclick="javascript:openWinScroll2('<%=request.getContextPath()%>/security/comunes/generareporte.do?id=<c:out
						value="${s.id}" />&id_reporte=14&anio=<c:out	value="${s.anioSiniestro}" />','NotaTecnicaSiniestroRmbls',800,600);"></td>
				</tr>
			</table>
		</c:if> <c:if test="${s.estatus.id!=9||s.estatus.id!=4}">
			<table class="tabla" width="700" cellpadding="2" cellspacing="1" border="0">
				<tr class="item" bgcolor="white">
					<td align="center">El reporte de Nota de Cobertura solo se puede imprimir cuando su estatus es Egresado o Liquidado.</td>
				</tr>
			</table>
		</c:if> <c:if test="${s.estatus.id==35}">
			<table class="tabla" width="700" cellpadding="2" cellspacing="1" border="0">
				<tr class="item" bgcolor="white">
					<td align="center">Haga click aqui para imprimir el reporte de Rechazo.</td>
				</tr>
			</table>
		</c:if> <c:if test="${s.estatus.id==2}">
			<table class="tabla" width="700" cellpadding="2" cellspacing="1" border="0">
				<tr class="item" bgcolor="white">
					<td align="center">Haga click aqui para imprimir el reporte de Solicitud de Recaudos.</td>
				</tr>
			</table>
		</c:if>
		<c:if test="${s.estatus.id==2||s.estatus.id==1}">
		<table class="tabla" width="600" cellpadding="2" cellspacing="1" border="0">
			<tr class="item" align="center" bgcolor="white">
				<td align="right"><input type="hidden" id="id" name="id" value="<c:out value="${s.id}" />" /> <input type="hidden" id="anioBusqueda" name="anioBusqueda" value="<c:out value="${s.anioSiniestro}" />" /> <input onclick="javaScript:submit2('<c:out value="${s.id}" />','<c:out  value="${s.anioSiniestro}" />');" type="button" value="liquidar" class="boton" /></td>

			</tr>
		</table>
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
						'menubar=yes,location=yes,toolbar=no,status=yes,scrollbars=yes,titlebar=no,directories=no,'
								+ size + posicion);
		popUp.opener = self;
	}
	
	function submit2(id,anioBusqueda){
		document.getElementById("id").value=id;
		document.getElementById("anioBusqueda").value=anioBusqueda;
		document.forms[0].action='<%=request.getContextPath()%>/security/aps/liquidacion/liquidarAps.do';
		document.forms[0].submit();
	}
	
	
</script>
