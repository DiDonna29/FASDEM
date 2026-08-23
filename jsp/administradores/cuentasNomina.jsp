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

	<tiles:put name="titulopagina" content="Busqueda de Cuentas"
		direct="true" />
	<tiles:put name="itemsup" content="" direct="true"/>
    <tiles:put name="itemsdown" content="/jsp/comunes/items.jsp"  />
    
    
	<tiles:put name="cuerpo" direct="true">

	
	
	
	<div class="textorange" style="padding-bottom: 5px">Listado de
			cuentas</div>
			<div style="overflow: visible">
			<table class="tabla" width="600" cellpadding="2" cellspacing="1" border="0">
				<tr class="tituloCabecera">
					<td><bean:message key="general.cedula" bundle="etiquetas" /></td>
					<td><bean:message key="general.cuenta" bundle="etiquetas" /></td>
					
				</tr>
				<tr class="item" bgcolor="white">
					<td><c:out value="${P.cedula}" /></td>
					<td><c:out value="${P.cuenta}" /></td>
					
					
				</tr>
			</table>
			</div>
	
	</tiles:put>
</tiles:insert>
<script>
function submit2(argid){
	document.getElementById("idSini").value=argid;
	document.forms[0].action='<%=request.getContextPath()%>/security/aps/detalle.do';
	document.forms[0].submit();
}
</script>

