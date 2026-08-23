/**04/05/2015
 * barrjime
 */
package ve.gob.dem.fasdem.action.reportes;



import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import ve.gob.dem.framework.cnx.Conexion;
import ve.gob.dem.fasdem.bean.Clinica;
import ve.gob.dem.fasdem.bean.Factura;
import ve.gob.dem.fasdem.bean.HojaRuta;
import ve.gob.dem.fasdem.bean.Persona;
import ve.gob.dem.fasdem.bean.PreOrdenPago;
import ve.gob.dem.fasdem.bean.ResumenPreOrdenPago;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.exp.ExpClinica;
import ve.gob.dem.fasdem.exp.ExpHojaRuta;
import ve.gob.dem.fasdem.exp.pagos.ExpPreOrdenPago;
import ve.gob.dem.fasdem.exp.pagos.ExpTipoProveedor;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.global.GenericAction;
import ve.gob.dem.framework.recursos.Constantes;
import ve.gob.dem.framework.recursos.Parametros;
import ve.gob.dem.framework.recursos.Utilidad;
import ve.gob.dem.framework.seguridad.bean.Usuario;
import ve.gob.dem.framework.seguridad.exp.ExpUsuario;

public class PrintRetencionOrden extends GenericAction {


    static protected Logger log = Logger.getLogger(PrintRetencionOrden.class);

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	
    	HttpSession session = request.getSession();
    	Persona tmp = new Persona();
    	Persona titular = new Persona();
    	
    	Usuario usuario = null;
    	Usuario usuario1 = null;
    	
    	usuario = (Usuario) session.getAttribute("usuario");
    	usuario1=ExpUsuario.buscarDatosP(usuario.getLogin());
    	
    	
    	ActionMessages am = new ActionMessages();
    	Entorno ent = new Entorno(Entorno.MOD_NINGUNO);

    	
    	try {
    		validarAction(request, form, ent, am, this.getClass());
    	} catch (PersonalNotFillItems e) {
    		return mapping.findForward(FWD_INPUT);
    	}

				    	String sql="";
						String alias="";
				    	HashMap mapa =new HashMap();
						JasperPrint jasperprint;
						String realPath = getServlet().getServletContext().getRealPath("/");
						String path;
						String rptfilename="";	
						ServletOutputStream outstream2;
						byte[] pdfasbytes;
						ServletOutputStream outstream;
						mapa.put("ruta", realPath + "images/");
						mapa.put("ruta_subrep", realPath );
				        String pre_orden = request.getParameter("h");
				        String mes = String.format("%02d",Integer.parseInt(request.getParameter("mes")));
				        log.info("mes "+ mes);
				        PreOrdenPago iva_vacio = ExpPreOrdenPago.buscarIvaVacio(pre_orden);
				        log.info("buscarIvaVacio "+ iva_vacio.getIva());
				        log.info("buscarIvaVacio "+ iva_vacio.getMtoisr());
				        log.info("buscarIvaVacio "+ iva_vacio.getMtotmf());
				        String id_pro = request.getParameter("c");
				        String periodo = request.getParameter("p");
				        String totales = Parametros.getStrParameter(request, "totales", "0");
				        log.info("totales "+ totales);
						mapa.put("REPORT_LOCALE",  new Locale("es", "VE"));
						String date = Utilidad.DateToString(new Date(), "ddMMyyyyhhmmss");
					 	mapa.put("sql", sql);
					 	mapa.put("mes", mes);
						mapa.put("USUARIO", usuario1.getDescripcion_usuario());
						Clinica p = ExpClinica.BuscarPorid(Integer.parseInt(request.getParameter("c")));
						
						
						
						mapa.put("CLINICA", p.getNombre());
						mapa.put("DIRECCION_CLINICA", p.getDireccion());
						mapa.put("TELEFONO_CLINICA", p.getTelefono());
						mapa.put("RIF_FORMATEADO", p.getRif());
						
                        int anio = Integer.parseInt(request.getParameter("p"));
						Connection db = null;
						
					 try {	
						 mapa.put("anio", anio);
						   db =   Conexion.getConexion();
						 //ajusta el sql para pasarlo
							
//							String sql="";
//							String alias="";
							
							switch (anio)
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
							log.info("ALIAS "+ alias);
							
							sql= " SELECT * FROM (select fact.siniestro as NROSIN, fact.subsiniestro as NROPAG, fact.fecha_factura as FECFAC,  fact.nro_factura as NROFAC,  fact.monto_total as MTOFAC, fact.porcentaje_isrl,hrpo.monto_pre_orden,"
									+ " fact.porcentaje_timbre,hrpo.monto_isrl AS mtoisr, hrpo.monto_timbre as mtotmf, fact.pre_orden, hrpo.monto_liquidado as mtoliq, hrpo.id_hoja_ruta as orden_pago, monto_iva,fact.porcentaje_iva,"
									+ "(COALESCE(gastos_clinicos, 0::double precision) +   COALESCE(odontologia, 0::double precision)  +  COALESCE(funerarios, 0::double precision) +  COALESCE(otros, 0::double precision) +  COALESCE(vida, 0::double precision) + COALESCE(material_iva, 0::double precision) + COALESCE(material, 0::double precision) )  as base_imponible_isrl"
									+ ",hrpo.base_iva AS base_iva,to_char(hrpo.id_preorden,'00000000') as id_preorden, hrpo.iva "
									+ "from (select siniestro.aniomes_codigo||'-'||siniestro.codigo_siniestro as siniestro,  subcodigo_siniestro as subsiniestro,  facturas.fecha_factura,  facturas.nro_factura, facturas.pre_orden,facturas.monto_total, id_factura,  porcentaje_isrl,  porcentaje_timbre,  porcentaje_iva,  facturas.anio_siniestro "
									+ "from facturas"+alias+" facturas,siniestro"+alias+" siniestro,pre_orden_pago"+alias+" pre_orden_pago  where  facturas.pre_orden=pre_orden_pago.pre_orden and  to_char(facturas.fecha_factura,'MM')::int="+mes+" and facturas.id_siniestro=siniestro.id_siniestro and "
									+ " is_factura is true and facturas.anio_siniestro="+periodo+" and siniestro.anio_siniestro="+periodo+" and pre_orden_pago.id_estatus IN (2,3,4)  and pre_orden_pago.id_proveedor="+id_pro+" and facturas.pre_orden='"+pre_orden+"') fact "
									+ "LEFT JOIN ( SELECT df.id_factura AS id_facturagc, sum(df.monto_amparado) AS gastos_clinicos  FROM detalles_factura"+alias+" df,tipo_gasto tg  WHERE df.id_tipo_gasto=tg.id_tipo_gasto and tg.id_tip_gas_gen = 3  and anio_siniestro="+periodo+" GROUP BY df.id_factura) gc ON fact.id_factura = gc.id_facturagc  "
									+ "LEFT JOIN ( SELECT df.id_factura AS id_facturahm, sum(df.monto_amparado) AS honorarios_medicos FROM detalles_factura"+alias+" df,tipo_gasto tg  WHERE df.id_tipo_gasto=tg.id_tipo_gasto and tg.id_tip_gas_gen = 6 and anio_siniestro="+periodo+" GROUP BY df.id_factura) hm ON fact.id_factura = hm.id_facturahm  "
									+ "LEFT JOIN ( SELECT df.id_factura AS id_factura_mativa, sum(df.monto_amparado) AS material_iva   FROM detalles_factura"+alias+" df,tipo_gasto tg  WHERE df.id_tipo_gasto=tg.id_tipo_gasto and tg.id_tip_gas_gen = 9 and anio_siniestro="+periodo+" GROUP BY df.id_factura) mmi ON fact.id_factura = mmi.id_factura_mativa   "
									+ "LEFT JOIN ( SELECT df.id_factura AS id_factura_mat, sum(df.monto_amparado) AS material  FROM detalles_factura"+alias+" df,tipo_gasto tg   WHERE df.id_tipo_gasto=tg.id_tipo_gasto and tg.id_tip_gas_gen = 10  and anio_siniestro="+periodo+" GROUP BY df.id_factura) mm ON fact.id_factura = mm.id_factura_mat    "
									+ "LEFT JOIN ( SELECT df.id_factura AS id_factura_odo, sum(df.monto_amparado) AS odontologia  FROM detalles_factura"+alias+" df,tipo_gasto tg   WHERE df.id_tipo_gasto=tg.id_tipo_gasto and tg.id_tip_gas_gen = 1 and anio_siniestro="+periodo+" GROUP BY df.id_factura) od ON fact.id_factura = od.id_factura_odo  "
									+ "LEFT JOIN ( SELECT df.id_factura AS id_factura_fun, sum(df.monto_amparado) AS funerarios  FROM detalles_factura"+alias+" df,tipo_gasto tg   WHERE df.id_tipo_gasto=tg.id_tipo_gasto and tg.id_tip_gas_gen = 2  and anio_siniestro="+periodo+" GROUP BY df.id_factura) fu ON fact.id_factura = fu.id_factura_fun   "
									+ "LEFT JOIN ( SELECT df.id_factura AS id_factura_vida, sum(df.monto_amparado) AS vida   FROM detalles_factura"+alias+" df,tipo_gasto tg   WHERE df.id_tipo_gasto=tg.id_tipo_gasto and tg.id_tip_gas_gen = 4 and anio_siniestro="+periodo+" GROUP BY df.id_factura) vid ON fact.id_factura = vid.id_factura_vida  "
									+ "LEFT JOIN ( SELECT df.id_factura AS id_factura_ambulancia, sum(df.monto_amparado) AS ambulancia FROM detalles_factura"+alias+" df,tipo_gasto tg  WHERE df.id_tipo_gasto=tg.id_tipo_gasto and tg.id_tip_gas_gen = 5 and anio_siniestro="+periodo+" GROUP BY df.id_factura) amb ON fact.id_factura = amb.id_factura_ambulancia  "
									+ "LEFT JOIN ( SELECT df.id_factura AS id_factura_farmacia, sum(df.monto_amparado) AS farmacia  FROM detalles_factura"+alias+" df,tipo_gasto tg  WHERE df.id_tipo_gasto=tg.id_tipo_gasto and tg.id_tip_gas_gen = 7 and anio_siniestro="+periodo+" GROUP BY df.id_factura) farm ON fact.id_factura = farm.id_factura_farmacia   "
									+ "LEFT JOIN ( SELECT df.id_factura AS id_factura_otros, sum(df.monto_amparado) AS otros  FROM detalles_factura"+alias+" df,tipo_gasto tg  WHERE df.id_tipo_gasto=tg.id_tipo_gasto and tg.id_tip_gas_gen = 8 and anio_siniestro="+periodo+" GROUP BY df.id_factura) otr ON fact.id_factura = otr.id_factura_otros  "
									+ "LEFT JOIN ( SELECT df.id_factura AS id_factura_iva, sum(df.monto_amparado) AS iva FROM detalles_factura"+alias+" df,tipo_gasto tg  WHERE df.id_tipo_gasto=tg.id_tipo_gasto and tg.id_tip_gas_gen = 11 and anio_siniestro="+periodo+" GROUP BY df.id_factura) iva ON fact.id_factura = iva.id_factura_iva,"
									+ "(select * from hoja_ruta_preorden where codigo='"+pre_orden+"' and rechazo='1') hrpo"
									+ ") AS FINAL ORDER BY FECFAC";
							

							if (totales=="0"||"0".equals(totales)){
							
								if(iva_vacio.getMtoisr()==0){
									log.info("reporte vacio ISRL");
									JOptionPane.showMessageDialog(null,"Reporte ISRL Vacio"); 
									Conexion.closeConexion(db);
									return null;
								}else{
							log.info("reporte islr ");
							rptfilename ="/jasper/RetencionOrdenIsrl.jasper"; 
								}
							}else if (totales=="1"||"1".equals(totales)){

								if(iva_vacio.getMtotmf()==0){
									log.info("reporte vacio 1x1000");
									JOptionPane.showMessageDialog(null,"Reporte 1x1000 Vacio"); 
									Conexion.closeConexion(db);
									return null;
								}else{
								log.info("reporte 1x1000 ");								
								rptfilename ="/jasper/Retencion1xMILOrden.jasper"; 
								}
							}else{
								if(iva_vacio.getIva()==0){
									log.info("reporte vacio");
									JOptionPane.showMessageDialog(null,"Reporte Iva Vacio"); 
									Conexion.closeConexion(db);
									return null;
								}else{
									log.info("reporte IVA ");
								rptfilename ="/jasper/RetencionIva.jasper";
								}
							}

						   	mapa.put("sql", sql);
							log.info("sql ewrw"+sql);
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
						   
						   
						   incluirTraza(TR_PAGO_IMPRIMIR_HOJA_RUTA,
								   request.getParameter("h"),
									"Se Imprimio la Hoja de Ruta N° " + request.getParameter("h"), usuarioSession(request));
							
						
					} finally {
					    Conexion.closeConexion(db);
					}
					return null;
    }

}