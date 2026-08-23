<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>

<tiles:insert page="/jsp/plantilla/plantilla.jsp">
	<tiles:put name="titulo" direct="true">
		<bean:message key="principal.titulo" bundle="etiquetas" />
		<input type="hidden" name="accion" />
	</tiles:put>
	<tiles:put name="titulopagina" content="Inicio / Emergencia / Modificar Emergencia"
		direct="true" />
	<tiles:put name="itemsup" content="/jsp/comunes/items.jsp" />
	<tiles:put name="itemsdown" content="" direct="true" />
	<tiles:put name="cuerpo" direct="true">
		<html:hidden name="globalActionForm"
			property="montoHonorariosMedicosNoAmparado"
			value="${montoHonorariosMedicosNoAmparado}"></html:hidden>
		<html:hidden name="globalActionForm"
			property="montoGastosClinicosNoAmparado"
			value="${montoGastosClinicosNoAmparado}"></html:hidden>
		<html:hidden name="globalActionForm"
			property="montoAmbulanciaNoAmparado"
			value="${montoAmbulanciaNoAmparado}"></html:hidden>
		<html:hidden name="globalActionForm"
			property="montoFunerariaNoAmparado"
			value="${montoFunerariaNoAmparado}"></html:hidden>
		<html:hidden name="globalActionForm"
			property="montoExamenesEspecialesNoAmparado"
			value="${montoExamenesEspecialesNoAmparado}"></html:hidden>
		<html:hidden name="globalActionForm" property="montoAmparado"
			value="${montoAmparado}"></html:hidden>
		<html:hidden name="globalActionForm" property="montoNoAmparado"
			value="${montoNoAmparado}"></html:hidden>
		<html:hidden name="globalActionForm" property="montoPresupuestado"
			value="${montoPresupuestado}"></html:hidden>
		<html:hidden name="globalActionForm" property="montoNegociado"
			value="${montoNegociado}"></html:hidden>
			
		<div  align="right" class="etiqueta titulo cgp"><br>Enviar SMS </div>
			
		<div
			style="border-bottom-color: #EEEEEE; border-bottom-width: thin; border-bottom-style: solid">&nbsp;</div>
				<div align="left"><input name="sms" type="checkbox"	style="width: auto"  onclick="checkedAll();" /> <input type="text" class="vtnumero" name="tlf" class="titblue" width="120px" disabled="disabled" maxlength="11" ><span class="etiqueta titulo cgp">ejp.123456789</span></div>
		<div class="sp5"></div>
		
		<div align="right"><input class="boton" value="aceptar"
			type="button" onclick="editar();" /></div>
			
	</tiles:put>
</tiles:insert>
<script language="JavaScript">
	function editar() {
		
		var sms = document.forms[0].sms.value;
		var tlf = document.forms[0].tlf.value;
		if (document.forms[0].sms.checked && tlf.length<11){
			alert('El número de teléfono debe constar de 11 caracteres');
			
		}else
			{
		document.forms[0].sms.value= sms;
		document.forms[0].tlf.value= tlf;
		document.forms[0].accion.value = 'editar';
		document.forms[0].submit();}
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

<script language="JavaScript">
function trim(myString){
	return myString.replace(/([\ \t]+(?=[\ \t])|^\s+|\s+$)/g, '').replace(/^\s+/g,'');
}
function validar(cadena, correcta){
	if (correcta.test(cadena)){
		return true;
	}else {
		return false;
	}
}
function cambio(cadena, correcta){
	var cadena_correcta = "";
	for (var i = 0; i< cadena.length; i++) {
		var caracter = cadena.charAt(i);
		if (validar(caracter, correcta)){cadena_correcta = cadena_correcta + caracter;}
	};
	return cadena_correcta;
}
$(document).ready(function() {
	$('.vtnumero').keypress(function(e) {
		if (e.ctrlKey || e.altKey){return true;}
		return validar(String.fromCharCode(e.which), /^[0-9\x00\b]+$/);
	});
	$('.vtnumero').blur(function() {
		// La siguiente es para la cédula
		//$(this).val(parseInt($(this).val()));
		return $(this).val(trim(cambio($(this).val(), /^[0-9\x00\b]+$/)));
	});
});
</script>
