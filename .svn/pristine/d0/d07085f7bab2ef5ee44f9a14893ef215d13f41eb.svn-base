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
				<td width="15%">Ingrese Numero de Siniestro a Liquidar</td>
				<td><input readonly="readonly" name="numero_siniestro"
					type="text"></td>
				<td><input readonly="readonly" name="numero_pago" type="text"></td>
				<input type="hidden" name="siniestro"
					value="1"></input>
			</tr>


		</table>
		<c:if test="${siniestro==1}">
		<table class="blackg" width="100%" cellpadding="1" cellspacing="0"
			border="0">
			<tr>
				<td width="15%">Numero de Siniestro</td>
				<td><input readonly="readonly" name="numero_siniestro"
					value="1012-36310" type="text"></td>
				<td><input readonly="readonly" name="numero_pago" value="001"
					type="text"></td>
			</tr>

			<tr>
				<td width="15%">Numero de Factura</td>
				<td><input name="numero_factura" type="text"></td>
			</tr>

			<tr>
				<td width="15%">Monto Factura</td>
				<td><input name="monto_factura" type="text"></td>
			</tr>

			<tr>
				<td width="15%">Monto a Liquidar/Amparado</td>
				<td><input name="monto_amaparado" type="text"></td>
			</tr>

			<tr>
				<td width="15%">Monto Orden</td>
				<td><input readonly="readonly" name="monto_orden" value="3000"
					type="text"></td>
			</tr>

		</table>
		</c:if>
	</tiles:put>
</tiles:insert>
