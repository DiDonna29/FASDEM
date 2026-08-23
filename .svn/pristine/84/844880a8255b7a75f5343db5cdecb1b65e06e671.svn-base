/**15/07/2010
 * marcenrl
 */
package ve.gob.dem.fasdem.persit;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.NamingException;

import org.apache.log4j.Logger;

import ve.gob.dem.fasdem.bean.Clinica;
import ve.gob.dem.fasdem.bean.Estado;
import ve.gob.dem.fasdem.bean.Persona;
import ve.gob.dem.fasdem.exp.administrador.ExpCuenta;
import ve.gob.dem.framework.exception.PersonalNotFoundException;
import ve.gob.dem.framework.recursos.Errores;

/**
 * @author marcenrl
 * 
 */
@SuppressWarnings("unchecked")
public class PerPersona {

	
    private Connection cnx = null;
    static protected Logger log = Logger.getLogger(PerPersona.class);

    public PerPersona(Connection c) {
	this.cnx = c;
    }


    private ArrayList buscar(String arg) throws SQLException, PersonalNotFoundException, NamingException  {
	ArrayList array = new ArrayList();
	ResultSet rs = null;
	Statement sentencia = null;
	String sql = "SELECT cedula,nombres,apellidos,id_tipo_nomina FROM BENEFICIARIOS_FASDEM ";
	if (arg!=null){
	    sql = sql + " " + arg;
	    log.info("DETALLE oracle" + sql);
	}
	

	try {
		
	    sentencia = cnx.createStatement();
	    rs = sentencia.executeQuery(sql);
	    
	    
	    while (rs.next()) {
		Persona p = new Persona();
		p.setCedula(rs.getString("cedula"));
		p.setNombres(rs.getString("nombres"));
		p.setApellidos(rs.getString("apellidos"));
		p.setIdTipoEmpleado(rs.getInt("id_tipo_nomina"));
		
		array.add(p);
		
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
    

    
    public Persona buscarporCedula(String cedula) throws SQLException, PersonalNotFoundException, NamingException  {
 	   return (Persona) this.buscar("  WHERE cedula='" + cedula +"' and PARENTESCO='TITULAR'").get(0);
 }
   
    public boolean ExistePersona(String cedula) throws SQLException, PersonalNotFoundException, NamingException  {
    	ResultSet rs = null;
 
    	Statement sentencia = null;
    	boolean rep = false;
    	String sql = "SELECT cedula,nombres,apellidos FROM BENEFICIARIOS_FASDEM WHERE cedula='" + cedula +"'";
    	

    	try {
    		
    	    sentencia = cnx.createStatement();
    	    rs = sentencia.executeQuery(sql);
    	    
    	    
    	    while (rs.next()) {
    	    	rep = true;
    	    }
    	    return rep;
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

    
    public boolean ExistePersona(String cedula, int id_estado) throws SQLException, PersonalNotFoundException, NamingException  {
    	ResultSet rs = null;
 
    	Statement sentencia = null;
    	boolean rep = false;
    	String sql = "SELECT cedula,nombres,apellidos FROM BENEFICIARIOS_FASDEM_CUENTA WHERE cedula='" + cedula +"' and ID_ESTADO = "+id_estado;
    	
log.info(sql);
    	try {
    		
    	    sentencia = cnx.createStatement();
    	    rs = sentencia.executeQuery(sql);
    	    
    	    
    	    while (rs.next()) {
    	    	rep = true;
    	    }
    	    return rep;
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

    
    
    
    public boolean ExistePersonaCuenta(String cedula) throws SQLException, PersonalNotFoundException, NamingException  {
    	ResultSet rs = null;
 
    	Statement sentencia = null;
    	boolean rep = false;
    	String sql = "SELECT cedula,nombres,apellidos FROM BENEFICIARIOS_FASDEM_CUENTA WHERE cedula='" + cedula +"' ";
    	

    	try {
    		
    	    sentencia = cnx.createStatement();
    	    rs = sentencia.executeQuery(sql);
    	    
    	    
    	    while (rs.next()) {
    	    	rep = true;
    	    }
    	    return rep;
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
    
    public boolean ExistePersonaCuentaSigefirrhh(String cedula) throws SQLException, PersonalNotFoundException, NamingException  {
    	ResultSet rs = null;
 
    	Statement sentencia = null;
    	boolean rep = false;
    	String sql = "SELECT cedula FROM view_cuenta_beneficiario_f WHERE cedula='" + cedula +"' and origen='sigefirrhh'";
    	

    	try {
    		
    	    sentencia = cnx.createStatement();
    	    rs = sentencia.executeQuery(sql);
    	    
    	    
    	    while (rs.next()) {
    	    	rep = true;
    	    }
    	    return rep;
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
