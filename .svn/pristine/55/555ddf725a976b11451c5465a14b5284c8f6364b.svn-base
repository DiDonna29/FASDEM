<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@page import="ve.gob.dem.framework.recursos.*,java.util.Date,java.text.NumberFormat,java.util.ArrayList,ve.gob.dem.fasdem.bean.*,java.text.SimpleDateFormat"%>
<tiles:insert page="/jsp/plantilla/plantilla.jsp">





	<tiles:put name="titulo" direct="true">
		<bean:message key="principal.titulo" bundle="etiquetas" />
	</tiles:put>

	<tiles:put name="titulopagina" content="Busqueda de Siniestros"
		direct="true" />
	<tiles:put name="itemsup" content="/jsp/comunes/items.jsp" />
    <tiles:put name="itemsdown" content=" " direct="true" />
	<tiles:put name="cuerpo" direct="true">
	<div style="overflow: visible"></div>
	
	
	<%
 String mensaje = (String) request.getAttribute("mensaje");
 if (mensaje != null){%>
 	<script>
 	 	alert('<%=mensaje%>');
 	</script>
 <%}
%>
	
<div style="border-bottom-color: #EEEEEE; border-bottom-width: thin; border-bottom-style: solid">&nbsp;</div>
			
 

				

				
						<input type="hidden" name="idSiniestro" id="idSiniestro"/>
				
				

			
			<table class="tabla" width="600" cellpadding="2" cellspacing="1"
				border="0">
				
				
				
<c:if test="${lista != null}">

				<tr class="tituloCabecera">

					<td>Nro de Siniestro</td>
					<td>Tipo de Siniestro</td>
					<td>Tipo Tramite</td>
					<td>RIF</td>
					<td>Proveedor</td>
				    <td>Estatus</td>
					
				</tr>


		<c:forEach items="${lista}" var="dfp">
				 
				 <tr class="item" bgcolor="white" onclick="goSubmit(<c:out value="${dfp.id_siniestro}"/>, <c:out value="${dfp.anioSiniestro}"/>)">
					<td>
					<c:out value="${dfp.aniomes}${dfp.codigo_siniestro}${dfp.sub_codigo_siniestro}"/>
					</td>
					<td><c:out value="${dfp.tipoSiniestro}"/></td>
					<td><c:out value="${dfp.tipoTramiteSiniestro}"/></td>
					<td><c:out value="${dfp.proveedor.rif}"/></td>
					<td><c:out value="${dfp.proveedor.nombre}"/></td>
					<td><c:out value="${dfp.estatusSiniestro}"/></td>
					
						
					
				</tr>
	
				
	</c:forEach>	


	

	
	
</c:if>

	
		

	
		

	
			</table>
			


			

           
            
	</tiles:put>
	
</tiles:insert>



<script language="JavaScript">
function goSubmit(arg0, arg1){
document.forms[0].action='<%=request.getContextPath()%>/security/administradores/editEstatusSiniestro.do';
document.forms[0].idSiniestro.value=arg0;
document.forms[0].anioSiniestro.value=arg1;
document.forms[0].submit();
}
</script>







	
	









