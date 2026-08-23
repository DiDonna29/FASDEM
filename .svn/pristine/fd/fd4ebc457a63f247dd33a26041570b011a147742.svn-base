<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<div align="left"><a>¿Cuáles son las clínicas afiliadas?</a></div>

<div align="left"><a class="gray"  href="javascript:ir(4);">Listado de Clínicas Afiliadas - Gran Caracas</a></div>
<div align="left"><a class="gray"  href="javascript:ir(5);">Listado de Clínicas Afiliadas APS - Gran Caracas</a></div>
<div align="left"><a class="gray"  href="javascript:ir(6);">Listado de Clínicas Afiliadas con Costos Razonables - Gran Caracas</a></div>
<div align="left"><a class="gray"  href="javascript:ir(7);">Listado de Clínicas Afiliadas - Nivel Nacional</a></div>
<div align="left"><a class="gray"  href="javascript:ir(8);">Listado de Clínicas Afiliadas APS - Nivel Nacional</a></div>
<div align="left"><a class="gray" href="javascript:ir(9);">Listado de Clínicas Afiliadas con Costos Razonables - Nivel Nacional</a></div>

<script language="JavaScript">
	function ir(acc){
		tipoReporte = acc;
		popup_url ='<%=request.getContextPath()%>/reportes/ReporteClinicaImprimir.do?tipo='+tipoReporte;
		name ='Reporte de Clinicas';		
		openWinScroll(popup_url,name,'800','600');
	}
		
		function openWinScroll(popup_url,name,width,height) 
		{
			var size = ',width=' + width + ',height=' + height;
			var posicion = ",left="+Math.round(screen.availWidth/4)+",top="+Math.round(screen.availHeight/4);
			var popUp=window.open(popup_url ,name,'menubar=no,location=no,toolbar=no,status=yes,scrollbars=yes,titlebar=no,directories=no,'+size+posicion);
			popUp.opener=self;
		}



</script>


