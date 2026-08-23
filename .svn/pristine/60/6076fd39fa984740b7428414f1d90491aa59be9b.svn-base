<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@page import="ve.gob.dem.framework.recursos.*,java.util.Date,java.text.NumberFormat,java.util.ArrayList,ve.gob.dem.fasdem.bean.*,java.text.SimpleDateFormat"%>
<tiles:insert page="/jsp/plantilla/plantilla.jsp">

<%
		Clinica proveedor=(Clinica)request.getAttribute("prov");
	    ArrayList List=(ArrayList)request.getAttribute("lista");
		String primera = (String)request.getAttribute("primera");
		ArrayList ListAuto=(ArrayList)request.getAttribute("listaAuto");
		

        
	     SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	
		 NumberFormat nf = NumberFormat.getInstance();
		 nf.setGroupingUsed(true);
		 nf.setMaximumFractionDigits(2);
	 	 String dateOut;
     	 dateOut = Utilidad.DateToString(new Date(), "dd/MM/yyyy");
         Autoridades auto =null;
     	 
%>



	<tiles:put name="titulo" direct="true">
		<bean:message key="principal.titulo" bundle="etiquetas" />
	</tiles:put>

	<tiles:put name="titulopagina" content="Inicio / Extensión de cobertura "
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
		
			    <div class="etiqueta titulo cgp">Siniestro:</div>
				
				
				<div class="nblack "><%=request.getAttribute("codigo")%>
		        </div>
				
				
				<div class="etiqueta titulo cgp">Responsable:</div>
				<div class="parametro titulo cgp">
				
					<select name="autoridad" class="cgp">
					    
						
						
						 <%
									for (int k=0;k!=ListAuto.size();k++){
										auto = (Autoridades) ListAuto.get(k);
					      		 %>		
							
							   	<option  value=<%=auto.getId()%>><%=auto.getNombres() + " " + auto.getApellidos() %></option>
							
				    			<%}%>
					
					</select>
		        
		        </div>
				
				
				<div class="etiqueta titulo cgp">Monto Maximo:</div>
				<div class="parametro titulo cgp">
				
				<input type="text" name="monto"  id="monto" class="cgp"> Bs.
		        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		        </div>
				
				<div class="etiqueta titulo cgp">Observación:</div>
				<div class="parametro titulo cgp">
				
				<textarea  name="observacion"  id="observacion" class="cgp"></textarea> 
		      
		        </div>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<div  align="center" >
				    <input class="boton" value="Registrar Autorización de Extensión" type="button"  onclick="ir(2);" /> <input class="boton" value="Cancelar" type="button"  onclick="window.history.back();" />
				</div>


			<input id="accionPago" type="hidden" name="accionPago">
			<input id="sin" type="hidden" name="sin" value="<%=request.getAttribute("id_siniestro")%>">
			
			<input id="sin1" type="hidden" name="sin1" value="<%=request.getAttribute("a_sin")%>">
          
	</tiles:put>
	
</tiles:insert>



<script language="JavaScript">

	function ir(acc){
		  
		document.getElementById('accionPago').value=acc;
	    document.forms[0].submit();
		
	}








		


</script>





