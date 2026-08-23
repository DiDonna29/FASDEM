/**
 * 15/07/2010 marcenrl
 */
package ve.gob.dem.fasdem.persit.administrador;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.apache.log4j.Logger;

/**
 * @author marcenrl
 */
@SuppressWarnings("unchecked")
public class PerSiniestro {
	private Connection cnx = null;
	static protected Logger log = Logger.getLogger(PerSiniestro.class);

	public PerSiniestro(Connection c) {
		this.cnx = c;
	}

	public void modificarEstatus(String id_siniestro, String anio) throws Exception {
		PreparedStatement sentencia = null;
		String sql = "UPDATE siniestro set id_estatus=CASE WHEN id_tipo_tramite = 3 THEN 1 ELSE 9 END  where anio_siniestro = ? and id_siniestro=?";
		try {
			sentencia = cnx.prepareStatement(sql);
			sentencia.setInt(1, Integer.parseInt(anio));
			sentencia.setInt(2, Integer.parseInt(id_siniestro));
			sentencia.executeUpdate();
		} catch (Exception e) {
			//log.info("BUSCA JC:"+sql2); 
			log.info("error", e);
			throw e;
		} finally {
			if (sentencia != null)
				sentencia.close();
		}
	}
}
