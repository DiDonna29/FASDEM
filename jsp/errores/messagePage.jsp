<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<tiles:insert page="/jsp/plantilla/plantilla.jsp">

	<tiles:put name="titulo"  direct="true">
    	<bean:message key="principal.titulo" bundle="etiquetas" />
    </tiles:put>
   <tiles:put name="imgtitulo"   content="/images/user.png" direct="true"/>
    <tiles:put name="view" content=" " direct="true"/>
	<tiles:put name="items" content="" />
    <tiles:put name="cuerpo" direct="true">
		<table class="blackg"  width="100%" cellpadding="1" cellspacing="0" border="0">    
    	<tr>
			<td colspan="2"><div class="line" /></td>
		</tr>
        <tr class="black"  height="255">
        	<td valign="bottom" class="titlenblackg" align="center" style="padding:5px;">
            	<strong>
            	<bean:message key="app.titulo" bundle="etiquetas"/><br/> <c:out value="${usuario.dependencia.descripcion}"/><br></br> 
            	</strong>
            </td>
		</tr>
        </table>
    </tiles:put>
</tiles:insert>
