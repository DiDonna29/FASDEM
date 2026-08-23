<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@page import="ve.gob.dem.framework.recursos.*,java.util.Date,java.text.NumberFormat,java.util.ArrayList,ve.gob.dem.fasdem.bean.*,java.text.SimpleDateFormat"%>
<tiles:insert page="/jsp/plantilla/plantilla.jsp">

<%
		
		PreOrdenPago pre=(PreOrdenPago)request.getAttribute("preorden");
		ArrayList List=(ArrayList)request.getAttribute("lista");
		String primera = (String)request.getAttribute("primera");
        DetalleFacturaPago list;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		    
        
        	NumberFormat nf = NumberFormat.getInstance();
			 nf.setGroupingUsed(true);
			 nf.setMaximumFractionDigits(2);
		 	 String dateOut;
	     	 dateOut = Utilidad.DateToString(new Date(), "dd/MM/yyyy");
	     	 ArrayList ListaU=(ArrayList)request.getAttribute("listaUnidad");
	     	 ArrayList TipoE=(ArrayList)request.getAttribute("tipoEmpleado");
	     	 UnidadTributaria unid =null;
	     	 TipoEmpleado tipE =null;
%>



	<tiles:put name="titulo" direct="true">
		<bean:message key="principal.titulo" bundle="etiquetas" />
	</tiles:put>

	<tiles:put name="titulopagina" content="Inicio / Pagos / Busqueda de Siniestros"
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

			
			
			  <div class="etiqueta titulo cgp">Codigo de PreOrden</div>
				<div class="parametro titulo cgp">
				
				<%=pre.getCod_completo()%>
				<input id="codpre" type="hidden" name="codpre" value="<%=pre.getCod_completo()%>">
		        
		      </div>
		      
		      
		     <div class="etiqueta titulo cgp">Fecha de PreOrden</div>
				<div class="parametro titulo cgp">
				
				<%=formato.format(pre.getFecha_preorden())%>
		        
		      </div> 
		      
		     
		     
		     
		     
		     <%if(pre.getProveedor()!=null && pre.getProveedor().getId()!=Constantes.ProveedorTitular && pre.getProveedor().getId()!=Constantes.ProveedorTercero){%>
			
					<div class="etiqueta titulo cgp">Proveedor</div>
					<div class="parametro titulo cgp">
					
					<%=pre.getProveedor().getNombre() + " (" + pre.getProveedor().getRif() + ")" %>
			        <input type="hidden" name="idProveedor" value="<%=pre.getProveedor().getId()%>">
			        </div>
		        
		      <%}else{%>  
		      
			        <div class="etiqueta titulo cgp">Beneficiario</div>
					<div class="parametro titulo cgp">
					
					<%=pre.getTitular().getNombres() + " " + pre.getTitular().getApellidos() + " (" + pre.getTitular().getCedula() + ")" %>
			        <input type="hidden" name="idProveedor" value="<%=pre.getTitular().getCedula()%>">
			        </div>
		        
		      <%}%>
		        
 
 
				<div class="etiqueta titulo cgp">Tipo Empleado</div>
				<div class="parametro titulo cgp">
				
				<select name="tipoempleado" id="tipoempleado" class="cgp">
				    <option value="-1" selected="selected" class="seleccione">[SELECCIONAR]</option>
					
					
						 <%
							for (int k=0;k!=TipoE.size();k++){
								tipE = (TipoEmpleado) TipoE.get(k);
			      		 %>		
								
								<option value=<%=tipE.getId()%>><%=tipE.getDescripcion()%></option>
						<%}%>	
					
					
					
		        </select>
		        
		        </div>
		        
		        
      <%if(pre.getProveedor()!=null && pre.getProveedor().getId()!=Constantes.ProveedorTitular && pre.getProveedor().getId()!=Constantes.ProveedorTercero){%>

				<div style="border-bottom-color: #EEEEEE; border-bottom-width: thin; border-bottom-style: solid">&nbsp;</div>

                <div style="border-bottom-color: #EEEEEE; border-bottom-width: thin; border-bottom-style: solid">&nbsp;</div>



				
				


				<div class="etiqueta titulo cgp">Fecha_siniestro_desde</div>
				<div class="parametro titulo ">
				<input type="text" name="fechaInicio" id="fechaInicio" value="" onfocus="javascript:showCalendarSelected2(document.forms[0].fechaInicio, document.forms[0].fechaInicio, 'dd/mm/yyyy','es',1);blur();" class="cgp">
				</div>
 
 
				<div class="etiqueta titulo cgp">Fecha_siniestro_hasta</div>
				<div class="parametro titulo ">
				<input type="text" name="fechaFin" id="fechaFin" value="" onfocus="javascript:showCalendarSelected2(document.forms[0].fechaFin, document.forms[0].fechaFin, 'dd/mm/yyyy','es',1);blur();" class="cgp">
				</div>
				

				<div class="sp5"></div>
				<div align="right">
				<input class="boton" value="buscar" type="button"  onclick="ir(7);" />
				</div>

 				<%}%>


				<div style="border-bottom-color: #EEEEEE; border-bottom-width: thin; border-bottom-style: solid">&nbsp;</div>
                <div style="border-bottom-color: #EEEEEE; border-bottom-width: thin; border-bottom-style: solid">&nbsp;</div>
			
 
				<div class="etiqueta titulo cgp">Codigo de Siniestro</div>
				<div class="parametro titulo ">
				
				<input type="text" name="cod_sin" id="cod_sin"  class="cgp">
				</div>
				
				
				<div class="sp5"></div>
				<div align="right">
				<input class="boton" value="Buscar por Codigo Siniestro " type="button"  onclick="ircodigo(8);" />
				
				</div>
				
				<div style="border-bottom-color: #EEEEEE; border-bottom-width: thin; border-bottom-style: solid">&nbsp;</div>
                <div style="border-bottom-color: #EEEEEE; border-bottom-width: thin; border-bottom-style: solid">&nbsp;</div>
			    <div style="border-bottom-color: #EEEEEE; border-bottom-width: thin; border-bottom-style: solid">&nbsp;</div>




			<table class="tabla" width="600" cellpadding="2" cellspacing="1"
				border="0">
				
				
				
				 <% if (List!=null && List.size()!=0){%>				
							
							 
							 
							 
				<%if(pre.getProveedor()!=null && pre.getProveedor().getId()!=Constantes.ProveedorTitular && pre.getProveedor().getId()!=Constantes.ProveedorTercero){%>
				
						 <tr>
								<td align="center" colspan="6" class="grayp" style="padding-left: 20px; padding-bottom: 5px;">
									<div class="grayp"></div>
									<strong><%=pre.getProveedor().getNombre() + " (" + pre.getProveedor().getRif() + ")" %></strong>
									<div class="grayp"></div>
								</td>
						</tr>
					
				<%}else{%>	
				
						<tr>
								<td align="center" colspan="6" class="grayp" style="padding-left: 20px; padding-bottom: 5px;">
									<div class="grayp"></div>
									<strong><%=pre.getTitular().getNombres()   + " " + pre.getTitular().getApellidos() +  " (" + pre.getTitular().getCedula() + ")" %></strong>
									<input id="cedula" type="hidden" name="cedula" value="<%=pre.getTitular().getCedula()%>">
           							<input id="nombres" type="hidden" name="nombres" value="<%=pre.getTitular().getApellidos()%>">
                    				<input id="apellidos" type="hidden" name="apellidos" value="<%=pre.getTitular().getNombres()%>">
									<div class="grayp"></div>
								</td>
						</tr>
				
				<%}%>
							
				
							<tr class="tituloCabecera">
			
								<td>Nro de Siniestro</td>
								<td>Nro de Factura</td>
								<td>Fecha de Factura</td>
								<td>Monto de Factura</td>
							    <td>Seleccionar</td>					
								
							</tr>
				
				
			
				  <%
					for (int i=0;i!=List.size();i++){
					list = (DetalleFacturaPago) List.get(i);
			      %>  		
							 
							 <tr class="item" bgcolor="white" >
							
							
								<td><%=list.getAniomes() + "-" + list.getCodigo_siniestro() + "-" + list.getSub_codigo_siniestro()%></td>
								<td><%=list.getNro_factura()%></td>
								<td><%=(list.getFecha_factura()!=null)?formato.format(list.getFecha_factura()):""%></td>
								<td><%=nf.format(list.getMonto_Factura())%></td>
								
								<td align="center"><INPUT type="checkbox"   value="<%=list.getId_factura()%>" NAME="listfact"></td>		
								
								
							</tr>
				
							
				<%}%>	
	
			
	
	 				<tr class="item" bgcolor="white" >
			            <td align="right" colspan="6">Seleccionar todas las facturas:&nbsp;&nbsp; 
						<input type='button' name='checkall' style="width: auto" onclick='checkedAll(listfact);'>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					</tr>
					

					
				<tr align="left">
					<td align="left" colspan="6">
					
					<%if(pre.getProveedor()!=null && pre.getProveedor().getId()!=Constantes.ProveedorTitular && pre.getProveedor().getId()!=Constantes.ProveedorTercero){%>
					<input id="prov_select" type="hidden" name="prov_select" value="<%=pre.getProveedor().getId()%>">
           			<%}%>
					
           			<input id="desde_select" type="hidden" name="desde_select" value="<%=request.getAttribute("dselect")%>">
           			<input id="hasta_select" type="hidden" name="hasta_select" value="<%=request.getAttribute("hselect")%>">
                    <input id="tip_select" type="hidden" name="tip_select" value="<%=request.getAttribute("tipselect")%>">
                    </td>
				</tr>

	
	<%}else{%>	
	
				<%if (primera!=null){ %>
	
					 <tr>
						<td align="center" class="grayp" style="padding-left: 20px; padding-bottom: 5px;">
							<div class="grayp"></div>
							<strong>No existen facturas sin orden de pago</strong>
							<div class="grayp"></div>
						</td>
					</tr>
	
				<%}%> 
	<%}%>    
	

			</table>
			
	
			 <% if (List!=null && List.size()!=0){%>		
				
				<div  align="center" >
				   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</div>
				
				
				<div  align="center" >
				    <input class="boton" value="Agregar Facturas a la PreOrden N° <%=pre.getCod_completo()%> " type="button"  onclick="iragregar(10);" />
				</div>
			
			<%}%> 
			
			
			<div  align="center" >
				   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</div>
				
				
				<div  align="center" >
				    <input class="boton" value="Regresar" type="button"  onclick="regresar(52);" />
				</div>

		
			

			<input id="accionPago" type="hidden" name="accionPago">
			<input id="anio_pre1" type="hidden" name="anio_pre1" value="<%=request.getAttribute("anio_pre")%>">
           
            
	</tiles:put>
	
</tiles:insert>



<script language="JavaScript">

		function ir(acc){
			
				if(document.getElementById('fechaInicio').value!=''){
						 if(document.getElementById('fechaFin').value!=''){
								

							 	document.getElementById('accionPago').value=acc;
						        document.forms[0].submit();
								
							 
						 }else{
						   alert("Debe especificar la fecha fin");
						 }

					 }else{
					   alert("Debe especificar la fecha inicio");
					 }


		}



		function iragregar(acc){
			
            document.getElementById('accionPago').value=acc;
			document.forms[0].submit();
	
	     }



		function ircodigo(acc){
			

			
			if(document.getElementById('cod_sin').value!=''){
		    	  document.getElementById('accionPago').value=acc;
		          document.forms[0].submit();
		      }else{
		       alert("Debe especificar el codigo de siniestro");
		      }


			   
			}

		function regresar(acc){
			
							

						 	document.getElementById('accionPago').value=acc;
					        document.forms[0].submit();
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
		


</script>
