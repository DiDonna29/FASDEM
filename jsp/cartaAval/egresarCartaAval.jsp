<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>


<tiles:insert page="/jsp/plantilla/plantilla.jsp">

	<tiles:put name="titulo" direct="true">
		<bean:message key="principal.titulo" bundle="etiquetas" />
		<input type="hidden" name="accion"/>
	</tiles:put>

	<tiles:put name="titulopagina" content="Inicio / Carta Aval / Egreso o Extensi&oacute;n de Carta Aval"
		direct="true" />
	<tiles:put name="itemsup" content="/jsp/comunes/items.jsp" />
    <tiles:put name="itemsdown" content="" direct="true"/>

	<tiles:put name="cuerpo" direct="true">
	
	<div class="sp5"></div>
		<div align="right">
<input type="hidden" name="idSini" value="<c:out value="${siniestro.id}" />" />
<input type="hidden" name="anioSiniestro" value="<c:out value="${siniestro.anioSiniestro}" />" />
<input class="boton" value="aceptar"
		 type="button" onclick="editar();" /></div>
		
	</tiles:put>
	
	 
	
	

</tiles:insert>

<script language="JavaScript">
function editar() {
		document.forms[0].accion.value='egresar';
		document.forms[0].action='<%=request.getContextPath()%>/security/cartaAval/egresarSiniestros.do';
		document.forms[0].submit();
	}
</script>
