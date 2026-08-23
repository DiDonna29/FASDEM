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
				<td
					onclick="javaScript:document.location='<%=request.getContextPath()%>/maqueta/aps_carga3.jsp'">12345678</td>
			</tr>
			<tr>
				<td width="15%">Nombres</td>
				<td>Alejandro José</td>
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
				<td colspan="2">
				<div class="line" />
				</td>
			</tr>
			<tr>
				<td colspan="2">Siniestros Anteriores</td>
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
				<td colspan="2">
				<div class="line" />
				</td>
			</tr>
									<tr>
				<td colspan="2" align="center">
				Listado de Siniestro
				</td>
			</tr>
			<tr>
				<td colspan="2" >
				<table width="100%" cellspacing="1" cellpadding="1" bgcolor="black">
					<tr>
						<td bgcolor="white">Nro</td>
						<td bgcolor="white">Especialidad</td>
						<td bgcolor="white">Patología</td>
						<td bgcolor="white">Tratamiento</td>
						<td bgcolor="white">Tipo de Tramite</td>
						<td bgcolor="white">Fecha Ocurrencia</td>
						<td bgcolor="white">Asociar</td>
					</tr>
					<tr>
						<td bgcolor="white">1012-36301</td>
						<td bgcolor="white">Gastroenterología</td>
						<td bgcolor="white">Gastritis</td>
						<td bgcolor="white">Medicinas</td>
						<td bgcolor="white">Aps</td>
						<td bgcolor="white">12/08/2010</td>
						<td bgcolor="white"><input type="radio"/></td>
					</tr>	
					<tr>
						<td bgcolor="white">1012-36301</td>
						<td bgcolor="white">Simulado</td>
						<td bgcolor="white">Simulado</td>
						<td bgcolor="white">Simulado</td>
						<td bgcolor="white">Simulado</td>
						<td bgcolor="white">12/08/2010</td>
						<td bgcolor="white"><input type="radio"/></td>
					</tr>
					<tr>
						<td bgcolor="white">1012-36301</td>
						<td bgcolor="white">Simulado</td>
						<td bgcolor="white">Simulado</td>
						<td bgcolor="white">Simulado</td>
						<td bgcolor="white">Simulado</td>
						<td bgcolor="white">12/08/2010</td>
						<td bgcolor="white"><input onclick="javaScript:document.location='<%=request.getContextPath()%>/maqueta/aps_carga4.jsp'" type="radio"/></td>
					</tr>	
					<tr>
						<td align="center" colspan="7" bgcolor="white"><input onclick="javaScript:document.location='<%=request.getContextPath()%>/maqueta/aps_carga4.jsp'" type="button" value="Cargar Nuevo Aps"/></td>
					</tr>	
				</table>
			</td>
			</tr>


		</table>
	</tiles:put>
</tiles:insert>
