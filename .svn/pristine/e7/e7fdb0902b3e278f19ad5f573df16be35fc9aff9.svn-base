<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>

<tiles:insert page="/jsp/plantilla/plantilla.jsp">
  <tiles:put name="titulo" direct="true">
    <bean:message key="principal.titulo" bundle="etiquetas" />
  </tiles:put>
  <tiles:put name="titulopagina" content="Inicio / Carta Aval / Detalle del Siniestro" direct="true" />
  <tiles:put name="itemsup" content=""  direct="true" />
  <tiles:put name="itemsdown" content=""  direct="true"/>
  <tiles:put name="cuerpo" direct="true">
   <div class="textorange" style="padding-bottom: 5px">Detalles
      del Siniestro</div>
    <div class="etiqueta titulo cgp">
      <bean:message key="general.nombres" bundle="etiquetas" />
      y
      <bean:message key="general.apellidos" bundle="etiquetas" />
    </div>
    <div class="parametro titulo ">
      <c:out value="${siniestro.nombresBeneficiario}"/>
      <c:out value="${siniestro.apellidosBeneficiario}"/>
    </div>
    <div class="etiqueta titulo cgp">
      <bean:message key="general.subCodigo" bundle="etiquetas" />
    </div>
    <div class="nblack">
      <c:out value="${siniestro.aniomesCodigo}-${siniestro.codigo}-${siniestro.subCodigo}"/>
    </div>
    <div class="etiqueta titulo cgp">
      <bean:message key="general.cobertura" bundle="etiquetas" />
    </div>
    <div class="parametro titulo ">
      <c:out value="${siniestro.cobertura.tipoCobertura.descripcion}"/>
    </div>
    <div class="etiqueta titulo cgp">
      <bean:message key="general.tipoProveedor" bundle="etiquetas" />
    </div>
    <div class="parametro titulo ">
      <c:out value="${siniestro.proveedor.tipoProveedor.descripcion}"/>
    </div>
    <div class="etiqueta titulo cgp">
      <bean:message key="general.proveedor" bundle="etiquetas" />
    </div>
    <div class="parametro titulo ">
      <c:out value="${siniestro.proveedor.descripcion}"/>
    </div>
    <div class="etiqueta titulo cgp">
      <bean:message key="general.fechaNotificacion" bundle="etiquetas" />
    </div>
    <div class="parametro titulo ">
      <fmt:formatDate pattern="dd/MM/yyyy" value="${siniestro.fechaNotificacion}"/>
    </div>
    <div class="etiqueta titulo cgp">
      <bean:message key="general.estatus" bundle="etiquetas" />
    </div>
    <div class="parametro titulo ">
      <c:out  value="${siniestro.estatus.descripcion}"/>
      <c:if test="${tipoImpresion!=null}"> <img src="<%=request.getContextPath()%>/images/printer.png"
					onclick="javascript:openWinScroll2('<%=request.getContextPath()%>/security/comunes/generareporte.do?id=<c:out value="${siniestro.id}" />&id_reporte=<c:out  value="${tipoImpresion}"/>&anio=<c:out	value="${siniestro.anioSiniestro}" />','CartaAval',800,600);"></c:if>
    </div>
    <c:if test="${siniestro.fechaOcurrencia!=null}">
      <div class="etiqueta titulo cgp">
        <bean:message key="general.fecha.ocurrencia" bundle="etiquetas" />
      </div>
      <div class="parametro titulo ">
        <fmt:formatDate pattern="dd/MM/yyyy" value="${siniestro.fechaOcurrencia}"/>
      </div>
    </c:if>
    <c:if test="${siniestro.fechaEgreso!=null}">
      <div class="etiqueta titulo cgp">
        <bean:message key="general.fechaEgreso" bundle="etiquetas" />
      </div>
      <div class="parametro titulo ">
        <fmt:formatDate pattern="dd/MM/yyyy" value="${siniestro.fechaEgreso}"/>
      </div>
    </c:if>
    <c:if test="${siniestro.fechaLiquidacion!=null}">
      <div class="etiqueta titulo cgp">
        <bean:message key="general.fechaLiquidacion" bundle="etiquetas" />
      </div>
      <div class="parametro titulo ">
        <fmt:formatDate pattern="dd/MM/yyyy" value="${siniestro.fechaLiquidacion}"/>
      </div>
    </c:if>
    <div class="etiqueta titulo cgp">
      <bean:message key="general.tiposiniestro" bundle="etiquetas" />
    </div>
    <div class="parametro titulo ">
      <c:out  value="${siniestro.tipoSiniestro.descripcion}"/>
    </div>
    <div class="etiqueta titulo cgp">
      <bean:message key="general.tipoTratamiento" bundle="etiquetas" />
    </div>
    <div class="parametro titulo ">
      <c:out value="${siniestro.tipoTratamiento.descripcionTratamiento}"/>
    </div>
    <div class="etiqueta titulo cgp">
      <bean:message key="general.tratamiento" bundle="etiquetas" />
    </div>
    <div title="<c:out  value="${siniestro.patologiaOrganoTratamiento.descripcion}"/>
    " class="parametro titulo ">
    <c:out  value="${siniestro.patologiaOrganoTratamiento.descripcion}"/>
    </div>
    <div class="etiqueta titulo cgp">
      <bean:message key="general.observacion" bundle="etiquetas" />
    </div>
    <div  class="parametro titulo ">
      <c:out  value="${siniestro.observacion}"/>
    </div>
    <div class="etiqueta titulo cgp">
      <bean:message key="general.citaPreOperatorio" bundle="etiquetas" />
    </div>
    <div  class="parametro titulo ">
      <c:if test="${siniestro.citaPreOperatorio==true}"> SI </c:if>
      <c:if test="${siniestro.citaPreOperatorio!=true}"> NO </c:if>
    </div>
    <div class="etiqueta titulo cgp">
      <bean:message key="general.citaPostOperatorio" bundle="etiquetas" />
    </div>
    <div  class="parametro titulo ">
      <c:if test="${siniestro.citaPostOperatorio==true}"> SI </c:if>
      <c:if test="${siniestro.citaPostOperatorio!=true}"> NO </c:if>
    </div>

    
    <div class="etiqueta titulo cgp">
      <bean:message key="general.negociado.solo" bundle="etiquetas" />
    </div>
    <div  class="parametro titulo ">
      <fmt:formatNumber maxFractionDigits="2"
				minFractionDigits="2" groupingUsed="true"
				value="${siniestro.montoNegociado}" />
                  Bs.</div>
   
    <div class="etiqueta titulo cgp">
      <bean:message key="general.monto.amparado" bundle="etiquetas" />
    </div>
    <div  class="parametro titulo ">
      <fmt:formatNumber maxFractionDigits="2"
				minFractionDigits="2" groupingUsed="true"
				value="${siniestro.montoAmparado}" />
                  Bs.</div>
    
    <div class="etiqueta titulo cgp">
      <bean:message key="general.monto.noamparado" bundle="etiquetas" />
    </div>
    <div  class="parametro titulo ">
      <fmt:formatNumber maxFractionDigits="2"
				minFractionDigits="2" groupingUsed="true"
				value="${siniestro.montoNoAmparado}" />
                  Bs.</div>
    
    
    <div class="etiqueta titulo cgp">
      <bean:message key="general.notaTecnica" bundle="etiquetas" />
    </div>
    <div class="parametro titulo cgp"><a class="red" href="#"
			onclick="javascript:openWinScroll2('<%=request.getContextPath()%>/security/notatecnica/saveNotaTecnica.do?idSiniestro=<c:out value='${siniestro.id}'/>&anioSiniestro=<c:out	value="${siniestro.anioSiniestro}" />','name',500,500);">Nueva Nota Técnica</a> </div>
            
            <div class="etiqueta titulo cgp"><bean:message
					key="general.notaActivo" bundle="etiquetas" /></div>
					
					
					<div  class="parametro titulo ">
				<img src="<%=request.getContextPath()%>/images/printer.png"
					onclick="javascript:openWinScroll2('<%=request.getContextPath()%>/security/comunes/generareporte.do?id=<c:out
								value="${siniestro.id}" />&anio=<c:out
								value="${siniestro.anioSiniestro}" />&id_reporte=11','NotaTecnicaUsuarioActivo',800,600);">
            </div>
            <div class="etiqueta titulo cgp"><bean:message
					key="general.notaSiniestro" bundle="etiquetas" /></div>
					<div  class="parametro titulo ">
				<img src="<%=request.getContextPath()%>/images/printer.png"
					onclick="javascript:openWinScroll2('<%=request.getContextPath()%>/security/comunes/generareporte.do?id=<c:out
								value="${siniestro.id}" />&anio=<c:out
								value="${siniestro.anioSiniestro}" />&id_reporte=12','NotaTecnicaSiniestro',800,600);">
								
								</div>
    <div class="etiqueta titulo cgp">
      <bean:message key="general.archivos" bundle="etiquetas" />
    </div>
    <div class="parametro titulo cgp"><a class="red" href="#"
			onclick="javascript:openWinScroll2('<%=request.getContextPath()%>/security/upload/viewFile.do?idSiniestro=<c:out value='${siniestro.id}'/>&anio=<c:out	value="${siniestro.anioSiniestro}" />','name',500,500);">Ver  <bean:message key="general.archivos" bundle="etiquetas" /></a> </div>
    <div class="etiqueta titulo cgp">Última
      <bean:message key="general.notaTecnica" bundle="etiquetas" />
    </div>
    <div class="parametro titulo cgp"><span>
      <c:out  value="${siniestro.notaTecnica.observacion}"/>
      </span><br/>
      <br/>
      <strong>
      <c:out  value="${siniestro.notaTecnica.desUsuario}"/>
      </strong> </div>
	
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
