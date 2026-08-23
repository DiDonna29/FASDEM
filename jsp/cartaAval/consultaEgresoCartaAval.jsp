<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html-el.tld" prefix="html"%>
<%@	taglib uri="/WEB-INF/tlds/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>

<tiles:insert page="/jsp/plantilla/plantilla.jsp">

	<tiles:put name="titulo" direct="true">
		<bean:message key="principal.titulo" bundle="etiquetas" />
		<input type="hidden" name="accion" value=""/>
	</tiles:put>
	
	<tiles:put name="titulopagina" content="Inicio / Carta Aval / Búsqueda de Siniestros"
		direct="true" />
	<tiles:put name="itemsdown" content="" direct="true"/>
    <tiles:put name="itemsup" content="/jsp/comunes/items.jsp"  />
    
    
	<tiles:put name="cuerpo" direct="true">
		
	
	
	
	<c:if test="${listado!=null}">
	<div class="textorange" style="padding-bottom: 5px">Listado de
			Siniestros</div>
	  <div style="overflow: visible">
	    <table class="tabla"  cellpadding="2" cellspacing="1"
				border="0" bgcolor="yellow">
          <tr class="tituloCabecera">
            <td>Código</td>
            <td>Cédula</td>
            <td>Nombres</td>
            <td>Apellidos</td>
            <td>Fecha Notif.</td>
            <td>Tipo Siniestro</td>
            <td>Monto</td>
            <td>Editar</td>
            <td>Anular</td>
          </tr>
          <pg:pager maxPageItems="<%=20%>" maxIndexPages="10"
					url="buscarSiniestro.do" isOffset="false"
					export="offset,currentPageNumber=pageNumber" scope="request">
            <pg:param name="cedula" />
            <pg:param name="nombres" />
            <pg:param name="codigo" />
            <c:forEach items="${listado}" var="sini">
              <pg:item>
                <tr class="item" bgcolor="white">
                  <td><input type="hidden" id="anioSiniestro"
									name="anioSiniestro" />
                      <c:if test="${sini.numero!=null}">
                        <c:out value="${sini.numero}" />
                      </c:if>
                      <c:if test="${sini.aniomesCodigo!=null}">
                        <c:out value="${sini.aniomesCodigo}" />
                        -
                        <c:out value="${sini.codigo}" />
                        -
                        <c:out value="${sini.subCodigo}" />
                      </c:if>
                      <input type="hidden" id="idSini"
									name="idSini" /></td>
                  <td><fmt:formatNumber value="${sini.cedulaBeneficiario}" groupingUsed="true" /></td>
                  <td><c:out value="${sini.nombresBeneficiario}"/></td>
                  <td><c:out value="${sini.apellidosBeneficiario}"/></td>
                  <td><fmt:formatDate pattern="dd/MM/yyyy"
									value="${sini.fechaNotificacion}" /></td>
                  <td><c:if test="${sini.lsTipoSiniestro!=null}">
                      <c:out value="${sini.lsTipoSiniestro}" />
                    </c:if>
                      <c:if test="${sini.tipoSiniestro!=null}">
                        <c:out value="${sini.tipoSiniestro.descripcion}" />
                      </c:if>
                  </td>
                  <td><c:if test="${sini.lsMontoAmparado !='0.0'}">
                      <c:out value="${sini.lsMontoAmparado}" />
                    </c:if>
                      <c:if test="${sini.montoAmparado!=null}">
                        <c:out value="${sini.montoAmparado}" />
                      </c:if>
                      <input
									type="hidden" name="id"
									value="<c:out value="${sini.aniomesCodigo}" />
                    <c:out value="${sini.codigo}" />
                    <c:out value="${sini.subCodigo}" />
                    " /></td>
                  <td><div align="center"><img  src="<%=request.getContextPath()%>/images/ic3d_enviado.gif"onclick="javaScript:submit2('<c:out value="${sini.id}" />','<c:out value="${sini.anioSiniestro}" />','buscar')"/></div></td>
                  <td><div align="center"><img  src="<%=request.getContextPath()%>/images/cross.png"onclick="javaScript:submit2('<c:out value="${sini.id}" />','<c:out value="${sini.anioSiniestro}" />','anular')"/></div></td>
                </tr>
              </pg:item>
            </c:forEach>
            <pg:index>
              <tr bgcolor="#FFFFFF">
                <td colspan="9" width="100%" align="right" class="textblue"><pg:prev export="pageUrl"> <img height="11"
									src="<%=request.getContextPath()%>/images/left1.gif"
									width="10" align="middle"/>&nbsp; <a style="cursor: hand; cursor: pointer;" class="textorange"
									href="<c:out value='${pageUrl}'/>">Anterior</a> </pg:prev>
                  &nbsp; |
                  <pg:pages>
                    <c:choose>
                      <c:when test="${pageNumber==currentPageNumber}">
                        <c:out value="${pageNumber}" />
                      </c:when>
                      <c:otherwise> <a style="cursor: hand; cursor: pointer;" class="textorange"
											href="<c:out value='${pageUrl}'/>">
                        <c:out
											value="${pageNumber}" />
                      </a> </c:otherwise>
                    </c:choose>
                  </pg:pages>
                  |
                  <pg:next export="pageUrl"> <a style="cursor: hand; cursor: pointer;" class="textorange"
									href="<c:out value="${pageurl}"/>"> Siguiente&nbsp;<img
									height="11"
									src="<%=request.getContextPath()%>/images/right1.gif"
									width="10" align="middle"/> </pg:next>
                  &nbsp;</td>
              </tr>
            </pg:index>
          </pg:pager>
        </table>
	  </div>
	</c:if>
	</tiles:put>
</tiles:insert>
<script>

function submit2(argid,anio,accion){
	document.forms[0].accion.value=accion;
	document.getElementById("idSini").value=argid;
	document.getElementById("anioSiniestro").value=anio;
	document.forms[0].action='<%=request.getContextPath()%>/security/cartaAval/egresar/buscarSiniestro.do';
	if(accion=='anular')
	{
		var answer = confirm ("¿Está seguro de anular la carta aval?");
		if (answer){	
			document.forms[0].submit();		
		}	
	}else
	{
			document.forms[0].submit();		
	}
	
}
</script>

