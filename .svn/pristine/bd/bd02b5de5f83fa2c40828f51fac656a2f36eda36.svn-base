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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.jfree.util.Log;

import ve.gob.dem.fasdem.bean.DetallePreOrdenPago;
import ve.gob.dem.fasdem.bean.ExpedientePago;
import ve.gob.dem.fasdem.bean.PreOrdenPago;
import ve.gob.dem.fasdem.bean.ResumenPreOrdenPago;
import ve.gob.dem.fasdem.exp.ExpClinica;
import ve.gob.dem.fasdem.exp.ExpHojaRuta;
import ve.gob.dem.fasdem.exp.ExpPersona;
import ve.gob.dem.fasdem.exp.pagos.ExpEstatusHojaRuta;
import ve.gob.dem.fasdem.exp.pagos.ExpEstatusPreorden;
import ve.gob.dem.fasdem.exp.pagos.ExpPreOrdenPago;
import ve.gob.dem.fasdem.exp.pagos.ExpTipoEmpleado;
import ve.gob.dem.framework.exception.PersonalNotFoundException;

/**
 * @author marcenrl
 * 
 */
@SuppressWarnings("unchecked")
public class PerExpedientePago {
	private Connection cnx = null;
	static protected Logger log = Logger.getLogger(PerExpedientePago.class);

	public PerExpedientePago(Connection c) {
		this.cnx = c;
	}

	private ArrayList buscar(String arg) throws SQLException, PersonalNotFoundException, NamingException, NumberFormatException, IOException {
		ArrayList array = new ArrayList();
		ResultSet rs = null;
		Statement sentencia = null;
		String sql = "SELECT * FROM hoja_ruta_preorden ";
		if (arg != null) {
			sql = sql + " " + arg;
		}
		try {
			sentencia = cnx.createStatement();
			rs = sentencia.executeQuery(sql);
			while (rs.next()) {
				ExpedientePago exp = new ExpedientePago();
				
				
				exp.setId(rs.getInt("id_hoja_ruta_preorden"));
				exp.setCodigo_preorden(rs.getString("codigo"));
				exp.setCausado(rs.getString("causado"));
				exp.setMonto(rs.getDouble("monto_pre_orden"));
				exp.setAnio(String.valueOf(rs.getInt("anio_preorden")));
				exp.setHoja(ExpHojaRuta.BuscarPorid(rs.getInt("id_hoja_ruta")));
				exp.setStatus(ExpEstatusHojaRuta.buscarpoID(rs.getInt("estatus")));
				exp.setObservacion(rs.getString("observacion"));
				
				array.add(exp);
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
	
	


	public ArrayList buscarListaEstatusAnio(String status, int anio) throws SQLException, PersonalNotFoundException, NamingException, NumberFormatException, IOException {
		return this.buscar("WHERE anio_preorden=" + anio + "  AND estatus in (" + status + ")");
	}
	

	
	


	

}
