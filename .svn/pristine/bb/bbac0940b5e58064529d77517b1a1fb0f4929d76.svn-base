<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="java.io.File"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ page import="java.util.Date"%>
<%@page contentType="text/html; charset=ISO-8859-1"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title><bean:message key="app.titulo" bundle="etiquetas" /></title>
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
<script src="<%=request.getContextPath()%>/js/jquery/ui/jquery.ui.tabs.js"></script>
<script src="<%=request.getContextPath()%>/js/popcalendar.js"></script>
<script src="Scripts/swfobject_modified.js" type="text/javascript"></script>
<script>
	$(function() {
		$( "#accordion" ).accordion({ navigation: true });
		$( "#tabs" ).tabs();
		$( "#dialog" ).dialog();
		
	});
	</script>
	
</head>
<body>

	<%
	Logger log = Logger.getLogger("test.jsp");
	File fichero = new File("c:\\test.txt");
	boolean result =fichero.delete();
      if (result)
	      System.out.println("El fichero ha sido borrado satisfactoriamente");
	      else
	      System.out.println("El fichero no puede ser borrado");
	%>
</body>
</html>
