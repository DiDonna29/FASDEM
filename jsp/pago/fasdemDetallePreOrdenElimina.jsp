<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@page import="ve.gob.dem.framework.recursos.*,java.util.Date,java.text.NumberFormat,java.util.ArrayList,ve.gob.dem.fasdem.bean.*,java.text.SimpleDateFormat"%>
<tiles:insert page="/jsp/plantilla/plantilla.jsp">

<%
		PreOrdenPago pre=(PreOrdenPago)request.getAttribute("preorden");
		ArrayList List=(ArrayList)request.getAttribute("lista");
		DetalleFacturaPago list;
       
        
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
	
	
	
	
	
			<div style="overflow: visible">
			
			
			<div class="etiqueta titulo cgp">Codigo de PreOrden</div>
				<div class="parametro titulo cgp">
				
				<%=pre.getCod_completo()%>
				<input id="codpre" type="hidden" name="codpre" value="<%=pre.getCod_completo()%>">
		        
		      </div>
		      
		      
		     <div class="etiqueta titulo cgp">Fecha de PreOrden</div>
				<div class="parametro titulo cgp">
				
				<%=formato.format(pre.getFecha_preorden())%>
		        
		      </div> 
		      

			<%if(pre.getProveedor()!=null && pre.getProveedor().getId()!=Constantes.ProveedorTitular && pre.getProveedor().getId()!=Constantes.ProveedorTercero ){ %>
				<div class="etiqueta titulo cgp">Proveedor</div>
				<div class="parametro titulo cgp">
				
				<%=pre.getProveedor().getNombre() + " (" + pre.getProveedor().getRif() + ")" %>
		        
		        </div>
		        
		     <%}else{ %>  
		        <div class="etiqueta titulo cgp">Beneficiario</div>
				<div class="parametro titulo cgp">
				
				<%=pre.getTitular().getNombres() + " " + pre.getTitular().getApellidos() +  " (" + pre.getTitular().getCedula() + ")" %>
		        
		        </div>
		         
		     <%} %>
		        

				<div style="border-bottom-color: #EEEEEE; border-bottom-width: thin; border-bottom-style: solid">&nbsp;</div>

                <div style="border-bottom-color: #EEEEEE; border-bottom-width: thin; border-bottom-style: solid">&nbsp;</div>



			
			<table class="tabla" width="600" cellpadding="2" cellspacing="1"
				border="0">
				
				
				
	 <% if (List!=null && List.size()!=0){%>				
			
				
	
				<tr class="tituloCabecera">

					<td>Nro de Siniestro</td>
					<td>Nro de Factura</td>
					<td>Fecha de Factura</td>
					<td>Monto de Factura</td>
				    <td>Eliminar</td>					
					
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
					
					<td align="center"> <img onclick="ir2(51,'<%=list.getId_factura()%>');" style="cursor:hand" src="<%=request.getContextPath()%>/images/cross.png" width="14" height="13" title="Eliminar"></td>		
					
					
				</tr>
	
				
	  <%}%>	
	
			
	


	
	<%}else{%>	
	
				
	
					 <tr>
						<td align="center" class="grayp" style="padding-left: 20px; padding-bottom: 5px;">
							<div class="grayp"></div>
							<strong>No existen facturas</strong>
							<div class="grayp"></div>
						</td>
					</tr>
	
				
	<%}%>    
	
	
	
		

	
			</table>
			
			
			
			
			
				
				<div  align="center" >
				   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</div>
				
				
				<div  align="center" >
				    <input class="boton" value="Regresar" type="button"  onclick="ir(52);" />
				</div>
			
		

		
			

			<input id="accionPago" type="hidden" name="accionPago">
			<input id="id_fact" type="hidden" name="id_fact">
			<input id="anio_pre1" type="hidden" name="anio_pre1" value="<%=request.getAttribute("anio_pre")%>">
           
            
	</tiles:put>
	
</tiles:insert>



<script language="JavaScript">
	function ir2(acc,fac){

		if(confirm('¿Esta seguro que desea realizar esta operación?')){
		document.getElementById('accionPago').value=acc;
		document.getElementById('id_fact').value=fac;
        document.forms[0].submit();
		}
	}


	function ir(acc){
		  
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





