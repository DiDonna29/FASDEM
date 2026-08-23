<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<tiles:insert page="/jsp/plantilla/plantilla.jsp">

	<tiles:put name="titulo" direct="true">
		<bean:message key="principal.titulo" bundle="etiquetas" />
	</tiles:put>

	<tiles:put name="titulopagina" content="Detalle del Siniestro" direct="true" />
	<tiles:put name="itemsup" content=""  direct="true" />
    <tiles:put name="itemsdown" content=""  direct="true"/>
    
    
	<tiles:put name="cuerpo" direct="true">
	
		<div class="etiqueta titulo cgp">
			<bean:message key="general.cobertura" bundle="etiquetas" />
		</div>
		<div class="parametro titulo ">
			<c:out value="${siniestro.cobertura.tipoCobertura.descripcion}"/>
		</div>
			
		<div class="etiqueta titulo cgp">
			<bean:message key="general.subCodigo" bundle="etiquetas" />
		</div>
		<div class="parametro titulo ">
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

				
		<div class="etiqueta titulo cgp"><bean:message key="general.notaTecnica" bundle="etiquetas" /></div>
		<div class="parametro titulo cgp"><a class="red" href="#"
			onclick="javascript:openWinScroll2('<%=request.getContextPath()%>/security/notatecnica/saveNotaTecnica.do?idSiniestro=<c:out value='${siniestro.id}'/>&anioSiniestro=<c:out	value="${siniestro.anioSiniestro}" />','name',500,500);">Nueva Nota Técnica</a>
		</div>
		<div class="etiqueta titulo cgp"><bean:message key="general.archivos" bundle="etiquetas" /></div>
		<div class="parametro titulo cgp"><a class="red" href="#"
			onclick="javascript:openWinScroll2('<%=request.getContextPath()%>/security/upload/viewFile.do?idSiniestro=<c:out value='${siniestro.id}'/>','name',500,500);">Ver Adjuntos</a>
		</div>

		<div class="etiqueta titulo cgp">Última <bean:message key="general.notaTecnica" bundle="etiquetas" /></div>
		<div class="parametro titulo cgp"><span><c:out  value="${siniestro.notaTecnica.observacion}"/></span><br/><br/>
		 <strong><c:out  value="${siniestro.notaTecnica.desUsuario}" default="No hay notas técnicas cargadas para este siniestro"/></strong>
		</div>
		<c:if test="${siniestro.estatus.id==9||siniestro.estatus.id==4}">
			<table class="tabla" width="700" cellpadding="2" cellspacing="1"
				border="0">
				<tr class="item" bgcolor="white">
					<td><div class="etiqueta titulo cgp"><bean:message
			key="general.notaCobertura"   bundle="etiquetas" /></div> <img
						src="<%=request.getContextPath()%>/images/printer.png"
						onclick="javascript:openWinScroll2('<%=request.getContextPath()%>/security/comunes/generareporte.do?id=<c:out
								value="${siniestro.id}" />&anio=<c:out
								value="${siniestro.anioSiniestro}" />&id_reporte=10','NotaCobertura',800,600);">

					</td>
				</tr>
			</table>
		</c:if>
		
		<c:if test="${siniestro.estatus.id!=9||siniestro.estatus.id!=4}">
			<table class="tabla" width="700" cellpadding="2" cellspacing="1"
				border="0">
				<tr class="item" bgcolor="white">
					<td align="center">El reporte de Nota de Cobertura solo se
					puede imprimir cuando su estatus es Egresado o Liquidado.</td>
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
						'menubar=yes,location=no,toolbar=no,status=no,scrollbars=yes,titlebar=no,directories=no,resizable=no'
								+ size + posicion);
		popUp.opener = self;

	}
</script>
