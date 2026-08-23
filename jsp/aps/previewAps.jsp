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
			<c:out value="${siniestro.cobertura.tipoCobertura.descripcion}:"/> <fmt:formatNumber   groupingUsed="true" value="${siniestro.cobertura.monto}"  /> Bs.
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
		<div class="etiqueta titulo cgp"><bean:message key="general.archivos" bundle="etiquetas" /></div>
		<div class="parametro titulo cgp"><a class="red" href="#"
			onclick="javascript:openWinScroll2('<%=request.getContextPath()%>/security/upload/uploadFile.do?idSiniestro=<c:out value='${siniestro.id}'/>&anio=<c:out	value="${siniestro.anioSiniestro}" />','name',500,500);">Adjuntar Archivos</a>
		</div>
		
		<div style="clear: both;">
		<table width="100%">
		
			<c:if test="${siniestro.estatus.id==9||siniestro.estatus.id==4}">
				<tr class="item" bgcolor="white">
					<td>

					<div class="etiqueta titulo cgp"><bean:message
						key="general.notaCobertura" bundle="etiquetas" /></div>
					<img src="<%=request.getContextPath()%>/images/printer.png"
						onclick="javascript:openWinScroll2('<%=request.getContextPath()%>/security/comunes/generareporte.do?id=<c:out
								value="${siniestro.id}" />&anio=<c:out
								value="${siniestro.anioSiniestro}" />&id_reporte=10','NotaCobertura',800,600);">
					</td>
				</tr>
			</c:if>

			<tr class="item" bgcolor="white">
				<td>
				<div class="etiqueta titulo cgp"><bean:message
					key="general.notaActivo" bundle="etiquetas" /></div>
				<img src="<%=request.getContextPath()%>/images/printer.png"
					onclick="javascript:openWinScroll2('<%=request.getContextPath()%>/security/comunes/generareporte.do?id=<c:out
								value="${siniestro.id}" />&anio=<c:out 
								value="${siniestro.anioSiniestro}" />&id_reporte=11','NotaTecnicaUsuarioActivo',800,600);">
				</td>
			</tr>
			<tr class="item" bgcolor="white">
				<td>
				<div class="etiqueta titulo cgp"><bean:message
					key="general.notaSiniestro" bundle="etiquetas" /></div>
				<img src="<%=request.getContextPath()%>/images/printer.png"
					onclick="javascript:openWinScroll2('<%=request.getContextPath()%>/security/comunes/generareporte.do?id=<c:out
								value="${siniestro.id}" />&anio=<c:out
								value="${siniestro.anioSiniestro}" />&id_reporte=12','NotaTecnicaSiniestro',800,600);">

				</td>
			</tr>
		
		</table>
		
		</div>
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

