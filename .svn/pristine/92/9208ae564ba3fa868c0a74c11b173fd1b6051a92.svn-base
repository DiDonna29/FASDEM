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
		ArrayList listaCobertura = (ArrayList) request.getAttribute("listaCobertura");
		ArrayList listaPoliza = (ArrayList) request.getAttribute("listaPoliza");
		
		String pol = "";
		if(request.getAttribute("poliza")!=null){
			pol = (String)request.getAttribute("poliza");
		}

		Poliza poliza;

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
				<div class="etiqueta titulo cgp">Poliza:</div>
				<div class="parametro titulo cgp">
				
				<select name="poliza" class="cgp">
				    <option value="-1" selected="selected" class="seleccione">[SELECCIONAR]</option>
					
					
						 <%
							for (int k=0;k!=listaPoliza.size();k++){
							poliza = (Poliza) listaPoliza.get(k);
							
			      		 %>		
								
								<option 
								<%if(pol.equals(String.valueOf(poliza.getId()))){%>
								selected="selected"
								<%}%>
								value=<%=poliza.getId()%>><%=poliza.getDescripcion()%>
								</option>
								
					    <%}%>	
					
					
					
		        </select>
		        
		        </div>			




<div class="sp5"></div>
				<div align="right">
				<input class="boton" value="Crear Coberturas" type="button"  onclick="crerP();" />
				<input class="boton" value="Buscar Coberturas" type="button"  onclick="ir('1');" />
				</div>

			<%if(listaCobertura!=null){%>
			
			<%if(listaCobertura.size()!=0){%>
			
			<div class="textorange" style="margin-left:5px; padding-bottom: 5px">Listado de Coberturas</div>
			<div style="overflow: visible">
			<table class="tabla" width="500" cellpadding="0" cellspacing="1" border="0">
				<tr class="tituloCabecera">
					<td>Nombre de la Cobertura</td>
					<td>Monto</td>
					<td>Por Patologia</td>
					<td>Estatus</td>
					<td>Modificar</td>
					<td>Cambiar Estatus</td>
				</tr>
				<pg:pager maxPageItems="<%=20%>" maxIndexPages="10" url="adminProveedores.do" isOffset="false" export="offset,currentPageNumber=pageNumber" scope="request">
					<pg:param name="crea" />
					<pg:param name="funcion" />
					<pg:param name="botonera" />		
					<pg:param name="busca" />		
					<c:forEach items="${listaCobertura}" var="l">
						<pg:item>
							<tr class="item" bgcolor="white" >
								<td><c:out value="${l.tipoCobertura.descripcion}" /></td>
								<td><c:out value="${l.monto}" /></td>
								<td>
								<c:choose>
								<c:when test="${l.porPatologia == true}">Si</c:when>
								<c:otherwise>No</c:otherwise>
								</c:choose>
								</td>
								<td>
								<c:choose>
								<c:when test="${l.isActivo == true}">Activo</c:when>
								<c:otherwise>Inactivo</c:otherwise>
								</c:choose>
								</td>
								<td class="norange" onclick="javaScript:modificaP('<c:out value="${l.id}" />')">Modificar</td>
								<td class="nredp" onclick="javaScript:modificaEstatus('<c:out value="${l.id}" />',<c:out value="${l.isActivo}" />)">Cambiar Estatus</td>
								
							</tr>
						</pg:item>
					</c:forEach>
					<pg:index>
						<tr bgcolor="#FFFFFF">
							<td colspan="9" width="100%" align="right" class="textblue"><pg:prev export="pageUrl">
								<IMG height="11" src="<%=request.getContextPath()%>/images/left1.gif" width="10" align="middle">&nbsp; 
					<a style="cursor: hand; cursor: pointer;" class="textorange" href="<c:out value='${pageUrl}'/>">Anterior</a>
							</pg:prev>&nbsp; | <pg:pages>
								<c:choose>
									<c:when test="${pageNumber==currentPageNumber}">
										<c:out value="${pageNumber}" />
									</c:when>
									<c:otherwise>
										<a style="cursor: hand; cursor: pointer;" class="textorange" href="<c:out value='${pageUrl}'/>"> <c:out value="${pageNumber}" /> </a>
									</c:otherwise>
								</c:choose>
							</pg:pages>| <pg:next export="pageUrl">
								<a style="cursor: hand; cursor: pointer;" class="textorange" href="<c:out value="${pageUrl}"/>"> Siguiente</a>&nbsp;<IMG height="11"
									src="<%=request.getContextPath()%>/images/right1.gif" width="10" align="middle">
							</pg:next>&nbsp;</td>
						</tr>
					</pg:index>
				</pg:pager>
			</table>
			</div>		
			
			
			

			<%}else{%>	
			<table class="tabla" width="600" cellpadding="2" cellspacing="1" border="0">
							 <tr>
						<td align="center" colspan="6" class="grayp" style="padding-left: 20px; padding-bottom: 5px;">
							<div class="grayp"></div>
							<strong></strong>
							<div class="grayp"></div>
						</td>
					</tr>
					
				<tr align="left">
					<td align="left" colspan="6">
                    </td>
				</tr>

	

	
			
	
					 <tr>
						<td align="center" class="grayp" style="padding-left: 20px; padding-bottom: 5px;">
							<div class="grayp"></div>
							<strong>No existen Proveedores con esta caracteristica</strong>
						    <div class="grayp"></div>
						</td>
					</tr>	
					 <tr>
						<td align="center" class="grayp" style="padding-left: 20px; padding-bottom: 5px;">
						    <div class="grayp"></div>
							<strong>¿Desea crear un Nuevo Proveedor?</strong>
						    <div class="grayp"></div>
						    
						</td>
					</tr>	
					 <tr>
						<td align="center" class="grayp" style="padding-left: 20px; padding-bottom: 5px;">
						
						<div align="center">
				<input class="boton" value="aceptar" type="button"  onclick="crerP('1');" />
				</div>
						</td>

					</tr>
	

	
	
		

	
			</table>
            <% } %>
			
			<% } %>
			
			
				
				
				<div  align="center" >
				   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</div>
				
				<input id="crea" type="hidden" name="crea">
				
								<input id="accionProveedor" type="hidden" name="accionProveedor">
				

				<input id="id_cobertura" type="hidden" name="id_cobertura">
				
								<input id="activar" type="hidden" name="activar">

		
			

			
      
  
            
	</tiles:put>
	
</tiles:insert>
</form>


<script language="JavaScript">
	function ir(acc){
		document.forms[0].action = "<%=request.getContextPath()%>/security/administradores/AdminCobertura.do"
		document.getElementById('accionProveedor').value='3';
        document.forms[0].submit();	
	}
	
	function modificaP(acc){
		
		document.getElementById('id_cobertura').value=acc;
		document.getElementById('accionProveedor').value='1';
		document.forms[0].action = "<%=request.getContextPath()%>/security/administradores/modificaCobertura.do"
        document.forms[0].submit();	
	}

	function modificaEstatus(acc,acc2){
		document.getElementById('id_cobertura').value=acc;
		var t = acc2.length
if(acc2){

	document.getElementById('activar').value='False';
	
}else{

	document.getElementById('activar').value='True';

}

		document.getElementById('accionProveedor').value='2';
		document.forms[0].action = "<%=request.getContextPath()%>/security/administradores/AdminCobertura.do"
        document.forms[0].submit();	
	}

	function crerP(acc){
		//document.getElementById('accionProveedor').value='3';
		document.forms[0].action = "<%=request.getContextPath()%>/security/administradores/agregarCobertura.do"
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



