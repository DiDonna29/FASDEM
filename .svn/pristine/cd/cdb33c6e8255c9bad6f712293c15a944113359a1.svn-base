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
			ArrayList ListaEstatus=(ArrayList)request.getAttribute("listaEstatus");
			String primera = (String)request.getAttribute("primera");
	        PreOrdenPago list=null;
	        Persona tit=null;
	        
	        try{
	        	PreOrdenPago list1=(PreOrdenPago)List.get(0);
	            tit =list1.getTitular();
	        } catch (Exception e) {}
	       
	        
	        
	        TipoProveedor tip;
	        EstatusPreOrden est;
	        
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
			
			
			
				<div class="etiqueta titulo cgp">Estatus Pre Orden</div>
				<div class="parametro titulo cgp">
				
				<select name="estatus" id="estatus" class="cgp">
				    <option value="-1" selected="selected" class="seleccione">[SELECCIONAR]</option>
					    <%
							for (int k=0;k!=ListaEstatus.size();k++){
							est = (EstatusPreOrden) ListaEstatus.get(k);
			      		 %>		
								
								<option value=<%=est.getId()%>><%=est.getDescripcion()%></option>
								
					    <%}%>	
				</select>
		        
		        </div>
			

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
				
					<input type="hidden" name="idProveedor" id="idProveedor" value=""> <input type="text" name="proveedor" value="" onfocus="blur()" style="width:50%" class="cgp"> <a class="red" href="#" onclick="javascript:openWinScrollProv('<%=request.getContextPath()%>/security/comunes/busquedaProveedor.do?tipoProveedor=' + document.forms[0].tipoProveedor.value,'name',500,500);">buscar</a>
				
				</div>


				<div class="etiqueta titulo cgp">Fecha Pre Orden (Desde)</div>
				<div class="parametro titulo ">
				<input type="text" name="fechaInicio" id="fechaInicio" value="" onfocus="javascript:showCalendarSelected2(document.forms[0].fechaInicio, document.forms[0].fechaInicio, 'dd/mm/yyyy','es',1);blur();" class="cgp">
				</div>
 
 
				<div class="etiqueta titulo cgp">Fecha Pre Orden (hasta)</div>
				<div class="parametro titulo ">
				<input type="text" name="fechaFin" id="fechaFin" value="" onfocus="javascript:showCalendarSelected2(document.forms[0].fechaFin, document.forms[0].fechaFin, 'dd/mm/yyyy','es',1);blur();" class="cgp">
				</div>
				
				
				
				<div class="sp5"></div>
				<div align="right">
				<input class="boton" value="buscar" type="button"  onclick="ir(1);" />
				</div>
				
				
				
				<div style="border-bottom-color: #EEEEEE; border-bottom-width: thin; border-bottom-style: solid">&nbsp;</div>
				
				
				<div style="border-bottom-color: #EEEEEE; border-bottom-width: thin; border-bottom-style: solid">&nbsp;</div>
				
				
				<div class="etiqueta titulo cgp">Codigo de Pre Orden</div>
				
				<div class="parametro titulo cgp">
				
					<input type="text" id="codigo1" name="codigo1"  size="4" value="" maxlength="4" class="cgp"> - <input type="text" id="codigo2" name="codigo2" value="" size="10" maxlength="5" class="cgp"> 
				
				</div>


				
				<div class="sp5"></div>
				<div align="right">
				<input class="boton" value="buscar por codigo" type="button"  onclick="ircodigo(3);" />
				</div>
				


			
			<table class="tabla" width="600" cellpadding="2" cellspacing="1"
				border="0">
				
				
				
	 <% if (List!=null && List.size()!=0){%>				
				
				
					
					<%if( proveedor!=null && proveedor.getId()!=Constantes.ProveedorTitular && proveedor.getId()!=Constantes.ProveedorTercero){%>
				
						 <tr>
								<td align="center" colspan="6" class="grayp" style="padding-left: 20px; padding-bottom: 5px;">
									<div class="grayp"></div>
									<strong><%=proveedor.getNombre() + " (" + proveedor.getRif() + ")" %></strong>
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

					<td>Nro de Pre Orden</td>
					<td>Fecha de Pre Orden</td>
					<td>Fecha de Orden</td>
					<td>Fecha de Pago</td>
					<td>Nro de Orden</td>
				    <td>Estatus</td>	
				    <td>Detalle</td>
				    
				    <td>Reporte</td>	
				    				
					
				</tr>
	
	

	  <%
		for (int i=0;i!=List.size();i++){
		list = (PreOrdenPago) List.get(i);
      %>  		
				 
				<tr class="item" bgcolor="white" >
				
				
					<td><%=list.getAniomes() + "-" + list.getCodigo_preorden()%></td>
					<td><%=(list.getFecha_preorden()!=null)?formato.format(list.getFecha_preorden()):""%></td>
					<td><%=(list.getFecha_orden()!=null)?formato.format(list.getFecha_orden()):"S/F"%></td>
					<td><%=(list.getFecha_pagado()!=null)?formato.format(list.getFecha_pagado()):"S/F"%></td>
					<td><%=(list.getNro_orden()!= null)?list.getNro_orden():"S/N"%></td>
					<td><%=list.getEstatus().getDescripcion()%></td>
					
					<td class="grayplink" align="center"> 
	                   <img onclick="ir1(4,'<%=list.getCod_completo()%>');" style="cursor:hand" src="<%=request.getContextPath()%>/images/zoom.png" width="14" height="13" title="Detalle">
	                </td>
                
                
					<td class="grayplink" align="center"> 
	                   <img onclick="javascript:openWinScroll2('<%=request.getContextPath()%>/security/pago/imprimirPreOrden.do?pre=<%=list.getAniomes() + "-" + list.getCodigo_preorden()%>','name',500,500);" style="cursor:hand" src="<%=request.getContextPath()%>/images/printer.png" width="14" height="13" title="PDF"> 
 						<%if (list.getTipo_preorden()==Constantes.TipoPreOrdenFarmacia){%>
	                  / <img onclick="javascript:openWinScroll2('<%=request.getContextPath()%>/security/pago/imprimirPreOrdenXLS.do?pre=<%=list.getAniomes() + "-" + list.getCodigo_preorden()%>','name',500,500);" style="cursor:hand" src="<%=request.getContextPath()%>/images/excel.bmp" width="14" height="13" title="XLS">
	                 <%}%>	

	                </td>	
	
				</tr>

	<%}%>	

	
	<%}else{%>	
	
				<%if (primera!=null){ %>
	
					 <tr>
						<td align="center" class="grayp" style="padding-left: 20px; padding-bottom: 5px;">
							<div class="grayp"></div>
							<strong>No existen Ordenes de Pago</strong>
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



    function ir(acc){

    if(document.getElementById('estatus').value!='-1'){
    	if(document.getElementById('idProveedor').value!=''){
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
	   alert("Debe especificar el proveedor");
	 }

    }else{
 	   alert("Debe especificar el proveedor");
 	 }


	
	}





	

	function ir1(acc,codig){
		
		
		document.getElementById('cod1').value=codig;
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





