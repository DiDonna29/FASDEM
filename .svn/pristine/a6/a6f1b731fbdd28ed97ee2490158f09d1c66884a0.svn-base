<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles" %>
<%@taglib uri="/WEB-INF/tlds/struts-logic-el.tld" prefix="logic" %>
<%@taglib uri="/WEB-INF/tlds/struts-html-el.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ page import="java.util.Date"%>
<%@page contentType="text/html; charset=ISO-8859-1" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title><bean:message  key="app.titulo" bundle="etiquetas"/></title>
	<link href="<%=request.getContextPath()%>/css/cssfasdem.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/css/css.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/css/jquery.ui.all.css" rel="stylesheet"/>
	<link href="<%=request.getContextPath()%>/css/demos.css" rel="stylesheet"/>
	<link href="<%=request.getContextPath()%>/css/jquery-ui-1.8.6.custom.css" rel="stylesheet"/>	
	
	<script src="<%=request.getContextPath()%>/js/jquery/jquery-1.4.3.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery/external/jquery.bgiframe-2.1.2.js"></script>	
	<script src="<%=request.getContextPath()%>/js/jquery/ui/jquery.ui.core.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery/ui/jquery.ui.widget.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery/ui/jquery.ui.mouse.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery/ui/jquery.ui.draggable.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery/ui/jquery.ui.position.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery/ui/jquery.ui.resizable.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery/ui/jquery.ui.dialog.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery/ui/jquery.ui.sortable.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery/ui/jquery.ui.accordion.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery/ui/jquery.ui.tabs.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery/ui/jquery.ui.dialog.js"></script>


	<script src="<%=request.getContextPath()%>/js/popcalendar.js"></script>

	<script>
	$(function() {
		$( "#accordion" ).accordion({ navigation: true });
		$( "#tabs" ).tabs();
		$( "#dialog:ui-dialog" ).dialog( "destroy" );
		
		$( "#dialog-modal" ).dialog({
			height: 140,
			modal: true
		});
	});
	</script>
</head>

<body>



<div id="container" class="container">
<div id="dialog-modal" title="Basic modal dialog">
	<p>Adding the modal overlay screen makes the dialog look more prominent because it dims out the page content.</p>
</div>
  <div class="header"> 
    <div  class="imgTsj"><img src="<%=request.getContextPath()%>/images/cbc_02.jpg" width="252" height="34" /></div>
    <div class="cerrarSesion" style="text-align:right"><a  class="textorange" href="http://intranet/inicio.do">cerrar sesi&oacute;n <img src="<%=request.getContextPath()%>/images/door_out.png" width="16" height="16" border="0" /></a></div>
  </div>
  <div style="clear:both"><img src="<%=request.getContextPath()%>/images/cabecera.jpg" width="770" height="44" /></div>
  <div class="header" >
    <div class="cabeceraUsuario whitesp">Bienvenido (a): <c:out value="${usuario.nombre}"/> <c:out value="${usuario.apellido}"/></div>
    <div class="cerrarFecha whitesp"><jsp:useBean id="ahora" class="java.util.Date" /><fmt:formatDate pattern="EEEEEEEEEEE  d 'de' MMMMMMM 'de' yyyy" value="${ahora}"/></div>
  </div>
    <logic:messagesPresent message="true">

                <html:messages id="msg" message="true" bundle="mensajes">
                    <div class="nredp" style="margin-left:5px;">
                        - <bean:write name="msg"/><br />
                    </div>
                 </html:messages>    
                 <div class="delineadoBotton"></div>
    </logic:messagesPresent>
  	<div class="botonera">  
	  	<div class="imagenBotonera" align="center" ><img src="<%=request.getContextPath()%>/images/mv_01.png" width="80" border="0" height="80" /></div>  
		<div style="text-align:center;height:20px;padding-top:15px"><img src="<%=request.getContextPath()%>/images/house.png" width="16" height="16" /><a class="titblue" href="<%=request.getContextPath()%>/">Inicio</a></div>
		<div id="accordion">
		 	<c:forEach items='${usuario.permisos}' var="n">	
				<h3 ><a style="padding-left: 5px;" class="cg" href="#"><c:out value='${n.etiqueta}'/></a></h3>
				<div class="navega" >
						<c:forEach items='${n.hijos}' var="h">
							<p style="margin:0px;padding:0px;"><a style="padding-left: 15px;" class="cgp" href="<%=request.getContextPath()%>/<c:out value='${h.pagina}'/>">-<c:out value='${h.etiqueta}'/></a></p>
						</c:forEach>
				</div>
			</c:forEach>	
		</div>  
	</div>

  <div class="contenido">
  <div class="titblue" style="background-color:#EEEEEE;padding-left:5px;padding-bottom:2px"><tiles:get name='titulopagina'/></div>
  <div class="sp5"></div>
<tiles:get name='itemsup'/>  
<tiles:get name='cuerpo'/>  
<tiles:get name='itemsdown'/>  


  
  </div>

            	<div align="center" class="blackp" style="clear:both;text-align:center;padding-top:5px;">
  					<div class="line">&nbsp;</div>
                    <div class="sp5"></div>
                  	Poder Judicial. Sitio web creado y dise&ntilde;ado por la Direcci&oacute;n 
                  	Ejecutiva de la Magistratura del Tribunal Supremo de Justicia.<br>

                  	Todos los Derechos Reservados. Venezuela <fmt:formatDate pattern="yyyy" value="${ahora}"/>.
                 </div>
  

</div>



</body>
</html>
