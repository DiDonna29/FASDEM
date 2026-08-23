<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<tiles:insert page="/jsp/plantilla/plantilla.jsp">

	<tiles:put name="titulo" direct="true">
		<bean:message key="principal.titulo" bundle="etiquetas" />
	</tiles:put>

	<tiles:put name="titulopagina" content="Consulta de Usuarios"
		direct="true" />
	<tiles:put name="itemsup" content="/jsp/comunes/items.jsp" />
	<tiles:put name="itemsdown" content=" " direct="true" />
	<tiles:put name="cuerpo" direct="true">
		
			<fmt:formatDate value="${fechaInicio}" pattern="dd/MM/yyyy" />
				
				<div align="right"><input class="boton" value="aceptar"
		 type="button" onclick="javascript:openWinScroll2('<%=request.getContextPath()%>/security/comunes/generareporte.do?id_reporte=8&fechaInicio='+this.document.forms[0].fechaInicio.value+'&fechaFin='+document.forms[0].fechaFin.value,'Usuarios',800,600);" /></div>
	
	</tiles:put>
</tiles:insert>

<script>
	<script language="JavaScript">
	function openWinScroll2(popup_url, name, width, height) {

		var size = ',width=' + width + ',height=' + height;
		var posicion = ",left=" + Math.round(screen.availWidth / 4) + ",top="
				+ Math.round(screen.availHeight / 4);
		var popUp = window
				.open(
						popup_url,
						name,
						'menubar=yes,location=yes,toolbar=no,status=yes,scrollbars=yes,titlebar=no,directories=no,'
								+ size + posicion);
		popUp.opener = self;
	}
</script>

