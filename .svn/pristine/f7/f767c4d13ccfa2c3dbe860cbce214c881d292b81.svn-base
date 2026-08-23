<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<tiles:insert page="/jsp/plantilla/plantilla.jsp">

	<tiles:put name="titulo" direct="true">
		<bean:message key="principal.titulo" bundle="etiquetas" />
	</tiles:put>

	<tiles:put name="titulopagina" content="Cambio de estatus" direct="true" />
	<tiles:put name="itemsup" content=""  direct="true" />
    <tiles:put name="itemsdown" content="/jsp/comunes/items.jsp"  />
    
    
	<tiles:put name="cuerpo" direct="true">
		<div class="etiqueta titulo cgp">
			<bean:message key="general.cobertura" bundle="etiquetas" />
		</div>
		<div class="parametro titulo ">
			<c:out value="${siniestro.cobertura.tipoCobertura.descripcion}:"/> <fmt:formatNumber   groupingUsed="true" value="${siniestro.cobertura.monto}"  /> Bs.
		</div>
				
		<div class="etiqueta titulo cgp">
			<bean:message key="general.subCodigo" bundle="etiquetas" />
		</div>
		<div class="parametro titulo ">
		<input type="hidden" name="idSini" name="idSini" value="<c:out value="${siniestro.id}"/>" />
			<c:out value="${siniestro.aniomesCodigo}${siniestro.codigo}${siniestro.subCodigo}"/>
		</div>
		
		<div class="etiqueta titulo cgp">
			<bean:message key="general.nombres" bundle="etiquetas" /> y  
			<bean:message key="general.apellidos" bundle="etiquetas" />
		</div>
		<div class="parametro titulo ">
			<c:out value="${siniestro.nombresBeneficiario}"/> <c:out value="${siniestro.apellidosBeneficiario}"/>
		</div>
		
		<div class="etiqueta titulo cgp">
			<bean:message key="general.fecha.ocurrencia" bundle="etiquetas" />
		</div>
		<div class="parametro titulo ">
			<fmt:formatDate pattern="dd/MM/yyyy" value="${siniestro.fechaOcurrencia}"/>
		</div>
		
		<div class="etiqueta titulo cgp">
			<bean:message key="general.fechaNotificacion" bundle="etiquetas" />
		</div>
		<div class="parametro titulo ">
			<fmt:formatDate pattern="dd/MM/yyyy" value="${siniestro.fechaNotificacion}"/>
		</div>	

		<div class="etiqueta titulo cgp">
			<bean:message key="general.tiposiniestro" bundle="etiquetas" />
		</div>
		<div class="parametro titulo ">
			<c:out  value="${siniestro.tipoSiniestro.descripcion}"/>
		</div>
		

		
		
		<div class="etiqueta titulo cgp">
			<bean:message key="general.tipoenfermedad" bundle="etiquetas" />
		</div>
		<div class="parametro titulo ">
			<c:out value="${siniestro.tipoEnfermedad.descripcion}"/>
		</div>
	
		<div class="etiqueta titulo cgp">
			<bean:message key="general.tratamiento" bundle="etiquetas" />
		</div>
		<div title="<c:out  value="${siniestro.patologiaOrganoTratamiento.descripcion}"/>" class="parametro titulo ">
			<c:out  value="${siniestro.patologiaOrganoTratamiento.descripcion}"/>
		</div>

		<div class="etiqueta titulo cgp">
			<bean:message key="general.observacion" bundle="etiquetas" />
		</div>
		<div  class="parametro titulo ">
			<c:out  value="${siniestro.observacion}"/>
		</div>

		<div class="etiqueta titulo cgp">
			<bean:message key="general.monto" bundle="etiquetas" />
		</div>
		<div  class="parametro titulo ">
			<c:out  value="${siniestro.montoPresupuestado}"/>
		</div>
	</tiles:put>
</tiles:insert>


