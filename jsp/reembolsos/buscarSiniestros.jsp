<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<tiles:insert page="/jsp/plantilla/plantilla.jsp">

	<tiles:put name="titulo" direct="true">
		<bean:message key="principal.titulo" bundle="etiquetas" />
	</tiles:put>

	<tiles:put name="titulopagina" content="Inicio / Reembolsos / Busqueda de Siniestros"
		direct="true" />
	<tiles:put name="itemsup" content="/jsp/comunes/items.jsp" />
    <tiles:put name="itemsdown" content=" " direct="true" />
	<tiles:put name="cuerpo" direct="true">
		<c:if test="${persona!=null}">
			<div class="s10"></div>
			<div class="textorange" style="padding-bottom: 5px"><bean:message key="general.asegurado" bundle="etiquetas" /></div>
			<div style="overflow: visible">
			<table class="tabla" width="600" cellpadding="2" cellspacing="1"
				border="0">
				<tr class="tituloCabecera">
					<td><bean:message key="general.cedula" bundle="etiquetas" /></td>
					<td><bean:message key="general.nombres" bundle="etiquetas" /></td>
					<td><bean:message key="general.apellidos" bundle="etiquetas" /></td>
					<td><bean:message key="general.fecha.nac" bundle="etiquetas" /></td>
					<td><bean:message key="general.edocivil" bundle="etiquetas" /></td>
					<td><bean:message key="general.cargo" bundle="etiquetas" /></td>
					<td><bean:message key="general.telefono" bundle="etiquetas" /></td>
					<td><bean:message key="general.estado" bundle="etiquetas" /></td>
				</tr>
				<tr class="item" bgcolor="white"
					onclick="javaScript:submit2('<c:out value="${persona.idBeneficiario}" />', '1')">
					<td><c:out value="${persona.cedula}" /></td>
					<td><c:out value="${persona.nombres}" /></td>
					<td><c:out value="${persona.apellidos}" /></td>
					<td><fmt:formatDate pattern="dd/MM/yyyy"
						value="${persona.fechaNacimiento}" /></td>
					<td><c:out value="${persona.estadoCivil}" /></td>
					<td><c:out value="${persona.cargo}" /></td>
					<td><c:out value="${persona.telefono}" /></td>
					<td><c:out value="${persona.estado}" /></td>
				</tr>
				<tr class="cgp" bgcolor="white">
					<td colspan="6"></td>
					<td align="right" colspan="2"><a class="nredp" href="<%=request.getContextPath()%>/security/reembolsos/nuevoRmbls.do">Nuevo Reembolso</a></td>

				</tr>				
			</table>
			</div>
			<div class="s10"></div>
			
			
			<div class="textorange" style="padding-bottom: 5px">Listado de Siniestros</div>
			<div style="overflow: visible">
			<table class="tabla" width="600" cellpadding="2" cellspacing="1"
				border="0">
				<tr class="tituloCabecera">
					<td>Código</td>
					<td>Fecha Ocurrencia</td>
					<td>Fecha Notif.</td>
					<td>Tipo Siniestro</td>
					<td>Monto</td>
				</tr>
				<tr class="item" bgcolor="white">
					<td>Simulado</td>
					<td>Simulado</td>
					<td>Simulado</td>
					<td>Simulado</td>
					<td>Simulado</td>
				</tr>
				<tr class="item" bgcolor="white">
					<td>Simulado</td>
					<td>Simulado</td>
					<td>Simulado</td>
					<td>Simulado</td>
					<td>Simulado</td>
				</tr>				
			</table>
			</div>
			
			
		</c:if>
	</tiles:put>
</tiles:insert>


