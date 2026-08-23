<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<tiles:insert page="/jsp/plantilla/plantilla.jsp">

	<tiles:put name="titulo" direct="true">
		<bean:message key="principal.titulo" bundle="etiquetas" />
	</tiles:put>

	<tiles:put name="titulopagina" content="Seleccione un Tratamiento"
		direct="true" />
	<tiles:put name="itemsup" content="/jsp/comunes/items.jsp" />
    <tiles:put name="itemsdown" content=" " direct="true" />
	<tiles:put name="cuerpo" direct="true">
	<div style="overflow: visible">
			</div>
		   
				<tr class="cgp" bgcolor="white">
				<input  class="textbox" title="descripcion" value=" " property = "descripcion"
		type="text" />
	<div class="sp5"></div><input
						type="hidden" name="formulario"/>
	<div align="left"><input class="boton" value="Agregar" onclick="Submit2();"
		type="submit" /></div></td>

				</tr>		
			
			
	</tiles:put>
	
</tiles:insert>

<script>
function Submit2(){
	document.forms[0].action='<%=request.getContextPath()%><c:out value='${myhref}'/>';
	document.forms[0].formulario.value='Agregar';
	document.forms[0].submit();
}
</script>