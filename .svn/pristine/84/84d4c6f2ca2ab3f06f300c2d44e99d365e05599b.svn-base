<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@	taglib uri="/WEB-INF/tlds/pager-taglib.tld" prefix="pg"%>
<tiles:insert page="/jsp/plantilla/plantilla.jsp">
	<tiles:put name="titulo" direct="true">
		<bean:message key="principal.titulo" bundle="etiquetas" />
	</tiles:put>
	<tiles:put name="titulopagina" content="Búsqueda de Siniestros"
		direct="true" />
	<tiles:put name="itemsup" content="/jsp/comunes/items.jsp" />
	<tiles:put name="itemsdown" content=" " direct="true" />
	<tiles:put name="cuerpo" direct="true">
		<c:if test="${titular!=null}">
			<div style="overflow: visible">
			<table class="tabla" cellpadding="2" cellspacing="1" border="0">
				<div class="textorange" style="padding-bottom: 5px">Datos del
				Asegurado</div>
				<tr class="tituloCabecera">
					<td align="left" bgcolor="white" colspan="8"><img width="160"
						height="120"
						src="<%=request.getContextPath()%>/security/foto/downloadFoto.do?cedula=<c:out value="${titular.beneficiario.cedula}" />"></td>
				</tr>
				<tr class="tituloCabecera">
					<td><bean:message key="general.cedula" bundle="etiquetas" /></td>
					<td><bean:message key="general.nombres" bundle="etiquetas" /></td>
					<td><bean:message key="general.apellidos" bundle="etiquetas" /></td>
					<td><bean:message key="general.fechanac" bundle="etiquetas" /></td>
					<td><bean:message key="general.edocivil" bundle="etiquetas" /></td>
					<td><bean:message key="general.cargo" bundle="etiquetas" /></td>
					<td><bean:message key="general.parentesco" bundle="etiquetas" /></td>
					<td><bean:message key="general.estado" bundle="etiquetas" /></td>
				</tr>
				<tr class="item" bgcolor="white"
					onclick="javaScript:submit2('<c:out value="${titular.beneficiario.cedula}" />', '1')">
					<td><c:out value="${titular.beneficiario.cedula}" /></td>
					<td><c:out value="${titular.beneficiario.nombres}" /></td>
					<td><c:out value="${titular.beneficiario.apellidos}" /></td>
					<td><fmt:formatDate pattern="dd/MM/yyyy"
						value="${titular.beneficiario.fechaNacimiento}" /></td>
					<td><c:out value="${titular.beneficiario.estadoCivil}" /></td>
					<td><c:out value="${titular.beneficiario.cargo}" /></td>
					<td><c:out value="${titular.beneficiario.parentesco}" /></td>
					<td><c:out value="${titular.beneficiario.estado}" /></td>
				</tr>
			</table>
			</div>
			<div class="s10"></div>
			<div style="overflow: visible">
			<div class="textorange" style="padding-bottom: 5px">Datos de
			las Coberturas</div>
			<table class="tabla" cellpadding="2" cellspacing="1" border="0">
				<tr class="tituloCabecera">
					<td width="25"><bean:message key="general.descripcion"
						bundle="etiquetas" /></td>
					<td width="25"><bean:message key="general.montoCobertura"
						bundle="etiquetas" /></td>
					<td width="25"><bean:message key="general.coberturaAgotada"
						bundle="etiquetas" /></td>
					<td width="25"><bean:message
						key="general.coberturadisponible" bundle="etiquetas" /></td>
				</tr>
				
				
				
				
				<c:forEach items="${cobertura}" var="cobert">
					
					<c:if test="${cobert.porPatologia==false}">
					
					<tr class="item" bgcolor="white">
						<td width="25"><c:out
							value="${cobert.tipoCobertura.descripcion}" /></td>
						<td width="25" align="right"><fmt:formatNumber minFractionDigits="2"
							maxFractionDigits="2" value="${cobert.monto}" /></td>
						<td width="25" align="right"><fmt:formatNumber minFractionDigits="2"
							maxFractionDigits="2" value="${cobert.montoAgotada}" /></td>
						<td width="25" align="right"><fmt:formatNumber minFractionDigits="2"
							maxFractionDigits="2" value="${cobert.montoDisponible}" /></td>
					</tr>
					</c:if>
					<c:if test="${cobert.porPatologia==true}">
					<tr class="tituloCabecera" >
					  <td colspan="4" ><c:out value="${cobert.tipoCobertura.descripcion}" /></td>
					  </tr>
							<c:forEach items="${cobert.desgloseCobertura}" var="desg">
							<tr class="item" bgcolor="white">
						<td width="25"><c:out
							value="${desg.patologia}" /></td>
						<td width="25" align="right"><fmt:formatNumber minFractionDigits="2"
							maxFractionDigits="2" value="${desg.monto}" /></td>
						<td width="25" align="right"><fmt:formatNumber minFractionDigits="2"
							maxFractionDigits="2" value="${desg.montoAgotada}" /></td>
						<td width="25" align="right"><fmt:formatNumber minFractionDigits="2"
							maxFractionDigits="2" value="${desg.montoDisponible}" /></td>
					</tr>
					</c:forEach>
					</c:if>
				</c:forEach>
			</table>
			</div>
		</c:if>
	</tiles:put>
</tiles:insert>

