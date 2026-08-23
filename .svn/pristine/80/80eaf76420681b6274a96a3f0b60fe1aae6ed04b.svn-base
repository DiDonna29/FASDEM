<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@page import="ve.gob.dem.framework.recursos.*,java.util.Date,java.text.NumberFormat,java.util.ArrayList,ve.gob.dem.fasdem.bean.*,java.text.SimpleDateFormat"%>
<tiles:insert page="/jsp/plantilla/plantilla.jsp">

<%

		
        DetallePreOrdenPago det =(DetallePreOrdenPago)request.getAttribute("detalle");
		PreOrdenPago preO =(PreOrdenPago)request.getAttribute("preorden");
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	
		 NumberFormat nf = NumberFormat.getInstance();
		 nf.setGroupingUsed(true);
		 nf.setMaximumFractionDigits(2);
	 	 String dateOut;
     	 dateOut = Utilidad.DateToString(new Date(), "dd/MM/yyyy");
     	 Double Tope=preO.getUnidad_tributaria()* Constantes.CantidadUnidadesPagoTimbre ;
     	 int tim =0;
     	  
     	 
     	 
     	 
		
%>



	<tiles:put name="titulo" direct="true">
		<bean:message key="principal.titulo" bundle="etiquetas" />
	</tiles:put>

	<tiles:put name="titulopagina" content="Detallle PreOrden de Pago"
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
	
	


    <table  class="tabla" width="600" cellpadding="2" cellspacing="1" border="1">
	
				<tr >

					<td> -- Codigo de Pre Orden </td>
					<td><strong><%=preO.getCod_completo()%></strong></td>

				</tr>
				
				
				<tr >

					<td> -- Fecha de Pre Orden</td>
					<td><strong><%=formato.format(preO.getFecha_preorden())%></strong></td>

				</tr>
				 

				
				    
				<%if(preO.getProveedor()!=null && preO.getProveedor().getId()!=Constantes.ProveedorTitular && preO.getProveedor().getId()!=Constantes.ProveedorTercero){ %>    
					    
					<tr >
	
						<td> -- Proveedor</td>
						<td><strong><%=preO.getProveedor().getNombre() + " (" + preO.getProveedor().getRif() + ")"  %></strong></td>
	
					</tr>
				
				<%}else{%> 
				
				<tr >
	
						<td> -- Beneficiario</td>
						<td><strong><%=preO.getTitular().getNombres() + " " + preO.getTitular().getApellidos() + " (" + preO.getTitular().getCedula() + ")"  %></strong></td>
	
						</tr>
				
				
				
				<%}%> 
				
				
	
				<tr >

					<td> -- Numero de Pagos</td>
					<td align="right"><strong><%=nf.format(det.getNro_pagos()) + " Facturas"%> </strong></td>

				</tr>
				
				
				
				
				
				<tr >

					<td> -- Unidad Tributaria Aplicable</td>
					<td align="right"><strong><%=nf.format(preO.getUnidad_tributaria())%> Bs.</strong></td>

				</tr>
				
				<tr >

					<td> -- Retención ISLR al Proveedor</td>
					<td align="right"><strong><%=preO.isAplica_isrl()==true?"SI":"NO"%> </strong></td>

				</tr>
				
	
				<tr >

					<td> -- Monto Liquidado</td>
					<td align="right"><strong><%=nf.format(det.getMonto_Liquidado())%> Bs.</strong></td>

				</tr>
				
				
				<tr >

					<td> ---- Gastos Clinicos</td>
					<td align="right"> <strong><%=nf.format(det.getGastos_Clinicos())%> Bs.</strong></td>

				</tr>
				
				
				<tr >

					<td> ---- Honorarios Medicos</td>
					<td align="right"><strong><%=nf.format(det.getHonorarios_Medicos())%> Bs.</strong></td>

				</tr>
				
				
				<tr >

					<td> ---- Farmacia</td>
					<td align="right"> <strong><%=nf.format(det.getMedicinas())%> Bs.</strong></td>

				</tr>
				
				
				<tr >

					<td> ---- Material Medico con IVA</td>
					<td align="right"><strong><%=nf.format(det.getMaterial_Medico_IVA())%> Bs.</strong></td>

				</tr>
				
				
				
				<tr >

					<td> ---- Material Medico sin Iva</td>
					<td align="right"><strong><%=nf.format(det.getMaterial_Medico())%> Bs.</strong></td>

				</tr>
				
			
				
				<tr >

					<td> ---- Servicio Odontologico</td>
					<td align="right"><strong><%=nf.format(det.getOdontologia())%> Bs.</strong></td>

				</tr>
				
				
				
				<tr >

					<td> ---- Gastos Funerarios</td>
					<td align="right"><strong><%=nf.format(det.getFunerarios())%> Bs.</strong></td>

				</tr>
				
				
				<tr >

					<td> ---- Otros Gastos</td>
					<td align="right"><strong><%=nf.format(det.getOtros_Gastos())%> Bs.</strong></td>

				</tr>
				
				
	<%if(Integer.parseInt(request.getAttribute("aplica").toString())!= Constantes.TipoPreOrdenReembolsos){ %>
				
				<%
				int factorIsrl=0;
				if(preO.isAplica_isrl()==true){factorIsrl=1; %>
				<%}else{factorIsrl=0;} %>
	
	
	
	
				<tr >

					<td> -- Base Imponible ISLR</td>
					<td align="right"><%=nf.format(det.getBase_imponible_isrl())%> Bs.</td>

				</tr>
				
				
				<tr >

					<td> ---- Monto ISLR</td>
					<td align="right"><strong><%=nf.format(det.getMonto_ISRL()*factorIsrl)%> Bs.</strong></td>

				</tr>
				
				
				

	 				
				
				<%if(det.getMonto_Liquidado()>=Tope){ %>
				<%tim=1;%>
				
					<tr >
	
						<td> -- Base Imponible Timbre</td>
					<td align="right" ><%=nf.format(det.getBase_imponible_timbre())%> Bs.</td>
	
					</tr>
				
				<%}else{%>
				<%tim=0;%>
				<tr >
	
						<td> ---- Base Imponible Timbre</td>
						<td align="right"><strong><%="No Aplica"%></strong></td>
	
					</tr>
				
				<%}%>
				
				
				
				<%if(det.getMonto_Liquidado()>=Tope){ %>
				<%tim=1;%>
				
					<tr >
	
						<td> ---- Monto Timbre</td>
						<td align="right"><strong><%=nf.format(det.getMonto_Timbre())%> Bs.</strong></td>
	
					</tr>
				
				<%}else{%>
				<%tim=0;%>
				<tr >
	
						<td> ---- Monto Timbre</td>
						<td align="right"><strong><%="No Aplica"%></strong></td>
	
					</tr>
				
				<%}%>
				
		
		
				
					<tr >
	
						<td> ---- Monto Iva</td>
						<td align="right"><strong><%=nf.format(det.getMonto_Iva())%> Bs.</strong></td>
	
					</tr>
					
					
					<%if(det.getMonto_Liquidado()>=Tope){ %>
					<tr >
	
						<td> ---- Monto a Pagar</td>
						<td align="right"><strong><%=nf.format(det.getMonto_Liquidado()-(det.getMonto_ISRL()*factorIsrl)-det.getMonto_Timbre()-det.getMonto_Iva())%> Bs.</strong></td>
	
					</tr>
				
				<%}else{%>
				
				<tr >
	
						<td> ---- Monto a Pagar</td>
						<td align="right"><strong><%=nf.format(det.getMonto_Liquidado()-(det.getMonto_ISRL()*factorIsrl)-det.getMonto_Iva())%> Bs.</strong></td>
	
					</tr>
				
				<%}%>
					
					
					

				
			   
			<%}else{%>	
			
			
			
			
				<%if(det.getMonto_Liquidado()>=Tope){ %>
					<tr >
	
						<td> ---- Monto a Pagar</td>
						<td align="right"><strong><%=nf.format(det.getMonto_Liquidado())%> Bs.</strong></td>
	
					</tr>
				
				<%}else{%>
				
				<tr >
	
						<td> ---- Monto a Pagar</td>
						<td align="right"><strong><%=nf.format(det.getMonto_Liquidado())%> Bs.</strong></td>
	
					</tr>
				
				<%}%>
			
	
			
				
		<%}%>	
		
			</table>
			

	
				<div style="border-bottom-color: #EEEEEE; border-bottom-width: thin; border-bottom-style: solid">&nbsp;</div>
	
	            
 				
 				<div class="sp5"></div>
				<div align="right">
				<input class="boton" value="Crear Nueva" type="button"  onclick="ir(6);" />
				</div>
				
				
				<div class="sp5"></div>
				<div align="right">
				<input class="boton" value="Imprimir PreOrden Nro <%=preO.getCod_completo()%>" type="button"  onclick="javascript:openWinScroll2('<%=request.getContextPath()%>/security/pago/imprimirPreOrden.do?pre=<%=preO.getCod_completo()%>','name',500,500);" />
				</div>

			    <input id="accionPago" type="hidden" name="accionPago" value="">
           		<input id="codigoPreo" type="hidden" name="codigoPreo" value=<%=preO.getCod_completo()%>>
            
	</tiles:put>
	
</tiles:insert>








<script language="JavaScript">

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







	function ir(acc){
  
		document.getElementById('accionPago').value=acc;
        document.forms[0].submit();
		
	}



</script>





