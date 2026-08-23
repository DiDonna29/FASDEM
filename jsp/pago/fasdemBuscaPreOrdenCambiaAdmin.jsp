<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@page import="ve.gob.dem.framework.recursos.*,java.util.Date,java.text.NumberFormat,java.util.ArrayList,ve.gob.dem.fasdem.bean.*,java.text.SimpleDateFormat"%>
<tiles:insert page="/jsp/plantilla/plantilla.jsp">

<%

		
	    ArrayList List=(ArrayList)request.getAttribute("lista");
		String primera = (String)request.getAttribute("primera");
        PreOrdenPago list;
        
        
	    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	
		 NumberFormat nf = NumberFormat.getInstance();
		 nf.setGroupingUsed(true);
		 nf.setMaximumFractionDigits(2);
	 	 String dateOut;
     	 dateOut = Utilidad.DateToString(new Date(), "dd/MM/yyyy");
		
%>



	<tiles:put name="titulo" direct="true">
		<bean:message key="principal.titulo" bundle="etiquetas" />
	</tiles:put>

	<tiles:put name="titulopagina" content="Inicio / Pagos / Busqueda de Pre Ordenes de Pago para reversar"
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
			
			

 
				<div class="etiqueta titulo cgp">Codigo de Pre Orden</div>
				
				<div class="parametro titulo cgp">
				
					<input type="text" name="codigo1" id="codigo1"  size="4" value="" maxlength="4" class="cgp"> - <input type="text" name="codigo2" id="codigo2" value="" size="10" maxlength="5" class="cgp"> 
				
				</div>

					<div class="sp5"></div>
				<div align="right">
				<input class="boton" value="Buscar por Codigo" type="button"  onclick="ir(1);" />
				</div>
				
				<div style="border-bottom-color: #EEEEEE; border-bottom-width: thin; border-bottom-style: solid">&nbsp;</div>
				<div style="border-bottom-color: #EEEEEE; border-bottom-width: thin; border-bottom-style: solid">&nbsp;</div>


	
			<table class="tabla" width="600" cellpadding="2" cellspacing="1"
				border="0">
				
				
				
			 <% if (List!=null && List.size()!=0){%>				

						<tr class="tituloCabecera">
		                    <td>Nro de Pre Orden</td>
							<td>Fecha de Pre Orden</td>
							<td>Estatus</td>	
						    <td>Detalle</td>	
						    <td>Reporte</td>
						    <td>Reversar</td>	
						</tr>
						
		
			  <%
				for (int i=0;i!=List.size();i++){
				list = (PreOrdenPago) List.get(i);
		      %>  		
						 
						<tr class="item" bgcolor="white" >
						
						
						
						
							<td><%=list.getCod_completo()%></td>
							<td><%=(list.getFecha_preorden()!=null)?formato.format(list.getFecha_preorden()):""%></td>
							<td><%=list.getEstatus().getDescripcion()%></td>
							
							<td class="grayplink" align="center"> 
			                   <img onclick="ir1(3,'<%=list.getCod_completo()%>');" style="cursor:hand" src="<%=request.getContextPath()%>/images/zoom.png" width="14" height="13" title="Detalle">
			                </td>
			                
							<td class="grayplink" align="center"> 
			                   <img onclick="javascript:openWinScroll2('<%=request.getContextPath()%>/security/pago/imprimirPreOrden.do?pre=<%=list.getAniomes() + "-" + list.getCodigo_preorden()%>','name',500,500);" style="cursor:hand" src="<%=request.getContextPath()%>/images/printer.png" width="14" height="13" title="Reporte">
			                </td>
			                
							
			<%if(list.getHoja()==null){ %>	
							
							<%if(list.getEstatus().getId()!=Constantes.StatusEnAdministracion){ %>
							
								<td class="grayplink" align="center"> 
				                   <img onclick="ir2(5,'<%=list.getCod_completo()%>');" style="cursor:hand" src="<%=request.getContextPath()%>/images/ic3d_enviado.gif" width="14" height="13" title="Reversar">
				                </td>	
			                
							<%}else{%>
							
							    <td class="grayplink" align="center"> 
			                     ---
			                    </td>	
							 
							<%}%>
				
				<%}else{%>			
							
							
					 <td class="grayplink" align="center"> 
				         Hoja de Ruta N° <%=list.getHoja().getNumero() %>
				     </td>	
							 
				<%}%>	
				
				
						</tr>
				
		
			<%}%>	
			

	
			<%}else{%>	
			
						<%if (primera!=null){ %>
			
							 <tr>
								<td align="center" class="grayp" style="padding-left: 20px; padding-bottom: 5px;">
									<div class="grayp"></div>
									<strong>Codigo de PreOrden no existe</strong>
									<div class="grayp"></div>
								</td>
							</tr>
			
						<%}%> 
			<%}%>    

	
			</table>

			</div>
			
			<input id="accionPago" type="hidden" name="accionPago" value="">
            <input id="cod1" type="hidden" name="cod1" value="">
          
            
	</tiles:put>
	
</tiles:insert>



<script language="JavaScript">
	function ir(acc){
  
		if(document.getElementById('codigo1').value!=''){
			if(document.getElementById('codigo2').value!=''){
	
				document.getElementById('accionPago').value=acc;
		        document.forms[0].submit();
			}else{
		          alert("Debe especificar el codigo de la PreOrden");
		     }
	   }else{
	  	 alert("Debe especificar el codigo de la PreOrden");
	   }
		
	}


	function ir1(acc,codig){
		
		document.getElementById('cod1').value=codig;
		document.getElementById('accionPago').value=acc;
        document.forms[0].submit();
		
	}


	function ir2(acc,codig){

		if(confirm('¿Esta seguro que desea realizar esta operación?')){
			document.getElementById('cod1').value=codig;
			document.getElementById('accionPago').value=acc;
	        document.forms[0].submit();
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





