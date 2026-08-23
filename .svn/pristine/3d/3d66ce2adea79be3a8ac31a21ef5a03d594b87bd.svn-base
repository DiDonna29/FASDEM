<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@	taglib uri="/WEB-INF/tlds/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@page import="ve.gob.dem.framework.recursos.*,java.util.Date,java.text.NumberFormat,java.util.ArrayList,ve.gob.dem.fasdem.bean.*,java.text.SimpleDateFormat"%>
<tiles:insert page="/jsp/plantilla/plantilla.jsp">

<%


		

		
%>



	<tiles:put name="titulo" direct="true">
		<bean:message key="principal.titulo" bundle="etiquetas" />
	</tiles:put>

	<tiles:put name="titulopagina" content="Reporte de Carga por Usuario"
		direct="true" />
	<tiles:put name="itemsup" content="/jsp/comunes/items.jsp" />
    <tiles:put name="itemsdown" content=" " direct="true" />
	<tiles:put name="cuerpo" direct="true">
	<div style="overflow: visible"></div>
	
	

	
	
	
	
	
			<div style="overflow: visible">
			
				<div class="etiqueta titulo cgp">Fecha:</div>
				<div class="parametro titulo ">
				<input type="text" name="fecha" id="fecha" onfocus="javascript:showCalendarSelected2(document.forms[0].fecha, document.forms[0].fecha, 'dd/mm/yyyy','es',1);blur();" class="cgp">
				</div>
				<div class="sp5"></div>
				<div align="right">

				<input class="boton" value="Imprimir" type="button"  onclick="ir();" />
				</div>

	
			
				
				
				<div  align="center" >
				   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</div>
				
				<input id="crea" type="hidden" name="crea">
				<input id="ced" type="hidden" name="ced" value="<%=request.getAttribute("ced")%>">
				<input id="funcion" type="hidden" name="funcion">
				<input id="ur" type="hidden" name="ur" value="<%=request.getContextPath()%>/security/reportes/ReporteImprimir.do?">
				


		
			

			
      
  
            
	</tiles:put>
	
</tiles:insert>

</form>

<script language="JavaScript">
	function ir(){
		if(document.getElementById('fecha').value ==''){		
			alert("Para continuar, Debe seleccionar una fecha para visualizar el reporte.");
			return;
		}
		var fechaReporte =document.getElementById('fecha').value; 
		var usuarioReporte = document.getElementById('ced').value;
		var tipoReporte = '1';
		var url = document.getElementById('ur').value;
		var anio = fechaReporte.substring(6,10);
		var popup_url =url+'tipo='+tipoReporte+'&ced='+usuarioReporte+'&fecha='+fechaReporte+'&anio='+anio;
		var name ='Reporte de Carga por Usuario';	
		//lert(popup_url);
		openWinScroll(popup_url,name,'800','600');
	}
		
		function openWinScroll(popup_url,name,width,height) 
		{
			var size = ',width=' + width + ',height=' + height;
			var posicion = ',left='+Math.round(screen.availWidth/4)+',top='+Math.round(screen.availHeight/4);
			var popUp=window.open(popup_url ,'Reporte','menubar=no,location=no,toolbar=no,status=yes,scrollbars=yes,titlebar=no,directories=no,'+size+posicion);
				//window.open(popup_url ,'nada','');
			popUp.opener=self;
		}



</script>

	<%
 String mensaje = (String) request.getAttribute("mensaje");
 if (mensaje != null){%>
 	<script>
 	 	alert('<%=mensaje%>');
 	</script>
 <%}
%>




