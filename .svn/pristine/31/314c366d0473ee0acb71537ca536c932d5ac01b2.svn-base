<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@page import="ve.gob.dem.framework.recursos.*,java.util.Date,java.text.NumberFormat,java.util.ArrayList,ve.gob.dem.fasdem.bean.*,java.text.SimpleDateFormat"%>
<tiles:insert page="/jsp/plantilla/plantilla.jsp">

<%

		Clinica proveedor=(Clinica)request.getAttribute("prov");
        DetallePreOrdenPago det =(DetallePreOrdenPago)request.getAttribute("detalle");
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
  

		 NumberFormat nf = NumberFormat.getInstance();
		 nf.setGroupingUsed(true);
		 nf.setMaximumFractionDigits(2);
	 	 String dateOut;
     	 dateOut = Utilidad.DateToString(new Date(), "dd/MM/yyyy");
     	 Double Tope=Double.parseDouble(request.getParameter("inidadTribut"))* Constantes.CantidadUnidadesPagoTimbre ;
     	 int tim;
     	 
     	Persona tit = new Persona();
     	tit.setCedula(request.getParameter("cedula"));
     	tit.setNombres(request.getParameter("nombres"));
     	tit.setApellidos(request.getParameter("apellidos"));
		
%>

	<tiles:put name="titulo" direct="true">
		<bean:message key="principal.titulo" bundle="etiquetas" />
	</tiles:put>

	<tiles:put name="titulopagina" content="Inicio / Pagos / Detalle PreOrden de Pago"
		direct="true" />
	<tiles:put name="itemsup" content="/jsp/comunes/items.jsp" />
    <tiles:put name="itemsdown" content=" " direct="true" />
	<tiles:put name="cuerpo" direct="true">
	<div style="overflow: visible"></div>
	


    <table  class="tabla" width="600" cellpadding="2" cellspacing="1" border="1">
	
				
				<%if(proveedor!=null && proveedor.getId()!=Constantes.ProveedorTitular && proveedor.getId()!=Constantes.ProveedorTercero){%>
				
						 <tr>
						<td align="center" colspan="6" class="grayp" style="padding-left: 20px; padding-bottom: 5px;">
							
							<strong><%=proveedor.getNombre() + " (" + proveedor.getRif() + ")" %></strong>
							<input id="cadena" type="hidden" name="cadena" value=<%=request.getAttribute("cadena") %>>
							<input id="prov_selection" type="hidden" name="prov_selection" value=<%=proveedor.getId()%>>
							
							
						</td>
					</tr>
					
				<%}else{%>	
				
						<tr>
								<td align="center" colspan="6" class="grayp" style="padding-left: 20px; padding-bottom: 5px;">
									<div class="grayp"></div>
									<strong><%=tit.getNombres()  + " " + tit.getApellidos() +  " (" + tit.getCedula() + ")" %></strong>
									<div class="grayp"></div>
									<input id="cadena" type="hidden" name="cadena" value=<%=request.getAttribute("cadena") %>>
							        <input id="prov_selection" type="hidden" name="prov_selection" value=<%=tit.getCedula()%>>
								
								</td>
						</tr>
				
				<%}%>
				

				
				
				<tr >

					<td> -- Numero de Pagos</td>
					<td align="right"><strong><%=nf.format(det.getNro_pagos()) + " Facturas"%> </strong></td>

				</tr>
		
				
				<tr >

					<td> -- Unidad Tributaria Aplicable</td>
					<td align="right"><strong><%=request.getParameter("inidadTribut")%> Bs.</strong></td>

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
				
				
				
				
	
				<tr >

					<td> -- Base Imponible ISLR</td>
					<td align="right"><%=nf.format(det.getBase_imponible_isrl())%> Bs.</td>

				</tr>
				
				
				<tr >

					<td> ---- Monto ISLR</td>
					<td align="right"><strong><%=nf.format(det.getMonto_ISRL())%> Bs.</strong></td>

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
						<td align="right"><strong><%=nf.format(det.getMonto_Liquidado()-det.getMonto_ISRL()-det.getMonto_Timbre()-det.getMonto_Iva())%> Bs.</strong></td>
	
					</tr>
				
				<%}else{%>
				
				<tr >
	
						<td> ---- Monto a Pagar</td>
						<td align="right"><strong><%=nf.format(det.getMonto_Liquidado()-det.getMonto_ISRL()-det.getMonto_Iva())%> Bs.</strong></td>
	
					</tr>
				
				<%}%>
				
				
				
				
				
				


	
			</table>

	
				
				
				<div style="border-bottom-color: #EEEEEE; border-bottom-width: thin; border-bottom-style: solid">&nbsp;</div>
	
				<div class="sp5"></div>
				<div align="right">
				<input class="boton" value="Crear Pre-Orden de Pago" type="button"  onclick="ir(3);" />
				</div>

 				<input id="anio_pre3" type="hidden" name="anio_pre3" value="<%=request.getAttribute("anio_pre2")%>">
			    <input id="accionPago" type="hidden" name="accionPago" value="">
           		<input id="uni" type="hidden" name="uni" value=<%=request.getParameter("inidadTribut")%>>
           		<input id="por" type="hidden" name="por" value=<%=request.getParameter("porretiva")%>>
           		<input id="aplicTim" type="hidden" name="aplicTim" value=<%=tim%>>
           		<input id="tip_select3" type="hidden" name="tip_select3" value=<%=request.getAttribute("tip_select2")%>>

            	<input id="tipopre1" type="hidden" name="tipopre1" value=<%=request.getAttribute("tipoPre")%>>
            	<input id="tipProvSelect2" type="hidden" name="tipProvSelect2" value=<%=request.getParameter("tipProvSelect1")%>>
	            <input id="isrl" type="hidden" name="isrl" value=<%=request.getParameter("apli_isrl")%>>

	
	
	
	
	</tiles:put>
	
</tiles:insert>



<script language="JavaScript">
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





