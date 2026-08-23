/**15/07/2010
 * marcenrl
 */
package ve.gob.dem.fasdem.persit.pagos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.jfree.util.Log;

import ve.gob.dem.fasdem.bean.DetalleFacturaPago;
import ve.gob.dem.fasdem.bean.Persona;
import ve.gob.dem.fasdem.bean.PreOrdenPago;
import ve.gob.dem.fasdem.bean.SiniestroBandeja;
import ve.gob.dem.fasdem.exp.ExpClinica;
import ve.gob.dem.fasdem.exp.pagos.ExpTipoEmpleado;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.recursos.Constantes;
import ve.gob.dem.framework.recursos.Errores;

/**
 * @author marcenrl
 * 
 */
@SuppressWarnings("unchecked")
public class PerDetalleFacturaPago {
	private Connection cnx = null;
	static protected Logger log = Logger.getLogger(PerDetalleFacturaPago.class);

	public PerDetalleFacturaPago(Connection c) {
		this.cnx = c;
	}

	private ArrayList buscar(String arg, int anio) throws SQLException, PersonalNotFoundException, NamingException {
		ArrayList array = new ArrayList();
		ResultSet rs = null;
		Statement sentencia = null;
		String sql = "select " + " id_factura, " + " nro_factura, " + " monto_total, " + " sini.id_siniestro, " + " fecha_factura, " + " id_proveedor, " + " cedula,nombres,apellidos, " + " siniestro_comp as codigo_completo, " + " id_tipo_tramite, " + " id_tipo_empleado, " + " codigo_siniestro, " + " subcodigo_siniestro, " + " aniomes_codigo, " + " pre_orden, " + " id_estatus " + " from  " + " ( " + " select " + " pre_orden, " + " id_factura, " + " nro_factura, " + " monto_total, " + " id_siniestro as id_sin, " + " fecha_factura,id_siniestro " + " from facturas where is_factura is true "
				+ " and anio_siniestro=" + anio + " ) fact, " + " ( " + " select  " + " id_estatus as id_es, " + " id_siniestro, " + " codigo_siniestro,  " + " subcodigo_siniestro, " + " id_proveedor, " + " cedula,nombres,apellidos, " + " id_estatus, " + " fecha_registro, " + " aniomes_codigo, " + " aniomes_codigo||codigo_siniestro||subcodigo_siniestro as siniestro_comp, " + " id_tipo_tramite, " + " id_tipo_empleado " + " from siniestro where anio_siniestro=" + anio + " ) sini ";
		if (arg != null) {
			sql = sql + " " + arg;
		}
		log.info("SQL " + sql);
		try {
			sentencia = cnx.createStatement();
			rs = sentencia.executeQuery(sql);
			while (rs.next()) {
				DetalleFacturaPago fp = new DetalleFacturaPago();
				fp.setId_siniestro(rs.getInt("id_siniestro"));
				fp.setCodSiniestroCompl(rs.getString("codigo_completo"));
				fp.setCodigo_siniestro(rs.getString("codigo_siniestro"));
				fp.setSub_codigo_siniestro(rs.getString("subcodigo_siniestro"));
				fp.setAniomes(rs.getString("aniomes_codigo"));
				fp.setId_factura(rs.getInt("id_factura"));
				fp.setNro_factura(rs.getString("nro_factura"));
				fp.setFecha_factura(rs.getDate("fecha_factura"));
				fp.setMonto_Factura(rs.getDouble("monto_total"));
				fp.setPreOrdendePago(rs.getString("pre_orden"));
				fp.setId_estatus(rs.getString("id_estatus"));
				Persona tit = new Persona();
				tit.setCedula(rs.getString("cedula"));
				tit.setNombres(rs.getString("nombres"));
				tit.setApellidos(rs.getString("apellidos"));
				fp.setTitular(tit);
				try {
					fp.setProveedor(ExpClinica.BuscarPorid(rs.getInt("id_proveedor")));
				} catch (Exception e) {
				}
				try {
					fp.setTipoEmpleado(ExpTipoEmpleado.buscarporID(rs.getInt("id_tipo_empleado")));
				} catch (Exception e) {
				}
				array.add(fp);
			}
			return array;
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (sentencia != null)
					sentencia.close();
			} catch (SQLException e) {
				log.error(e);
			}
		}
	}
	
	private ArrayList buscarAnulado(String arg, int anio) throws SQLException, PersonalNotFoundException, NamingException {
		ArrayList array = new ArrayList();
		ResultSet rs = null;
		Statement sentencia = null;
		String sql = " select sini.id_siniestro, siniestro_comp as codigo_completo,  id_tipo_tramite, id_tipo_empleado,  codigo_siniestro,  subcodigo_siniestro,  aniomes_codigo,  id_estatus "
				+ "from  (select   id_estatus as id_es,  id_siniestro,  codigo_siniestro,   subcodigo_siniestro,  id_proveedor,  cedula,nombres,apellidos,  id_estatus,  fecha_registro,  "
				+ "aniomes_codigo,  aniomes_codigo||codigo_siniestro||subcodigo_siniestro as siniestro_comp,  id_tipo_tramite,  id_tipo_empleado  from siniestro where anio_siniestro=" + anio + "  ) sini  ";
		if (arg != null) {
			sql = sql + " " + arg;
		}
		log.info("SQL " + sql);
		try {
			sentencia = cnx.createStatement();
			rs = sentencia.executeQuery(sql);
			while (rs.next()) {
				DetalleFacturaPago fp = new DetalleFacturaPago();
				fp.setId_siniestro(rs.getInt("id_siniestro"));
				fp.setCodSiniestroCompl(rs.getString("codigo_completo"));
				fp.setCodigo_siniestro(rs.getString("codigo_siniestro"));
				fp.setSub_codigo_siniestro(rs.getString("subcodigo_siniestro"));
				fp.setAniomes(rs.getString("aniomes_codigo"));
				fp.setId_estatus(rs.getString("id_estatus"));
				try {
					fp.setTipoEmpleado(ExpTipoEmpleado.buscarporID(rs.getInt("id_tipo_empleado")));
				} catch (Exception e) {
				}
				array.add(fp);
			}
			return array;
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (sentencia != null)
					sentencia.close();
			} catch (SQLException e) {
				log.error(e);
			}
		}
	}

	private ArrayList buscarSinietro(String codigo, String anio) throws SQLException, PersonalNotFoundException, NamingException {
		ArrayList array = new ArrayList();
		ResultSet rs = null;
		Statement sentencia = null;
		String sql = "select " + "id_factura,  " + "nro_factura,  " + "monto_total, " + "siniestro.id_siniestro,  " + "fecha_factura, " + "id_proveedor,  " + "cedula, " + "nombres, " + "apellidos,  " + "aniomes_codigo||codigo_siniestro||subcodigo_siniestro as codigo_completo, " + "tipo_tramite.descripcion as tipo_tramite,  " + "id_tipo_empleado,  " + "codigo_siniestro,  " + "subcodigo_siniestro,  " + "aniomes_codigo,  " + "pre_orden,  " + "estatus_siniestro.descripcion as estatus,  " + "siniestro.anio_siniestro, " + "tipo_siniestro.descripcion as tipo_siniestro " + "from " + "siniestro "
				+ "INNER JOIN facturas ON (siniestro.anio_siniestro = " + anio + " and siniestro.anio_siniestro= facturas.anio_siniestro and siniestro.id_siniestro=facturas.id_siniestro and is_factura is true and aniomes_codigo||codigo_siniestro||subcodigo_siniestro='" + codigo + "') " + "INNER JOIN estatus_siniestro ON (siniestro.id_estatus = estatus_siniestro.id_estatus_siniestro) " + "INNER JOIN tipo_siniestro ON (siniestro.id_tipo_siniestro = tipo_siniestro.id_tipo_siniestro) " + "INNER JOIN tipo_tramite ON (siniestro.id_tipo_tramite=tipo_tramite.id_tipo_tramite) limit 1 ";
		log.info("SQL " + sql);
		try {
			sentencia = cnx.createStatement();
			rs = sentencia.executeQuery(sql);
			while (rs.next()) {
				DetalleFacturaPago fp = new DetalleFacturaPago();
				fp.setId_siniestro(rs.getInt("id_siniestro"));
				fp.setCodigo_siniestro(rs.getString("codigo_siniestro"));
				fp.setSub_codigo_siniestro(rs.getString("subcodigo_siniestro"));
				fp.setAniomes(rs.getString("aniomes_codigo"));
				fp.setAnioSiniestro(rs.getString("anio_siniestro"));
				fp.setId_factura(rs.getInt("id_factura"));
				fp.setNro_factura(rs.getString("nro_factura"));
				fp.setFecha_factura(rs.getDate("fecha_factura"));
				fp.setMonto_Factura(rs.getDouble("monto_total"));
				fp.setPreOrdendePago(rs.getString("pre_orden"));
				fp.setProveedor(ExpClinica.BuscarPorid(rs.getInt("id_proveedor")));
				fp.setTipoSiniestro(rs.getString("tipo_Siniestro"));
				fp.setTipoTramiteSiniestro(rs.getString("tipo_tramite"));
				fp.setEstatusSiniestro(rs.getString("estatus"));
				Persona tit = new Persona();
				tit.setCedula(rs.getString("cedula"));
				tit.setNombres(rs.getString("nombres"));
				tit.setApellidos(rs.getString("apellidos"));
				fp.setTitular(tit);
				try {
					fp.setProveedor(ExpClinica.BuscarPorid(rs.getInt("id_proveedor")));
				} catch (Exception e) {
					log.error("error", e);
				}
				try {
					fp.setTipoEmpleado(ExpTipoEmpleado.buscarporID(rs.getInt("id_tipo_empleado")));
				} catch (Exception e) {
					log.error("error", e);
				}
				array.add(fp);
			}
			return array;
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (sentencia != null)
					sentencia.close();
			} catch (SQLException e) {
				log.error(e);
			}
		}
	}

	public int buscarSeleccionadas(String cadena, String anio) throws SQLException, PersonalNotFoundException, NamingException {
		ResultSet rs = null;
		Statement sentencia = null;
		String sql = "select count(id_factura) as cantidad from facturas where anio_siniestro=" + anio + "  and pre_orden!='' and is_factura=true and id_factura in (" + cadena + ")";
		int cantidad = 0;
		try {
			sentencia = cnx.createStatement();
			rs = sentencia.executeQuery(sql);
			while (rs.next()) {
				cantidad = rs.getInt("cantidad");
			}
			return cantidad;
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (sentencia != null)
					sentencia.close();
			} catch (SQLException e) {
				log.error(e);
			}
		}
	}
	
	
	public String buscarDescEstatus(String estatus) throws SQLException, PersonalNotFoundException, NamingException {
		ResultSet rs = null;
		Statement sentencia = null;
		String sql = "select descripcion from estatus_siniestro where  id_estatus_siniestro = (" + estatus + ")";
		String desc = null;
		try {
			sentencia = cnx.createStatement();
			rs = sentencia.executeQuery(sql);
			while (rs.next()) {
				desc = rs.getString("descripcion");
			}
			return desc;
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (sentencia != null)
					sentencia.close();
			} catch (SQLException e) {
				log.error(e);
			}
		}
	}

	public ArrayList buscarListaProveedorFechaSiniestro(int tipo_empleado, int id_proveedor, String desde, String hasta, int anio) throws SQLException, PersonalNotFoundException, NamingException {
		return this.buscar("WHERE id_tipo_empleado=" + tipo_empleado + " AND  SINI.id_siniestro=FACT.id_siniestro AND PRE_ORDEN IS NULL AND ID_ESTATUS=4 AND ID_PROVEEDOR=" + id_proveedor + " AND TO_DATE(TO_CHAR(fecha_registro,'DD/MM/YYYY'),'DD/MM/YYYY')>= TO_DATE('" + desde + "','DD/MM/YYYY') AND TO_DATE(TO_CHAR(fecha_registro ,'DD/MM/YYYY'),'DD/MM/YYYY')<= TO_DATE('" + hasta + "','DD/MM/YYYY') ORDER BY cast(aniomes_codigo as int),cast(codigo_siniestro as int),cast(subcodigo_siniestro as int) ", anio);
	}

	public ArrayList buscarListaCodigoSiniestro(String codigo, int anio) throws SQLException, PersonalNotFoundException, NamingException {
		return this.buscar("WHERE SINI.id_siniestro=FACT.id_siniestro and siniestro_comp='" + codigo + "' ", anio);
	}

	public ArrayList<DetalleFacturaPago> buscarListaCodigoSiniestroEstatus(String codigo, String anio) throws SQLException, PersonalNotFoundException, NamingException {
		return this.buscarSinietro(codigo, anio);
	}

	public ArrayList buscarListaCodigoFactura(String fac, int anio) throws SQLException, PersonalNotFoundException, NamingException {
		return this.buscar("WHERE SINI.id_siniestro=FACT.id_siniestro and nro_factura='" + fac + "' ", anio);
	}

	public ArrayList buscarListaBuscaFacPorSiniestro(String Codigo, int anio) throws SQLException, PersonalNotFoundException, NamingException {
		return this.buscar("WHERE siniestro_comp='" + Codigo + "' AND  SINI.id_siniestro=FACT.id_siniestro AND PRE_ORDEN IS NULL AND ID_ESTATUS=4  ", anio);
	}
	
	public ArrayList buscarListaBuscaFacPorSiniestroAnu(String Codigo, int anio) throws SQLException, PersonalNotFoundException, NamingException {
		return this.buscarAnulado("WHERE siniestro_comp='" + Codigo + "'", anio);
	}
	
	public ArrayList buscarListaPorSiniestroAnu(int Codigo, int anio) throws SQLException, PersonalNotFoundException, NamingException {
		return this.buscar("WHERE id_siniestro='" + Codigo + "' ", anio);
	}

	public ArrayList buscarListaBuscaFacPorSiniestroProv(String Codigo, int anio, int id_prov) throws SQLException, PersonalNotFoundException, NamingException {
		return this.buscar("WHERE SINI.id_proveedor=" + id_prov + "  AND siniestro_comp='" + Codigo + "' AND  SINI.id_siniestro=FACT.id_siniestro AND PRE_ORDEN IS NULL AND ID_ESTATUS=4  ", anio);
	}

	public ArrayList buscarListaBuscaFacPorSiniestroTitularReemb(String Codigo, int anio, String cedula) throws SQLException, PersonalNotFoundException, NamingException {
		return this.buscar("WHERE id_tipo_tramite=3 AND cedula='" + cedula + "' AND  siniestro_comp='" + Codigo + "' AND  SINI.id_siniestro=FACT.id_siniestro AND PRE_ORDEN IS NULL AND ID_ESTATUS=4  ", anio);
	}

	public ArrayList buscarListaBuscaFacPorCodigo(String Codigo_fac, int anio) throws SQLException, PersonalNotFoundException, NamingException {
		return this.buscar("WHERE nro_factura='" + Codigo_fac + "' AND  SINI.id_siniestro=FACT.id_siniestro AND PRE_ORDEN IS NULL AND ID_ESTATUS=4  ", anio);
	}

	public ArrayList buscarListaBuscaFacPorCodigoYProv(String Codigo_fac, int anio, int id_prov) throws SQLException, PersonalNotFoundException, NamingException {
		return this.buscar("WHERE SINI.id_proveedor=" + id_prov + "  AND  nro_factura='" + Codigo_fac + "' AND  SINI.id_siniestro=FACT.id_siniestro AND PRE_ORDEN IS NULL AND ID_ESTATUS=4  ", anio);
	}

	public ArrayList buscarListaBuscaFacPorCodigoYTitular(String Codigo_fac, int anio, String cedula) throws SQLException, PersonalNotFoundException, NamingException {
		return this.buscar("WHERE id_tipo_tramite=3 AND cedula='" + cedula + "' AND nro_factura='" + Codigo_fac + "' AND  SINI.id_siniestro=FACT.id_siniestro AND PRE_ORDEN IS NULL AND ID_ESTATUS=4  ", anio);
	}

	public ArrayList buscarListaBuscaFacPorPreOrden(String pre, int anio) throws SQLException, PersonalNotFoundException, NamingException {
		return this.buscar("WHERE SINI.id_siniestro=FACT.id_siniestro AND pre_orden ='" + pre + "'", anio);
	}

	// revisar
	// ------------------------------------------------------------------------
	public void quitarFacturaPreorden(int id_factura, int anio) throws SQLException, ClassNotFoundException, IOException {
		PreparedStatement sentencia = null;
		String sql = "UPDATE facturas SET pre_orden=null, fecha_pre_orden=null where id_factura=? and anio_siniestro=? ";
		try {
			sentencia = cnx.prepareStatement(sql);
			sentencia.setInt(1, id_factura);
			sentencia.setInt(2, anio);
			sentencia.executeUpdate();
		} finally {
			if (sentencia != null)
				sentencia.close();
		}
	}

	public void quitarLoteFactPreorden(String pre, int anio) throws SQLException, ClassNotFoundException, IOException {
		PreparedStatement sentencia = null;
		String sql = "UPDATE facturas SET pre_orden=null, fecha_pre_orden=null where pre_orden=? and anio_siniestro=? ";
		try {
			sentencia = cnx.prepareStatement(sql);
			sentencia.setString(1, pre);
			sentencia.setInt(2, anio);
			sentencia.executeUpdate();
		} finally {
			if (sentencia != null)
				sentencia.close();
		}
	}
	
	public int verificaPreorden(String pre, int anio) throws SQLException, PersonalNotFoundException, NamingException {
		ResultSet rs = null;
		Statement sentencia = null;
		String sql = "select hoja_ruta from pre_orden_pago where anio_preordenpago=" + anio + "  and pre_orden= '"+ pre +"'" ;
		log.info("SQL " + sql);
		int hoja_ruta = 0;
		try {
			sentencia = cnx.createStatement();
			rs = sentencia.executeQuery(sql);
			while (rs.next()) {
				hoja_ruta = rs.getInt("hoja_ruta");
			}
			return hoja_ruta;
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (sentencia != null)
					sentencia.close();
			} catch (SQLException e) {
				log.error(e);
			}
		}
	}
}
