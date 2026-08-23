<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<tiles:insert page="/jsp/plantilla/plantilla.jsp">

	<tiles:put name="titulo" direct="true">
		<bean:message key="principal.titulo" bundle="etiquetas" />
	</tiles:put>

	<tiles:put name="titulopagina" content="Inicio / Carta Aval / Liquidación de Cartas Avales"
		direct="true" />
	<tiles:put name="itemsup" content="/jsp/comunes/items.jsp" />
	<tiles:put name="itemsdown" content=" " direct="true" />
	<tiles:put name="cuerpo" direct="true">
		<c:if test="${Op!=null}">

			<div style="overflow: visible;">
			<table class="tabla" cellpadding="2" cellspacing="1"
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
			<table class="tabla"  cellpadding="2" cellspacing="1"
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


			<table class="tabla" cellpadding="2" cellspacing="1"
				border="0">
				<tr class="tituloCabecera">
					<td><bean:message key="general.subCodigo" bundle="etiquetas" /></td>
					<td><bean:message key="general.fechaNotificacion"
						bundle="etiquetas" /></td>
					<td><bean:message key="general.montoPresupuestado"
						bundle="etiquetas" /></td>
					<td><bean:message key="general.montoAmparado"
						bundle="etiquetas" /></td>
					<td><bean:message key="general.tipoTratamiento"
						bundle="etiquetas" /></td>
					<td><bean:message key="general.tipoSiniestro" bundle="etiquetas" /></td>
					<td><bean:message key="general.causaingreso"
						bundle="etiquetas" /></td>
					
					<div class="s10"></div>
				</tr>
				<tr class="item" bgcolor="white">
					<td><c:out value="${Op.aniomes}" /><c:out value="${Op.codigo}" /><c:out
						value="${Op.subCodigo}" /></td>
					<td><fmt:formatDate value="${Op.fechaNotificacion}"
						type="date" pattern="dd/MM/yyyy" /></td>
					<td><fmt:formatNumber
						value="${Op.montoAmparado}" pattern='###0.00' /><input
						type="hidden" name="id" value="<c:out value="${Op.id}" />" /></td>
					<td><fmt:formatNumber
						value="${Op.montoAmparado}" pattern='###0.00' /></td>
					<td><c:out value="${Op.tipoTratamiento.descripcionTratamiento}" /></td>
					<td><c:out value="${Op.tipoSiniestro.descripcion}" /></td>
					<td><c:out
						value="${Op.patologiaOrganoTratamiento.descripcion}" /></td>
					</tr>
				
			</table>
			</div>
			
			</c:if>
	</tiles:put>
</tiles:insert>
