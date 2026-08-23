
<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<tiles:insert page="/jsp/plantilla/plantilla.jsp">
	<tiles:put name="titulo" direct="true">
		<bean:message key="principal.titulo" bundle="etiquetas" />
	</tiles:put>
	<tiles:put name="titulopagina" content="Búsqueda de Beneficiario" direct="true" />
	<tiles:put name="itemsup" content="/jsp/comunes/items.jsp" />
	<tiles:put name="itemsdown" content=" " direct="true" />
	<tiles:put name="cuerpo" direct="true">
		<c:if test="${bene!=null}">
			<input type="hidden" id="id" name="id" />
			<input type="hidden" id="tipo" name="tipo" />
				<div class="etiqueta titulo cgp">Año</div>
		<div class="parametro titulo "><html:select name="globalActionForm" size="4" property="anioBusqueda">
			<html:option value="2010">2010</html:option>
			<html:option value="2011">2011</html:option>
			<html:option value="2012">2012</html:option>
			<html:option value="2013">2013</html:option>
			<html:option value="2014">2014</html:option>
		</html:select></div>
			<div class="s10"></div>
			<div class="textorange" style="padding-bottom: 5px"><bean:message key="general.datos.titular" bundle="etiquetas" /></div>
			<div style="overflow: visible">
			
			<table class="tabla" width="600" cellpadding="2" cellspacing="1" border="0">
				<tr class="tituloCabecera">
					<td><bean:message key="general.cedula" bundle="etiquetas" /></td>
					<td><bean:message key="general.nombres" bundle="etiquetas" /></td>
					<td><bean:message key="general.apellidos" bundle="etiquetas" /></td>
					<td><bean:message key="general.fechanac" bundle="etiquetas" /></td>
				
					<td><bean:message key="general.telefono" bundle="etiquetas" /></td>
					<td><bean:message key="general.fechaIngreso" bundle="etiquetas" /></td>
					<td><bean:message key="general.tipoEmpleado" bundle="etiquetas" /></td>
					<td><bean:message key="general.estatus" bundle="etiquetas" /></td>
					<td><bean:message key="general.estado" bundle="etiquetas" /></td>
					<td><bean:message key="general.fechaEgreso" bundle="etiquetas" /></td>
				</tr>
				<tr class="item" bgcolor="white" onclick="javaScript:submit2('<c:out value="${bene.cedula}" />', '1')">
					<td><c:out value="${bene.cedula}" /></td>
					<td><c:out value="${bene.nombres}" /></td>
					<td><c:out value="${bene.apellidos}" /></td>
					<td><fmt:formatDate pattern="dd/MM/yyyy" value="${bene.fechaNacimiento}" /></td>
				
					<!--<td><c:out value="${bene.estadoCivil}" /></td>
					<td><c:out value="${bene.cargo}" /></td>
					--><td><c:out value="${bene.telefono}" /></td>
					<td><fmt:formatDate pattern="dd/MM/yyyy" value="${bene.fechaIngreso}" /></td>
					<td><c:out value="${bene.tipoEmpleado}" /></td>
					<td><c:out value="${bene.estatus}" /></td>
					<td><c:out value="${bene.estado}" /></td><td>
					<c:if test="${bene.fechaEgreso!=null}">
					<fmt:formatDate pattern="dd/MM/yyyy" value="${bene.fechaEgreso}" />
					</c:if>
					<c:if test="${bene.fechaEgreso==null}">
					No Posee
					</c:if></td>
					
				</tr>
			</table>
			<c:out value="${cuenta.Cuenta}" />
			</div>
			<div class="s10"></div>
			<div class="textorange" style="padding-bottom: 5px">Carga Familiar</div>
			<div>
			<table class="tabla" width="600" cellpadding="2" cellspacing="1" border="0">
				<tr class="tituloCabecera">
					<td><bean:message key="general.cedula" bundle="etiquetas" /></td>
					<td><bean:message key="general.nombres" bundle="etiquetas" /></td>
					<td><bean:message key="general.apellidos" bundle="etiquetas" /></td>
					<td><bean:message key="general.fechanac" bundle="etiquetas" /></td>
					<td><bean:message key="general.edad" bundle="etiquetas" /></td>
					<td><bean:message key="general.sexo" bundle="etiquetas" /></td>
					<!--<td><bean:message key="general.edocivil" bundle="etiquetas" /></td>
					--><td><bean:message key="general.parentesco" bundle="etiquetas" /></td>
					<td><bean:message key="general.estatus" bundle="etiquetas" /></td>
					<td><bean:message key="general.fechaEgreso" bundle="etiquetas" /></td>
				</tr>
				<c:forEach items="${bene.cargaFamiliar}" var="cf">
					<jsp:useBean id="ahora" class="java.util.Date" />
					<tr class="item" bgcolor="white" onclick="javaScript:submit2('<c:out value="${cf.cedula}" />', '2')">
						<td><c:choose>
							<c:when test="${cf.tipoCedRif =='X'}">No Posee</c:when>
							<c:otherwise>
								<c:out value="${cf.cedula}" />
							</c:otherwise>
						</c:choose></td>
						<td><c:out value="${cf.nombres}" /></td>
						<td><c:out value="${cf.apellidos}" /></td>
						<td><fmt:formatDate pattern="dd/MM/yyyy" value="${cf.fechaNacimiento}" /></td>
						<td><c:out value="${cf.edad}" /></td>
						<td><c:out value="${cf.sexo}" /></td>
						<!--<td><c:out value="${cf.estadoCivil}" /></td>
						--><td><c:out value="${cf.parentesco}" /></td>
						<td><c:out value="${cf.estatus}" /></td>
						<td>
					<c:if test="${cf.fechaEgreso!=null}">
					<fmt:formatDate pattern="dd/MM/yyyy" value="${cf.fechaEgreso}" />
					</c:if>
					<c:if test="${cf.fechaEgreso==null}">
					No Posee
					</c:if></td>
					</tr>
				</c:forEach>
			</table>
			</div>
		</c:if>
	</tiles:put>
</tiles:insert>
<script>
function submit2(argCedula, argTipo){
	document.getElementById('id').value=argCedula;
	document.getElementById('tipo').value=argTipo;
	document.forms[0].action='<%=request.getContextPath()%><c:out value='${myhref}'/>'
	document.forms[0].submit();
}
</script>
