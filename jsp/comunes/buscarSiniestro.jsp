<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html-el.tld" prefix="html"%>
<%@	taglib uri="/WEB-INF/tlds/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<tiles:insert page="/jsp/plantilla/plantilla.jsp">

	<tiles:put name="titulo" direct="true">
		<bean:message key="principal.titulo" bundle="etiquetas" />
	</tiles:put>

	<tiles:put name="titulopagina" content="Búsqueda de Siniestros"
		direct="true" />
	<tiles:put name="itemsup" content="" direct="true"/>
    <tiles:put name="itemsdown" content="/jsp/comunes/items.jsp"  />
    
    
	<tiles:put name="cuerpo" direct="true">
	
	<c:if test="${sin!=null}">
	<div class="textorange" style="padding-bottom: 5px">Listado de
			Siniestros</div>
			<div style="overflow: visible">
			<table class="tabla"  cellpadding="2" cellspacing="1"
				border="0" bgcolor="yellow">

				<tr class="tituloCabecera">
					<td>Número de Siniestro</td>
					<td>Fecha Ocurrencia</td>
					<td>Fecha Notif.</td>
					<td>Tipo Tramite</td>
					<td>Tipo Siniestro</td>
					<td>Estatus</td>
					<td>Monto Presupuestado (Bs)</td>
					<td>Monto Amparado (Bs)</td>
				</tr>
				<pg:pager maxPageItems="<%=20%>" maxIndexPages="10"
					url="consulta.do" isOffset="false"
					export="offset,currentPageNumber=pageNumber" scope="request">
					<pg:param name="cedula" />
					<pg:param name="nombres" />
					<pg:param name="codigo" />
					<c:forEach items="${sin}" var="siniestro">
						<pg:item>
							<tr class="item" bgcolor="white"
								onclick="javaScript:submit2('<c:out value="${siniestro.id}" />')">
								<td><input type="hidden" id="idSini"
									name="idSini" /><c:out value="${siniestro.numero}" /></td>					
								<td><fmt:formatDate pattern="dd/MM/yyyy"
									value="${siniestro.fechaOcurrencia}" /></td>
								<td><fmt:formatDate pattern="dd/MM/yyyy"
									value="${siniestro.fechaNotificacion}" /></td>
									<td><c:out value="${siniestro.tipoTramite.descripcion}" /></td>
								<td><c:out value="${siniestro.lsTipoSiniestro}" /></td>
								<td><c:out value="${siniestro.estatus.descripcion}" /></td>
								<td><c:out value="${siniestro.montoPresupuestado}" /><input
									type="hidden" name="id"
									value="<c:out value="${siniestro.aniomesCodigo}" /><c:out value="${siniestro.codigo}" /><c:out value="${siniestro.subCodigo}" />" /></td>
							
							<td><c:out value="${siniestro.montoAmparado}" /></td></tr>
						</pg:item>
					</c:forEach> 
					<pg:index>
						<tr bgcolor="#FFFFFF">
							<td colspan="8" width="100%" align="right" class="textblue">
							<pg:prev export="pageUrl">
								<img height="11"
									src="<%=request.getContextPath()%>/images/left1.gif"
									width="10" align="middle"/>&nbsp; 
					<a style="cursor: hand; cursor: pointer;" class="textorange"
									href="<c:out value='${pageUrl}'/>">Anterior</a>
							</pg:prev>&nbsp; | <pg:pages>
								<c:choose>
									<c:when test="${pageNumber==currentPageNumber}">
										<c:out value="${pageNumber}" />
									</c:when>
									<c:otherwise>
										<a style="cursor: hand; cursor: pointer;" class="textorange"
											href="<c:out value='${pageUrl}'/>"> <c:out
											value="${pageNumber}" /> </a>
									</c:otherwise>
								</c:choose>
							</pg:pages>| <pg:next export="pageUrl">
								<a style="cursor: hand; cursor: pointer;" class="textorange"
									href="<c:out value="${pageUrl}"/>"> Siguiente</a>&nbsp;<img
									height="11"
									src="<%=request.getContextPath()%>/images/right1.gif"
									width="10" align="middle"/>
							</pg:next>&nbsp;</td>
						</tr>
					</pg:index>

				</pg:pager>
			</table>
			</div>
	</c:if>
	</tiles:put>
</tiles:insert>
<script>
function submit2(argid){
	document.getElementById("idSini").value=argid;
	document.forms[0].action='<%=request.getContextPath()%>/security/consulta/detalle.do';
	document.forms[0].submit();
}
</script>

