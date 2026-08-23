<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
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
				<td colspan="2">
				<div class="line" />
				</td>
			</tr>

			<tr>
				<td colspan="2">&nbsp;</td>
			</tr>
<td align="center" class=textblue colspan="2"><input
					type="hidden" name="nodo" value="1"></td>
<tr>
				<td width="29%">Numero de Siniestro</td>
				<td width="71%"><input name="siniestro" type="text">
			    <input type="button" value="Buscar" onclick="javaScript:document.forms.action='<%=request.getContextPath()%>/security/pago/consulta_pago.do?fromlist=true';document.forms.submit();"  /></td>
						
			</tr>
		
			

		</table>
	</tiles:put>
</tiles:insert>
