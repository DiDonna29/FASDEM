<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@page import="ve.gob.dem.framework.recursos.*,java.util.Date,java.text.NumberFormat,java.util.ArrayList,ve.gob.dem.fasdem.bean.*,java.text.SimpleDateFormat"%>
<tiles:insert page="/jsp/plantilla/plantilla.jsp">

<%
		
	    ArrayList List=(ArrayList)request.getAttribute("lista");
	    String primera = (String)request.getAttribute("primera");
        ExpedientePago list;
        String anio_actual = (String)request.getAttribute("a_actual");
       
        
	     SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	     SimpleDateFormat formato_a = new SimpleDateFormat("yyyy");
	     NumberFormat nf = NumberFormat.getInstance();
		 nf.setGroupingUsed(true);
		 nf.setMaximumFractionDigits(2);
	 	 String dateOut;
     	 dateOut = Utilidad.DateToString(new Date(), "dd/MM/yyyy");
     	
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

		        <div class="etiqueta titulo cgp">Año:</div>
		      
		       
				<div class="parametro titulo ">
				
				<select name="anio_h" class="cgp">
				   <% int anio_act = Integer.parseInt(formato_a.format(new Date()));
				   for (int k=2011;k!=anio_act+1;k++){%>		
					<option title="<%=k%>" value=<%=k%> <%=Integer.parseInt(anio_actual)==k?"selected":""%>><%=k%></option>
				   <%}%>	
			    </select>
			
				</div>
				
				
				<div class="sp5"></div>
				<div align="right">
				<input class="boton" value="Buscar" type="button"  onclick="ir(2);" />
				
				</div>

				<div style="border-bottom-color: #EEEEEE; border-bottom-width: thin; border-bottom-style: solid">&nbsp;</div>
                <div style="border-bottom-color: #EEEEEE; border-bottom-width: thin; border-bottom-style: solid">&nbsp;</div>
                <div style="border-bottom-color: #EEEEEE; border-bottom-width: thin; border-bottom-style: solid">&nbsp;</div>
		
			<table class="tabla" width="600" cellpadding="2" cellspacing="1"
				border="0">
				
				
				
	 <% if (List!=null && List.size()!=0){%>	

	
				<tr class="tituloCabecera">

					<td>Pre Orden</td>
				    <td>Nro Causado</td>
					<td>Monto</td>
					<td>Estatus</td>
					<td>Hoja de Ruta</td>
				    <td>Observación</td>
				    <td>Reporte</td>
				    <td>Recibir</td>					
					
				</tr>
	
	

	  <%
		for (int i=0;i!=List.size();i++){
		list = (ExpedientePago) List.get(i);
      %>  		
				 
				 <tr class="item" bgcolor="white" >
				
				
					<td><%=list.getCodigo_preorden()%></td>
					<input id="anio<%=list.getId()%>" type="hidden" name="anio<%=list.getId()%>" value="<%=list.getAnio()%>">
					<td><%=list.getCausado()%></td>
					<td><%=nf.format(list.getMonto())%></td>
					
					<td><%=list.getStatus().getDescripcion() %></td>
					
					<td><%=list.getHoja().getNumero() %></td>
					
					<td><%=list.getObservacion()==null?"S/O":list.getObservacion()%></td>
					
				    <td class="grayplink" align="center"> 
		                    <img onclick="javascript:openWinScroll2('<%=request.getContextPath()%>/security/pago/imprimirPreOrden.do?pre=<%=list.getCodigo_preorden() %>','name',500,500);" style="cursor:hand" src="<%=request.getContextPath()%>/images/printer.png" width="14" height="13" title="PDF"> 
		            </td>
		            
		            
		            <%if(list.getStatus().getId()==12){ %>
		             <td class="grayplink" align="center"> 
		                  ---
		            </td>
		            
		            
		            <%}else{ %>
		            
				    <td class="grayplink" align="center"> 
		                   <img onclick="recibir(1,'<%=list.getCodigo_preorden()%>');" style="cursor:hand" src="<%=request.getContextPath()%>/images/ic3d_enviado.gif" width="14" height="13" >
		            </td>
					
					<%}%>
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
			<input id="pre" type="hidden" name="pre">
           
            
	</tiles:put>
	
</tiles:insert>



<script language="JavaScript">

	function ir(acc){
		  
		document.getElementById('accionPago').value=acc;
	    document.forms[0].submit();
		
	}

	
	function recibir(acc,p){
		if(confirm('¿Esta seguro que desea realizar esta operación?')){  
		document.getElementById('accionPago').value=acc;
		document.getElementById('pre').value=p;
	    document.forms[0].submit();
		}
	}
	
</script>





