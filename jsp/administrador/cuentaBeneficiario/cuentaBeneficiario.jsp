<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@page import="ve.gob.dem.framework.recursos.*,java.util.Date,java.text.NumberFormat,java.util.ArrayList,ve.gob.dem.fasdem.bean.*,java.text.SimpleDateFormat"%>
<tiles:insert page="/jsp/plantilla/plantilla.jsp">

<%



		
%>



	<tiles:put name="titulo" direct="true">
		<bean:message key="principal.titulo" bundle="etiquetas" />
	</tiles:put>

	<tiles:put name="titulopagina" content="Administrador de Cuenta de Beneficiario"
		direct="true" />
	<tiles:put name="itemsup" content="/jsp/comunes/items.jsp" />
    <tiles:put name="itemsdown" content=" " direct="true" />
	<tiles:put name="cuerpo" direct="true">
	<div style="overflow: visible"></div>
	
	

	
	
	
	
	
			<div style="overflow: visible">
			
				<div class="etiqueta titulo cgp">C&eacute;dula de Beneficiario:</div>
                				<div class="parametro titulo ">
				                  <%if(request.getAttribute("busca")!=null || "".equals(request.getAttribute("busca"))){%>
				                    <input type="text" id="busca" name="busca" value="<%=request.getAttribute("busca")%>"  >
				                  <%}else{%>
				                  	<input type="text" id="busca" name="busca" value=""  >
				                  <%}%>
</div>


<script>
function doIt(_v) {
document.getElementById('accionProveedor').value=_v;
//document.globalActionForm.busca.value=_v;
}
</script>


   				
<div class="sp5"></div>
				<div align="right">
				<input class="boton" value="Aceptar" type="button"  onclick="crerP();" />
				</div>


			
			
				
				
				<div  align="center" >
				   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</div>
				
				<input id="crea" type="hidden" name="crea">
				<input id="botonera" type="hidden" name="botonera" value="<%=request.getAttribute("botonera")%>">
				
								<input id="funcion" type="hidden" name="funcion">
				


		
			

			
      
  
            
	</tiles:put>
	
</tiles:insert>

</form>

<script language="JavaScript">
	function ir(acc){
		document.getElementById('crea').value=acc;
		document.getElementById('funcion').value='3';		
		document.getElementById('botonera').value='1';		
		document.forms[0].action = "<%=request.getContextPath()%>/security/administradores/CuentasNomina.do"
		document.forms[0].submit();	

	}
	
	function modificaP(acc){
		if(document.getElementById('busca').value ==  ""){
			alert("Favor introducir el nombre de la Especialidad para continuar con la operación.");
			return;
		}

		document.getElementById('crea').value=acc;
		document.getElementById('funcion').value='2';
		document.forms[0].action = "<%=request.getContextPath()%>/security/administradores/CuentasNomina.do"
        document.forms[0].submit();	
	}


	function crerP(){
		if(document.getElementById('busca').value ==  ""){
			alert("Favor introducir el nombre de la Especialidad para continuar con la operación.");
			return;
		}

		document.getElementById('funcion').value='1';
		document.forms[0].action = "<%=request.getContextPath()%>/security/administradores/CuentasNomina.do"
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



