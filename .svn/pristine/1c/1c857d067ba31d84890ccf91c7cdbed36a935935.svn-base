<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<tiles:insert page="/jsp/plantilla/plantilla.jsp">

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
<div class="etiqueta titulo cgp"><bean:message
			key="general.usuarioCreador" bundle="etiquetas" /></div>
		<div class="parametro titulo "><c:out
			value="${siniestro.idUsuario}" /></div>

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
			key="general.parentesco" bundle="etiquetas" /></div>
		<div class="parametro titulo "><c:out
			value="${siniestro.parentesco}" />
		</div>
		<div class="etiqueta titulo cgp"><bean:message
			key="general.tipoEmpleado" bundle="etiquetas" /></div>
		<div class="parametro titulo "><c:out
			value="${siniestro.tipoEmpleado.descripcion}" />
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
		<c:if test="${siniestro.fechaIngreso!=null}">
		<div class="etiqueta titulo cgp"><bean:message
			key="general.fechaIngreso" bundle="etiquetas" /></div>
		<div class="parametro titulo "><fmt:formatDate
			pattern="dd/MM/yyyy" value="${siniestro.fechaIngreso}" /></div>
		</c:if>
		<c:if test="${siniestro.fechaEgreso!=null}">
		<div class="etiqueta titulo cgp"><bean:message
			key="general.fechaEgreso" bundle="etiquetas" /></div>
		<div class="parametro titulo "><fmt:formatDate
			pattern="dd/MM/yyyy" value="${siniestro.fechaEgreso}" /></div>
		</c:if>
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
		<c:if test="${siniestro.estatus.id==4}">
			<div class="etiqueta titulo cgp"><bean:message
				key="general.usuarioLiquidacion" bundle="etiquetas" /></div>
			<div class="parametro titulo "><c:out
				value="${motEst.idUsuario}" /></div>
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
		<c:if test="${siniestro.estatus.id!=34}">
			<div class="parametro titulo "><c:out
				value="${siniestro.estatus.descripcion}" /> <c:if
				test="${tipoImpresion!=null}">
				<img src="<%=request.getContextPath()%>/images/printer.png"
					onclick="javascript:openWinScroll2('<%=request.getContextPath()%>/security/comunes/generareporte.do?id=<c:out value="${siniestro.id}" />&id_reporte=<c:out  value="${tipoImpresion}"/>&anio=<c:out	value="${siniestro.anioSiniestro}" />','Reporte',800,600);">
			</c:if></div>
		</c:if>
		
		<c:if test="${siniestro.estatus.id==34}">
			<div class="parametro titulo "><c:out
				value="${siniestro.estatus.descripcion}" /></div>
		</c:if>
		<c:if test="${(siniestro.estatus.id ==31) || (siniestro.estatus.id ==32) || (siniestro.estatus.id ==33) || (siniestro.estatus.id ==34)}">
			<div class="etiqueta titulo cgp">Motivo de Anulación</div>
			<div class="parametro titulo "><c:out
				value="${siniestro.motivo}" /></div>
		</c:if>		
		
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

		<div class="etiqueta titulo cgp"><bean:message
			key="general.tratamiento" bundle="etiquetas" /></div>
		<div
			title="<c:out  value="${siniestro.patologiaOrganoTratamiento.descripcion}"/>"
			class="parametro titulo "><c:out
			value="${siniestro.patologiaOrganoTratamiento.descripcion}" /></div>

		<div class="etiqueta titulo cgp"><bean:message
			key="general.observacion" bundle="etiquetas" /></div>
		<div class="parametro titulo "><c:out
			value="${siniestro.observacion}" /></div>
			

		<table class="tabla" width="700" cellpadding="2" cellspacing="1"
			border="0">
			
				

			
			<tr class="item" bgcolor="white">
				<td>
				<div class="etiqueta titulo cgp"><bean:message
					key="general.adjuntos" bundle="etiquetas" /></div>
				<div class="parametro titulo cgp"><a class="red" href="#"
					onclick="javascript:openWinScroll2('<%=request.getContextPath()%>/security/upload/uploadFile.do?idSiniestro=<c:out value='${siniestro.id}'/>&anio=<c:out	value="${siniestro.anioSiniestro}" />','name',500,500);">Ver
				Anexos</a>
				</div>

				</td>
			</tr>



			<tr class="item" bgcolor="white">
				<td>
				<div class="etiqueta titulo cgp"><bean:message
					key="general.notatecnicas" bundle="etiquetas" /></div>
				<div class="parametro titulo cgp"><a class="red" href="#"
					onclick="javascript:openWinScroll2('<%=request.getContextPath()%>/security/notatecnica/saveNotaTecnica.do?idSiniestro=<c:out value='${siniestro.id}'/>&anioSiniestro=<c:out	value="${siniestro.anioSiniestro}" />','name',500,500);">Ver
				Nota Técnica</a></div>
				</td>
			</tr>


			<tr class="item" bgcolor="white">
				<td>
				<div class="etiqueta titulo cgp">Nota Médica</div>
				<div class="parametro titulo cgp"><a class="red" href="#"
					onclick="javascript:openWinScroll2('<%=request.getContextPath()%>/security/notaMedica/verNotaMedica.do?idSiniestro=<c:out value='${siniestro.id}'/>&anio=<c:out	value="${siniestro.anioSiniestro}" />','name',500,500);">Ver
				Nota Médica</a>
				</div>

				<c:if test="${siniestro.estatus.id==9}">
				<c:if test="${siniestro.tipoTramite.id==1||siniestro.tipoTramite.id==2}">
						<tr class="item" bgcolor="white">
					<td>

					<div class="etiqueta titulo cgp"><bean:message
						key="general.notaCobertura" bundle="etiquetas" /></div>
					<img src="<%=request.getContextPath()%>/images/printer.png"
						onclick="javascript:openWinScroll2('<%=request.getContextPath()%>/security/comunes/generareporte.do?id=<c:out
								value="${siniestro.id}" />&anio=<c:out
								value="${siniestro.anioSiniestro}" />&id_reporte=13','NotaCobertura',800,600);">
					</td>
				</tr>
				
			</c:if></c:if>
				<c:if test="${siniestro.estatus.id==4}">
                <c:if test="${siniestro.tipoTramite.id==3}">
						<tr class="item" bgcolor="white">
					<td>

					<div class="etiqueta titulo cgp"><bean:message
						key="general.notaCobertura" bundle="etiquetas" /></div>
					<img src="<%=request.getContextPath()%>/images/printer.png"
						onclick="javascript:openWinScroll2('<%=request.getContextPath()%>/security/comunes/generareporte.do?id=<c:out
								value="${siniestro.id}" />&anio=<c:out
								value="${siniestro.anioSiniestro}" />&id_reporte=17','NotaCobertura',800,600);">
					</td>
				</tr>
                </c:if>
				<c:if test="${siniestro.tipoTramite.id!=3}">
						
						<tr class="item" bgcolor="white">
					<td>

					<div class="etiqueta titulo cgp"><bean:message
						key="general.notaCobertura" bundle="etiquetas" /></div>
					<img src="<%=request.getContextPath()%>/images/printer.png"
						onclick="javascript:openWinScroll2('<%=request.getContextPath()%>/security/comunes/generareporte.do?id=<c:out
								value="${siniestro.id}" />&anio=<c:out
								value="${siniestro.anioSiniestro}" />&id_reporte=10','NotaCobertura',800,600);">
					</td>
				</tr>
                </c:if>
			</c:if>








				</td>
			</tr>

		
			<tr class="item" bgcolor="white">
				<td>
				<div class="etiqueta titulo cgp"><bean:message
					key="general.notaActivo" bundle="etiquetas" /></div>
				<img src="<%=request.getContextPath()%>/images/printer.png"
					onclick="javascript:openWinScroll2('<%=request.getContextPath()%>/security/comunes/generareporte.do?id=<c:out
								value="${siniestro.id}" />&anio=<c:out
								value="${siniestro.anioSiniestro}" />&id_reporte=11','NotaTecnicaUsuarioActivo',800,600);">
				</td>
			</tr>
			<tr class="item" bgcolor="white">
				<td>
				<div class="etiqueta titulo cgp"><bean:message
					key="general.notaSiniestro" bundle="etiquetas" /></div>
				<img src="<%=request.getContextPath()%>/images/printer.png"
					onclick="javascript:openWinScroll2('<%=request.getContextPath()%>/security/comunes/generareporte.do?id=<c:out
								value="${siniestro.id}" />&anio=<c:out
								value="${siniestro.anioSiniestro}" />&id_reporte=12','NotaTecnicaSiniestro',800,600);">

				</td>
			</tr>
			<c:if test="${siniestro.tipoTramite.id==6&&siniestro.estatus.id==9}">
				<tr class="item" bgcolor="white">
					<td>
					<div class="etiqueta titulo cgp"><bean:message
						key="general.ordenMedicinas" bundle="etiquetas" /></div>
					<img src="<%=request.getContextPath()%>/images/printer.png"
						onclick="javascript:openWinScroll2('<%=request.getContextPath()%>/security/comunes/generareporte.do?id=<c:out
								value="${siniestro.id}" />&anio=<c:out
								value="${siniestro.anioSiniestro}" />&descripcionMedicinas=<c:out
								value="${siniestro.tipoEmpleado.descripcion}" />&id_reporte=1','OrdenDeMedicinas',800,600);">
					</td>
				</tr>
			</c:if>
			<c:if test="${siniestro.estatus.id==4}">
			<tr class="item" bgcolor="white">
					<td>
					<div class="etiqueta titulo cgp">Liquidación Siniestro</div>
					<img src="<%=request.getContextPath()%>/images/printer.png"
						onclick="javascript:openWinScroll2('<%=request.getContextPath()%>/security/comunes/generareporte.do?id=<c:out
								value="${siniestro.id}" />&anio=<c:out
								value="${siniestro.anioSiniestro}" />&fechaInicio=<c:out
								value="${fechaFactura}" />&id_reporte=15','Liquidación',800,600);">
					</td>
			</tr>
			</c:if>
			
			<div class="etiqueta titulo cgp"><bean:message
			key="general.montoPresupuestado" bundle="etiquetas" /></div>
		<div class="parametro titulo "><fmt:formatNumber
						value="${siniestro.montoPresupuestado}" pattern='###0.00' /> Bs.</div>
                 <c:if test="${siniestro.estatus.id!=4}"> 
              <div class="etiqueta titulo cgp"><bean:message
			key="general.negociado.solo" bundle="etiquetas" /></div>
            </c:if>   
            <c:if test="${siniestro.estatus.id==4}"> 
              <div class="etiqueta titulo cgp"><bean:message
			key="general.monto.facturado" bundle="etiquetas" /></div>
            </c:if> 
		<div class="parametro titulo "><fmt:formatNumber
						value="${siniestro.montoNegociado}" pattern='###0.00' /> Bs.</div>  
                        
			
		
			<div class="etiqueta titulo cgp"><bean:message
			key="general.amparado" bundle="etiquetas" /></div>
		<div class="parametro titulo "><fmt:formatNumber
						value="${siniestro.montoAmparado}" pattern='###0.00' /> Bs.</div>
		
		<c:if test="${siniestro.montoMaximoAutorizado!=0}">
		<div class="etiqueta titulo cgp"><bean:message
			key="general.monto.autorizado" bundle="etiquetas" /></div>
		<div class="parametro titulo "><fmt:formatNumber
						value="${siniestro.montoMaximoAutorizado}" pattern='###0.00' /> Bs.</div></c:if>
			<div class="etiqueta titulo cgp"><bean:message
			key="general.noAmparado" bundle="etiquetas" /></div>
		<div class="parametro titulo "><fmt:formatNumber
						value="${siniestro.montoNoAmparado}" pattern='###0.00' /> Bs.</div>
			<%--<c:if test="${siniestro.tipoTramite.id==2&&siniestro.estatus.id==5}">
				<tr class="item" bgcolor="white">
					<td>
					<div class="etiqueta titulo cgp"><bean:message
						key="general.cartaCompromiso" bundle="etiquetas" /></div>
					<img src="<%=request.getContextPath()%>/images/printer.png"
						onclick="javascript:openWinScroll2('<%=request.getContextPath()%>/security/comunes/generareporte.do?id=<c:out value="${siniestro.id}" />&id_reporte=9&anio=<c:out	value="${siniestro.anioSiniestro}" />','CartaAval',800,600);">
					</td>
				</tr>
			</c:if>
--%>
		</table>
<c:if test="${factura.numeroFactura!=null}">
		<table class="tabla" width="600" cellpadding="2" cellspacing="1"
			border="0">
			<tr class="tituloCabecera">
				<td><bean:message key="general.tipoGasto" bundle="etiquetas" /></td>
				<td><bean:message key="general.montoPresup" bundle="etiquetas" /></td>
				<td><bean:message key="general.montoNego" bundle="etiquetas" /></td>
				<td><bean:message key="general.montoAmp" bundle="etiquetas" /></td>
				<td><bean:message key="general.montoNoAmp" bundle="etiquetas" /></td>


			</tr>
			<c:forEach items="${listDetalle}" var="detalleFacturas">
				<tr class="item" bgcolor="white">

					<td align="right"> <c:out value="${detalleFacturas.tipoGasto.descripcion}" /></td>
					<td align="right"><fmt:formatNumber
						value="${detalleFacturas.montoPresupuestado}" pattern='###0.00' /></td>
					<td align="right"><fmt:formatNumber
						value="${detalleFacturas.montoNegociado}" pattern='###0.00' /></td>
					<td align="right"><fmt:formatNumber value="${detalleFacturas.montoAmparado}"
						pattern='###0.00' /></td>
					<td align="right"><fmt:formatNumber
						value="${detalleFacturas.montoNoAmparado}" pattern='###0.00' /></td>





				</tr>
			</c:forEach>
			<tr class="item">

				<td>
				<div align="right" class="parametro titulo ">Totales
				</td>
				<td align="right"><fmt:formatNumber value="${siniestro.montoPresupuestado}"
					pattern='###0.00' /> Bs</td>
				<td align="right"><fmt:formatNumber value="${siniestro.montoNegociado}"
					pattern='###0.00' /> Bs</td>
				<td align="right"><fmt:formatNumber value="${siniestro.montoAmparado}"
					pattern='###0.00' /> Bs</td>
				<td align="right"><fmt:formatNumber value="${siniestro.montoNoAmparado}"
					pattern='###0.00' /> Bs</td>





			</tr>

		</table>
		
			<table class="tabla" width="600" cellpadding="2" cellspacing="1"
				border="0">
				<tr class="tituloCabecera">
					<div class="textorange" style="padding-bottom: 5px">Detalles
					de Liquidación</div>
					<td><bean:message key="general.numeroFactura"
						bundle="etiquetas" /></td>
					<td><bean:message key="general.controlFactura"
						bundle="etiquetas" /></td>
					<td><bean:message key="general.fechaRecepcionFactura"
						bundle="etiquetas" /></td>
					<td><bean:message key="general.fechaFactura"
						bundle="etiquetas" /></td>
					<td><bean:message key="general.montoFacturado"
						bundle="etiquetas" /></td>

				</tr>
				<c:forEach items="${listFactura}" var="facturas">
					<tr class="item" bgcolor="white">
						<td><c:out value="${facturas.numeroFactura}" /></td>
						<td><c:out value="${facturas.controlFactura}" /></td>
						<td><fmt:formatDate pattern="dd/MM/yyyy"
							value="${facturas.fechaRecepcionFactura}" /></td>
						<td><fmt:formatDate pattern="dd/MM/yyyy"
							value="${facturas.fechaFactura}" /></td>
						<td><fmt:formatNumber value="${facturas.montoFactura}"
							pattern='###0.00' /></td>

					</tr>
				</c:forEach>
			</table>

		</c:if>
		
		
		
		
		
		
		
		<c:if test="${listFactura!=null}">
		<div style="overflow: visible; clear: both;">
		<table class="tabla" width="500" cellpadding="1" cellspacing="1" border="0" bgcolor="yellow">
			<tr class="tituloCabecera">
				<td>Nro. Factura</td>
				<td>Nro. Control</td>
				<td>Fecha Factura</td>
				<td>Fecha Recepción</td>
				<td>Monto</td>
				<td>Pre-Orden</td>
				<td>Estatus Pre-Orden</td>
				<td>Liquidación Factura </td>

			</tr>
			<c:forEach items="${listFactura}" var="f">
				<tr class="item" bgcolor="white">
					<td><c:out value="${f.preOrden}" /><c:out value="${f.numeroFactura}" /></td>
					<td><c:out value="${f.controlFactura}" /></td>
					<td><fmt:formatDate pattern="dd/MM/yyyy" value="${f.fechaFactura}" /></td>
					<td><fmt:formatDate pattern="dd/MM/yyyy" value="${f.fechaRecepcionFactura}" /></td>
					<td><fmt:formatNumber value="${f.montoFactura}" /></td>
					<td><c:out value="${f.preOrden}" default="No posee"/></td>
					<td><c:out value="${f.estatusPreOrden}" default="No posee"/></td>
					<td><img src="<%=request.getContextPath()%>/images/printer.png"
						onclick="javascript:openWinScroll2('<%=request.getContextPath()%>/security/comunes/generareporte.do?id=<c:out
								value="${siniestro.id}" />&anio=<c:out
								value="${siniestro.anioSiniestro}" />&idFactura=<c:out
								value="${f.id}" />&fechaInicio=<c:out
								value="${fechaFactura}" />&id_reporte=22','LiquidaciónPorFactura',800,600);"></td>
					
				</tr>
			</c:forEach>
		</table>
		</div>
		</c:if>
		
		
		
		
		
		
		
		
		
		
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
</script>

