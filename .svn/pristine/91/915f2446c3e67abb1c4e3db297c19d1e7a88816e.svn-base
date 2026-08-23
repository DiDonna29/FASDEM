<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@	taglib uri="/WEB-INF/tlds/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@page import="ve.gob.dem.framework.recursos.*,java.util.Date,java.text.NumberFormat,java.util.ArrayList,ve.gob.dem.fasdem.bean.*,java.text.SimpleDateFormat"%>
<tiles:insert page="/jsp/plantilla/plantilla.jsp">

<%


		String primera = (String)request.getAttribute("primera");
		ArrayList listaTipoTramite = (ArrayList) request.getAttribute("listaTipoTramite");
		ArrayList listaPoliza = (ArrayList) request.getAttribute("listaPoliza");
		ArrayList listTipoTra = (ArrayList) request.getAttribute("listTipoTra");

		boolean porPatologia= false;
		if(request.getAttribute("porPatologia")!=null){
			porPatologia = Boolean.valueOf((String)request.getAttribute("porPatologia")) ;
		}
		TipoTramite tipoTramite;

%>



	<tiles:put name="titulo" direct="true">
		<bean:message key="principal.titulo" bundle="etiquetas" />
	</tiles:put>

	<tiles:put name="titulopagina" content="Administrador de Cobertura"
		direct="true" />
	<tiles:put name="itemsup" content="/jsp/comunes/items.jsp" />
    <tiles:put name="itemsdown" content=" " direct="true" />
	<tiles:put name="cuerpo" direct="true">
	<div style="overflow: visible"></div>
	
	

	
	
	
	
	
			<div style="overflow: visible">		
				<div class="etiqueta titulo cgp">Monto:</div>
                				<div class="parametro titulo ">
				                  <%if(request.getAttribute("monto")!=null || "".equals(request.getAttribute("monto"))){%>
				                    <input type="text" id="monto" name="monto" onkeyUp="ValNumero(this);" value="<%=request.getAttribute("monto")%>"  >
				                  <%}else{%>
				                  	<input type="text" id="monto" name="monto" onkeyUp="ValNumero(this);" value=""  >
				                  <%}%>

				</div>			
				<div class="etiqueta titulo cgp">Por Patologia:</div>
				<div class="parametro titulo cgp">
				
				<select name="porPatologia" class="cgp">
				
								<%if(porPatologia){%>
				    <option value="True" selected="selected" class="seleccione">Si</option>
				    <option value="False" class="seleccione">No</option>
								<%}else{%>
					<option value="True" class="seleccione">Si</option>
				    <option value="False" selected="selected" class="seleccione">No</option>
								<%}%>
		        </select>
		        
		        </div>		                

<div class="sp5"></div>
				<div align="right">

				<input class="boton" value="Aceptar" type="button"  onclick="ir('1');" />
				</div>
<div class="sp5"></div>
	 <% if (listaTipoTramite!=null && listaTipoTramite.size()!=0){%>				
				
				<div class="etiqueta titulo cgp"></div>
				<div class="parametro titulo cgp">
				 
				<table class="tabla" width="600" cellpadding="2" cellspacing="1"
				border="0">		
	
				<tr class="tituloCabecera">

					<td>Tipo de Tramite</td>
				    <td>Seleccionar</td>					
					
				</tr>
	
	

	  <%
		for (int i=0;i!=listaTipoTramite.size();i++){
			tipoTramite = (TipoTramite) listaTipoTramite.get(i);
      %>  		
				 
				 <tr class="item" bgcolor="white" >
				
				
					<td><%=tipoTramite.getDescripcion()%></td>					
					<td align="center">
				<INPUT type="checkbox"   value="<%=tipoTramite.getId()%>" 		  
	   <%for (int j=0;j!=listTipoTra.size();j++){
		   if((Integer)listTipoTra.get(j)==tipoTramite.getId()){
			   %>   checked 
			<%}
		
	   }%>		
				NAME="listTipoTra"></td>		
									
				</tr>
	
				
	<%}%>	
	
			
	
	 				<!--tr class="item" bgcolor="white" >
			            <td align="right" colspan="3">Seleccionar todas los tipos de Proveedor:&nbsp;&nbsp;<INPUT type="checkbox"  onclick='checkedAll(listfact);' value="" NAME="miCheck"> 
						<input type='button' name='checkall' style="width: auto" onclick='checkedAll(listfact);'>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					</tr-->
					
					
	
</table>
	</div>
	<%}%>			
				
				
				<div  align="center" >
				   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</div>
				
				<input id="crea" type="hidden" name="crea">
				
								<input id="accionProveedor" type="hidden" name="accionProveedor">
				
				<input id="poliza" type="hidden" name="poliza" value="<%=request.getAttribute("poliza")%>">

				<input id="id_cobertura" type="hidden" name="id_cobertura" value="<%=request.getAttribute("id_cobertura")%>">

		
			

			
      
  
            
	</tiles:put>
	
</tiles:insert>
</form>


<script language="JavaScript">
	function ir(acc){
		
		document.forms[0].action = "<%=request.getContextPath()%>/security/administradores/modificaCobertura.do"
			if(document.getElementById('monto').value==""){
				alert("Favor introducir un Monto para continuar con el proceso.");
				return;
			}
		document.getElementById('accionProveedor').value='3';
        document.forms[0].submit();	
	}
	
	function modificaP(acc){
		document.getElementById('crea').value=acc;
		document.forms[0].action = "<%=request.getContextPath()%>/security/administradores/ModificarProveedor.do"
        document.forms[0].submit();	
	}

	agregarCobertura
	function crerP(acc){
  		vari = document.getElementById('busca').value;
		vari = vari.replace(/[-.,_]/gi, '');
		document.getElementById('busca').value=vari;
		document.getElementById('crea').value = '1';
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
	
	
	
	function Solo_Numerico(variable){
        Numer=variable;
        if (isNaN(Numer)){
        	valor = Numer.substring(0,Numer.length-1);
            return valor;// Numer.substring(0,Numer.length-2));
        }
        return Numer;
    }
    function ValNumero(Control){
        Control.value=Solo_Numerico(Control.value);
    }


    
    function checkDecimals(fieldName, fieldValue) {

    	decallowed = 2;  // how many decimals are allowed?

    	if (isNaN(fieldValue) || fieldValue == "") {
    	alert("Esto no es un valor de un número válido.");
    	
    	fieldName.select();
    	fieldName.focus();
    	}
    	else {
    	if (fieldValue.indexOf('.') == -1) fieldValue += ".";
    	dectext = fieldValue.substring(fieldValue.indexOf('.')+1, fieldValue.length);

    	if (dectext.length > decallowed)
    	{
    	alert ("Por favor, introducir un número con " + decallowed + " números decimales.");
    	fieldName.select();
    	fieldName.focus();
    	      }
    	else {
    	      }
    	   }
    	}

    function comprueba(obj){
    	if (isNaN(obj.value)){
    	alert("¡Debes introducir un número!");
    	return false;
    	}
    	else {
    	return true;
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



