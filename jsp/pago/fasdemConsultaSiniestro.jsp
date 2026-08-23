<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@page import="ve.gob.dem.framework.recursos.*,java.util.Date,java.text.NumberFormat,java.util.ArrayList,ve.gob.dem.fasdem.bean.*,java.text.SimpleDateFormat"%>
<tiles:insert page="/jsp/plantilla/plantilla.jsp">

<%

		
	    ArrayList List=(ArrayList)request.getAttribute("lista");
		String primera = (String)request.getAttribute("primera");
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
	
<div style="border-bottom-color: #EEEEEE; border-bottom-width: thin; border-bottom-style: solid">&nbsp;</div>
			
 
				<div class="etiqueta titulo cgp">Codigo de Siniestro</div>
				<div class="parametro titulo ">
				
				<input type="text" id="cod_sin" name="cod_sin" value="" class="cgp">
				</div>
				
				
				<div class="sp5"></div>
				<div align="right">
				<input class="boton" value="Buscar por Codigo Siniestro " type="button"  onclick="ircodigo(1);" />
				
				</div>
				
				<div style="border-bottom-color: #EEEEEE; border-bottom-width: thin; border-bottom-style: solid">&nbsp;</div>
				
				
				<div class="etiqueta titulo cgp">Numero de Factura</div>
				<div class="parametro titulo ">
				<input type="text" id="numero_factura" name="numero_factura" value="" class="cgp">
				</div>
				
				<div class="etiqueta titulo cgp">Año de Factura</div>
				<div class="parametro titulo ">
				
				
						<select name="anio_factura" id="anio_factura" class="cgp"  >
						    	
							<%
							int anio_actual= Integer.parseInt(Utilidad.DateToString(new Date(),"yyyy"));
							
							for (int k=2010;k!=anio_actual+1;k++){
								
							%>		
									
							<option title="<%=k%>" selected value=<%=k%>><%=k%></option>
									
						    <%}%>	
									
							
							
				       	</select>
				
				
				</div>
				
				
	
				<div class="sp5"></div>
				<div align="right">
				<input class="boton" value="Buscar por Factura" type="button"  onclick="irfactura(3);" />
				</div>
				
				
				<div style="border-bottom-color: #EEEEEE; border-bottom-width: thin; border-bottom-style: solid">&nbsp;</div>

			
			<table class="tabla" width="600" cellpadding="2" cellspacing="1"
				border="0">
				
				
				
	 <% if (List!=null && List.size()!=0){%>				

				<tr class="tituloCabecera">

					<td>Nro de Siniestro</td>
					<td>Nro de Factura</td>
					<td>Fecha de Factura</td>
					<td>Pre Orden Pago</td>
				    <td>Imprimir</td>
				    <td>Detalle</td>					
					
				</tr>


	  <%
		for (int i=0;i!=List.size();i++){
		list = (DetalleFacturaPago) List.get(i);
      %>  		
				 
				 <tr class="item" bgcolor="white" >
				
				
					<td><%=list.getAniomes() + "-" + list.getCodigo_siniestro() + "-" + list.getSub_codigo_siniestro()%></td>
					<td><%=list.getNro_factura()%></td>
					<td><%=(list.getFecha_factura()!=null)?formato.format(list.getFecha_factura()):""%></td>
					<td><%=(list.getPreOrdendePago()!=null)?list.getPreOrdendePago():"Sin PreOrden"%></td>
					
					
					<%if(list.getPreOrdendePago()!=null){%>
					
					<td align="center"> 
					<img onclick="javascript:openWinScroll2('<%=request.getContextPath()%>/security/pago/imprimirPreOrden.do?pre=<%=list.getPreOrdendePago()%>','name',500,500);" style="cursor:hand" src="<%=request.getContextPath()%>/images/ic3d_buscar.gif" width="14" height="13" title="Ver">
					</td>	
					
					<td align="center"> 
					<img onclick="ir1(2,'<%=list.getPreOrdendePago()%>');" style="cursor:hand" src="<%=request.getContextPath()%>/images/ic3d_buscar.gif" width="14" height="13" title="Ver">
					</td>
					
					
						
					<%}else{%>
					<td align="center"> 
					--
					</td>
					
					<td align="center"> 
					--
					</td>
					<%}%>
					
				</tr>
	
				
	<%}%>	


	
	<%}else{%>	
	
				<%if (primera!=null){ %>
	
					 <tr>
						<td align="center" class="grayp" style="padding-left: 20px; padding-bottom: 5px;">
							<div class="grayp"></div>
							<strong>Siniestro o Factura no existe o no se encuentra Liquidado</strong>
							<div class="grayp"></div>
						</td>
					</tr>
	
				<%}%> 
	<%}%>    
	
	
	
		

	
			</table>
			


			
			<input id="accionPago" type="hidden" name="accionPago" value="">
			<input id="cod1" type="hidden" name="cod1" value="">
           
            
	</tiles:put>
	
</tiles:insert>



<script language="JavaScript">
	function ir(acc){
  
		document.getElementById('accionPago').value=acc;
        document.forms[0].submit();
		
	}


	function ir1(acc,c){
		document.getElementById('cod1').value=c;
		document.getElementById('accionPago').value=acc;
        document.forms[0].submit();
		
	}




	function ircodigo(acc){
		

      if(document.getElementById('cod_sin').value==""){
    	 alert ("Debe introducir el codigo del siniestro");
      }else{
          document.getElementById('accionPago').value=acc;
          document.forms[0].submit();
      }


    }



	function irfactura(acc){
		

	      if(document.getElementById('numero_factura').value==""){
	    	 alert ("Debe introducir el numero de factura");
	      }else{
	          document.getElementById('accionPago').value=acc;
	          document.forms[0].submit();
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





