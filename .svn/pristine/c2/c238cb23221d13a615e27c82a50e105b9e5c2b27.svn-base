<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@	taglib uri="/WEB-INF/tlds/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@page import="ve.gob.dem.framework.recursos.*,java.util.Date,java.text.NumberFormat,java.util.ArrayList,ve.gob.dem.fasdem.bean.*,java.text.SimpleDateFormat"%>
<tiles:insert page="/jsp/plantilla/plantilla.jsp">

<%

ArrayList listaAnio = (ArrayList)request.getAttribute("listaAnio");	
ArrayList listaTipoTramite = (ArrayList)request.getAttribute("listaTipoTramite");
ArrayList listaMeses = (ArrayList)request.getAttribute("listaMeses");

int anioActual = (Integer)request.getAttribute("anio");
int mesActual = (Integer)request.getAttribute("mes");
int mes=0;
int anio=0;
String NombreMes="";
TipoTramite tt;
Date fecha = new Date();
%>



	<tiles:put name="titulo" direct="true">
		<bean:message key="principal.titulo" bundle="etiquetas" />
	</tiles:put>

	<tiles:put name="titulopagina" content="Relación de Liquidación"
		direct="true" />
	<tiles:put name="itemsup" content="/jsp/comunes/items.jsp" />
    <tiles:put name="itemsdown" content=" " direct="true" />
	<tiles:put name="cuerpo" direct="true">
	<div style="overflow: visible"></div>
	
			<div style="overflow: visible">
			
				<div class="etiqueta titulo cgp">Año:</div>
				<div class="parametro titulo ">
				<select name="anio" id="anio" class="cgp" >
				    <option value="-1" selected="selected" class="seleccione">[SELECCIONAR]</option>
					
					
						 <%
							for (int j=0;j!=listaAnio.size();j++){
							anio = (Integer) listaAnio.get(j);
							
			      		 %>		
								
								<option 
								<%if(anio==anioActual){%>
								selected="selected"
								<%}%>
								value=<%=anio%>><%=anio%>
								</option>
								
					    <%}%>	
					
					
					
		        </select>

				</div>
				<div class="etiqueta titulo cgp">Mes:</div>
				<div class="parametro titulo ">
				<select name="mes" id="mes" class="cgp" >
				    <option value="-1" selected="selected" class="seleccione">[SELECCIONAR]</option>
					
					
						 <%
							for (int j=0;j!=listaMeses.size();j++){
							mes = (Integer)listaMeses.get(j);
							j++;
							NombreMes = (String)listaMeses.get(j);
			      		 %>		
								
								<option 
								<%if(mes==mesActual){%>
								selected="selected"
								<%}%>
								value=<%=mes%>><%=NombreMes%>
								</option>
								
					    <%}%>	
					
					
					
		        </select>
				</div>
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
		
            
	</tiles:put>
	
</tiles:insert>

</form>

<script language="JavaScript">
	function ir(){
		if(document.getElementById('anio').value =='-1'){		
			alert("Para continuar, Debe seleccionar un año para visualizar el reporte.");
			return;
		}
		if(document.getElementById('mes').value =='-1'){		
			alert("Para continuar, Debe seleccionar un mes para visualizar el reporte.");
			return;
		}

		aniop =document.getElementById('anio').value;
		mesp = document.getElementById('mes').value;
		usuarioReporte = document.getElementById('ced').value;
		tipoReporte = '10';
		popup_url ='<%=request.getContextPath()%>/security/reportes/ReporteImprimir.do?tipo='+tipoReporte+'&ced='+usuarioReporte+'&fecha='+mesp+'&anio='+aniop;
		name ='Reporte Liquidacion';		
		openWinScroll(popup_url,name,'800','600');
	}
		
		function openWinScroll(popup_url,name,width,height) 
		{
			var size = ',width=' + width + ',height=' + height;
			var posicion = ",left="+Math.round(screen.availWidth/4)+",top="+Math.round(screen.availHeight/4);
			var popUp=window.open(popup_url ,'Reporte','menubar=no,location=no,toolbar=no,status=yes,scrollbars=yes,titlebar=no,directories=no,'+size+posicion);
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



