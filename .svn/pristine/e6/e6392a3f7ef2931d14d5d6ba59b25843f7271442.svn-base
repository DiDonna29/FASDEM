function newWindow(file, window, w, h, isScroll, isStatus, isResizable,
		isMenuBar) {
	if (document.all || document.layers || document.getElementById) {
		wCenter = screen.availWidth;
		hCenter = screen.availHeight;
	}

	var popW = w, popH = h;
	var leftPos = (wCenter - popW) / 2, topPos = (hCenter - popH) / 2;

	msgWindow = open(file, window, "menubar=" + isMenuBar + ",status="
			+ isStatus + ",scrollbars=" + isScroll + ",resizable="
			+ isResizable + ",width=" + w + ",height=" + h + ",top=" + topPos
			+ ",left=" + leftPos);
	msgWindow.location.href = file;
	if (msgWindow.opener == null) {
		msgWindow.opener = self;
	}
	// msgWindow.opener.name = "opener";
}

function openWinScroll2(popup_url, name, width, height) {

	var size = ',width=' + width + ',height=' + height;
	var posicion = ",left=" + Math.round(screen.availWidth / 4) + ",top="
			+ Math.round(screen.availHeight / 4);
	var popUp = window
			.open(
					popup_url,
					name,
					'menubar=yes,location=yes,toolbar=no,status=yes,scrollbars=yes,titlebar=no,directories=no,'
							+ size + posicion);
	popUp.opener = self;

}
function IsNumeric(argText){
	   var sText = argText.value;
	   var ValidChars = "0123456789.";
	   var IsNumber=true;
	   var Char;
	   
	   patron = /[^0-9.]/g;
	   cadena = sText;
	   cadena = cadena.replace(patron,'');
	   if(!isNaN(cadena)){
		   argText.value =cadena;   
	   }else{
		   alert('Ingrese un número válido');
		   argText.value ='';
	   }

	   
	  /* for (i = 0; i < sText.length && IsNumber == true; i++){ 
	      Char = sText.charAt(i); 
	      if (ValidChars.indexOf(Char) == -1) {
	    	  argText.value=sText.substring(0, sText.length - 1)
	         // alert('Coloque un número válido')
	      }
	   }
	   return IsNumber;*/
	}