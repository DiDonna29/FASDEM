<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@page import="ve.gob.dem.framework.recursos.*,java.util.Date,java.text.NumberFormat,java.util.ArrayList,ve.gob.dem.fasdem.bean.*,java.text.SimpleDateFormat"%>
<tiles:insert page="/jsp/plantilla/plantilla.jsp">

<%
        
        ArrayList List=(ArrayList)request.getAttribute("lista");
        String primera = (String)request.getAttribute("primera");

        PreOrdenPago list;
        
        HojaRuta h = new HojaRuta();
                
        h=(HojaRuta)request.getAttribute("hoja");
        
         SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
         SimpleDateFormat formato_anio = new SimpleDateFormat("yyyy");
         NumberFormat nf = NumberFormat.getInstance();
         nf.setGroupingUsed(true);
         nf.setMaximumFractionDigits(2);
         String dateOut;
         dateOut = Utilidad.DateToString(new Date(), "dd/MM/yyyy");
         
         
         
         UnidadTributaria unid =null;
         TipoEmpleado tipE =null;
         
         Double total=0.0;
         
%>



    <tiles:put name="titulo" direct="true">
        <bean:message key="principal.titulo" bundle="etiquetas" />
    </tiles:put>

    <tiles:put name="titulopagina" content="Inicio / Pagos / Generar Archivo Plano"
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
                
                <div class="etiqueta titulo cgp">Fecha de Hoja</div>
                <div class="parametro titulo ">
                <div class="etiqueta titulo cgp"><%=formato.format(h.getFecha())%></div>
                </div>
                
                <div class="etiqueta titulo cgp">Cantidad de Pagos</div>
                <div class="parametro titulo ">
                <div class="etiqueta titulo cgp"><%=h.getCantidad() %></div>
                </div>
            
                 <div class="etiqueta titulo cgp">Tipo</div>
                <div class="parametro titulo ">
                <div class="etiqueta titulo cgp"><%=h.getTipo().getDescripcion() %></div>
                </div>
                
                <div style="border-bottom-color: #EEEEEE; border-bottom-width: thin; border-bottom-style: solid">&nbsp;</div>
                <div style="border-bottom-color: #EEEEEE; border-bottom-width: thin; border-bottom-style: solid">&nbsp;</div>
                <div style="border-bottom-color: #EEEEEE; border-bottom-width: thin; border-bottom-style: solid">&nbsp;</div>
        
            <table class="tabla" width="600" cellpadding="2" cellspacing="1"
                border="0">
                
                
                
     <% if (List!=null && List.size()!=0){%>    

    
                <tr class="tituloCabecera">

                    <td>Pre Orden</td>
                    <td>Beneficiario</td>
                    <td>Monto</td>
                
                </tr>
    
    

      <%
        for (int i=0;i!=List.size();i++){
        list = (PreOrdenPago) List.get(i);
      %>        
                 
                 <tr class="item" bgcolor="white" >
                
                
                    <td><%=list.getCod_completo() %></td>
                    <input id="anio<%=list.getId()%>" type="hidden" name="anio<%=list.getId()%>" value="<%=list.getAnioPreorden() %>">
                    
                    <td><%=(list.getProveedor().getId()==44)?(list.getTitular()!=null)?list.getTitular().getNombres()+ " " + list.getTitular().getApellidos():"":list.getProveedor().getNombre()%></td>
                    <td><%=nf.format(list.getMonto())%></td>
                    <%total=total + list.getMonto(); %>

                </tr>
     
                
    <%}%>   
                
                <tr class="item" bgcolor="white" >
                
                   <td class="tituloCabecera"><%=" "%></td>
                   
                    <td class="tituloCabecera"><%="Monto General:"%></td>
                    <td class="tituloCabecera" colspan="2"><%=nf.format(total)%></td>
                   
                    
                </tr>

   
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
                            <strong>No existen PreOrdenes</strong>
                            <div class="grayp"></div>
                        </td>
                    </tr>
    
                <%}%> 
    <%}%>    
    

            </table>
            

                <div  align="center" >
                   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                </div>
                
                <div class="etiqueta titulo cgp">Numero de la Orden de Pago</div>
                
                <div class="parametro titulo cgp">
                
                    <input type="text" name="causado" id="causado"  size="8" value="" maxlength="8" class="cgp"> 
                
                </div>
                
                 <%if (h.getTipo().getId()!=1){ %>
                
                <div class="sp5"></div>
                <div align="center">
                <input class="boton" value="Insertar" type="button"  onclick="ircodigo(4);" />
                
                </div>
                
                <%}else{%> 
                
                
                <div class="sp5"></div>
                <div align="center">
                <input class="boton" value="Insertar" type="button"  onclick="ircodigo(8);" />
                
                </div>
                
                <%}%>
                
                <div style="border-bottom-color: #EEEEEE; border-bottom-width: thin; border-bottom-style: solid">&nbsp;</div>
                <div style="border-bottom-color: #EEEEEE; border-bottom-width: thin; border-bottom-style: solid">&nbsp;</div>
                <div style="border-bottom-color: #EEEEEE; border-bottom-width: thin; border-bottom-style: solid">&nbsp;</div>
            

            <input id="accionPago" type="hidden" name="accionPago">
            <input id="id_hoja" type="hidden" name="id_hoja" value="<%=h.getId()%>">
            <input id="anioP" type="hidden" name="anioP" value="<%=formato_anio.format(h.getFecha())%>">
            <input id="pre" type="hidden" name="pre">
           
            
    </tiles:put>
    
</tiles:insert>



<script language="JavaScript">

    function ir(acc){
          
        document.getElementById('accionPago').value=acc;
        document.forms[0].submit();
        
    }

    
    function agregar(acc,p){
          
        document.getElementById('accionPago').value=acc;
        document.getElementById('pre').value=p;
        document.forms[0].submit();
        
    }
    
    
    function ircodigo(acc){
          
        if(document.getElementById('causado').value!=''){
             
              document.getElementById('accionPago').value=acc;
              document.forms[0].submit();              
         }else{
             alert("Debe especificar el Numero de la Orden de Pago");
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
        


</script>





