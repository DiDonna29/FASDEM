//// Funciones HD
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
	$('.vtcedula').keypress(function(e) {
		alert("prueba...");
		if (e.ctrlKey || e.altKey){return true;}
		return validar(String.fromCharCode(e.which), /^[0-9\x00\b]+$/);
	});
	$('.vtcedula').blur(function() {
		$(this).val(parseInt($(this).val()));
		if ($(this).val() == 0) {
			$(this).val("");
		}
		return $(this).val(trim(cambio($(this).val(), /^[0-9\x00\b]+$/)));
	});
});