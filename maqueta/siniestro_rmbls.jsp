<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<tiles:insert page="/jsp/plantilla/plantilla.jsp">

	<tiles:put name="titulo" direct="true">
		<bean:message key="principal.titulo" bundle="etiquetas" />
	</tiles:put>
	<tiles:put name="imgtitulo" content="/images/user.png" direct="true" />
	<tiles:put name="view" content=" " direct="true" />
	<tiles:put name="items" content="" />
	<tiles:put name="cuerpo" direct="true">
		<table class="blackg" width="100%" cellpadding="1" cellspacing="0"
			border="0">
			<tr>
				<td width="19%">Numero de Siniestro </td>
        <td><input readonly="readonly" name="numero_siniestro"
					type="text" /></td>
        <td width="26%"><input readonly="readonly" name="nremb" type="text" /></td>
        <input type="hidden" name="siniestro"
					value="1" />
			</tr>
			<tr>
        <td>Fecha de Ocurrencia:</td>
        <td ><input class="black" type="text" style="width:100px" id="true"  name="fecha_desde" onfocus="Javascript:showCalendarSelected2(document.forms[0].fecha_desde,document.forms[0].fecha_desde,'yyyy-mm-dd','es',1);blur();"/>
        </td>
      </tr>
<tr>
        <td>Fecha Notificado:</td>
        <td ><input class="black" type="text" style="width:100px" id="true"  name="fecha_desde" onfocus="Javascript:showCalendarSelected2(document.forms[0].fecha_desde,document.forms[0].fecha_desde,'yyyy-mm-dd','es',1);blur();"/>
        </td>
      </tr>
	   <tr>
        <td> Proveedor:</td>
        <td><select name="prov"
					style="width: 250px">
		  </select></td>
      </tr>
	  <tr>
        <td>Causa del Ingreso:</td>
        <td><select name="prov"
					style="width: 250px">
		  </select></td>
      </tr>
	  <tr>
        <td>Tipo de Enfermedad:</td>
        <td><select name="prov"
					style="width: 250px">
		  </select></td>
      </tr>
	   <tr>
        <td> Monto Facturado:</td>
        <td width="48%" ><input  name="mf"  type="text" size=12 style="width:100px" maxlength="9" onblur="return validador(this,'U',0)" id=true />
        </td>
      </tr>
	   <tr>
        <td> Monto Pagado:</td>
        <td width="48%" ><input  name="mp"  type="text" size=12 style="width:100px" maxlength="9" onblur="return validador(this,'U',0)" id=true />
        </td>
      </tr>
	   <tr width="50%">
				<td >&nbsp;</td>
				<td >
				<input name="genrembls" type="submit"
					value="Generar Reembolso" style="width: 120px" 
					 onclick="this.form.action = '//creareembo.do';">
		   </td>
	  </tr>
	  </table>
		
	</tiles:put>
</tiles:insert>
