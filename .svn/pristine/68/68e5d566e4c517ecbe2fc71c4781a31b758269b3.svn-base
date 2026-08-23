<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<tiles:insert page="/jsp/plantilla/plantilla.jsp">

	<tiles:put name="titulo" direct="true">
		<bean:message key="principal.titulo" bundle="etiquetas" />
	</tiles:put>

	<tiles:put name="titulopagina" content="Busqueda de Siniestros" direct="true" />
	<tiles:put name="itemsup" content="" direct="true" />
	<tiles:put name="itemsdown" content="" direct="true" />
	<tiles:put name="cuerpo" direct="true">
		<div class="etiqueta titulo cgp"><bean:message key="general.especialidad" bundle="etiquetas" /></div>
		<div class="parametro titulo cgp">
		<html:select styleClass="cgp" name="globalActionForm" property="especialidad">
			<html:option styleClass="seleccione" value="-1">[SELECCIONAR]</html:option>
			<html:options collection="listEspecialidad" property="id" labelProperty="descripcion" />
		</html:select>
		</div>
		<div class="etiqueta titulo cgp"><bean:message key="general.organo" bundle="etiquetas" /></div>
		<div class="parametro titulo cgp">
		<html:select styleClass="cgp" name="globalActionForm" property="organo" >
			<html:option styleClass="seleccione" value="-1">[SELECCIONAR]</html:option>
			<html:options collection="listOrgano" property="id" labelProperty="descripcion" />
		</html:select>
		</div>
		<div class="etiqueta titulo cgp"><bean:message key="general.patologia" bundle="etiquetas" /></div>
		<div class="parametro titulo cgp">
		<html:select styleClass="cgp" name="globalActionForm" property="patologias" >
			<html:option styleClass="seleccione" value="-1">[SELECCIONAR]</html:option>
			<html:options collection="listPatologias" property="id" labelProperty="descripcion" />
		</html:select>
		</div>
		
		<div class="etiqueta titulo cgp"><bean:message key="general.tratamiento" bundle="etiquetas" /></div>
		<div class="parametro titulo cgp">
		<html:select styleClass="cgp" name="globalActionForm" property="tratamiento" >
			<html:option styleClass="seleccione" value="-1">[SELECCIONAR]</html:option>
			<html:options collection="listTratamiento" property="id" labelProperty="descripcion" />
		</html:select> <html:submit styleClass="boton"/>
		</div>
		
		
		
		<div class="textorange" style="margin-left:5px; padding-bottom: 5px">Listado de Siniestros</div>
			<div style="overflow: visible">
			<table class="tabla" width="500" cellpadding="0" cellspacing="1" border="0">
				<tr class="tituloCabecera">
					<td>Especialidad</td>
					<td>Organo</td>
					<td>Patologia</td>
					<td>Tratameinto</td>
				</tr>
				<pg:pager maxPageItems="<%=20%>" maxIndexPages="10" url="buscarSiniestros.do" isOffset="false" export="offset,currentPageNumber=pageNumber"
					scope="request">
					<pg:param name="id" />
					<c:forEach items="${listEspPatOrgTra}" var="l">
						<pg:item>
							<tr class="item" bgcolor="white" onclick="javaScript:submit2('<c:out value="${sini.id}" />')">
								<td><c:out value="${l.strEspecialidad}" /></td>
								<td><c:out value="${l.strOrgano}" /></td>
								<td><c:out value="${l.strPatologia}" /></td>
								<td><c:out value="${l.strTratamiento}" /></td>
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
			</div>
	</tiles:put>
</tiles:insert>


