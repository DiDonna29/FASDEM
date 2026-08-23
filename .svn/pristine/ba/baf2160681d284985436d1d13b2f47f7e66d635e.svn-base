/**15/07/2010
 * marcenrl
 */
package ve.gob.dem.fasdem.exp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.naming.NamingException;
import org.apache.log4j.Logger;
import ve.gob.dem.fasdem.bean.Clinica;
import ve.gob.dem.fasdem.bean.SiniestroPortal;
import ve.gob.dem.fasdem.per.PerPersona;
import ve.gob.dem.fasdem.persit.PerSiniestroPortal;
import ve.gob.dem.framework.cnx.Conexion;
import ve.gob.dem.framework.exception.PersonalException;
import ve.gob.dem.framework.exception.PersonalNotFoundException;


/**
 * @author marcenrl
 * 
 */

public class ExpSiniestroPortal {
	 static protected Logger log = Logger.getLogger(ExpSiniestroPortal.class);


    
    public static ArrayList buscarListaPorFecha(String fecha,String clin) throws NamingException, SQLException, PersonalNotFoundException, ClassNotFoundException  {

    	Connection cnx = null;
    	try {
    		cnx = Conexion.getConexion();
    	    PerSiniestroPortal ps = new PerSiniestroPortal(cnx);
    	    return ps.buscarListaPorFecha(fecha, clin);
    	} finally {
    	    Conexion.closeConexion(cnx);
    	}
        }
     
    
    
    public static SiniestroPortal buscarNroSiniestroClinica(String cod,String subCod,String mesanio, String clin) throws NamingException, SQLException, PersonalNotFoundException, ClassNotFoundException  {

    	Connection cnx = null;
    	try {
    		cnx = Conexion.getConexion();
    	    PerSiniestroPortal ps = new PerSiniestroPortal(cnx);
    	    return ps.buscarNroSiniestroClinica(cod,subCod,mesanio, clin);
    	} finally {
    	    Conexion.closeConexion(cnx);
    	}
        }
    
    
    
    
    public static void ActualizarEtapa(String aniomes_codigo,String nro,String pag) throws SQLException, PersonalNotFoundException, ClassNotFoundException, IOException, NamingException, PersonalException{
		Connection cnx = null;
		 try {
		   cnx = Conexion.getConexion();
		   PerSiniestroPortal ps = new PerSiniestroPortal(cnx);
		   ps.ActualizarEtapa(aniomes_codigo, nro, pag);
			 }
		 finally{
			   if (cnx!=null) Conexion.closeConexion(cnx);
		 }
	 }	
    
    
    public static SiniestroPortal buscarClavePorClinicaFechaBenef(String fecha,String clin,int cedula) throws NamingException, SQLException, PersonalNotFoundException, ClassNotFoundException  {

    	Connection cnx = null;
    	try {
    		cnx = Conexion.getConexion();
    	    PerSiniestroPortal ps = new PerSiniestroPortal(cnx);
    	    return ps.buscarClavePorClinicaFechaBenef(fecha,clin,cedula);
    	} finally {
    	    Conexion.closeConexion(cnx);
    	}
        }
     
    
}
