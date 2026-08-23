/**15/07/2010
 * marcenrl
 */
package ve.gob.dem.fasdem.action.reportes;

import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import ve.gob.dem.fasdem.bean.Clinica;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.exp.ExpClinica;
import ve.gob.dem.framework.cnx.Conexion;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.global.GenericAction;
import ve.gob.dem.framework.recursos.Constantes;
import ve.gob.dem.framework.recursos.Parametros;
import ve.gob.dem.framework.recursos.Utilidad;
import ve.gob.dem.framework.seguridad.bean.Usuario;
import ve.gob.dem.framework.seguridad.exp.ExpUsuario;

public class PrintRetencion extends GenericAction {
	static protected Logger log = Logger.getLogger(PrintRetencion.class);

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Usuario usuario = null;
		Usuario usuario1 = null;
		usuario = (Usuario) session.getAttribute("usuario");
		usuario1 = ExpUsuario.buscarDatosP(usuario.getLogin());
		ActionMessages am = new ActionMessages();
		Entorno ent = new Entorno(Entorno.MOD_NINGUNO);
		try {
			validarAction(request, form, ent, am, this.getClass());
		} catch (PersonalNotFillItems e) {
			return mapping.findForward(FWD_INPUT);
		}
		HashMap mapa = new HashMap();
		JasperPrint jasperprint;
		String realPath = getServlet().getServletContext().getRealPath("/");
		String path;
		String rptfilename = "";
		String anio;
		String mes;
		String totales;
		String sql="";
		String alias="";
		ServletOutputStream outstream2;
		byte[] pdfasbytes;
		mapa.put("ruta", realPath + "images/");
		mapa.put("REPORT_LOCALE", new Locale("es", "VE"));
		String date = Utilidad.DateToString(new Date(), "ddMMyyyyhhmmss");
		mapa.put("USUARIO", usuario1.getDescripcion_usuario());
		Clinica p = ExpClinica.BuscarPorid(Integer.parseInt(request.getParameter("c")));
		mapa.put("anio", Integer.parseInt(request.getParameter("p")));
		mapa.put("id_prov", Integer.parseInt(request.getParameter("c")));
		mapa.put("mes", Integer.parseInt(request.getParameter("mes")));
		mapa.put("totales", request.getParameter("totales"));
		
	
		
		mapa.put("CLINICA", p.getNombre());
		mapa.put("DIRECCION_CLINICA", p.getDireccion());
		mapa.put("TELEFONO_CLINICA", p.getTelefono());
		mapa.put("RIF_FORMATEADO", p.getRif());
		sql=Parametros.getStrParameter(request, "sql", "");
		String accion = request.getParameter("accionReporte");
	
		anio = Parametros.getStrParameter(request, "anio", "0");
		mes = Parametros.getStrParameter(request, "mes", "0");
		totales = Parametros.getStrParameter(request, "totales", "0");
	
		alias=Parametros.getStrParameter(request, "alias", "");
	
		Connection db = null;
		
		 try {	
			
				mapa.put("anio", request.getParameter("p"));
				 db =   Conexion.getConexion();
				
				switch (Integer.parseInt(request.getParameter("p")))
				{
				
				case 2010:
					alias="000";
					break;
					case 2011:
						alias="010";
						break;
					case 2012:
						alias="020";
						break;
					case 2013:
						alias="030";
						break;
					case 2014:
						alias="040";
						break;
					case 2015:
						alias="050";
						break;
					case 2016:
						alias="060";
						break;
					case 2017:
						alias="070";
						break;
					case 2018:
						alias="080";
						break;
					case 2019:
						alias="090";
						break;
					case 2020:
						alias="100";
						break;
					default:
					break;
					
				}
				
				mapa.put("anio", Integer.parseInt(request.getParameter("p")));
			
			if (accion.equals("2")) { // //
				if 
				 (p.getTipoProveedor().getId_tipo_preOrden() == Constantes.TipoPreOrdenFarmacia) {
					if (totales=="1"||"1".equals(totales)){
						log.info("1TOTALES ES   SSSSS "+totales);
						mapa.put("sql","SELECT * FROM (select sum(fact.monto_total) as mtofac,to_char(fact.fecha_factura,'MM') as mes,fact.porcentaje_isrl,fact.porcentaje_timbre,0::double precision as base_imponible_isrl,(COALESCE(farmacia, 0::double precision)) as MTOLIQ,0::double precision as MTOISR,fact.porcentaje_timbre * (COALESCE(farmacia, 0::double precision))/100 as MTOTMF from (select siniestro.aniomes_codigo||'-'||siniestro.codigo_siniestro as siniestro,subcodigo_siniestro as subsiniestro,facturas.fecha_factura,facturas.nro_factura,facturas.monto_total,id_factura,porcentaje_isrl,porcentaje_timbre,porcentaje_iva,facturas.anio_siniestro from facturas"+alias+" facturas,siniestro"+alias+" siniestro,pre_orden_pago"+alias+" pre_orden_pago where facturas.pre_orden=pre_orden_pago.pre_orden and facturas.id_siniestro=siniestro.id_siniestro and is_factura is true and facturas.anio_siniestro="+Integer.parseInt(request.getParameter("p"))+" and siniestro.anio_siniestro="+Integer.parseInt(request.getParameter("p"))+" and pre_orden_pago.id_estatus IN (2,3,4)  and pre_orden_pago.id_proveedor="+request.getParameter("c")+") fact LEFT JOIN ( SELECT df.id_factura AS id_factura_farmacia, sum(df.monto_amparado) AS farmacia FROM detalles_factura"+alias+" df,tipo_gasto tg WHERE df.id_tipo_gasto=tg.id_tipo_gasto and tg.id_tip_gas_gen = 7 and anio_siniestro="+Integer.parseInt(request.getParameter("p"))+" GROUP BY df.id_factura) farm ON fact.id_factura = farm.id_factura_farmacia group by mes,fact.porcentaje_isrl,fact.porcentaje_timbre,base_imponible_isrl,MTOLIQ,MTOISR,MTOTMF) AS FINAL ORDER BY mes");
						rptfilename = "/jasper/RetencionISRLFarmaciaTOTALES.jasper";
					}else{
					mapa.put("sql","SELECT * FROM (select fact.siniestro as NROSIN,fact.subsiniestro as NROPAG,fact.fecha_factura as FECFAC,fact.nro_factura as NROFAC,fact.monto_total as MTOFAC,fact.porcentaje_isrl,fact.porcentaje_timbre,0::double precision as base_imponible_isrl,(COALESCE(farmacia, 0::double precision)) as MTOLIQ,0::double precision as MTOISR,fact.porcentaje_timbre * (COALESCE(farmacia, 0::double precision))/100 as MTOTMF from (select siniestro.aniomes_codigo||'-'||siniestro.codigo_siniestro as siniestro,subcodigo_siniestro as subsiniestro,facturas.fecha_factura,facturas.nro_factura,facturas.monto_total,id_factura,porcentaje_isrl,porcentaje_timbre,porcentaje_iva,facturas.anio_siniestro from facturas"+alias+" facturas,siniestro"+alias+" siniestro,pre_orden_pago"+alias+" pre_orden_pago where to_char(facturas.fecha_factura,'MM')::int="+mes+" and facturas.pre_orden=pre_orden_pago.pre_orden and facturas.id_siniestro=siniestro.id_siniestro and is_factura is true and facturas.anio_siniestro="+Integer.parseInt(request.getParameter("p"))+" and siniestro.anio_siniestro="+Integer.parseInt(request.getParameter("p"))+" and pre_orden_pago.id_estatus IN (2,3,4)  and pre_orden_pago.id_proveedor="+request.getParameter("c")+") fact LEFT JOIN ( SELECT df.id_factura AS id_factura_farmacia, sum(df.monto_amparado) AS farmacia FROM detalles_factura"+alias+" df,tipo_gasto tg WHERE df.id_tipo_gasto=tg.id_tipo_gasto and tg.id_tip_gas_gen = 7 and anio_siniestro="+Integer.parseInt(request.getParameter("p"))+" GROUP BY df.id_factura) farm ON fact.id_factura = farm.id_factura_farmacia) AS FINAL ORDER BY FECFAC");
					rptfilename = "/jasper/RetencionISRLFarmacia.jasper";
					}
				} else {
					if (totales=="1"||"1".equals(totales)){
						log.info("2TOTALES ES   SSSSS "+totales);
						mapa.put("sql","SELECT * FROM ("+
								" select"+
								" sum(fact.monto_total) as mtofac,"+
								" to_char(fact.fecha_factura,'MM') as mes,"+
								" fact.porcentaje_isrl, "+
								" fact.porcentaje_timbre,"+
								" "+
								" "+
								" sum((COALESCE(gastos_clinicos, 0::double precision) + "+
								"  COALESCE(odontologia, 0::double precision)  + "+
								" COALESCE(funerarios, 0::double precision) + "+
								" COALESCE(otros, 0::double precision) +"+
								"  COALESCE(vida, 0::double precision) +"+
								" COALESCE(material_iva, 0::double precision) +"+
								" COALESCE(material, 0::double precision) ))  as base_imponible_isrl, "+
								" "+
								" sum((COALESCE(gastos_clinicos, 0::double precision) + "+
								" COALESCE(odontologia, 0::double precision)  + "+
								" COALESCE(funerarios, 0::double precision) + "+
								" COALESCE(otros, 0::double precision) + "+
								" COALESCE(material, 0::double precision) + "+
								" COALESCE(material_iva, 0::double precision) + "+
								" COALESCE(vida, 0::double precision) + "+
								" COALESCE(honorarios_medicos, 0::double precision) +  "+
								" COALESCE(farmacia, 0::double precision) +"+
								" COALESCE(iva, 0::double precision))) as MTOLIQ, "+
								"  "+
								" sum(fact.porcentaje_isrl * (COALESCE(gastos_clinicos, 0::double precision) + "+
								" COALESCE(odontologia, 0::double precision)  + "+
								" COALESCE(funerarios, 0::double precision) + "+
								" COALESCE(otros, 0::double precision) +"+
								" COALESCE(vida, 0::double precision) +"+
								" COALESCE(material_iva, 0::double precision) +"+
								" COALESCE(material, 0::double precision) )/100) as MTOISR, "+
								"  "+
								" sum(fact.porcentaje_timbre * (COALESCE(gastos_clinicos, 0::double precision) + "+
								"  COALESCE(odontologia, 0::double precision)  + "+
								" COALESCE(funerarios, 0::double precision) + "+
								" COALESCE(otros, 0::double precision) + "+
								"  COALESCE(material, 0::double precision) + "+
								" COALESCE(material_iva, 0::double precision) + "+
								" COALESCE(vida, 0::double precision) + "+
								"  COALESCE(honorarios_medicos, 0::double precision) +  "+
								" COALESCE(farmacia, 0::double precision) "+
								"  )/100) as MTOTMF"+
								"  "+
								" from "+
								" "+
								" (select"+
								" siniestro.aniomes_codigo||'-'||siniestro.codigo_siniestro as siniestro, "+
								" subcodigo_siniestro as subsiniestro, "+
								" facturas.fecha_factura, "+
								" facturas.nro_factura,"+
								" facturas.monto_total,"+
								" id_factura, "+
								" porcentaje_isrl, "+
								" porcentaje_timbre, "+
								" porcentaje_iva, "+
								" facturas.anio_siniestro"+
								" "+
								" from facturas"+alias+" facturas,siniestro"+alias+" siniestro,pre_orden_pago"+alias+" pre_orden_pago "+
								" where "+
								" facturas.pre_orden=pre_orden_pago.pre_orden and "+
								" facturas.id_siniestro=siniestro.id_siniestro and "+
								" is_factura is true and facturas.anio_siniestro="+Integer.parseInt(request.getParameter("p"))+" and siniestro.anio_siniestro="+Integer.parseInt(request.getParameter("p"))+" and pre_orden_pago.id_estatus IN (2,3,4)  and pre_orden_pago.id_proveedor="+request.getParameter("c")+") fact "+
								" "+
								" LEFT JOIN ( SELECT df.id_factura AS id_facturagc, sum(df.monto_amparado) AS gastos_clinicos "+
								" FROM detalles_factura"+alias+" df,tipo_gasto tg "+
								" WHERE df.id_tipo_gasto=tg.id_tipo_gasto and tg.id_tip_gas_gen = 3  and anio_siniestro="+Integer.parseInt(request.getParameter("p"))+""+
								" GROUP BY df.id_factura) gc ON fact.id_factura = gc.id_facturagc  "+
								" "+
								" "+
								" LEFT JOIN ( SELECT df.id_factura AS id_facturahm, sum(df.monto_amparado) AS honorarios_medicos"+  
								" FROM detalles_factura"+alias+" df,tipo_gasto tg "+
								" WHERE df.id_tipo_gasto=tg.id_tipo_gasto and tg.id_tip_gas_gen = 6 and anio_siniestro="+Integer.parseInt(request.getParameter("p"))+""+
								" GROUP BY df.id_factura) hm ON fact.id_factura = hm.id_facturahm "+
								" "+
								" "+
								" LEFT JOIN ( SELECT df.id_factura AS id_factura_mativa, sum(df.monto_amparado) AS material_iva  "+
								" FROM detalles_factura"+alias+" df,tipo_gasto tg "+
								" WHERE df.id_tipo_gasto=tg.id_tipo_gasto and tg.id_tip_gas_gen = 9 and anio_siniestro="+Integer.parseInt(request.getParameter("p"))+""+
								" GROUP BY df.id_factura) mmi ON fact.id_factura = mmi.id_factura_mativa "+
								" "+
								" "+
								" LEFT JOIN ( SELECT df.id_factura AS id_factura_mat, sum(df.monto_amparado) AS material "+
								" FROM detalles_factura"+alias+" df,tipo_gasto tg  "+
								" WHERE df.id_tipo_gasto=tg.id_tipo_gasto and tg.id_tip_gas_gen = 10  and anio_siniestro="+Integer.parseInt(request.getParameter("p"))+""+
								" GROUP BY df.id_factura) mm ON fact.id_factura = mm.id_factura_mat "+
								" "+
								" "+
								" LEFT JOIN ( SELECT df.id_factura AS id_factura_odo, sum(df.monto_amparado) AS odontologia "+ 
								" FROM detalles_factura"+alias+" df,tipo_gasto tg  "+
								" WHERE df.id_tipo_gasto=tg.id_tipo_gasto and tg.id_tip_gas_gen = 1 and anio_siniestro="+Integer.parseInt(request.getParameter("p"))+""+
								" GROUP BY df.id_factura) od ON fact.id_factura = od.id_factura_odo "+
								" "+
								" "+
								" LEFT JOIN ( SELECT df.id_factura AS id_factura_fun, sum(df.monto_amparado) AS funerarios "+
								" FROM detalles_factura"+alias+" df,tipo_gasto tg  "+
								" WHERE df.id_tipo_gasto=tg.id_tipo_gasto and tg.id_tip_gas_gen = 2  and anio_siniestro="+Integer.parseInt(request.getParameter("p"))+""+
								" GROUP BY df.id_factura) fu ON fact.id_factura = fu.id_factura_fun  "+
								" "+
								" "+
								" LEFT JOIN ( SELECT df.id_factura AS id_factura_vida, sum(df.monto_amparado) AS vida  "+
								" FROM detalles_factura"+alias+" df,tipo_gasto tg  "+
								" WHERE df.id_tipo_gasto=tg.id_tipo_gasto and tg.id_tip_gas_gen = 4 and anio_siniestro="+Integer.parseInt(request.getParameter("p"))+""+
								" GROUP BY df.id_factura) vid ON fact.id_factura = vid.id_factura_vida "+
								" "+
								" "+
								" LEFT JOIN ( SELECT df.id_factura AS id_factura_ambulancia, sum(df.monto_amparado) AS ambulancia"+
								" FROM detalles_factura"+alias+" df,tipo_gasto tg "+
								" WHERE df.id_tipo_gasto=tg.id_tipo_gasto and tg.id_tip_gas_gen = 5 and anio_siniestro="+Integer.parseInt(request.getParameter("p"))+""+
								" GROUP BY df.id_factura) amb ON fact.id_factura = amb.id_factura_ambulancia "+
								" "+
								" "+
								" LEFT JOIN ( SELECT df.id_factura AS id_factura_farmacia, sum(df.monto_amparado) AS farmacia "+
								" FROM detalles_factura"+alias+" df,tipo_gasto tg "+
								" WHERE df.id_tipo_gasto=tg.id_tipo_gasto and tg.id_tip_gas_gen = 7 and anio_siniestro="+Integer.parseInt(request.getParameter("p"))+""+
								" GROUP BY df.id_factura) farm ON fact.id_factura = farm.id_factura_farmacia "+
								" "+
								" "+
								" LEFT JOIN ( SELECT df.id_factura AS id_factura_otros, sum(df.monto_amparado) AS otros "+ 
								" FROM detalles_factura"+alias+" df,tipo_gasto tg "+
								" WHERE df.id_tipo_gasto=tg.id_tipo_gasto and tg.id_tip_gas_gen = 8 and anio_siniestro="+Integer.parseInt(request.getParameter("p"))+""+
								" GROUP BY df.id_factura) otr ON fact.id_factura = otr.id_factura_otros"+
								" "+
								" LEFT JOIN ( SELECT df.id_factura AS id_factura_iva, sum(df.monto_amparado) AS iva"+  
								" FROM detalles_factura"+alias+" df,tipo_gasto tg "+
								" WHERE df.id_tipo_gasto=tg.id_tipo_gasto and tg.id_tip_gas_gen = 11 and anio_siniestro="+Integer.parseInt(request.getParameter("p"))+""+
								" GROUP BY df.id_factura) iva ON fact.id_factura = iva.id_factura_iva group by mes,fact.porcentaje_isrl,fact.porcentaje_timbre) AS FINAL  ORDER BY mes");
													rptfilename = "/jasper/RetencionISRLTOTALES.jasper";
					}else{
					mapa.put("sql","SELECT * FROM ("+
" select"+
" fact.siniestro as NROSIN,"+
" fact.subsiniestro as NROPAG,"+
" fact.fecha_factura as FECFAC, "+
" fact.nro_factura as NROFAC, "+
" fact.monto_total as MTOFAC,"+
" fact.porcentaje_isrl, "+
" fact.porcentaje_timbre,"+
" "+
" "+
" (COALESCE(gastos_clinicos, 0::double precision) + "+
"  COALESCE(odontologia, 0::double precision)  + "+
" COALESCE(funerarios, 0::double precision) + "+
" COALESCE(otros, 0::double precision) +"+
"  COALESCE(vida, 0::double precision) +"+
" COALESCE(material_iva, 0::double precision) +"+
" COALESCE(material, 0::double precision) )  as base_imponible_isrl, "+
" "+
" (COALESCE(gastos_clinicos, 0::double precision) + "+
" COALESCE(odontologia, 0::double precision)  + "+
" COALESCE(funerarios, 0::double precision) + "+
" COALESCE(otros, 0::double precision) + "+
" COALESCE(material, 0::double precision) + "+
" COALESCE(material_iva, 0::double precision) + "+
" COALESCE(vida, 0::double precision) + "+
" COALESCE(honorarios_medicos, 0::double precision) +  "+
" COALESCE(farmacia, 0::double precision) +"+
" COALESCE(iva, 0::double precision)) as MTOLIQ, "+
"  "+
" fact.porcentaje_isrl * (COALESCE(gastos_clinicos, 0::double precision) + "+
" COALESCE(odontologia, 0::double precision)  + "+
" COALESCE(funerarios, 0::double precision) + "+
" COALESCE(otros, 0::double precision) +"+
" COALESCE(vida, 0::double precision) +"+
" COALESCE(material_iva, 0::double precision) +"+
" COALESCE(material, 0::double precision) )/100 as MTOISR, "+
"  "+
" fact.porcentaje_timbre * (COALESCE(gastos_clinicos, 0::double precision) + "+
"  COALESCE(odontologia, 0::double precision)  + "+
" COALESCE(funerarios, 0::double precision) + "+
" COALESCE(otros, 0::double precision) + "+
"  COALESCE(material, 0::double precision) + "+
" COALESCE(material_iva, 0::double precision) + "+
" COALESCE(vida, 0::double precision) + "+
"  COALESCE(honorarios_medicos, 0::double precision) +  "+
" COALESCE(farmacia, 0::double precision) "+
"  )/100 as MTOTMF"+
"  "+
" from "+
" "+
" (select"+
" siniestro.aniomes_codigo||'-'||siniestro.codigo_siniestro as siniestro, "+
" subcodigo_siniestro as subsiniestro, "+
" facturas.fecha_factura, "+
" facturas.nro_factura,"+
" facturas.monto_total,"+
" id_factura, "+
" porcentaje_isrl, "+
" porcentaje_timbre, "+
" porcentaje_iva, "+
" facturas.anio_siniestro"+
" "+
" from facturas"+alias+" facturas,siniestro"+alias+" siniestro,pre_orden_pago"+alias+" pre_orden_pago "+
" where "+
" facturas.pre_orden=pre_orden_pago.pre_orden and  to_char(facturas.fecha_factura,'MM')::int="+mes+" and"+
" facturas.id_siniestro=siniestro.id_siniestro and "+
" is_factura is true and facturas.anio_siniestro="+Integer.parseInt(request.getParameter("p"))+" and siniestro.anio_siniestro="+Integer.parseInt(request.getParameter("p"))+" and pre_orden_pago.id_estatus IN (2,3,4)  and pre_orden_pago.id_proveedor="+request.getParameter("c")+") fact "+
" "+
" LEFT JOIN ( SELECT df.id_factura AS id_facturagc, sum(df.monto_amparado) AS gastos_clinicos "+
" FROM detalles_factura"+alias+" df,tipo_gasto tg "+
" WHERE df.id_tipo_gasto=tg.id_tipo_gasto and tg.id_tip_gas_gen = 3  and anio_siniestro="+Integer.parseInt(request.getParameter("p"))+""+
" GROUP BY df.id_factura) gc ON fact.id_factura = gc.id_facturagc  "+
" "+
" "+
" LEFT JOIN ( SELECT df.id_factura AS id_facturahm, sum(df.monto_amparado) AS honorarios_medicos"+  
" FROM detalles_factura"+alias+" df,tipo_gasto tg "+
" WHERE df.id_tipo_gasto=tg.id_tipo_gasto and tg.id_tip_gas_gen = 6 and anio_siniestro="+Integer.parseInt(request.getParameter("p"))+""+
" GROUP BY df.id_factura) hm ON fact.id_factura = hm.id_facturahm "+
" "+
" "+
" LEFT JOIN ( SELECT df.id_factura AS id_factura_mativa, sum(df.monto_amparado) AS material_iva  "+
" FROM detalles_factura"+alias+" df,tipo_gasto tg "+
" WHERE df.id_tipo_gasto=tg.id_tipo_gasto and tg.id_tip_gas_gen = 9 and anio_siniestro="+Integer.parseInt(request.getParameter("p"))+""+
" GROUP BY df.id_factura) mmi ON fact.id_factura = mmi.id_factura_mativa "+
" "+
" "+
" LEFT JOIN ( SELECT df.id_factura AS id_factura_mat, sum(df.monto_amparado) AS material "+
" FROM detalles_factura"+alias+" df,tipo_gasto tg  "+
" WHERE df.id_tipo_gasto=tg.id_tipo_gasto and tg.id_tip_gas_gen = 10  and anio_siniestro="+Integer.parseInt(request.getParameter("p"))+""+
" GROUP BY df.id_factura) mm ON fact.id_factura = mm.id_factura_mat "+
" "+
" "+
" LEFT JOIN ( SELECT df.id_factura AS id_factura_odo, sum(df.monto_amparado) AS odontologia "+ 
" FROM detalles_factura"+alias+" df,tipo_gasto tg  "+
" WHERE df.id_tipo_gasto=tg.id_tipo_gasto and tg.id_tip_gas_gen = 1 and anio_siniestro="+Integer.parseInt(request.getParameter("p"))+""+
" GROUP BY df.id_factura) od ON fact.id_factura = od.id_factura_odo "+
" "+
" "+
" LEFT JOIN ( SELECT df.id_factura AS id_factura_fun, sum(df.monto_amparado) AS funerarios "+
" FROM detalles_factura"+alias+" df,tipo_gasto tg  "+
" WHERE df.id_tipo_gasto=tg.id_tipo_gasto and tg.id_tip_gas_gen = 2  and anio_siniestro="+Integer.parseInt(request.getParameter("p"))+""+
" GROUP BY df.id_factura) fu ON fact.id_factura = fu.id_factura_fun  "+
" "+
" "+
" LEFT JOIN ( SELECT df.id_factura AS id_factura_vida, sum(df.monto_amparado) AS vida  "+
" FROM detalles_factura"+alias+" df,tipo_gasto tg  "+
" WHERE df.id_tipo_gasto=tg.id_tipo_gasto and tg.id_tip_gas_gen = 4 and anio_siniestro="+Integer.parseInt(request.getParameter("p"))+""+
" GROUP BY df.id_factura) vid ON fact.id_factura = vid.id_factura_vida "+
" "+
" "+
" LEFT JOIN ( SELECT df.id_factura AS id_factura_ambulancia, sum(df.monto_amparado) AS ambulancia"+
" FROM detalles_factura"+alias+" df,tipo_gasto tg "+
" WHERE df.id_tipo_gasto=tg.id_tipo_gasto and tg.id_tip_gas_gen = 5 and anio_siniestro="+Integer.parseInt(request.getParameter("p"))+""+
" GROUP BY df.id_factura) amb ON fact.id_factura = amb.id_factura_ambulancia "+
" "+
" "+
" LEFT JOIN ( SELECT df.id_factura AS id_factura_farmacia, sum(df.monto_amparado) AS farmacia "+
" FROM detalles_factura"+alias+" df,tipo_gasto tg "+
" WHERE df.id_tipo_gasto=tg.id_tipo_gasto and tg.id_tip_gas_gen = 7 and anio_siniestro="+Integer.parseInt(request.getParameter("p"))+""+
" GROUP BY df.id_factura) farm ON fact.id_factura = farm.id_factura_farmacia "+
" "+
" "+
" LEFT JOIN ( SELECT df.id_factura AS id_factura_otros, sum(df.monto_amparado) AS otros "+ 
" FROM detalles_factura"+alias+" df,tipo_gasto tg "+
" WHERE df.id_tipo_gasto=tg.id_tipo_gasto and tg.id_tip_gas_gen = 8 and anio_siniestro="+Integer.parseInt(request.getParameter("p"))+""+
" GROUP BY df.id_factura) otr ON fact.id_factura = otr.id_factura_otros"+
" "+
" LEFT JOIN ( SELECT df.id_factura AS id_factura_iva, sum(df.monto_amparado) AS iva"+  
" FROM detalles_factura"+alias+" df,tipo_gasto tg "+
" WHERE df.id_tipo_gasto=tg.id_tipo_gasto and tg.id_tip_gas_gen = 11 and anio_siniestro="+Integer.parseInt(request.getParameter("p"))+""+
" GROUP BY df.id_factura) iva ON fact.id_factura = iva.id_factura_iva) AS FINAL ORDER BY FECFAC");
					rptfilename = "/jasper/RetencionISRL.jasper";}
				}
			}
			if (accion.equals("3")) { // //
				if (p.getTipoProveedor().getId_tipo_preOrden() == Constantes.TipoPreOrdenFarmacia) {
					if (totales=="1"||"1".equals(totales)){
						log.info("3TOTALES ES   SSSSS "+totales);
						mapa.put("sql", "SELECT * FROM (select sum(fact.monto_total) as mtofac,to_char(fact.fecha_factura,'MM') as mes,fact.porcentaje_isrl,fact.porcentaje_timbre,0::double precision as base_imponible_isrl,( COALESCE(farmacia, 0::double precision)) as MTOLIQ,0::double precision as MTOISR,fact.porcentaje_timbre * (COALESCE(farmacia, 0::double precision))/100 as MTOTMF from (select siniestro.aniomes_codigo||'-'||siniestro.codigo_siniestro as siniestro,subcodigo_siniestro as subsiniestro,facturas.fecha_factura,facturas.nro_factura,facturas.monto_total,id_factura,porcentaje_isrl,porcentaje_timbre,porcentaje_iva,facturas.anio_siniestro from facturas"+alias+" facturas,siniestro"+alias+" siniestro,pre_orden_pago"+alias+" pre_orden_pago where facturas.pre_orden=pre_orden_pago.pre_orden and facturas.id_siniestro=siniestro.id_siniestro and is_factura is true and facturas.anio_siniestro="+Integer.parseInt(request.getParameter("p"))+" and siniestro.anio_siniestro="+Integer.parseInt(request.getParameter("p"))+" and pre_orden_pago.id_estatus IN (2,3,4)  and pre_orden_pago.id_proveedor="+request.getParameter("c")+") fact LEFT JOIN ( SELECT df.id_factura AS id_factura_farmacia, sum(df.monto_amparado) AS farmacia FROM detalles_factura"+alias+" df,tipo_gasto tg WHERE df.id_tipo_gasto=tg.id_tipo_gasto and tg.id_tip_gas_gen = 7 and anio_siniestro="+Integer.parseInt(request.getParameter("p"))+" GROUP BY df.id_factura) farm ON fact.id_factura = farm.id_factura_farmacia group by mes,fact.porcentaje_isrl,fact.porcentaje_timbre,base_imponible_isrl,MTOLIQ,MTOISR,MTOTMF) AS FINAL ORDER BY mes");
						rptfilename = "/jasper/Retencion1xMILFarmaciaTOTALES.jasper";
					}else{
					mapa.put("sql", "SELECT * FROM (select fact.siniestro as NROSIN,fact.subsiniestro as NROPAG,fact.fecha_factura as FECFAC,fact.nro_factura as NROFAC,fact.monto_total as MTOFAC,fact.porcentaje_isrl,fact.porcentaje_timbre,0::double precision as base_imponible_isrl,( COALESCE(farmacia, 0::double precision)) as MTOLIQ,0::double precision as MTOISR,fact.porcentaje_timbre * (COALESCE(farmacia, 0::double precision))/100 as MTOTMF from (select siniestro.aniomes_codigo||'-'||siniestro.codigo_siniestro as siniestro,subcodigo_siniestro as subsiniestro,facturas.fecha_factura,facturas.nro_factura,facturas.monto_total,id_factura,porcentaje_isrl,porcentaje_timbre,porcentaje_iva,facturas.anio_siniestro from facturas"+alias+" facturas,siniestro"+alias+" siniestro,pre_orden_pago"+alias+" pre_orden_pago where  to_char(facturas.fecha_factura,'MM')::int="+mes+" and facturas.pre_orden=pre_orden_pago.pre_orden and facturas.id_siniestro=siniestro.id_siniestro and is_factura is true and facturas.anio_siniestro="+Integer.parseInt(request.getParameter("p"))+" and siniestro.anio_siniestro="+Integer.parseInt(request.getParameter("p"))+" and pre_orden_pago.id_estatus IN (2,3,4)  and pre_orden_pago.id_proveedor="+request.getParameter("c")+") fact LEFT JOIN ( SELECT df.id_factura AS id_factura_farmacia, sum(df.monto_amparado) AS farmacia FROM detalles_factura"+alias+" df,tipo_gasto tg WHERE df.id_tipo_gasto=tg.id_tipo_gasto and tg.id_tip_gas_gen = 7 and anio_siniestro="+Integer.parseInt(request.getParameter("p"))+" GROUP BY df.id_factura) farm ON fact.id_factura = farm.id_factura_farmacia) AS FINAL ORDER BY FECFAC");
					rptfilename = "/jasper/Retencion1xMILFarmacia.jasper";}
				} else {
					if (totales=="1"||"1".equals(totales)){
						log.info("4TOTALES ES   SSSSS "+totales);
						mapa.put("sql", "SELECT * FROM ("+
								" select"+
								" sum(fact.monto_total) as mtofac, "+
								" to_char(fact.fecha_factura,'MM') as mes,"+
								
								" fact.porcentaje_isrl, "+
								" fact.porcentaje_timbre,"+
								" "+
								" "+
								" sum((COALESCE(gastos_clinicos, 0::double precision) + "+
								" COALESCE(odontologia, 0::double precision)  + "+
								"  COALESCE(funerarios, 0::double precision) + "+
								" COALESCE(otros, 0::double precision) +"+
								" COALESCE(vida, 0::double precision) +"+
								"  COALESCE(material_iva, 0::double precision) +"+
								"  COALESCE(material, 0::double precision) ))  as base_imponible_isrl, "+
								" "+
								" sum((COALESCE(gastos_clinicos, 0::double precision) + "+
								" COALESCE(odontologia, 0::double precision)  + "+
								" COALESCE(funerarios, 0::double precision) + "+
								"  COALESCE(otros, 0::double precision) + "+
								"  COALESCE(material, 0::double precision) + "+
								"  COALESCE(material_iva, 0::double precision) + "+
								"  COALESCE(vida, 0::double precision) + "+
								"  COALESCE(honorarios_medicos, 0::double precision) +  "+
								"  COALESCE(farmacia, 0::double precision) +"+
								"  COALESCE(iva, 0::double precision))) as MTOLIQ, "+
								" "+
								" sum(fact.porcentaje_isrl * (COALESCE(gastos_clinicos, 0::double precision) + "+
								"  COALESCE(odontologia, 0::double precision)  + "+
								" COALESCE(funerarios, 0::double precision) + "+
								" COALESCE(otros, 0::double precision) +"+
								" COALESCE(vida, 0::double precision) +"+
								"  COALESCE(material_iva, 0::double precision) +"+
								"  COALESCE(material, 0::double precision) )/100) as MTOISR, "+
								"  "+
								" sum(fact.porcentaje_timbre * (COALESCE(gastos_clinicos, 0::double precision) + "+
								"  COALESCE(odontologia, 0::double precision)  + "+
								" COALESCE(funerarios, 0::double precision) + "+
								" COALESCE(otros, 0::double precision) + "+
								" COALESCE(material, 0::double precision) + "+
								"  COALESCE(material_iva, 0::double precision) + "+
								"  COALESCE(vida, 0::double precision) + "+
								" COALESCE(honorarios_medicos, 0::double precision) +  "+
								"  COALESCE(farmacia, 0::double precision) "+
								"  )/100) as MTOTMF"+
								"   "+
								" from "+
								" "+
								" (select"+
								" siniestro.aniomes_codigo||'-'||siniestro.codigo_siniestro as siniestro, "+
								" subcodigo_siniestro as subsiniestro, "+
								" facturas.fecha_factura, "+
								" facturas.nro_factura,"+
								" facturas.monto_total,"+
								" id_factura, "+
								" porcentaje_isrl, "+
								" porcentaje_timbre, "+
								" porcentaje_iva, "+
								" facturas.anio_siniestro"+
								" "+
								" from facturas"+alias+" facturas,siniestro"+alias+" siniestro,pre_orden_pago"+alias+" pre_orden_pago "+
								" where "+
								" facturas.pre_orden=pre_orden_pago.pre_orden and "+
								" facturas.id_siniestro=siniestro.id_siniestro and "+
								" is_factura is true and facturas.anio_siniestro="+Integer.parseInt(request.getParameter("p"))+" and siniestro.anio_siniestro="+Integer.parseInt(request.getParameter("p"))+" and pre_orden_pago.id_estatus IN (2,3,4) and pre_orden_pago.aplica_timbre=1 and pre_orden_pago.id_proveedor="+request.getParameter("c")+") fact"+ 
								" "+	
								" LEFT JOIN ( SELECT df.id_factura AS id_facturagc, sum(df.monto_amparado) AS gastos_clinicos "+
								" FROM detalles_factura"+alias+" df,tipo_gasto tg "+
								" WHERE df.id_tipo_gasto=tg.id_tipo_gasto and tg.id_tip_gas_gen = 3  and anio_siniestro="+Integer.parseInt(request.getParameter("p"))+""+
								" GROUP BY df.id_factura) gc ON fact.id_factura = gc.id_facturagc  "+
								" "+
								" "+
								" LEFT JOIN ( SELECT df.id_factura AS id_facturahm, sum(df.monto_amparado) AS honorarios_medicos "+ 
								" FROM detalles_factura"+alias+" df,tipo_gasto tg "+
								" WHERE df.id_tipo_gasto=tg.id_tipo_gasto and tg.id_tip_gas_gen = 6 and anio_siniestro="+Integer.parseInt(request.getParameter("p"))+""+
								" GROUP BY df.id_factura) hm ON fact.id_factura = hm.id_facturahm "+
								" "+
								" "+ 					
								"  LEFT JOIN ( SELECT df.id_factura AS id_factura_mativa, sum(df.monto_amparado) AS material_iva  "+
								" FROM detalles_factura"+alias+" df,tipo_gasto tg "+
								" WHERE df.id_tipo_gasto=tg.id_tipo_gasto and tg.id_tip_gas_gen = 9 and anio_siniestro="+Integer.parseInt(request.getParameter("p"))+""+
								" GROUP BY df.id_factura) mmi ON fact.id_factura = mmi.id_factura_mativa "+
								" "+
								" "+
								" LEFT JOIN ( SELECT df.id_factura AS id_factura_mat, sum(df.monto_amparado) AS material  "+
								" FROM detalles_factura"+alias+" df,tipo_gasto tg  "+
								" WHERE df.id_tipo_gasto=tg.id_tipo_gasto and tg.id_tip_gas_gen = 10  and anio_siniestro="+Integer.parseInt(request.getParameter("p"))+""+
								" GROUP BY df.id_factura) mm ON fact.id_factura = mm.id_factura_mat "+
								" "+
								" "+
								" LEFT JOIN ( SELECT df.id_factura AS id_factura_odo, sum(df.monto_amparado) AS odontologia  "+
								" FROM detalles_factura"+alias+" df,tipo_gasto tg  "+
								" WHERE df.id_tipo_gasto=tg.id_tipo_gasto and tg.id_tip_gas_gen = 1 and anio_siniestro="+Integer.parseInt(request.getParameter("p"))+""+
								" GROUP BY df.id_factura) od ON fact.id_factura = od.id_factura_odo "+
								" "+
								" "+
								" LEFT JOIN ( SELECT df.id_factura AS id_factura_fun, sum(df.monto_amparado) AS funerarios "+
								" FROM detalles_factura"+alias+" df,tipo_gasto tg  "+
								" WHERE df.id_tipo_gasto=tg.id_tipo_gasto and tg.id_tip_gas_gen = 2  and anio_siniestro="+Integer.parseInt(request.getParameter("p"))+""+
								" GROUP BY df.id_factura) fu ON fact.id_factura = fu.id_factura_fun  "+
								" "+
								" "+
								" LEFT JOIN ( SELECT df.id_factura AS id_factura_vida, sum(df.monto_amparado) AS vida  "+
								" FROM detalles_factura"+alias+" df,tipo_gasto tg  "+
								" WHERE df.id_tipo_gasto=tg.id_tipo_gasto and tg.id_tip_gas_gen = 4 and anio_siniestro="+Integer.parseInt(request.getParameter("p"))+""+
								" GROUP BY df.id_factura) vid ON fact.id_factura = vid.id_factura_vida "+
								" "+
								" "+
								" LEFT JOIN ( SELECT df.id_factura AS id_factura_ambulancia, sum(df.monto_amparado) AS ambulancia"+
								" FROM detalles_factura"+alias+" df,tipo_gasto tg "+
								" WHERE df.id_tipo_gasto=tg.id_tipo_gasto and tg.id_tip_gas_gen = 5 and anio_siniestro="+Integer.parseInt(request.getParameter("p"))+""+
								" GROUP BY df.id_factura) amb ON fact.id_factura = amb.id_factura_ambulancia "+
								" "+
								" "+
								" LEFT JOIN ( SELECT df.id_factura AS id_factura_farmacia, sum(df.monto_amparado) AS farmacia "+
								" FROM detalles_factura"+alias+" df,tipo_gasto tg "+
								" WHERE df.id_tipo_gasto=tg.id_tipo_gasto and tg.id_tip_gas_gen = 7 and anio_siniestro="+Integer.parseInt(request.getParameter("p"))+""+
								" GROUP BY df.id_factura) farm ON fact.id_factura = farm.id_factura_farmacia "+
								" "+
								" "+
								" LEFT JOIN ( SELECT df.id_factura AS id_factura_otros, sum(df.monto_amparado) AS otros  "+
								" FROM detalles_factura"+alias+" df,tipo_gasto tg "+
								" WHERE df.id_tipo_gasto=tg.id_tipo_gasto and tg.id_tip_gas_gen = 8 and anio_siniestro="+Integer.parseInt(request.getParameter("p"))+""+
								" GROUP BY df.id_factura) otr ON fact.id_factura = otr.id_factura_otros"+
								" "+
								" LEFT JOIN ( SELECT df.id_factura AS id_factura_iva, sum(df.monto_amparado) AS iva  "+
								" FROM detalles_factura"+alias+" df,tipo_gasto tg "+
								" WHERE df.id_tipo_gasto=tg.id_tipo_gasto and tg.id_tip_gas_gen = 11 and anio_siniestro="+Integer.parseInt(request.getParameter("p"))+""+
								" GROUP BY df.id_factura) iva ON fact.id_factura = iva.id_factura_iva group by mes,fact.porcentaje_isrl,fact.porcentaje_timbre) AS FINAL  ORDER BY mes");
												
												
													rptfilename = "/jasper/Retencion1xMILTOTALES.jasper";
					}else{
					mapa.put("sql", "SELECT * FROM ("+
" select"+
" fact.siniestro as NROSIN,"+
" fact.subsiniestro as NROPAG,"+
" fact.fecha_factura as FECFAC, "+
" fact.nro_factura as NROFAC, "+
" fact.monto_total as MTOFAC,"+
" fact.porcentaje_isrl, "+
" fact.porcentaje_timbre,"+
" "+
" "+
" (COALESCE(gastos_clinicos, 0::double precision) + "+
" COALESCE(odontologia, 0::double precision)  + "+
"  COALESCE(funerarios, 0::double precision) + "+
" COALESCE(otros, 0::double precision) +"+
" COALESCE(vida, 0::double precision) +"+
"  COALESCE(material_iva, 0::double precision) +"+
"  COALESCE(material, 0::double precision) )  as base_imponible_isrl, "+
" "+
" (COALESCE(gastos_clinicos, 0::double precision) + "+
" COALESCE(odontologia, 0::double precision)  + "+
" COALESCE(funerarios, 0::double precision) + "+
"  COALESCE(otros, 0::double precision) + "+
"  COALESCE(material, 0::double precision) + "+
"  COALESCE(material_iva, 0::double precision) + "+
"  COALESCE(vida, 0::double precision) + "+
"  COALESCE(honorarios_medicos, 0::double precision) +  "+
"  COALESCE(farmacia, 0::double precision) +"+
"  COALESCE(iva, 0::double precision)) as MTOLIQ, "+
" "+
" fact.porcentaje_isrl * (COALESCE(gastos_clinicos, 0::double precision) + "+
"  COALESCE(odontologia, 0::double precision)  + "+
" COALESCE(funerarios, 0::double precision) + "+
" COALESCE(otros, 0::double precision) +"+
" COALESCE(vida, 0::double precision) +"+
"  COALESCE(material_iva, 0::double precision) +"+
"  COALESCE(material, 0::double precision) )/100 as MTOISR, "+
"  "+
" fact.porcentaje_timbre * (COALESCE(gastos_clinicos, 0::double precision) + "+
"  COALESCE(odontologia, 0::double precision)  + "+
" COALESCE(funerarios, 0::double precision) + "+
" COALESCE(otros, 0::double precision) + "+
" COALESCE(material, 0::double precision) + "+
"  COALESCE(material_iva, 0::double precision) + "+
"  COALESCE(vida, 0::double precision) + "+
" COALESCE(honorarios_medicos, 0::double precision) +  "+
"  COALESCE(farmacia, 0::double precision) "+
"  )/100 as MTOTMF"+
"   "+
" from "+
" "+
" (select"+
" siniestro.aniomes_codigo||'-'||siniestro.codigo_siniestro as siniestro, "+
" subcodigo_siniestro as subsiniestro, "+
" facturas.fecha_factura, "+
" facturas.nro_factura,"+
" facturas.monto_total,"+
" id_factura, "+
" porcentaje_isrl, "+
" porcentaje_timbre, "+
" porcentaje_iva, "+
" facturas.anio_siniestro"+
" "+
" from facturas"+alias+" facturas,siniestro"+alias+" siniestro,pre_orden_pago"+alias+" pre_orden_pago "+
" where  to_char(facturas.fecha_factura,'MM')::int="+mes+" and "+
" facturas.pre_orden=pre_orden_pago.pre_orden and "+
" facturas.id_siniestro=siniestro.id_siniestro and "+
" is_factura is true and facturas.anio_siniestro="+Integer.parseInt(request.getParameter("p"))+" and siniestro.anio_siniestro="+Integer.parseInt(request.getParameter("p"))+" and pre_orden_pago.id_estatus IN (2,3,4) and pre_orden_pago.aplica_timbre=1 and pre_orden_pago.id_proveedor="+request.getParameter("c")+") fact"+ 
" "+	
" LEFT JOIN ( SELECT df.id_factura AS id_facturagc, sum(df.monto_amparado) AS gastos_clinicos "+
" FROM detalles_factura"+alias+" df,tipo_gasto tg "+
" WHERE df.id_tipo_gasto=tg.id_tipo_gasto and tg.id_tip_gas_gen = 3  and anio_siniestro="+Integer.parseInt(request.getParameter("p"))+""+
" GROUP BY df.id_factura) gc ON fact.id_factura = gc.id_facturagc  "+
" "+
" "+
" LEFT JOIN ( SELECT df.id_factura AS id_facturahm, sum(df.monto_amparado) AS honorarios_medicos "+ 
" FROM detalles_factura"+alias+" df,tipo_gasto tg "+
" WHERE df.id_tipo_gasto=tg.id_tipo_gasto and tg.id_tip_gas_gen = 6 and anio_siniestro="+Integer.parseInt(request.getParameter("p"))+""+
" GROUP BY df.id_factura) hm ON fact.id_factura = hm.id_facturahm "+
" "+
" "+ 					
"  LEFT JOIN ( SELECT df.id_factura AS id_factura_mativa, sum(df.monto_amparado) AS material_iva  "+
" FROM detalles_factura"+alias+" df,tipo_gasto tg "+
" WHERE df.id_tipo_gasto=tg.id_tipo_gasto and tg.id_tip_gas_gen = 9 and anio_siniestro="+Integer.parseInt(request.getParameter("p"))+""+
" GROUP BY df.id_factura) mmi ON fact.id_factura = mmi.id_factura_mativa "+
" "+
" "+
" LEFT JOIN ( SELECT df.id_factura AS id_factura_mat, sum(df.monto_amparado) AS material  "+
" FROM detalles_factura"+alias+" df,tipo_gasto tg  "+
" WHERE df.id_tipo_gasto=tg.id_tipo_gasto and tg.id_tip_gas_gen = 10  and anio_siniestro="+Integer.parseInt(request.getParameter("p"))+""+
" GROUP BY df.id_factura) mm ON fact.id_factura = mm.id_factura_mat "+
" "+
" "+
" LEFT JOIN ( SELECT df.id_factura AS id_factura_odo, sum(df.monto_amparado) AS odontologia  "+
" FROM detalles_factura"+alias+" df,tipo_gasto tg  "+
" WHERE df.id_tipo_gasto=tg.id_tipo_gasto and tg.id_tip_gas_gen = 1 and anio_siniestro="+Integer.parseInt(request.getParameter("p"))+""+
" GROUP BY df.id_factura) od ON fact.id_factura = od.id_factura_odo "+
" "+
" "+
" LEFT JOIN ( SELECT df.id_factura AS id_factura_fun, sum(df.monto_amparado) AS funerarios "+
" FROM detalles_factura"+alias+" df,tipo_gasto tg  "+
" WHERE df.id_tipo_gasto=tg.id_tipo_gasto and tg.id_tip_gas_gen = 2  and anio_siniestro="+Integer.parseInt(request.getParameter("p"))+""+
" GROUP BY df.id_factura) fu ON fact.id_factura = fu.id_factura_fun  "+
" "+
" "+
" LEFT JOIN ( SELECT df.id_factura AS id_factura_vida, sum(df.monto_amparado) AS vida  "+
" FROM detalles_factura"+alias+" df,tipo_gasto tg  "+
" WHERE df.id_tipo_gasto=tg.id_tipo_gasto and tg.id_tip_gas_gen = 4 and anio_siniestro="+Integer.parseInt(request.getParameter("p"))+""+
" GROUP BY df.id_factura) vid ON fact.id_factura = vid.id_factura_vida "+
" "+
" "+
" LEFT JOIN ( SELECT df.id_factura AS id_factura_ambulancia, sum(df.monto_amparado) AS ambulancia"+
" FROM detalles_factura"+alias+" df,tipo_gasto tg "+
" WHERE df.id_tipo_gasto=tg.id_tipo_gasto and tg.id_tip_gas_gen = 5 and anio_siniestro="+Integer.parseInt(request.getParameter("p"))+""+
" GROUP BY df.id_factura) amb ON fact.id_factura = amb.id_factura_ambulancia "+
" "+
" "+
" LEFT JOIN ( SELECT df.id_factura AS id_factura_farmacia, sum(df.monto_amparado) AS farmacia "+
" FROM detalles_factura"+alias+" df,tipo_gasto tg "+
" WHERE df.id_tipo_gasto=tg.id_tipo_gasto and tg.id_tip_gas_gen = 7 and anio_siniestro="+Integer.parseInt(request.getParameter("p"))+""+
" GROUP BY df.id_factura) farm ON fact.id_factura = farm.id_factura_farmacia "+
" "+
" "+
" LEFT JOIN ( SELECT df.id_factura AS id_factura_otros, sum(df.monto_amparado) AS otros  "+
" FROM detalles_factura"+alias+" df,tipo_gasto tg "+
" WHERE df.id_tipo_gasto=tg.id_tipo_gasto and tg.id_tip_gas_gen = 8 and anio_siniestro="+Integer.parseInt(request.getParameter("p"))+""+
" GROUP BY df.id_factura) otr ON fact.id_factura = otr.id_factura_otros"+
" "+
" LEFT JOIN ( SELECT df.id_factura AS id_factura_iva, sum(df.monto_amparado) AS iva  "+
" FROM detalles_factura"+alias+" df,tipo_gasto tg "+
" WHERE df.id_tipo_gasto=tg.id_tipo_gasto and tg.id_tip_gas_gen = 11 and anio_siniestro="+Integer.parseInt(request.getParameter("p"))+""+
" GROUP BY df.id_factura) iva ON fact.id_factura = iva.id_factura_iva) AS FINAL ORDER BY FECFAC");
				
				
					rptfilename = "/jasper/Retencion1xMIL.jasper";}
				}
			}
			path = realPath + rptfilename;
			// LLENAR EL INFORME
			jasperprint = JasperFillManager.fillReport(path, mapa, db);
			// EXPORTAR EL INFORME A FORMATO PDF.
			pdfasbytes = JasperExportManager.exportReportToPdf(jasperprint);
			outstream2 = response.getOutputStream();
			response.setContentType("application/pdf");
			response.setContentLength(pdfasbytes.length);
			response.setHeader("Pragma", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setHeader("Content-disposition", "inline; filename=\"Report" + date + ".pdf\"");
			outstream2.write(pdfasbytes);
		} finally {
			
			Conexion.closeConexion(db);
		}
		return null;
	}
}