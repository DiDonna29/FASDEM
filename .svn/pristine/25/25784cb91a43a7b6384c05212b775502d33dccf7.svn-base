<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@page import="ve.gob.dem.framework.recursos.*,java.util.Date,java.text.NumberFormat,java.util.ArrayList,ve.gob.dem.fasdem.bean.*,java.text.SimpleDateFormat"%>
<tiles:insert page="/jsp/plantilla/plantilla.jsp">

<%


		String primera = (String)request.getAttribute("primera");
		Persona persona = (Persona)request.getAttribute("persona");
		Cuenta cuenta = (Cuenta)request.getAttribute("cuenta");
		ArrayList listaBanco = (ArrayList) request.getAttribute("listaBanco");
		Banco banco;
		String tc;
		String cb;
		String cuentanumero;
		if(cuenta == null ){cb = "-1";}else{cb = cuenta.getCodBanco();}
		if(cuenta == null ){tc = "-1";}else{tc = cuenta.getTipoCuenta();}
		if(cuenta == null ){cuentanumero = "";}else{cuentanumero = cuenta.getCuenta();}
		

		
%>



	<tiles:put name="titulo" direct="true">
		<bean:message key="principal.titulo" bundle="etiquetas" />
	</tiles:put>

	<tiles:put name="titulopagina" content="Administrador de Cuenta de Beneficiario"
		direct="true" />
	<tiles:put name="itemsup" content=" " direct="true"  />
    <tiles:put name="itemsdown" content=" " direct="true" />
	<tiles:put name="cuerpo" direct="true">
	<div style="overflow: visible"></div>
	
	

	
	
	
	
	
			<div style="overflow: visible">
			
				<div class="etiqueta titulo cgp">C&eacute;dula:</div>
                				<div class="parametro titulo ">
				                  <%=persona.getCedula()%>
</div>

				<div class="etiqueta titulo cgp">Nombres:</div>
                				<div class="parametro titulo ">
				                  <%=persona.getNombres()%>
</div>
				<div class="etiqueta titulo cgp">Apellidos:</div>
                				<div class="parametro titulo ">
				                  <%=persona.getApellidos()%>
</div>

				<div class="etiqueta titulo cgp">Banco:</div>
				<div class="parametro titulo cgp">
				
				<select name="banco"  id="banco" class="cgp">
				    <option value="-1" selected="selected" class="seleccione">[SELECCIONAR]</option>
					
					
						 <%
							for (int j=0;j!=listaBanco.size();j++){
							banco = (Banco) listaBanco.get(j);
							
			      		 %>		
								
								<option 
								<%if(cb.equals(String.valueOf(banco.getId()))){%>
								selected="selected"
								<%}%>
								value=<%=banco.getId()%>><%=banco.getDescripcion()%>
								</option>
								
					    <%}%>	
					
					
					
		        </select>
		        
		        </div>
				<div class="etiqueta titulo cgp">Tipo de Cuenta:</div>
				<div class="parametro titulo cgp">
				
				<select name="tipoCuenta" id="tipoCuenta" class="cgp">
				    <option value="-1" selected="selected" class="seleccione">[SELECCIONAR]</option>
					
					
								
								<option 
								<%if(tc.equals("1")){%>
								selected="selected"
								<%}%>
								value="1" class="seleccione">Corriente
								</option>
								<option 
								<%if(tc.equals("2")){%>
								selected="selected"
								<%}%>
								value="2" class="seleccione">Ahorro
								</option>
	
					
		        </select>
		        
		        </div>
				<div class="etiqueta titulo cgp">N&uacute;mero de Cuenta:</div>
                				<div class="parametro titulo ">
				                    <input type="text" id="numeroCuenta" name="numeroCuenta" value="<%=cuentanumero%>"  >
</div>
                


<script>
function doIt(_v) {
document.getElementById('accionProveedor').value=_v;
//document.globalActionForm.busca.value=_v;
}
</script>


   				
<div class="sp5"></div>
				<div align="right">
				<%if(request.getAttribute("funcion").equals("2")){%>
				<input class="boton" value="Crear" type="button"  onclick="crerP();" />
				<%}else{%>
				<input class="boton" value="Modificar" type="button"  onclick="modificaP('<%=persona.getCedula()%>');" />
				<input class="boton" value="Cancelar" type="button"  onclick="inicio();" />
				<%}%>
				</div>


			
			
				
				
				<div  align="center" >
				   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</div>
				
				<input id="crea" type="hidden" name="crea" value="<%=persona.getCedula()%>">
				<input id="botonera" type="hidden" name="botonera" value="<%=request.getAttribute("botonera")%>">
							<input id="busca" type="hidden" name="busca" >
				
								<input id="funcion" type="hidden" name="funcion">
				


		
			

			
      
  
            
	</tiles:put>
	
</tiles:insert>

</form>

<script language="JavaScript">
	function ir(acc){
		document.getElementById('crea').value=acc;
		document.getElementById('funcion').value='3';		
		document.getElementById('botonera').value='1';		
		document.forms[0].action = "<%=request.getContextPath()%>/security/administradores/CreaCuenta.do"
		document.forms[0].submit();	

	}
	
	function modificaP(acc){
		if(document.getElementById('banco').value ==  "-1"){
			alert("Favor seleccionar un Banco para continuar con la operación.");
			return;
		}
		if(document.getElementById('tipoCuenta').value ==  "-1"){
			alert("Favor seleccionar un tipo de Cuenta para continuar con la operación.");
			return;
		}
		if(document.getElementById('numeroCuenta').value ==  ""){
			alert("Favor introducir el Número de Cuenta para continuar con la operación.");
			return;
		}
		cuent = document.getElementById('numeroCuenta').value;
		if(cuent.length !=  20){
			alert("Favor introducir un Número de Cuenta de 20 digitos para continuar con la operación.");
			return;
		}

		document.getElementById('crea').value=acc;
		document.getElementById('funcion').value='1';
		document.forms[0].action = "<%=request.getContextPath()%>/security/administradores/CreaCuenta.do"
        document.forms[0].submit();	
	}


	function crerP(){
		if(document.getElementById('banco').value ==  "-1"){
			alert("Favor seleccionar un Banco para continuar con la operación.");
			return;
		}
		if(document.getElementById('tipoCuenta').value ==  "-1"){
			alert("Favor seleccionar un tipo de Cuenta para continuar con la operación.");
			return;
		}
		if(document.getElementById('numeroCuenta').value ==  ""){
			alert("Favor introducir el Número de Cuenta para continuar con la operación.");
			return;
		}
		cuent = document.getElementById('numeroCuenta').value;
		if(cuent.length !=  20){
			alert("Favor introducir un Número de Cuenta de 20 digitos para continuar con la operación.");
			return;
		}


		document.getElementById('funcion').value='2';
		document.forms[0].action = "<%=request.getContextPath()%>/security/administradores/CreaCuenta.do"
        document.forms[0].submit();	
	}


	function inicio(){
		document.getElementById('busca').value =  '';
		document.getElementById('funcion').value = '';
		document.forms[0].action = "<%=request.getContextPath()%>/security/administradores/CuentasNomina.do"
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

	<%
 String mensaje = (String) request.getAttribute("mensaje");
 if (mensaje != null){%>
 	<script>
 	 	alert('<%=mensaje%>');
 	</script>
 <%}
%>



