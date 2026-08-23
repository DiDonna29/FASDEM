/**
 * 15/07/2010 marcenrl
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
import ve.gob.dem.fasdem.bean.DetalleFacturaPago;
import ve.gob.dem.fasdem.bean.PreOrdenPago;
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
 */
public class ExpDetalleFacturaPago {
	static protected Logger log = Logger.getLogger(ExpDetalleFacturaPago.class);

	public static ArrayList buscarListaProveedorFechaSiniestro(int tipo_empleado, int id_proveedor, String desde, String hasta, int anio) throws NamingException, SQLException, PersonalNotFoundException, ClassNotFoundException {
		Connection cnx = null;
		try {
			cnx = Conexion.getConexion();
			PerDetalleFacturaPago fp = new PerDetalleFacturaPago(cnx);
			return fp.buscarListaProveedorFechaSiniestro(tipo_empleado, id_proveedor, desde, hasta, anio);
		} finally {
			Conexion.closeConexion(cnx);
		}
	}

	public static ArrayList buscarListaCodigoSiniestro(String codigo, int anio) throws NamingException, SQLException, PersonalNotFoundException, ClassNotFoundException {
		Connection cnx = null;
		try {
			cnx = Conexion.getConexion();
			PerDetalleFacturaPago fp = new PerDetalleFacturaPago(cnx);
			return fp.buscarListaCodigoSiniestro(codigo, anio);
		} finally {
			Conexion.closeConexion(cnx);
		}
	}

	public static ArrayList<DetalleFacturaPago> buscarListaCodigoSiniestroEstatus(String codigo, String anio) throws NamingException, SQLException, PersonalNotFoundException, ClassNotFoundException {
		Connection cnx = null;
		try {
			cnx = Conexion.getConexion();
			PerDetalleFacturaPago fp = new PerDetalleFacturaPago(cnx);
			return fp.buscarListaCodigoSiniestroEstatus(codigo, anio);
		} finally {
			Conexion.closeConexion(cnx);
		}
	}

	public static void quitarFacturaPreorden(int id_factura, int anio) throws SQLException, PersonalNotFoundException, ClassNotFoundException, IOException, NamingException, PersonalException {
		Connection cnx = null;
		try {
			cnx = Conexion.getConexion();
			PerDetalleFacturaPago po = new PerDetalleFacturaPago(cnx);
			po.quitarFacturaPreorden(id_factura, anio);
		} finally {
			if (cnx != null)
				Conexion.closeConexion(cnx);
		}
	}

	public static void quitarLoteFactPreorden(String pre, int anio) throws SQLException, PersonalNotFoundException, ClassNotFoundException, IOException, NamingException, PersonalException {
		Connection cnx = null;
		try {
			cnx = Conexion.getConexion();
			PerDetalleFacturaPago po = new PerDetalleFacturaPago(cnx);
			po.quitarLoteFactPreorden(pre, anio);
		} finally {
			if (cnx != null)
				Conexion.closeConexion(cnx);
		}
	}
	
	public static int verificaPreorden(String pre, int anio) throws SQLException, PersonalNotFoundException, ClassNotFoundException, IOException, NamingException, PersonalException {
		Connection cnx = null;
		try {
			cnx = Conexion.getConexion();
			PerDetalleFacturaPago po = new PerDetalleFacturaPago(cnx);
			return po.verificaPreorden(pre, anio);
		} finally {
			if (cnx != null)
				Conexion.closeConexion(cnx);
		}
	}

	public static ArrayList buscarListaBuscaFacPorSiniestro(String codigo_sin, int anio) throws NamingException, SQLException, PersonalNotFoundException, ClassNotFoundException {
		Connection cnx = null;
		try {
			cnx = Conexion.getConexion();
			PerDetalleFacturaPago fp = new PerDetalleFacturaPago(cnx);
			return fp.buscarListaBuscaFacPorSiniestro(codigo_sin, anio);
		} finally {
			Conexion.closeConexion(cnx);
		}
	}
	
	public static ArrayList buscarListaBuscaFacPorSiniestroAnu(String codigo_sin, int anio) throws NamingException, SQLException, PersonalNotFoundException, ClassNotFoundException {
		Connection cnx = null;
		try {
			cnx = Conexion.getConexion();
			PerDetalleFacturaPago fp = new PerDetalleFacturaPago(cnx);
			return fp.buscarListaBuscaFacPorSiniestroAnu(codigo_sin, anio);
		} finally {
			Conexion.closeConexion(cnx);
		}
	}
	
	public static ArrayList buscarListaPorSiniestroAnu(int codigo_sin, int anio) throws NamingException, SQLException, PersonalNotFoundException, ClassNotFoundException {
		Connection cnx = null;
		try {
			cnx = Conexion.getConexion();
			PerDetalleFacturaPago fp = new PerDetalleFacturaPago(cnx);
			return fp.buscarListaPorSiniestroAnu(codigo_sin, anio);
		} finally {
			Conexion.closeConexion(cnx);
		}
	}

	public static int buscarSeleccionadas(String cadena, String anio) throws NamingException, SQLException, PersonalNotFoundException, ClassNotFoundException {
		Connection cnx = null;
		try {
			cnx = Conexion.getConexion();
			PerDetalleFacturaPago fp = new PerDetalleFacturaPago(cnx);
			return fp.buscarSeleccionadas(cadena, anio);
		} finally {
			Conexion.closeConexion(cnx);
		}
	}
	
	public static String buscarDescEstatus(String estatus) throws NamingException, SQLException, PersonalNotFoundException, ClassNotFoundException {
		Connection cnx = null;
		try {
			cnx = Conexion.getConexion();
			PerDetalleFacturaPago fp = new PerDetalleFacturaPago(cnx);
			return fp.buscarDescEstatus(estatus);
		} finally {
			Conexion.closeConexion(cnx);
		}
	}

	public static ArrayList buscarListaBuscaFacPorSiniestroProv(String codigo_sin, int anio, int id_prov) throws NamingException, SQLException, PersonalNotFoundException, ClassNotFoundException {
		Connection cnx = null;
		try {
			cnx = Conexion.getConexion();
			PerDetalleFacturaPago fp = new PerDetalleFacturaPago(cnx);
			return fp.buscarListaBuscaFacPorSiniestroProv(codigo_sin, anio, id_prov);
		} finally {
			Conexion.closeConexion(cnx);
		}
	}

	public static ArrayList buscarListaBuscaFacPorSiniestroTitularReemb(String codigo_sin, int anio, String ced) throws NamingException, SQLException, PersonalNotFoundException, ClassNotFoundException {
		Connection cnx = null;
		try {
			cnx = Conexion.getConexion();
			PerDetalleFacturaPago fp = new PerDetalleFacturaPago(cnx);
			return fp.buscarListaBuscaFacPorSiniestroTitularReemb(codigo_sin, anio, ced);
		} finally {
			Conexion.closeConexion(cnx);
		}
	}

	public static ArrayList buscarListaBuscaFacPorCodigo(String codigo_fact, int anio) throws NamingException, SQLException, PersonalNotFoundException, ClassNotFoundException {
		Connection cnx = null;
		try {
			cnx = Conexion.getConexion();
			PerDetalleFacturaPago fp = new PerDetalleFacturaPago(cnx);
			return fp.buscarListaBuscaFacPorCodigo(codigo_fact, anio);
		} finally {
			Conexion.closeConexion(cnx);
		}
	}

	public static ArrayList buscarListaBuscaFacPorCodigoYProv(String codigo_fact, int anio, int id_prov) throws NamingException, SQLException, PersonalNotFoundException, ClassNotFoundException {
		Connection cnx = null;
		try {
			cnx = Conexion.getConexion();
			PerDetalleFacturaPago fp = new PerDetalleFacturaPago(cnx);
			return fp.buscarListaBuscaFacPorCodigoYProv(codigo_fact, anio, id_prov);
		} finally {
			Conexion.closeConexion(cnx);
		}
	}

	public static ArrayList buscarListaBuscaFacPorCodigoYTitular(String codigo_fact, int anio, String cedula) throws NamingException, SQLException, PersonalNotFoundException, ClassNotFoundException {
		Connection cnx = null;
		try {
			cnx = Conexion.getConexion();
			PerDetalleFacturaPago fp = new PerDetalleFacturaPago(cnx);
			return fp.buscarListaBuscaFacPorCodigoYTitular(codigo_fact, anio, cedula);
		} finally {
			Conexion.closeConexion(cnx);
		}
	}

	public static ArrayList buscarListaBuscaFacPorPreOrden(String pre, int anio) throws NamingException, SQLException, PersonalNotFoundException, ClassNotFoundException {
		Connection cnx = null;
		try {
			cnx = Conexion.getConexion();
			PerDetalleFacturaPago fp = new PerDetalleFacturaPago(cnx);
			return fp.buscarListaBuscaFacPorPreOrden(pre, anio);
		} finally {
			Conexion.closeConexion(cnx);
		}
	}

	public static ArrayList buscarListaCodigoFactura(String codigo_fac, int anio) throws NamingException, SQLException, PersonalNotFoundException, ClassNotFoundException {
		Connection cnx = null;
		try {
			cnx = Conexion.getConexion();
			PerDetalleFacturaPago fp = new PerDetalleFacturaPago(cnx);
			return fp.buscarListaCodigoFactura(codigo_fac, anio);
		} finally {
			Conexion.closeConexion(cnx);
		}
	}
}
