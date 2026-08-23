<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<tiles:insert page="/jsp/plantilla/plantilla.jsp">

	<tiles:put name="titulo" direct="true">
		<bean:message key="principal.titulo" bundle="etiquetas" />
	</tiles:put>

	<tiles:put name="titulopagina" content="Fondo Autoadministrado de Salud de la DEM" direct="true" />
	<tiles:put name="itemsup" content=" " direct="true" />
	<tiles:put name="itemsdown" content=" " direct="true" />
	<tiles:put name="cuerpo" direct="true">
		<div class="textblue corner-rounded" style="padding:20px; margin: 60px;text-align: justify;background-color: #EEEEEE">
		<p align="center"><strong>EL SISTEMA DE GESTIÓN DEL FONDO AUTOADMINISTRADO DE SALUD (FASDEM)</strong> </p>
		<p>Es una herramienta tecnológica diseñada como apoyo a los procesos y servicios de administrar de forma directa los beneficios de salud y vida de todos los trabajadores del PODER JUDICIAL y su grupo familiar.</p>
		</div>
		<div class="textblue corner-rounded" style="padding:20px; margin: 60px;text-align: justify;">
		<p align="center"><a class="textblue" href="<%=request.getContextPath()%>/pdf/guia_uso.pdf" target="_BLANK"><img border="0" src="<%=request.getContextPath()%>/images/manual_usuario2.png"/><br> Guia de Uso</a></p>
		</div>
	</tiles:put>
</tiles:insert>
