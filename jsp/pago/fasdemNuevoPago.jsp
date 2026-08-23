<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@page import="ve.gob.dem.framework.recursos.*,java.util.Date,java.text.NumberFormat,java.util.ArrayList,ve.gob.dem.fasdem.bean.*,java.text.SimpleDateFormat"%>
<tiles:insert page="/jsp/plantilla/plantilla.jsp">

<%
		Clinica proveedor=(Clinica)request.getAttribute("prov");
	    ArrayList List=(ArrayList)request.getAttribute("lista");
		ArrayList ListaTipoProv=(ArrayList)request.getAttribute("listaTipoProv");
		String primera = (String)request.getAttribute("primera");
        DetalleFacturaPago list;
        TipoProveedor tip;
        Persona tit = new Persona();
        
        
        try{
        	DetalleFacturaPago list1=(DetalleFacturaPago)List.get(0);
            tit =list1.getTitular();
            
        } catch (Exception e) {}
       
        
        
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
         int dateOut1;
     	 dateOut1 = (Integer.parseInt(Utilidad.DateToString(new Date(), "yyyy")));
     	 
     	 
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

				<div class="etiqueta titulo cgp">Tipo de Proveedor</div>
				<div class="parametro titulo cgp">
				
				<select name="tipoProveedor" id="tipoProveedor" class="cgp">
				    <option value="-1" selected="selected" class="seleccione">[SELECCIONAR]</option>
					
					
						 <%
							for (int j=0;j!=ListaTipoProv.size();j++){
							tip = (TipoProveedor) ListaTipoProv.get(j);
			      		 %>		
								
								<option value=<%=tip.getId()%>><%=tip.getDescripcion()%></option>
								
					    <%}%>	
					
					
					
		        </select>
		        
		        </div>
 
 
				<div class="etiqueta titulo cgp">Proveedor</div>
				
				<div class="parametro titulo cgp">
				
					<input type="hidden" id="idProveedor" name="idProveedor" value=""> <input type="text" name="proveedor" value="" onfocus="blur()" style="width:50%" class="cgp"> <a class="red" href="#" onclick="javascript:openWinScrollProv('<%=request.getContextPath()%>/security/comunes/busquedaProveedor.do?tipoProveedor=' + document.forms[0].tipoProveedor.value,'name',500,500);">buscar</a>
				     
				</div>
				
				
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

				<div class="etiqueta titulo cgp">Fecha_siniestro_desde</div>
				<div class="parametro titulo ">
				<input type="text" name="fechaInicio" id="fechaInicio" onfocus="javascript:showCalendarSelected2(document.forms[0].fechaInicio, document.forms[0].fechaInicio, 'dd/mm/yyyy','es',1);blur();" class="cgp">
				</div>
 
 
				<div class="etiqueta titulo cgp">Fecha_siniestro_hasta</div>
				<div class="parametro titulo ">
				<input type="text" name="fechaFin" id="fechaFin" onfocus="javascript:showCalendarSelected2(document.forms[0].fechaFin, document.forms[0].fechaFin, 'dd/mm/yyyy','es',1);blur();" class="cgp">
				</div>
				

				<div class="sp5"></div>
				<div align="right">
				<input class="boton" value="buscar" type="button"  onclick="buscarfechas('1');" />
				</div>

				<div style="border-bottom-color: #EEEEEE; border-bottom-width: thin; border-bottom-style: solid">&nbsp;</div>
                <div style="border-bottom-color: #EEEEEE; border-bottom-width: thin; border-bottom-style: solid">&nbsp;</div>
			
 
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
				
				<input class="boton" value="Buscar por Codigo Siniestro " type="button"  onclick="buscarcodigosin(4);" />
				
				</div>
				
				<div style="border-bottom-color: #EEEEEE; border-bottom-width: thin; border-bottom-style: solid">&nbsp;</div>
                <div style="border-bottom-color: #EEEEEE; border-bottom-width: thin; border-bottom-style: solid">&nbsp;</div>

				<div style="border-bottom-color: #EEEEEE; border-bottom-width: thin; border-bottom-style: solid">&nbsp;</div>
		
			<table class="tabla" width="600" cellpadding="2" cellspacing="1"
				border="0">
				
				
				
	 <% if (List!=null && List.size()!=0){%>	
	 
	 
	 
	 			<%if(proveedor!=null && proveedor.getId()!=Constantes.ProveedorTitular && proveedor.getId()!=Constantes.ProveedorTercero){%>
				
						 <tr>
								<td align="center" colspan="6" class="grayp" style="padding-left: 20px; padding-bottom: 5px;">
									<div class="grayp"></div>
									<strong><%=proveedor.getNombre() + " (" + proveedor.getRif() + ")" %></strong>
									<input id="provTerc" type="hidden" name="provTerc" value="<%=proveedor.getId()%>">
									<div class="grayp"></div>
								</td>
						</tr>
					
				<%}else{%>	
				
						<tr>
								<td align="center" colspan="6" class="grayp" style="padding-left: 20px; padding-bottom: 5px;">
									<div class="grayp"></div>
									<strong><%=tit.getNombres()  + " " + tit.getApellidos() +  " (" + tit.getCedula() + ")" %></strong>
									<input id="cedula" type="hidden" name="cedula" value="<%=tit.getCedula()%>">
           							<input id="nombres" type="hidden" name="nombres" value="<%=tit.getApellidos()%>">
                    				<input id="apellidos" type="hidden" name="apellidos" value="<%=tit.getNombres()%>">
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
					<td align="center"><INPUT type="checkbox" value="<%=list.getId_factura()%>" NAME="listfact"></td>		
					
					
				</tr>
	
				
	<%}%>	

	 				<tr class="item" bgcolor="white" >
			            <td align="right" colspan="6">Seleccionar todas las facturas:&nbsp;&nbsp; 
						<input type='button' name='checkall' style="width: auto" onclick='checkedAll(listfact);'>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					</tr>
					
					
					
					<tr class="item" bgcolor="white" >
			            <td align="right" colspan="6">Unidad Tributaria Aplicable:&nbsp;&nbsp; 
						
						<select name="inidadTribut" class="cgp">
				    	
							      <%
									String anio = Utilidad.DateToString(new Date(),"yyyy");
									String selec = "";
									for (int k=0;k!=ListaU.size();k++){
										selec = "";
										unid = (UnidadTributaria) ListaU.get(k);
										if (String.valueOf(unid.getAnio()).equals(anio)){
											selec = "selected=\"selected\"";
										}
									      		 %>		
							
							   	<option  <%=selec%> title="<%=unid.getMonto_unidad()%>" value=<%=unid.getMonto_unidad()%>><%=unid.getAnio() + " - " + unid.getMonto_unidad() + " Bs."%></option>
							
				    			<%}%>	
							
					
					
		       			 </select>
						
						
						

							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					</tr>
					
					<tr class="item" bgcolor="white" >
			            <td align="right" colspan="6">% Retención IVA:&nbsp;&nbsp; 
						
						<select name="porretiva" class="cgp">
				    	       <option value=<%=100%>><%="100%"%></option>
				    	       <option value=<%=75%>><%="75%"%></option>
						</select>
						
						
						

							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					</tr>
					
					
					<tr class="item" bgcolor="white" >
			            <td align="right" colspan="6">% Aplica Retención de ISLR:&nbsp;&nbsp; 
						
						<select name="apli_isrl" class="cgp">
				    	       <option value=<%=1%>><%="SI"%></option>
				    	       <option value=<%=0%>><%="NO"%></option>
						</select>
						
						
						

							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					</tr>
					
					

					
				<tr align="left">
					<td align="left" colspan="6">
					
					<%if(proveedor!=null && proveedor.getId()!=Constantes.ProveedorTitular && proveedor.getId()!=Constantes.ProveedorTercero){%>
					<input id="prov_select" type="hidden" name="prov_select" value="<%=proveedor.getId()%>">
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
				    <input class="boton" value="Crear Pre Orden de Pago" type="button"  onclick="ir(2);" />
				</div>
			
			<%}%> 

		
			

			<input id="accionPago" type="hidden" name="accionPago">
			<input id="anio_pre1" type="hidden" name="anio_pre1" value="<%=request.getAttribute("anio_pre")%>">
			<input id="tipProvSelect1" type="hidden" name="tipProvSelect1" value="<%=request.getAttribute("tipProvSelect")%>">
           
            
	</tiles:put>
	
</tiles:insert>



<script language="JavaScript">

	function ir(acc){
		  
		document.getElementById('accionPago').value=acc;
	    document.forms[0].submit();
		
	}



	function buscarfechas(acc){
  
		 if(document.getElementById('idProveedor').value!=''){
				 if(document.getElementById('tipoempleado').value!='-1'){
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
	 
				 }else{
				   alert("Debe especificar el tipo de empleado");
				 }

		 }else{
		   alert("Debe especificar el proveedor");
		 }


		
		
	}



	function buscarcodigosin(acc){
		
		  
      if(document.getElementById('cod_sin').value!=''){
    	  document.getElementById('accionPago').value=acc;
          document.forms[0].submit();
      }else{
       alert("Debe especificar el codigo de siniestro");
      }
		
		
	}


	function buscarcodigofac(acc){
		  
		if(document.getElementById('numero_factura').value!=''){
	    	  document.getElementById('accionPago').value=acc;
	          document.forms[0].submit();
	      }else{
	       alert("Debe especificar el numero de la factura");
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





