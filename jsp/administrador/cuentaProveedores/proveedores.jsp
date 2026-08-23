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
		Cuenta cuenta;
		
%>



	<tiles:put name="titulo" direct="true">
		<bean:message key="principal.titulo" bundle="etiquetas" />
	</tiles:put>

	<tiles:put name="titulopagina" content="Administrador de Cuenta de Proveedor"
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
				                  <input name="accionProveedor" type="radio" value="1" <%=request.getAttribute("selec1")%> onclick="doIt(1)">RIF
<input name="accionProveedor" type="radio" value="2" <%=request.getAttribute("selec2")%> onclick="doIt(2)">Nombre


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

			<%if(listaProveedores!=null){%>
			
			<%if(listaProveedores.size()!=0){%>
			
			<div class="textorange" style="margin-left:5px; padding-bottom: 5px">Listado de Patologias</div>
			<div style="overflow: visible">
			<table class="tabla" width="500" cellpadding="0" cellspacing="1" border="0">
				<tr class="tituloCabecera">
					<td>RIF</td>
					<td>Nombre del Proveedor</td>
					<td>Banco</td>
					<td>Tipo de Cuenta</td>
				    <td>N&uacute;mero de Cuenta</td>
				</tr>
				<pg:pager maxPageItems="<%=20%>" maxIndexPages="10" url="adminProveedores.do" isOffset="false" export="offset,currentPageNumber=pageNumber" scope="request">
					<pg:param name="crea" />
					<pg:param name="funcion" />
					<pg:param name="botonera" />		
					<pg:param name="busca" />		
					<c:forEach items="${listProveedores}" var="l">
						<pg:item>
							<tr class="item" bgcolor="white" onclick="javaScript:modificaP('<c:out value="${l.rif}" />')">
								<td><c:out value="${l.rif}" /></td>
								<td><c:out value="${l.nombre}" /></td>
								<td>
								<c:choose>
								<c:when test="${l.objcuenta.nombreBanco == null}">Sin Definir</c:when>
								<c:otherwise><c:out value="${l.objcuenta.nombreBanco}" /></c:otherwise>
								</c:choose>
								</td>
								<td>
								<c:choose>
								<c:when test="${l.objcuenta.tipoCuenta == null}">Sin Definir</c:when>
								<c:when test="${l.objcuenta.tipoCuenta eq 1}">Corriente</c:when>
								<c:otherwise>Ahorro</c:otherwise>
								</c:choose>
								</td>
								<td>
								<c:choose>
								<c:when test="${l.objcuenta.cuenta == null}">Sin Definir</c:when>
								<c:otherwise><c:out value="${l.objcuenta.cuenta}" /></c:otherwise>
								
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
			
			<!--table class="tabla" width="600" cellpadding="2" cellspacing="1" border="0">
				 <tr>
						<td align="center" colspan="5" class="grayp" style="padding-left: 20px; padding-bottom: 5px;">
							<div class="grayp"></div>
							<strong></strong>
							<div class="grayp"></div>
						</td>
					</tr>
				
	
				<tr class="tituloCabecera">

					<td>RIF</td>
					<td>Nombre del Proveedor</td>
					<td>Banco</td>
					<td>Tipo de Cuenta</td>
				    <td>N&uacute;mero de Cuenta</td>
					
				</tr>
	
	

	  	
				<%
		for (int i=0;i!=listaProveedores.size();i++){
		pro = (Clinica) listaProveedores.get(i);
		cuenta = pro.getObjcuenta();
      %>  
				 <tr class="item" bgcolor="white" onclick="modificaP('<%=pro.getRif()%>');">
				
				
					<td><%=pro.getRif()%></td>
					<td><%=pro.getNombre()%></td>
					<%if(cuenta==null){%>
						<td>Sin Definir</td>
						<td>Sin Definir</td>
						<td>Sin Definir</td>
					<%}else{%>
						<td><%=cuenta.getNombreBanco()%></td>
						<td><%if(cuenta.getTipoCuenta().equals("1")){%>
						Corriente
						<%}else{%>
						Ahorro
						<%}%>
						</td>
						<td><%=cuenta.getCuenta()%></td>
					<%}%>
						
					
					
				</tr>
				<tr align="left">
					<td align="left" colspan="5">
                    </td>
				</tr>
				

			<%}%>	
		</table-->
			<%}else{%>	
			<table class="tabla" width="600" cellpadding="2" cellspacing="1" border="0">
							 <tr>
						<td align="center" colspan="5" class="grayp" style="padding-left: 20px; padding-bottom: 5px;">
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
	

	
	
		

	
			</table>
            <% } %>
			
			<% } %>
			
			
				
				
				<div  align="center" >
				   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</div>
				
				<input id="crea" type="hidden" name="crea">
				
				<input id="vali" type="hidden" name="vali">
				


		
			

			
      
  
            
	</tiles:put>
	
</tiles:insert>

</form>

<script language="JavaScript">
	function ir(acc){
		if(document.getElementById('busca').value ==  ""){
			alert("Favor introducir texto para realizar la búsqueda.");
			return;
		}
  		vari = document.getElementById('busca').value;
		
		if(vari.length <  3){
			alert("Favor introducir texto con minimo 3 caracteres.");
			return;
		}
		vari = vari.replace(/[-.,_]/gi, '');
		document.getElementById('busca').value=vari;
		document.forms[0].action = "<%=request.getContextPath()%>/security/administradores/CuentasProveedor.do"
		document.getElementById('crea').value='';
		document.getElementById('vali').value=acc;
        document.forms[0].submit();	
	}
	
	function modificaP(acc){
		document.getElementById('crea').value=acc;
		document.forms[0].action = "<%=request.getContextPath()%>/security/administradores/CuentasProveedor.do"
        document.forms[0].submit();	
	}


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
		


</script>

	<%
 String mensaje = (String) request.getAttribute("mensaje");
 if (mensaje != null){%>
 	<script>
 	 	alert('<%=mensaje%>');
 	</script>
 <%}
%>



