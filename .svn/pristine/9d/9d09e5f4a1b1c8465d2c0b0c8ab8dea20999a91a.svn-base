/**15/07/2010
 * marcenrl
 */
package ve.gob.dem.fasdem.persit.administrador;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.NamingException;
import org.apache.log4j.Logger;

import ve.gob.dem.fasdem.bean.Clinica;
import ve.gob.dem.fasdem.bean.Especialidad;
import ve.gob.dem.fasdem.bean.EstatusPreOrden;
import ve.gob.dem.fasdem.bean.Poliza;
import ve.gob.dem.fasdem.bean.TipoEmpleado;
import ve.gob.dem.framework.exception.PersonalException;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.recursos.Errores;
import ve.gob.dem.framework.recursos.Utilidad;

/**
 * @author marcenrl
 * 
 */
@SuppressWarnings("unchecked")
public class PerPoliza {
	private Connection cnx = null;
	static protected Logger log = Logger.getLogger(PerPoliza.class);

	public PerPoliza(Connection c) {
		this.cnx = c;
	}

	private ArrayList buscar(String arg) throws SQLException, PersonalNotFoundException, NamingException {
		ArrayList array = new ArrayList();
		ResultSet rs = null;
		Statement sentencia = null;
		String sql = "SELECT * FROM poliza ";
		if (arg != null) {
			sql = sql + " " + arg;
			log.info("sql"+ sql);
		}
		try {
			sentencia = cnx.createStatement();
			rs = sentencia.executeQuery(sql);
			while (rs.next()) {
				Poliza e = new Poliza();
				e.setId(rs.getInt("id_poliza"));
				e.setDescripcion(rs.getString("descripcion"));
				e.setFechaInicio(rs.getDate("fecha_inicio"));
				e.setFechaFin(rs.getDate("fecha_fin"));
				e.setActivo(rs.getBoolean("is_activo"));
				array.add(e);
			}
			if (array.size() == 0) {
				throw new PersonalNotFoundException(new Errores(Errores.ERR_ELEMENTO_NOFOUND, Errores.buscarDescripcion(Errores.ERR_ELEMENTO_NOFOUND), "PerElemento", "buscar(argumento)"));
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

	private boolean buscarExite(String arg) throws SQLException {
		ResultSet rs = null;
		Statement sentencia = null;
		boolean existe = false;
		String sql = "SELECT id_poliza FROM poliza ";
		if (arg != null) {
			sql = sql + " " + arg;
		}
		log.info("EXISTE POLIZA: " + sql);
		try {
			sentencia = cnx.createStatement();
			rs = sentencia.executeQuery(sql);
			while (rs.next()) {
				existe = true;
			}
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
		return existe;
	}

	public Poliza buscarPorId(int id) throws SQLException, PersonalNotFoundException, NamingException {
		return (Poliza) this.buscar("  WHERE id_poliza=" + id).get(0);
	}

	
	public Poliza buscarPoliza(Poliza pol) throws SQLException, PersonalNotFoundException, NamingException, PersonalException {
		return (Poliza) this.buscar("  WHERE descripcion='" + pol.getDescripcion()+"' and fecha_inicio='" + Utilidad.DateToString(pol.getFechaInicio(), "yyyy-MM-dd") + "'"+" and fecha_fin='" + Utilidad.DateToString(pol.getFechaFin(), "yyyy-MM-dd") + "'").get(0);
	}

	
	
	
	public Poliza buscarActiva() throws SQLException, PersonalNotFoundException, NamingException {
		return (Poliza) this.buscar("  WHERE is_activo = true").get(0);
	}

	public boolean validarFechaPolizaInicio(Poliza pol) throws SQLException, PersonalException {
		return this.buscarExite("  WHERE '" + Utilidad.DateToString(pol.getFechaInicio(), "yyyy-MM-dd") + "' between fecha_inicio and fecha_fin");
	}

	public boolean validarFechaPolizaFin(Poliza pol) throws SQLException, PersonalNotFoundException, NamingException {
		try {
			return this.buscarExite("  WHERE '" + Utilidad.DateToString(pol.getFechaFin(), "yyyy-MM-dd") + "' between fecha_inicio and fecha_fin");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		return true;
	}

	public boolean validarNombrePoliza(Poliza pol) throws SQLException, PersonalNotFoundException, NamingException {
		return this.buscarExite("  WHERE Upper(descripcion)= Upper('" + pol.getDescripcion() + "')");
	}

	public ArrayList buscarLista() throws SQLException, PersonalNotFoundException, NamingException {
		return (ArrayList) this.buscar(" order by fecha_inicio asc");
	}

	public void crearPoliza(Poliza poliza) throws SQLException, ClassNotFoundException, IOException {
		PreparedStatement sentencia = null;
		int id_proveedor = 0;
		String sql = "INSERT INTO poliza (DESCRIPCION,fecha_inicio, fecha_fin,is_activo) VALUES (UPPER(?),?,?,false)";
		try {
			sentencia = cnx.prepareStatement(sql);
			sentencia.setString(1, poliza.getDescripcion());
			sentencia.setDate(2, new java.sql.Date(poliza.getFechaInicio().getTime()));
			sentencia.setDate(3, new java.sql.Date(poliza.getFechaFin().getTime()));
			sentencia.executeUpdate();
		} catch (Exception e) {
			// log.info("BUSCA JC:"+sql2);
			log.info(e);
		} finally {
			if (sentencia != null)
				sentencia.close();
		}
	}

	public void modificarPoliza(Poliza poliza) throws SQLException, ClassNotFoundException, IOException {
		PreparedStatement sentencia = null;
		String sql = "UPDATE poliza set DESCRIPCION=? ,fecha_inicio=cast(? as Date),fecha_fin=cast(? as Date),is_activo=? where id_poliza = ?";
		 log.info("BUSCA :"+sql);
		try {
			sentencia = cnx.prepareStatement(sql);
			sentencia.setString(1, poliza.getDescripcion());
			sentencia.setString(2,(poliza.getFechaInicioString()));
			sentencia.setString(3, (poliza.getFechaFinString()));
			sentencia.setBoolean(4, poliza.isActivo());
			sentencia.setInt(5, poliza.getId());
//			 log.info("fi :"+new java.sql.Date(poliza.getFechaInicio().getTime()));
//			 log.info("f2  :"+ new java.sql.Date(poliza.getFechaFin().getTime()));
//			 log.info("BUSCA2 :"+sql);
			sentencia.executeUpdate();
		} catch (Exception e) {
		
			log.info("", e);
		} finally {
			if (sentencia != null)
				sentencia.close();
		}
	}
}
