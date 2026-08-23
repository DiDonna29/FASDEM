<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html-el.tld" prefix="html"%>
<%@	taglib uri="/WEB-INF/tlds/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@page import="ve.gob.dem.framework.recursos.*,java.util.Date,java.text.NumberFormat,java.util.ArrayList,java.text.SimpleDateFormat"%>
<tiles:insert page="/jsp/plantilla/plantilla.jsp">

	<tiles:put name="titulo" direct="true">
		<bean:message key="principal.titulo" bundle="etiquetas" />
	</tiles:put>

	<tiles:put name="titulopagina" content="Consulta de Siniestros" direct="true" />
	<tiles:put name="itemsup" content="" direct="true" />
	<tiles:put name="itemsdown" content="/jsp/comunes/items.jsp" />


	<tiles:put name="cuerpo" direct="true">
		<div class="etiqueta titulo cgp"><bean:message key="general.cedula" bundle="etiquetas" /></div>
		<div class="parametro titulo "><input class="cgp" type="text" name="cedula" name="cedula" value="<c:out value='${cedula}'/>" /></div>
		<div class="etiqueta titulo cgp"><bean:message key="general.nombres" bundle="etiquetas" /> o <bean:message key="general.apellidos" bundle="etiquetas" /></div>
		<div class="parametro titulo "><input class="cgp" type="text" name="nombres" name="nombres" value="<c:out value='${nombres}'/>" /></div>
		<div class="etiqueta titulo cgp"><bean:message key="general.codigo" bundle="etiquetas" /></div>
		<div class="parametro titulo "><input class="cgp" type="text" name="codigo" name="codigo" value="<c:out value="${sini.aniomesCodigo}" /><c:out value="${sini.codigo}" /><c:out value="${sini.subCodigo}" />" /></div>
		<div class="etiqueta titulo cgp">Año del siniestro a consultar</div>
		<div class="parametro titulo "><select name="anioBusqueda" class="cgp">

			<%
				int anio_actual = Integer.parseInt(Utilidad.DateToString(
								new Date(), "yyyy"));
						for (int k = 2010; k != anio_actual + 1; k++) {
			%>

			<option title="<%=k%>" selected value=<%=k%>><%=k%></option>

			<%
				}
			%>

		</select></div>
		<input type="hidden" name="anioBusqueda" value="${anioBusqueda}"></input>


		<c:if test="${resultado!=null}">
			<div class="textorange" style="padding-bottom: 5px">Listado de Siniestros</div>
			<div style="overflow: visible">
			<table class="tabla" cellpadding="2" cellspacing="1" border="0" bgcolor="yellow">

				<tr class="tituloCabecera">
					<td>Número de Siniestro</td>
					<td>Cédula</td>
					<td>Nombre</td>
					<td>Estatus</td>
					<td>Fecha Notif.</td>
					<td>Tipo Tramite</td>
					<td>Monto Presupuestado (Bs)</td>
					<td>Monto Amparado (Bs)</td>
				</tr>
				<pg:pager maxPageItems="<%=20%>" maxIndexPages="10" url="buscarBeneficiario.do" isOffset="false" export="offset,currentPageNumber=pageNumber" scope="request">
					<pg:param name="cedula" />
					<pg:param name="nombres" />
					<pg:param name="codigo" />
                    <pg:param name="anioBusqueda" />
					<c:forEach items="${resultado}" var="sini">
						<pg:item>
							<tr class="item" bgcolor="white" onclick="javaScript:submit2('<c:out value="${sini.id}" />','<c:out value="${sini.anioSiniestro}" />')">
								<td class="nblack"><input type="hidden" id="idSini" name="idSini" /><c:out value="${sini.aniomesCodigo}" />-<c:out value="${sini.codigo}" />-<c:out value="${sini.subCodigo}" /></td>
								<td><c:out value="${sini.cedulaBeneficiario}" /></td>
								<td><c:out value="${sini.nombresBeneficiario}" /> <c:out value="${sini.apellidosBeneficiario}" /></td>
								<td><c:out value="${sini.estatus.descripcion}" /></td>
								<td><fmt:formatDate pattern="dd/MM/yyyy" value="${sini.fechaNotificacion}" /></td>
								<td><c:out value="${sini.tipoTramite.descripcion}" /></td>
								<td align="right"><fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${sini.montoPresupuestado}" /><input type="hidden" name="id" value="<c:out value="${sini.aniomesCodigo}" /><c:out value="${sini.codigo}" /><c:out value="${sini.subCodigo}" />" /></td>
								<td align="right"><fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${sini.montoAmparado}" /></td>
							</tr>
						</pg:item>
					</c:forEach>
					<pg:index>
						<tr bgcolor="#FFFFFF">
							<td colspan="8" width="100%" align="right" class="textblue"><pg:prev export="pageUrl">
								<img height="11" src="<%=request.getContextPath()%>/images/left1.gif" width="10" align="middle" />&nbsp; 
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
								<a style="cursor: hand; cursor: pointer;" class="textorange" href="<c:out value="${pageUrl}"/>"> Siguiente</a>&nbsp;<img height="11" src="<%=request.getContextPath()%>/images/right1.gif" width="10" align="middle" />
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

	document.forms[0].action='<%=request.getContextPath()%>/security/aps/detalle.do';
	document.forms[0].submit();
}
</script>

