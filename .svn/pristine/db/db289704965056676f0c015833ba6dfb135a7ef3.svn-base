<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles" %>
<%@taglib uri="/WEB-INF/tlds/struts-logic-el.tld" prefix="logic" %>
<%@taglib uri="/WEB-INF/tlds/struts-html-el.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ page import="java.util.Date"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<%=request.getContextPath()%>/style/css.css" rel="stylesheet" type="text/css"/>
<script language="JavaScript" src="<%=request.getContextPath()%>/js/popcalendar.js"></script>
<script language="JavaScript" src="<%=request.getContextPath()%>/js/js.js"></script>



<title><bean:message  key="app.titulo" bundle="etiquetas"/></title>
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">

<form action="#">

	<table align="center" width="800" border="0" cellspacing="0" cellpadding="0">
    	<tr> 
        	<td align="left" height="34" width="799"><img align="center" src="<%=request.getContextPath()%>/images/cbc_02.jpg" width="252" height="34"></td>
        	<td valign="middle" align="right" width="100%"><a   href="<%=request.getContextPath()%>/security/logout.do" class="textorange" style="v">&nbsp; cerrar sesión&nbsp;</a><img   height="16" width="16" border="0" src="<%=request.getContextPath()%>/images/door_out.png"/></td>			
       	</tr>
       	<tr> 
        	<td colspan="2" align="center">Imagen FASDEM</td>
        </tr>
        <tr> 
        	<td colspan="2" bgcolor="09357A" align="center">
            	<table width="100%" border="0" cellspacing="0" cellpadding="0">
                	<tr height="16" > 
	                	<td valign="bottom" width="60%" style="padding-left:10px" class="whitesp">Bienvenido (a): <c:out value="${usuario.nombre}"/> <c:out value="${usuario.apellido}"/></td>
	                    <td valign="bottom" width="40%" style="padding-right:10px" align="right" class="whitesp" ><jsp:useBean id="ahora" class="java.util.Date" /><fmt:formatDate pattern="EEEEEEEEEEE  d 'de' MMMMMMM 'de' yyyy" value="${ahora}"/></td>
	               	</tr>
	            </table>
            </td>
        </tr>
     </table>


<table align="center" width="800" border="0" cellspacing="0" cellpadding="0">


            				<tr> 
              					<td width="122" valign="top" background="images/fondo_43.gif"> 
                					<div align="left"> 
                  						<table width="100%" border="0" cellspacing="0" cellpadding="0">

                    						<tr> 
                      							<td bgcolor="#EEEEEE"> 
												<div class="sp5"></div>
												
                        							<div align="center" class="nblue">
                        								<table class="border" cellspacing="2" cellpadding="2"> 
                        									<tr>
                        										<td align="center">
																	<img src="<%=request.getContextPath()%>/images/mv_01.jpg" width="80" height="80" border="1">
																</td>
															</tr>
														</table>
                        							</div>
													<div class="sp5"></div>
                        						</td>

							                    </tr>

												<tr> 
                      								<td bgcolor="#EEEEEE" height="2">
														
													</td>  
						                    	</tr>							                    
						                    

						                    
						                    <tr bgcolor="#EEEEEE">
						                    <td height="30" align="center" style="padding-left:5px;color:#7585B6"><div align="center"><img  alt="" src="<%=request.getContextPath()%>/images/house.png"/>&nbsp;&nbsp;<a class="titblue" href="<%=request.getContextPath()%>/">Inicio</a></div></td>
						                    </tr>
						                    <tr> 
						                      	<td bgcolor="#EEEEEE"><div align="right">
						                      		<!-- INICIO MENU SYSGLOBAL -->
			<table cellpadding="1" cellspacing="1" width="180" >
	                <c:forEach items='${usuario.permisos}' var="n">
	                    <tr class="titblue">
	                    	<c:choose >
		                        <c:when test="${n.idPadre==0}">
									<td valign="top" align="left"><img src="<%=request.getContextPath()%>/images/vineta.gif" ></td>
		                        	<td valign="top" class="cg" align="left"><c:out value='${n.etiqueta}'/></td>
		                      	</c:when>
		                        <c:otherwise>
		                        	<td valign="top" width=""align="left">&nbsp;</td>
		                        	<td valign="top" align="left">
		                        		<a href="<%=request.getContextPath()%>/<c:out value='${n.pagina}'/>" class="titblue">
		                        			&nbsp;-&nbsp;<c:out value='${n.etiqueta}'/>
		                        		</a>
									</td>                        
		                      	</c:otherwise>
	                      	</c:choose>
	                  </tr>
	                  </c:forEach>
              </table>
						                      		<!-- FIN MENU SYSGLOBAL -->
												</td>
						              		</tr>
						      			</table>
						         	</div>
						      	</td>
								<td valign="top">
									<div align="center">
			                  			<table width="100%" border="0" align="center" cellpadding="1" cellspacing="1" class="space">
			<logic:messagesPresent message="true">

			 			<html:messages id="msg" message="true" bundle="mensajes">
			                <div class="nredp" style="margin-left:5px;">
			                	- <bean:write name="msg"/><br />
			                </div>
			             </html:messages>    
			             <div class="delineadoBotton"></div>
			</logic:messagesPresent>
	      <tiles:get name='view'/>
      <tiles:get name='items'/>
      <tiles:get name='cuerpo'/>
			                     		</table>
			                      	</div>
			   					</td>
			              	</tr>
			              	
			       		</table>


          				<table align="center" width="800" border="0" cellspacing="0" cellpadding="0">
            				<tr>
              					<td>
              						<div class="sp5"></div>
              						<div class="delineadoBotton"></div>
              						<div align="center" class="blackp">
                  					
                  							Poder Judicial. Sitio web creado y dise&ntilde;ado por la Direcci&oacute;n 
                  							Ejecutiva de la Magistratura del Tribunal Supremo de Justicia.<br>
                  							Todos los Derechos Reservados. Venezuela 2010.
                  					</div>
                  				</td>
            				</tr>
          				</table>						
	</form>
</body>
</html>






