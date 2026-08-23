/**15/07/2010
 * barrjime
 */
package ve.gob.dem.fasdem.action.pago;



import java.sql.Connection;
import java.util.ArrayList;
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

import ve.gob.dem.framework.cnx.Conexion;
import ve.gob.dem.fasdem.bean.HojaRuta;
import ve.gob.dem.fasdem.bean.Persona;
import ve.gob.dem.fasdem.bean.PreOrdenPago;
import ve.gob.dem.fasdem.bean.ResumenPreOrdenPago;
import ve.gob.dem.fasdem.ent.Entorno;
import ve.gob.dem.fasdem.exp.ExpHojaRuta;
import ve.gob.dem.fasdem.exp.pagos.ExpPreOrdenPago;
import ve.gob.dem.framework.exception.PersonalNotFillItems;
import ve.gob.dem.framework.global.GenericAction;
import ve.gob.dem.framework.recursos.Constantes;
import ve.gob.dem.framework.recursos.Utilidad;
import ve.gob.dem.framework.seguridad.bean.Usuario;
import ve.gob.dem.framework.seguridad.exp.ExpUsuario;

public class PrintRegistroPagoMasivo extends GenericAction {


    static protected Logger log = Logger.getLogger(PrintRegistroPagoMasivo.class);

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	
    	HttpSession session = request.getSession();
    	Persona tmp = new Persona();
    	Persona titular = new Persona();
    	
    	Usuario usuario = null;
    	Usuario usuario1 = null;
    	
    	usuario = (Usuario) session.getAttribute("usuario");
    	usuario1=ExpUsuario.buscarDatosP(usuario.getLogin());

		ArrayList RESUMEN = new ArrayList();
		ResumenPreOrdenPago r;
    	
    	
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
				        log.info("ruta_subrep" + realPath);
						mapa.put("REPORT_LOCALE",  new Locale("es", "VE"));
						String date = Utilidad.DateToString(new Date(), "ddMMyyyyhhmmss");
						mapa.put("hoja", Integer.parseInt(request.getParameter("h")));
					 	mapa.put("sql", sql);
						mapa.put("USUARIO", usuario1.getDescripcion_usuario());
                        HojaRuta h = ExpHojaRuta.BuscarPorid(Integer.parseInt(request.getParameter("h")));
						mapa.put("ANALISTA",h.getAnalista().getNombre() + " " + h.getAnalista().getApellido());
						int anio = h.getPeriodo_fiscal();
						int hojadr= h.getId();
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
							
							log.info("tipo "+ h.getTipo().getId());
							
							
							
							ArrayList list = ExpHojaRuta.buscarDetalleHoja(h.getId());
							
							
							
														
							if (h.getTipo().getId() == 3 || h.getTipo().getId() ==4) // reembolsos y acreencia remmbolsos
							{

							sql="select a.aniomes,(select tipo_empleado.descripcion from tipo_empleado where tipo_empleado.id_tipo_empleado = a.id_tipo_empleado) AS id_tipo_empleado,"
									+ " a.id_estatus,a.id_usuario,a.id_tipo_pre_orden_pago,a.id_beneficiario as cedula,b.estatus,monto_pre_orden,causado,b.codigo,b.monto_fijo,b.monto_contratado,b.monto_obrero,b.monto_jubilado,"
										+ "	b.monto_incapacitado,d.descripcion AS OBSERVACION, periodo_fiscal,numero,b.id_hoja_ruta, e.fecha"
											+ ",(select numero_cuenta from view_cuenta_beneficiario_f where cedula::integer=a.id_beneficiario and is_activo='TRUE' LIMIT 1) AS cuenta"
												+ " ,(select nombres||' '||apellidos from siniestro"+alias+"  siniestro where siniestro.anio_siniestro = a.anio_preordenpago and cedula::integer=a.id_beneficiario limit 1) as proveedor "
													+ " from pre_orden_pago a, hoja_ruta_preorden b, tipo_hoja_ruta d, hoja_ruta e where b.codigo=a.pre_orden and d.id_tipo_hoja_ruta=e.tipo_hoja and b.id_hoja_ruta=a.hoja_ruta and a.hoja_ruta="+hojadr+" AND e.id_hoja_ruta=b.id_hoja_ruta "
														+ "and a.id_pre_orden=b.id_preorden order by fecha_hoja_ruta desc";

									//***************************************
							
							  log.info("HojaDeRutaReembolsos ");

							 rptfilename ="/jasper/HojaDeRutaReembolsos.jasper"; 

							} else {
								// select reporte seguro
								
								for (int i=0;i!=list.size();i++){
									PreOrdenPago carga = new PreOrdenPago();
									carga = (PreOrdenPago) list.get(i);									
									carga.getCod_completo();
								

								RESUMEN = ExpPreOrdenPago.buscarResumenPreOrden(carga.getCod_completo(), anio);
								
								 r = new ResumenPreOrdenPago();
								
								}
								
							sql= "select * from (select numero,proveedor.descripcion AS PROVEEDOR,monto_pre_orden,codigo,causado,fecha_hoja_ruta, hoja_ruta.fecha,tipo_hoja_ruta.descripcion AS OBSERVACION,periodo_fiscal,"
									+ "monto_fijo,monto_contratado,monto_obrero,monto_jubilado,monto_incapacitado,proveedor.rif as rif,hoja_ruta_preorden.id_hoja_ruta AS hoja_ruta, base_iva,iva,"
									  + "(select codigo_cuenta from cuenta_proveedor where id_proveedor=proveedor.id_proveedor and is_activo='TRUE') as cuenta,monto_liquidado,monto_isrl,monto_iva,monto_timbre "
										+ " from hoja_ruta_preorden, hoja_ruta, pre_orden_pago"+alias+", proveedor, tipo_hoja_ruta where pre_orden_pago"+alias+".anio_preordenpago = "+anio+"  "
											+ "AND hoja_ruta.id_hoja_ruta=hoja_ruta_preorden.id_hoja_ruta and pre_orden_pago"+alias+".id_pre_orden=hoja_ruta_preorden.id_preorden and pre_orden_pago"+alias+".anio_preordenpago=hoja_ruta_preorden.anio_preorden  "
												+ "and proveedor.id_proveedor=pre_orden_pago"+alias+".id_proveedor and tipo_hoja_ruta.id_tipo_hoja_ruta=hoja_ruta.tipo_hoja and hoja_ruta_preorden.id_hoja_ruta= "+hojadr+") v order by fecha_hoja_ruta asc";

								
							log.info("HojaDeRutaSeguros ");
							
							rptfilename ="/jasper/HojaDeRutaSeguro.jasper"; 
								
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