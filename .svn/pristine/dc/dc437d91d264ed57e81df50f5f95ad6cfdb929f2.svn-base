<%@page import="java.util.Calendar"%>
<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@page
    import="ve.gob.dem.framework.recursos.*,java.util.Date,java.text.NumberFormat,java.util.ArrayList,ve.gob.dem.fasdem.bean.*,java.text.SimpleDateFormat"%>
<tiles:insert page="/jsp/plantilla/plantilla.jsp">


    <%
        Calendar c = Calendar.getInstance();
            int paramAnioBandeja = c.get(Calendar.YEAR);
            ArrayList List = (ArrayList) request.getAttribute("lista");

            String C_A = (String) request.getAttribute("C_Atencion");
            String C_P = (String) request.getAttribute("C_Pendientes");
            String C_ATEND = (String) request.getAttribute("C_Atendidos");

            String cl = (String) request.getAttribute("usuario_bandeja");
            SiniestroBandeja list;
            SimpleDateFormat formato = new SimpleDateFormat(
                    "dd/MM/yyyy - HH:mm");

            NumberFormat nf = NumberFormat.getInstance();
            nf.setGroupingUsed(true);
            nf.setMaximumFractionDigits(0);
            String dateOut;
            dateOut = Utilidad.DateToString(new Date(),
                    "dd/MM/yyyy-hh:mm a");
            if (request.getAttribute("paramAnioBandeja") != null) {
                paramAnioBandeja = Integer.parseInt((String) request
                        .getAttribute("paramAnioBandeja"));
            }
    %>






    <tiles:put name="titulo" direct="true">
        <bean:message key="principal.titulo" bundle="etiquetas" />
    </tiles:put>

    <tiles:put name="titulopagina"
        content="Solicitudes enviadas de Atención Primaria de Salud"
        direct="true" />
    <tiles:put name="itemsup" content="" direct="true" />
    <tiles:put name="itemsdown" content="" direct="true" />
    <tiles:put name="cuerpo" direct="true">
        <div style="overflow: visible">

        <table class="tabla" width="600" cellpadding="2" cellspacing="1"
            border="0">





            <tr>
                <td width="790" height="20" bgcolor="#FFFFFF" align="right"
                    style="padding-left: 20px; padding-bottom: 0px; padding-top: 0px;"><span
                    class="grayplink">Actualizado al: <%=dateOut%></span><br>
                </td>
            </tr>

            <tr>
                <td width="790" height="20" bgcolor="#FFFFFF" align="right"
                    style="padding-left: 20px; padding-bottom: 0px; padding-top: 0px;"><span
                    class="grayplink">Solicitudes atendidas: <strong>(<%=(C_ATEND != null) ? C_ATEND : "0"%>)
                </strong></span><br>
                </td>
            </tr>


            <tr>
                <td width="790" height="20" bgcolor="#FFFFFF" align="right"
                    style="padding-left: 20px; padding-bottom: 0px; padding-top: 0px;"><span
                    class="grayplink">Solicitudes en atención: <strong>(<%=(C_A != null) ? C_A : "0"%>)
                </strong></span><br>
                </td>
            </tr>

            <tr>
                <td width="790" height="20" bgcolor="#FFFFFF" align="right"
                    style="padding-left: 20px; padding-bottom: 0px; padding-top: 0px;"><span
                    class="grayplink">Solicitudes pendientes: <strong>(<%=(C_P != null) ? C_P : "0"%>)
                </strong></span><br>
                </td>
            </tr>

            <tr>

                <td width="790" height="20" bgcolor="#FFFFFF" align="left"
                    style="padding-left: 20px; padding-bottom: 0px; padding-top: 0px;"><img
                    src="<%=request.getContextPath()%>/images/f_pts.gif" width="5"
                    height="9"> <span class="grayplink"><a
                    onclick="javascript:document.forms[0].submit();" href="#">Actualizar</a></span><br>
            </tr>



        </table>



        <table class="tabla" width="600" cellpadding="2" cellspacing="1"
            border="0">
            <tr style="background-color: white">
                <td colspan="5"><select name="paramAnioBandeja"
                    id="paramAnioBandeja" onchange="submit();">
                    <%
                        String selec = "";
                                for (int i = c.get(Calendar.YEAR); i >= 2010; i--) {
                                    selec = "";
                                    if (i == paramAnioBandeja) {
                                        selec = "selected ";
                                    }
                    %>
                    <option <%=selec%> value="<%=i%>"><%=i%></option>
                    <%
                        }
                    %>
                </select></td>
            </tr>


            <%
                if (List != null) {
            %>





            <tr class="tituloCabecera">

                <td>Beneficiario</td>
                <td>Datos de la Solicitud</td>
                <td>Fecha Registro</td>
                <td>Observación</td>
                <td>Cerrar</td>

            </tr>


            <%
                for (int i = 0; i != List.size(); i++) {
                                list = (SiniestroBandeja) List.get(i);
            %>


            <tr class="item" bgcolor="white">

                <%
                    pageContext.setAttribute("cedulaBene",
                                            list.getCedBeneficiario());
                %>
                <td><%=list.getCrifBeneficiario() + "-"%> <fmt:formatNumber
                    value="${cedulaBene}" groupingUsed="true" /> <br>
                <%=list.getBeneficiario()%> <br>
                <%="Tlf Contacto:<br> " + list.getTlf()
                                    + "<br><b>Titular: <b>"%> <input id="telefonon" type="hidden" name="telefonon" value=<%=list.getTlf()%>><fmt:formatNumber
                    value="${cedulaBene}" groupingUsed="true" /></td>
                <td><strong>Centro de Atención:</strong><strong class="nblack"><br><%="(" + list.getRifClinica() + ")" + " "+ list.getClinica()%><br>
                </strong><br>
                <strong>Causa de Ingreso:</strong><br><%=(list.getCausaIngreso() == null)
                                    ? ""
                                    : list.getCausaIngreso()%> <br>
                <br>
                <strong>Monto: <%=(list.getMonto() == null) ? "" : list
                                    .getMonto() + " Bs."%></strong></td>
                <td><%=Utilidad.DateToString(
                                    list.getFechaIngreso(), "dd/MM/yyyy")
                                    + "<br>"
                                    + Utilidad.DateToString(
                                            list.getHoraIngreso(), "hh:mm a")%></td>


                <%
                    if (list.getId_estatus() == 0) {
                %>


                <%
                    if (cl.equals(list.getLoginProcesa())) {
                %>

                <td align="center" valign="middle"><textarea rows="3" cols="25"
                    name="<%=list.getId()%>" id="<%=list.getId()%>" class="grayplink"></textarea><br>
                    <input id="cl_<%=list.getId()%>" type="hidden" name="clinica" maxlength="30"  value=<%=list.getClinica()%>>
                    <input id="ben_<%=list.getId()%>" type="hidden" name="beneficiario" maxlength="30" value=<%=list.getBeneficiario()%>>
           <!-- sms start -->   
           <div  align="right" class="etiqueta titulo cgp"><br>Enviar SMS </div>
              <div style="border-bottom-color: #EEEEEE; border-bottom-width: thin; border-bottom-style: solid">&nbsp;</div>
                <div align="left">
                <input name="sms" id="sms_<%=list.getId()%>" type="checkbox" style="width: auto"  onclick="checkedAll(<%=list.getId()%>);" /> 
                <input type="text" name="tlf" id="tlf_<%=list.getId()%>" class="titblue" width = 60px; disabled="disabled" maxlength="11" value="<%=list.getTlf()%>" >
                <span class="etiqueta titulo cgp">ejp.123456789</span></div>
              <!-- sms end -->
        </td>
                <td align="center" valign="middle"
                    style="padding-left: 10px; padding-right: 10px; padding-bottom: 8px; padding-top: 8px;"><img
                    onclick="cargaAps('<%=list.getId()%>','<%=list.getTlf()%>')"
                    src="<%=request.getContextPath()%>/images/ic3d_enviado.gif"
                    width="14" height="13" title="Procesar"> <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <img onclick="ir('3','<%=list.getId()%>','<%=list.getTlf()%>','tlf_<%=list.getId()%>','sms_<%=list.getId()%>',cl_<%=list.getId()%>,ben_<%=list.getId()%>)"
                    src="<%=request.getContextPath()%>/images/cross.png" width="14"
                    height="13" title="Cerrar"></td>

                <%
                    } else {
                %>

                <td valign="middle" align="center" colspan="2" class="grayplink"
                    style="padding-left: 10px; padding-right: 10px; padding-bottom: 5px; padding-top: 5px;"><b>Siniestro
                en Atención <br>
                (<%=list.getDatosProcesa()%>) </b><br>
                </td>

                <%
                    }
                %>

                <%
                    }
                %>

                <%
                    if (list.getId_estatus() == 1) {
                %>

                <td colspan="2" valign="middle" align="center" class="blueblink"
                    style="padding-left: 10px; padding-bottom: 2px; padding-top: 2px;"><img
                    src="<%=request.getContextPath()%>/images/f_pts.gif" width="5"
                    height="9"> <span class="grayplink">
                    <a  onclick="ir1('2','<%=list.getId()%>','<%=list.getTlf()%>')" href="#">Tomar Caso</a></span><br>
                </td>

                <%
                    }
                %>



            </tr>

            <%
                }
            %>


            <%
                } else {
            %>

            <tr>
                <td align="center" class="grayp"
                    style="padding-left: 20px; padding-bottom: 5px;">
                <div class="grayp"></div>
                <strong>No existen solicitudes pendientes por atención</strong>
                <div class="grayp"></div>
                </td>
            </tr>


            <%
                }
            %>




        </table>


        </div>

        <input id="accion" type="hidden" name="accion" value="">
        <input id="siniestro" type="hidden" name="siniestro" value="">
        <input id="siniestro2" type="hidden" name="siniestro2" value="">
        <input id="sms" type="hidden" name="sms" value="">
        <input id="tlf" type="hidden" name="tlf" value="">
        <input id="clinica" type="hidden" name="clinica" value="">
        <input id="beneficiario" type="hidden" name="beneficiario" value="">
        


    </tiles:put>

</tiles:insert>



<script language="JavaScript">
function ir1(acc,valor,tel){
    
    //document.forms[0].tlf.value= tlf;
      document.getElementById('accion').value=acc;
      document.getElementById('siniestro').value=valor;
      document.getElementById('telefonon').value=tel;
      document.forms[0].submit();
    
      
  }

  function ir(acc,valor,tel,tlf,sms,clinica,beneficiario){
    
    texto=document.getElementById(valor).value;
    
    if(texto==''){alert("Debe colocar el motivo en el campo de observacion"); 
    document.getElementById(valor).focus(); 
    }
     else{
  
  
    if(confirm('¿Esta seguro que desea realizar esta operación?')){
      document.getElementById('accion').value=acc;
      document.getElementById('siniestro').value=valor;
      document.getElementById('telefonon').value=tel;
      document.getElementById('sms').value=sms;
      document.getElementById('tlf').value=tlf;
      document.getElementById('clinica').value=clinica;
      document.getElementById('beneficiario').value=beneficiario;
      document.getElementById('siniestro2').value=document.getElementById(valor).value;
      document.forms[0].submit();}
    }
    //else{document.forms[0].submit();}
      
      
    }
      
  


  function cargaAps(valor,tel){
    document.getElementById('siniestro').value=valor;
    document.getElementById('telefonon').value=tel;
    
    document.forms[0].action='<%=request.getContextPath()%>/security/aps/puenteOnline.do';
    document.forms[0].submit();
  }

    
function checkedAll (valor) {
      //alert("pasa por aqui"+valor);
   var telf  = document.getElementById('tlf_'+valor);
   var smst  = document.getElementById('sms_'+valor);
   //alert(smst);
  //alert(smst.checked);
  // var telfv = document.getElementById('tlf_'+valor).value;
   
  if (smst.checked== false) {
            telf.disabled = true;
            if(telf.value=='-' || telf.value==''){
                telf.value= '-';
            }
        } else {
          telf.disabled = false;
             if(telf.value=='-'){
               telf.value= '';
             }
        }

    }
    
    
</script>





