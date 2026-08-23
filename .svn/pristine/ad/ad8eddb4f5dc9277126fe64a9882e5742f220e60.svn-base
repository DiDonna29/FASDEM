<%@page contentType="text/html; charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html-el.tld" prefix="html"%>

<tiles:insert page="/jsp/plantilla/plantilla.jsp">
  <tiles:put name="titulo" direct="true">
    <bean:message key="principal.titulo" bundle="etiquetas" />
    <input type="hidden" name="accion" value=""/>
   
	<input type="hidden" name="idSiniPadre" value="<c:out value='${idSiniPadre}'/>"/>
  </tiles:put>
  <tiles:put name="titulopagina" content="Inicio / Carta Aval / Declarar Carta Aval"
		direct="true" />
  <tiles:put name="itemsup" content="/jsp/comunes/items.jsp" />
  <tiles:put name="itemsdown" content=""  direct="true"/>
  <tiles:put name="cuerpo" direct="true">
    <div class="parametro titulo ">
      <html:hidden  name="globalActionForm" property="montoHonorariosMedicosNoAmparado"  value="${montoHonorariosMedicosNoAmparado}" ></html:hidden>
      <html:hidden  name="globalActionForm" property="montoGastosClinicosNoAmparado" value="${montoGastosClinicosNoAmparado}" ></html:hidden>
      <html:hidden  name="globalActionForm" property="montoAmparado" value="${montoAmparado}" ></html:hidden>
      <html:hidden  name="globalActionForm" property="montoNoAmparado" value="${montoNoAmparado}" ></html:hidden>
      <html:hidden  name="globalActionForm" property="montoPresupuestado" value="${montoPresupuestado}" ></html:hidden>
      <html:hidden  name="globalActionForm" property="montoNegociado" value="${montoNegociado}" ></html:hidden>
    </div>
    <div
		style="border-bottom-color: #EEEEEE; border-bottom-width: thin; border-bottom-style: solid">&nbsp;</div>
    <div class="sp5"></div>
    <div align="right">
      <input class="boton" value="aceptar"
		 type="button" onclick="procesar();" />
    </div>
	
  </tiles:put>
</tiles:insert>
<script language="JavaScript">
function procesar() {
		document.forms[0].accion.value='procesar';
		document.forms[0].submit();
	}
</script>
