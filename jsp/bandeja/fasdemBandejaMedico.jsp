<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@page
	import="ve.gob.dem.framework.recursos.*,java.util.Date,java.text.NumberFormat,java.util.ArrayList,ve.gob.dem.fasdem.bean.*,java.text.SimpleDateFormat"%>
<tiles:insert page="/jsp/plantilla/plantilla.jsp">


	<%
		ArrayList List = (ArrayList) request.getAttribute("lista");

			String C_A = (String) request.getAttribute("C_Atencion");
			String C_P = (String) request.getAttribute("C_Pendientes");
			String C_ATEND = (String) request.getAttribute("C_Atendidos");

			String cl = (String) request.getAttribute("usuario_bandeja");
			SiniestroBandeja list;
			SimpleDateFormat formato = new SimpleDateFormat(
					"dd/MM/yyyy - HH:mm");

			NumberFormat nf = NumberFormat.getInstance();
			nf.setGroupingUsed(true);
			nf.setMaximumFractionDigits(0);
			String dateOut;
			dateOut = Utilidad.DateToString(new Date(),
					"dd/MM/yyyy-hh:mm a");
			
			String dateOut2="";
			if(request.getAttribute("fecha")!=null){
				dateOut2=(String) request.getAttribute("fecha");
			}else{
				dateOut2=Utilidad.DateToString(new Date(),"dd/MM/yyyy");
			}
			
			
	%>






	<tiles:put name="titulo" direct="true">
		<bean:message key="principal.titulo" bundle="etiquetas" />
	</tiles:put>

	<tiles:put name="titulopagina"
		content="Siniestros pendientes por Notas Medicas" direct="true" />
	<tiles:put name="itemsup" content="" direct="true" />
	<tiles:put name="itemsdown" content="" direct="true" />
	<tiles:put name="cuerpo" direct="true">
		<div style="overflow: visible">

		<table class="tabla" width="600" cellpadding="2" cellspacing="1"
			border="0">

			<tr>

				<td width="30%">Fecha:<input class="black" type="text" style="width: 100px"
					id=true name="fecha" value="<%=dateOut2%>" 
					onfocus="Javascript:showCalendarSelected2(document.forms[0].fecha,document.forms[0].fecha,'dd/mm/yyyy','es',1);blur();" />
<img
					src="<%=request.getContextPath()%>/images/f_pts.gif" width="5"
					height="9"> <span class="grayplink"><a
					onclick="javascript:document.forms[0].submit();" href="#">Actualizar</a></span><br>
				</td>
			</tr>
			



			<tr>
				<td width="790" height="20" bgcolor="#FFFFFF" align="right"
					style="padding-left: 20px; padding-bottom: 0px; padding-top: 0px;"><span
					class="grayplink">Actualizado al: <%=dateOut%></span><br>
				</td>
			</tr>

			<tr>
				<td width="790" height="20" bgcolor="#FFFFFF" align="right"
					style="padding-left: 20px; padding-bottom: 0px; padding-top: 0px;"><span
					class="grayplink">Solicitudes atendidas: <strong>(<%=(C_ATEND != null) ? C_ATEND : "0"%>)</strong></span><br>
				</td>
			</tr>


			<tr>
				<td width="790" height="20" bgcolor="#FFFFFF" align="right"
					style="padding-left: 20px; padding-bottom: 0px; padding-top: 0px;"><span
					class="grayplink">Solicitudes en atención: <strong>(<%=(C_A != null) ? C_A : "0"%>)</strong></span><br>
				</td>
			</tr>

			<tr>
				<td width="790" height="20" bgcolor="#FFFFFF" align="right"
					style="padding-left: 20px; padding-bottom: 0px; padding-top: 0px;"><span
					class="grayplink">Solicitudes pendientes: <strong>(<%=(C_P != null) ? C_P : "0"%>)</strong></span><br>
				</td>
			</tr>

			



		</table>



		<table class="tabla" width="600" cellpadding="2" cellspacing="1"
			border="0">



			<%
				if (List != null) {
			%>



			<tr class="tituloCabecera">

				<td>Tipo de Trámite</td>
				<td>Datos de la Solicitud</td>
				<td>Fecha Registro</td>
				<td>Anexos</td>
				<td>Atender</td>

			</tr>


			<%
				for (int i = 0; i != List.size(); i++) {
								list = (SiniestroBandeja) List.get(i);
			%>


			<tr class="item" bgcolor="white">

				<%
					pageContext
											.setAttribute("numero", list.getNumero());
				%>
				<td><%=list.getNumero()%> <br>
				<%=list.getTipoTramite()%> <br>
				</td>
				<td><br>
				<strong>Causa de Ingreso:</strong><br><%=(list.getIdCausaIngreso() == null)
									? ""
									: list.getIdCausaIngreso()%> <br>
				<br>
				<br>
				<strong>Observaciones:</strong><br><%=(list.getObservacion() == null)
									? ""
									: list.getObservacion()%> <br>
				<br>
				</td>
				<td><%=Utilidad.DateToString(
									list.getFechaIngreso(), "dd/MM/yyyy")
									+ "<br>"
									+ Utilidad.DateToString(
											list.getHoraIngreso(), "hh:mm a")%></td>
											<td>
				
				<div class="parametro titulo cgp"><a class="red" href="#"
					onclick="javascript:openWinScroll2('<%=request.getContextPath()%>/security/upload/viewFile.do?idSiniestro=<%=list.getId_siniestro()%>&anio=<%=list.getAnio_siniestro()%>','name',500,500);">Ver
				Anexos</a></div>

				</td>


				<%
					if (list.getId_estatus() == 0) {
				%>


				<%
					if (cl.equals(list.getLoginProcesa())) {
				%>

				
				<td align="center" valign="middle"
					style="padding-left: 10px; padding-right: 10px; padding-bottom: 8px; padding-top: 8px;"><img
					onclick="cargaAps('<%=list.getId()%>','<%=list.getId_siniestro()%>')"
					src="<%=request.getContextPath()%>/images/ic3d_enviado.gif"
					width="14" height="13" title="Procesar"></td>

				<%
					} else {
				%>

				<td valign="middle" align="center" colspan="2" class="grayplink"
					style="padding-left: 10px; padding-right: 10px; padding-bottom: 5px; padding-top: 5px;"><b>Siniestro
				en Atención <br>
				(<%=list.getDatosProcesa()%>)</b><br>
				</td>

				<%
					}
				%>

				<%
					}
				%>

				<%
					if (list.getId_estatus() == 1) {
				%>

				<td colspan="2" valign="middle" align="center" class="blueblink"
					style="padding-left: 10px; padding-bottom: 2px; padding-top: 2px;"><img
					src="<%=request.getContextPath()%>/images/f_pts.gif" width="5"
					height="9"> <span class="grayplink"><a
					onclick="ir1('2','<%=list.getId()%>')" href="#">Tomar Caso</a></span><br>
				</td>

				<%
					}
				%>



			</tr>
			
				
			

			<%
				}
			%>


			<%
				} else {
			%>

			<tr>
				<td align="center" class="grayp"
					style="padding-left: 20px; padding-bottom: 5px;">
				<div class="grayp"></div>
				<strong>No existen solicitudes pendientes por atención</strong>
				<div class="grayp"></div>
				</td>
			</tr>


			<%
				}
			%>




		</table>


		</div>

		<input id="accion" type="hidden" name="accion" value="">
		<input id="siniestro" type="hidden" name="siniestro" value="">
		<input id="id_siniestro" type="hidden" name="id_siniestro" value="">


	</tiles:put>

</tiles:insert>



<script language="JavaScript">
	function ir(acc,valor){
    
		if(confirm('¿Esta seguro que desea realizar esta operación?')){
			document.getElementById('accion').value=acc;
			document.getElementById('siniestro').value=valor;
			document.forms[0].submit();
		}
			
	}

	function ir1(acc,valor){
	    
		
			document.getElementById('accion').value=acc;
			document.getElementById('siniestro').value=valor;
			document.forms[0].submit();
			//fd/security/notaMedica/addNotaMedica.do?idSiniestro=748993
			
	}
	function cargaAps(valor,acc){
		document.getElementById('siniestro').value=valor;
		document.getElementById('id_siniestro').value=acc;
		
		//document.forms[0].action='<%=request.getContextPath()%>/security/bandeja/puenteOnline.do';
		//document.forms[0].submit();
		var popup_url='<%=request.getContextPath()%>/security/notaMedica/addNotaMedica.do?idSiniestro='+acc+'&id='+valor;
		openWinScroll(popup_url,'pagina','800','600');
	
}	
	
	

	
	function openWinScroll(popup_url,name,width,height) 
	{
		var size = ',width=' + width + ',height=' + height;
		var posicion = ',left='+Math.round(screen.availWidth/4)+',top='+Math.round(screen.availHeight/4);
		var popUp=window.open(popup_url ,'Reporte','menubar=no,location=no,toolbar=no,status=yes,scrollbars=yes,titlebar=no,directories=no,'+size+posicion);
			//window.open(popup_url ,'nada','');
		popUp.opener=self;
	}
	function openWinScroll2(popup_url, name, width, height) {

		var size = ',width=' + width + ',height=' + height;
		var posicion = ",left=" + Math.round(screen.availWidth / 4) + ",top="
				+ Math.round(screen.availHeight / 4);
		var popUp = window
				.open(
						popup_url,
						name,
						'menubar=yes,location=no,toolbar=no,status=no,scrollbars=yes,titlebar=no,directories=no,resizable=no'
								+ size + posicion);
		popUp.opener = self;

	}

</script>





