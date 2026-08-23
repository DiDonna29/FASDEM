<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<tiles:insert page="/jsp/plantilla/plantilla.jsp">

	<tiles:put name="titulo" direct="true">
		<bean:message key="principal.titulo" bundle="etiquetas" />
	</tiles:put>

	<tiles:put name="titulopagina" content="Nuevo Siniestro" direct="true" />

	<tiles:put name="itemsup" content="/jsp/comunes/items.jsp" />
	<tiles:put name="itemsdown" content="" direct="true" />
	<tiles:put name="cuerpo" direct="true">
		<c:if test="${aps!=null}">
	
	<div class="parametro titulo cgp" style="padding-bottom: 5px"><a class="cgp"> El beneficiario tiene:  </a><n><a class="nred"><%=request.getAttribute("aps")%></a><a class="cgp">solicitud(es) de A.P.S. en el mes actual</a></div>
		
		</c:if>
	<input id="aps" type="hidden" name="aps" value="<%=request.getAttribute("aps")%>">
	
	<input id="telefonon" type="hidden" name="telefonon" value="<%=request.getAttribute("telefonon")%>">

	</tiles:put>
</tiles:insert>



