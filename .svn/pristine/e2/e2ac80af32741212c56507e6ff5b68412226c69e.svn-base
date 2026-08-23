<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@page import="ve.gob.dem.framework.recursos.*,java.util.*,java.text.NumberFormat,java.util.ArrayList,ve.gob.dem.fasdem.bean.*,java.text.SimpleDateFormat"%>
<tiles:insert page="/jsp/plantilla/plantilla.jsp">

<%


	ArrayList List=(ArrayList)request.getAttribute("lista");
	String f= (String)request.getAttribute("f_select");	
	String f2= (String)request.getAttribute("f_select2");
	String ana= (String)request.getAttribute("analist");

	String cl=(String)request.getAttribute("clinica");
	SiniestroBandeja list;
	SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
	
	NumberFormat nf = NumberFormat.getInstance();
	nf.setGroupingUsed(true);
	nf.setMaximumFractionDigits(0);
	String dateOut;
    dateOut = Utilidad.DateToString(new Date(), "dd/MM/yyyy-hh:mm a");
		
%>


	<tiles:put name="titulo" direct="true">
		<bean:message key="principal.titulo" bundle="etiquetas" />
	</tiles:put>

	<tiles:put name="titulopagina" content="Detalle de solicitudes Atendidas" direct="true" />
	<tiles:put name="itemsup" content=""  direct="true" />
    <tiles:put name="itemsdown" content=""  direct="true"/>
	<tiles:put name="cuerpo" direct="true">
			<div style="overflow: visible">
			

			<table class="tabla" width="600" cellpadding="2" cellspacing="1"
				border="0">
				
 <% if (List!=null){%>	
				
				<tr class="tituloCabecera">

					<td>Beneficiario</td>
					<td>Datos de la Solicitud</td>
					<td>Fecha Registro</td>
					<td>Observación</td>
					<td>Estatus</td>		
					
					
				</tr>
	
	       <%
			for (int i=0;i!=List.size();i++){
			   list = (SiniestroBandeja) List.get(i);
            %>  
				 
				<tr class="item" bgcolor="white" >
				

				
					<td><%=list.getCrifBeneficiario() + "-" +  list.getCedBeneficiario() + "<br> " + list.getBeneficiario() %> <br> <%="Tlf Contacto:<br> " + list.getTlf()+ "<br><b>Titular: <b>" +  list.getCrifTitular()+ "-" + list.getCedTitular() %></td>
					<td> <strong>Centro de Atención:</strong><br><%="(" + list.getRifClinica() + ")" + " "  + list.getClinica() %><br><br><strong>Causa de Ingreso:</strong><br><%=(list.getCausaIngreso() ==null)?"":list.getCausaIngreso() %> <br><br><strong>Monto: <%=(list.getMonto()==null)?"":list.getMonto() + " Bs." %></strong></td>
					<%if(list.getId_estatus()==0){%>
                        <td class="grayplink"> 
                          <%="<b>Ingresado:</b> <br>"  + Utilidad.DateToString(list.getFechaIngreso(), "dd/MM/yyyy") + "<br>" +  Utilidad.DateToString(list.getHoraIngreso(), "hh:mm a") + "<b><br>Tomado:</b> <br>"  + Utilidad.DateToString(list.getFechaTomado(), "dd/MM/yyyy") + "<br>" +  Utilidad.DateToString(list.getHoraTomado(), "hh:mm a")%>
                        </td>
                        
                         <td class="grayplink"> 
                          <%=(list.getObservacion()!=null)?list.getObservacion():"" %>
                        </td>
                        
						<td valign="middle" align="center"  class="grayplink" style="padding-left: 10px;padding-right: 10px; padding-bottom: 5px;padding-top: 5px;"><b>Solicitud en Atención</b><br></td>
						
						<%}%>
					<%if(list.getId_estatus()==2){%>
						<td class="grayplink"> 
                          <%="<b>Ingresado:</b> <br>"  + Utilidad.DateToString(list.getFechaIngreso(), "dd/MM/yyyy") + "<br>" +  Utilidad.DateToString(list.getHoraIngreso(), "hh:mm a") + "<b><br>Tomado:</b> <br>"  + Utilidad.DateToString(list.getFechaTomado(), "dd/MM/yyyy") + "<br>" +  Utilidad.DateToString(list.getHoraTomado(), "hh:mm a") +   "<br><b>Procesado:</b><br>"  + Utilidad.DateToString(list.getFechaProcesa(), "dd/MM/yyyy") + "<br>" +  Utilidad.DateToString(list.getHoraProcesa(), "hh:mm a")%>
                        </td>
						
						 <td class="grayplink"> 
                          <%=(list.getObservacion()!=null)?list.getObservacion():"" %>
                        </td>
						
						<td valign="middle" align="center"  class="grayplink" style="padding-left: 10px;padding-right: 10px; padding-bottom: 5px;padding-top: 5px;">Solicitud Atendida<br></td>
					
					<%}%>
					
  					
					
				</tr>			
		<%}%>
		
		
		
		<tr>
						<td
							 colspan="10" align="center" style="padding-left: 10px; padding-bottom: 5px; padding-top: 5px;">

						<img
							onclick="javascript:window.history.back();"
							src="<%=request.getContextPath()%>/images/b_regresar.gif"
							width="52" height="11">
						
							
						
						</td>
					</tr>
		
		
		 <%}else{%>   
    
    	
    
    		<tr> 
                  <td width="1%"></td>
                  <td width="95%" valign="top"> 
                  
                  <table width="100%" border="0" cellspacing="3" cellpadding="2">
                      
                   <tr>
						<td align="center" class="grayp" style="padding-left: 20px; padding-bottom: 5px;">
							<div class="grayp"></div>
							<strong>No existen solicitudes pendientes por atención</strong>
							<div class="grayp"></div>
						</td>
					</tr>
					
					
					<tr>
						<td
							 colspan="10" align="center" style="padding-left: 10px; padding-bottom: 5px; padding-top: 5px;">

						<img
							onclick="javascript:window.history.back();"
							src="<%=request.getContextPath()%>/images/b_regresar.gif"
							width="52" height="11">
						
							
						
						</td>
					</tr>
                   
                   
                  </table>
                    
                  </td>
                </tr>
    
    <%}%>    
		
		
	
			</table>


			</div>
			
			<input id="accion" type="hidden" name="accion" value="">
            <input id="siniestro" type="hidden" name="siniestro" value="">
            
            
	</tiles:put>
	
</tiles:insert>



<script language="JavaScript">
	function ir(acc,valor){
    
		if(confirm('¿Esta seguro que desea realizar esta operación?')){
			document.getElementById('accion').value=acc;
			document.getElementById('siniestro').value=valor;
			document.forms[0].submit();
		}
			
	}

	function ir1(acc,valor){
	    
		
			document.getElementById('accion').value=acc;
			document.getElementById('siniestro').value=valor;
			document.forms[0].submit();
		
			
	}


</script>





