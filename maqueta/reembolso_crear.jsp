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
        <td colspan="2" align="center"><div class="line" />CARGA DE REEMBOLSOS </td>
      </tr>
      <tr>
        <td width="19%">Numero de Siniestro </td>
        <td><input readonly="readonly" name="numero_siniestro"
					type="text" /></td>
        <td width="26%"><input readonly="readonly" name="nremb" type="text" /></td>
        <input type="hidden" name="siniestro"
					value="1" />
        <td width="3%"></td>
        <td width="4%"></td>
      </tr>
      <tr>
        <td>Fecha Recepci&oacute;n:</td>
        <td ><input class="black" type="text" style="width:100px" id="true"  name="fecha_desde" onfocus="Javascript:showCalendarSelected2(document.forms[0].fecha_desde,document.forms[0].fecha_desde,'yyyy-mm-dd','es',1);blur();"/>
        </td>
      </tr>
      <tr>
        <td>Fecha de Factura:</td>
        <td ><input class="black" type="text" style="width:100px" id="true"  name="fecha_desde" onfocus="Javascript:showCalendarSelected2(document.forms[0].fecha_desde,document.forms[0].fecha_desde,'yyyy-mm-dd','es',1);blur();"/>
        </td>
      </tr>
      <tr>
        <td> Número de Factura: </td>
        <td width="48%" ><input  name="serial"  type="text" size=12 style="width:100px" maxlength="9" onblur="return validador(this,'U',0)" id=true />
        </td>
      </tr>
      <tr>
        <td> Número de Control: </td>
        <td width="48%" ><input  name="serial"  type="text" size=12 style="width:100px" maxlength="9" onblur="return validador(this,'U',0)" id=true />
        </td>
      </tr>
      <tr>
        <td> Tipo Gasto:</td>
        <td><select name="ano"
					style="width: 180px">
					<option value=1>Honor&aacute;rios M&eacute;dicos</option>
					<option value=2>Gastos Clínicos</option>
					<option value=3>Examenes Preoperatorios</option>
					<option value=4>Material M&eacute;dico con IVA</option>
					<option value=5>Material M&eacute;dico sin IVA</option>
					<option value=6>Medicinas</option>
					<option value=7>Laboratorio</option>
					<option value=8>Examenes Especiales</option>
					<option value=9>Im&aacute;genes</option>
					<option value=10>Odontolog&iacute;a</option>
					<option value=11>Funer&aacute;rios</option>
					<option value=12>Vida</option>
		  </select></td>
      </tr>
	  <tr>
        <td> Monto Facturado:</td>
        <td width="48%" ><input  name="serial"  type="text" size=12 style="width:100px" maxlength="9" onblur="return validador(this,'U',0)" id=true />
        </td>
      </tr>
	  <tr>
        <td> Monto Amparado:</td>
        <td width="48%" ><input  name="serial"  type="text" size=12 style="width:100px" maxlength="9" onblur="return validador(this,'U',0)" id=true />
        </td>
      </tr>
	  <tr>
        <td> Gsatos no Amparados:</td>
        <td width="48%" ><input  name="serial"  type="text" size=12 style="width:100px" maxlength="9" onblur="return validador(this,'U',0)" id=true />
        </td>
      </tr>
	  <tr>
        <td>Detalle de Factura:</td>
        <td width="48%" ><input  name="serial"  type="text" size=12 style="width:100px" maxlength="9" onblur="return validador(this,'U',0)" id=true />
        </td>
      </tr>
	  <tr width="50%">
				<td >&nbsp;</td>
				<td >
				<input name="Crear" type="submit"
					value="Crear" style="width: 80px" 
					 onclick="this.form.action = '//creareembo.do';">
				</td>
			</tr>
    </table>
	
  </tiles:put>
</tiles:insert>
