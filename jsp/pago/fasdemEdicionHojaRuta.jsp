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
	     SimpleDateFormat formato_anio = new SimpleDateFormat("yyyy");
	     NumberFormat nf = NumberFormat.getInstance();
		 nf.setGroupingUsed(true);
		 nf.setMaximumFractionDigits(2);
	 	 String dateOut;
     	 dateOut = Utilidad.DateToString(new Date(), "dd/MM/yyyy");
     	 
     	 
     	 
     	 UnidadTributaria unid =null;
     	 TipoEmpleado tipE =null;
     	 
     	 Double total=0.0;
     	 
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

		        <div class="etiqueta titulo cgp">Hoja de Ruta Nro</div>
				<div class="parametro titulo ">
				<div class="etiqueta titulo cgp"><%=h.getNumero()%></div>
				</div>
				
				
				<div class="etiqueta titulo cgp">Fecha Hoja de Ruta</div>
				<div class="parametro titulo ">
				<div class="etiqueta titulo cgp"><%=formato.format(h.getFecha())%></div>
				</div>
				
				<div class="etiqueta titulo cgp">Cantidad de Pagos</div>
				<div class="parametro titulo ">
				<div class="etiqueta titulo cgp"><%=h.getCantidad() %></div>
				</div>
				
				<div class="etiqueta titulo cgp">Agregar Nuevos   </div>
				<div class="parametro titulo ">
				
				<%if(h.getTipo().getId()!=5){ %>
				
					<%if (Constantes.CantidadMaxHojaRuta>h.getCantidad()){ %>
						<div class="etiqueta titulo cgp"><img onclick="ir(6);" style="cursor:hand" src="<%=request.getContextPath()%>/images/page_add.png" width="14" height="13" title="Agregar nuevos pagos"></div>
					<%}else{ %>
					     <div class="etiqueta titulo cgp">Hoja de Ruta Llena</div> 
					<%}%>
					
				<%}else{ %>	
				
					
					<div class="etiqueta titulo cgp"><img onclick="ir(6);" style="cursor:hand" src="<%=request.getContextPath()%>/images/page_add.png" width="14" height="13" title="Agregar nuevos pagos"></div>
					
					
				<%}%>	
					
				
				</div>

				

			
				
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
					<td>Monto</td>
				    <td>Eliminar</td>					
					
				</tr>
	
	

	  <%
		for (int i=0;i!=List.size();i++){
		list = (PreOrdenPago) List.get(i);
      %>  		
				 
				 <tr class="item" bgcolor="white" >
				
				
					<td><%=list.getCod_completo() %></td>
					<input id="anio<%=list.getId()%>" type="hidden" name="anio<%=list.getId()%>" value="<%=list.getAnioPreorden() %>">
					
					<td><%=(list.getProveedor().getId()==44)?(list.getTitular()!=null)?list.getTitular().getNombres()+ " " + list.getTitular().getApellidos():"":list.getProveedor().getNombre()%></td>
					<td><%=list.getCausado()%></td>
					<td><%=nf.format(list.getMonto())%></td>
					<%total=total + list.getMonto(); %>
				    <td class="grayplink" align="center"> 
		                   <img onclick="eliminar(5,'<%=list.getId()%>');" style="cursor:hand" src="<%=request.getContextPath()%>/images/cross.png" width="14" height="13" title="Eliminar Pago">
		            
		            
		            
		            </td>
					
					
				</tr>
	 
				
	<%}%>	
	
	            <tr class="item" bgcolor="white" >
				
				   <td class="tituloCabecera"><%=" "%></td>
				   <td class="tituloCabecera"><%=" "%></td>
				
				   
				    <td class="tituloCabecera"><%="Monto Total :"%></td>
					<td class="tituloCabecera" colspan="2"><%=nf.format(total)%></td>
				   
					<td class="tituloCabecera" ><%=" "%></td>
					
				</tr>

	 				
					
					
				
					
					
					
					

					
				<tr align="left">
					<td align="left" colspan="6">
					<input id="desde_select" type="hidden" name="desde_select" value="<%=request.getAttribute("dselect")%>">
           			<input id="hasta_select" type="hidden" name="hasta_select" value="<%=request.getAttribute("hselect")%>">
					<input id="total" type="hidden" name="total" value="<%=total%>">
                    </td>
				</tr>

	
	<%}else{%>	
	
				<%if (primera!=null){ %>
	
					 <tr>
						<td align="center" class="grayp" style="padding-left: 20px; padding-bottom: 5px;">
							<div class="grayp"></div>
							<strong>No existen PreOrdenes</strong>
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
			<input id="anioP" type="hidden" name="anioP" value="<%=formato_anio.format(h.getFecha())%>">
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
	
	function eliminar(acc,p){
		if(confirm('¿Esta seguro que desea realizar esta operación?')){  
		document.getElementById('accionPago').value=acc;
		document.getElementById('pre').value=p;
	    document.forms[0].submit();
		}
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





