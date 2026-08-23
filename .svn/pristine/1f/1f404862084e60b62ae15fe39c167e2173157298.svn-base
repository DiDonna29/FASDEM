<%@page contentType="text/html; charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html-el.tld" prefix="html"%>

<tiles:insert page="/jsp/plantilla/plantilla.jsp">
	<tiles:put name="titulo" direct="true">
		<bean:message key="principal.titulo" bundle="etiquetas" />
		<input type="hidden" name="accion" value="" />
	</tiles:put>
	<tiles:put name="titulopagina" content="Inicio / Emergencia / Declaración de Emergencia"
		direct="true" />
	<tiles:put name="itemsup" content="/jsp/comunes/items.jsp" />
	<tiles:put name="itemsdown" content="" direct="true" />
	<tiles:put name="cuerpo" direct="true">
		<div class="parametro titulo "><html:hidden
			name="globalActionForm" property="montoHonorariosMedicosNoAmparado"
			value="${montoHonorariosMedicosNoAmparado}"></html:hidden> <html:hidden
			name="globalActionForm" property="montoGastosClinicosNoAmparado"
			value="${montoGastosClinicosNoAmparado}"></html:hidden> <html:hidden
			name="globalActionForm" property="montoAmbulanciaNoAmparado"
			value="${montoAmbulanciaNoAmparado}"></html:hidden> <html:hidden
			name="globalActionForm" property="montoFunerariaNoAmparado"
			value="${montoFunerariaNoAmparado}"></html:hidden> <html:hidden
			name="globalActionForm" property="montoExamenesEspecialesNoAmparado"
			value="${montoExamenesEspecialesNoAmparado}"></html:hidden> <html:hidden
			name="globalActionForm" property="montoAmparado"
			value="${montoAmparado}"></html:hidden> <html:hidden
			name="globalActionForm" property="montoNoAmparado"
			value="${montoNoAmparado}"></html:hidden> <html:hidden
			name="globalActionForm" property="montoPresupuestado"
			value="${montoPresupuestado}"></html:hidden> <html:hidden
			name="globalActionForm" property="montoNegociado"
			value="${montoNegociado}"></html:hidden></div>
		<div
			style="border-bottom-color: #EEEEEE; border-bottom-width: thin; border-bottom-style: solid">&nbsp;</div>
		<div class="sp5"></div>
		
			<div  align="right" class="etiqueta titulo cgp"><br>Enviar SMS </div>
			
		<div
			style="border-bottom-color: #EEEEEE; border-bottom-width: thin; border-bottom-style: solid">&nbsp;</div>
				<div align="left"><input name="sms" type="checkbox"	style="width: auto"  onclick="checkedAll();" /> <input type="text" class="vtnumero" name="tlf" class="titblue" width="120px" disabled="disabled" maxlength="11" ><span class="etiqueta titulo cgp">ejp.123456789</span></div>
		<div class="sp5"></div>
		
		<div align="right"><input class="boton" value="aceptar"
			type="button" onclick="procesar();" /></div>
			<input id="telefonon" type="hidden" name="telefonon" value="<%=request.getAttribute("telefonon")%>">
			<input id="tt" type="hidden" name="tt" value="<%=request.getAttribute("tt")%>">
			<input id="bb" type="hidden" name="bb" value="<%=request.getAttribute("bb")%>">
		
	</tiles:put>
</tiles:insert>
<script language="JavaScript">
	function procesar() {
		var sms = document.forms[0].sms.value;
		var tlf = document.forms[0].tlf.value;
		
		if (document.forms[0].sms.checked && tlf.length<11){
			alert('El número de teléfono debe constar de 11 caracteres');
			
		}else
			{
		document.forms[0].sms.value= sms;
		document.forms[0].tlf.value= tlf;
		document.forms[0].accion.value = 'procesar';
		document.forms[0].submit();
	}
		}
		
	function checkedAll () {
		
		if (document.forms[0].sms.checked == false) {
				document.forms[0].tlf.disabled = true
				document.forms[0].tlf.value= ''
			} else {
				document.forms[0].tlf.disabled = false
			}

		}
</script>
