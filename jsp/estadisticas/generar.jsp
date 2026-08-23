<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@	taglib uri="/WEB-INF/tlds/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@page import="ve.gob.dem.framework.recursos.*,java.util.Date,java.text.NumberFormat,java.util.ArrayList,ve.gob.dem.fasdem.bean.*,java.text.SimpleDateFormat"%>
<%@ include file="/jsp/estadisticas/import.jsp"%>

<script type="text/javascript">
	var ruta = "<%=request.getContextPath() %>";
	//alert("Ruta:" + ruta);
</script>

<!-- Código de la página -->
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/WEB-INF/recursos/js/estadistica.js" charset="UTF-8"></script> --%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/estadistica.js" charset="UTF-8"></script>

<tiles:insert page="/jsp/plantilla/plantilla.jsp">
	<tiles:put name="titulopagina" content="Reporte estadísticas" direct="true" />
	<tiles:put name="itemsup" content="/jsp/comunes/items.jsp" />
    <tiles:put name="itemsdown" content=" " direct="true" />
	<tiles:put name="cuerpo" direct="true">
	
		<form class="form-horizontal" id="datos">
				<div style="position:relative;left:22px; top:50px">
					<table >
							<tr>
								<td VALIGN="MIDDLE" ALIGN="CENTER">
									<div style="position:relative;left:22px; top:-09px">
										<label class="control-label" for="rf1txt" style="width:105px;">Fecha :</label>
									</div>
								</td>
								<td VALIGN="MIDDLE" ALIGN="CENTER">
									<div class="control-group">
										<div class="controls" >
											<div class="input-append locked" style="width:105px;">
								        		<input id="rf1txt" name="rf1txt" type="text" style="width:175px; height: 30px;" maxlength="23" readonly></input>
								            	<span id="btncalendario1" title="Calendario" class="add-on btn"><i class="icon-calendar"></i></span>
								                <span title="Borrar campo" id="rf1sup" class="add-on btn"><i class="icon-remove"></i></span>
											</div>
										</div>
									</div>	
								</td>
							</tr>
							<tr>
								<td colspan= "2">
						            <div style="position:relative;left:60px;">
										<button type="button" class="btn btn-primary" style="margin-left:110px;" id="Imprimir"><i class="icon-print"></i> Imprimir</button>
									</div>
								</td>						
							</tr>
					</table>
				</div>		
		</form>
	</tiles:put>
</tiles:insert>





