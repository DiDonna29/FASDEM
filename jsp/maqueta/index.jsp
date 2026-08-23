<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html-el.tld" prefix="html" %>
<tiles:insert page="/jsp/plantilla/plantilla.jsp">
	<tiles:put name="titulo"  direct="true">
	    	
    	<bean:message key="principal.titulo" bundle="etiquetas" />    	
    </tiles:put>
	<tiles:put name="items" content="/jsp/comunes/items.jsp" />
    <tiles:put name="cuerpo" direct="true">

	
<c:if test="${enviroment.list}">
<tr>
	<td  class="blackg" align="center">
		Listado de Expedientes
	</td>
</tr>
<tr>
	<td>
		<table width="580" cellpadding="1" cellspacing="1" bgcolor="#CCCCCC" >
			<tr bgcolor="#ebf0f8"  class="blackg">
				<td align="center"><strong  style="padding-left:5px;">N° Expediente</strong></td>
				<td align="center"><strong  style="padding-left:5px;">Demandante</strong></td>
				<td align="center"><strong  style="padding-left:5px;">Demandado</strong></td>
				<td align="center"><strong  style="padding-left:5px;">Motivo</strong></td>
				<td align="center"><strong  style="padding-left:5px;">Dependencia</strong></td>
				<td align="center"><strong  style="padding-left:5px;">Ubicación</strong></td>
				<td align="center">&nbsp;</td>
			</tr>
			<tr bgcolor="#FFFFFF"  class="blackg">
				<td align="center">e</td>
				<td align="center">d</td>
				<td align="center">d</td>
				<td align="center">m</td>
				<td align="center">d</td>
				<td align="center">u</td>
				<td align="center"><a href="<%=request.getContextPath()%>/<c:out value='${enviroment.ruta}'/>"/><img  border="0" src="<%=request.getContextPath()%>/images/zoom.png"></a></td>
			</tr>
	
		</table>
	</td>
</tr>
</c:if>


<c:if test="${enviroment.id==5}">
<tr>
	<td  class="blackg" align="center">
		Listado de Expedientes
	</td>
</tr>
<tr>
	<td>
		<table width="580" cellpadding="1" cellspacing="1" bgcolor="#CCCCCC" >
			<tr bgcolor="#ebf0f8"  class="blackg">
				<td align="center"><strong  style="padding-left:5px;">N° Expediente</strong></td>
				<td align="center"><strong  style="padding-left:5px;">Demandante</strong></td>
				<td align="center"><strong  style="padding-left:5px;">Demandado</strong></td>
				<td align="center"><strong  style="padding-left:5px;">Motivo</strong></td>
				<td align="center"><strong  style="padding-left:5px;">Dependencia</strong></td>
				<td align="center"><strong  style="padding-left:5px;">Ubicación</strong></td>
				<td align="center">&nbsp;</td>
			</tr>
			<tr bgcolor="#FFFFFF"  class="blackg">
				<td align="center">e</td>
				<td align="center">d</td>
				<td align="center">d</td>
				<td align="center">m</td>
				<td align="center">d</td>
				<td align="center">u</td>
				<td align="center"><input type="checkbox" /></td>
			</tr>
			<tr bgcolor="#FFFFFF"  class="blackg">
				<td align="center">e</td>
				<td align="center">d</td>
				<td align="center">d</td>
				<td align="center">m</td>
				<td align="center">d</td>
				<td align="center">u</td>
				<td align="center"><input type="checkbox" /></td>
			</tr>
			<tr bgcolor="#FFFFFF"  class="blackg">
				<td align="center">e</td>
				<td align="center">d</td>
				<td align="center">d</td>
				<td align="center">m</td>
				<td align="center">d</td>
				<td align="center">u</td>
				<td align="center"><input type="checkbox" /></td>
			</tr>
			<tr bgcolor="#FFFFFF"  class="blackg">
				<td align="center">e</td>
				<td align="center">d</td>
				<td align="center">d</td>
				<td align="center">m</td>
				<td align="center">d</td>
				<td align="center">u</td>
				<td align="center"><input type="checkbox" /></td>
			</tr>									
				<tr  bgcolor="#FFFFFF"  class="blackg">
				<td align="right" colspan="2">
				Urgente <input  type="checkbox" /> 
				</td>
				<td align="right" colspan="5">
				<html:submit styleClass="boton" value="solicitar"/>
				</td>				
			</tr>
		</table>
	</td>
</tr>
</c:if>

<c:if test="${enviroment.id==12}">
<tr>
	<td  class="blackg" align="center">
		<strong>Listado de Expedientes Solicitados</strong><br></br>
	</td>
</tr>
<tr>
	<td>
		<table width="580" cellpadding="1" cellspacing="1" bgcolor="#CCCCCC" >
			<tr bgcolor="#ebf0f8"  class="blackg">
				<td align="center"><strong  style="padding-left:5px;">N° Expediente</strong></td>
				<td align="center"><strong  style="padding-left:5px;">Demandante</strong></td>
				<td align="center"><strong  style="padding-left:5px;">Demandado</strong></td>
				<td align="center"><strong  style="padding-left:5px;">Motivo</strong></td>
				<td align="center"><strong  style="padding-left:5px;">Dependencia</strong></td>
				<td align="center"><strong  style="padding-left:5px;">Ubicación</strong></td>
				<td align="center"><strong  style="padding-left:5px;">Secretario</strong></td>
				<td align="center"><strong  style="padding-left:5px;">Tipo</strong></td>
				<td align="center">&nbsp;</td>
			</tr>
			<tr bgcolor="#FFFFFF"  class="blackg">
				<td align="center">e</td>
				<td align="center">d</td>
				<td align="center">d</td>
				<td align="center">m</td>
				<td align="center">d</td>
				<td align="center">u</td>
				<td align="center">s</td>
				<td align="center" class="nredp"><blink>Urgente</blink></td>
				<td align="center"><input type="checkbox" /></td>
			</tr>
			<tr bgcolor="#FFFFFF"  class="blackg">
				<td align="center">e</td>
				<td align="center">d</td>
				<td align="center">d</td>
				<td align="center">m</td>
				<td align="center">d</td>
				<td align="center">u</td>
				<td align="center">s</td>
				<td align="center" class="nredp"><blink>Urgente</blink></td>
				<td align="center"><input type="checkbox" /></td>
			</tr>
			<tr bgcolor="#FFFFFF"  class="blackg">
				<td align="center">e</td>
				<td align="center">d</td>
				<td align="center">d</td>
				<td align="center">m</td>
				<td align="center">d</td>
				<td align="center">u</td>
				<td align="center">s</td>
				<td align="center"></td>
				<td align="center"><input type="checkbox" /></td>
			</tr>
			<tr bgcolor="#FFFFFF"  class="blackg">
				<td align="center">e</td>
				<td align="center">d</td>
				<td align="center">d</td>
				<td align="center">m</td>
				<td align="center">d</td>
				<td align="center">u</td>
				<td align="center">s</td>
				<td align="center"></td>
				<td align="center"><input type="checkbox" /></td>
			</tr>									
				<tr  bgcolor="#FFFFFF"  class="blackg">
				<td align="right" colspan="9">
				<html:submit styleClass="boton" value="imprimir"/>
				</td>				
			</tr>
		</table>
	</td>
</tr>
</c:if>
    </tiles:put>
</tiles:insert>
