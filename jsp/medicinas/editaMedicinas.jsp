<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>


<tiles:insert page="/jsp/plantilla/plantilla.jsp">
	<tiles:put name="titulo" direct="true">
		<bean:message key="principal.titulo" bundle="etiquetas" />
		<input type="hidden" name="accion" />
	</tiles:put>

	<tiles:put name="titulopagina" content="Inicio / Medicinas / Modificar Medicinas"
		direct="true" />
	<tiles:put name="itemsup" content="/jsp/comunes/items.jsp" />
	<tiles:put name="itemsdown" content="" direct="true" />
	<tiles:put name="cuerpo" direct="true">
		<c:if test="${Op!=null}">

			<div style="overflow: visible">
			<table class="tabla" width="600" cellpadding="2" cellspacing="1"
				border="0">
				<tr class="tituloCabecera">
					<div class="textorange" style="padding-bottom: 5px">Datos del
					Titular</div>
					<td><bean:message key="general.cedula" bundle="etiquetas" /></td>
					<td><bean:message key="general.nombres" bundle="etiquetas" /></td>
					<td><bean:message key="general.apellidos" bundle="etiquetas" /></td>
					<td><bean:message key="general.fechanac" bundle="etiquetas" /></td>

				</tr>
				<tr class="item" bgcolor="white">
					<td><c:out value="${Op.cedula}" /></td>
					<td><c:out value="${Op.nombres}" /></td>
					<td><c:out value="${Op.apellidos}" /></td>
					<td><fmt:formatDate pattern="dd/MM/yyyy"
						value="${Op.fechaNacimiento}" /></td>

				</tr>
			</table><br>
			<table class="tabla" width="600" cellpadding="2" cellspacing="1"
				border="0">
				<tr class="tituloCabecera">
					<div class="textorange" style="padding-bottom: 5px">Datos del
					Asegurado</div>
					<td><bean:message key="general.cedula" bundle="etiquetas" /></td>
					<td><bean:message key="general.nombres" bundle="etiquetas" /></td>
					<td><bean:message key="general.apellidos" bundle="etiquetas" /></td>
					<td><bean:message key="general.fechanac" bundle="etiquetas" /></td>
					<td><bean:message key="general.parentesco" bundle="etiquetas" /></td>

				</tr>
				<tr class="item" bgcolor="white">
					<td><c:out value="${Op.cedulaBeneficiario}" /><input
						type="hidden" name="id" value="<c:out value="${Op.id}" />" /></td>
					<td><c:out value="${Op.nombresBeneficiario}" /></td>
					<td><c:out value="${Op.apellidosBeneficiario}" /></td>
					<td><fmt:formatDate pattern="dd/MM/yyyy"
						value="${Op.fechaNacimientoBeneficiario}" /></td>
					<td><c:out value="${Op.parentesco}" /></td>

				</tr>
			</table>


			<table class="tabla" width="600" cellpadding="2" cellspacing="1"
				border="0">
				<tr class="tituloCabecera">
					<td><bean:message key="general.subCodigo" bundle="etiquetas" /></td>
					<td><bean:message key="general.fechaNotificacion"
						bundle="etiquetas" /></td>
					<td><bean:message key="general.montoPresupuestado"
						bundle="etiquetas" /></td>
					<td><bean:message key="general.montoAmparado"
						bundle="etiquetas" /></td>
					<td><bean:message key="general.tipoenfermedad"
						bundle="etiquetas" /></td>
					<td><bean:message key="general.estatus" bundle="etiquetas" /></td>
					<td><bean:message key="general.causaingreso"
						bundle="etiquetas" /></td>
					<td><bean:message key="general.observacion" bundle="etiquetas" /></td>
					<div class="s10"></div>
				</tr>
				<tr class="item" bgcolor="white">
					<td class="nblack"><c:out value="${Op.aniomesCodigo}" />-<c:out value="${Op.codigo}" />-<c:out
						value="${Op.subCodigo}" /></td>
					<td><fmt:formatDate value="${Op.fechaNotificacion}"
						type="date" pattern="dd/MM/yyyy" /></td>
					<td><fmt:formatNumber
						value="${Op.montoPresupuestado}" pattern='###0.00' /></td>
					<td><fmt:formatNumber
						value="${Op.montoAmparado}" pattern='###0.00' /></td>
					<td><c:out value="${Op.tipoEnfermedad.descripcion}" /></td>
					<td><c:out value="${Op.estatus.descripcion}" /></td>
					<td><c:out
						value="${Op.patologiaOrganoTratamiento.descripcion}" /></td>
					<td><c:out value="${Op.observacion}" /></td></tr>
				
			</table>
			</div>
			
			</c:if>
				<div
			style="border-bottom-color: #EEEEEE; border-bottom-width: thin; border-bottom-style: solid">&nbsp;</div>
		<div class="sp5"></div>
		<div align="right"><input class="boton" value="aceptar"
			type="button" onclick="editar();" /></div>
	</tiles:put>
</tiles:insert>
<script language="JavaScript">
	function editar() {
		document.forms[0].accion.value = 'editar';
		document.forms[0].submit();
	}
</script>

