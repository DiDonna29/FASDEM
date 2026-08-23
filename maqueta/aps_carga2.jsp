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
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td colspan="2">
				<div class="line" />
				</td>
			</tr>
			<tr>
				<td colspan="2">
				<div class="line" />
				</td>
			</tr>
			<tr>
				<td colspan="2">Datos del Titular</td>
			</tr>
			<tr>
				<td colspan="2">
				<div class="line" />
				</td>
			</tr>			
			<tr>

				<td bgcolor="black" colspan="2" height="1"></td>
			</tr>
			<tr>
				<td colspan="2">
				<div class="line" />
				</td>
			</tr>	
			<tr>
				<td width="15%">Cédula</td>
				<td onclick="javaScript:document.location='<%=request.getContextPath()%>/maqueta/aps_carga3.jsp'">12345678</td>
			</tr>
			<tr>
				<td width="15%">Nombres</td>
				<td >Alejandro José</td>
			</tr>
			<tr>
				<td width="15%">Apellidos</td>
				<td>Perez Prado</td>
			</tr>
			<tr>
				<td colspan="2">
				<div class="line" />
				</td>
			</tr>
			<tr>
				<td colspan="2">
				<div class="line" />
				</td>
			</tr>
			<tr>
				<td colspan="2">Datos de los Beneficiarios</td>
			</tr>
			<tr>
				<td colspan="2">
				<div class="line" />
				</td>
			</tr>				
			<tr>

				<td bgcolor="black" colspan="2" height="1"></td>
			</tr>	
			
						<tr>
				<td colspan="2">
				<div class="line" />
				</td>
			</tr>
			
			<tr>
				<td width="15%">Cédula</td>
				<td>12345678</td>
			</tr>
			<tr>
				<td width="15%">Nombres</td>
				<td>Alexandra Maria</td>
			</tr>
			<tr>
				<td width="15%">Apellidos</td>
				<td>Rangel Diaz</td>
			</tr>	
			<tr>
				<td width="15%">Parentesco</td>
				<td>Esposa</td>
			</tr>
			
						<tr>
				<td colspan="2">
				<div class="line" />
				</td>
			</tr>
			
			<tr>
				<td width="15%">Cédula</td>
				<td>No Posee</td>
			</tr>
			<tr>
				<td width="15%">Nombres</td>
				<td>Luis Alejandro</td>
			</tr>
			<tr>
				<td width="15%">Apellidos</td>
				<td>Perez Rangel</td>
			</tr>	
			<tr>
				<td width="15%">Parentesco</td>
				<td>Hijo</td>
			</tr>			
		</table>
	</tiles:put>
</tiles:insert>
