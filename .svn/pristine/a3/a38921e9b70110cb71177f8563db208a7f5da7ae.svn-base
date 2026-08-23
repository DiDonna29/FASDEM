<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<tiles:insert page="/jsp/plantilla/plantilla.jsp">

	<tiles:put name="titulo" direct="true">
		<bean:message key="principal.titulo" bundle="etiquetas" />
	</tiles:put>

	<tiles:put name="titulopagina" content="Consulta de Coberturas" direct="true" />

	<tiles:put name="itemsup" content="/jsp/comunes/items.jsp"/>
	<tiles:put name="itemsdown"  content="" direct="true"   />
	<tiles:put name="cuerpo" direct="true">
	
	<div align="center" class="textorange" style="padding-bottom: 5px">Datos del Beneficiario</div>
	<table class="tabla" width="600" cellpadding="2" cellspacing="1" border="0">
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
			<td><fmt:formatNumber value="${titular.beneficiario.cedula}" groupingUsed="true" /> </td>
			<td><c:out value="${titular.beneficiario.nombres}" /></td>
			<td><c:out value="${titular.beneficiario.apellidos}" /></td>
			<td><fmt:formatDate pattern="dd/MM/yyyy"
				value="${titular.beneficiario.fechaNacimiento}" /></td>
			<td><c:out value="${titular.beneficiario.estadoCivil}" /></td>
			<td><c:out value="${titular.beneficiario.cargo}" /></td>				
			<td><c:out value="${titular.beneficiario.parentesco}" /></td>
			<td><c:out value="${titular.beneficiario.dependencia}" /></td>

		</tr>
	</table>
	
		<div class="s10"></div>
	
	<c:if test="${detallePoliza!=null}">
		<div align="center" class="textorange" style="padding-bottom: 5px">Datos de la póliza</div>
	<table class="tabla" width="600" cellpadding="2" cellspacing="1" border="0">
		<tr class="tituloCabecera">
			
			<td><bean:message key="general.poliza" bundle="etiquetas" /></td>
			<td><bean:message key="general.fecha" bundle="etiquetas" /> Inicio</td>
			<td><bean:message key="general.fechaFin" bundle="etiquetas" /></td>
		</tr>
		<tr class="item" bgcolor="white">
			<td> <c:out value="${detallePoliza.descripcion}"/></td>
			<td><fmt:formatDate value="${detallePoliza.fechaInicio}" pattern="dd/MM/yyyy" /></td>
			<td><fmt:formatDate value="${detallePoliza.fechaFin}" pattern="dd/MM/yyyy" /></td>
			
		</tr>
	</table>
	</c:if>
	<div class="s10"></div>
	
	
	
	<c:choose>
	<c:when test="${detalleCobertura !=null}">
	<div align="center"  class="textorange" style="padding-bottom: 5px">Coberturas</div>
	<table class="tabla" width="600" cellpadding="2" cellspacing="1" border="0">
		<tr class="tituloCabecera">
			
			<td><bean:message key="general.tipoCobertura" bundle="etiquetas" /></td>
			<td align="right"><bean:message key="general.monto" bundle="etiquetas" /></td>
			<td align="right"><bean:message key="general.monto" bundle="etiquetas" /> Agotado</td>
			<td align="right"><bean:message key="general.monto" bundle="etiquetas" /> Disponible</td>
		</tr>
		<c:forEach items="${detalleCobertura}" var="dc">
			<tr class="item" bgcolor="white">
				<td><c:out value="${dc.tipoCobertura.descripcion}" /></td>
				<td align="right"><fmt:formatNumber value="${dc.monto}" groupingUsed="true" maxFractionDigits="2" minFractionDigits="2"/></td>
				<td align="right"><fmt:formatNumber value="${dc.montoAgotada}" groupingUsed="true" maxFractionDigits="2" minFractionDigits="2"/></td>
				<td align="right"><fmt:formatNumber value="${dc.montoDisponible}" groupingUsed="true" maxFractionDigits="2" minFractionDigits="2"/></td>
			</tr>
		</c:forEach>
		<c:forEach items="${detalleCoberturaPorPatologia}" var="dc2">
			<tr class="item" bgcolor="white">
				<td><c:out value="${dc2.tipoCobertura.descripcion} <br> <strong>${dc2.patologia}</strong>" escapeXml="false" /></td>
				<td align="right"><fmt:formatNumber value="${dc2.monto}" groupingUsed="true" maxFractionDigits="2" minFractionDigits="2"/></td>
				<td align="right"><fmt:formatNumber value="${dc2.montoAgotada}" groupingUsed="true" maxFractionDigits="2" minFractionDigits="2"/></td>
				<td align="right"><fmt:formatNumber value="${dc2.montoDisponible}" groupingUsed="true" maxFractionDigits="2" minFractionDigits="2"/></td>
			</tr>
		</c:forEach>
	</table>
	
	

	<table class="tabla" width="600" cellpadding="2" cellspacing="1" border="0">


	</table>
		
	</c:when>
	<c:otherwise>
<div align="center" class="nredp">La Busqueda No Arrojó Resultados</div>
	</c:otherwise>
	</c:choose>

	</tiles:put>
</tiles:insert>