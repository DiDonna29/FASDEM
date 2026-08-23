/**15/07/2010
 * marcenrl
 */
package ve.gob.dem.fasdem.exp.extension;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;

import ve.gob.dem.fasdem.bean.Autorizacion;
import ve.gob.dem.fasdem.bean.PreOrdenPago;
import ve.gob.dem.fasdem.persit.extension.PerAutorizacion;
import ve.gob.dem.fasdem.persit.pagos.PerPreOrdenPago;
import ve.gob.dem.framework.cnx.Conexion;
import ve.gob.dem.framework.exception.PersonalException;
import ve.gob.dem.framework.exception.PersonalNotFoundException;

public class ExpExtension {
	public static ArrayList BuscarListaPorSiniestro(int idsin) throws NamingException, SQLException, PersonalNotFoundException {
		Connection c = null;
		try {
			c = Conexion.getConexion();
			PerAutorizacion p = new PerAutorizacion(c);
			return p.buscarListaSiniestro(idsin);
		} finally {
			Conexion.closeConexion(c);
		}
	}

	public static void crearAutorizacion(Autorizacion auto) throws SQLException, PersonalNotFoundException, ClassNotFoundException, IOException, NamingException, PersonalException {
		Connection cnx = null;
		try {
			cnx = Conexion.getConexion();
			PerAutorizacion p = new PerAutorizacion(cnx);
			p.InsertarAutorizacion(auto);
		} finally {
			if (cnx != null)
				Conexion.closeConexion(cnx);
		}
	}

	public static void eliminarAutorizacion(int id) throws SQLException, PersonalNotFoundException, ClassNotFoundException, IOException, NamingException, PersonalException {
		Connection cnx = null;
		try {
			cnx = Conexion.getConexion();
			PerAutorizacion p = new PerAutorizacion(cnx);
			p.EliminarAutorizacion(id);
		} finally {
			if (cnx != null)
				Conexion.closeConexion(cnx);
		}
	}
}
