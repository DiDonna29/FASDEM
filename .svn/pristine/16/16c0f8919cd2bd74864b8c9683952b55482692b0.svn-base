<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@	taglib uri="/WEB-INF/tlds/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@page import="ve.gob.dem.framework.recursos.*,java.util.Date,java.text.NumberFormat,java.util.ArrayList,ve.gob.dem.fasdem.bean.*,java.text.SimpleDateFormat"%>
<tiles:insert page="/jsp/plantilla/plantilla.jsp">

	<%
		String primera = (String) request.getAttribute("primera");
			ArrayList listaEspecialidad = (ArrayList) request
					.getAttribute("listaEspecialidad");
			ArrayList listaPatologia = (ArrayList) request
					.getAttribute("listaPatologia");
			ArrayList listaOrgano = (ArrayList) request
					.getAttribute("listaOrgano");
			ArrayList listaTratamiento = (ArrayList) request
					.getAttribute("listaTratamiento");
			ArrayList listaCausas = (ArrayList) request
					.getAttribute("listaCausas");
			Especialidad especialidad;
			Organo organo;
			Patologias patologia;
			Tratamiento tratamiento;
			PatologiaOrganoTratamiento causa;
	%>



	<tiles:put name="titulo" direct="true">
		<bean:message key="principal.titulo" bundle="etiquetas" />
	</tiles:put>

	<tiles:put name="titulopagina" content="Administrador de Causas de Ingreso" direct="true" />
	<tiles:put name="itemsup" content="/jsp/comunes/items.jsp" />
	<tiles:put name="itemsdown" content=" " direct="true" />
	<tiles:put name="cuerpo" direct="true">
		<div style="overflow: visible"></div>









		<div style="overflow: visible">



		<div class="etiqueta titulo cgp">Especialidad:</div>
		<div class="parametro titulo cgp"><select name="especialidad" id="especialidad" class="cgp" onChange="">
			<option value="-1" selected="selected" class="seleccione">[SELECCIONAR]</option>


			<%
				for (int j = 0; j != listaEspecialidad.size(); j++) {
							especialidad = (Especialidad) listaEspecialidad.get(j);
			%>

			<option <%if (request.getAttribute("especialidad") != null
								&& request.getAttribute("especialidad").equals(
										String.valueOf(especialidad.getId()))) {%> selected="selected" <%}%> value=<%=especialidad.getId()%>><%=especialidad.getDescripcion()%></option>

			<%
				}
			%>



		</select></div>
		<div class="etiqueta titulo cgp">Organo:</div>
		<div class="parametro titulo cgp"><select name="organo" id="organo" class="cgp">
			<option value="-1" selected="selected" class="seleccione">[SELECCIONAR]</option>


			<%
				for (int j = 0; j != listaOrgano.size(); j++) {
							organo = (Organo) listaOrgano.get(j);
			%>

			<option <%if (request.getAttribute("organo") != null
								&& request.getAttribute("organo").equals(
										String.valueOf(organo.getId()))) {%> selected="selected" <%}%> value=<%=organo.getId()%>><%=organo.getDescripcion()%></option>

			<%
				}
			%>



		</select></div>
		<div class="etiqueta titulo cgp">Patologia:</div>
		<div class="parametro titulo cgp"><select name="patologia" id="patologia" class="cgp">
			<option value="-1" selected="selected" class="seleccione">[SELECCIONAR]</option>


			<%
				for (int j = 0; j != listaPatologia.size(); j++) {
							patologia = (Patologias) listaPatologia.get(j);
			%>

			<option <%if (request.getAttribute("patologia") != null
								&& request.getAttribute("patologia").equals(
										String.valueOf(patologia.getId()))) {%> selected="selected" <%}%> value=<%=patologia.getId()%>><%=patologia.getDescripcion()%></option>

			<%
				}
			%>



		</select></div>
		<div class="etiqueta titulo cgp">Tratamiento:</div>
		<div class="parametro titulo cgp"><select name="tratamiento" id="tratamiento" class="cgp">
			<option value="-1" selected="selected" class="seleccione">[SELECCIONAR]</option>


			<%
				for (int j = 0; j != listaTratamiento.size(); j++) {
							tratamiento = (Tratamiento) listaTratamiento.get(j);
			%>

			<option <%if (request.getAttribute("tratamiento") != null
								&& request.getAttribute("tratamiento").equals(
										String.valueOf(tratamiento.getId()))) {%> selected="selected" <%}%> value=<%=tratamiento.getId()%>><%=tratamiento.getDescripcion()%></option>

			<%
				}
			%>



		</select></div>
		</div>



		<script>
function doIt(_v) {
document.getElementById('accionProveedor').value=_v;
//document.globalActionForm.busca.value=_v;
}
</script>



		<div class="sp5"></div>
		<div align="right">
		<%if (request.getAttribute("funcion").equals("1")) {
 		  	if (request.getAttribute("listaCausas") != null) {
 				if (listaCausas.size() != 0) {%> 
 					<input class="boton" value="Buscar" type="button" onclick="ir(1);" /> 
 				<%}else{%> 
 					<input class="boton" value="Crear" type="button" onclick="crerP();" /> 
 					<input class="boton" value="Cancelar" type="button" onclick="inicio();" /> 
 				<%}
 			} else {%> 
 			<input class="boton" value="Buscar" type="button" onclick="ir(1);" /> 
 			<%}
 		}%>
		</div>







		<%
			if (listaCausas != null) {
		%>

		<%
			if (listaCausas.size() != 0) {
		%>

		<div class="textorange" style="margin-left: 5px; padding-bottom: 5px">Listado de Causas de Ingreso</div>
		<div style="overflow: visible">
		<table class="tabla" width="500" cellpadding="0" cellspacing="1" border="0">
			<tr class="tituloCabecera">
				<td>Especialidad</td>
				<td>Organo</td>
				<td>Patologia</td>
				<td>Tratameinto</td>
				<td>Estatus</td>
			</tr>
			<pg:pager maxPageItems="<%=20%>" maxIndexPages="10" url="causaIngreso.do" isOffset="false" export="offset,currentPageNumber=pageNumber" scope="request">
				<pg:param name="especialidad" />
				<pg:param name="organo" />
				<pg:param name="patologia" />
				<pg:param name="tratamiento" />
				<pg:param name="busca" />
				<pg:param name="funcion" />
				<c:forEach items="${listaCausas}" var="l">
					<pg:item>
						<tr class="item" bgcolor="white" onclick="javaScript:modificaP('<c:out value="${l.id}" />')">
							<td><c:out value="${l.strEspecialidad}" /></td>
							<td><c:out value="${l.strOrgano}" /></td>
							<td><c:out value="${l.strPatologia}" /></td>
							<td><c:out value="${l.strTratamiento}" /></td>
							<td><c:choose>
								<c:when test="${l.isActivo == true}">Activo</c:when>
								<c:otherwise>Inactivo</c:otherwise>
							</c:choose></td>

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
							<a style="cursor: hand; cursor: pointer;" class="textorange" href="<c:out value="${pageUrl}"/>"> Siguiente</a>&nbsp;<IMG height="11" src="<%=request.getContextPath()%>/images/right1.gif" width="10" align="middle">
						</pg:next>&nbsp;</td>
					</tr>
				</pg:index>
			</pg:pager>
		</table>
		</div>

		<!--table class="tabla" width="600" cellpadding="0" cellspacing="1" border="0">
				 <tr>
						<td align="center" colspan="2" class="grayp" style="padding-left: 20px; padding-bottom: 5px;">
							<div class="grayp"></div>
							<strong></strong>
							<div class="grayp"></div>
						</td>
					</tr>
				
	
				<tr class="tituloCabecera">

					<td>Especialidad</td>
					<td>Organo</td>
					<td>Patologia</td>
					<td>Tratamiento</td>
					<td>Estatus</td>
					
				</tr>
	
	

	  	
				<%for (int i = 0; i != listaCausas.size(); i++) {
								causa = (PatologiaOrganoTratamiento) listaCausas
										.get(i);%>  
				 <tr class="item" bgcolor="white" onclick="ir('<%=causa.getId()%>');">
				
					<td><%=causa.getStrEspecialidad()%></td>
					<td><%=causa.getStrOrgano()%></td>
					<td><%=causa.getStrPatologia()%></td>
					<td><%=causa.getStrTratamiento()%></td>
					<td><%if (causa.getIsActivo()) {%>Activo<%} else {%>Inactivo<%}%></td>
						
					
					
				</tr>
				<tr align="left">
					<td align="left" colspan="72">
                    </td>
				</tr>
				

			<%}%>	
		</table-->

		<%
			}
		%>
		<%
			}
		%>

		<div align="center">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>

		<input id="crea" type="hidden" name="crea" value="">
		<input id="botonera" type="hidden" name="botonera" value="<%=request.getAttribute("botonera")%>">
		<input id="busca" type="hidden" name="busca" />

		<input id="funcion" type="hidden" name="funcion" />














	</tiles:put>

</tiles:insert>



<script language="JavaScript">
	function ir(acc){
		document.getElementById('busca').value = '1';
		document.forms[0].action = "<%=request.getContextPath()%>/security/administradores/causaIngreso/causaIngreso.do"
		document.forms[0].submit();	

	}
	
	function modificaP(acc){
		document.getElementById('busca').value = '1';
		document.getElementById('crea').value=acc;
		document.getElementById('funcion').value='3';
		document.forms[0].action = "<%=request.getContextPath()%>/security/administradores/causaIngreso/causaIngreso.do"
        document.forms[0].submit();	
	}


	function crerP(){
		if(document.getElementById('especialidad').value ==  "-1"){
			alert("Favor seleccionar una Especialidad para continuar con la operación.");
			return;
		}
		if(document.getElementById('organo').value ==  "-1"){
			alert("Favor seleccionar un Organo para continuar con la operación.");
			return;
		}
		if(document.getElementById('patologia').value ==  "-1"){
			alert("Favor seleccionar una Patologia para continuar con la operación.");
			return;
		}
		if(document.getElementById('tratamiento').value ==  "-1"){
			alert("Favor seleccionar un Tratamiento para continuar con la operación.");
			return;
		}
		document.getElementById('busca').value = '1';
		document.getElementById('funcion').value='2';
		document.forms[0].action = "<%=request.getContextPath()%>/security/administradores/causaIngreso/causaIngreso.do"
        document.forms[0].submit();	
	}


	function inicio(){
		document.getElementById('busca').value =  '';
		document.getElementById('funcion').value = '1';
		document.getElementById('busca').value = '0';
		document.getElementById('especialidad').value ='-1';
		document.getElementById('organo').value ='-1';
		document.getElementById('patologia').value ='-1';
		document.getElementById('tratamiento').value ='-1';		
		document.forms[0].action = "<%=request.getContextPath()%>/security/administradores/causaIngreso/causaIngreso.do"
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
	if (mensaje != null) {
%>
<script>
 	 	alert('<%=mensaje%>');
 	</script>
<%
	}
%>



