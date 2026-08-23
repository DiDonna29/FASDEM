var $j = jQuery.noConflict();
$j(document).on('ready', function() {
	$j('#rf1txt').daterangepicker(
		{
			format: 'dd/MM/yyyy',
			separator: ' - ',
			startDate: Date.today(),
			endDate: Date.today(),
			minDate: '01/01/2010',
			maxDate: Date.today(),
			locale: {
				applyLabel: 'Aceptar',
				clearLabel:"Borrar",
				fromLabel: 'Desde',
				toLabel: 'Hasta',
				weekLabel: 'S',
				customRangeLabel: 'Rango deseado',
				daysOfWeek: ['Do', 'Lu', 'Ma', 'Mi', 'Ju', 'Vi','Sa'],
				monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
				firstDay: 1
		},
			showWeekNumbers: true,
			buttonClasses: ['btn-danger','btn-succes'],
			showDropdowns: true,
			dateLimit: { months: 1 }
		},
		function(start, end) {
			$j('#rf1txt').blur();
		}
	);
	
	$j('#btncalendario1').click(function () {
	    $j('#rf1txt').focus();
	});

	$j('#rf1sup').click(function () {
		$j('#rf1txt').val("");
	    $j("#rf1txt").blur();
	});
	
	$j('#Imprimir').click(function () {
		if ($j('#rf1txt').val() == ""){
			alert("Verifique se requiere el campo. 123");
			$j('#rf1txt').focus();
	    }else{
	    	var fechas = $j('#rf1txt').val();
	    	var elem = fechas.split(' - ');
	    	fd = elem[0]; fh = elem[1];
	    	var anod = fd.split('/'); var anoh = fh.split('/');
	    	if (anod[2] != anoh[2]){
	    		alert("Verifique el rango debe ser del mismo año.");
	    	}else{
	    		tipoReporte = '11';
	    		popup_url = ruta + '/security/reportes/ReporteImprimir.do?tipo='+tipoReporte+'&desde='+fd+'&hasta='+fh;
	    		name ='Reporte Estadística';
	    		openWinScroll(popup_url,name,'800','600');
	    	}
	    }
	});
});

function openWinScroll(popup_url,name,width,height) 
	{
		var size = ',width=' + width + ',height=' + height;
		var posicion = ",left="+Math.round(screen.availWidth/4)+",top="+Math.round(screen.availHeight/4);
		var popUp=window.open(popup_url ,'Reporte','menubar=no,location=no,toolbar=no,status=yes,scrollbars=yes,titlebar=no,directories=no,'+size+posicion);
		popUp.opener=self;
	}