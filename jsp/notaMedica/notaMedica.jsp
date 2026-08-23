<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic-el.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-html-el.tld" prefix="html"%>
<%@	taglib uri="/WEB-INF/tlds/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ page import="java.util.Date"%>
<%@page contentType="text/html; charset=ISO-8859-1"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<link href="<%=request.getContextPath()%>/css/cssfasdem.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/css.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/jquery.ui.all.css" rel="stylesheet" />
<link href="<%=request.getContextPath()%>/css/demos.css" rel="stylesheet" />
<link href="<%=request.getContextPath()%>/css/jquery-ui-1.8.6.custom.css" rel="stylesheet" />
<script src="<%=request.getContextPath()%>/js/jquery/jquery-1.4.3.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery/ui/jquery.ui.core.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery/ui/jquery.ui.widget.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery/ui/jquery.ui.mouse.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery/ui/jquery.ui.sortable.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery/ui/jquery.ui.accordion.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery/ui/jquery.ui.datepicker.js"></script>
<script src="<%=request.getContextPath()%>/js/popcalendar.js"></script>
<style>
.padeo {
	width: 18%;
	padding-right: 5px;
	padding-left: 5px;
}

.parampadeo {
	width: 79% px;
}
</style>
</head>
<body style="margin: 5px;">
<html:form action="/security/notaMedica/addNotaMedica" method="post">
	<div class="titblue" style="background-color: #EEEEEE; padding-left: 5px; padding-bottom: 2px">Nota Médica</div>
	<div class="s10"></div>
	<div style="width: 490px; border-top: 0px;"><logic:messagesPresent message="true">
		<html:messages id="msg" message="true" bundle="mensajes">
			<div class="nredp" style="margin-left: 5px;">- <bean:write name="msg" /><br />
			</div>
		</html:messages>
		<div class="delineadoBotton"></div>
	</logic:messagesPresent></div>
	<div class="etiqueta padeo">Nota Médica</div>
	<div class="parametro parampadeo"><html:hidden property="idSiniestro" /><html:hidden property="anioSiniestro" /><html:textarea style="width:100%" property="observacion" /></div>
	<div align="right" style="clear: both; width: 100%;padding-right: 5px"><html:submit styleClass="boton">Aceptar</html:submit></div>
	<div class="s10"></div>
	<div style="float: left; clear: both; width: 100%" align="right">
	<table class="tabla" width="100%" cellpadding="2" cellspacing="1" border="0">
		<tr class="tituloCabecera">
			<td align="center" bgcolor="white" class="textorange" colspan="4">Listado de Notas Medicas</td>
		</tr>
		<tr class="tituloCabecera">
			<td><bean:message key="general.fecha" bundle="etiquetas" /></td>
			<td><bean:message key="general.observacion" bundle="etiquetas" /></td>
			<td>Médico</td>
			<td>Imprimir</td>
		</tr>
		<pg:pager maxPageItems="10" maxIndexPages="10" url="addNotaMedica.do" isOffset="false" export="offset,currentPageNumber=pageNumber" scope="request">
			<pg:param name="idSiniestro" />
			<pg:param name="anioSiniestro" />
			<pg:param name="id" />
			<c:forEach items="${listNotaMedica}" var="n">
				<pg:item>
					<tr class="item" bgcolor="white">
						<td><fmt:formatDate value="${n.fecha}" pattern="dd/MM/yyyy hh:mm a" /></td>
						<td><c:out value="${n.observacion}" /></td>
						<td><c:out value="${n.desUsuario}" /></td>
						<td>
						<img src="<%=request.getContextPath()%>/images/printer.png"
							onclick="javascript:openWinScroll2('<%=request.getContextPath()%>/security/comunes/generareporte.do?id=<bean:write
									name='globalActionForm' property='idSiniestro' />&anio=<bean:write
									name='globalActionForm' property='anioSiniestro' />&idNotaMedica=<c:out
									value="${n.id}" />&id_reporte=16','Nota Médica',800,600);"/>
						</td>

						
						
						
						
						
						<td></td>
					</tr>
				</pg:item>
			</c:forEach>
			<pg:index>
				<tr bgcolor="#FFFFFF">
					<td colspan="3" width="100%" align="right" class="textblue"><pg:prev export="pageUrl">
						<img height="11" src="<%=request.getContextPath()%>/images/left1.gif" width="10" align="middle"/>&nbsp; <a
							style="cursor: hand; cursor: pointer;" class="textorange" href="<c:out value='${pageUrl}'/>">Anterior</a>
					</pg:prev>&nbsp; | <pg:pages>
						<c:choose>
							<c:when test='${pageNumber==currentPageNumber}'>
								<c:out value='${pageNumber}' />
							</c:when>
							<c:otherwise>
								<a style="cursor: hand; cursor: pointer;" class="textorange" href="<c:out value='${pageUrl}'/>"> <c:out value="${pageNumber}" /> </a>
							</c:otherwise>
						</c:choose>
					</pg:pages>| <pg:next export="pageUrl">
						<a style="cursor: hand; cursor: pointer;" class="textorange" href="<c:out value="${pageUrl}"/>"> Siguiente</a>&nbsp;<img height="11"
							src="<%=request.getContextPath()%>/images/right1.gif" width="10" align="middle"/>
					</pg:next>&nbsp;</td>
				</tr>
			</pg:index>
		</pg:pager>
	</table>
	</div>
	<div class="s10"></div>
	<%--input id="id_siniestro" type="hidden" name="id" value="<%=request.getAttribute("id")%>"/ --%>
	</div>
</html:form>
</body>
</html>
<script>
function verNota(argIdNota){
	document.location.target='_blank'
	document.location.href = '<%=request.getContextPath()%>/security/notatecnica/viewNotaTecnica.do?idNota=' + argIdNota
}

function openWinScroll2(popup_url, name, width, height) {

	var size = ',width=' + width + ',height=' + height;
	var posicion = ",left=" + Math.round(screen.availWidth / 4) + ",top="
			+ Math.round(screen.availHeight / 4);
	var popUp = window
			.open(
					popup_url,
					name,
					'menubar=yes,location=no,toolbar=no,status=no,scrollbars=no,titlebar=no,directories=no,resizable=no'
							+ size + posicion);
	popUp.opener = self;

}
</script>
