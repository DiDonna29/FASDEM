<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<tiles:insert page="/jsp/plantilla/plantilla.jsp">

	<tiles:put name="titulo" direct="true">
		<bean:message key="principal.titulo" bundle="etiquetas" />
	</tiles:put>

	<tiles:put name="titulopagina" content="Inicio / Liquidación / Liquidación de Cartas Avales"
		direct="true" />
	<tiles:put name="itemsup" content="/jsp/comunes/items.jsp" />
    <tiles:put name="itemsdown" content=" " direct="true" />
	<tiles:put name="cuerpo" direct="true">
	<%--<div style="overflow: visible; clear: both;">
		<table class="tabla" width="500" cellpadding="1" cellspacing="1" border="0" bgcolor="yellow">
			<tr class="tituloCabecera">
				<td>Código</td>
				<td>Fecha Notificación.</td>
				<td>Servicio</td>
				<td>Tipo Siniestro</td>
				<td>Monto Amparado (Bs)</td>
				<td>Monto Presup (Bs)</td>
				<td>Tipo Enfermedad</td>
				<td>Estatus</td>
				<td>Causa de Ingreso</td>
			</tr>
			<pg:pager maxPageItems="<%=10%>" maxIndexPages="10" url="bandejaAnalizar.do" isOffset="false" export="offset,currentPageNumber=pageNumber"
				scope="request">
				<pg:param name="id" />
				<c:forEach items="${list}" var="s">
					<pg:item>
						<tr class="item" bgcolor="white" onclick="javaScript:submit2('<c:out value="${s.id}" />')">
							<td><c:out value="${s.codigo}" /></td>
							<td><fmt:formatDate pattern="dd/MM/yyyy" value="${s.fechaNotificacion}" /></td>
							<td><c:out value="${s.lsTipoTramite}" /></td>
							<td><c:out value="${s.lsTipoSiniestro}" /></td>
							<td><c:out value="${s.lsMontoAmparado}" /></td>
							<td><c:out value="${s.lsMontoPresupuestado}" /></td>
							<td><c:out value="${s.lsTipoEnfermedad}" /></td>
							<td><c:out value="${s.lsEstatus}" /></td>
							<td><c:out value="${s.lsCausaIngreso}" /></td>
						</tr>
					</pg:item>
				</c:forEach>
				<pg:index>
					<tr bgcolor="#FFFFFF">
						<td colspan="9" width="100%" align="right" class="textblue"><pg:prev export="pageUrl">
							<IMG height="11" src="<%=request.getContextPath()%>/images/left1.gif" width="10" align="middle">&nbsp; 
					<a style="cursor: hand; cursor: pointer;" class="textorange" href="<c:out value='${pageUrl}'/>">Anterior</a>
						</pg:prev>&nbsp; | <pg:pages>
							<c:choose>
								<c:when test="${pageNumber==currentPageNumber}">
									<c:out value="${pageNumber}" />
								</c:when>
								<c:otherwise>
									<a style="cursor: hand; cursor: pointer;" class="textorange" href="<c:out value='${pageUrl}'/>"> <c:out value="${pageNumber}" /> </a>
								</c:otherwise>
							</c:choose>
						</pg:pages>| <pg:next export="pageUrl">
							<a style="cursor: hand; cursor: pointer;" class="textorange" href="<c:out value="${pageUrl}"/>"> Siguiente</a>&nbsp;<IMG height="11"
								src="<%=request.getContextPath()%>/images/right1.gif" width="10" align="middle">
						</pg:next>&nbsp;</td>
					</tr>
				</pg:index>
			</pg:pager>
		</table>
		</div>--%>

			
	</tiles:put>
</tiles:insert>