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

import javax.naming.NamingException;
import org.apache.log4j.Logger;

import ve.gob.dem.fasdem.bean.Clinica;
import ve.gob.dem.fasdem.bean.EstatusPreOrden;
import ve.gob.dem.fasdem.bean.TipoEmpleado;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.recursos.Errores;

/**
 * @author marcenrl
 * 
 */
@SuppressWarnings("unchecked")
public class PerTipoEmpleado {

	
    private Connection cnx = null;
    static protected Logger log = Logger.getLogger(PerTipoEmpleado.class);

    public PerTipoEmpleado(Connection c) {
	this.cnx = c;
    }


    private ArrayList buscar(String arg) throws SQLException, PersonalNotFoundException, NamingException  {
	ArrayList array = new ArrayList();
	ResultSet rs = null;
	Statement sentencia = null;
	String sql = "SELECT * FROM tipo_empleado ";
	if (arg!=null){
	    sql = sql + " " + arg;
	}
	
		
	try {
	    sentencia = cnx.createStatement();
	    rs = sentencia.executeQuery(sql);
	    while (rs.next()) {
		TipoEmpleado e = new TipoEmpleado();
		e.setId(rs.getInt("id_tipo_empleado"));
		e.setDescripcion(rs.getString("descripcion"));
		

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
    
    


    public TipoEmpleado buscarPorId(int id) throws SQLException, PersonalNotFoundException, NamingException  {
	       return (TipoEmpleado) this.buscar("  WHERE id_tipo_empleado=" + id ).get(0);
    }
    
    public ArrayList buscarLista() throws SQLException, PersonalNotFoundException, NamingException  {
    	   return (ArrayList) this.buscar("");
    }
   
 
    
    public void modificarTipoEmpleado(int id_siniestro, int anio,int id_tipo) throws SQLException, ClassNotFoundException, IOException {
		PreparedStatement sentencia = null;
		String sql = "UPDATE siniestro SET id_tipo_empleado=? where id_siniestro=? and anio_siniestro=? ";
		try {
			sentencia = cnx.prepareStatement(sql);
			sentencia.setInt(1, id_tipo);
			sentencia.setInt(2, id_siniestro);
			sentencia.setInt(3, anio);
			sentencia.executeUpdate();
		} finally {
			if (sentencia != null)
				sentencia.close();
		}
	}
   
    
    
    
   
}
