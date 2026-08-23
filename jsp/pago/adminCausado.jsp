<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@page import="ve.gob.dem.framework.recursos.*,java.util.Date,java.text.NumberFormat,java.util.ArrayList,ve.gob.dem.fasdem.bean.*,java.text.SimpleDateFormat"%>
<tiles:insert page="/jsp/plantilla/plantilla.jsp">

<%
        
        ArrayList List=(ArrayList)request.getAttribute("lista");
        String primera = (String)request.getAttribute("primera");
        HojaRuta list;
        String anio_actual = (String)request.getAttribute("a_actual");
        String mes_actual = (String)request.getAttribute("m_actual");
        
        ArrayList ListTipo=(ArrayList)request.getAttribute("tipo_hoja");
        
        TipoHojaRuta th;
        
        

         SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
         SimpleDateFormat formato_a = new SimpleDateFormat("yyyy");
         NumberFormat nf = NumberFormat.getInstance();
         nf.setGroupingUsed(true);
         nf.setMaximumFractionDigits(2);
         String dateOut;
         dateOut = Utilidad.DateToString(new Date(), "dd/MM/yyyy");
     
%>



    <tiles:put name="titulo" direct="true">
        <bean:message key="principal.titulo" bundle="etiquetas" />
    </tiles:put>

    <tiles:put name="titulopagina" content="Inicio / Pagos / Relacion de Pagos Masivos"
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

                <div class="etiqueta titulo cgp">Relacion de Pago Mes:</div>
              
               
                <div class="parametro titulo ">
                
                <select name="mes" class="cgp">
                   <option value=<%="01"%> <%=mes_actual.equals("01")?"selected":""%> ><%="ENERO"%></option>
                   <option value=<%="02"%> <%=mes_actual.equals("02")?"selected":""%>><%="FEBRERO"%></option>
                    <option value=<%="03"%> <%=mes_actual.equals("03")?"selected":""%>><%="MARZO"%></option>
                     <option value=<%="04"%> <%=mes_actual.equals("04")?"selected":""%>><%="ABRIL"%></option>
                      <option value=<%="05"%> <%=mes_actual.equals("05")?"selected":""%>><%="MAYO"%></option>
                       <option value=<%="06"%> <%=mes_actual.equals("06")?"selected":""%>><%="JUNIO"%></option>
                        <option value=<%="07"%> <%=mes_actual.equals("07")?"selected":""%>><%="JULIO"%></option>
                         <option value=<%="08"%> <%=mes_actual.equals("08")?"selected":""%>><%="AGOSTO"%></option>
                          <option value=<%="09"%> <%=mes_actual.equals("09")?"selected":""%>><%="SEPTIEMBRE"%></option>
                           <option value=<%="10"%> <%=mes_actual.equals("10")?"selected":""%>><%="OCTUBRE"%></option>
                            <option value=<%="11"%> <%=mes_actual.equals("11")?"selected":""%>><%="NOVIEMBRE"%></option>
                             <option value=<%="12"%> <%=mes_actual.equals("12")?"selected":""%>><%="DICIEMBRE"%></option>
                             
                </select>
                
                
                </div>
                
                
               <div class="etiqueta titulo cgp">Año:</div>
              
               
                <div class="parametro titulo ">
                
                <select name="anio_h" class="cgp">
                   <% int anio_act = Integer.parseInt(formato_a.format(new Date()));
                   for (int k=2011;k!=anio_act+1;k++){%>        
                    <option title="<%=k%>" value=<%=k%> <%=Integer.parseInt(anio_actual)==k?"selected":""%>><%=k%></option>
                   <%}%>    
                </select>
            
                </div>
                
                
                <div class="sp5"></div>
                <div align="right">
                <input class="boton" value="Buscar" type="button"  onclick="ir(1);" />
                
                </div>
                
                
                
                <div style="border-bottom-color: #EEEEEE; border-bottom-width: thin; border-bottom-style: solid">&nbsp;</div>
                <div style="border-bottom-color: #EEEEEE; border-bottom-width: thin; border-bottom-style: solid">&nbsp;</div>
                <div style="border-bottom-color: #EEEEEE; border-bottom-width: thin; border-bottom-style: solid">&nbsp;</div>
        
        
            
                
                <div class="etiqueta titulo cgp">Periodo Fiscal:</div>
              
               
                <div class="parametro titulo ">
                
                <select name="periodo" class="cgp">
                   <% int per = Integer.parseInt(formato_a.format(new Date()));
                   for (int k=2010;k!=per+2;k++){%>     
                    <option title="<%=k%>" value=<%=k%> <%=Integer.parseInt(anio_actual)==k?"selected":""%>><%=k%></option>
                   <%}%>    
                </select>
            
                </div>
                
                
                <div class="etiqueta titulo cgp">Tipo</div>
                <div class="parametro titulo cgp">
                
                <select name="tipohojar" id="tipohojar" class="cgp">
                    <option value="0" selected="selected" class="seleccione">[SELECCIONAR]</option>
                    
                    
                         <%
                            for (int k=0;k!=ListTipo.size();k++){
                                th = (TipoHojaRuta) ListTipo.get(k);
                         %>     
                                
                                <option value=<%=th.getId()%>><%=th.getDescripcion()%></option>
                                
                        <%}%>   

                    
                </select>
                
                </div>
                
                
                
                
                
                
                
                <div class="sp5"></div>
                <div align="right">
                <input class="boton" value="Crear Hoja de Pagos" type="button"  onclick="irCrear(6);" />
                
                </div>
                
                
                
            
        
                <div style="border-bottom-color: #EEEEEE; border-bottom-width: thin; border-bottom-style: solid">&nbsp;</div>
                <div style="border-bottom-color: #EEEEEE; border-bottom-width: thin; border-bottom-style: solid">&nbsp;</div>
        
        
            <table class="tabla" width="600" cellpadding="2" cellspacing="1"
                border="0">
                
                
                
     <% if (List!=null && List.size()!=0){%>    

    
                <tr class="tituloCabecera">

                    <td>Relacion Pago</td>
<!--                     <td>Fecha</td> -->
                    <td>Estatus</td>
                    <td>Tipo</td>
                    <td>Cantidad Pagos</td>
                    <td>Analista</td>                     
                    <td>Editar</td>
                    <td>Cerrar/Abrir</td>
                    <td>Generar TXT</td>
                    <td>Reporte</td>
                    <td>Reporte HR</td>
                    
                </tr>
    
    

      <%
        for (int i=0;i!=List.size();i++){
        list = (HojaRuta) List.get(i);
      %>        
                 
                 <tr class="item" bgcolor="white" >
                
                
                    <td align="center"><B><%=list.getId()%></B></td>
                    <input id="cod<%=list.getId()%>" type="hidden" name="cod<%=list.getId()%>" value="<%=list.getFecha()%>">
<%--                     <td><%= formato.format(list.getFecha())%></td> --%>
                    <td><%=list.getStatus().getDescripcion() %></td>
                    <td><%=list.getTipo().getDescripcion()  %></td>
                    <td align="center"><%=list.getCantidad() %></td>
                    <td><%=list.getAnalista().getNombre() + " " + list.getAnalista().getApellido() %></td>

                    
                    <%if(list.getStatus().getId()==1){ %>
                        <td class="grayplink" align="center"> 
                           <img onclick="irdetalle(15,'<%=list.getId()%>');" style="cursor:hand" src="<%=request.getContextPath()%>/images/page_add.png" width="14" height="13" title="Agregar a Hoja de Pago">
                        </td>
                    <%}else{%>
                        <td align="center"><%="---"%></td>
                    <%}%>
                    
                    
                    
                    <%if(list.getStatus().getId()==1){ %>
                        <td class="grayplink" align="center"> 
                           <img onclick="cambiar(7,'<%=list.getId()%>','2');" style="cursor:hand" src="<%=request.getContextPath()%>/images/ic3d_micta.gif" width="14" height="13" title="Cerrar">
                        </td>
                    <%}%>
                    
                    <%if(list.getStatus().getId()==2){ %>
                        <td class="grayplink" align="center"> 
                           <img onclick="cambiar(7,'<%=list.getId()%>','1');" style="cursor:hand" src="<%=request.getContextPath()%>/images/ic_candadoabierto.gif" width="14" height="13" title="Abrir">
                        </td>
                    <%}%>
                    
                    <%if(list.getStatus().getId()==5){ %>
                        <td align="center"><%="---"%></td>
                    <%}%>
                    
                    
                    <%if(list.getStatus().getId()==3){ %>
                        <td align="center"><%="---"%></td>
                    <%}%>
                    
                    <%if(list.getStatus().getId()==4){ %>
                        <td align="center"><%="---"%></td>
                    <%}%>
                    
                    
                    <%if(list.getStatus().getId()>=2){ %>
                         <td class="grayplink" align="center"> 
                               <img onclick="cambiar(5,'<%=list.getId()%>','3');" style="cursor:hand" src="<%=request.getContextPath()%>/images/door_out.png" width="14" height="13" title="Generar">
                         </td>
                    <%}else{%>
                        <td align="center"><%="---"%></td>
                    <%}%>
                    
                    
                    <td class="grayplink" align="center"> 
                           <img onclick="javascript:openWinScroll2('<%=request.getContextPath()%>/security/pago/imprimirPagoMasivo.do?h=<%=list.getId()%>','name',500,500);" style="cursor:hand" src="<%=request.getContextPath()%>/images/printer.png" width="14" height="13" title="Reporte">

                    </td>
                    
                    <td class="grayplink" align="center"> 
                           <img onclick="javascript:openWinScroll2('<%=request.getContextPath()%>/security/pago/imprimirHojaBanco.do?h=<%=list.getId()%>','name',500,500);" style="cursor:hand" src="<%=request.getContextPath()%>/images/printer.png" width="14" height="13" title="Reporte">

                    </td>
                </tr>
    
                
    <%}%>   

                    
                    
                    
                
                    
                    
                    
                    

                    
                <tr align="left">
                    <td align="left" colspan="6">
                    <input id="desde_select" type="hidden" name="desde_select" value="<%=request.getAttribute("dselect")%>">
                    <input id="hasta_select" type="hidden" name="hasta_select" value="<%=request.getAttribute("hselect")%>">

                    
                    
                    </td>
                </tr>

    
    <%}else{%>  
    
                <%if (primera!=null){ %>
    
                     <tr>
                        <td align="center" class="grayp" style="padding-left: 20px; padding-bottom: 5px;">
                            <div class="grayp"></div>
                            <strong>No existen Hojas de Pagos</strong>
                            <div class="grayp"></div>
                        </td>
                    </tr>
    
                <%}%> 
    <%}%>
    

            </table>
            
            
            
            
            
                
                <div  align="center" >
                   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                </div>
                
                
                
            
            
        
            

            <input id="accionPago" type="hidden" name="accionPago">
            <input id="pre" type="hidden" name="pre">
             <input id="id_hoja" type="hidden" name="id_hoja">
             <input id="status" type="hidden" name="status">
            
    </tiles:put>
    
</tiles:insert>



<script language="JavaScript">

    function irdetalle(acc,id){
          
        document.forms[0].action='nuevaHojaRuta.do';
        document.getElementById('accionPago').value=acc;
        document.getElementById('id_hoja').value=id;
        document.forms[0].submit();
        
    }

    function ir(acc){
        document.getElementById('accionPago').value=acc;
        document.forms[0].submit();
        
    }
    
    function irCrear(acc){
        
             if(document.getElementById('tipohojar').value!=0){
                    

                    document.getElementById('accionPago').value=acc;
                    document.forms[0].submit();
                    
                 
             }else{
               alert("Debe especificar el Tipo de Hoja de Ruta");
             }

        
        
    }
    
    function agregar(acc,p){
          
        document.getElementById('accionPago').value=acc;
        document.getElementById('pre').value=p;
        document.forms[0].submit();
        
    }
    
    function cambiar(acc,h,stat){
          
        document.getElementById('accionPago').value=acc;
        document.getElementById('id_hoja').value=h;
        document.getElementById('status').value=stat;
        document.forms[0].submit();
        
    }
    


    function buscarfechas(acc){
  
        
                
                     if(document.getElementById('fechaInicio').value!=''){
                         if(document.getElementById('fechaFin').value!=''){
                                

                                document.getElementById('accionPago').value=acc;
                                document.forms[0].submit();
                                
                             
                         }else{
                           alert("Debe especificar la fecha fin");
                         }
 
                     }else{
                       alert("Debe especificar la fecha inicio");
                     }
     
                

        
        
    }



    function ircodigo(acc){
          
        if(document.getElementById('codigo1').value!=''){
             if(document.getElementById('codigo2').value!=''){
             
              document.getElementById('accionPago').value=acc;
              document.forms[0].submit();

             }else{
                  alert("Debe especificar el codigo de la Hoja de Ruta");
             }
         }else{
             alert("Debe especificar el codigo de la Hoja de Ruta");
         }
    
    }


    
    

    function checkedAll (frm1) {
        checked=false;
        var aa= document.forms[0];

        for (var i =0; i < aa.elements.length; i++) 
        {
             if (aa.elements[i].checked == false)
             {
              checked = true
             }
           else
             {
             checked = false
             }

             
         aa.elements[i].checked = checked;
        }
          }
        
    
    function openWinScroll2(popup_url, name, width, height) {

        var size = ',width=' + width + ',height=' + height;
        var posicion = ",left=" + Math.round(screen.availWidth / 4) + ",top="
                + Math.round(screen.availHeight / 4);
        var popUp = window
                .open(
                        popup_url,
                        name,
                        'menubar=yes,location=no,toolbar=no,status=no,scrollbars=no,titlebar=no,directories=no,resizable=yes'
                                + size + posicion);
        popUp.opener = self;

    }


</script>





