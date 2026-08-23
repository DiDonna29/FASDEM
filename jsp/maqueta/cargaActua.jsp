<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html-el.tld" prefix="html" %>
<tiles:insert page="/jsp/plantilla/plantilla.jsp">
	<tiles:put name="titulo"  direct="true">
	    	
    	Detalle del Expediente
    </tiles:put>
	<tiles:put name="items" content="" direct="true"/>
    <tiles:put name="cuerpo" direct="true">

	

<tr>
	<td>
	<div class="line"></div>
		<table width="580" cellpadding="1" cellspacing="1"  >
			<tr height="21">
				<th  class="titulo delineadoBotton" ><bean:message key="general.nroExpediente" bundle="etiquetas" /></th>
				<td class="delineadoBotton">
				231321-545</td>
			</tr>
       		<div class="sp5"></div>
	
			<tr height="21">
				<th  class="titulo delineadoBotton" ><bean:message key="general.fechaRegistro" bundle="etiquetas" /></th>
				<td class="delineadoBotton">
				05/05/2010 </td>
			</tr>
       		<div class="sp5"></div>
			<tr height="21">
				<th  class="titulo delineadoBotton" ><bean:message key="general.fechaActualizacion" bundle="etiquetas" /></th>
				<td class="delineadoBotton">
				05/05/2010 </td>
			</tr>
       		<div class="sp5"></div>
       					<tr height="21">
				<th  class="titulo delineadoBotton" ><bean:message key="general.demandante" bundle="etiquetas" /></th>
				<td class="delineadoBotton">
				Pedro Perez </td>
			</tr>
       		<div class="sp5"></div>
			<tr height="21">
				<th  class="titulo delineadoBotton" ><bean:message key="general.demandado" bundle="etiquetas" /></th>
				<td class="delineadoBotton">
				Andres Perez</td>
			</tr>
       		<div class="sp5"></div>
			<tr height="21">
				<th  class="titulo delineadoBotton" ><bean:message key="general.motivo" bundle="etiquetas" /></th>
				<td class="delineadoBotton">
				Desalojo </td>
			</tr>
       		<div class="sp5"></div>
			<tr height="21">
				<th  class="titulo delineadoBotton" ><bean:message key="general.tipoDependencia" bundle="etiquetas" /></th>
				<td class="delineadoBotton">
				Tribunal/Circuito </td>
			</tr>
       		<div class="sp5"></div>
       					<tr height="21">
				<th  class="titulo delineadoBotton" ><bean:message key="general.dependencia" bundle="etiquetas" /></th>
				<td class="delineadoBotton">
				Tribunal Decimo Tercero de Municipio de la CJ AM de CCS </td>
			</tr>
       		<div class="sp5"></div>
			<tr height="21">
				<th  class="titulo delineadoBotton" ><bean:message key="general.tipoUbicacion" bundle="etiquetas" /></th>
				<td class="delineadoBotton">
				Archivo </td>
			</tr>
       		<div class="sp5"></div>
			<tr height="21">
				<th  class="titulo delineadoBotton" ><bean:message key="general.ubicacion" bundle="etiquetas" /></th>
				<td class="delineadoBotton">
				Remitido a Archivo Judicial </td>
			</tr>
       		<div class="sp5"></div>
			<tr height="21">
				<th  class="titulo delineadoBotton" >- N° de Folio</th>
				<td class="delineadoBotton">
				<input type="text" /></td>
			</tr>
       		<div class="sp5"></div>
			<tr height="21">
				<th  class="titulo delineadoBotton" >- Diligenciante</th>
				<td class="delineadoBotton">
				<input type="text" /></td>
			</tr>			
       		<div class="sp5"></div>
			<tr height="21">
				<th  class="titulo delineadoBotton" >- Actuación</th>
				<td class="delineadoBotton">
				<textarea cols="50" rows="5"></textarea> </td>
			</tr>
       		<div class="sp5"></div>
			<tr align="right" height="21">
				<td align="right" colspan="2" class="delineadoBotton"><html:submit styleClass="boton" value="guardar"/> </td>
			</tr>

	
		</table>
	</td>
</tr>
    </tiles:put>
</tiles:insert>
