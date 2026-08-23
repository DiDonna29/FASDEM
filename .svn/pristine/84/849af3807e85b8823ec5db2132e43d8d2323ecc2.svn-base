<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@page import="ve.gob.dem.framework.recursos.*,java.util.Date,java.text.NumberFormat,java.util.ArrayList,ve.gob.dem.fasdem.bean.*,java.text.SimpleDateFormat"%>
<tiles:insert page="/jsp/plantilla/plantilla.jsp">
<tiles:put name="titulo" direct="true">
		<bean:message key="principal.titulo" bundle="etiquetas" />
	</tiles:put>

<%
		Clinica proveedor=(Clinica)request.getAttribute("prov");
	    ArrayList List=(ArrayList)request.getAttribute("lista");
	    ArrayList List2=(ArrayList)request.getAttribute("lista");
		ArrayList ListaTipoProv=(ArrayList)request.getAttribute("listaTipoProv");
		String primera = (String)request.getAttribute("primera");
        DetalleFacturaPago list;
        Factura list2;
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
     	 
     	 
         String cliselect=(String)request.getAttribute("cli_select");
         String mes=(String)request.getAttribute("mes");
         String perselect=(String)request.getAttribute("per_select");
         Clinica prov=(Clinica)request.getAttribute("clinica_seleccionada");
         
%>



	<tiles:put name="titulo" direct="true">
		<bean:message key="principal.titulo" bundle="etiquetas" />
	</tiles:put>

	<tiles:put name="titulopagina" content="Inicio / Reportes / Retención de Impuestos por Orden de Pago"
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
				
				
				<div class="etiqueta titulo cgp">Periodo</div>
				<div class="parametro titulo ">
				
				
						<select name="periodo" id="periodo" class="cgp"  >
						    	
							<%
							int anio_actual= Integer.parseInt(Utilidad.DateToString(new Date(),"yyyy"));
							
							for (int k=2011;k!=anio_actual+1;k++){
								
							%>		
									
							<option title="<%=k%>" selected value=<%=k%>><%=k%></option>
									
						    <%}%>	
									
							
							
				       	</select>
				
				
				</div>
				<div class="etiqueta titulo cgp">Mes</div>
				<div class="parametro titulo ">
					<select name="mes" id="mes" class="cgp">
	
			<option class="seleccione"  value="-1">[SELECCIONAR]</option>
			<option value="1"><c:out value="Enero" /></option>
			<option value="2">
				<c:out value="Febrero" />
			</option>
			<option value="3">
				<c:out value="Marzo" />
			</option>
			<option value="4">
				<c:out value="Abril" />
			</option>
			<option value="5">
				<c:out value="Mayo" />
			</option>
			<option value="6">
				<c:out value="Junio" />
			</option>
			<option value="7">
				<c:out value="Julio" />
			</option>
			<option value="8">
				<c:out value="Agosto" />
			</option>
			<option value="9">
				<c:out value="Septiembre" />
			</option>
			<option value="10">
				<c:out value="Octubre" />
			</option>
			<option value="11">
				<c:out value="Noviembre" />
			</option>
			<option value="12">
				<c:out value="Diciembre" />
			</option>

		</select>
				
				</div>
				
				
 

				<div class="sp5"></div>
				<div align="right">
				<input class="boton" value="Buscar" type="button"  onclick="ir(1)" />
				</div>




				<div style="border-bottom-color: #EEEEEE; border-bottom-width: thin; border-bottom-style: solid">&nbsp;</div>
                <div style="border-bottom-color: #EEEEEE; border-bottom-width: thin; border-bottom-style: solid">&nbsp;</div>

 
 
 
			<table class="tabla" width="600" cellpadding="2" cellspacing="1"
                border="0">
				
<%if (cliselect!=null){ %>			
					
				
			<%if (!cliselect.equals("") && !perselect.equals("")){ %>	
			
				 <tr class="tituloCabecera">

                    <td align="center">Proveedor</td>
                    <td>Fecha</td>
                    <td>Pre Orden</td>
                    <td>ISRL</td>
                    <td>1x1000</td>
                    <td>Valor Agregado</td>                     
 
                    
                </tr>
				
				<%
        			for (int i=0;i!=List2.size();i++){
        				list2 = (Factura) List2.get(i);
      			%> 
				
				<tr class="item" bgcolor="white" >
                
                

                    <td><%=prov.getNombre() %></td>
                    <td><%=list2.getFechaFactura()  %></td>
                    <td align="center"><%=list2.getPreOrden() %></td>
                    
                     <td class="grayplink" align="center"> 
                           <img onclick="javascript:openWinScroll2('<%=request.getContextPath()%>/security/reportes/imprimirRetencionOrden.do?h=<%=list2.getPreOrden()%>&mes=<%=mes%>&c=<%=cliselect%>&p=<%=perselect%>','name',500,500);" style="cursor:hand" src="<%=request.getContextPath()%>/images/printer.png" width="14" height="13" title="Reporte">

                    </td>
                    <td class="grayplink" align="center"> 
                           <img onclick="javascript:openWinScroll2('<%=request.getContextPath()%>/security/reportes/imprimirRetencionOrden.do?h=<%=list2.getPreOrden()%>&mes=<%=mes%>&totales=1&c=<%=cliselect%>&p=<%=perselect%>','name',500,500);" style="cursor:hand" src="<%=request.getContextPath()%>/images/printer.png" width="14" height="13" title="Reporte">

                    </td>                   
                    <td class="grayplink" align="center"> 
                           <img onclick="javascript:openWinScroll2('<%=request.getContextPath()%>/security/reportes/imprimirRetencionOrden.do?h=<%=list2.getPreOrden()%>&mes=<%=mes%>&totales=2&c=<%=cliselect%>&p=<%=perselect%>','name',500,500);" style="cursor:hand" src="<%=request.getContextPath()%>/images/printer.png" width="14" height="13" title="Reporte">

                    </td>
                </tr>
                
                    <%}%>
				
				   <%}%>
				

     <%}else{%>  
    
    
                     <tr>
                        <td align="center" class="grayp" style="padding-left: 20px; padding-bottom: 5px;">
                            <div class="grayp"></div>
                            <strong>No existen Hojas de Pagos</strong>
                            <div class="grayp"></div>
                        </td>
                    </tr>
    
    <%}%>
	

			</table>
			<input id="procesar" type="hidden" name="procesar">
			<input id="accionPago" type="hidden" name="accionPago">
			<input id="anio_pre1" type="hidden" name="anio_pre1" value="<%=request.getAttribute("anio_pre")%>">
			<input id="tipProvSelect1" type="hidden" name="tipProvSelect1" value="<%=request.getAttribute("tipProvSelect")%>">
           
            
	</tiles:put>
	
</tiles:insert>



<script language="JavaScript">

	function ir(acc){
		document.getElementById('accionPago').value=acc;
		document.getElementById('procesar').value='procesado';
		document.forms[0].submit();
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





