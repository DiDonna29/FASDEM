/**15/07/2014
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

public class PrintHojaBanco extends GenericAction {


    static protected Logger log = Logger.getLogger(PrintHojaBanco.class);

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
							sql= "select * from (select numero,"
									+ " monto_pre_orden,codigo,causado,fecha_hoja_ruta, hoja_ruta.fecha,tipo_hoja_ruta.descripcion AS OBSERVACION,periodo_fiscal,pre_orden_pago"+alias+".id_tipo_empleado,monto_fijo,monto_contratado,monto_obrero,monto_jubilado,monto_incapacitado "
											+ "from hoja_ruta_preorden, hoja_ruta, pre_orden_pago"+alias+", proveedor, tipo_hoja_ruta where pre_orden_pago"+alias+".anio_preordenpago = "+anio+"  AND hoja_ruta.id_hoja_ruta=hoja_ruta_preorden.id_hoja_ruta and pre_orden_pago"+alias+".id_pre_orden=hoja_ruta_preorden.id_preorden and pre_orden_pago"+alias+".anio_preordenpago=hoja_ruta_preorden.anio_preorden  and proveedor.id_proveedor=pre_orden_pago"+alias+".id_proveedor and tipo_hoja_ruta.id_tipo_hoja_ruta=hoja_ruta.tipo_hoja and hoja_ruta_preorden.id_hoja_ruta= "+hojadr+" "
													+ "union select numero,monto_neto,'','',fecha_impresion, fecha_impresion,'' AS OBSERVACION,1,0,monto_fijo,monto_contratado,monto_obrero,monto_jubilado,monto_incapacitado from relleno_hoja_ruta) v order by fecha_hoja_ruta asc limit 1";
						    
							Double contratados = ExpHojaRuta.contratados(h.getId());
						    Double fijos = ExpHojaRuta.fijos(h.getId());
						    Double obreros = ExpHojaRuta.obreros(h.getId());
						    Double jubilados = ExpHojaRuta.jubilados(h.getId());
						    Double incapacitados = ExpHojaRuta.incapacitados(h.getId());
							
						    Double monto = (double) 0.00;

						    monto = contratados + fijos + obreros + jubilados + incapacitados;
						    
									//***************************************
						   
							log.info("SQL REPORTE ----------> "+ sql);


							   rptfilename ="/jasper/HojaDeRutaB.jasper"; 

						  
						   	mapa.put("sql", sql);
						   	mapa.put("monto", monto);
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