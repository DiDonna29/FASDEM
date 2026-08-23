<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@page import="ve.gob.dem.framework.recursos.*,java.util.Date,java.text.NumberFormat,java.util.ArrayList,ve.gob.dem.fasdem.bean.*,java.text.SimpleDateFormat"%>
<tiles:insert page="/jsp/plantilla/plantilla.jsp">

<%
		
		ArrayList List=(ArrayList)request.getAttribute("lista");
		String primera = (String)request.getAttribute("primera");
        DetalleFacturaPago list;
        String estatus = (String)request.getAttribute("idEstatus");
        String des_esta = (String)request.getAttribute("des_esta");
        
	     SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	     NumberFormat nf = NumberFormat.getInstance();
		 nf.setGroupingUsed(true);
		 nf.setMaximumFractionDigits(2);
	 	 String dateOut;
     	 dateOut = Utilidad.DateToString(new Date(), "dd/MM/yyyy");
     	 int dateOut1;
     	 dateOut1 = (Integer.parseInt(Utilidad.DateToString(new Date(), "yyyy")));
		
%>



	<tiles:put name="titulo" direct="true">
		<bean:message key="principal.titulo" bundle="etiquetas" />
	</tiles:put>

	<tiles:put name="titulopagina" content="Inicio / Administradores / Reversar Anulados"
		direct="true" />
	<tiles:put name="itemsup" content="/jsp/comunes/items.jsp" />
    <tiles:put name="itemsdown" content=" " direct="true" />
	<tiles:put name="cuerpo" direct="true">
	<div style="overflow: visible"></div>
	
	
<%
	 String mensaje = (String) request.getAttribute("mensaje");
	 if (mensaje != null){%>
 	<script>
 	 	alert('<%=mensaje%>');
 	</script>
 <%}
%>
	
			<div style="overflow: visible">
 
				<div class="etiqueta titulo cgp">Año Siniestro</div>
				<div class="parametro titulo ">
				
				<select  name="anio_busca" id="anio_busca" class="cgp">
				    <option value="0" selected="selected" class="seleccione"></option>
					
					
						 <%
							for (int k=2010;k!=dateOut1+1 ;k++){
								
			      		 %>		
								
								<option value=<%=k%>><%=k%></option>
								
					    <%}%>	

					
		        </select>
				</div>
				
				
					<div class="etiqueta titulo cgp">Codigo de Siniestro</div>
				<div class="parametro titulo ">
				
				<input type="text" name="cod_sin"  id="cod_sin" class="cgp">
				</div>

					<div class="sp5"></div>
				<div align="right">
				<input class="boton" value="Buscar por Codigo" type="button"  onclick="buscarcodigosin(2);" />
				</div>
				
				<div style="border-bottom-color: #EEEEEE; border-bottom-width: thin; border-bottom-style: solid">&nbsp;</div>
				<div style="border-bottom-color: #EEEEEE; border-bottom-width: thin; border-bottom-style: solid">&nbsp;</div>
	

			
			<table class="tabla" width="600" cellpadding="2" cellspacing="1"
				border="0">
				
				
				
	 <% if (List!=null && List.size()!=0){%>				
				
				
				
	
				<tr class="tituloCabecera">
                    <td align="center">Nro Siniestro</td>
					<td align="center">Estatus</td>	
<!--				    <td align="center">Tipo de Hoja</td>	-->
<!-- 				    <td align="center">Reporte</td> -->
				    
				    <td align="center">Cambiar Status</td>
				</tr>
	
				<%
						for (int i=0;i!=List.size();i++){
						list = (DetalleFacturaPago) List.get(i);
      			%>  			
				 
				<tr class="item" bgcolor="white" >

				
					<td align="center"><%=list.getCodSiniestroCompl()%></td>
					<td align="center"><%=des_esta%></td>
<!--					<td align="center"><%=list.getCodigo_siniestro()%></td> -->
										
		                
					

					<%if (estatus.equals(Constantes.EtapaSiniestroAnulado) || estatus.equals(Constantes.EtapaSiniestroAnuladoNoProcedente) || estatus.equals(Constantes.EtapaSiniestroAnuladoError) || estatus.equals(Constantes.EtapaSiniestroAnuladoRechazado)){ %>
						<td class="grayplink" align="center"> 
		                   <img onclick="irAnulacion(3,'<%=list.getId_siniestro()%>', '<%=list.getCodSiniestroCompl()%>');" style="cursor:hand" src="<%=request.getContextPath()%>/images/ic3d_enviado.gif" width="14" height="13" title="Cambiar">
		                </td>
					<%}else{ %>
					
						<td class="grayplink" align="center"> 
		                   ---
		                </td>
					
					<%} %>
		
				</tr>
				
		<%}%>

	<%}else{%>	
	
				<%if (primera!=null){ %>
	
					 <tr>
						<td align="center" class="grayp" style="padding-left: 20px; padding-bottom: 5px;">
							<div class="grayp"></div>
							<strong>No existe Siniestro Con ese Codigo</strong>
							<div class="grayp"></div>
						</td>
					</tr>
	
				<%}%> 
	<%}%>    
	
	
	
		

	
			</table>
			
			

			</div>
			
			<input id="accionPago" type="hidden" name="accionPago" value="">
            <input id="cod1" type="hidden" name="cod1" value="">
            <input id="cod2" type="hidden" name="cod2" value="">
            
	</tiles:put>
	
</tiles:insert>



<script language="JavaScript">
	
	
	function buscarcodigosin(acc){
		
		  
      if(document.getElementById('cod_sin').value!=''){
    	  document.getElementById('accionPago').value=acc;
          document.forms[0].submit();
      }else{
       alert("Debe especificar el codigo de siniestro");
      }
		
		
	}
	
	
	function irAnulacion(acc,codig,codig2){

		if(confirm('¿Esta seguro que desea realizar esta operación?')){
			document.getElementById('cod1').value=codig;
			document.getElementById('cod2').value=codig2;
			document.getElementById('accionPago').value=acc;
	        document.forms[0].submit();
        }
		
	}
	
	function ir(acc){
  
			if(document.getElementById('cod1').value!=''){
				if(document.getElementById('cod2').value!=''){
		
					document.getElementById('accionPago').value=acc;
			        document.forms[0].submit();
				}else{
			          alert("Debe especificar el codigo de la PreOrden");
			     }
		   }else{
		  	 alert("Debe especificar el codigo de la PreOrden");
		   }
	        
		
	}

	function checkedAll (frm1) {
		checked=false;
		var aa= document.forms[0];

		for (var i =0; i < aa.elements.length; i++) 
		{
			 if (aa.elements[i].checked == false)
	         {
	          checked = true
	         }
	       else
	         {
	         checked = false
	         }

	         
		 aa.elements[i].checked = checked;
		}
	      }



	function openWinScroll2(popup_url, name, width, height) {

		var size = ',width=' + width + ',height=' + height;
		var posicion = ",left=" + Math.round(screen.availWidth / 4) + ",top="
				+ Math.round(screen.availHeight / 4);
		var popUp = window
				.open(
						popup_url,
						name,
						'menubar=yes,location=no,toolbar=no,status=no,scrollbars=no,titlebar=no,directories=no,resizable=yes'
								+ size + posicion);
		popUp.opener = self;

	}
	

</script>





