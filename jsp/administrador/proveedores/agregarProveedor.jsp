<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@page import="ve.gob.dem.framework.recursos.*,java.util.Date,java.text.NumberFormat,java.util.ArrayList,ve.gob.dem.fasdem.bean.*,java.text.SimpleDateFormat"%>
<tiles:insert page="/jsp/plantilla/plantilla.jsp">

<%


		String primera = (String)request.getAttribute("primera");
		ArrayList listaProveedores = (ArrayList) request.getAttribute("listProveedores");
		Clinica pro;
		ArrayList listaEstado = (ArrayList) request.getAttribute("listaEstado");
		Estado estado;
		ArrayList listaTipoProveedor = (ArrayList) request.getAttribute("listaTipoProveedor");
		ArrayList listaTipoTramite = (ArrayList) request.getAttribute("listaTipoTramite");
		ArrayList listaTipoTramitePortal = (ArrayList) request.getAttribute("listaTipoTramitePortal");
		ArrayList listaCiudad = (ArrayList) request.getAttribute("listaCiudad");
		Ciudad ciudad;
		TipoProveedor tipoProveedor;
		TipoTramite tipoTramite;
		
		boolean is_razonable= (Boolean)request.getAttribute("razonable");
		
		
%>



	<tiles:put name="titulo" direct="true">
		<bean:message key="principal.titulo" bundle="etiquetas" />
	</tiles:put>

	<tiles:put name="titulopagina" content="Administrador de Proveedores"
		direct="true" />
	<tiles:put name="itemsup" content="/jsp/comunes/items.jsp" />
    <tiles:put name="itemsdown" content=" " direct="true" />
	<tiles:put name="cuerpo" direct="true">
	<div style="overflow: visible"></div>
	
	

	
	
	
	
			<div style="overflow: visible">
				<div class="etiqueta titulo cgp">RIF:</div>
                				<div class="parametro titulo ">
				                  <%if(request.getAttribute("rif")!=null || "".equals(request.getAttribute("rif"))){%>
				                    <input name="rif" type="text" id="rif" value="<%=request.getAttribute("rif")%>"  >
	                        <%}else{%>
				                  	<input name="rif" type="text" id="rif" value=""  >
				                  <%}%>
				                  </div>

			
				<div class="etiqueta titulo cgp">Descripci&oacute;n:</div>
                				<div class="parametro titulo ">
				                  <%if(request.getAttribute("descripcion")!=null || "".equals(request.getAttribute("descripcion"))){%>
				                    <input type="text" id="descripcion" name="descripcion" value="<%=request.getAttribute("descripcion")%>"  >
				                  <%}else{%>
				                  	<input type="text" id="descripcion" name="descripcion" value=""  >
				                  <%}%>
				                  </div>
                                  
				<div class="etiqueta titulo cgp">Direcci&oacute;n:</div>
                				<div class="parametro titulo ">
				                  <%if(request.getAttribute("direccion")!=null || "".equals(request.getAttribute("direccion"))){%>
                                  <textarea name="direccion" cols="70" rows="2" id="direccion"><%=request.getAttribute("direccion")%></textarea>
			                    <%}else{%>
                                <textarea name="direccion" cols="70" rows="2" id="direccion"></textarea>
				                  <%}%>
				                  </div>
                                  
				<div class="etiqueta titulo cgp">Telefono:</div>
                				<div class="parametro titulo ">
				                  <%if(request.getAttribute("telefono")!=null || "".equals(request.getAttribute("telefono"))){%>
				                    <input name="telefono" type="text" id="telefono" value="<%=request.getAttribute("telefono")%>" size="50"  >
			                    <%}else{%>
				                  	<input name="telefono" type="text" id="telefono" value="" size="50"  >
				                  <%}%>
				                  </div>
				<div class="etiqueta titulo cgp">Persona Contacto:</div>
                				<div class="parametro titulo ">
				                  <%if(request.getAttribute("contacto")!=null || "".equals(request.getAttribute("contacto"))){%>
				                    <input name="contacto" type="text" id="contacto" value="<%=request.getAttribute("contacto")%>" size="50"  >
	                        <%}else{%>
				                  	<input name="contacto" type="text" id="contacto" value="" size="50"  >
				                  <%}%>
				                  </div>
				<div class="etiqueta titulo cgp">Estado:</div>
				<div class="parametro titulo cgp">
				
				<select name="estado" id="estado" class="cgp" onchange="irCuidad();">
				    <option value="-1" selected="selected" class="seleccione">[SELECCIONAR]</option>
					
					
						 <%
							for (int j=0;j!=listaEstado.size();j++){
							estado = (Estado) listaEstado.get(j);
							
			      		 %>		
								
								<option 
								<%if(request.getAttribute("estado").equals(String.valueOf(estado.getId()))){%>
								selected="selected"
								<%}%>
								value=<%=estado.getId()%>><%=estado.getDescripcion()%>
								</option>
								
					    <%}%>	
					
					
					
		        </select>
		        
		        </div>
				<div class="etiqueta titulo cgp">Ciudad:</div>
				<div class="parametro titulo cgp">
				
				<select name="ciudad" id="ciudad" class="cgp">
				    <option value="-1" selected="selected" class="seleccione">[SELECCIONAR]</option>
					
					
						 <%if(listaCiudad!=null && listaCiudad.size()!=0){
							for (int j=0;j!=listaCiudad.size();j++){
							ciudad = (Ciudad) listaCiudad.get(j);
							
			      		 %>		
								
								<option 
								<%if(request.getAttribute("ciudad").equals(String.valueOf(ciudad.getId()))){%>
								selected="selected"
								<%}%>
								value=<%=ciudad.getId()%>><%=ciudad.getDescripcion()%>
								</option>
								
					    <%}}%>	
					
					
					
		        </select>
		        
		        </div>
				<div class="etiqueta titulo cgp">Tipo Proveedor:</div>
				<div class="parametro titulo cgp">
				
				<select name="tipoProveedor" id="tipoProveedor" class="cgp">
				    <option value="-1" selected="selected" class="seleccione">[SELECCIONAR]</option>
					
					
						 <%
							for (int j=0;j!=listaTipoProveedor.size();j++){
								tipoProveedor = (TipoProveedor) listaTipoProveedor.get(j);
							
			      		 %>		
								
								<option 
								<%if(request.getAttribute("tipoProveedor").equals(String.valueOf(tipoProveedor.getId()))){%>
								selected="selected"
								<%}%>
								value=<%=tipoProveedor.getId()%>><%=tipoProveedor.getDescripcion()%>
								</option>
								
					    <%}%>	
					
					
					
		        </select>
		        
		        </div>				   
				<div class="etiqueta titulo cgp">Costo Razonable:</div>
				<div class="parametro titulo cgp">
				
				<select name="razonable" id="razonable" class="cgp">
				    <option value="-1" selected="selected" class="seleccione">[SELECCIONAR]</option>
					
					
	
								<option 
								<%if(request.getAttribute("razonable")!=null && (Boolean)request.getAttribute("razonable")){%>
								selected="selected"
								<%}%>
								value=true>Si
								</option>
								<option 
								<%if(request.getAttribute("razonable")!=null && !(Boolean)request.getAttribute("razonable")){%>
								selected="selected"
								<%}%>
								value=false>No
								</option>
								
					    	
					
					
					
		        </select>
		        
		        </div>
					<div class="etiqueta titulo cgp">Servicios que presta:</div>
                				<div class="parametro titulo ">
				                  <%if(request.getAttribute("servicio")!=null || "".equals(request.getAttribute("servicio"))){%>
                                  <textarea name="servicio" cols="70" rows="2" id="servicio"><%=request.getAttribute("servicio")%></textarea>
			                    <%}else{%>
                                <textarea name="servicio" cols="70" rows="2" id="servicio"></textarea>
				                  <%}%>
				                  </div>
				                

				
				
<div class="etiqueta titulo cgp">	</div>	
<div class="parametro titulo ">		
				<table cellpadding="2" cellspacing="1"
				border="0">		
				 <tr valign="top" >
<td>
	 <% if (listaTipoTramite!=null && listaTipoTramite.size()!=0){%>				
				
				 
				<table class="tabla"  cellpadding="2" cellspacing="1"
				border="0">		
	
				<tr class="tituloCabecera">

					<td>Tipo de Servicio</td>
				    <td>Seleccionar</td>					
					
				</tr>
	
	

	  <%
		for (int i=0;i!=listaTipoTramite.size();i++){
			tipoTramite = (TipoTramite) listaTipoTramite.get(i);
      %>  		
				 
				 <tr class="item" bgcolor="white" >
				
				
					<td><%=tipoTramite.getDescripcion()%></td>					
					<td align="center"><INPUT type="checkbox"   value="<%=tipoTramite.getId()%>" NAME="listTipoTra"></td>		
										
				</tr>
	
				
	<%}%>	
	
							
	
</table>
					
	
	<%}%>	
	</td>
	<td>
		 <% if (listaTipoTramitePortal!=null && listaTipoTramitePortal.size()!=0){%>				
				

				
				 
				<table class="tabla"  cellpadding="2" cellspacing="1"
				border="0">		
	
				<tr class="tituloCabecera">

					<td>Tipo de Servicio del Portal</td>
				    <td>Seleccionar</td>					
					
				</tr>
	
	

	  <%
		for (int i=0;i!=listaTipoTramitePortal.size();i++){
			tipoTramite = (TipoTramite) listaTipoTramitePortal.get(i);
      %>  		
				 
				 <tr class="item" bgcolor="white" >
				
				
					<td><%=tipoTramite.getDescripcion()%></td>					
					<td align="center"><INPUT type="checkbox"   value="<%=tipoTramite.getId()%>" NAME="listTipoTraPortal"></td>		
										
				</tr>
	
				
	<%}%>	
	
			
	

					
	
</table>
	
	<%}%>
	
	</td>
</tr>
	</table>	
</div>
	<input id="accionProveedor" type="hidden" name="accionProveedor">
			
	





<script>
function doIt(_v) {
document.getElementById('accionProveedor').value=_v;
//document.globalActionForm.busca.value=_v;
}
</script>


   				</div>
<div class="sp5"></div>
				<div align="right">
				<input class="boton" value="aceptar" type="button"  onclick="ir('1');" />
				</div>

		
			
			
				
				
				<div  align="center" >
				   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</div>
				
				<input id="crea" type="hidden" name="crea">
				<input id="buscaCiudad" type="hidden" name="buscaCiudad">
				


		
			

			
      
  
            
	</tiles:put>
	
</tiles:insert>
</form>


<script language="JavaScript">
	function ir(acc){
		if(document.getElementById('rif').value ==  ""){
			alert("Favor introducir RIF para realizar la operación.");
			return;
		}
  		vari = document.getElementById('rif').value;
		
		vari = vari.replace(/[-.,_]/gi, '');
		if(vari.length !=  10){
			alert("Favor introducir un RIF de 10 caracteres.");
			return;
		}

		if(document.getElementById('descripcion').value ==  ""){
			alert("Favor introducir una descripcion para realizar la operación.");
			return;
		}

		if(document.getElementById('telefono').value ==  ""){
			alert("Favor introducir un telefono para realizar la operación.");
			return;
		}
	
		if(document.getElementById('estado').value ==  "-1"){
			alert("Favor introducir un estado para realizar la operación.");
			return;
		}
		

		if(document.getElementById('ciudad').value ==  "-1"){
			alert("Favor introducir una ciudad para realizar la operación.");
			return;
		}

		
		if(document.getElementById('tipoProveedor').value ==  "-1"){
			alert("Favor introducir un Tipo Proveedor para realizar la operación.");
			return;
		}
	
		if(document.getElementById('razonable').value ==  "-1"){
			alert("Favor especificar si el proveedor es de costo razonable para realizar la operación.");
			return;
		}

		
		if(document.getElementById('servicio').value ==  ""){
			alert("Favor introducir uno de los servicios que presta para realizar la operación.");
			return;
		}
		
		
		document.getElementById('rif').value=vari;
		document.getElementById('accionProveedor').value='1';
		document.forms[0].action = "<%=request.getContextPath()%>/security/administradores/AgregarProveedor.do"
        document.forms[0].submit();	
	}

	function ir2(acc){
  		vari = document.getElementById('busca').value;
		vari = vari.replace(/[-.,_]/gi, '');
		document.getElementById('busca').value=vari;
		document.getElementById('crea').value = '1';
        document.forms[0].submit();	
	}

	function irCuidad(){
		if(document.getElementById('estado').value ==  "-1"){
			return;
		}
		document.getElementById('buscaCiudad').value = '1';
		document.forms[0].action = "<%=request.getContextPath()%>/security/administradores/AgregarProveedor.do"			 
		document.forms[0].submit();	
	}

	
   
	
	
	
	
	function ir3(acc){
  		vari = document.getElementById('busca').value;
		vari = vari.replace(/[-.,_]/gi, '');
		document.getElementById('busca').value=vari;
		document.getElementById('modifica').value = acc;
        document.forms[0].submit();	
	}


	function checkedAll (frm1) {
		
		if (document.forms[0].miCheck.checked){
			alert(document.forms[0].miCheck.checked);
			document.forms[0].listfact.checked = true;
		return;
		}
		document.forms[0].listfact.checked = true;
		
		//checked=false;
		//var aa= document.forms[0];

		//for (var i =0; i < aa.elements.length; i++) 
		//{
			// if (aa.elements[i].checked == false)
	         //{
	          //checked = true
	         //}
	       //else
	         //{
	         //checked = false
	         //}

	         
		// aa.elements[i].checked = checked;
		//}
	      }
		


</script>

	<%
 String mensaje = (String) request.getAttribute("mensaje");
 if (mensaje != null){%>
 	<script>
 	 	alert('<%=mensaje%>');
 	</script>
 <%}
%>
	



