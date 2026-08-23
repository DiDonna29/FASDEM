<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<tiles:insert page="/jsp/plantilla/plantilla.jsp">

	<tiles:put name="titulo" direct="true">
		<bean:message key="principal.titulo" bundle="etiquetas" />
	</tiles:put>
	<tiles:put name="imgtitulo" content="/images/user.png" direct="true" />
	<tiles:put name="view" content=" " direct="true" />
	<tiles:put name="items" content="" />
	<tiles:put name="cuerpo" direct="true">
		<table class="blackg" width="100%" cellpadding="1" cellspacing="0"
			border="0">
			<tr>
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr>
				<td align="center" class=textblue colspan="2"><input
					type="hidden" name="nodo" value="1"></td>
			</tr>
			<tr>
				<td colspan="2">
				<div class="line" />
				</td>
			</tr>

			<tr>
				<td width="15%">Organo</td>
				<td><select name="organo" id="organo">
					<option value=1>Ojo</option>
				</select></td>
			</tr>
			<tr>
				<td width="15%">Sistema</td>
				<td><select name="sistema" id="sistema">
					<option value=1>Oftalmología</option>
				</select></td>
			</tr>
			<tr>
				<td width="15%">Enfermedad</td>
				<td><select name="enfermedad" id="enfermedad">
					<option value=1>cataratas</option>
				</select></td>
			</tr>
			<tr>
				<td width="15%">Tratamiento</td>
				<td><select name="tratamiento" id="tratamiento">
					<option value=1>Cirugia Ambulatoria</option>
				</select></td>
			</tr>


			<tr>
				<td width="15%">Tipo de Enfermedad</td>
				<td><select name="tipo_enfermedad" id="tipo_enfermedad">
					<option value=1>Aguda</option>
					<option value=2>Crónica</option>
				</select></td>
			</tr>

			<tr>
				<td width="15%">Descripción de Medicamentos</td>
				<td><c:if test="${tipo_enfermedad==2}">
					<textarea name="observaciones">
				Código Convenio N°:999670
				
				Se indica tratamiento médico para enfermedad aguda, según recipes e indicaciones:
				1.- 
				2.- 
				3.- 
				4.- 
				5.- 
				
				Esta orden es válida por (5) días hábiles hasta un monto máximo de Bs.F.500,00.
								
				</textarea>
				</c:if> <c:if test="${tipo_enfermedad==3}||${tipo_enfermedad==4}">
					<textarea name="observaciones">
				
				Código Convenio N°:999670
				
				Se indica tratamiento médico para enfermedad crónica, según recipes e indicaciones:
				1.- 
				2.- 
				3.- 
				4.- 
				5.- 
				
				Esta orden es válida por (5) días hábiles hasta un monto máximo de Bs.F.3000,00. Se autoriza hasta 2 meses el tratamiento.
								
				</textarea>
				</c:if></td>
			</tr>


		</table>
	</tiles:put>
</tiles:insert>
