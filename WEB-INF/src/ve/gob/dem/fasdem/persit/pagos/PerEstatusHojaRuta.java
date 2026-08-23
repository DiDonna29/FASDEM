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
import ve.gob.dem.fasdem.bean.EstatusHojaRuta;
import ve.gob.dem.fasdem.bean.EstatusPreOrden;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.recursos.Errores;

/**
 * @author marcenrl
 * 
 */
@SuppressWarnings("unchecked")
public class PerEstatusHojaRuta {

	
    private Connection cnx = null;
    static protected Logger log = Logger.getLogger(PerEstatusHojaRuta.class);

    public PerEstatusHojaRuta(Connection c) {
	this.cnx = c;
    }


    private ArrayList buscar(String arg) throws SQLException, PersonalNotFoundException, NamingException  {
	ArrayList array = new ArrayList();
	ResultSet rs = null;
	Statement sentencia = null;
	String sql = "SELECT * FROM estatus_hoja_Ruta ";
	if (arg!=null){
	    sql = sql + " " + arg;
	}
	
		
	try {
	    sentencia = cnx.createStatement();
	    rs = sentencia.executeQuery(sql);
	    while (rs.next()) {
		EstatusHojaRuta e = new EstatusHojaRuta();
		e.setId(rs.getInt("id_estatus"));
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
    
    


    public EstatusHojaRuta buscarPorId(int id) throws SQLException, PersonalNotFoundException, NamingException  {
	       return (EstatusHojaRuta) this.buscar("  WHERE id_estatus=" + id ).get(0);
    }
    
    public ArrayList buscarLista() throws SQLException, PersonalNotFoundException, NamingException  {
    	   return (ArrayList) this.buscar("");
    }
   
 
   
    
    
    
   
}
