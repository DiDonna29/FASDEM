/**15/07/2010
 * marcenrl
 */
package ve.gob.dem.fasdem.exp.pagos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.naming.NamingException;

import org.apache.log4j.Logger;

import ve.gob.dem.fasdem.bean.Clinica;
import ve.gob.dem.fasdem.bean.DetallePreOrdenPago;
import ve.gob.dem.fasdem.bean.PreOrdenPago;
import ve.gob.dem.fasdem.bean.ResumenPreOrdenPago;
import ve.gob.dem.fasdem.bean.SiniestroPortal;
import ve.gob.dem.fasdem.per.PerPersona;
import ve.gob.dem.fasdem.persit.PerSiniestroPortal;
import ve.gob.dem.fasdem.persit.pagos.PerDetalleFacturaPago;
import ve.gob.dem.fasdem.persit.pagos.PerPreOrdenPago;
import ve.gob.dem.framework.cnx.Conexion;
import ve.gob.dem.framework.exception.PersonalException;
import ve.gob.dem.framework.exception.PersonalNotFoundException;

/**
 * @author marcenrl
 * 
 */
public class ExpPreOrdenPago {
	static protected Logger log = Logger.getLogger(ExpPreOrdenPago.class);

	public static ArrayList buscarListaProveedorFechaPreOrden(int id_proveedor, String desde, String hasta, int id_estatus, int anio) throws NamingException, SQLException, PersonalNotFoundException, ClassNotFoundException {
		Connection cnx = null;
		try {
			cnx = Conexion.getConexion();
			PerPreOrdenPago po = new PerPreOrdenPago(cnx);
			return po.buscarListaProveedorFechaPreOrden(id_proveedor, desde, hasta, id_estatus, anio);
		} finally {
			Conexion.closeConexion(cnx);
		}
	}
	
	
	
	public static ArrayList buscarListaFechaPreOrdenHojaRuta(String desde, String hasta,int anio,int estatus) throws NamingException, SQLException, PersonalNotFoundException, ClassNotFoundException {
		Connection cnx = null;
		try {
			cnx = Conexion.getConexion();
			PerPreOrdenPago po = new PerPreOrdenPago(cnx);
			return po.buscarListaFechaPreOrdenHojaRuta(desde, hasta, anio,estatus);
		} finally {
			Conexion.closeConexion(cnx);
		}
	}
	
	public static ArrayList buscarPorCodigoPreOrdenHojaRuta(String codigo,int anio,int estatus) throws NamingException, SQLException, PersonalNotFoundException, ClassNotFoundException {
		Connection cnx = null;
		try {
			cnx = Conexion.getConexion();
			PerPreOrdenPago po = new PerPreOrdenPago(cnx);
			return po.buscarPorCodigoPreOrdenHojaRuta(codigo, anio,estatus);
		} finally {
			Conexion.closeConexion(cnx);
		}
	}
	
	public static ArrayList buscarPorCodigoPreOrdenHojaRutaAnulaONT(String codigo,int anio) throws NamingException, SQLException, PersonalNotFoundException, ClassNotFoundException {
		Connection cnx = null;
		try {
			cnx = Conexion.getConexion();
			PerPreOrdenPago po = new PerPreOrdenPago(cnx);
			return po.buscarPorCodigoPreOrdenHojaRutaAnulaONT(codigo, anio);
		} finally {
			Conexion.closeConexion(cnx);
		}
	}
	
	
	
	
	
	public static PreOrdenPago buscarPorIdPreOrdenHojaRuta(int id,int anio) throws NamingException, SQLException, PersonalNotFoundException, ClassNotFoundException {
		Connection cnx = null;
		try {
			cnx = Conexion.getConexion();
			PerPreOrdenPago po = new PerPreOrdenPago(cnx);
			return po.buscarPorIdPreOrdenHojaRuta(id, anio);
		} finally {
			Conexion.closeConexion(cnx);
		}
	}
	

	public static ArrayList buscarporCodigo(String codigo, int anio) throws NamingException, SQLException, PersonalNotFoundException, ClassNotFoundException {
		Connection cnx = null;
		try {
			cnx = Conexion.getConexion();
			PerPreOrdenPago po = new PerPreOrdenPago(cnx);
			return po.buscarporCodigo(codigo, anio);
		} finally {
			Conexion.closeConexion(cnx);
		}
	}

	public static ArrayList buscarporCodigoEstatus(String codigo, int anio, String estatus) throws NamingException, SQLException, PersonalNotFoundException, ClassNotFoundException {
		Connection cnx = null;
		try {
			cnx = Conexion.getConexion();
			PerPreOrdenPago po = new PerPreOrdenPago(cnx);
			return po.buscarporCodigoEstatus(codigo, anio, estatus);
		} finally {
			Conexion.closeConexion(cnx);
		}
	}

	public static ArrayList buscarporanioEstatus(int anio, int estatus) throws NamingException, SQLException, PersonalNotFoundException, ClassNotFoundException {
		Connection cnx = null;
		try {
			cnx = Conexion.getConexion();
			PerPreOrdenPago po = new PerPreOrdenPago(cnx);
			return po.buscarporAnioEstatus(anio, estatus);
		} finally {
			Conexion.closeConexion(cnx);
		}
	}

	public static PreOrdenPago buscarUnicoporCodigo(String codigo, int anio) throws NamingException, SQLException, PersonalNotFoundException, ClassNotFoundException {
		Connection cnx = null;
		try {
			cnx = Conexion.getConexion();
			PerPreOrdenPago po = new PerPreOrdenPago(cnx);
			return po.buscarUnicoporCodigo(codigo, anio);
		} finally {
			Conexion.closeConexion(cnx);
		}
	}

	public static String verifica(String alias,String anio,String variable) throws NamingException, SQLException, PersonalNotFoundException, ClassNotFoundException {
		Connection cnx = null;
		try {
			cnx = Conexion.getConexion();
			PerPreOrdenPago po = new PerPreOrdenPago(cnx);
			return po.verifica(alias,anio,variable);
		} finally {
			Conexion.closeConexion(cnx);
		}
	}
	public static String verificatipo(int verificapro) throws NamingException, SQLException, PersonalNotFoundException, ClassNotFoundException {
		Connection cnx = null;
		try {
			cnx = Conexion.getConexion();
			PerPreOrdenPago po = new PerPreOrdenPago(cnx);
			return po.verificatipo(verificapro);
		} finally {
			Conexion.closeConexion(cnx);
		}
	}
	public static int buscarCodigoNuevo(int anio,String aniomes) throws NamingException, SQLException, PersonalNotFoundException, ClassNotFoundException {
		Connection cnx = null;
		try {
			cnx = Conexion.getConexion();
			PerPreOrdenPago po = new PerPreOrdenPago(cnx);
			return po.buscarCodigoNuevo(anio,aniomes);
		} finally {
			Conexion.closeConexion(cnx);
		}
	}

	public static String estatusPreOrden(String preOrden) throws NamingException, SQLException, PersonalNotFoundException, ClassNotFoundException {
		Connection cnx = null;
		try {
			cnx = Conexion.getConexion();
			PerPreOrdenPago po = new PerPreOrdenPago(cnx);
			return po.estatusPreOrden(preOrden);
		} finally {
			Conexion.closeConexion(cnx);
		}
	}
	
	public static PreOrdenPago buscarIvaVacio(String preOrden) throws NamingException, SQLException, PersonalNotFoundException, ClassNotFoundException {
		Connection cnx = null;
		try {
			cnx = Conexion.getConexion();
			PerPreOrdenPago po = new PerPreOrdenPago(cnx);
			return po.buscarporIdVacio(preOrden);
		} finally {
			Conexion.closeConexion(cnx);
		}
	}

	public static void crearPreOrden(PreOrdenPago pre, String cod_completo, Double unidad_trib, int tim, int tipo, Double porcent ) throws SQLException, PersonalNotFoundException, ClassNotFoundException, IOException, NamingException, PersonalException {
		Connection cnx = null;
		try {
			cnx = Conexion.getConexion();
			PerPreOrdenPago po = new PerPreOrdenPago(cnx);
			po.crearPreOrdenPago(pre, cod_completo, unidad_trib, tim, tipo, porcent);
		} finally {
			if (cnx != null)
				Conexion.closeConexion(cnx);
		}
	}

	public static void cambiarEstatus(String codigo_pre, String numero, int estatus, Date fecha_orden, Date fecha_cheque, int anio) throws SQLException, PersonalNotFoundException, ClassNotFoundException, IOException, NamingException, PersonalException {
		Connection cnx = null;
		try {
			cnx = Conexion.getConexion();
			PerPreOrdenPago po = new PerPreOrdenPago(cnx);
			po.cambiarEstatusPreOrden(codigo_pre, numero, estatus, fecha_orden, fecha_cheque, anio);
		} finally {
			if (cnx != null)
				Conexion.closeConexion(cnx);
		}
	}

	
	
	public static void asignarHojaPreOrden(int codigo_pre, int hoja,int anio) throws SQLException, PersonalNotFoundException, ClassNotFoundException, IOException, NamingException, PersonalException {
		Connection cnx = null;
		try {
			cnx = Conexion.getConexion();
			PerPreOrdenPago po = new PerPreOrdenPago(cnx);
			po.asignarHojaPreOrden(codigo_pre,hoja,anio);
		} finally {
			if (cnx != null)
				Conexion.closeConexion(cnx);
		}
	}
	
	public static void SacarHojaPreOrden(int codigo_pre,int anio) throws SQLException, PersonalNotFoundException, ClassNotFoundException, IOException, NamingException, PersonalException {
		Connection cnx = null;
		try {
			cnx = Conexion.getConexion();
			PerPreOrdenPago po = new PerPreOrdenPago(cnx);
			po.SacarHojaPreOrden(codigo_pre,anio);
		} finally {
			if (cnx != null)
				Conexion.closeConexion(cnx);
		}
	}
	
	public static void SacarHojaPreOrden(String codigo_pre,int anio) throws SQLException, PersonalNotFoundException, ClassNotFoundException, IOException, NamingException, PersonalException {
		Connection cnx = null;
		try {
			cnx = Conexion.getConexion();
			PerPreOrdenPago po = new PerPreOrdenPago(cnx);
			po.SacarHojaPreOrden(codigo_pre,anio);
		} finally {
			if (cnx != null)
				Conexion.closeConexion(cnx);
		}
	}
	
	
	
	public static void anularPreOrden(String codigo_pre, int estatus, int anio) throws SQLException, PersonalNotFoundException, ClassNotFoundException, IOException, NamingException, PersonalException {
		Connection cnx = null;
		try {
			cnx = Conexion.getConexion();
			PerPreOrdenPago po = new PerPreOrdenPago(cnx);
			po.anularPreOrden(codigo_pre, estatus, anio);
		} finally {
			if (cnx != null)
				Conexion.closeConexion(cnx);
		}
	}

	public static void cambiarTopePreOrden(String codigo_pre, int aplica, int anio) throws SQLException, PersonalNotFoundException, ClassNotFoundException, IOException, NamingException, PersonalException {
		Connection cnx = null;
		try {
			cnx = Conexion.getConexion();
			PerPreOrdenPago po = new PerPreOrdenPago(cnx);
			po.cambiarTopePreOrden(codigo_pre, aplica, anio);
		} finally {
			if (cnx != null)
				Conexion.closeConexion(cnx);
		}
	}

	public static void cambiarEstatusPagado(String codigo_pre, int estatus, Date fecha_cheque, int anio) throws SQLException, PersonalNotFoundException, ClassNotFoundException, IOException, NamingException, PersonalException {
		Connection cnx = null;
		try {
			cnx = Conexion.getConexion();
			PerPreOrdenPago po = new PerPreOrdenPago(cnx);
			po.cambiarEstatusPagado(codigo_pre, estatus, fecha_cheque, anio);
		} finally {
			if (cnx != null)
				Conexion.closeConexion(cnx);
		}
	}

	public static void autorizarEstatusPreOrden(String codigo_pre, int estatus, int anio) throws SQLException, PersonalNotFoundException, ClassNotFoundException, IOException, NamingException, PersonalException {
		Connection cnx = null;
		try {
			cnx = Conexion.getConexion();
			PerPreOrdenPago po = new PerPreOrdenPago(cnx);
			po.autorizarEstatusPreOrden(codigo_pre, estatus, anio);
		} finally {
			if (cnx != null)
				Conexion.closeConexion(cnx);
		}
	}
	
	
	public static void administracionEstatusPreOrden(String codigo_pre, int estatus, int anio) throws SQLException, PersonalNotFoundException, ClassNotFoundException, IOException, NamingException, PersonalException {
		Connection cnx = null;
		try {
			cnx = Conexion.getConexion();
			PerPreOrdenPago po = new PerPreOrdenPago(cnx);
			po.autorizarEstatusPreOrden(codigo_pre, estatus, anio);
		} finally {
			if (cnx != null)
				Conexion.closeConexion(cnx);
		}
	}
	
	
	

	public static void agregarFacturasOrden(String cadena, String Orden, int anio) throws SQLException, PersonalNotFoundException, ClassNotFoundException, IOException, NamingException, PersonalException {
		Connection cnx = null;
		try {
			cnx = Conexion.getConexion();
			PerPreOrdenPago po = new PerPreOrdenPago(cnx);
			po.agregarFacturasOrden(cadena, Orden, anio);
		} finally {
			if (cnx != null)
				Conexion.closeConexion(cnx);
		}
	}
	
	
	
	
	
	
	public static DetallePreOrdenPago buscarDetallePreOrden(String cadena, int anio) throws NamingException, SQLException, PersonalNotFoundException, ClassNotFoundException {
		Connection cnx = null;
		try {
			cnx = Conexion.getConexion();
			PerPreOrdenPago po = new PerPreOrdenPago(cnx);
			return po.buscarDetallePreOrden(cadena, anio);
		} finally {
			Conexion.closeConexion(cnx);
		}
	}

	
	
	
	
	
	public static ArrayList buscarResumenPreOrden(String PreO, int anio) throws NamingException, SQLException, PersonalNotFoundException, ClassNotFoundException {
		Connection cnx = null;
		try {
			cnx = Conexion.getConexion();
			PerPreOrdenPago po = new PerPreOrdenPago(cnx);
			return po.buscarResumen(PreO, anio);
		} finally {
			Conexion.closeConexion(cnx);
		}
	}

	public static DetallePreOrdenPago buscarDetallePreOrdenPorCodigo(String codigo, int anio) throws NamingException, SQLException, PersonalNotFoundException, ClassNotFoundException {
		Connection cnx = null;
		try {
			cnx = Conexion.getConexion();
			PerPreOrdenPago po = new PerPreOrdenPago(cnx);
			return po.buscarDetallePreOrdenPorCodigo(codigo, anio);
		} finally {
			Conexion.closeConexion(cnx);
		}
	}
	
	public static void RechazoPreorden(String pre, int anio) throws SQLException, PersonalNotFoundException, ClassNotFoundException, IOException, NamingException, PersonalException {
		Connection cnx = null;
		try {
			cnx = Conexion.getConexion();
			PerPreOrdenPago po = new PerPreOrdenPago(cnx);
			po.RechazoPreorden(pre, anio);
		} finally {
			if (cnx != null)
				Conexion.closeConexion(cnx);
		}
	}
	
	public static void cambiarEstatusRechazo(String codigo_pre, int estatus, int anio) throws SQLException, PersonalNotFoundException, ClassNotFoundException, IOException, NamingException, PersonalException {
		Connection cnx = null;
		try {
			cnx = Conexion.getConexion();
			PerPreOrdenPago po = new PerPreOrdenPago(cnx);
			po.cambiarEstatusRechazo(codigo_pre, estatus, anio);
		} finally {
			if (cnx != null)
				Conexion.closeConexion(cnx);
		}
	}
	
	public static void buscarporCodigoCambioStatus(int codigo_pre, int estatus) throws SQLException, PersonalNotFoundException, ClassNotFoundException, IOException, NamingException, PersonalException {
		Connection cnx = null;
		try {
			cnx = Conexion.getConexion();
			PerPreOrdenPago po = new PerPreOrdenPago(cnx);
			po.buscarporCodigoCambioStatus(codigo_pre, estatus);
		} finally {
			if (cnx != null)
				Conexion.closeConexion(cnx);
		}
	}
	
	public static void buscarporCodigoCambioStatusSiniestroAnu(int codigo_pre, int estatus) throws SQLException, PersonalNotFoundException, ClassNotFoundException, IOException, NamingException, PersonalException {
		Connection cnx = null;
		try {
			cnx = Conexion.getConexion();
			PerPreOrdenPago po = new PerPreOrdenPago(cnx);
			po.buscarporCodigoCambioStatusSiniestroAnu(codigo_pre, estatus);
		} finally {
			if (cnx != null)
				Conexion.closeConexion(cnx);
		}
	}
}
