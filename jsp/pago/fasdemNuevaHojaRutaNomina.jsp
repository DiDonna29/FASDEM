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
        
        HojaRuta h = new HojaRuta();
        		
        h=(HojaRuta)request.getAttribute("hoja");
        
	     SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	     NumberFormat nf = NumberFormat.getInstance();
		 nf.setGroupingUsed(true);
		 nf.setMaximumFractionDigits(2);
	 	 String dateOut;
     	 dateOut = Utilidad.DateToString(new Date(), "dd/MM/yyyy");
     	 
     	 UnidadTributaria unid =null;
     	 TipoEmpleado tipE =null;
     	 
     	 Double t;
     	 
     	 if(request.getAttribute("tot")==null){
     		 t=0.00;
     	 }else{
     		t=Double.parseDouble(request.getAttribute("tot").toString());
     	 }
     	 
     	 
%>



	<tiles:put name="titulo" direct="true">
		<bean:message key="principal.titulo" bundle="etiquetas" />
	</tiles:put>

	<tiles:put name="titulopagina" content="Inicio / Pagos / Busqueda de Pagos"
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

		        <div class="etiqueta titulo cgp">Hoja de Ruta Nro (Nomina)</div>
				<div class="parametro titulo ">
				<div class="etiqueta titulo cgp"><%=h.getNumero()%></div>
				</div>
				
				
				<div class="etiqueta titulo cgp">Fecha Hoja de Ruta</div>
				<div class="parametro titulo ">
				<div class="etiqueta titulo cgp"><%=formato.format(h.getFecha())%></div>
				</div>
				
				<div class="etiqueta titulo cgp">Tipo</div>
				<div class="parametro titulo ">
				<div class="etiqueta titulo cgp"><%=h.getTipo().getDescripcion() %></div>
				</div>
				
				<div class="etiqueta titulo cgp">Cantidad de Pagos</div>
				<div class="parametro titulo ">
				<div class="etiqueta titulo cgp"><%=h.getCantidad() %></div>
				</div>
				
				<div class="etiqueta titulo cgp">Detalle Hoja de Ruta</div>
				<div class="parametro titulo ">
				<div class="etiqueta titulo cgp"><img onclick="ir(4);" style="cursor:hand" src="<%=request.getContextPath()%>/images/page.png" width="14" height="13" title="Agregar a Hoja de Ruta"></div>
				</div>

				

				<div style="border-bottom-color: #EEEEEE; border-bottom-width: thin; border-bottom-style: solid">&nbsp;</div>
                <div style="border-bottom-color: #EEEEEE; border-bottom-width: thin; border-bottom-style: solid">&nbsp;</div>
			
 
             
						<%if (Constantes.MontoMaximoHoja>t){ %>
						<div class="etiqueta titulo cgp">Codigo de Pre Orden</div>
						
						<div class="parametro titulo cgp">
						
							<input type="text" name="codigo1" id="codigo1"  size="4" value="" maxlength="4" class="cgp"> - <input type="text" name="codigo2" id="codigo2"  value="" size="10" maxlength="5" class="cgp"> 
						
						</div>
						
						
						<div class="sp5"></div>
						<div align="right">
						<input class="boton" value="Buscar" type="button"  onclick="ircodigo(7);" />
						</div>
						
						
						
				<%}else{ %>
				
					     <tr>
						<td align="center" class="grayp" style="padding-left: 20px; padding-bottom: 5px;">
							<div class="grayp" align="center"></div>
							<strong>Se ha superado el monto maximo, no se pueden agragar mas pagos</strong>
							<div class="grayp"></div>
						</td>
					</tr>
					     
				<%}%>
						
						
						
				
 
 
				
				
				
				
				<div style="border-bottom-color: #EEEEEE; border-bottom-width: thin; border-bottom-style: solid">&nbsp;</div>
                <div style="border-bottom-color: #EEEEEE; border-bottom-width: thin; border-bottom-style: solid">&nbsp;</div>
                <div style="border-bottom-color: #EEEEEE; border-bottom-width: thin; border-bottom-style: solid">&nbsp;</div>
		
			<table class="tabla" width="600" cellpadding="2" cellspacing="1"
				border="0">
				
				
				
	 <% if (List!=null && List.size()!=0){%>	

	
				<tr class="tituloCabecera">

					<td>Pre Orden</td>
					<td>Beneficiario</td>
					<td>Nro Causado</td>
				    <td>Seleccionar</td>					
					
				</tr>
	
	

	  <%
		for (int i=0;i!=List.size();i++){
		list = (PreOrdenPago) List.get(i);
      %>  		
				 
				 <tr class="item" bgcolor="white" >
				
				
					<td><%=list.getCod_completo()%></td>
					
					<td><%=(list.getProveedor().getId()==44)?(list.getTitular()!=null)?list.getTitular().getNombres()+ " " + list.getTitular().getApellidos():"":list.getProveedor().getNombre()%></td>
					
					
					<%if (list.getHoja()==null){ %>
					  
					  <%if (h.getTipo().getId()==3 || h.getTipo().getId()==4 || h.getTipo().getId()==5){ %>
					     
					     <%if (list.getProveedor().getId()==44){ %>
					     
					      
					         
					                  <td><input type="text" name="nro<%=list.getId()%>" size="10" maxlength="10" class="cgp"></td>
					
										<input id="cod<%=list.getId()%>" type="hidden" name="cod<%=list.getId()%>" value="<%=list.getCod_completo()%>">
										<td class="grayplink" align="center"> 
							                   <img onclick="agregar(2,'<%=list.getId()%>');" style="cursor:hand" src="<%=request.getContextPath()%>/images/page_add.png" width="14" height="13" title="Agregar a Hoja de Ruta">
							            </td>
					         
					     
					     		<%}else{%>
					     		
					     				<b><td colspan="2"><%="La PreOrden no corresponde a un Reembolso"%></td></b>
					     		
					     	    <%}%>	
					     		
					     
					     <%}else{%>
					     
					          <%if (list.getProveedor().getId()==44){ %>
					          
					               <b><td colspan="2"><%="Debe incluir en una Hoja de Ruta Tipo Reembolso"%></td></b>
					          
					     
					     		<%}else{%>
					     		
					     				<td><input type="text" name="nro<%=list.getId()%>" size="10" maxlength="10" class="cgp"></td>
					
										<input id="cod<%=list.getId()%>" type="hidden" name="cod<%=list.getId()%>" value="<%=list.getCod_completo()%>">
										<td class="grayplink" align="center"> 
							                   <img onclick="agregar(2,'<%=list.getId()%>');" style="cursor:hand" src="<%=request.getContextPath()%>/images/page_add.png" width="14" height="13" title="Agregar a Hoja de Ruta">
							            </td>
					     		
					     	    <%}%>	
					     
					     	
					     
					     
					     <%} %>
					     

					
		             
		             <%}else{ %>
		 
		             	<b><td colspan="2"><%="Hoja de Ruta " + list.getHoja().getNumero()%></td></b>
		             
					 <%} %>
					
					
				</tr>
	
				
	<%}%>	

	 				
					
					
				
					
					
					
					

					
				<tr align="left">
					<td align="left" colspan="6">
					<input id="desde_select" type="hidden" name="desde_select" value="<%=request.getAttribute("dselect")%>">
           			<input id="hasta_select" type="hidden" name="hasta_select" value="<%=request.getAttribute("hselect")%>">

                    
                    
                    </td>
				</tr>

	
	<%}else{%>	
	
				<%if (primera!=null){ %>
	
					 <tr>
						<td align="center" class="grayp" style="padding-left: 20px; padding-bottom: 5px;">
							<div class="grayp"></div>
							<strong>No existen PreOrdenes (Verifique el Estatus)</strong>
							<div class="grayp"></div>
						</td>
					</tr>
	
				<%}%> 
	<%}%>    
	

			</table>
			
			
			
			
			
				
				<div  align="center" >
				   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</div>
				
				
				
			
			
		
			

			<input id="accionPago" type="hidden" name="accionPago">
			<input id="id_hoja" type="hidden" name="id_hoja" value="<%=h.getId()%>">
		    <input id="pre" type="hidden" name="pre">
           
            
	</tiles:put>
	
</tiles:insert>



<script language="JavaScript">

	function ir(acc){
		  
		document.getElementById('accionPago').value=acc;
	    document.forms[0].submit();
		
	}

	
	
	
	
	function agregar(acc,p){
		  
		document.getElementById('accionPago').value=acc;
		document.getElementById('pre').value=p;
	    document.forms[0].submit();
		
	}
	
	
	


	function buscarfechas(acc){
  
		
				
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



	function ircodigo(acc){
		  
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





