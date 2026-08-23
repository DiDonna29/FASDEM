/*
 * Created on 20-abr-2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ve.gob.dem.framework.reporte;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Locale;

import org.apache.log4j.Logger;

import ve.gob.dem.framework.cnx.Conexion;
import ve.gob.dem.framework.exception.PersonalException;
import ve.gob.dem.framework.exception.PersonalNotFoundException;

/**
 * @author jugomez
 * 
 *         To change the template for this generated type comment go to
 *         Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ExpReporte implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -987819242454163074L;
	static Logger log = Logger.getLogger(ExpReporte.class);

	public static Reporte pintaReporte(int tipo, String anio, String usuario, String fecha, String ruta, String nombre) throws PersonalException, PersonalNotFoundException, ClassNotFoundException, IOException, SQLException {
		Connection cnx = null;
		try {
			cnx = Conexion.getConexion();
			Reporte rep = new Reporte();
			if (tipo == 1) {
				// ******** Lleno los parametros del reporte
				rep.setMapa(llenaMapaListadoExcel(usuario, fecha, ruta, anio, nombre));
				// ******** Nombre del archivo
				log.info("valoressssss"+usuario + nombre);
				rep.setArchivoReporte("CargaFacturasPorUsuario.jasper");
				return rep;
			}
			if (tipo == 2) {
				// ******** Lleno los parametros del reporte
				rep.setMapa(llenaMapaListadoExcel(usuario, fecha, ruta, anio, nombre));
				log.info("mapaaaaaaaaaaaaaaaaaaaaaaaaaa"+rep.getMapa());
				// ******** Nombre del archivo
				rep.setArchivoReporte("CantidadSiniestrosPorUsuario.jasper");
				return rep;
			}
			if (tipo == 3) {
				// ******** Lleno los parametros del reporte
				rep.setMapa(llenaMapaListadoExcelSabana(usuario, fecha, ruta, anio, nombre));
				// ******** Nombre del archivo
				rep.setArchivoReporte("DatosSiniestros.jasper");
				return rep;
			}
			if (tipo == 4) {
				// ******** Lleno los parametros del reporte
				rep.setMapa(llenaMapaListadoClinicas(ruta));
				// ******** Nombre del archivo
				rep.setArchivoReporte("ListadoClinicas.jasper");
				return rep;
			}
			if (tipo == 5) {
				// ******** Lleno los parametros del reporte
				rep.setMapa(llenaMapaListadoClinicas(ruta));
				// ******** Nombre del archivo
				rep.setArchivoReporte("ListadoAPS.jasper");
				return rep;
			}
			if (tipo == 6) {
				// ******** Lleno los parametros del reporte
				rep.setMapa(llenaMapaListadoClinicas(ruta));
				// ******** Nombre del archivo
				rep.setArchivoReporte("ListadoClinicasRazonable.jasper");
				return rep;
			}
			if (tipo == 7) {
				// ******** Lleno los parametros del reporte
				rep.setMapa(llenaMapaListadoClinicas(ruta));
				// ******** Nombre del archivo
				rep.setArchivoReporte("ListadoEstado.jasper");
				return rep;
			}
			if (tipo == 8) {
				// ******** Lleno los parametros del reporte
				rep.setMapa(llenaMapaListadoClinicas(ruta));
				// ******** Nombre del archivo
				rep.setArchivoReporte("ListadoEstadoAPS.jasper");
				return rep;
			}
			if (tipo == 9) {
				// ******** Lleno los parametros del reporte
				rep.setMapa(llenaMapaListadoClinicas(ruta));
				// ******** Nombre del archivo
				rep.setArchivoReporte("ListadoEstadoRazonable.jasper");
				return rep;
			}
			if (tipo == 10) {
				// ******** Lleno los parametros del reporte
				rep.setMapa((llenaMapaLiquidaciones(ruta, usuario,  anio, fecha)));
				
				// ******** Nombre del archivo
				rep.setArchivoReporte("Siniestrosliq.jasper");
				return rep;
			}
		} catch (Exception e) {
			log.info(e);
		} finally {
			if (cnx != null)
				Conexion.closeConexion(cnx);
		}
		return null;
	}
	
	
	public static Reporte pintaReporte(int tipo, String desde, String hasta, String ruta) throws PersonalException, PersonalNotFoundException, ClassNotFoundException, IOException, SQLException {
		Connection cnx = null;
		try {
			cnx = Conexion.getConexion();
			Reporte rep = new Reporte();
			if (tipo == 11) {
				// ******** Lleno los parametros del reporte
				rep.setMapa(llenaMapaEstadistica(ruta, desde, hasta));
				// ******** Nombre del archivo
				rep.setArchivoReporte("estadisticaxls.jasper");
				return rep;
			}
		} catch (Exception e) {
			log.info(e);
		} finally {
			if (cnx != null)
				Conexion.closeConexion(cnx);
		}
		return null;
	}
	
	private static HashMap llenaMapaListadoExcel(String usuario, String fecha, String ruta, String anio, String nombre) throws PersonalException, PersonalNotFoundException {
		/*
		 * Este pana arma el HashMap de parametros, dada una instancia de una
		 * solicitud
		 */
		HashMap params = new HashMap();
		Locale locale = new java.util.Locale("es", "VE");
		params.put("REPORT_LOCALE", locale);
		params.put("testing", "testing por codigo");
		params.put("usuario", usuario);
		params.put("fecha", fecha);
		params.put("ruta", ruta);
		params.put("anio", anio);
		params.put("NombreUsuario", nombre);
		// params.put("id_sorteo", (estado != null) ? estado : null);
		// params.put("ruta2", cadena + "jasper/");
		return params;
	}

	private static HashMap llenaMapaListadoExcelSabana(String usuario, String mes, String ruta, String anio, String tipoTramite) throws PersonalException, PersonalNotFoundException {
		/*
		 * Este pana arma el HashMap de parametros, dada una instancia de una
		 * solicitud
		 */
		HashMap params = new HashMap();
		Locale locale = new java.util.Locale("es", "VE");
		params.put("REPORT_LOCALE", locale);
		params.put("testing", "testing por codigo");
		params.put("mes", Integer.valueOf(mes));
		log.info("mes:" + Integer.valueOf(mes));
		params.put("ruta", ruta);
		log.info("ruta:" + ruta);
		params.put("anio", Integer.valueOf(anio));
		log.info("anio:" + Integer.valueOf(anio));
		// params.put("id_sorteo", (estado != null) ? estado : null);
		// params.put("ruta2", cadena + "jasper/");
		return params;
	}

	private static HashMap llenaMapaListadoClinicas(String ruta) throws PersonalException, PersonalNotFoundException {
		/*
		 * Este pana arma el HashMap de parametros, dada una instancia de una
		 * solicitud
		 */
		HashMap params = new HashMap();
		Locale locale = new java.util.Locale("es", "VE");
		params.put("REPORT_LOCALE", locale);
		//params.put("testing", "testing por codigo");
		params.put("ruta", ruta);
		return params;
	}
	
	
	private static HashMap llenaMapaLiquidaciones(String ruta, String usuario, String anio, String mes) throws PersonalException, PersonalNotFoundException {
		/*
		 * Este pana arma el HashMap de parametros, dada una instancia de una
		 * solicitud
		 */
		HashMap params = new HashMap();
		Locale locale = new java.util.Locale("es", "VE");
		params.put("REPORT_LOCALE", locale);

		params.put("ruta", ruta);
		//params.put("usuario", usuario);
		params.put("anio", Integer.parseInt(anio));
		params.put("mes", Integer.parseInt(mes));
		return params;
	}
	
	//Este es para estadística
	@SuppressWarnings("unused")
	private static HashMap llenaMapaEstadistica(String ruta, String desde, String hasta) throws PersonalException, PersonalNotFoundException {
		/*
		 * Este pana arma el HashMap de parametros, dada una instancia de una
		 * solicitud
		 */
		HashMap params = new HashMap();
		Locale locale = new java.util.Locale("es", "VE");
		params.put("REPORT_LOCALE", locale);
		System.out.println("ruta : " + ruta);
		System.out.println("desde : " + desde);
		System.out.println("hasta : " + hasta);
		params.put("ruta", ruta);
		//params.put("usuario", usuario);
		params.put("desde", desde);
		params.put("hasta", hasta);
		return params;
	}
}
