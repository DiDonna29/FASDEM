package ve.gob.dem.framework.seguridad.persistencia;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.global.GenericAction;


public class PerIdAcceso implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7910595058077506085L;
	private static PerIdAcceso instancia;

	private PerIdAcceso() {
		super();
	}

	public static synchronized PerIdAcceso getInstance() {
		if (instancia == null) {
			instancia = new PerIdAcceso();
		}
		return instancia;
	}

	/**
	 * Retorna un ID ingreso de datos.
	 * 
	 * @return
	 * @throws PersonalNotFoundException 
	 * @throws PersonalNotFoundException
	 * @throws SQLException 
	 * @throws PersonalCriticalException
	 */
	public synchronized String getIDAcceso(Connection conexion) throws PersonalNotFoundException, SQLException {
		ResultSet consulta = null;
		Statement sentencia = null;
		String resultado = null;
		try {
			sentencia = conexion.createStatement();
			consulta = sentencia.executeQuery("SELECT S_TRAZA_ACCESO.nextval FROM dual");
			if (consulta.next()) {
				resultado = ((consulta.getString("nextval") != null ? consulta.getString("nextval") : ""));
			}
			if (resultado == null) {
				// No encontro nada
				throw new PersonalNotFoundException(GenericAction.KEY_NOTFOUND);
			}
		} finally {
			try {
				if (consulta != null)
					consulta.close();
				if (sentencia != null)
					sentencia.close();
			} catch (SQLException e) {
			}
		}
		return resultado;
	}

	private Object readResolve() throws ObjectStreamException {
		return instancia;
	}
}
