<%@page contentType="text/html; charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html-el.tld" prefix="html"%>
<%@ page import="java.util.Date"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<input type="hidden" name="accion" />
<input type="hidden" name="requestCedBeneficiario" value="<c:out value='${titular.beneficiario.cedula}'/>"/>
<input type="hidden" name="requestCedTitular" value="<c:out value='${titular.cedula}'/>"/>
<input type="hidden" name="idtt" value="<c:out value='${idtt}'/>"/>

<c:if test="${entorno.anioBusqueda}">
	<div class="etiqueta titulo cgp"><bean:message
		key="general.anioBusqueda" bundle="etiquetas" /></div>
	<div class="parametro titulo cgp"><html:select styleClass="cgp"
		property="anioBusqueda">

		<html:options collection="listAnioBusqueda" property="id"
			labelProperty="descripcion" />
	</html:select></div>
</c:if>
<c:if test="${entorno.listTipoTramite}">
	<div class="etiqueta titulo cgp"><bean:message
		key="general.listTipoTramite" bundle="etiquetas" /></div>
	<div class="parametro titulo cgp"><html:select styleClass="cgp"
		property="listTipoTramite">
		<html:option styleClass="seleccione" value="-1">[SELECCIONAR]</html:option>
		<html:options collection="listTipoTramite" property="id"
			labelProperty="descripcion" />
	</html:select></div>
</c:if>

<c:if test="${codigoPreOrden!=null}">
	<input type="hidden" name="codigoPreOrden"
		value="<c:out value="${codigoPreOrden}" />" />
</c:if>

<c:if test="${entorno.criterioBusqueda}">
	<div class="etiqueta titulo cgp"><bean:message
		key="general.cedula" bundle="etiquetas" /></div>
	<div class="parametro titulo "><input class="cgp" type="text"
		name="cedula" name="cedula" value="<c:out value='${cedula}'/>" /></div>
	<div class="etiqueta titulo cgp"><bean:message
		key="general.nombres" bundle="etiquetas" /> o <bean:message
		key="general.apellidos" bundle="etiquetas" /></div>
	<div class="parametro titulo "><input class="cgp" type="text"
		name="nombres" name="nombres" value="<c:out value='${nombres}'/>" /></div>
	<div class="etiqueta titulo cgp"><bean:message
		key="general.codigo" bundle="etiquetas" /></div>
	<div class="parametro titulo "><input class="cgp" type="text"
		name="codigo" name="codigo" value="<c:out value='${codigo}'/>" /></div>
</c:if>
<c:if test="${entorno.datosPersonales}">
	<!--div class="textorange" style="padding-bottom: 5px">Datos del Titular</div>
	<table class="tabla" width="600" cellpadding="2" cellspacing="1" border="0">
		<tr class="tituloCabecera">
			
			<td><bean:message key="general.cedula" bundle="etiquetas" /></td>
			<td><bean:message key="general.nombres" bundle="etiquetas" /></td>
			<td><bean:message key="general.apellidos" bundle="etiquetas" /></td>
			<td><bean:message key="general.fechanac" bundle="etiquetas" /></td>
			<td><bean:message key="general.edocivil" bundle="etiquetas" /></td>
			<td><bean:message key="general.cargo" bundle="etiquetas" /></td>
			<td><bean:message key="general.telefono" bundle="etiquetas" /></td>
			<td><bean:message key="general.estado" bundle="etiquetas" /></td>
		</tr>
		< tr class="item" bgcolor="white" onclick="javaScript:submit2('<c:out value="${titular.cedula}" />', '1')">
			<td><c:out value="${titular.cedula}" /></td>
			<td><c:out value="${titular.nombres}" /></td>
			<td><c:out value="${titular.apellidos}" /></td>
			<td><fmt:formatDate pattern="dd/MM/yyyy" value="${titular.fechaNacimiento}" /></td>
			<td><c:out value="${titular.estadoCivil}" /></td>
			<td><c:out value="${titular.cargo}" /></td>
			<td><c:out value="${titular.telefono}" /></td>
			<td><c:out value="${titular.estado}" /></td>
		</tr >
	</table-->

	<table class="tabla" width="600" cellpadding="2" cellspacing="1"
		border="0">
		<tr class="tituloCabecera">
			<div class="textorange" style="padding-bottom: 5px">Datos del
			Beneficiario</div>
			<td><bean:message key="general.cedula" bundle="etiquetas" /></td>
			<td><bean:message key="general.nombres" bundle="etiquetas" /></td>
			<td><bean:message key="general.apellidos" bundle="etiquetas" /></td>
			<td><bean:message key="general.fechanac" bundle="etiquetas" /></td>
			<td><bean:message key="general.edocivil" bundle="etiquetas" /></td>
			<td><bean:message key="general.sexo" bundle="etiquetas" /></td>
			<td><bean:message key="general.edad" bundle="etiquetas" /></td>
			<td><bean:message key="general.parentesco" bundle="etiquetas" /></td>
		</tr>
		<tr class="item" bgcolor="white"
			onclick="javaScript:submit2('<c:out value="${titular.beneficiario.cedula}" />', '1')">
			<td><fmt:formatNumber value="${titular.beneficiario.cedula}" groupingUsed="true" /> </td>
			<td><c:out value="${titular.beneficiario.nombres}" /></td>
			<td><c:out value="${titular.beneficiario.apellidos}" /></td>
			<td><fmt:formatDate pattern="dd/MM/yyyy"
				value="${titular.beneficiario.fechaNacimiento}" /></td>
			<td><c:out value="${titular.beneficiario.estadoCivil}" /></td>
			<td><c:out value="${titular.beneficiario.sexo}" /></td>
			<td><c:out value="${titular.beneficiario.edad}" /></td>
			<td><c:out value="${titular.beneficiario.parentesco}" /></td>
		</tr>
	</table>
	<div class="sp5"></div>
</c:if>
<c:if test="${entorno.tituloItems}">
	<div class="textorange" style="padding-bottom: 5px">Detalles del
	Siniestro</div>
</c:if>



<c:if test="${entorno.detalleSiniestroPadre}">

	<c:if test="${siniestroPadre!=null}">
	<input type="hidden" name="idSiniPadre" value="<c:out value='${siniestroPadre.id}'/>"/>
	<input type="hidden" name="anioSiniPadre" value="<c:out value='${siniestroPadre.anioSiniestro}'/>"/>
		<table class="tabla" width="500" cellpadding="0" cellspacing="1"
			border="0">
			<tr class="tituloCabecera">
				<div class="textorange" style="padding-bottom: 5px">Datos del
				Siniestro Padre</div>

				<td>Número de Siniestro</td>
				<td>Fecha Notif.</td>
				<td>Servicio</td>
				<td>Tipo Siniestro</td>
				<td>Monto Amparado (Bs)</td>
				<td>Estatus</td>
				<td>Causa de Ingreso</td>
			</tr>
			<tr class="item" bgcolor="white">

				<td><c:out value="${siniestroPadre.aniomesCodigo}" />-<c:out
					value="${siniestroPadre.codigo}" />-<c:out
					value="${siniestroPadre.subCodigo}" /></td>

				<td><fmt:formatDate pattern="dd/MM/yyyy"
					value="${siniestroPadre.fechaNotificacion}" /></td>
				<td><c:out value="${siniestroPadre.tipoTramite.descripcion}" /></td>
				<td><c:out value="${siniestroPadre.tipoSiniestro.descripcion}" /></td>
				<td><c:out value="${siniestroPadre.montoAmparado}" />(Bs)</td>

				<td><c:out value="${siniestroPadre.estatus.descripcion}" /></td>
				<td><c:out
					value="${siniestroPadre.patologiaOrganoTratamiento.descripcion}" /></td>


			</tr>




		</table>

	</c:if>

</c:if>
<c:if test="${entorno.codigoetiqueta}">
	<div class="etiqueta titulo cgp"><bean:message
		key="general.codigo" bundle="etiquetas" /></div>
	<div class="parametro titulo "><html:text onfocus="blur();"
		styleClass="cgp" property="codigo"></html:text></div>
</c:if>

<c:if test="${entorno.detalleSiniestroEgreso}">

	<div class="etiqueta titulo cgp"><bean:message
		key="general.nombres" bundle="etiquetas" /> y <bean:message
		key="general.apellidos" bundle="etiquetas" /></div>
	<div class="parametro titulo "><c:out
		value="${siniestro.nombresBeneficiario}" /> <c:out
		value="${siniestro.apellidosBeneficiario}" /></div>
	<div class="etiqueta titulo cgp"><bean:message
		key="general.subCodigo" bundle="etiquetas" /></div>
	<div class="nblack"><c:out
		value="${siniestro.aniomesCodigo}-${siniestro.codigo}-${siniestro.subCodigo}" /></div>
	<div class="etiqueta titulo cgp"><bean:message
		key="general.cobertura" bundle="etiquetas" /></div>
	<div class="parametro titulo "><c:out
		value="${siniestro.cobertura.tipoCobertura.descripcion}" /></div>
        
	<div class="etiqueta titulo cgp"><bean:message
		key="general.tipoProveedor" bundle="etiquetas" /></div>
	<div class="parametro titulo "><c:out
		value="${siniestro.proveedor.tipoProveedor.descripcion}" /></div>
	<div class="etiqueta titulo cgp"><bean:message
		key="general.proveedor" bundle="etiquetas" /></div>
	<div class="parametro titulo "><c:out
		value="${siniestro.proveedor.descripcion}" /></div>
	<div class="etiqueta titulo cgp"><bean:message
		key="general.fechaNotificacion" bundle="etiquetas" /></div>
	<div class="parametro titulo "><fmt:formatDate
		pattern="dd/MM/yyyy" value="${siniestro.fechaNotificacion}" /></div>
	<div class="etiqueta titulo cgp"><bean:message
		key="general.estatus" bundle="etiquetas" /></div>
	<div class="parametro titulo "><c:out
		value="${siniestro.estatus.descripcion}" /></div>
	<c:if test="${siniestro.fechaOcurrencia!=null}">
		<div class="etiqueta titulo cgp"><bean:message
			key="general.fecha.ocurrencia" bundle="etiquetas" /></div>
		<div class="parametro titulo "><fmt:formatDate
			pattern="dd/MM/yyyy" value="${siniestro.fechaOcurrencia}" /></div>
	</c:if>
	<c:if test="${siniestro.fechaEgreso!=null}">
		<div class="etiqueta titulo cgp"><bean:message
			key="general.fechaEgreso" bundle="etiquetas" /></div>
		<div class="parametro titulo "><fmt:formatDate
			pattern="dd/MM/yyyy" value="${siniestro.fechaEgreso}" /></div>
	</c:if>
	<c:if test="${siniestro.fechaLiquidacion!=null}">
		<div class="etiqueta titulo cgp"><bean:message
			key="general.fechaLiquidacion" bundle="etiquetas" /></div>
		<div class="parametro titulo "><fmt:formatDate
			pattern="dd/MM/yyyy" value="${siniestro.fechaLiquidacion}" /></div>
	</c:if>
	<div class="etiqueta titulo cgp"><bean:message
		key="general.tiposiniestro" bundle="etiquetas" /></div>
	<div class="parametro titulo "><c:out
		value="${siniestro.tipoSiniestro.descripcion}" /></div>
	<div class="etiqueta titulo cgp"><bean:message
		key="general.tipoTratamiento" bundle="etiquetas" /></div>
	<div class="parametro titulo "><c:out
		value="${siniestro.tipoTratamiento.descripcionTratamiento}" /></div>
	<div class="etiqueta titulo cgp"><bean:message
		key="general.tratamiento" bundle="etiquetas" /></div>
	<div
		title="<c:out  value="${siniestro.patologiaOrganoTratamiento.descripcion}"/>
    "
		class="parametro titulo "><c:out
		value="${siniestro.patologiaOrganoTratamiento.descripcion}" /></div>
	<div class="etiqueta titulo cgp"><bean:message
		key="general.citaPreOperatorio" bundle="etiquetas" /></div>
	<div class="parametro titulo "><c:if
		test="${siniestro.citaPreOperatorio==true}"> SI </c:if> <c:if
		test="${siniestro.citaPreOperatorio!=true}"> NO </c:if></div>

</c:if>

<c:if test="${entorno.detalleCobertura}">
	<table class="tabla" cellpadding="2" cellspacing="1" border="0">
		<tr class="tituloCabecera">
			<td width="25px"><bean:message key="general.descripcion"
				bundle="etiquetas" /></td>
			<td width="25px"><bean:message key="general.montoCobertura"
				bundle="etiquetas" /></td>
			<td width="25px"><bean:message key="general.coberturaAgotada"
				bundle="etiquetas" /></td>
			<td width="25px"><bean:message key="general.coberturadisponible"
				bundle="etiquetas" /></td>
		</tr>
		<c:forEach items="${cobertura}" var="cobert">
			<tr class="item" bgcolor="white">
				<td width="25px"><c:out value="${cobert.tipoCobertura}" /></td>
				<td width="25px"><c:out value="${cobert.montoCobertura}" /></td>
				<td width="25px"><c:out value="${cobert.montoAgotado}" /></td>
				<td width="25px"><c:out value="${cobert.montoDisponible}" /></td>
			</tr>
		</c:forEach>

	</table>
</c:if>

<c:if test="${entorno.id}">
	<html:hidden property="id" />
</c:if>

<c:if test="${entorno.anioSiniestro}">
	<html:hidden name="globalActionForm" property="anioSiniestro" />
</c:if>
<c:if test="${entorno.numeroFactura}">
	<div class="etiqueta titulo cgp"><bean:message
		key="general.numeroFactura" bundle="etiquetas" /></div>
	<div class="parametro titulo "><html:text styleClass="cgp"
		property="numeroFactura"></html:text></div>
</c:if>

<c:if test="${entorno.porcentajeIva}">
	<div class="etiqueta titulo cgp"><bean:message
 		key="general.porcentajeIva" bundle="etiquetas" /></div> 
 	<div class="parametro titulo "><html:text styleClass="cgp" 
 		property="porcentajeIva"></html:text></div> 
</c:if> 

<c:if test="${entorno.listIva}">
	<div class="etiqueta titulo cgp"><bean:message
  		key="general.porcentajeIva" bundle="etiquetas" /></div>  
  	<div class="parametro titulo cgp"><html:select styleClass="cgp"  
  		property="listIva" onchange="submit();">  
  		<html:option styleClass="seleccione" value="-1">[SELECCIONAR]</html:option>  
  		<html:options collection="listIva" property="monto" 
  			labelProperty="descripcion" /> 
  	</html:select></div>  
  </c:if>  

<c:if test="${entorno.controlFactura}">
	<div class="etiqueta titulo cgp"><bean:message
		key="general.controlfactura" bundle="etiquetas" /></div>
	<div class="parametro titulo "><html:text styleClass="cgp"
		property="controlFactura"></html:text></div>
</c:if>
<c:if test="${entorno.cedula}">
	<div class="etiqueta titulo cgp"><bean:message
		key="general.cedula" bundle="etiquetas" /></div>
	<div class="parametro titulo "><html:text styleClass="cgp"
		property="cedula"></html:text></div>
</c:if>

<c:if test="${entorno.nombres}">
	<div class="etiqueta titulo cgp"><bean:message
		key="general.nombres" bundle="etiquetas" /></div>
	<div class="parametro titulo cgp"><html:text styleClass="cgp"
		property="nombres"></html:text></div>
</c:if>
<c:if test="${entorno.nombreApellido}">
	<div class="etiqueta titulo cgp"><bean:message
		key="general.nombresApellido" bundle="etiquetas" /></div>
	<div class="parametro titulo cgp"><html:text styleClass="cgp"
		property="nombres"></html:text></div>
</c:if>

<c:if test="${entorno.apellidos}">
	<div class="etiqueta titulo cgp"><bean:message
		key="general.apellidos" bundle="etiquetas" /></div>
	<div class="parametro titulo cgp"><html:text styleClass="cgp"
		property="apellidos"></html:text></div>
</c:if>
<c:if test="${entorno.presupInicial}">
	<div class="etiqueta titulo cgp"><bean:message
		key="general.presupInicial" bundle="etiquetas" /></div>
	<div class="parametro titulo "><html:text styleClass="cgp"
		property="presupInicial"></html:text></div>
</c:if>
<c:if test="${entorno.subCodigo}">
	<div class="etiqueta titulo cgp"><bean:message
		key="general.subCodigo" bundle="etiquetas" /></div>
	<div class="parametro titulo "><html:text styleClass="cgp"
		property="subCodigo"></html:text></div>
</c:if>
<c:if test="${entorno.codigoPreOrden}">
	<div class="etiqueta titulo cgp"><bean:message
		key="general.codigoPreOrden" bundle="etiquetas" /></div>
	<div class="parametro titulo "><html:text styleClass="cgp"
		property="codigoPreOrden"></html:text></div>
</c:if>
<c:if test="${entorno.codigo}">
	<div class="etiqueta titulo cgp"><bean:message
		key="general.codigo" bundle="etiquetas" /></div>
	<div class="parametro titulo "><html:text styleClass="cgp"
		property="codigo"></html:text></div>
</c:if>

<c:if test="${entorno.rif}">
	<div class="etiqueta titulo cgp"><bean:message
		key="general.rif" bundle="etiquetas" /></div>
	<div class="parametro titulo cgp"><html:text styleClass="cgp"
		property="rif"></html:text></div>
</c:if>
<c:if test="${entorno.fechaProveedor}">
	<div class="etiqueta titulo cgp"><bean:message
		key="general.fecha.proveedor" bundle="etiquetas" /></div>
	<div class="parametro titulo "><html:text styleClass="cgp"
		property="fechaInicio"
		onfocus="javascript:showCalendarSelected2(document.forms[0].fechaInicio, document.forms[0].fechaInicio, 'dd/mm/yyyy','es',1);blur();" /></div>
</c:if>
<c:if test="${entorno.fechaInicio}">
	<div class="etiqueta titulo cgp"><bean:message
		key="general.fecha.inicio" bundle="etiquetas" /></div>
	<div class="parametro titulo "><html:text styleClass="cgp"
		property="fechaInicio"
		onfocus="javascript:showCalendarSelected2(document.forms[0].fechaInicio, document.forms[0].fechaInicio, 'dd/mm/yyyy','es',1);blur();" /></div>
</c:if>
<c:if test="${entorno.fechaFin}">
	<div class="etiqueta titulo cgp"><bean:message
		key="general.fecha.fin" bundle="etiquetas" /></div>
	<div class="parametro titulo "><html:text styleClass="cgp"
		property="fechaFin"
		onfocus="javascript:showCalendarSelected2(document.forms[0].fechaFin, document.forms[0].fechaFin, 'dd/mm/yyyy','es',1);blur();" /></div>
</c:if>
<c:if test="${entorno.fechaFactura}">
	<div class="etiqueta titulo cgp"><bean:message
		key="general.fechafactura" bundle="etiquetas" /></div>
	<div class="parametro titulo cgp"><html:text styleClass="cgp"
		property="fechaFactura"
		onfocus="javascript:showCalendarSelected2(document.forms[0].fechaFactura, document.forms[0].fechaFactura, 'dd/mm/yyyy','es',1);blur();" /></div>
</c:if>
<c:if test="${entorno.fechaRecepcionFactura}">
	<div class="etiqueta titulo cgp"><bean:message
		key="general.fechaRecepcionFactura" bundle="etiquetas" /></div>
	<div class="parametro titulo cgp"><html:text styleClass="cgp"
		property="fechaRecepcionFactura"
		onfocus="javascript:showCalendarSelected2(document.forms[0].fechaRecepcionFactura, document.forms[0].fechaRecepcionFactura, 'dd/mm/yyyy','es',1);blur();" /></div>
</c:if>
<c:if test="${entorno.fechaOcurrencia}">
	<div class="etiqueta titulo cgp"><bean:message
		key="general.fecha.ocurrencia" bundle="etiquetas" /></div>
	<div class="parametro titulo cgp"><html:text styleClass="cgp"
		property="fechaOcurrencia" 
		onfocus="javascript:showCalendarSelected2(document.forms[0].fechaOcurrencia, document.forms[0].fechaOcurrencia, 'dd/mm/yyyy','es',1);blur();" /></div>
</c:if>

<c:if test="${entorno.fechaNotificacion}">
	<div class="etiqueta titulo cgp"><bean:message
		key="general.fecha.notificacion" bundle="etiquetas" /></div>
	<div class="parametro titulo cgp"><html:text styleClass="cgp"
		property="fechaNotificacion"
		onfocus="javascript:showCalendarSelected2(document.forms[0].fechaNotificacion, document.forms[0].fechaNotificacion, 'dd/mm/yyyy','es',1);blur();" /></div>
</c:if>


<c:if test="${entorno.cobertura || entorno.coberturaRangoFecha}">
	<br>
	<div class="etiqueta titulo cgp"><bean:message
		key="general.cobertura" bundle="etiquetas" /></div>
	<div class="parametro titulo cgp"><c:if
		test="${siniestroPadre==null}">


		<html:select styleClass="cgp" property="cobertura"
			onchange="submit();">
			<html:option styleClass="seleccione" value="-1">[SELECCIONAR]</html:option>
			<html:options collection="listCobertura" property="id"
				labelProperty="descripcion"  />
		</html:select>
	</c:if> <c:if
		test="${siniestroPadre!=null && siniestroPadre.cobertura.tipoCobertura.id!=8}">


		<html:select styleClass="cgp" property="cobertura"
			onchange="submit();">
			<html:option styleClass="seleccione" value="-1">[SELECCIONAR]</html:option>
			<html:options collection="listCobertura" property="id"
				labelProperty="descripcion" />
		</html:select>
	</c:if> 
	
	<c:if
		test="${siniestroPadre!=null  && siniestroPadre.cobertura.tipoCobertura.id==8}">
		<html:hidden
		property="cobertura" value="${siniestroPadre.cobertura.id}"/>
		<c:out value="${siniestroPadre.cobertura.tipoCobertura.descripcion}" />

	</c:if> 
	
	<c:if test="${detalleMontoCobertura!=null}">

		<table class="tabla" width="3px" cellspacing="1" border="0">
			<tr class="tituloCabecera">
				<td width="1px">Monto:</td>
				<td width="1px">Agotada:</td>
				<td width="1px">Disponible:</td>
			</tr>
			<c:forEach items="${detalleMontoCobertura}" var="cobert">
				<tr class="item" bgcolor="white">
					<td width="1px"><fmt:formatNumber minFractionDigits="2"
						maxFractionDigits="2" value="${cobert.monto}" /> Bs.</td>
					<td width="1px"><fmt:formatNumber minFractionDigits="2"
						maxFractionDigits="2" value="${cobert.montoAgotada}" /> Bs.</td>
					<td width="1px"><fmt:formatNumber minFractionDigits="2"
						maxFractionDigits="2" value="${cobert.montoDisponible}" /> Bs.</td>
				</tr>
			</c:forEach>
		</table>

	</c:if> 
	
	<c:if test="${desgloseCobertura!=null}">

		<table class="tabla" width="3px" cellspacing="1" border="0">
			<tr class="tituloCabecera">
				<td width="1px">Patología:</td>
				<td width="1px">Agotada:</td>
				<td width="1px">Disponible:</td>
			</tr>
			<c:forEach items="${desgloseCobertura}" var="desg">
				<tr class="item" bgcolor="white">
					<td width="1px"><c:out value="${desg.patologia}" /></td>
					<td width="1px"><fmt:formatNumber minFractionDigits="2"
						maxFractionDigits="2" value="${desg.montoAgotada}" /> Bs.</td>
					<td width="1px"><fmt:formatNumber minFractionDigits="2"
						maxFractionDigits="2" value="${desg.montoDisponible}" /> Bs.</td>
				</tr>
			</c:forEach>
		</table>

	</c:if></div>
	<br>
</c:if>









<c:if test="${entorno.detalleServMedico}">
	<c:if test="${listConsultas!=null}">
		<br>
		<table class="tabla" width="600" cellpadding="2" cellspacing="1"
			border="0">
			<tr class="tituloCabecera">
				<div class="textorange" style="padding-bottom: 5px">Datos de
				Servicios Médicos</div>
				<td><bean:message key="general.fechaRegistro"
					bundle="etiquetas" /></td>
				<td><bean:message key="general.especialidad" bundle="etiquetas" /></td>
				<td><bean:message key="general.observacion" bundle="etiquetas" /></td>
				<td><bean:message key="general.medicamento" bundle="etiquetas" /></td>
				<td><bean:message key="general.seleccionar" bundle="etiquetas" /></td>
			</tr>
			<c:forEach items="${listConsultas}" var="consulta">
				<c:forEach items="${consulta.recipes}" var="recipe">
					<c:set var="pintado"></c:set>
					<tr class="item" bgcolor="white">
						<td><fmt:formatDate pattern="dd/MM/yyyy"
							value="${recipe.fechaRegistro}" /></td>
						<td><c:out value="${recipe.especialidad}" /></td>
						<td><c:out value="${recipe.observaciones}" /></td>
						<td><c:forEach items="${recipe.medicamentos}"
							var="medicamento">
								- <c:out value="${medicamento.medicamento}" />
							<br />
						</c:forEach></td>
						<td align="center" class="titblue" width="11%"><input
							name="consignables" onclick="submit();" type="checkbox"
							value="<c:out value="${recipe.idRecipe}"/>" style="width: auto" /></td>


					</tr>
				</c:forEach>
			</c:forEach>

		</table>
		<br>
	</c:if>

</c:if>
<c:if test="${entorno.poliza}">
	<div class="etiqueta titulo cgp"><bean:message
		key="general.poliza" bundle="etiquetas" /></div>
	<div class="parametro titulo cgp"><html:select styleClass="cgp"
		property="poliza">
		<html:option styleClass="seleccione" value="-1">[SELECCIONAR]</html:option>
		<html:options collection="listPoliza" property="id"
			labelProperty="descripcion" />
	</html:select></div>
</c:if>
<c:if test="${entorno.cantFacturas}">
	<div class="etiqueta titulo cgp"><bean:message
		key="general.cantFacturas" bundle="etiquetas" /></div>
	<div class="parametro titulo cgp"><html:hidden
		property="cantFacturas" /> <html:text onfocus="blur()"
		styleClass="cgp" style="width:24%" property="cantFacturas" /> <a
		class="red" href="#"
		onclick="javascript:openWinScroll2('<%=request.getContextPath()%>/security/reembolsos/cargaFactura.do?nroSin=<c:out value="${siniestro.aniomesCodigo}${siniestro.codigo}${siniestro.subCodigo}"/>&idS=<c:out  value="${siniestro.id}"/>&montop=<c:out  value="${siniestro.montoPresupuestado}"/>&anioS=<c:out  value="${siniestro.anioSiniestro}"/>','name',620,500);">Cargar
	Facturas </a></div>
</c:if>
<c:if test="${entorno.tgastosClinicos}">
	<div class="etiqueta titulo cgp"><bean:message
		key="general.tgastosClinicos" bundle="etiquetas" /></div>
	<div class="parametro titulo "><html:text styleClass="cgp"
		property="tgastosClinicos"></html:text></div>
</c:if>
<c:if test="${entorno.thonoMedicos}">
	<div class="etiqueta titulo cgp"><bean:message
		key="general.thonoMedicos" bundle="etiquetas" /></div>
	<div class="parametro titulo "><html:text styleClass="cgp"
		property="thonoMedicos"></html:text></div>
</c:if>
<c:if test="${entorno.totalFacturado}">
	<div class="etiqueta titulo cgp"><bean:message
		key="general.totalFacturado" bundle="etiquetas" /></div>
	<div class="parametro titulo "><html:text styleClass="cgp"
		property="totalFacturado"></html:text></div>
</c:if>
<c:if test="${entorno.descripcion}">
	<div class="etiqueta titulo cgp"><bean:message
		key="general.descripcion" bundle="etiquetas" /></div>
	<div class="parametro titulo "><html:text styleClass="cgp"
		property="descripcion"></html:text></div>
</c:if>
<c:if test="${entorno.totalMontoNoAmparado}">
	<div class="etiqueta titulo cgp"><bean:message
		key="general.totalMontoNoAmparado" bundle="etiquetas" /></div>
	<div class="parametro titulo "><html:text styleClass="cgp"
		property="totalMontoNoAmparado"></html:text></div>
</c:if>
<c:if test="${entorno.totalAliquidar}">
	<div class="etiqueta titulo cgp"><bean:message
		key="general.totalAliquidar" bundle="etiquetas" /></div>
	<div class="parametro titulo "><html:text styleClass="cgp"
		property="totalAliquidar"></html:text></div>
</c:if>
<c:if test="${entorno.fechaLiquidacion}">
	<div class="etiqueta titulo cgp"><bean:message
		key="general.fechaLiquidacion" bundle="etiquetas" /></div>
	<div class="parametro titulo cgp"><html:text styleClass="cgp"
		property="fechaLiquidacion"
		onfocus="javascript:showCalendarSelected2(document.forms[0].fechaLiquidacion, document.forms[0].fechaLiquidacion, 'dd/mm/yyyy','es',1);blur();" /></div>
</c:if>

<c:if test="${entorno.fechaIngreso}">
	<div class="etiqueta titulo cgp"><bean:message
		key="general.fechaIngreso" bundle="etiquetas" /></div>
	<div class="parametro titulo cgp"><html:text styleClass="cgp"
		property="fechaIngreso"
		onfocus="javascript:showCalendarSelected2(document.forms[0].fechaIngreso, document.forms[0].fechaIngreso, 'dd/mm/yyyy','es',1);blur();" /></div>
</c:if>
<c:if test="${entorno.fechaEgreso}">
	<div class="etiqueta titulo cgp"><bean:message
		key="general.fechaEgreso" bundle="etiquetas" /></div>
	<div class="parametro titulo cgp"><html:text styleClass="cgp"
		property="fechaEgreso"
		onfocus="javascript:showCalendarSelected2(document.forms[0].fechaEgreso, document.forms[0].fechaEgreso, 'dd/mm/yyyy','es',1);blur();" /></div>
</c:if>
<c:if test="${entorno.fechaEgresoRequerida}">

	<div class="etiqueta titulo cgp"><bean:message
		key="general.fechaEgreso" bundle="etiquetas" /></div>

	<div class="parametro titulo cgp"><html:text styleClass="cgp"
		property="fechaEgreso"
		onfocus="javascript:showCalendarSelected2(document.forms[0].fechaEgreso, document.forms[0].fechaEgreso, 'dd/mm/yyyy','es',1);blur();" /></div>
</c:if>
<c:if test="${entorno.tipoSiniestro}">
	<div class="etiqueta titulo cgp"><bean:message
		key="general.tiposiniestro" bundle="etiquetas" /></div>
	<div class="parametro titulo cgp"><html:select styleClass="cgp"
		property="tipoSiniestro">
		<html:option styleClass="seleccione" value="-1">[SELECCIONAR]</html:option>
		<html:options collection="listTipoSiniestro" property="id"
			labelProperty="descripcion" />
	</html:select></div>
</c:if>
<c:if test="${entorno.especialidad}">
	<div class="etiqueta titulo cgp"><bean:message
		key="general.especialidad" bundle="etiquetas" /></div>
	<div class="parametro titulo cgp"><html:select styleClass="cgp"
		property="especialidad" onchange="submit();">
		<html:option styleClass="seleccione" value="-1">[SELECCIONAR]</html:option>
		<html:options collection="listEspecialidad" property="id"
			labelProperty="descripcion" />
	</html:select></div>
</c:if>
<c:if test="${entorno.organo}">
	<div class="etiqueta titulo cgp"><bean:message
		key="general.organo" bundle="etiquetas" /></div>
	<div class="parametro titulo cgp"><html:select styleClass="cgp"
		property="organo">
		<html:option styleClass="seleccione" value="-1">[SELECCIONAR]</html:option>
		<html:options collection="listOrgano" property="id"
			labelProperty="descripcion" />
	</html:select></div>
</c:if>
<c:if test="${entorno.tipoTratamiento}">
	<div class="etiqueta titulo cgp"><bean:message
		key="general.tipoTratamiento" bundle="etiquetas" /></div>
	<div class="parametro titulo cgp"><html:select styleClass="cgp"
		property="tipoTratamiento">
		<html:option styleClass="seleccione" value="-1">[SELECCIONAR]</html:option>
		<html:options collection="listTratamiento"
			property="idTipoTratamiento" labelProperty="descripcionTratamiento" />
	</html:select></div>
</c:if>


<c:if test="${entorno.patologias}">
	<div class="etiqueta titulo cgp"><bean:message
		key="general.patologia" bundle="etiquetas" /></div>
	<div class="parametro titulo cgp"><html:select styleClass="cgp"
		property="patologias" onchange="submit();">
		<html:option styleClass="seleccione" value="-1">[SELECCIONAR]</html:option>
		<html:options collection="listPatologias" property="id"
			labelProperty="descripcion" />
	</html:select></div>
</c:if>
<c:if test="${entorno.tratamiento}">
	<div class="etiqueta titulo cgp"><bean:message
		key="general.tratamiento" bundle="etiquetas" /></div>
	<div class="parametro titulo cgp"><html:select styleClass="cgp"
		property="tratamiento">
		<html:option styleClass="seleccione" value="-1">[SELECCIONAR]</html:option>
		<html:options collection="listTratamiento" property="id"
			labelProperty="descripcion" />
	</html:select></div>
</c:if>
<c:if test="${entorno.tipoEmpleado}">
	<div class="etiqueta titulo cgp"><bean:message
		key="general.tipoempleado" bundle="etiquetas" /></div>
	<div class="parametro titulo cgp"><html:select styleClass="cgp"
		property="tipoEmpleado">
		<html:option styleClass="seleccione" value="-1">[SELECCIONAR]</html:option>
		<html:options collection="listEmpleado" property="id"
			labelProperty="descripcion" />
	</html:select></div>
</c:if>
<c:if test="${entorno.numeroOrdenPago}">
	<div class="etiqueta titulo cgp"><bean:message
		key="general.numeroOrdenPago" bundle="etiquetas" /></div>
	<div class="parametro titulo "><html:text styleClass="cgp"
		property="numeroOrdenPago"></html:text></div>
</c:if>
<c:if test="${entorno.tipoEnfermedad}">
	<div class="etiqueta titulo cgp"><bean:message
		key="general.tipoenfermedad" bundle="etiquetas" /></div>
	<div class="parametro titulo cgp"><html:select styleClass="cgp"
		property="tipoEnfermedad">
		<html:option styleClass="seleccione" value="-1">[SELECCIONAR]</html:option>
		<html:options collection="listTipoEnfermedad" property="id"
			labelProperty="descripcion" />
	</html:select></div>
</c:if>
<c:if test="${entorno.estatus}">
	<div class="etiqueta titulo cgp"><bean:message
		key="general.estatus" bundle="etiquetas" /></div>
	<div class="parametro titulo cgp"><html:select styleClass="cgp"
		property="estatus" onchange="submit();">
		<html:option styleClass="seleccione" value="-1">[SELECCIONAR]</html:option>
		<html:options collection="listEstatus" property="id"
			labelProperty="descripcion" />
	</html:select></div>
</c:if>
<c:if test="${justificar!=null}">
	<div class="etiqueta titulo cgp"><bean:message
		key="general.justificacion" bundle="etiquetas" /></div>
	<div class="parametro titulo cgp"><html:textarea
		styleClass="cgp texto" property="justificacion">
	</html:textarea> <input type="hidden" name="justificar" id="justificar" /></div>
</c:if>
<c:if test="${entorno.tipoProveedor}">
	<div class="etiqueta titulo cgp"><bean:message
		key="general.tipoProveedor" bundle="etiquetas" /></div>
	<div class="parametro titulo cgp"><html:select styleClass="cgp"
		property="tipoProveedor" onchange="limpiarProveedor();submit();">
		<html:option styleClass="seleccione" value="-1">[SELECCIONAR]</html:option>
		<html:options collection="listTipoProveedor" property="id"
			labelProperty="descripcion" />
	</html:select></div>
	<div class="etiqueta titulo cgp"></div>
<script>
function limpiarProveedor(){
	document.forms[0].proveedor.value = '';
	document.forms[0].idProveedor.value = '0';
}
</script>

</c:if>

<!-- CAMBIO FASDEM TIPO PROVEEDOR FECHA 21/09/2015-->	
<c:if test="${entorno.proveedor}">
	<div class="etiqueta titulo cgp"><bean:message
		key="general.proveedor" bundle="etiquetas" /></div>
	<div class="parametro titulo cgp"><html:hidden
		property="idProveedor" /> <html:text onfocus="blur()"
		styleClass="cgp" style="width:50%" property="proveedor" /> <a
		class="red" href="#"
		onclick="javascript:openWinScrollProv('<%=request.getContextPath()%>/security/comunes/busquedaProveedorP.do?tipoProveedor=' + document.forms[0].tipoProveedor.value +'&listTipoTramite=<c:out value="${idtt}" />','name',500,500);">buscar</a></div>
</c:if>


<!-- PROVEEDOR LIQUIDACION DE MEDICINAS -->

<c:if test="${entorno.proveedor_combo}">
	<div class="etiqueta titulo cgp"><bean:message
		key="general.proveedorCombo" bundle="etiquetas" /></div>
	<div class="parametro titulo cgp"><html:select styleClass="cgp"
		property="proveedorCombo" >
		<html:option styleClass="seleccione" value="-1">[SELECCIONAR]</html:option>
		<html:options collection="listTipoProveedorCombo" property="id"
			labelProperty= "descripcion" />
	</html:select></div>
	<div class="etiqueta titulo cgp"></div>


</c:if>

<!-- ******************************** -->





<c:if test="${entorno.tipoProveedorRmbls}">
	<div class="etiqueta titulo cgp"><bean:message
		key="general.tipoProveedor" bundle="etiquetas" /></div>
	<div class="parametro titulo cgp"><html:select styleClass="cgp"
		property="tipoProveedor" onchange="submit();">
		<html:option styleClass="seleccione" value="-1">[SELECCIONAR]</html:option>
		<html:options collection="listTipoProveedoRmbls" property="id"
			labelProperty="descripcion" />
	</html:select></div>
	<div class="etiqueta titulo cgp"></div>

</c:if>
<c:if test="${cuenta!=null}">
	<div class="etiqueta titulo cgp"><bean:message
		key="general.infcuenta" bundle="etiquetas" /></div>
	<div class="parametro titulo cgp"><c:out value="${cuenta.cuenta}" />
	<c:out value="${cuenta.nombreBanco}" /></div>
</c:if>
<c:if test="${entorno.causaIngreso}">
	<div class="etiqueta titulo cgp"><bean:message
		key="general.causaingreso" bundle="etiquetas" /></div>
	<div class="parametro titulo cgp"><html:hidden
		property="idCausaIngreso" /> 
	<c:if test="${siniestroPadre==null}">
		<html:text onfocus="blur()" styleClass="cgp" style="width:50%"
			property="causaIngreso" />
		<a class="red" href="#"
			onclick="javascript:openWinScroll2('<%=request.getContextPath()%>/security/comunes/busquedaCausaIngreso.do','name',700,400);">buscar</a>
	</c:if> 
	<c:if
		test="${siniestroPadre!=null&&siniestroPadre.cobertura.tipoCobertura.id!=8}">
		<html:text onfocus="blur()" styleClass="cgp" style="width:50%"
			property="causaIngreso" />
		<a class="red" href="#"
			onclick="javascript:openWinScroll2('<%=request.getContextPath()%>/security/comunes/busquedaCausaIngreso.do','name',700,400);">buscar</a>
	</c:if>
	<c:if
		test="${siniestroPadre!=null&&siniestroPadre.cobertura.tipoCobertura.id==8}">
		<c:out value="${siniestroPadre.patologiaOrganoTratamiento.descripcion}" />
		
	</c:if>
	</div>
</c:if>



<c:if
	test="${entorno.observacion || entorno.observacionMedicinas|| entorno.observacionNoRequerida}">
	<div class="etiqueta titulo cgp"><bean:message
		key="general.observacion" bundle="etiquetas" /></div>
	<div class="parametro titulo cgp"><html:textarea
		styleClass="cgp texto" property="observacion">
	</html:textarea></div>
</c:if>

<c:if test="${entorno.montoFactura}">
	<div class="etiqueta titulo cgp"><bean:message
		key="general.montoFactura" bundle="etiquetas" /></div>
	<div class="parametro titulo "><html:text styleClass="cgp"
		property="montoFactura"></html:text></div>
</c:if>
<c:if test="${entorno.montoPagado}">
	<div class="etiqueta titulo cgp"><bean:message
		key="general.monto.pagado" bundle="etiquetas" /></div>
	<div class="parametro titulo "><html:text styleClass="cgp"
		property="montoPagado"></html:text></div>
</c:if>
<c:if test="${entorno.montoPresupuestado}">
	<div class="etiqueta titulo cgp"><bean:message
		key="general.montoPresupuestado" bundle="etiquetas" /></div>
	<div class="parametro titulo "><html:text styleClass="cgp"
		property="montoPresupuestado"></html:text></div>
</c:if>
<c:if test="${entorno.montoNegociado}">
	<div class="etiqueta titulo cgp"><bean:message
		key="general.negociado.solo" bundle="etiquetas" /></div>
	<div class="parametro titulo "><html:text styleClass="cgp"
		property="montoNegociado"></html:text></div>
</c:if>
<c:if test="${entorno.montoAmparado}">
	<div class="etiqueta titulo cgp"><bean:message
		key="general.monto.amparado" bundle="etiquetas" /></div>
	<div class="parametro titulo "><html:text styleClass="cgp"
		property="montoAmparado"></html:text></div>
        <c:if test="${montoMaximoAutorizado!=null}">
        <div class="etiqueta titulo cgp"><bean:message
		key="general.monto.autorizado" bundle="etiquetas" /></div>
        <div class="parametro titulo "><fmt:formatNumber maxFractionDigits="2"
				minFractionDigits="2" groupingUsed="true" value="${montoMaximoAutorizado}" />
			Bs.</div>
</c:if>
        
</c:if>

<c:if test="${entorno.montoNoAmparado}">
	<div class="etiqueta titulo cgp"><bean:message
		key="general.monto.noamparado" bundle="etiquetas" /></div>
	<div class="parametro titulo "><html:text styleClass="cgp"
		property="montoNoAmparado"></html:text></div>
</c:if>
<c:if test="${entorno.monto}">
	<div class="etiqueta titulo cgp"><bean:message
		key="general.monto" bundle="etiquetas" /></div>
	<div class="parametro titulo "><html:text styleClass="cgp"
		property="monto"></html:text>Bs.</div>
</c:if>

<c:if test="${entorno.montoHonorariosMedicos}">
	<div class="etiqueta titulo cgp"><bean:message
		key="general.montoHonorariosMedicos" bundle="etiquetas" /></div>
	<div class="parametro titulo "><html:text styleClass="cgp"
		property="montoHonorariosMedicos" onchange="submit();"></html:text>Bs.</div>
</c:if>


<c:if test="${entorno.montoGastosClinicos}">
	<div class="etiqueta titulo cgp"><bean:message
		key="general.montoGastosClinicos" bundle="etiquetas" /></div>
	<div class="parametro titulo "><html:text styleClass="cgp"
		property="montoGastosClinicos" onchange="submit();"></html:text>Bs.</div>
</c:if>
<c:if test="${entorno.montoExamenesPreoperatorios}">
	<div class="etiqueta titulo cgp"><bean:message
		key="general.montoExamenesPreoperatorios" bundle="etiquetas" /></div>
	<div class="parametro titulo "><html:text styleClass="cgp"
		property="montoExamenesPreoperatorios" onchange="submit();"></html:text>Bs.</div>
</c:if>

<c:if test="${entorno.montoFuneraria}">
	<div class="etiqueta titulo cgp"><bean:message
		key="general.montoFuneraria" bundle="etiquetas" /></div>
	<div class="parametro titulo "><html:text styleClass="cgp"
		property="montoFuneraria" onchange="submit();"></html:text>Bs.</div>
</c:if>

<c:if test="${entorno.montoAmbulancia}">
	<div class="etiqueta titulo cgp"><bean:message
		key="general.montoAmbulancia" bundle="etiquetas" /></div>
	<div class="parametro titulo "><html:text styleClass="cgp"
		property="montoAmbulancia" onchange="submit();"></html:text>Bs.</div>
</c:if>

<c:if test="${entorno.montoExamenesEspeciales}">
	<div class="etiqueta titulo cgp"><bean:message
		key="general.montoExamenesEspeciales" bundle="etiquetas" /></div>
	<div class="parametro titulo "><html:text styleClass="cgp"
		property="montoExamenesEspeciales" onchange="submit();"></html:text>Bs.</div>
</c:if>

<c:if test="${entorno.citaPreOperatorio}">
	<div class="etiqueta titulo cgp"><bean:message
		key="general.citaPreOperatorio" bundle="etiquetas" /></div>
	<div class="parametro titulo " align="right"><html:checkbox
		property="citaPreOperatorio" value="true"></html:checkbox></div>
</c:if>

<c:if test="${entorno.citaPostOperatorio}">
	<div class="etiqueta titulo cgp"><bean:message
		key="general.citaPostOperatorio" bundle="etiquetas" /></div>
	<div class="parametro titulo " align="right"><html:checkbox
		property="citaPostOperatorio" value="true"></html:checkbox></div>
</c:if>



<c:if test="${entorno.notaTecnica}">
	<div class="etiqueta titulo cgp">
		<bean:message key="general.notatecnicas" bundle="etiquetas" />
	</div>
	<div class="parametro titulo cgp">
		<a 
			class="red" 
			href="#"
			onclick="javascript:openWinScroll2('<%=request.getContextPath()%>/security/notatecnica/saveNotaTecnica.do?idSiniestro=<bean:write name="globalActionForm" property="id"/>&anioSiniestro=<bean:write name="globalActionForm" property="anioSiniestro"/>','name',500,500);">
			Ver Nota Técnica</a>
	</div>
</c:if>

<c:if test="${entorno.printNotaTecnica}">

				<div class="etiqueta titulo cgp"><bean:message
					key="general.notaActivo" bundle="etiquetas" /></div>
				<div class="parametro titulo cgp">	
				<img src="<%=request.getContextPath()%>/images/printer.png"
					onclick="javascript:openWinScroll2('<%=request.getContextPath()%>/security/comunes/generareporte.do?id=<bean:write name="globalActionForm" property="id"/>&anio=<bean:write name="globalActionForm" property="anioSiniestro"/>&id_reporte=11','NotaTecnicaUsuarioActivo',800,600);">
</div>

				<div class="etiqueta titulo cgp"><bean:message
					key="general.notaSiniestro" bundle="etiquetas" /></div>
					<div class="parametro titulo cgp">
				<img src="<%=request.getContextPath()%>/images/printer.png"
					onclick="javascript:openWinScroll2('<%=request.getContextPath()%>/security/comunes/generareporte.do?id=<bean:write name="globalActionForm" property="id"/>&anio=<bean:write name="globalActionForm" property="anioSiniestro"/>&id_reporte=12','NotaTecnicaSiniestro',800,600);">
					</div>


</c:if>



<c:if test="${entorno.montoMaterialMedicoConIva}">
	<div class="etiqueta titulo cgp"><bean:message
		key="general.montoMaterialMedicoConIva" bundle="etiquetas" /></div>
	<div class="parametro titulo "><html:text styleClass="cgp"
		property="montoMaterialMedicoConIva" onchange="submit();"></html:text>Bs.</div>
</c:if>
<c:if test="${entorno.montoMaterialMedicoSinIva}">
	<div class="etiqueta titulo cgp"><bean:message
		key="general.montoMaterialMedicoSinIva" bundle="etiquetas" /></div>
	<div class="parametro titulo "><html:text styleClass="cgp"
		property="montoMaterialMedicoSinIva" onchange="submit();"></html:text>Bs.</div>
</c:if>

<c:if test="${entorno.montosPresupuesto}">

	<table class="tabla" width="600" cellpadding="2" cellspacing="1"
		border="0">

		<tr class="item" bgcolor="white">
			<td width="70"><bean:message key="general.presupuestado"
				bundle="etiquetas" /></td>
			<td align="left">
			<div class="parametro titulo " align="left"><html:text
				styleClass="cgp" style="text-align:right"
				property="montoPresupuestado"></html:text>Bs.</div>
			</td>
		</tr>

		<tr class="item" bgcolor="white">
			<td width="70"><bean:message key="general.negociado"
				bundle="etiquetas" /></td>
			<td align="right">
			<div class="parametro titulo " align="left"><html:text
				styleClass="cgp" style="text-align:right" property="montoNegociado"></html:text>Bs.</div>
			</td>
		</tr>

		<tr class="item" bgcolor="white">
			<td width="70"><bean:message key="general.amparado"
				bundle="etiquetas" /></td>
			<td align="left">
			<div class="parametro titulo " align="left"><html:text
				styleClass="cgp" style="text-align:right" property="montoAmparado"></html:text>Bs.</div>
			</td>
		</tr>

	</table>
</c:if>
<c:if test="${entorno.detallePresupuesto}">

	<table class="tabla" width="600" cellpadding="2" cellspacing="1"
		border="0">

		<tr class="tituloCabecera">
			<div class="textorange" style="padding-bottom: 5px">Detalles
			del Presupuesto</div>
			<td>
			<div align="left">Tipo Gasto</div>
			</td>
			<td>
			<div align="center">Presupuestado</div>
			</td>
			<td>
			<div align="center">Ajustado</div>
			</td>
			<td>
			<div align="center">Amparado</div>
			</td>
			<td>
			<div align="center">No Amparado</div>
			</td>
		</tr>

		<tr class="item" bgcolor="white">
			<td><bean:message key="general.montoHonorariosMedicos"
				bundle="etiquetas" /></td>
			<td>
			<div class="parametro titulo " align="right"><html:text
				styleClass="cgp" style="text-align:right"
				property="montoHonorariosPresupuestado" onchange="submit();"></html:text>Bs.</div>
			</td>
			<td>
			<div class="parametro titulo " align="right"><html:text
				styleClass="cgp" style="text-align:right"
				property="montoHonorariosNegociado" onchange="submit();"></html:text>Bs.</div>
			</td>
			<td>
			<div class="parametro titulo " align="right"><html:text
				styleClass="cgp" style="text-align:right"
				property="montoHonorariosMedicos" onchange="submit();"></html:text>Bs.</div>
			</td>
			<td>
			<div align="right" class="parametro titulo "><fmt:formatNumber
				maxFractionDigits="2" minFractionDigits="2" groupingUsed="true"
				value="${montoHonorariosMedicosNoAmparado}" /> Bs.</div>
			</td>
		</tr>


		<tr class="item" bgcolor="white">
			<td><bean:message key="general.montoGastosClinicos"
				bundle="etiquetas" /></td>
			<td>
			<div class="parametro titulo " align="right"><html:text
				styleClass="cgp" style="text-align:right"
				property="montoGastosPresupuestado" onchange="submit();"></html:text>Bs.</div>
			</td>
			<td>
			<div class="parametro titulo " align="right"><html:text
				styleClass="cgp" style="text-align:right" name="globalActionForm"
				property="montoGastosNegociado" onchange="submit();"></html:text>Bs.</div>
			</td>
			<td>
			<div class="parametro titulo " align="right"><html:text
				styleClass="cgp" style="text-align:right" name="globalActionForm"
				property="montoGastosClinicos" onchange="submit();"></html:text>Bs.</div>
			</td>
			<td>
			<div align="right" class="parametro titulo "><fmt:formatNumber
				maxFractionDigits="2" minFractionDigits="2" groupingUsed="true"
				value="${montoGastosClinicosNoAmparado}" /> Bs.</div>
			</td>
		</tr>

		<tr>
			<td>Total</td>
			<td>
			<div class="parametro titulo ">
			<div align="right"><fmt:formatNumber maxFractionDigits="2"
				minFractionDigits="2" groupingUsed="true"
				value="${montoPresupuestado}" /> Bs.</div>
			</div>
			</td>
			<td>
			<div class="parametro titulo ">
			<div align="right"><fmt:formatNumber maxFractionDigits="2"
				minFractionDigits="2" groupingUsed="true" value="${montoNegociado}" />
			Bs.</div>
			</div>
			</td>
			<td>
			<div class="parametro titulo ">
			<div align="right"><fmt:formatNumber maxFractionDigits="2"
				minFractionDigits="2" groupingUsed="true" value="${montoAmparado}" />
			Bs.</div>
			</div>
			</td>
			<td>
			<div class="parametro titulo " align="right"><fmt:formatNumber
				maxFractionDigits="2" minFractionDigits="2" groupingUsed="true"
				value="${montoNoAmparado}" /> Bs.</div>
			</td>
		</tr>
	</table>
</c:if>
<c:if test="${entorno.detallePresupuestoEmergencia}">
	<table class="tabla" width="600" cellpadding="2" cellspacing="1"
		border="0">

		<tr class="tituloCabecera">
			<div class="textorange" style="padding-bottom: 5px">Detalles
			del Presupuesto</div>
			<td>
			<div align="left">Tipo Gasto</div>
			</td>
			<td>
			<div align="center">Presupuestado</div>
			</td>
			<td>
			<div align="center">Ajustado/Facturado</div>
			</td>
			<td>
			<div align="center">Amparado</div>
			</td>
			<td>
			<div align="center">No Amparado</div>
			</td>
		</tr>

		<tr class="item" bgcolor="white">
			<td><bean:message key="general.montoHonorariosMedicos"
				bundle="etiquetas" /></td>
			<td>
			<div class="parametro titulo " align="right"><html:text
				styleClass="cgp" style="text-align:right"
				property="montoHonorariosPresupuestado" onchange="submit();"></html:text>Bs.</div>
			</td>
			<td>
			<div class="parametro titulo " align="right"><html:text
				styleClass="cgp" style="text-align:right"
				property="montoHonorariosNegociado" onchange="submit();"></html:text>Bs.</div>
			</td>
			<td>
			<div class="parametro titulo " align="right"><html:text
				styleClass="cgp" style="text-align:right"
				property="montoHonorariosMedicos" onchange="submit();"></html:text>Bs.</div>
			</td>
			<td>
			<div align="right" class="parametro titulo "><fmt:formatNumber
				maxFractionDigits="2" minFractionDigits="2" groupingUsed="true"
				value="${montoHonorariosMedicosNoAmparado}" /> Bs.</div>
			</td>
		</tr>


		<tr class="item" bgcolor="white">
			<td><bean:message key="general.montoGastosClinicos"
				bundle="etiquetas" /></td>
			<td>
			<div class="parametro titulo " align="right"><html:text
				styleClass="cgp" style="text-align:right"
				property="montoGastosPresupuestado" onchange="submit();"></html:text>Bs.</div>
			</td>
			<td>
			<div class="parametro titulo " align="right"><html:text
				styleClass="cgp" style="text-align:right" name="globalActionForm"
				property="montoGastosNegociado" onchange="submit();"></html:text>Bs.</div>
			</td>
			<td>
			<div class="parametro titulo " align="right"><html:text
				styleClass="cgp" style="text-align:right" name="globalActionForm"
				property="montoGastosClinicos" onchange="submit();"></html:text>Bs.</div>
			</td>
			<td>
			<div align="right" class="parametro titulo "><fmt:formatNumber
				maxFractionDigits="2" minFractionDigits="2" groupingUsed="true"
				value="${montoGastosClinicosNoAmparado}" /> Bs.</div>
			</td>
		</tr>
		<tr class="item" bgcolor="white">
			<td><bean:message key="general.montoAmbulancia"
				bundle="etiquetas" /></td>
			<td>
			<div class="parametro titulo " align="right"><html:text
				styleClass="cgp" style="text-align:right"
				property="montoAmbulanciaPresupuestado" onchange="submit();"></html:text>Bs.</div>
			</td>
			<td>
			<div class="parametro titulo " align="right"><html:text
				styleClass="cgp" style="text-align:right" name="globalActionForm"
				property="montoAmbulanciaNegociado" onchange="submit();"></html:text>Bs.</div>
			</td>
			<td>
			<div class="parametro titulo " align="right"><html:text
				styleClass="cgp" style="text-align:right" name="globalActionForm"
				property="montoAmbulancia" onchange="submit();"></html:text>Bs.</div>
			</td>
			<td>
			<div align="right" class="parametro titulo "><fmt:formatNumber
				maxFractionDigits="2" minFractionDigits="2" groupingUsed="true"
				value="${montoAmbulanciaNoAmparado}" /> Bs.</div>
			</td>
		</tr>
		<tr class="item" bgcolor="white">
			<td><bean:message key="general.montoFuneraria"
				bundle="etiquetas" /></td>
			<td>
			<div class="parametro titulo " align="right"><html:text
				styleClass="cgp" style="text-align:right"
				property="montoFunerariaPresupuestado" onchange="submit();"></html:text>Bs.</div>
			</td>
			<td>
			<div class="parametro titulo " align="right"><html:text
				styleClass="cgp" style="text-align:right" name="globalActionForm"
				property="montoFunerariaNegociado" onchange="submit();"></html:text>Bs.</div>
			</td>
			<td>
			<div class="parametro titulo " align="right"><html:text
				style="text-align:right" styleClass="cgp" name="globalActionForm"
				property="montoFuneraria" onchange="submit();"></html:text>Bs.</div>
			</td>
			<td>
			<div align="right" class="parametro titulo "><fmt:formatNumber
				maxFractionDigits="2" minFractionDigits="2" groupingUsed="true"
				value="${montoFunerariaNoAmparado}" /> Bs.</div>
			</td>
		</tr>
		<tr class="item" bgcolor="white">
			<td><bean:message key="general.montoExamenesEspeciales"
				bundle="etiquetas" /></td>
			<td>
			<div class="parametro titulo " align="right"><html:text
				styleClass="cgp" style="text-align:right"
				property="montoExamenesEspecialesPresupuestado" onchange="submit();"></html:text>Bs.</div>
			</td>
			<td>
			<div class="parametro titulo " align="right"><html:text
				styleClass="cgp" style="text-align:right" name="globalActionForm"
				property="montoExamenesEspecialesNegociado" onchange="submit();"></html:text>Bs.</div>
			</td>
			<td>
			<div class="parametro titulo " align="right"><html:text
				styleClass="cgp" name="globalActionForm" style="text-align:right"
				property="montoExamenesEspeciales" onchange="submit();"></html:text>Bs.</div>
			</td>
			<td>
			<div align="right" class="parametro titulo "><fmt:formatNumber
				maxFractionDigits="2" minFractionDigits="2" groupingUsed="true"
				value="${montoExamenesEspecialesNoAmparado}" /> Bs.</div>
			</td>
		</tr>

		<tr>
			<td>Total</td>
			<td>
			<div class="parametro titulo ">
			<div align="right"><fmt:formatNumber maxFractionDigits="2"
				minFractionDigits="2" groupingUsed="true"
				value="${montoPresupuestado}" /> Bs.</div>
			</div>
			</td>
			<td>
			<div class="parametro titulo ">
			<div align="right"><fmt:formatNumber maxFractionDigits="2"
				minFractionDigits="2" groupingUsed="true" value="${montoNegociado}" />
			Bs.</div>
			</div>
			</td>
			<td>
			<div class="parametro titulo ">
			<div align="right"><fmt:formatNumber maxFractionDigits="2"
				minFractionDigits="2" groupingUsed="true" value="${montoAmparado}" />
			Bs.</div>
			</div>
			</td>
			<td>
			<div class="parametro titulo " align="right"><fmt:formatNumber
				maxFractionDigits="2" minFractionDigits="2" groupingUsed="true"
				value="${montoNoAmparado}" /> Bs.</div>
			</td>
		</tr>
	</table>
</c:if>
<c:if test="${entorno.detallePresupuestoEdit}">
	<table class="tabla" width="600" cellpadding="2" cellspacing="1"
		border="0">

		<tr class="item" bgcolor="white">
			<td colspan="5" class="textorange" style="padding-bottom: 5px">Detalles
			del Presupuesto</td>
		</tr>
		<tr class="tituloCabecera">

			<td>

			<div align="left">Tipo Gasto <html:hidden
				name="globalActionForm" property="montoGastosPresupuestado"
				value="${montoGastosPresupuestado}"></html:hidden><html:hidden
				name="globalActionForm" property="montoHonorariosPresupuestado"
				value="${montoHonorariosPresupuestado}"></html:hidden></div>
			</td>
			<td>
			<div align="center">Presupuestado</div>
			</td>
			<td>
			<div align="center">Ajustado</div>
			</td>
			<td>
			<div align="center">Amparado</div>
			</td>
			<td>
			<div align="center">No Amparado</div>
			</td>
		</tr>

		<tr class="item" bgcolor="white">
			<td><bean:message key="general.montoHonorariosMedicos"
				bundle="etiquetas" /></td>
			<td>
			<div class="parametro titulo " align="right">
			<div align="right"><fmt:formatNumber maxFractionDigits="2"
				minFractionDigits="2" groupingUsed="true"
				value="${montoHonorariosPresupuestado}" /> Bs.</div>
			</div>
			</td>
			<td>
			<div class="parametro titulo " align="right"><html:text
				styleClass="cgp" style="text-align:right"
				property="montoHonorariosNegociado" onchange="submit();"></html:text>Bs.</div>
			</td>
			<td>
			<div class="parametro titulo " align="right"><html:text
				styleClass="cgp" style="text-align:right"
				property="montoHonorariosMedicos" onchange="submit();"></html:text>Bs.</div>
			</td>
			<td>
			<div align="right" class="parametro titulo ">
			<div align="right"><fmt:formatNumber maxFractionDigits="2"
				minFractionDigits="2" groupingUsed="true"
				value="${montoHonorariosMedicosNoAmparado}" />Bs.</div>
			</div>
			</td>
		</tr>


		<tr class="item" bgcolor="white">
			<td><bean:message key="general.montoGastosClinicos"
				bundle="etiquetas" /></td>
			<td>
			<div class="parametro titulo " align="right">
			<div align="right"><fmt:formatNumber maxFractionDigits="2"
				minFractionDigits="2" groupingUsed="true"
				value="${montoGastosPresupuestado}" /> Bs.</div>
			</div>
			</td>
			<td>
			<div class="parametro titulo " align="right"><html:text
				styleClass="cgp" style="text-align:right" name="globalActionForm"
				property="montoGastosNegociado" onchange="submit();"></html:text>Bs.</div>
			</td>
			<td>
			<div class="parametro titulo " align="right"><html:text
				styleClass="cgp" style="text-align:right" name="globalActionForm"
				property="montoGastosClinicos" onchange="submit();"></html:text>Bs.</div>
			</td>
			<td>
			<div align="right" class="parametro titulo ">
			<div align="right"><fmt:formatNumber maxFractionDigits="2"
				minFractionDigits="2" groupingUsed="true"
				value="${montoGastosClinicosNoAmparado}" /> Bs.</div>
			</div>
			</td>
		</tr>

		<tr>
			<td>Total</td>
			<td>
			<div class="parametro titulo ">

			<div align="right"><fmt:formatNumber maxFractionDigits="2"
				minFractionDigits="2" groupingUsed="true"
				value="${montoPresupuestado}" /> Bs.</div>
			</div>
			</td>
			<td>
			<div class="parametro titulo ">
			<div align="right"><fmt:formatNumber maxFractionDigits="2"
				minFractionDigits="2" groupingUsed="true" value="${montoNegociado}" />
			Bs.</div>
			</div>
			</td>
			<td>
			<div class="parametro titulo ">

			<div align="right"><fmt:formatNumber maxFractionDigits="2"
				minFractionDigits="2" groupingUsed="true" value="${montoAmparado}" />
			Bs.</div>
			</div>
			</td>
			<td>
			<div class="parametro titulo " align="right"><fmt:formatNumber
				maxFractionDigits="2" minFractionDigits="2" groupingUsed="true"
				value="${montoNoAmparado}" /> Bs.</div>
			</td>
		</tr>
	</table>
</c:if>
<c:if test="${entorno.detallePresupuestoEditEmergencia}">
	<table class="tabla" width="600" cellpadding="2" cellspacing="1"
		border="0">
		<html:hidden name="globalActionForm"
			property="montoHonorariosPresupuestado"
			value="${montoHonorariosPresupuestado}"></html:hidden>
		<html:hidden name="globalActionForm"
			property="montoGastosPresupuestado"
			value="${montoGastosPresupuestado}"></html:hidden>
		<html:hidden name="globalActionForm"
			property="montoAmbulanciaPresupuestado"
			value="${montoAmbulanciaPresupuestado}"></html:hidden>
		<html:hidden name="globalActionForm"
			property="montoFunerariaPresupuestado"
			value="${montoFunerariaPresupuestado}"></html:hidden>
		<html:hidden name="globalActionForm"
			property="montoExamenesEspecialesPresupuestado"
			value="${montoExamenesEspecialesPresupuestado}"></html:hidden>

		</div>
		<tr class="tituloCabecera">
			<div class="textorange" style="padding-bottom: 5px">Detalles
			del Presupuesto</div>
			<td>
			<div align="left">Tipo Gasto</div>
			</td>
			<td>
			<div align="center">Presupuestado</div>
			</td>
			<td>
			<div align="center">Ajustado</div>
			</td>
			<td>
			<div align="center">Amparado</div>
			</td>
			<td>
			<div align="center">No Amparado</div>
			</td>
		</tr>

		<tr class="item" bgcolor="white">
			<td><bean:message key="general.montoHonorariosMedicos"
				bundle="etiquetas" /></td>
			<td>
			<div class="parametro titulo " align="right">
			<div align="right"><fmt:formatNumber maxFractionDigits="2"
				minFractionDigits="2" groupingUsed="true"
				value="${montoHonorariosPresupuestado}" /> Bs.</div>
			</div>
			</td>
			<td>
			<div class="parametro titulo " align="right"><html:text
				styleClass="cgp" style="text-align:right"
				property="montoHonorariosNegociado" onchange="submit();"></html:text>Bs.</div>
			</td>
			<td>
			<div class="parametro titulo " align="right"><html:text
				styleClass="cgp" style="text-align:right"
				property="montoHonorariosMedicos" onchange="submit();"></html:text>Bs.</div>
			</td>
			<td>
			<div align="right" class="parametro titulo ">
			<div align="right"><fmt:formatNumber maxFractionDigits="2"
				minFractionDigits="2" groupingUsed="true"
				value="${montoHonorariosMedicosNoAmparado}" />Bs.</div>
			</div>
			</td>
		</tr>


		<tr class="item" bgcolor="white">
			<td><bean:message key="general.montoGastosClinicos"
				bundle="etiquetas" /></td>
			<td>
			<div class="parametro titulo " align="right">
			<div align="right"><fmt:formatNumber maxFractionDigits="2"
				minFractionDigits="2" groupingUsed="true"
				value="${montoGastosPresupuestado}" /> Bs.</div>
			</div>
			</td>
			<td>
			<div class="parametro titulo " align="right"><html:text
				styleClass="cgp" style="text-align:right" name="globalActionForm"
				property="montoGastosNegociado" onchange="submit();"></html:text>Bs.</div>
			</td>
			<td>
			<div class="parametro titulo " align="right"><html:text
				styleClass="cgp" style="text-align:right" name="globalActionForm"
				property="montoGastosClinicos" onchange="submit();"></html:text>Bs.</div>
			</td>
			<td>
			<div align="right" class="parametro titulo ">
			<div align="right"><fmt:formatNumber maxFractionDigits="2"
				minFractionDigits="2" groupingUsed="true"
				value="${montoGastosClinicosNoAmparado}" /> Bs.</div>
			</div>
			</td>
		</tr>
		<tr class="item" bgcolor="white">
			<td><bean:message key="general.montoAmbulancia"
				bundle="etiquetas" /></td>
			<td>
			<div class="parametro titulo " align="right">
			<div align="right"><fmt:formatNumber maxFractionDigits="2"
				minFractionDigits="2" groupingUsed="true"
				value="${montoAmbulanciaPresupuestado}" /> Bs.</div>
			</div>
			</td>
			<td>
			<div class="parametro titulo " align="right"><html:text
				styleClass="cgp" style="text-align:right" name="globalActionForm"
				property="montoAmbulanciaNegociado" onchange="submit();"></html:text>Bs.</div>
			</td>
			<td>
			<div class="parametro titulo " align="right"><html:text
				styleClass="cgp" style="text-align:right" name="globalActionForm"
				property="montoAmbulancia" onchange="submit();"></html:text>Bs.</div>
			</td>
			<td>
			<div align="right" class="parametro titulo ">
			<div align="right"><fmt:formatNumber maxFractionDigits="2"
				minFractionDigits="2" groupingUsed="true"
				value="${montoAmbulanciaNoAmparado}" /> Bs.</div>
			</div>
			</td>
		</tr>
		<tr class="item" bgcolor="white">
			<td><bean:message key="general.montoFuneraria"
				bundle="etiquetas" /></td>
			<td>
			<div class="parametro titulo " align="right">
			<div align="right"><fmt:formatNumber maxFractionDigits="2"
				minFractionDigits="2" groupingUsed="true"
				value="${montoFunerariaPresupuestado}" /> Bs.</div>
			</div>
			</td>
			<td>
			<div class="parametro titulo " align="right"><html:text
				styleClass="cgp" style="text-align:right" name="globalActionForm"
				property="montoFunerariaNegociado" onchange="submit();"></html:text>Bs.</div>
			</td>
			<td>
			<div class="parametro titulo " align="right"><html:text
				styleClass="cgp" style="text-align:right" name="globalActionForm"
				property="montoFuneraria" onchange="submit();"></html:text>Bs.</div>
			</td>
			<td>
			<div align="right" class="parametro titulo ">
			<div align="right"><fmt:formatNumber maxFractionDigits="2"
				minFractionDigits="2" groupingUsed="true"
				value="${montoFunerariaNoAmparado}" /> Bs.</div>
			</div>
			</td>
		</tr>
		<tr class="item" bgcolor="white">
			<td><bean:message key="general.montoExamenesEspeciales"
				bundle="etiquetas" /></td>
			<td>
			<div class="parametro titulo " align="right">
			<div align="right"><fmt:formatNumber maxFractionDigits="2"
				minFractionDigits="2" groupingUsed="true"
				value="${montoExamenesEspecialesPresupuestado}" /> Bs.</div>
			</div>
			</td>
			<td>
			<div class="parametro titulo " align="right"><html:text
				styleClass="cgp" style="text-align:right" name="globalActionForm"
				property="montoExamenesEspecialesNegociado" onchange="submit();"></html:text>Bs.</div>
			</td>
			<td>
			<div class="parametro titulo " align="right"><html:text
				styleClass="cgp" style="text-align:right" name="globalActionForm"
				property="montoExamenesEspeciales" onchange="submit();"></html:text>Bs.</div>
			</td>
			<td>
			<div align="right" class="parametro titulo ">
			<div align="right"><fmt:formatNumber maxFractionDigits="2"
				minFractionDigits="2" groupingUsed="true"
				value="${montoExamenesEspecialesNoAmparado}" /> Bs.</div>
			</div>
			</td>
		</tr>


		<tr>
			<td>Total</td>
			<td>
			<div class="parametro titulo ">

			<div align="right"><fmt:formatNumber maxFractionDigits="2"
				minFractionDigits="2" groupingUsed="true"
				value="${montoPresupuestado}" /> Bs.</div>
			</div>
			</td>
			<td>
			<div class="parametro titulo ">
			<div align="right"><fmt:formatNumber maxFractionDigits="2"
				minFractionDigits="2" groupingUsed="true" value="${montoNegociado}" />
			Bs.</div>
			</div>
			</td>
			<td>
			<div class="parametro titulo ">

			<div align="right"><fmt:formatNumber maxFractionDigits="2"
				minFractionDigits="2" groupingUsed="true" value="${montoAmparado}" />
			Bs.</div>
			</div>
			</td>
			<td>
			<div class="parametro titulo " align="right"><fmt:formatNumber
				maxFractionDigits="2" minFractionDigits="2" groupingUsed="true"
				value="${montoNoAmparado}" /> Bs.</div>
			</td>
		</tr>
	</table>
</c:if>
<c:if test="${entorno.mes}">
	<div class="etiqueta titulo cgp">
		<bean:message key="general.mes" bundle="etiquetas" />
	</div>
	<div class="parametro titulo ">
		<html:select styleClass="cgp" name="globalActionForm" property="mes">
			<html:option value="-1">[SELECCIONAR]</html:option>
			<html:option value="1">
				<c:out value="Enero" />
			</html:option>
			<html:option value="2">
				<c:out value="Febrero" />
			</html:option>
			<html:option value="3">
				<c:out value="Marzo" />
			</html:option>
			<html:option value="4">
				<c:out value="Abril" />
			</html:option>
			<html:option value="5">
				<c:out value="Mayo" />
			</html:option>
			<html:option value="6">
				<c:out value="Junio" />
			</html:option>
			<html:option value="7">
				<c:out value="Julio" />
			</html:option>
			<html:option value="8">
				<c:out value="Agosto" />
			</html:option>
			<html:option value="9">
				<c:out value="Septiembre" />
			</html:option>
			<html:option value="10">
				<c:out value="Octubre" />
			</html:option>
			<html:option value="11">
				<c:out value="Noviembre" />
			</html:option>
			<html:option value="12">
				<c:out value="Diciembre" />
			</html:option>

		</html:select>
	</div>
</c:if>

<c:if test="${entorno.tipoGasto}">
	<div class="etiqueta titulo cgp"><bean:message
		key="general.tipoGasto" bundle="etiquetas" /></div>
	<div class="parametro titulo cgp"><html:select styleClass="cgp"
		property="tipoGasto" onchange="submit();">
		<html:option styleClass="seleccione" value="-1">[SELECCIONAR]</html:option>
		<html:options collection="listTipoGasto" property="id"
			labelProperty="descripcion" />
	</html:select></div>
</c:if>

<c:if test="${entorno.adjuntos}">
	<div class="etiqueta titulo cgp"><bean:message key="general.archivos" bundle="etiquetas" /></div>
	<div class="parametro titulo "><a class="red" href="#"
		onclick="javascript:openWinScroll2('<%=request.getContextPath()%>/security/upload/uploadFile.do?idSiniestro='+document.forms[0].id.value+'&anio=<bean:write name="globalActionForm" property="anioSiniestro"/>' ,'name',500,500);">Ver
	Archivos Anexos</a></div>
</c:if>

<c:if test="${entorno.notaMedica}">
	<div class="etiqueta titulo cgp">Nota Médica</div>
	<div class="parametro titulo "><a class="red" href="#"
		onclick="javascript:openWinScroll2('<%=request.getContextPath()%>/security/notaMedica/addNotaMedica.do?idSiniestro=<bean:write name="globalActionForm" property="id"/>&anioSiniestro=<bean:write name="globalActionForm" property="anioSiniestro"/>','name',500,500);">
	Nota Médica</a></div>
</c:if>
<c:if test="${entorno.verNotaMedica}">
	<div class="etiqueta titulo cgp">Nota Médica</div>
	<div class="parametro titulo "><a class="red" href="#"
		onclick="javascript:openWinScroll2('<%=request.getContextPath()%>/security/notaMedica/verNotaMedica.do?idSiniestro=<bean:write name="globalActionForm" property="id"/>&anio=<bean:write name="globalActionForm" property="anioSiniestro"/>','name',500,500);">
	Nota Médica</a></div>
</c:if>

<c:if test="${entorno.rechazado}">
	<div class="etiqueta titulo cgp">Estatus Rechazado</div>
	<div class="parametro titulo "><html:checkbox property="rechazo">Rechazo</html:checkbox> </div>
</c:if>			
				

<c:if test="${entorno.boton}">
	<div
		style="border-bottom-color: #EEEEEE; border-bottom-width: thin; border-bottom-style: solid">&nbsp;</div>
	<div class="sp5"></div>
	<div align="right"><input class="boton" value="aceptar"	type="submit" /></div>
</c:if>
<script >
	function openWinScroll2(popup_url, name, width, height) {

		var size = ',width=' + width + ',height=' + height;
		var posicion = ",left=" + Math.round(screen.availWidth / 4) + ",top="
				+ Math.round(screen.availHeight / 4);
		var popUp = window
				.open(
						popup_url,
						name,
						'menubar=yes,location=no,toolbar=no,status=no,scrollbars=yes,titlebar=no,directories=no,resizable=0'
								+ size + posicion);
		popUp.opener = self;

	}

	function openWinScrollProv(popup_url, name, width, height) {
		var objtp = document.forms[0].tipoProveedor;

		if (objtp.value == '-1') {
			alert('Seleccione un tipo de proveedor');
		} else {
			openWinScroll2(popup_url, name, width, height);
		}
	}
</script>
<script>
function submit1(cedula){
document.getElementById("id").value=cedula;
if(document.forms[0].tipoProveedor.value=='2'){
	document.forms[0].action='<%=request.getContextPath()%>/security/liquidacion/cuentas.do';
			document.forms[0].submit();
		} else {
			document.getElementById('cuentas').innerHTML = '';
		}
	}


</script>
