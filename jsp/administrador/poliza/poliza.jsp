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
		ArrayList listaPoliza = (ArrayList) request.getAttribute("listPoliza");
		Especialidad especialidad;
	     

		boolean is_activo= false;
		if(request.getAttribute("activo")!=null){
			is_activo = (Boolean)request.getAttribute("activo") ;
		}
		

		
%>



	<tiles:put name="titulo" direct="true">
		<bean:message key="principal.titulo" bundle="etiquetas" />
	</tiles:put>

	<tiles:put name="titulopagina" content="Administrador de Poliza"
		direct="true" />
	<tiles:put name="itemsup" content="/jsp/comunes/items.jsp" />
    <tiles:put name="itemsdown" content=" " direct="true" />
	<tiles:put name="cuerpo" direct="true">
	<div style="overflow: visible"></div>
	
	

	
	
	
	
	
			<div style="overflow: visible">
			
				<div class="etiqueta titulo cgp">Descripci&oacute;n:</div>
                				<div class="parametro titulo ">
				                  <%if(request.getAttribute("busca")!=null || "".equals(request.getAttribute("busca"))){%>
				                    <input type="text" id="busca" name="busca" value="<%=request.getAttribute("busca")%>"  >
				                  <%}else{%>
				                  	<input type="text" id="busca" name="busca" value=""  >
				                  <%}%>


<script>
function doIt(_v) {
document.getElementById('accionProveedor').value=_v;
//document.globalActionForm.busca.value=_v;
}
</script>


   				</div>
				<div class="etiqueta titulo cgp">Fecha Inicio Vigencia</div>
				<div class="parametro titulo ">
				                  <%if(request.getAttribute("fechaInicio")!=null || "".equals(request.getAttribute("fechaInicio"))){%>
				<input type="text" name="fechaInicio" value="<fmt:formatDate value="${fechaInicio}" pattern="dd/MM/yyyy"/>"  onfocus="javascript:showCalendarSelected2(document.forms[0].fechaInicio, document.forms[0].fechaInicio, 'dd/mm/yyyy','es',1);blur();" class="cgp">
				                  <%}else{%>
				<input type="text" name="fechaInicio" onfocus="javascript:showCalendarSelected2(document.forms[0].fechaInicio, document.forms[0].fechaInicio, 'dd/mm/yyyy','es',1);blur();" class="cgp">
				                  <%}%>
				</div>
 
 
				<div class="etiqueta titulo cgp">Fecha Fin Vigencia</div>
				<div class="parametro titulo ">
				                  <%if(request.getAttribute("fechaFin")!=null || "".equals(request.getAttribute("fechaFin"))){%>
				<input type="text" name="fechaFin" value="<fmt:formatDate value="${fechaFin}" pattern="dd/MM/yyyy"/>"  onfocus="javascript:showCalendarSelected2(document.forms[0].fechaFin, document.forms[0].fechaFin, 'dd/mm/yyyy','es',1);blur();" class="cgp">
				                  <%}else{%>
				<input type="text" name="fechaFin" onfocus="javascript:showCalendarSelected2(document.forms[0].fechaFin, document.forms[0].fechaFin, 'dd/mm/yyyy','es',1);blur();" class="cgp">
				                  <%}%>
				</div>
   				
   				
<div class="sp5"></div>
				<div align="right">
				<%if(request.getAttribute("botonera").equals("1")){%>
				<input class="boton" value="Crear" type="button"  onclick="crerP();" />
				<%}else{%>
				<div class="etiqueta titulo cgp">Activo:</div>
				<div class="parametro titulo cgp">
				
				<select name="activo" class="cgp">
				
								<%if(is_activo){%>
				    <option value="True" selected="selected" class="seleccione">Activo</option>
				    <option value="False" class="seleccione">Inactivo</option>
								<%}else{%>
					<option value="True" class="seleccione">Activo</option>
				    <option value="False" selected="selected" class="seleccione">Inactivo</option>
								<%}%>
		        </select>
		        
		        </div>		                

				<input class="boton" value="Modificar" type="button"  onclick="modificaP('<%=request.getAttribute("id_especialidad")%>');" />
				<input class="boton" value="Cancelar" type="button"  onclick="inicio();" />
				<%}%>
				</div>

			<%if(listaPoliza!=null){%>
			
			<%if(listaPoliza.size()!=0){%>
			
			
					<div class="textorange" style="margin-left:5px; padding-bottom: 5px">Listado de Polizas</div>
			<div style="overflow: visible">
			<table class="tabla" width="500" cellpadding="0" cellspacing="1" border="0">
				<tr class="tituloCabecera">
					<td>Nombre de la poliza</td>
					<td>Fecha Inicio Vigencia</td>
					<td>Fecha Fin Vigencia</td>
					<td>Estatus</td>
				</tr>
				<pg:pager maxPageItems="<%=20%>" maxIndexPages="10" url="adminPoliza.do" isOffset="false" export="offset,currentPageNumber=pageNumber" scope="request">
					<pg:param name="crea" />
					<pg:param name="funcion" />
					<pg:param name="botonera" />		
					<c:forEach items="${listPoliza}" var="l">
						<pg:item>
							<tr class="item" bgcolor="white" onclick="javaScript:ir('<c:out value="${l.id}" />')">
								<td><c:out value="${l.descripcion}" /></td>
								<td><fmt:formatDate value="${l.fechaInicio}" pattern="dd/MM/yyyy"/></td>
								<td><fmt:formatDate value="${l.fechaFin}" pattern="dd/MM/yyyy"/></td>
								<td>
								<c:choose>
								<c:when test="${l.activo == true}">Activo</c:when>
								<c:otherwise>Inactivo</c:otherwise>
								</c:choose>
								</td>
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
		document.forms[0].action = "<%=request.getContextPath()%>/security/administradores/adminPoliza.do"
		document.forms[0].submit();	

	}
	
	function modificaP(acc){
		if(document.getElementById('busca').value ==  ""){
			alert("Favor introducir el nombre de la Especialidad para continuar con la operación.");
			return;
		}

		document.getElementById('crea').value=acc;
		document.getElementById('funcion').value='2';
		document.forms[0].action = "<%=request.getContextPath()%>/security/administradores/adminPoliza.do"
        document.forms[0].submit();	
	}


	function crerP(){
		if(document.getElementById('busca').value ==  ""){
			alert("Favor introducir el nombre de la Especialidad para continuar con la operación.");
			return;
		}

		document.getElementById('funcion').value='1';
		document.forms[0].action = "<%=request.getContextPath()%>/security/administradores/adminPoliza.do"
        document.forms[0].submit();	
	}


	function inicio(){
		document.getElementById('busca').value =  '';
		document.getElementById('funcion').value = '';
		document.forms[0].action = "<%=request.getContextPath()%>/security/administradores/adminPoliza.do"
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



