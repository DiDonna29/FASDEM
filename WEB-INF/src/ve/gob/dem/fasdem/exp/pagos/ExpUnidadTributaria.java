/**15/07/2010
 * marcenrl
 */
package ve.gob.dem.fasdem.exp.pagos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.naming.NamingException;
import org.apache.log4j.Logger;
import ve.gob.dem.fasdem.bean.Clinica;
import ve.gob.dem.fasdem.bean.DetallePreOrdenPago;
import ve.gob.dem.fasdem.bean.PreOrdenPago;
import ve.gob.dem.fasdem.bean.SiniestroPortal;
import ve.gob.dem.fasdem.per.PerPersona;
import ve.gob.dem.fasdem.persit.PerSiniestroPortal;
import ve.gob.dem.fasdem.persit.pagos.PerPreOrdenPago;
import ve.gob.dem.fasdem.persit.pagos.PerUnidadTributaria;
import ve.gob.dem.framework.cnx.Conexion;
import ve.gob.dem.framework.exception.PersonalException;
import ve.gob.dem.framework.exception.PersonalNotFoundException;


/**
 * @author marcenrl
 * 
 */

public class ExpUnidadTributaria {
	 static protected Logger log = Logger.getLogger(ExpUnidadTributaria.class);


    
    public static ArrayList buscarLista() throws NamingException, SQLException, PersonalNotFoundException, ClassNotFoundException  {

    	Connection cnx = null;
    	try {
    		cnx = Conexion.getConexion();
    	    PerUnidadTributaria po = new PerUnidadTributaria(cnx);
    	    return po.buscarLista();
    	} finally {
    	    Conexion.closeConexion(cnx);
    	}
        }
     
    
   
    
}
