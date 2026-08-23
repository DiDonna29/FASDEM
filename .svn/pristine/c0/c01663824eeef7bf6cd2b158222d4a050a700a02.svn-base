<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@page import="ve.gob.dem.framework.recursos.*,java.util.Date,java.text.NumberFormat,java.util.ArrayList,ve.gob.dem.fasdem.bean.*,java.text.SimpleDateFormat"%>
<tiles:insert page="/jsp/plantilla/plantilla.jsp">
<%
	     	 ArrayList TipoE=(ArrayList)request.getAttribute("tipoEmpleado");
	     	 TipoEmpleado tipE =null;
%>


	<tiles:put name="titulo" direct="true">
		<bean:message key="principal.titulo" bundle="etiquetas" />
	</tiles:put>

	<tiles:put name="titulopagina" content="Consulta de Siniestros"
		direct="true" />
	<tiles:put name="itemsup" content="" direct="true" />
	<tiles:put name="itemsdown" content="" direct="true" />


	<tiles:put name="cuerpo" direct="true">

		<div class="etiqueta titulo cgp"><bean:message
			key="general.subCodigo" bundle="etiquetas" /></div>
		<div class="nblack "><c:out
			value="${siniestro.aniomesCodigo}-${siniestro.codigo}-${siniestro.subCodigo}" />
		</div>
		<div class="nblack ">
		<input id="a_sin2"  type="hidden" name="a_sin2" value="<%=request.getAttribute("a_sin")%>"></input>
		</div>

<div class="etiqueta titulo cgp"><bean:message
			key="general.cobertura" bundle="etiquetas" /></div>
		<div class="parametro titulo "><c:out
			value="${siniestro.cobertura.tipoCobertura.descripcion}" /> </div>
		<div class="etiqueta titulo cgp"><bean:message
			key="general.nombres" bundle="etiquetas" /> y <bean:message
			key="general.apellidos" bundle="etiquetas" /></div>
		<div class="parametro titulo "><c:out
			value="${siniestro.nombresBeneficiario}" /> <c:out
			value="${siniestro.apellidosBeneficiario}" /></div>
			<div class="etiqueta titulo cgp"><bean:message
			key="general.cedula" bundle="etiquetas" /></div>
		<div class="parametro titulo "><c:out
			value="${siniestro.cedulaBeneficiario}" />
		</div>
		
		<div class="etiqueta titulo cgp"><bean:message
			key="general.tipoempleado" bundle="etiquetas" /></div>
		<div class="parametro titulo "><c:out
			value="${siniestro.tipoEmpleado.descripcion}" /></div>
		<div class="etiqueta titulo cgp"><bean:message
			key="general.parentesco" bundle="etiquetas" /></div>
		<div class="parametro titulo "><c:out
			value="${siniestro.parentesco}" />
		</div>
		<div class="etiqueta titulo cgp"><bean:message
			key="general.tipoproveedor" bundle="etiquetas" /></div>
		<div class="parametro titulo "><c:out 
			 value="${siniestro.proveedor.tipoProveedor.descripcion}" /></div>
		<div class="etiqueta titulo cgp"><bean:message
			key="general.proveedor" bundle="etiquetas" /></div>
		<div class="parametro titulo "><c:out 
			 value="${siniestro.proveedor.descripcion}" /></div>

		<div class="etiqueta titulo cgp"><bean:message
			key="general.fecha.ocurrencia" bundle="etiquetas" /></div>
		<div class="parametro titulo "><fmt:formatDate
			pattern="dd/MM/yyyy" value="${siniestro.fechaOcurrencia}" /></div>
		<div class="etiqueta titulo cgp"><bean:message
			key="general.fechaIngreso" bundle="etiquetas" /></div>
		<div class="parametro titulo "><fmt:formatDate
			pattern="dd/MM/yyyy" value="${siniestro.fechaIngreso}" /></div>
		<div class="etiqueta titulo cgp"><bean:message
			key="general.fechaEgreso" bundle="etiquetas" /></div>
		<div class="parametro titulo "><fmt:formatDate
			pattern="dd/MM/yyyy" value="${siniestro.fechaEgreso}" /></div>

		<div class="etiqueta titulo cgp"><bean:message
			key="general.fechaNotificacion" bundle="etiquetas" /></div>
		<div class="parametro titulo "><fmt:formatDate
			pattern="dd/MM/yyyy" value="${siniestro.fechaNotificacion}" /></div>
		
		<c:if test="${siniestro.fechaLiquidacion!=null}">
			<div class="etiqueta titulo cgp"><bean:message
				key="general.fechaLiquidacion" bundle="etiquetas" /></div>
			<div class="parametro titulo "><fmt:formatDate
				pattern="dd/MM/yyyy" value="${siniestro.fechaLiquidacion}" /></div>
		</c:if>
		<div class="etiqueta titulo cgp"><bean:message
			key="general.citaPostOperatorio" bundle="etiquetas" /></div>
		<div class="parametro titulo "><c:if
			test="${siniestro.citaPostOperatorio!=false}">
			Requiere de Cita PostOperatorio</c:if><c:if
			test="${siniestro.citaPostOperatorio==false}">
			No Requiere de Cita PostOperatorio</c:if></div>

		<div class="etiqueta titulo cgp"><bean:message
			key="general.tiposiniestro" bundle="etiquetas" /></div>
		<div class="parametro titulo "><c:out
			value="${siniestro.tipoSiniestro.descripcion}" /></div>
			
			
			
		<div class="etiqueta titulo cgp"><bean:message
			key="general.estatus" bundle="etiquetas" /></div>
		<div class="parametro titulo "><c:out
			value="${siniestro.estatus.descripcion}" />  <c:if test="${tipoImpresion!=null}"> <img src="<%=request.getContextPath()%>/images/printer.png"
					onclick="javascript:openWinScroll2('<%=request.getContextPath()%>/security/comunes/generareporte.do?id=<c:out value="${siniestro.id}" />&id_reporte=<c:out  value="${tipoImpresion}"/>&anio=<c:out	value="${siniestro.anioSiniestro}" />','CartaAval',800,600);"></c:if></div>
		
		
		<c:if test="${siniestro.estatus.id==34}">
		<div class="etiqueta titulo cgp"><bean:message
			key="general.justificacion" bundle="etiquetas" /></div>
		<div class="parametro titulo "><c:out
			value="${motEst.descripcion}" /></div>
			</c:if>
			<c:if test="${siniestro.estatus.id==35}">
		<div class="etiqueta titulo cgp"><bean:message
			key="general.justificacion" bundle="etiquetas" /></div>
		<div class="parametro titulo "><c:out
			value="${motEst.descripcion}" /></div>
			</c:if>
			<c:if test="${siniestro.estatus.id==2}">
		<div class="etiqueta titulo cgp"><bean:message
			key="general.justificacion" bundle="etiquetas" /></div>
		<div class="parametro titulo "><c:out
			value="${motEst.descripcion}" /></div>
			</c:if>
			<c:if test="${siniestro.estatus.id==38}">
		<div class="etiqueta titulo cgp"><bean:message
			key="general.justificacion" bundle="etiquetas" /></div>
		<div class="parametro titulo "><c:out
			value="${motEst.descripcion}" /></div>
			</c:if>
			
			
			
		<div class="etiqueta titulo cgp"><bean:message
			key="general.tipoenfermedad" bundle="etiquetas" /></div>
		<div class="parametro titulo "><c:out
			value="${siniestro.tipoEnfermedad.descripcion}" /></div>
		<div class="etiqueta titulo cgp"><bean:message
			key="general.listTipoTramite" bundle="etiquetas" /></div>
		<div class="parametro titulo "><c:out
			value="${siniestro.tipoTramite.descripcion}" /></div>



      <c:if test="${siniestro.estatus.id!=4}">

		<div class="etiqueta titulo cgp">Tipo Empleado</div>
				<div class="parametro titulo cgp">
				
				<select name="tipoempleado" id="tipoempleado" class="cgp">
				    <option value="-1" selected="selected" class="seleccione">[SELECCIONAR]</option>
					
					
						 <%
							for (int k=0;k!=TipoE.size();k++){
								tipE = (TipoEmpleado) TipoE.get(k);
			      		 %>		
								
								<option value=<%=tipE.getId()%>><%=tipE.getDescripcion()%></option>
						<%}%>	
					
					
					
		        </select>
		        
		        </div>	
		        
		        


		<div class="etiqueta titulo cgp"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
				<div class="parametro titulo cgp">
				</div>	
					<div class="etiqueta titulo cgp"></div>
				<div class="parametro titulo cgp">
				</div>
					<div class="etiqueta titulo cgp"></div>
				<div class="parametro titulo cgp">
				</div>
					<div class="etiqueta titulo cgp"></div>
				<div class="parametro titulo cgp">
				</div>
					<div class="etiqueta titulo cgp"></div>
				<div class="parametro titulo cgp">
				</div>
				


				<div  align="center" >
				   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</div>
				
				<br>
				<div  align="center" >
				    <input class="boton" value="Guardar Cambio de Tipo Empleado" type="button"  onclick="ir(1);" />
				</div>
		
		</c:if>	
		
			
				
				
		
		<input id="accionPago" type="hidden" name="accionPago"></input>
		<input id="cod" type="hidden" name="cod" value="<%=request.getAttribute("cod_sin")%>"></input>
		<input id="id_s" type="hidden" name="id_s" value="<%=request.getAttribute("id_sin")%>"></input>
		<input id="id_auto" type="hidden" name="id_auto"></input>
		
		
	</tiles:put>
</tiles:insert>
<script language="JavaScript">
	function openWinScroll2(popup_url, name, width, height) {

		var size = ',width=' + width + ',height=' + height;
		var posicion = ",left=" + Math.round(screen.availWidth / 4) + ",top="
				+ Math.round(screen.availHeight / 4);
		var popUp = window
				.open(
						popup_url,
						name,
						'menubar=yes,location=no,toolbar=no,status=no,scrollbars=no,titlebar=no,directories=no,resizable=no'
								+ size + posicion);
		popUp.opener = self;

	}

	function ir(acc){
		if(confirm('¿Esta seguro que desea realizar el cambio de Tipo Empleado Para este Siniestro?')){ 
		document.getElementById('accionPago').value=acc;
		document.forms[0].action='<%=request.getContextPath()%>/security/modificaTipoEmpleado/buscarSiniestro.do';
        document.forms[0].submit();
		}
	}

	function elimina(acc,id){
		if(confirm('¿Esta seguro que desea realizar esta operación?')){
		document.getElementById('accionPago').value=acc;
		document.getElementById('id_auto').value=id;
		document.forms[0].action='<%=request.getContextPath()%>/security/extension/nueva.do';
        document.forms[0].submit();
		}
	}






	
</script>

