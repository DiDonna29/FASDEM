<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<tiles:insert page="/jsp/plantilla/plantilla.jsp">

	<tiles:put name="titulo" direct="true">
		<bean:message key="principal.titulo" bundle="etiquetas" />
	</tiles:put>

	<tiles:put name="titulopagina" content="Inicio / Liquidación / Liquidación de Reembolsos"
		direct="true" />
	<tiles:put name="itemsup" content="/jsp/comunes/items.jsp" />
	<tiles:put name="itemsdown" content=" " direct="true" />
	<tiles:put name="cuerpo" direct="true">
		<c:if test="${Op!=null}">
			<table class="tabla" width="600" cellpadding="2" cellspacing="1"
				border="0">
				<tr class="tituloCabecera">
			<div class="textorange" style="padding-bottom: 5px">Datos del
					Siniestro</div>
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
					
					<div class="s10"></div>
				</tr>
				<tr class="item" bgcolor="white">
					<td><c:out value="${Op.aniomesCodigo}" /><c:out value="${Op.codigo}" /><c:out
						value="${Op.subCodigo}" /></td>
					<td><fmt:formatDate value="${Op.fechaNotificacion}"
						type="date" pattern="dd/MM/yyyy" /></td>
					<td><fmt:formatNumber
						value="${Op.montoPresupuestado}" pattern='###0.00' /><input
						type="hidden" name="id" value="<c:out value="${Op.id}" />" /></td>
					<td><fmt:formatNumber
						value="${Op.montoAmparado}" pattern='###0.00' /></td>
					<td><c:out value="${Op.tipoEnfermedad.descripcion}" /></td>
					<td><c:out value="${Op.estatus.descripcion}" /></td>
					<td><c:out
						value="${Op.patologiaOrganoTratamiento.descripcion}" /></td>
					</tr>
				<tr class="item" bgcolor="white"><td><div
		style="border-bottom-color: #EEEEEE; border-bottom-width: thin; border-bottom-style: solid"></div>
	
	<div align="center"><input class="boton" value="Liquidar" onclick="Submit2();"
		type="submit" id=<c:out value="${s.id}" /> anio=<c:out	value="${s.anioSiniestro}" />' /></div></td></tr>
			</table>
			</div>
			
			</c:if>
	</tiles:put>
</tiles:insert>
<script>
function Submit2(){
	document.forms[0].action='<%=request.getContextPath()%><c:out value='${myhref}'/>';
	document.forms[0].submit();
}
</script>