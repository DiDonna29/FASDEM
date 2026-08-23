<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<input type="hidden" name="accion" value=""/>
<tiles:insert page="/jsp/plantilla/plantilla.jsp">

	<tiles:put name="titulo" direct="true">
		<bean:message key="principal.titulo" bundle="etiquetas" />
	</tiles:put>

	<tiles:put name="titulopagina" content="Inicio / Carta Aval / Busqueda de Siniestros"
		direct="true" />
	<tiles:put name="itemsup" content="/jsp/comunes/items.jsp" />
    <tiles:put name="itemsdown" content=""  direct="true"/>
	<tiles:put name="cuerpo" direct="true">
<div class="etiqueta titulo cgp"><bean:message
		key="general.montoCalculado" bundle="etiquetas" /></div>
	

	<div class="parametro titulo ">
		<input type="hidden" name="montoCalculado" onfocus="blur();" 
		value="<c:out value='${montoCalculado}'/>" />
		<fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" groupingUsed="true" value="${montoCalculado}" />
		Bs.</div>
		<div
		style="border-bottom-color: #EEEEEE; border-bottom-width: thin; border-bottom-style: solid">&nbsp;</div>
	<div class="sp5"></div>
	<div align="right"><input class="boton" value="aceptar"
		 type="button" onclick="procesar();" /></div>
		
	</tiles:put>
</tiles:insert>
<script language="JavaScript">
function procesar() {
		document.forms[0].accion.value='procesar';
		document.forms[0].submit();
	}
</script>


