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
				<td width="15%">Número de Siniestro</td>
				<td>><input readonly="readonly" name="siniestro" type="text"></td>
			</tr>
			
			<tr>
				<td width="15%">Fecha de Registro</td>
				<td>><input readonly="readonly" name="siniestro" type="text"></td>
			</tr>
			
			<tr>
				<td width="15%">Estatus</td>
				<td>><input readonly="readonly" name="siniestro" value="Egresado" type="text"></td>
			</tr>
			
			<tr>
				<td width="15%">Tratamiento</td>
				<td><input readonly="readonly" value="Cirugia Ambulatoria, Catarata, Oftalmología, Ojo." name="siniestro" type="text">
				</td>
			</tr>
			
			
			<tr>
				<td width="15%">Tipo de Enfermedad</td>
				<td><input readonly="readonly" name="siniestro" value="Aguda" type="text"></td>
			</tr>
			
			<tr>
				<td width="15%">Descripción de Medicamentos</td>
				<td>
				<textarea readonly="readonly" name="observaciones">
				Código Convenio N°:999670
				
				Se indica tratamiento médico para enfermedad aguda, según recipes e indicaciones:
				1.- Gotas.
				
				Esta orden es válida por (5) días hábiles hasta un monto máximo de Bs.F.500,00.
								
				</textarea></td>
			</tr>


		</table>
	</tiles:put>
</tiles:insert>

