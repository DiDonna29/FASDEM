/**15/07/2010
 * marcenrl
 */
package ve.gob.dem.fasdem.persit.pagos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.NamingException;
import org.apache.log4j.Logger;

import ve.gob.dem.fasdem.bean.Clinica;
import ve.gob.dem.fasdem.bean.EstatusPreOrden;
import ve.gob.dem.fasdem.bean.TipoEmpleado;
import ve.gob.dem.fasdem.bean.UnidadTributaria;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.recursos.Errores;

/**
 * @author marcenrl
 * 
 */
@SuppressWarnings("unchecked")
public class PerUnidadTributaria {

	
    private Connection cnx = null;
    static protected Logger log = Logger.getLogger(PerUnidadTributaria.class);

    public PerUnidadTributaria(Connection c) {
	this.cnx = c;
    }


    private ArrayList buscar(String arg) throws SQLException, PersonalNotFoundException, NamingException  {
	ArrayList array = new ArrayList();
	ResultSet rs = null;
	Statement sentencia = null;
	String sql = "SELECT * FROM UNIDAD_TRIBUTARIA ";
	if (arg!=null){
	    sql = sql + " " + arg;
	}
	
		
	try {
	    sentencia = cnx.createStatement();
	    rs = sentencia.executeQuery(sql);
	    while (rs.next()) {
			
		    UnidadTributaria u = new UnidadTributaria();
			
		    u.setId(rs.getInt("id"));
			u.setMonto_unidad(rs.getDouble("valor_unidad"));
			u.setAnio(rs.getInt("anio"));
	        u.setActivo(rs.getBoolean("is_activo"));
			array.add(u);
        
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
    
    
    public ArrayList buscarLista() throws SQLException, PersonalNotFoundException, NamingException  {
    	return (ArrayList) this.buscar("WHERE is_activo =TRUE");
    }
   
 
   
    
    
    
   
}
